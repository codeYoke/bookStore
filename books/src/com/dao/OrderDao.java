package com.dao;

import java.util.List;

import com.entity.Order;

public interface OrderDao {
	/**
	 * 查询所有订单
	 * @param sql
	 * @return
	 */
	public List query(String sql);
	/**
	 * 添加订单方法
	 * @param order
	 * @return
	 */
	public int insert(Order order);
	/**
	 * 查询数量
	 * @return
	 */
	public int count();
	/**
	 * 根据用户名查询相应的订单
	 * @param username
	 * @return
	 */
	public List queryByusername(String username);
	
	
}
