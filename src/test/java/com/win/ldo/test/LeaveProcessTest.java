package com.win.ldo.test;

import java.util.List;

import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.win.ldo.common.Assert;
import com.win.ldo.common.ProcessJunitHelper;
import com.win.ldo.entity.Leave;
import com.win.ldo.service.leave.LeaveService;
import com.win.ldo.service.workflow.WorkflowService;
import com.win.ldo.web.form.workflow.TaskTodoForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:springtest/applicationContext*.xml" })
public class LeaveProcessTest {
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private ProcessJunitHelper helper;
	

	@Test
	public void testAll() {
		// 启动流程
		Leave leave = new Leave();
		leave.setName("yxd test leave");
		leaveService.create(leave, null, "yxd");
		String businessKey = leave.getId();
		// 待办验证
		TaskTodoForm form = helper.getTaskByUserAndBusinessKey("leader",
				businessKey);
		Assert.assertNotNull(form);
		Assert.assertTaskNotClaimed(form);
		// 签收
		workflowService.claim(form.getId(), "leader");
		form = helper.getTaskByUserAndBusinessKey("leader", businessKey);
		// 签收验证
		Assert.assertTaskClaimed(form);
		// 审核处理
		workflowService.complete(form.getId(), null);
		System.err.println("workflow competed:" + businessKey);
	}

	@Deployment
	@Test
	public void testCreate() {
		// 启动流程
		Leave leave = new Leave();
		leave.setName("yxd test leave");
		leaveService.create(leave, null, "yxd");
		String businessKey = leave.getId();
		// 待办验证
		TaskTodoForm form = helper.getTaskByUserAndBusinessKey("leader",
				businessKey);
		Assert.assertNotNull(form);
		Assert.assertTaskNotClaimed(form);
	}

	@Test
	public void testClaim() {
		workflowService.claim("6aad69cf-5be1-472e-b584-9b9aa4e3656a", "yxd");
	}

	@Test
	@Deployment
	public void testQueryTodos() {
		List<TaskTodoForm> formList = workflowService.queryTodos("leader");
		System.err.println(formList.size());
	}

	@Test
	public void testComplete() {
		workflowService.complete("105", null);
	}

	@Test
	public void testHq() {
		leaveService.getClass();
		String businessKey = "2f15884b-3c1c-4296-87f7-d7e2d31ce6f6";
		// 待办验证
		TaskTodoForm form = helper.getTaskByUserAndBusinessKey("leader",
				businessKey);
//		Assert.assertNotNull(form);
//		Assert.assertTaskNotClaimed(form);
//		// 签收
//		workflowService.claim(form.getId(), "leader");
//		form = helper.getTaskByUserAndBusinessKey("leader", businessKey);
//		// 签收验证
//		Assert.assertTaskClaimed(form);
		// 审核处理
		workflowService.complete(form.getId(), null);
	}
}
