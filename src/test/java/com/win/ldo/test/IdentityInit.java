package com.win.ldo.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.win.ldo.entity.identity.Role;
import com.win.ldo.entity.identity.User;
import com.win.ldo.service.identity.RoleService;
import com.win.ldo.service.identity.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:springtest/applicationContext*.xml" })
public class IdentityInit {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Test
	public void init() {
		Role pgRole = new Role();
		pgRole.setCode("pg");
		pgRole.setName("程序员");
		roleService.save(pgRole);

		Role leaderRole = new Role();
		leaderRole.setCode("leader");
		leaderRole.setName("领导");
		roleService.save(leaderRole);

		Role role1 = new Role();
		role1.setCode("role1");
		role1.setName("角色1");
		roleService.save(role1);

		Role role2 = new Role();
		role2.setCode("role2");
		role2.setName("角色2");
		roleService.save(role2);

		Role role3 = new Role();
		role3.setCode("role3");
		role3.setName("角色3");
		roleService.save(role3);

		User yxd = new User();
		yxd.setCode("yxd");
		yxd.setPassword("yxd");
		yxd.setName("袁晓冬");
		List<Role> yxdRoleList = new ArrayList<Role>();
		yxdRoleList.add(pgRole);
		yxdRoleList.add(role1);
		yxd.setRoles(yxdRoleList);
		userService.save(yxd);

		User leader = new User();
		leader.setCode("leader");
		leader.setName("领导A");
		leader.setPassword("leader");
		List<Role> leaderRoleList = new ArrayList<Role>();
		leaderRoleList.add(leaderRole);
		leaderRoleList.add(role2);
		leaderRoleList.add(role3);
		leader.setRoles(leaderRoleList);
		userService.save(leader);

	}
}
