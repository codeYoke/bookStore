package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
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

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookBiz bookbiz = null;
	private BookDao bookdao = null;
	
	private BaseDao basedao = null;
	
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		bookdao = new BookDaoImpl();
		bookbiz = new BookBizImpl();
		bookbiz.setBookdao(bookdao);
		
		basedao = new BaseDao();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] bids = request.getParameterValues("bookId");//获取页面所有名字为bookID的input值
		String[] title = request.getParameterValues("title");
		String[] price = request.getParameterValues("price");
		String[] stock = request.getParameterValues("stock");
		String[] image = request.getParameterValues("image");
		
		int oneStock = 0;
		boolean isOrNotChangeStock = true;//是否修改库存，保证库存不被修改为负数
		
		/*double allPrice;//总价
		double oneAllPrice;//书单价*购买数量
*/		List bookcart;	//获取购物车中的内容，没有则初始化一个列表
		bookcart = (List) request.getSession().getAttribute("bookcart");//虚拟一个空表
		if(bookcart == null) {
			bookcart = new ArrayList();
		}
		
		for(int i = 0; i< stock.length; i++) {
			System.out.println("stock"+stock[i]);
		}
		
		//获取复选框选中的值
		for (int i = 0; i < bids.length; i++) {
			boolean isNotExists = true;//定义购物车中的值
			Book book = new Book();
			int bid = Integer.parseInt(bids[i]);
			
			try {
				oneStock = basedao.queryStock(bid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(oneStock > 0) {//如果库存大于0
				//查看当前图书是否已经存在于购物车中
				//查看购物车列表是否已经存在该图书，对比两个图书id
				for (int j = 0; j < bookcart.size(); j++) {
					Book existBook = (Book) bookcart.get(j);
					if(existBook.getBid() == bid) {//如果购物车中的id==被选中的图书id，则购物车中的数量+1
						bookcart.remove(j);
						existBook.setCount(existBook.getCount()+1);//购物车中商品数量+1
						double totalPrice = existBook.getPrice();
						bookcart.add(existBook);//商品总价
						System.out.println("总价"+totalPrice);
						isNotExists = false;
						
						//修改库存
						isOrNotChangeStock = bookbiz.changeStock(bid, "-1");
						break;
					}
				}
				
				if(!isNotExists) {
					continue;
				}
				book.setBid(bid);
				
				//判断当前获取的图书信息是否为指定bid下的信息
				for (int j = 0; j < title.length; j++) {
//					request.setCharacterEncoding("utf-8");
					//解决页面图书标题中文显示乱码
					String title_temp =new String(title[j].getBytes("ISO-8859-1"),"utf-8");//取标题
					
					
					if(title_temp.indexOf(bids[i]+":")<0) {//indexOf匹配bids是否包含在title_temp中，如果没有返回-1
						continue;
					}
					if(image[j].indexOf(bids[i]+":")<0) {//indexOf匹配bids是否包含在title_temp中，如果没有返回-1
						continue;
					}
					if(price[j].indexOf(bids[i]+":")<0) {//indexOf匹配bids是否包含在title_temp中，如果没有返回-1
						continue;
					}
					if(stock[j].indexOf(bids[i]+":")<0) {//indexOf匹配bids是否包含在title_temp中，如果没有返回-1
						continue;
					}
					//添加指定bid下的图书信息
					book.setBookname(filter(title_temp, bids[i]));
					book.setImage(filter(image[j], bids[i]));
					book.setPrice(Double.parseDouble(filter(price[j], bids[i])));
//						book.setPrice(Double.valueOf(filter(price[j], bids[i])));
					book.setStock(filter(stock[j], bids[i]));
					book.setCount(1);
					
					//修改库存
					bookbiz.changeStock(bid, "-1");
					bookcart.add(book);				
				}	
				
			}
			
				
		}	
		request.getSession().setAttribute("bookcart", bookcart);//将购物车信息存入session
		request.getSession().setAttribute("bookcart_count", bookcart.size());//购物车的数量
		response.sendRedirect("shopping_success.jsp");
		
	}
	
	/**
	 * 截取图书信息
	 * @param s：从哪里开始截取
	 * @param t：信息
	 * @return
	 */
	public String filter(String s, String t) {
		return s.substring(t.length()+1, s.length());
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
