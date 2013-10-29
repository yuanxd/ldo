package com.win.ldo.service.identity;

import java.util.List;

import com.win.ldo.entity.identity.Department;
import com.win.ldo.entity.identity.User;
import com.win.ldo.service.BaseService;

/**
 * �û�����
 * 
 * @author Ԭ����
 * 
 */
public interface UserService extends BaseService<User, String> {
	/**
	 * �����û����Ʋ�ѯ�û�
	 * 
	 * @param username
	 *            �û�����
	 * @return
	 */
	public User getByName(String username);

	/**
	 * �����û����Ʋ�ѯ�û���֯
	 * 
	 * @param username
	 *            �û�����
	 * @return
	 */
	public List<Department> queryDeptByUserName(String username);

	/**
	 * �����û������ѯ�û�
	 * @param code
	 * @return
	 */
	public User getByCode(String code);
}
