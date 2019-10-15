package com.biz;

import com.dao.ItemDao;
import com.entity.Item;

public interface ItemBiz {
	/**
	 * 添加订单详细信息
	 * @param item
	 * @return
	 */
	public boolean addItemToOrder(Item item);
	/**
	 * 初始化订单信息
	 * @param itemdao
	 */
	public void setItemdao(ItemDao itemdao);
	
	public int count();
}
