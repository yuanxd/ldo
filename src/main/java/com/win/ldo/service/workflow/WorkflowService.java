package com.win.ldo.service.workflow;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.win.ldo.web.form.workflow.TaskTodoForm;

/**
 * 工作流服务基类接口<br>
 * 需要使用工作流的服务实现此接口，并集成WfBaseServiceImpl类
 * 
 * @author 袁晓冬
 * 
 */
public interface WorkflowService {

	/**
	 * *发起流程<br>
	 * 
	 * @param processDefinitionKey
	 *            工作流key
	 * @param businessKey
	 *            业务ID
	 * @param variables
	 *            流程变量
	 * @param userid
	 *            流程发起人
	 * @return ProcessInstance
	 */
	public ProcessInstance startProcess(String processDefinitionKey,
			String businessKey, Map<String, Object> variables, String userid);

	/**
	 * 根据用户标识查询用户待办任务
	 * 
	 * @param userid
	 *            用户标识
	 * @return
	 */
	public List<TaskTodoForm> queryTodos(String userid);

	/**
	 * 根据业务key签收流程
	 * 
	 * @param businessKey
	 *            业务key
	 * @param userId
	 *            任务处理人
	 */
	public void claim(String businessKey, String userId);

	/**
	 * 根据业务KEY完成工作流
	 * 
	 * @param taskId
	 *            任务ID
	 * @param variables
	 *            流程变量
	 */
	public void complete(String taskId, Map<String, Object> variables);
}
