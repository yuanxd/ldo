package com.win.ldo.service.impl;

import java.io.Serializable;
import java.util.Collection;

import com.win.ldo.dao.BaseDAO;
import com.win.ldo.entity.BaseEntity;
import com.win.ldo.service.BaseService;

public abstract class BaseServiceImpl<E extends BaseEntity, PK extends Serializable>
		implements BaseService<E, PK> {
	
	public abstract BaseDAO<E, PK> getBaseDAO();

	@Override
	public PK save(E entity) {
		return getBaseDAO().save(entity);
	}

	@Override
	public void save(Collection<E> entities) {
		getBaseDAO().save(entities);
	}

	@Override
	public E get(PK id) {
		return getBaseDAO().get(id);
	}
}
