package com.win.ldo.entity;


/**
 * ������ʵ�����<br>
 * 
 * @author Ԭ����
 * 
 */
public abstract class BaseWorkflowEntity extends BaseEntity {
	/**
	 * ��ȡʵ���ҵ��������һ����ʵ������<br>
	 * 
	 * @return String ҵ��key
	 */
	public abstract String getBusinessKey();
}
