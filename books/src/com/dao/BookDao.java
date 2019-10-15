package com.dao;

import java.util.List;

public interface BookDao {

	/**
	 * 查询所有图书
	 * @param sql
	 * @return
	 */
	public List query(String sql);
	/**
	 * 查询书籍总数
	 * @return
	 */
	public int count();

	/**
	 * 
	 * @Title: count
	 * @Description:根据sql查询记录数
	 *  @param sql 一条count语句查询
	 *  @return 记录条数
	 */
	public int count(String sql);
	/**
	 * 修改商品库存，防止库存为负数
	 * @param sql
	 * @return
	 */
	
	public int update(String sql);
	
	
}
