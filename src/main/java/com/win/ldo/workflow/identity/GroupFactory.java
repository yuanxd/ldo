package com.win.ldo.workflow.identity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;

import com.win.ldo.entity.identity.Role;

public class GroupFactory {
	public static List<Group> getGroupsByRoles(Collection<Role> roles) {
		List<Group> groupList = new ArrayList<Group>();
		if (null == roles || roles.size() == 0) {
			return groupList;
		}
		for (Role r : roles) {
			Group group = new GroupEntity();
			group.setId(r.getCode());
			group.setName(r.getName());
			groupList.add(group);
		}
		return groupList;
	}
}
