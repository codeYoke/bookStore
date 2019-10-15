package com.biz.impl;

import java.util.List;
import java.util.Map;

import com.biz.OrderBiz;
import com.dao.OrderDao;
import com.entity.Order;

public class OrderBizImpl implements OrderBiz {
	private OrderDao orderdao = null;
	
	
	public OrderDao getOrderdao() {
		return orderdao;
	}

	@Override
	public boolean newOrder(Order order) {
		int i = orderdao.insert(order);
		
		return i>0?true:false;
	}

	@Override
	public List findByUsername(String username, int page_orders, int page_No) {
		String sql = "select * from orders o,items i,books b where o.oid=i.oid and b.bid=i.bid order by i.oid";
		
		return orderdao.query(sql);
	}

	@Override
	public void setOrderdao(OrderDao orderdao) {
		this.orderdao = orderdao;
	}

	@Override
	public String getCurrentOid(String username) {
		String sql = "select * from orders where username='"+username+"' order by oid desc";
		List orders = orderdao.queryByusername(sql);
		Map order = (Map) orders.get(0);//desc排序了，取当前最大的id
		
		return order.get("oid")+"";
	}

	@Override
	public int count() {
		return orderdao.count();
	}

}
