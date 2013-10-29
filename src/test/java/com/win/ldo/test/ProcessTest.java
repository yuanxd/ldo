package com.win.ldo.test;

import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.win.ldo.service.workflow.WorkflowService;

/**
 * 流程测试类<br>
 * 通过WorkflowService接口执行流程流转
 * 
 * @author 袁晓冬
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:springtest/applicationContext*.xml" })
public class ProcessTest {
	/** 工作流服务 */
	@Autowired
	protected WorkflowService workflowService;

	/**
	 * 请假流程测试
	 */
	@Test
	@Deployment
	public void leaveProcessTest() {
		workflowService.startProcess("leaveProcess", "test2", null, "yxd");
	}
}
