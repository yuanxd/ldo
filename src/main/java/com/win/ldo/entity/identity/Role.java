package com.win.ldo.entity.identity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.activiti.engine.identity.Group;
import org.hibernate.annotations.GenericGenerator;

import com.win.ldo.entity.BaseEntity;

@Entity
@Table(name = "T_ACTIVITI_ROLE")
public class Role extends BaseEntity implements Group{
	@Transient
	private static final long serialVersionUID = 8846849547978767254L;
	private String id;
	private String code;
	private String name;
	private String type;

	private Collection<User> users;
	@Column(length = 40)
	public String getCode() {
		return code;
	}
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid2")
	@GeneratedValue(generator = "idGenerator")
	@Column(length = 40)
	public String getId() {
		return id;
	}

	@Column(length = 40)
	public String getName() {
		return name;
	}

	@Column(length = 10)
	public String getType() {
		return type;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roles", targetEntity = User.class)
	public Collection<User> getUsers() {
		return users;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
}
