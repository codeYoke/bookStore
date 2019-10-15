package com.dao.impl;

import java.util.List;

import com.dao.BaseDao;
import com.dao.BookDao;

public class BookDaoImpl extends BaseDao implements BookDao {

	@Override
	public List query(String sql) {
		String[] column = {"bid","bookname","b_price","image","stock"};
		return query(sql, column);
	}

	@Override
	public int count() {
		String sql = "select count(*) from books";	//查询books的总条数
		openConnection();
		int i = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				i = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return i;
	}

	@Override
	public int update(String sql) {
		return deleteOrUpdate(sql);
	}

	@Override
	public int count(String sql) {
		
		openConnection();
		int i = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				i = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return i;
	}
	
	/**
	 * 测试count方法
	 * @param args
	 */
	/*public static void main(String[] args) {
		BookDaoImpl b = new BookDaoImpl();
		int a = b.count();
		System.out.println(a);
	}*/

}
