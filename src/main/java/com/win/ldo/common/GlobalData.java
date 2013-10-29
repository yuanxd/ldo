package com.win.ldo.common;

import java.util.regex.Pattern;

public class GlobalData {
	/** 用户sessionID */
	public static final String USER_SESSION_KEY = "_USER_SESSION_KEY";
	/** 是否需要已ajax方式登录 */
	public static final String AJAX_LOGIN = "_AJAX_LOGIN";
	/** 日期格式化 */
	public static final String FORMAT_DATE = "yyyy-MM-dd hh:mm:ss";
	/**
	 * 不判断Session的URI的正则表达式
	 */
	public static final Pattern UNCHECKURI_PATTERN = Pattern
			.compile("(.*/login.*)|(.*/*.js)|(.*/*.css)|(.*/*.jpg)|(.*/*.gif)|(.*/*.png)");
	/**
	 * 不缓存的URI的正则表达式(开发时不缓存JS)
	 */
	public static Pattern NOCACHEURI_PATTERN = Pattern
			.compile("(.*\\*)");

	/** servlet初始化设置 */
	private static String loginPage;

	/** servlet初始化设置 */
	private static String homePage;

	/**
	 * 获取登录画面URL
	 * 
	 * @return String
	 */
	public static String getLoginPage() {
		return loginPage;
	}

	/**
	 * 获取主页URL
	 * 
	 * @return String
	 */
	public static String getHomePage() {
		return homePage;
	}
}
