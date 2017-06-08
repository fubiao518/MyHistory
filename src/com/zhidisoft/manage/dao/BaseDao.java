package com.zhidisoft.manage.dao;

import java.util.List;

public interface BaseDao<T> {

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<T> getAll();

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	public T getById(Integer id);

	/**
	 * 新增
	 * 
	 * @param t
	 * @return
	 */
	public boolean add(T t);

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(Integer id);

	/**
	 * 修改信息
	 * 
	 * @param t
	 * @return
	 */
	public boolean update(T t);
}
