package com.biz.impl;

import java.util.List;

import com.biz.UserBiz;
import com.dao.UserDao;
import com.entity.UserInfo;

public class UserBizImpl implements UserBiz {
	private UserDao userDao = null;
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	
	
	
	@Override
	public boolean checkLogin(String username,String password) {

		String sql = "select * from userinfo where username='"+username+"'and password='"+password+"'";
		List<UserInfo> list = userDao.query(sql);
		
		return list.size()>0 ? true : false;
	}




	@Override
	public void setUserDao(UserDao userdao) {
		// TODO Auto-generated method stub
		this.userDao = userdao;
	}




	@Override
	public boolean addUser(UserInfo userinfo) {
		int i = userDao.insert(userinfo);
		
		return i > 0 ? true : false;
	}




	@Override
	public boolean userExist(String username) {
		String sql = "select * from userinfo where username='"+username+"'";
		List<UserInfo> list = userDao.query(sql);		
		System.out.println(sql);
		return list.size()>0?true:false;	//如果查询有数据则已经被注册
	}
	
}
