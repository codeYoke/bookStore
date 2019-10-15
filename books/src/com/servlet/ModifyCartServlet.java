package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.BookBiz;
import com.biz.impl.BookBizImpl;
import com.dao.BaseDao;
import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.entity.Book;

public class ModifyCartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private BookBiz bookbiz = null;
	private BookDao bookdao = null;
	private BaseDao basedao = null;
	
	@Override
	public void init() throws ServletException {
		bookdao = new BookDaoImpl();
		bookbiz = new BookBizImpl();
		bookbiz.setBookdao(bookdao);
		
		basedao = new BaseDao();
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("update")) {
			updateCart(request, response);
		}
		else if(action.equals("remove")) {
			remove(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);		
	}
	
	
	protected void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取图书id和修改后的数量
		String bid = request.getParameter("bid");
		String count = request.getParameter("count");
		//2.获取购物车
		List<Book> bookcart = (List<Book>) request.getSession().getAttribute("bookcart");
		//3.查找购物车中修改过的图书，并修改相应的信息
		for (Book book : bookcart) {
			if(Integer.valueOf(bid) == book.getBid()) {
				//4.获取修改前的图书数量
				int old_count = book.getCount();
				//5.设置当前图书的新数量
				book.setCount(Integer.valueOf(count));
				System.out.println("当前图书新数量"+count);
				
				int nowStock = 0;
				try {
					nowStock = basedao.queryStock(Integer.parseInt(bid));
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				//6.修改库存
				bookbiz.changeStock(Integer.parseInt(bid), (old_count-Integer.parseInt(count))+"");
				break;			
			}
		}
	}
	
	protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取图书
		String bid = request.getParameter("bid");
		//2.获取购物车
		List<Book> bookcart = (List<Book>) request.getSession().getAttribute("bookcart");
		//3.循环查找购物车中相同的图书id
		for(int i = 0; i< bookcart.size(); i++) {
			Book book = bookcart.get(i);
			if(Integer.valueOf(bid) == book.getBid()) {
				//4.移除图书
				bookcart.remove(i);
				//5.修改库存
				bookbiz.changeStock(Integer.parseInt(bid), book.getCount()+"");
				break;
			}
		}		
		
		//6.将购物车保存到session中
//		request.getSession().setAttribute("bookcart", bookcart);
		request.getSession().setAttribute("bookcart_count", bookcart.size());
		System.out.println(request.getSession().getAttribute("bookcart_count"));
	}

}
