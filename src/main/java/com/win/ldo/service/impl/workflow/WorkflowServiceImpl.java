package com.win.ldo.service.impl.workflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.win.ldo.common.GlobalData;
import com.win.ldo.service.workflow.WorkflowService;
import com.win.ldo.web.form.workflow.TaskTodoForm;

/**
 * 工作流服务基类，作为工作流访问统一接口，避免了业务与工作流过多耦合<br>
 * 注入工作流相关服务<br>
 * 注入用户服务userService
 * 
 * @author 袁晓冬
 * 
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {
	/**
	 * 流程定义缓存
	 */
	protected static Map<String, ProcessDefinition> PROCESS_DEFINITION_CACHE = new HashMap<String, ProcessDefinition>();
	/** Service to manage Users and Groups. */
	@Autowired
	protected IdentityService identityService;

	/** Service which provides access to {@link Deployment}s */
	@Autowired
	protected RuntimeService runtimeService;
	/**
	 * Service which provides access to {@link Task} and form related
	 * operations.
	 */
	@Autowired
	protected TaskService taskService;

	/**
	 * Service providing access to the repository of process definitions and
	 * deployments.
	 */
	@Autowired
	protected RepositoryService repositoryService;

	/**
	 * 发起流程处理<br>
	 * {@inheritDoc}
	 */
	@Override
	public ProcessInstance startProcess(String processDefinitionKey,
			String businessKey, Map<String, Object> variables, String userid) {
		if (null == variables) {
			variables = new HashMap<String, Object>();
		}
		variables.put("businessKey", businessKey);
		// 流程key不可为空
		Assert.hasLength(processDefinitionKey);
		identityService.setAuthenticatedUserId(userid);
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(processDefinitionKey, businessKey,
						variables);
		return processInstance;
	}

	/**
	 * {@inheritDoc}
	 */
	public void claim(String businessKey, String userid,
			String processDefinitionKey) {
		if (!StringUtils.hasLength(userid)) {
			throw new ActivitiException("can not get login user!");
		}
		// 流程定义key获取不到
		Assert.hasLength(processDefinitionKey);
		// 根据当前人未签收的任务
		Task task = taskService.createTaskQuery()
				.processDefinitionKey(processDefinitionKey)
				.taskCandidateUser(userid).active().orderByTaskPriority()
				.processInstanceBusinessKey(businessKey).desc()
				.orderByTaskCreateTime().desc().singleResult();
		if (null == task) {
			throw new ActivitiException("can not find task with businesskey:"
					+ businessKey);
		}
		taskService.claim(task.getId(), userid);
	}

	/**
	 * {@inheritDoc}
	 */
	public void complete(String businessKey, Map<String, Object> variables,
			String userid, String processDefinitionKey) {
		if (!StringUtils.hasLength(userid)) {
			throw new ActivitiException("can not get login user!");
		}
		// 流程定义key获取不到
		Assert.hasLength(processDefinitionKey);
		// 根据当前人签收的任务
		Task task = taskService.createTaskQuery()
				.processDefinitionKey(processDefinitionKey)
				.taskAssignee(userid).active().orderByTaskPriority()
				.processInstanceBusinessKey(businessKey).desc()
				.orderByTaskCreateTime().desc().singleResult();
		if (null == task) {
			throw new ActivitiException("can not find task with businesskey:"
					+ businessKey);
		}
		taskService.complete(task.getId(), variables);
	}

	public List<TaskTodoForm> queryTodos(String userid) {
		// 结果集
		List<TaskTodoForm> result = new ArrayList<TaskTodoForm>();
		// 时间格式化
		SimpleDateFormat sdf = new SimpleDateFormat(GlobalData.FORMAT_DATE);
		// 已经签收的任务
		List<Task> todoList = taskService.createTaskQuery()
				.taskAssignee(userid).active().list();
		for (Task task : todoList) {
			String processDefinitionId = task.getProcessDefinitionId();
			ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);
			TaskTodoForm singleTask = packageTaskInfo(sdf, task,
					processDefinition);
			singleTask.setStatus("todo");
			// 设置业务key
			ProcessInstance processInstance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).active()
					.singleResult();
			singleTask.setBusinessKey(processInstance.getBusinessKey());
			result.add(singleTask);
		}
		// 等待签收的任务
		List<Task> toClaimList = taskService.createTaskQuery()
				.taskCandidateUser(userid).active().list();
		for (Task task : toClaimList) {
			String processDefinitionId = task.getProcessDefinitionId();
			ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);
			TaskTodoForm singleTask = packageTaskInfo(sdf, task,
					processDefinition);
			singleTask.setStatus("claim");
			// 设置业务key
			ProcessInstance processInstance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).active()
					.singleResult();
			singleTask.setBusinessKey(processInstance.getBusinessKey());
			result.add(singleTask);
		}

		return result;
	}

	/**
	 * 根据流程定义KEY查询流程定义
	 * 
	 * @param processDefinitionId
	 *            流程定义KEY
	 * @return ProcessDefinition
	 */
	private ProcessDefinition getProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = PROCESS_DEFINITION_CACHE
				.get(processDefinitionId);
		if (processDefinition == null) {
			// 缓存中不存在时执行查询
			processDefinition = repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(processDefinitionId).singleResult();
			PROCESS_DEFINITION_CACHE
					.put(processDefinitionId, processDefinition);
		}
		return processDefinition;
	}

	/**
	 * 待办中显示数据处理
	 * 
	 * @param sdf
	 *            SimpleDateFormat
	 * @param task
	 *            Task
	 * @param processDefinition
	 *            ProcessDefinition
	 * @return
	 */
	private TaskTodoForm packageTaskInfo(SimpleDateFormat sdf, Task task,
			ProcessDefinition processDefinition) {
		TaskTodoForm form = new TaskTodoForm();
		form.setId(task.getId());
		form.setName(task.getName());
		form.setCreateTime(sdf.format(task.getCreateTime()));
		form.setPdname(processDefinition.getName());
		form.setPdversion(processDefinition.getVersion());
		form.setPid(task.getProcessInstanceId());
		return form;
	}

	@Override
	public void claim(String taskId, String userId) {
		taskService.setAssignee(taskId, userId);
	}

	/**
	 * 根据任务ID获取任务对象
	 * 
	 * @param taskId
	 * @return
	 */
	private Task getTaskByTaskId(String taskId) {
		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}

	@Override
	public void complete(String taskId, Map<String, Object> variables) {
		taskService.complete(taskId, variables);
	}

}
