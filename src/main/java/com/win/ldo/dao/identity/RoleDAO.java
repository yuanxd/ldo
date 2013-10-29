package com.win.ldo.dao.identity;

import com.win.ldo.dao.BaseDAO;
import com.win.ldo.entity.identity.Role;

/**
 */
public interface RoleDAO extends BaseDAO<Role, String> {
	Role getByName(String name);
}
