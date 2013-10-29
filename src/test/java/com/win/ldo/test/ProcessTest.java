package com.win.ldo.test;

import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.win.ldo.service.workflow.WorkflowService;

/**
 * ���̲�����<br>
 * ͨ��WorkflowService�ӿ�ִ��������ת
 * 
 * @author Ԭ����
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:springtest/applicationContext*.xml" })
public class ProcessTest {
	/** ���������� */
	@Autowired
	protected WorkflowService workflowService;

	/**
	 * ������̲���
	 */
	@Test
	@Deployment
	public void leaveProcessTest() {
		workflowService.startProcess("leaveProcess", "test2", null, "yxd");
	}
}
