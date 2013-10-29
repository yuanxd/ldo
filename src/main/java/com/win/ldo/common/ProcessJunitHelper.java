package com.win.ldo.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.win.ldo.service.workflow.WorkflowService;
import com.win.ldo.web.form.workflow.TaskTodoForm;

@Component
public class ProcessJunitHelper {
	@Autowired
	private WorkflowService workflowService;

	/**
	 * 根据用户和业务主键查询任务
	 * 
	 * @param user
	 * @param businessKey
	 * @return
	 */
	public TaskTodoForm getTaskByUserAndBusinessKey(String user,
			String businessKey) {
		List<TaskTodoForm> formList = workflowService.queryTodos(user);
		if (null == formList) {
			return null;
		}
		for (TaskTodoForm form : formList) {
			if (form.getBusinessKey().equals(businessKey)) {
				return form;
			}
		}
		return null;
	}
}
