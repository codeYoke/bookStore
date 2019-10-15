package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.OrderBiz;
import com.biz.impl.OrderBizImpl;
import com.dao.OrderDao;
import com.dao.impl.OrderDaoImpl;


public class ShowOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderBiz orderbiz;
    private OrderDao orderdao;
    
   
	public void init(ServletConfig config) throws ServletException {

		orderbiz=new OrderBizImpl();
		orderdao=new OrderDaoImpl();
		orderbiz.setOrderdao(orderdao);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getSession().getAttribute("loginuser").toString();
		List orders=orderbiz.findByUsername(username, 3, 0);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("orderlist.jsp").forward(request, response);
	
	}

}
