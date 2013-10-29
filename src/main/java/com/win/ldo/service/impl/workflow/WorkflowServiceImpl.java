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
 * ������������࣬��Ϊ����������ͳһ�ӿڣ�������ҵ���빤�����������<br>
 * ע�빤������ط���<br>
 * ע���û�����userService
 * 
 * @author Ԭ����
 * 
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {
	/**
	 * ���̶��建��
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
	 * �������̴���<br>
	 * {@inheritDoc}
	 */
	@Override
	public ProcessInstance startProcess(String processDefinitionKey,
			String businessKey, Map<String, Object> variables, String userid) {
		if (null == variables) {
			variables = new HashMap<String, Object>();
		}
		variables.put("businessKey", businessKey);
		// ����key����Ϊ��
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
		// ���̶���key��ȡ����
		Assert.hasLength(processDefinitionKey);
		// ���ݵ�ǰ��δǩ�յ�����
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
		// ���̶���key��ȡ����
		Assert.hasLength(processDefinitionKey);
		// ���ݵ�ǰ��ǩ�յ�����
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
		// �����
		List<TaskTodoForm> result = new ArrayList<TaskTodoForm>();
		// ʱ���ʽ��
		SimpleDateFormat sdf = new SimpleDateFormat(GlobalData.FORMAT_DATE);
		// �Ѿ�ǩ�յ�����
		List<Task> todoList = taskService.createTaskQuery()
				.taskAssignee(userid).active().list();
		for (Task task : todoList) {
			String processDefinitionId = task.getProcessDefinitionId();
			ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);
			TaskTodoForm singleTask = packageTaskInfo(sdf, task,
					processDefinition);
			singleTask.setStatus("todo");
			// ����ҵ��key
			ProcessInstance processInstance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).active()
					.singleResult();
			singleTask.setBusinessKey(processInstance.getBusinessKey());
			result.add(singleTask);
		}
		// �ȴ�ǩ�յ�����
		List<Task> toClaimList = taskService.createTaskQuery()
				.taskCandidateUser(userid).active().list();
		for (Task task : toClaimList) {
			String processDefinitionId = task.getProcessDefinitionId();
			ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);
			TaskTodoForm singleTask = packageTaskInfo(sdf, task,
					processDefinition);
			singleTask.setStatus("claim");
			// ����ҵ��key
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
	 * �������̶���KEY��ѯ���̶���
	 * 
	 * @param processDefinitionId
	 *            ���̶���KEY
	 * @return ProcessDefinition
	 */
	private ProcessDefinition getProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = PROCESS_DEFINITION_CACHE
				.get(processDefinitionId);
		if (processDefinition == null) {
			// �����в�����ʱִ�в�ѯ
			processDefinition = repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(processDefinitionId).singleResult();
			PROCESS_DEFINITION_CACHE
					.put(processDefinitionId, processDefinition);
		}
		return processDefinition;
	}

	/**
	 * ��������ʾ���ݴ���
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
	 * ��������ID��ȡ�������
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
