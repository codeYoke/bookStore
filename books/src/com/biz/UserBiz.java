package com.biz;

import java.util.List;

import com.dao.UserDao;
import com.entity.UserInfo;

public interface UserBiz {
	/**
	 * 查询方法
	 * @param sql
	 * @return
	 */
	public boolean checkLogin(String username,String password);
	public void setUserDao(UserDao userdao);
	/**
	 * 注册、添加用户
	 * @param userinfo
	 * @return
	 */
	public boolean addUser(UserInfo userinfo);
	
	/**
	 * 判断用户是否已存在
	 * @param username
	 * @return
	 */
	public boolean userExist(String username);
}
