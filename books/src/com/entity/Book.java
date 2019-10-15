package com.entity;

import java.io.Serializable;

public class Book implements Serializable {
	private final String bid_seq = "BS_BOOKID.nextVal";	//数据库，做自增长
	private int bid;
	private String bookname;
	private double price;
	private String image;
	private String stock;//库存
	private int count = 0;
	
	
	
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getBid_seq() {
		return bid_seq;
	}
	
}
