package com.win.ldo.service.identity;

import com.win.ldo.entity.identity.Role;
import com.win.ldo.service.BaseService;

/**
 * ��ɫ����
 * 
 * @author Ԭ����
 * 
 */
public interface RoleService extends BaseService<Role, String> {
	Role getByName(String name);
}
