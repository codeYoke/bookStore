package com.dao;

import java.util.List;

import com.entity.UserInfo;

/**
 * 接口
 * @author Administrator
 *
 */
public interface UserDao {
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public List<UserInfo> query(String sql);
	/**
	 * 
	 * @param userinfo
	 * @return
	 */
	public int insert(UserInfo userinfo);
	
//	public int deleteOrUpdate(String sql);
//	public int insert(String sql);
	
}
