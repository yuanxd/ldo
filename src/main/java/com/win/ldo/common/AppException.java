package com.win.ldo.common;

/**
 * 业务系统的异常类,主要用于封装业务逻辑错,给客户端提示 同时还可以封装系统错
 * 
 * @author 袁晓冬
 * 
 */
public class AppException extends RuntimeException {
	private static final long serialVersionUID = -9013803276532569636L;
	/**
	 * 警告,此时系统不记日志
	 */
	public static final int INFTYPE_WARNING = 1;
	/**
	 * 错误,此时系统记日志
	 */
	public static final int INFTYPE_ERROR = 2;

	/**
	 * 信息类型： 1警告 2错误
	 */
	private int infType = INFTYPE_WARNING;
	private boolean showDetial = false;

	public AppException() {
		super();
	}

	public AppException(String message) {
		this.setInfType(INFTYPE_WARNING);
	}

	public AppException(Throwable cause) {
		super(cause);
		this.setInfType(INFTYPE_ERROR);
	}

	public AppException(String message, Throwable cause) {
		this.setInfType(INFTYPE_ERROR);

	}

	public AppException(String message, int infType) {
		this(message);
		this.setInfType(infType);
	}

	public AppException(Throwable cause, int infType) {
		super(cause);
		this.setInfType(infType);

	}

	public AppException(String message, Throwable cause, int infType) {
		this.setInfType(infType);

	}

	public int getInfType() {
		return infType;
	}

	public void setInfType(int infType) {
		this.infType = infType;
	}

	public boolean isShowDetial() {
		return showDetial;
	}

	public AppException setShowDetial(boolean showdetial) {
		this.showDetial = showdetial;
		return this;
	}

}
