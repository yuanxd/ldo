package com.win.ldo.service;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.runtime.ProcessInstance;

import com.win.ldo.entity.BaseWorkflowEntity;

/**
 * �������������ӿ�<br>
 * ��Ҫʹ�ù������ķ���ʵ�ִ˽ӿڣ�������WfBaseServiceImpl��
 * 
 * @author Ԭ����
 * 
 */
public interface BaseWorkflowService<E extends BaseWorkflowEntity, PK extends Serializable>
		extends BaseService<E, PK> {
	/**
	 * �ύ��¼����������<br>
	 * ����ҵ�����������̱����б������˷�������
	 * 
	 * @param businessKey
	 *            a key that uniquely identifies the process instance in the
	 *            context or the given process definition.
	 * @param variables
	 *            the variables to pass, can be null.
	 * @return processInstanceId
	 */
	public ProcessInstance create(E entity, Map<String, Object> variables,
			String user);

	/**
	 * processDefinitionKey key of process definition, cannot be null.
	 * 
	 * @return processDefinitionKey
	 */
	public String getProcessDefinitionKey();

	/**
	 * ��ǩʱ�ж������Ƿ�������
	 * 
	 * @param execution
	 * @param nrOfInstances
	 * @param nrOfActiveInstances
	 * @param nrOfCompletedInstances
	 * @param loopCounter
	 * @return
	 */
	public Boolean canComplete(ActivityExecution execution);

}
