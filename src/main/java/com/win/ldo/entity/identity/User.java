package com.win.ldo.entity.identity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.win.ldo.entity.BaseEntity;

@Entity
@Table(name = "T_ACTIVITI_USER")
public class User extends BaseEntity {

	private String id;
	private String code;
	private String name;

	private String password;

	private String email;

	private Collection<Role> roles;
	
	@Column(length = 40)
	public String getCode() {
		return code;
	}
	@Column(length = 40)
	public String getEmail() {
		return email;
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

	@Column(length = 40)
	public String getPassword() {
		return password;
	}

	@ManyToMany(targetEntity = Role.class, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinTable(name = "T_ACTIVITI_USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
