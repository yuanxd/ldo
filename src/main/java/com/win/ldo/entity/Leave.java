package com.win.ldo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_ACTIVITI_LEAVE")
public class Leave extends BaseWorkflowEntity {

	private String id;
	private String name;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid2")
	@GeneratedValue(generator = "idGenerator")
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	@Transient
	public String getBusinessKey() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
