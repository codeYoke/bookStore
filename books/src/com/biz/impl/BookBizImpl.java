package com.biz.impl;

import java.sql.SQLException;
import java.util.List;

import com.biz.BookBiz;
import com.dao.BaseDao;
import com.dao.BookDao;
import com.entity.Book;

public class BookBizImpl implements BookBiz {
	BookDao bookdao = null;
	BaseDao basedao = new BaseDao();
	
	@Override
	public List<Book> findBookByName(String bookname,int page_books, int page_No) {
		String sql = "select * from books  where bookname like '%"+bookname+"%' limit "+(page_No-1)*page_books+","+page_books;	//模糊匹配查询
		return bookdao.query(sql);
	}

	@Override
	public int count() {
		return bookdao.count();
	}

	@Override
	public void setBookdao(BookDao bookdao) {
		this.bookdao = bookdao;
	}

	@Override
	public List<Book> findAll(int page_books, int page_No) {
		//分页查询，MySQL中limit子句指定偏移量查询
		String sql = "select * from books where bid limit "+(page_No-1)*page_books+","+page_books;	
		return bookdao.query(sql);
	}

	@Override
	public boolean changeStock(int bid, String count) {
		boolean flag = false;
		String sql = "update books set stock=stock+"+count+" where bid="+bid;
		try {
			if(basedao.queryStock(bid) > 0) {
				flag = bookdao.update(sql)>0?true:false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return flag;
	}

	@Override
	public int findBookByName(String booksName) {
		String sql = "select count(*) from books  where bookname like '%"+booksName+"%'";
		int countByName = bookdao.count(sql);
		return countByName;
	}

	
}
