package com.win.ldo.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import com.win.ldo.dao.BaseDAO;
import com.win.ldo.entity.BaseEntity;

/**
 * 数据库访问DAO基类
 * 
 * @author 袁晓冬
 * 
 * @param <E>Entity对象
 * @param <PK>主键
 */
public class BaseDAOImpl<E extends BaseEntity, PK extends Serializable>
		implements BaseDAO<E, PK> {
	/** sessionFactory */
	@Autowired
	private SessionFactory sessionFactory = null;
	/** DAO所处理的实体类 */
	private final Class<E> entityClass;
	/** 主键名称 */
	private String pkName;
	/** 查询主键大于指定值的HSQL */
	private String hqlOptimizePreListAll;
	/** 查询主键小于指定值的HSQL */
	private String hqlOptimizeNextListAll;
	/** 查询所有有效记录HSQL */
	private String hqlListAllValid;
	/** 查询所有记录降序HSQL */
	private String hqlListAll;
	/** 查询所有记录降序HSQL */
	private String hqlListAllValidAsc;
	/** 查询所有记录升序HSQL */
	private String hqlListAllAsc;
	/** 查询所有有效记录条数 */
	private String hqlCountAllValid;
	/** 查询所有记录条数 */
	private String hqlCountAll;

	/**
	 * 构造函数
	 */
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		Class<?> clazz = getClass();
		Type genType = getClass().getGenericSuperclass();
		// 代理对象时
		if (!(genType instanceof ParameterizedType)) {
			Class<?> superClass = clazz.getSuperclass();
			genType = superClass.getGenericSuperclass();
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<E>) params[0];
	}

	/**
	 * 根据查询语句hql得到合计值
	 * 
	 * @param <T>
	 *            Entity类型
	 * @param hql
	 *            查询语句
	 * @param paramlist
	 *            参数列表
	 * @return 结果值
	 */
	@SuppressWarnings("unchecked")
	protected <T> T aggregate(final String hql, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		return (T) query.uniqueResult();
	}

	/**
	 * 数组向字符串转换
	 * 
	 * @param array
	 *            数组
	 * @return 字符串
	 */
	public String arrayToParamInString(String[] array) {
		StringBuffer paramIn = new StringBuffer(" ( ");
		for (int i = 0; i < array.length; i++) {
			paramIn.append("'" + array[i] + "'");
			if (i != array.length - 1) {
				paramIn.append(",");
			}
		}
		paramIn.append(" ) ");

		return paramIn.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		getSession().clear();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int countAll() {
		Long total = aggregate(hqlCountAll);
		return total.intValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int countAll(String hql, Object... paramlist) {
		Long total = aggregate(hql, paramlist);
		return total.intValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int countValidAll() {
		Long total = aggregate(hqlCountAllValid);
		return total.intValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(E entity) {
		Session session = this.getSession();
		session.delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(PK id) {
		Session session = this.getSession();
		BaseEntity entity = this.get(id);
		session.delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(PK id) {
		return get(id) != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() {
		getSession().flush();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E get(PK id) {
		return (E) getSession().get(this.entityClass, id);
	}

	/**
	 * 取得第一条数据
	 * 
	 * @return 数据对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public E getFirstRecord() {
		Query query = getSession().createQuery(hqlListAllAsc);
		return (E) query.uniqueResult();
	}

	/**
	 * 取得第一条有效数据
	 * 
	 * @return 数据对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public E getFirstValidRecord() {
		Query query = getSession().createQuery(hqlListAllValidAsc);
		return (E) query.uniqueResult();
	}

	/**
	 * 取得最后一条数据
	 * 
	 * @return 数据对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public E getLastRecord() {
		Query query = getSession().createQuery(hqlListAll);
		return (E) query.uniqueResult();
	}

	/**
	 * 取得最后一条数据
	 * 
	 * @return 数据对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public E getLastValidRecord() {
		Query query = getSession().createQuery(hqlListAllValid);
		return (E) query.uniqueResult();
	}

	/**
	 * 取得前一条或后一条数据
	 * 
	 * @param id
	 *            自增列
	 * @param type
	 *            前一条或后一条数（0：前一项,1:后一项）
	 * @param addUser
	 *            addUser
	 * @return 数据对象
	 * @author caic
	 * @throws Exception
	 *             Exception
	 */
	@SuppressWarnings("unchecked")
	public E getPreOrNextData(Integer id, int type, String addUser)
			throws Exception {
		Session session = getSession();
		Query query = null;
		try {
			if ((id == null || id == 0) && type == 0) {
				return getLastRowData(addUser);
			}

			String sql = "";

			// 后一条数据
			if (type == 1) {
				sql = "from " + this.entityClass.getSimpleName()
						+ " where addId > ? and recordStatus  ="
						+ RECORD_STATE_VALID
						+ " and addUser=? order by addId asc";
			}

			// 前一条数据
			if (type == 0) {
				sql = "from " + this.entityClass.getSimpleName()
						+ " where addId < ? and recordStatus ="
						+ RECORD_STATE_VALID
						+ " and addUser=? order by addId desc";
			}
			query = session.createQuery(sql);
			query.setInteger(0, id);
			query.setString(1, addUser);
			query.setFirstResult(0);
			query.setMaxResults(1);
		} catch (Exception ex) {
			throw ex;
		}

		return (E) query.uniqueResult();
	}

	/**
	 * 取得最后一条数据
	 * 
	 * @return 数据对象
	 * @param addUser
	 *            addUser
	 * @throws Exception
	 *             异常抛出
	 */
	@SuppressWarnings("unchecked")
	public E getLastRowData(String addUser) throws Exception {
		Session session = getSession();
		Query query = null;
		try {
			String sql = "from " + this.entityClass.getSimpleName()
					+ " where recordStatus = " + RECORD_STATE_VALID
					+ " and addUser = ? order by addId desc";
			query = session.createQuery(sql);
			query.setString(0, addUser);
			query.setFirstResult(0);
			query.setMaxResults(1);

		} catch (Exception ex) {
			throw ex;
		}

		return (E) query.uniqueResult();
	}

	/** ********** 共通方法 ********** */
	/**
	 * 返回当前会话
	 * 
	 * @return Session
	 */
	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> List<T> list(final String hql, final int pn, final int pageSize,
			final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		if (pn > -1 && pageSize > -1) {
			query.setMaxResults(pageSize);
			int start = (pn - 1) * pageSize;
			if (start != 0) {
				query.setFirstResult(start);
			}
		}
		if (pn < 0) {
			query.setFirstResult(0);
		}
		@SuppressWarnings("unchecked")
		List<T> results = query.list();
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> List<T> listWithParams(final String hql,
			final Object... paramlist) {
		return list(hql, -1, -1, paramlist);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> listAll() {
		return listWithParams(hqlListAll);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> listAll(int pn, int pageSize) {
		return list(hqlListAll, pn, pageSize);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> listValidAll() {
		return listWithParams(hqlListAllValid);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> listValidAll(int pn, int pageSize) {
		return list(hqlListAllValid, pn, pageSize);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void merge(E entity) {
		getSession().merge(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> next(PK pk, int pn, int pageSize) {
		if (pk == null) {
			return listAll();
		}
		return list(hqlOptimizeNextListAll, 1, pageSize, pk);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> pre(PK pk, int pn, int pageSize) {
		if (pk == null) {
			return listAll();
		}
		// 倒序，重排
		List<E> result = list(hqlOptimizePreListAll, 1, pageSize, pk);
		Collections.reverse(result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public PK save(E entity) {
		return (PK) getSession().save(entity);
	}
	@Override
	public void save(Collection<E> entities) {
		for(E entity:entities) {
			getSession().save(entity);
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveOrUpdate(E entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * 为query对象设置参数
	 * 
	 * @param query
	 *            HSQL
	 * @param paramlist
	 *            参数列表
	 */
	protected void setParameters(Query query, Object[] paramlist) {
		if (paramlist != null) {
			for (int i = 0; i < paramlist.length; i++) {
				query.setParameter(i, paramlist[i]);
			}
		}
	}

	/**
	 * 注入SessionFactory
	 * 
	 * @param sessionFactory
	 *            SessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		ClassMetadata cmd = sessionFactory.getClassMetadata(entityClass);
		this.pkName = cmd.getIdentifierPropertyName();
		// Use id for default
		if (null == pkName) {
			pkName = "id";
		}
		hqlListAllValid = "from " + this.entityClass.getSimpleName()
				+ " where recordStatus !=" + RECORD_STATE_DELETE + " order by "
				+ pkName + " desc";
		hqlListAll = "from " + this.entityClass.getSimpleName() + " order by "
				+ pkName + " desc";
		hqlListAllValidAsc = "from " + this.entityClass.getSimpleName()
				+ " where recordStatus !=" + RECORD_STATE_DELETE + " order by "
				+ pkName + " asc";
		hqlListAllAsc = "from " + this.entityClass.getSimpleName()
				+ " order by " + pkName + " asc";
		hqlOptimizePreListAll = "from " + this.entityClass.getSimpleName()
				+ " where recordStatus !=" + RECORD_STATE_DELETE + " and "
				+ pkName + " > ? order by " + pkName + " asc";
		hqlOptimizeNextListAll = "from " + this.entityClass.getSimpleName()
				+ " where recordStatus !=" + RECORD_STATE_DELETE + " and "
				+ pkName + " < ? order by " + pkName + " desc";
		hqlCountAllValid = " select count(*) from "
				+ this.entityClass.getSimpleName() + " where recordStatus !="
				+ RECORD_STATE_DELETE;
		hqlCountAll = " select count(*) from "
				+ this.entityClass.getSimpleName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(E entity) {
		getSession().update(entity);
	}
}
