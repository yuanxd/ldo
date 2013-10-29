package com.win.ldo.service.impl.identity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.win.ldo.dao.BaseDAO;
import com.win.ldo.dao.identity.UserDAO;
import com.win.ldo.entity.identity.Department;
import com.win.ldo.entity.identity.User;
import com.win.ldo.service.identity.UserService;
import com.win.ldo.service.impl.BaseServiceImpl;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements
		UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public BaseDAO<User, String> getBaseDAO() {
		return userDAO;
	}

	@Override
	public User getByCode(String code) {
		return userDAO.getByCode(code);
	}

	@Override
	public User getByName(String username) {
		return userDAO.getByName(username);
	}

	@Override
	public List<Department> queryDeptByUserName(String username) {
		return null;
	}
}
