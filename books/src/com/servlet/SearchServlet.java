package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.biz.BookBiz;
import com.biz.impl.BookBizImpl;
import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.entity.Book;

/**
 * Servlet implementation class SearchServlet
 */

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookBiz bookbiz = null;
	private BookDao bookdao = null;
	
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		bookdao = new BookDaoImpl();
		bookbiz = new BookBizImpl();
		bookbiz.setBookdao(bookdao);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int countByName = 0;
		String currentPage = request.getParameter("currentPage");//获取当前页
		int no = currentPage == null?1:Integer.parseInt(currentPage);//如果当前页为空，则默认为1，否则转化为相应的int
		
		String bookname = request.getParameter("keywords");//获取你输入的书名	
		String keywords = bookname;
		List<Book> books = null;
		if(bookname==null || bookname.equals("")|| bookname.equals("null")){
			books=bookbiz.findAll(3, no);//查询所有。3：代表每页显示的条数，no：当前显示页面
			request.setAttribute("totalPage", (bookbiz.count()/3 + 1));//将总页面数存入request
			
		} else {
			countByName = bookbiz.findBookByName(bookname);//记录模糊查询的总记录数
			books = bookbiz.findBookByName(bookname,3, no);//根据书名模糊查询出数据
			//System.out.println("bookname不为0："+books.size());
			request.setAttribute("totalPage", (books.size()/3 + 1));//将总页面数存入request
		}
		
		request.setAttribute("keywords", keywords);
		System.out.println(keywords);
		request.setAttribute("books", books);//将查询出的数据存入request
		request.setAttribute("current", no);//将当前页存入request	
		request.getRequestDispatcher("main.jsp").forward(request, response);//页面转发
		
	}

}
