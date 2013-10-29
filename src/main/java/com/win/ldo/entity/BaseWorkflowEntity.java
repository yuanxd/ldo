package com.win.ldo.entity;


/**
 * 工作流实体基类<br>
 * 
 * @author 袁晓冬
 * 
 */
public abstract class BaseWorkflowEntity extends BaseEntity {
	/**
	 * 获取实体的业务主键，一般是实体主键<br>
	 * 
	 * @return String 业务key
	 */
	public abstract String getBusinessKey();
}
