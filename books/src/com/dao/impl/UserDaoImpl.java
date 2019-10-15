package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.UserDao;
import com.entity.UserInfo;

/**
 * 实现类
 * @author Administrator
 *
 */
public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public List<UserInfo> query(String sql) {
		String[] colums = {"username","password","email"};
		
		return query(sql, colums);
	}

	@Override
	public int insert(UserInfo userinfo) {
		String table = "userinfo";
		//List 有序有重复
		List<String> list = new ArrayList<String>();	
		list.add(userinfo.getUsername());
		list.add(userinfo.getPassword());
		list.add(userinfo.getEmail());
		
		return insert(table, list);
	}

	
	
	
}
