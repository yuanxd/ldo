package com.win.ldo.workflow.identity;

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserManager extends
		org.activiti.engine.impl.persistence.entity.UserManager {

	@Override
	public User createNewUser(String userId) {
		throw new ActivitiException("unsupported method");

	}

	@Override
	public void insertUser(User user) {
		throw new ActivitiException("unsupported method");

	}

	@Override
	public void updateUser(UserEntity updatedUser) {
		throw new ActivitiException("unsupported method");

	}

	@Override
	public UserEntity findUserById(String userId) {
		throw new ActivitiException("unsupported method");

	}

	@Override
	public void deleteUser(String userId) {
		throw new ActivitiException("unsupported method");
	}

	@Override
	public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
		throw new ActivitiException("unsupported method");
	}

	@Override
	public long findUserCountByQueryCriteria(UserQueryImpl query) {
		throw new ActivitiException("unsupported method");
	}

	@Override
	public List<Group> findGroupsByUser(String userId) {
		throw new ActivitiException("unsupported method");
	}

	@Override
	public UserQuery createNewUserQuery() {
		throw new ActivitiException("unsupported method");
	}

	@Override
	public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId,
			String key) {
		throw new ActivitiException("unsupported method");
	}

	@Override
	public List<String> findUserInfoKeysByUserIdAndType(String userId,
			String type) {
		throw new ActivitiException("unsupported method");
	}

	@Override
	public Boolean checkPassword(String userId, String password) {
		throw new ActivitiException("unsupported method");
	}

	@Override
	public List<User> findPotentialStarterUsers(String proceDefId) {
		throw new ActivitiException("unsupported method");
	}

}
