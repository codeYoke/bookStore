package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.OrderDao;
import com.entity.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	@Override
	public List query(String sql) {
		String[] colums = {"oid","username","bid","createdate","count","price",
				"state","b_price","image","bookname","total_price"};
		
		return query(sql, colums);		
	}

	@Override
	public int insert(Order order) {
		String table = "orders";
		List list = new ArrayList();
		list.add(order.getOid());
		list.add(order.getUsername());
		
		return insert(table, list);
	}

	@Override
	public int count() {
		String sql = "select count(*) from items";
		openConnection();
		int i = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				i = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return i;
	}

	@Override
	public List queryByusername(String sql) {
		String[] colums = {"oid", "username"};
		
		return query(sql, colums);
	}

}
