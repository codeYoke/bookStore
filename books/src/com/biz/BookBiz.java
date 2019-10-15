package com.biz;

import java.util.List;

import com.dao.BookDao;
import com.entity.Book;

/**
 * 业务逻辑层
 * @author Administrator
 *
 */
public interface BookBiz {
	/**
	 * 根据书名模糊查询
	 * @param bookname
	 * @return List集合
	 */
	List<Book> findBookByName(String bookname,int page_books, int page_No);
	
	//接口中所有方法默认为public
	
	/**
	 * 
	 * @Title: findGoodsByName
	 * @Description: 通过关键字模糊查询所有商品记录
	 *  @param goodsName 搜索框关键字
	 *  @return 返回记录条数
	 */
	public int findBookByName(String goodsName);
	/**
	 * 查询所有书籍数
	 * @return
	 */
	public int count();
	
	public void setBookdao(BookDao bookdao);
	/**
	 * 根据分页查询所有图书
	 * @param page_books 每页显示条数
	 * @param page_No 当前显示页面
	 * @return
	 */
	List<Book> findAll(int page_books, int page_No);
	
	/**
	 * 修改商品库存,防止库存为负数
	 * @param bid 根据商品id修改
	 * @param count 数量
	 * @return
	 */
	public boolean changeStock(int bid, String count);
}
