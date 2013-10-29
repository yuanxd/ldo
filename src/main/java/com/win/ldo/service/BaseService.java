package com.win.ldo.service;

import java.io.Serializable;
import java.util.Collection;

import com.win.ldo.entity.BaseEntity;

/**
 * �����ӿڻ���
 * 
 * @author Ԭ����
 * 
 * @param <E>
 *            ʵ������
 * @param <PK>
 *            ��������
 */
public interface BaseService<E extends BaseEntity, PK extends Serializable> {
	/**
	 * �洢ʵ��
	 * 
	 * @param entity
	 *            ʵ��
	 * @return ����
	 */
	public PK save(E entity);

	/**
	 * ��������ʵ��
	 * 
	 * @param entities
	 *            ʵ�弯��
	 * @return
	 */
	public void save(Collection<E> entities);

	/**
	 * ����������ѯʵ��
	 * 
	 * @param id
	 *            ���I
	 * @return ʵ��
	 */
	public E get(PK id);
}
