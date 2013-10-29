package com.win.ldo.service.impl.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.win.ldo.dao.BaseDAO;
import com.win.ldo.dao.identity.RoleDAO;
import com.win.ldo.entity.identity.Role;
import com.win.ldo.service.identity.RoleService;
import com.win.ldo.service.impl.BaseServiceImpl;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements
		RoleService {
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public BaseDAO<Role, String> getBaseDAO() {
		return roleDAO;
	}

	@Override
	public Role getByName(String name) {
		return roleDAO.getByName(name);
	}

}
