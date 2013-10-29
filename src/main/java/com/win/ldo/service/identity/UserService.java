package com.win.ldo.service.identity;

import java.util.List;

import com.win.ldo.entity.identity.Department;
import com.win.ldo.entity.identity.User;
import com.win.ldo.service.BaseService;

/**
 * 用户服务
 * 
 * @author 袁晓冬
 * 
 */
public interface UserService extends BaseService<User, String> {
	/**
	 * 根据用户名称查询用户
	 * 
	 * @param username
	 *            用户名称
	 * @return
	 */
	public User getByName(String username);

	/**
	 * 根据用户名称查询用户组织
	 * 
	 * @param username
	 *            用户名称
	 * @return
	 */
	public List<Department> queryDeptByUserName(String username);

	/**
	 * 根据用户编码查询用户
	 * @param code
	 * @return
	 */
	public User getByCode(String code);
}
