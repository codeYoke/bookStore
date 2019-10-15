package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.UserBiz;
import com.biz.impl.UserBizImpl;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = null;
	private UserBiz userBiz = null;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		userDao = new UserDaoImpl();
		userBiz = new UserBizImpl();
		userBiz.setUserDao(userDao);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");//获取页面传过来的参数
		String password = request.getParameter("password");
//		System.out.print("fdsafdsfsa");
		//登录操作
		boolean flag = userBiz.checkLogin(username, password);
		
		if(flag) {
			request.getSession().setAttribute("loginuser", username);
//			response.sendRedirect("main.jsp");
			response.sendRedirect("SearchServlet");	//首页直接显示
		} else {//登录失败
			response.setContentType("text/html;charset=utf-8");
//			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("alert(\"登录失败！请重新登录\")");//反斜杠转义
			out.println("open(\"login.jsp\", \"_self\");");//重新打开新的页面, _self在原窗口打开
			out.println("</script>");
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setAttribute("list", "list");
		doGet(request, response);
		
	}

}
