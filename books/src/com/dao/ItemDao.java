package com.dao;

import com.entity.Item;

public interface ItemDao {
	/**
	 * 添加订单详细信息
	 * @param item
	 * @return
	 */
	public int insert(Item item);
	/**
	 * 查询总记录数
	 * @return
	 */
	public int count();
}
