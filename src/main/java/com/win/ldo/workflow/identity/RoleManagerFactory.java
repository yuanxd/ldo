package com.win.ldo.workflow.identity;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleManagerFactory implements SessionFactory {
	@Autowired
	private RoleManager roleManager;

	@Override
	public Class<?> getSessionType() {
		return GroupEntityManager.class;
	}

	@Override
	public Session openSession() {
		return roleManager;
	}

}
