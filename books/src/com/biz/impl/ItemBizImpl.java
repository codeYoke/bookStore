package com.biz.impl;

import com.biz.ItemBiz;
import com.dao.ItemDao;
import com.entity.Item;

public class ItemBizImpl implements ItemBiz {
	private ItemDao itemdao;
	
	
	public ItemDao getItemdao() {
		return itemdao;
	}

	@Override
	public boolean addItemToOrder(Item item) {
		
		return itemdao.insert(item)>0? true:false;
	}

	@Override
	public void setItemdao(ItemDao itemdao) {
		this.itemdao = itemdao;
	}

	@Override
	public int count() {
		return itemdao.count();
	}

}
