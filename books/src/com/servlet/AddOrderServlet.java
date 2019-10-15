package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.ItemBiz;
import com.biz.OrderBiz;
import com.biz.impl.ItemBizImpl;
import com.biz.impl.OrderBizImpl;
import com.dao.ItemDao;
import com.dao.OrderDao;
import com.dao.impl.ItemDaoImpl;
import com.dao.impl.OrderDaoImpl;
import com.entity.Item;
import com.entity.Order;


public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ItemBiz itembiz;
    private ItemDao itemdao;
    private OrderBiz orderbiz;
    private OrderDao orderdao;
	

	
	public void init(ServletConfig config) throws ServletException {
		
		itembiz = new ItemBizImpl();
		itemdao = new ItemDaoImpl();
		itembiz.setItemdao(itemdao);
		
		orderbiz = new OrderBizImpl();
		orderdao = new OrderDaoImpl();
		orderbiz.setOrderdao(orderdao);
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取用户名
		String username = request.getSession().getAttribute("loginuser").toString();
		
		Order order = new Order();
		order.setUsername(username);
		//2.生成订单
		if(!orderbiz.newOrder(order)) {
			System.out.println("添加失败");
			return;
		}
		//3.生成订单详细信息
		int count_str = Integer.parseInt(request.getParameter("count"));
		String oid_str = orderbiz.getCurrentOid(username);
		for(int i = 1; i< count_str; i++) {
			String bid = request.getParameter("hidden_bid_"+ i);//获取bid
			String book_count = request.getParameter("nums_"+ i);//获取图书数量
			String book_price = request.getParameter("hidden_"+ i);
			String totalPrice = request.getParameter("hidden_book_total_price_"+i);//获取单个图书总价格
			//String hidden_total_price = request.getParameter("hidden_total_price");//获取提交订单总价格
			
			Item item = new Item();
			item.setOid(Integer.parseInt(oid_str));
			item.setBid(Integer.parseInt(bid));
			item.setCount(Integer.parseInt(book_count));
			item.setPrice(Double.parseDouble(book_price));
			item.setState(0);
			item.setTotal_price(Double.parseDouble(totalPrice));
			
			itembiz.addItemToOrder(item);			
		}
		//提交订单后清空购物信息
		request.getSession().removeAttribute("bookcart");
		request.getSession().removeAttribute("bookcart_count");
		response.sendRedirect("ShowOrderServlet");//我的订单
	
	}

}
