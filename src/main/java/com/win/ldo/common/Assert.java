package com.win.ldo.common;

import com.win.ldo.web.form.workflow.TaskTodoForm;


/**
 * Assert Util
 * 
 * @author Ԭ����
 * 
 */
public class Assert extends org.junit.Assert {
	/**
	 * ���������ѱ�ǩ��
	 * 
	 * @param form
	 */
	static public void assertTaskClaimed(TaskTodoForm form) {
		assertEquals("todo", form.getStatus());
	}

	/**
	 * ��������δ��ǩ��
	 * 
	 * @param form
	 */
	static public void assertTaskNotClaimed(TaskTodoForm form) {
		assertEquals("claim", form.getStatus());
	}
}
