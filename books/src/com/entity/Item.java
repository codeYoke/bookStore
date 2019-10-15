package com.entity;

import java.io.Serializable;
import java.sql.Date;

import com.dao.BaseDao;

public class Item extends BaseDao implements Serializable {
	private int iid = 1;
	private int oid;
	private int bid;
	private Date createDate;
	private double price;
	private double total_price;
	private int count;
	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	private int state;//订单状态
	
	/**
	 * 由于表没有设置自动增长
	 * 获取order表中最大id值
	 * @return
	 */
	public int maxid() {
		String sql = "select * from items order by iid desc limit 0,1";
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
	
	public int getIid() {
		iid = maxid();
		return iid;
	}
	
	public void setIid(int iid) {
		this.iid = iid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	
}
