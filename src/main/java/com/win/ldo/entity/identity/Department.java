package com.win.ldo.entity.identity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.win.ldo.entity.BaseEntity;

@Entity
@Table(name="T_ACTIVITI_DEPARTMENT")
public class Department extends BaseEntity {

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

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
