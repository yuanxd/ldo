package com.win.ldo.dao.impl.auth;

import org.springframework.stereotype.Component;

import com.win.ldo.dao.identity.RoleDAO;
import com.win.ldo.dao.impl.BaseDAOImpl;
import com.win.ldo.entity.identity.Role;

@Component
public class RoleDAOImpl extends BaseDAOImpl<Role, String> implements RoleDAO {

	@Override
	public Role getByName(String name) {
		return (Role) getSession().createQuery("from Role where name=:name ")
				.setParameter("name", name).uniqueResult();
	}
}
