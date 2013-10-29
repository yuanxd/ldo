package com.win.ldo.common;

import com.win.ldo.web.form.workflow.TaskTodoForm;


/**
 * Assert Util
 * 
 * @author 袁晓冬
 * 
 */
public class Assert extends org.junit.Assert {
	/**
	 * 断言任务已被签收
	 * 
	 * @param form
	 */
	static public void assertTaskClaimed(TaskTodoForm form) {
		assertEquals("todo", form.getStatus());
	}

	/**
	 * 断言任务未被签收
	 * 
	 * @param form
	 */
	static public void assertTaskNotClaimed(TaskTodoForm form) {
		assertEquals("claim", form.getStatus());
	}
}
