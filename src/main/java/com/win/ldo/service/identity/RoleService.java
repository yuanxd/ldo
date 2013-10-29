package com.win.ldo.service.identity;

import com.win.ldo.entity.identity.Role;
import com.win.ldo.service.BaseService;

/**
 * ½ÇÉ«·þÎñ
 * 
 * @author Ô¬Ïþ¶¬
 * 
 */
public interface RoleService extends BaseService<Role, String> {
	Role getByName(String name);
}
