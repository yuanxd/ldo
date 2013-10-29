package com.win.ldo.web.form.workflow;

/**
 * 待办任务一览显示form
 * 
 * @author 袁晓冬
 * 
 */
public class TaskTodoForm {
	/** 任务ID */
	private String id;
	/** 任务名称 */
	private String name;
	/** 创建时间 */
	private String createTime;
	/** 流程定义名称 */
	private String pdname;
	/** 流程版本 */
	private int pdversion;
	/** 流程ID */
	private String pid;
	/** 任务状态 */
	private String status;
	/** 业务主键 */
	private String businessKey;

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPdname() {
		return pdname;
	}

	public int getPdversion() {
		return pdversion;
	}

	public void setPdversion(int pdversion) {
		this.pdversion = pdversion;
	}

	public String getPid() {
		return pid;
	}

	public String getStatus() {
		return status;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPdname(String pdname) {
		this.pdname = pdname;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
