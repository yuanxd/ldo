package com.win.ldo.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.win.ldo.entity.BaseEntity;

/**
 * Dao层共通基类<br>
 * <p>
 * 所有数据访问层（@Entity注解）继承此共通基类，实现数据访问层的共通处理。
 * 
 * @param <E>
 *            Dao所处理的Entity对象
 * @param <PK>
 *            Entity对象的主键类型，一般是{@link Long}
 * @author 袁晓冬
 */
public interface BaseDAO<E extends BaseEntity, PK extends Serializable> {
	// 记录状态-有效
	public static final int RECORD_STATE_VALID = 1;
	// 记录状态-提交
	public static final int RECORD_STATE_SUBMIT = 5;
	// 记录状态-删除
	public static final int RECORD_STATE_DELETE = 9;

	/**
	 * 清空缓冲区
	 */
	public void clear();

	/**
	 * 得到所有实体个数，无过滤条件
	 * 
	 * @return
	 */
	public int countAll();

	/**
	 * 根据查询语句得到记录总数
	 * 
	 * @param hql
	 * @param paramlist
	 * @return
	 */
	public int countAll(String hql, final Object... paramlist);

	/**
	 * 得到有效实体个数，无过滤条件
	 * 
	 * @return
	 */
	public int countValidAll();

	/**
	 * 删除实体对象
	 * 
	 * @paraE entity
	 */
	public void delete(E entity);

	/**
	 * 根据主键删除实体
	 * 
	 * @param id
	 */
	public void delete(PK id);

	/**
	 * 判断是否存在主键为id的实体
	 * 
	 * @param id
	 *            实体主键
	 * @return boolean 是否存在
	 */
	boolean exists(PK id);

	/**
	 * 刷新缓冲区
	 */
	public void flush();

	/**
	 * 根据主键获取实体
	 * 
	 * @param id
	 * @return
	 */
	public E get(PK id);

	/**
	 * 根据查询语句执行查询处理（有分页）
	 * 
	 * @param hql
	 *            查询语句
	 * @param pn
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @param paramlist
	 *            参数列表
	 * @return 结果集
	 */
	public <T> List<T> list(final String hql, final int pn, final int pageSize,
			final Object... paramlist);

	/**
	 * 根据查询语句执行查询处理（无分页）
	 * 
	 * @param hql
	 *            查询语句
	 * @param paramlist
	 *            参数列表
	 * @return 结果集
	 */
	public <T> List<T> listWithParams(final String hql,
			final Object... paramlist);

	/**
	 * 得到所有实体
	 * 
	 * @return
	 */
	public List<E> listAll();

	/**
	 * 根据页数和每页记录数得到所有实体
	 * 
	 * @param pn
	 *            第几页记录
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	public List<E> listAll(int pn, int pageSize);

	/**
	 * 得到所有有效实体
	 * 
	 * @return
	 */
	public List<E> listValidAll();

	/**
	 * 根据页数和每页记录数得到所有有效实体
	 * 
	 * @param pn
	 *            第几页记录
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	public List<E> listValidAll(int pn, int pageSize);

	/**
	 * 整合实体处理
	 * 
	 * @paraE entity BaseEntity
	 */
	public void merge(E entity);

	/**
	 * 主键小于PK的第一页数据。如果pk不指定则相当于调用listAll({@link listAll}
	 * 
	 * @param pk
	 *            主键
	 * @param pn
	 *            页码
	 * @param pageSize
	 *            每页数量
	 * @return
	 */
	public List<E> next(PK pk, int pn, int pageSize);

	/**
	 * 主键大于pk的第一页数据，并倒序重拍检索出的数据。如果pk不指定则相当于调用listAll({@link listAll}
	 * 
	 * @param pk
	 *            主键
	 * @param pn
	 *            页码
	 * @param pageSize
	 *            每页数量
	 * @return
	 */
	public List<E> pre(PK pk, int pn, int pageSize);

	/**
	 * 保存实体处理
	 * 
	 * @paraE entity BaseEntity
	 * @return 主键
	 */
	public PK save(E entity);
	
	/**
	 * 批量保存实体处理
	 * 
	 * @paraE Collection<E> BaseEntity集合
	 * @return int
	 */
	public void save(Collection<E> entities);

	/**
	 * 保存或更新实体处理
	 * 
	 * @paraE entity BaseEntity
	 */
	public void saveOrUpdate(E entity);

	/**
	 * 更新实体处理
	 * 
	 * @paraE entity BaseEntity
	 */
	public void update(E entity);

	/**
	 * 上一向下一项处理
	 * 
	 * @param id
	 *            插入序号
	 * @param type
	 *            类型
	 * @return Object 返回对象
	 * @throws Exception
	 *             抛出异常
	 */
	public E getPreOrNextData(Integer id, int type, String addUser)
			throws Exception;

	/**
	 * 取得最后一条数据
	 * 
	 * @return 数据对象
	 * @throws Exception
	 *             异常抛出
	 */
	public E getLastRowData(String addUser) throws Exception;

}
