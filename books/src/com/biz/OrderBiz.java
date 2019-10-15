package com.biz;

import java.util.List;

import com.dao.OrderDao;
import com.entity.Order;

/**
 * 
 * @author Administrator
 *
 */
public interface OrderBiz {
	/**
	 * 添加新的订单
	 * @param order
	 * @return
	 */
	public boolean newOrder(Order order);
	/**
	 * 根据用户名查询相应的订单
	 * @param username 用户名
	 * @param page_orders 每页显示条数
	 * @param page_No 当前第几页
	 * @return list
	 */
	public List findByUsername(String username, int page_orders, int page_No);
	/**
	 * 初始化orderdao
	 * @param orderdao
	 * @return
	 */
	public void setOrderdao(OrderDao orderdao);
	/**
	 * 根据用户名获取当前oid
	 * @param username
	 * @return
	 */
	public String getCurrentOid(String username);
	/**
	 * 获取总记录数
	 * @return
	 */
	public int count();
	

}
