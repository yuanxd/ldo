package com.win.ldo.common;

import java.util.regex.Pattern;

public class GlobalData {
	/** �û�sessionID */
	public static final String USER_SESSION_KEY = "_USER_SESSION_KEY";
	/** �Ƿ���Ҫ��ajax��ʽ��¼ */
	public static final String AJAX_LOGIN = "_AJAX_LOGIN";
	/** ���ڸ�ʽ�� */
	public static final String FORMAT_DATE = "yyyy-MM-dd hh:mm:ss";
	/**
	 * ���ж�Session��URI��������ʽ
	 */
	public static final Pattern UNCHECKURI_PATTERN = Pattern
			.compile("(.*/login.*)|(.*/*.js)|(.*/*.css)|(.*/*.jpg)|(.*/*.gif)|(.*/*.png)");
	/**
	 * �������URI��������ʽ(����ʱ������JS)
	 */
	public static Pattern NOCACHEURI_PATTERN = Pattern
			.compile("(.*\\*)");

	/** servlet��ʼ������ */
	private static String loginPage;

	/** servlet��ʼ������ */
	private static String homePage;

	/**
	 * ��ȡ��¼����URL
	 * 
	 * @return String
	 */
	public static String getLoginPage() {
		return loginPage;
	}

	/**
	 * ��ȡ��ҳURL
	 * 
	 * @return String
	 */
	public static String getHomePage() {
		return homePage;
	}
}
