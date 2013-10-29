package com.win.ldo.dao.identity;

import com.win.ldo.dao.BaseDAO;
import com.win.ldo.entity.identity.User;

/**
 */
public interface UserDAO extends BaseDAO<User, String> {
	public User getByName(String username);
	
	public User getByCode(String username);
}
