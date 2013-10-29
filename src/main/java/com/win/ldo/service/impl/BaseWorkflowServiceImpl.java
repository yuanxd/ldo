package com.win.ldo.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.win.ldo.entity.BaseWorkflowEntity;
import com.win.ldo.service.BaseWorkflowService;
import com.win.ldo.service.workflow.WorkflowService;

/**
 * �������������<br>
 * ע�빤������ط���<br>
 * ע���û�����userService
 * 
 * @author Ԭ����
 * 
 */
public abstract class BaseWorkflowServiceImpl<E extends BaseWorkflowEntity, PK extends Serializable>
		extends BaseServiceImpl<E, PK> implements BaseWorkflowService<E, PK> {
	@Autowired
	protected WorkflowService workflowService;

	/**
	 * �ύ��¼����������
	 * 
	 * @param businessKey
	 *            a key that uniquely identifies the process instance in the
	 *            context or the given process definition.
	 * @param variables
	 *            the variables to pass, can be null.
	 * @return processInstanceId
	 */
	public ProcessInstance create(E entity, Map<String, Object> variables,
			String user) {
		String key = getProcessDefinitionKey();
		Assert.hasLength(key);
		return workflowService.startProcess(getProcessDefinitionKey(),
				entity.getBusinessKey(), variables, user);
	}

	@Override
	public Boolean canComplete(ActivityExecution execution) {
		return false;
	}
}
