package com.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dao.BaseDao;
import com.dao.ItemDao;
import com.entity.Item;

public class ItemDaoImpl extends BaseDao implements ItemDao {

	@Override
	public int insert(Item item) {
		String table = "items";
		List list = new ArrayList();
		list.add(item.getIid());
		list.add(item.getOid());
		list.add(item.getBid());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		Timestamp ts = new Timestamp(new java.util.Date().getTime());
		list.add(new Date(new java.util.Date().getTime()));
		//数据库为varchar类型
//		list.add(sdf.format(ts.getTime()));
		list.add(item.getCount());
		list.add(item.getPrice());
		list.add(item.getState());
		list.add(item.getTotal_price());
		
		return insert(table, list);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
