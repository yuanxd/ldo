package com.win.ldo.service.workflow;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.win.ldo.web.form.workflow.TaskTodoForm;

/**
 * �������������ӿ�<br>
 * ��Ҫʹ�ù������ķ���ʵ�ִ˽ӿڣ�������WfBaseServiceImpl��
 * 
 * @author Ԭ����
 * 
 */
public interface WorkflowService {

	/**
	 * *��������<br>
	 * 
	 * @param processDefinitionKey
	 *            ������key
	 * @param businessKey
	 *            ҵ��ID
	 * @param variables
	 *            ���̱���
	 * @param userid
	 *            ���̷�����
	 * @return ProcessInstance
	 */
	public ProcessInstance startProcess(String processDefinitionKey,
			String businessKey, Map<String, Object> variables, String userid);

	/**
	 * �����û���ʶ��ѯ�û���������
	 * 
	 * @param userid
	 *            �û���ʶ
	 * @return
	 */
	public List<TaskTodoForm> queryTodos(String userid);

	/**
	 * ����ҵ��keyǩ������
	 * 
	 * @param businessKey
	 *            ҵ��key
	 * @param userId
	 *            ��������
	 */
	public void claim(String businessKey, String userId);

	/**
	 * ����ҵ��KEY��ɹ�����
	 * 
	 * @param taskId
	 *            ����ID
	 * @param variables
	 *            ���̱���
	 */
	public void complete(String taskId, Map<String, Object> variables);
}
