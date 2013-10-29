package com.win.ldo.service;

import java.io.Serializable;
import java.util.Collection;

import com.win.ldo.entity.BaseEntity;

/**
 * 服务层接口基类
 * 
 * @author 袁晓冬
 * 
 * @param <E>
 *            实体类型
 * @param <PK>
 *            主键类型
 */
public interface BaseService<E extends BaseEntity, PK extends Serializable> {
	/**
	 * 存储实体
	 * 
	 * @param entity
	 *            实体
	 * @return 主键
	 */
	public PK save(E entity);

	/**
	 * 批量保存实体
	 * 
	 * @param entities
	 *            实体集合
	 * @return
	 */
	public void save(Collection<E> entities);

	/**
	 * 根据主键查询实体
	 * 
	 * @param id
	 *            主鍵
	 * @return 实体
	 */
	public E get(PK id);
}
