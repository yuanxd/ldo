package com.win.ldo.web.form.workflow;

/**
 * ��������һ����ʾform
 * 
 * @author Ԭ����
 * 
 */
public class TaskTodoForm {
	/** ����ID */
	private String id;
	/** �������� */
	private String name;
	/** ����ʱ�� */
	private String createTime;
	/** ���̶������� */
	private String pdname;
	/** ���̰汾 */
	private int pdversion;
	/** ����ID */
	private String pid;
	/** ����״̬ */
	private String status;
	/** ҵ������ */
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
