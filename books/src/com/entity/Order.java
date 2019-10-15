package com.entity;

import java.io.Serializable;

import com.dao.BaseDao;

public class Order extends BaseDao implements Serializable {
	private int oid = 1;
	private String username;
	
	
	public int getOid() {
		oid = maxid();	//获取最大id值
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 由于表没有设置自动增长
	 * 获取order表中最大id值
	 * @return
	 */
	public int maxid() {
		String sql = "select * from orders order by oid desc limit 0,1";
		openConnection();
		int i = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				i = rs.getInt(1) + 1;
			} else {
				i = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return i;
	}
	
}
