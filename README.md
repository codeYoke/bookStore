# 网上书城项目答辩

[TOC]



## 主要技术

**关键字**：JSP、servlet、Ajax、jstl、JavaScript、注册登录、分页、购物车、增删改查

开发环境：Eclipse、MySQL 5.7、Tomcat 8.0

## 数据库表结构设计

![image](https://github.com/comeCU/books/raw/master/image/img01.png)

**books表结构：**

![image](https://github.com/comeCU/books/raw/master/image/img02.png)

**items表结构：**

![image](https://github.com/comeCU/books/raw/master/image/img03.png)

**orders表结构：**

![image](https://github.com/comeCU/books/raw/master/image/img04.png)

**userinfo表结构：**

![image](https://github.com/comeCU/books/raw/master/image/img05.png)



## 项目包结构

![image](https://github.com/comeCU/books/raw/master/image/img06.png)



## MVC设计模式

### M（model层）

1. biz包：业务处理。
2. dao包：数据访问，对数据库的一些封装操作。
3. entity包：实体类，javabean构建，View层和数据库之间的桥梁作用。

### V（view层）

1. Jsp页面：与用户进行交互的界面。

   ![image](https://github.com/comeCU/books/raw/master/image/img07.png)

### C（controller 层）

1. servlet包：控制层，处理View层Jsp页面发来的请求。

## 注册登录模块

### Register

![image](https://github.com/codeYoke/my-picture/blob/master/bookStore/book2.png)

**注册页面中form表单**

验证用户名、验证密码、验证邮箱

```jsp
<form method="post" action="RegisterServlet" onsubmit="return checkRegister()">
		<dl>
			<dt>用 户 名：</dt>
			<dd><input class="input-text" type="text" id="username" name="username" onblur="isUsernameLegal()"/><span id="usernull"></span><span id="alreadyExsits"></span></dd>
			<dt>密　　码：</dt>
			<dd><input class="input-text" type="password" id="password" name="password" onblur="isPasswordLegal()" /><span id="nullpassword"><font color=\"green\">密码至少8位</font></span><span id="simplepassword"></span></dd>
			<dt>确认密码：</dt>
			<dd><input class="input-text" type="password" id="rePassword" name="rePassword" onblur="isRepasswordLegal()" /><span id="nullrePassword"></span><span id="uneq"></span></dd>
			<dt>Email地址：</dt>
			<dd><input class="input-text" type="text" id="email" name="email" onblur="isEmailLegal()" /><span id="nullemail"><font color=\"green\">请输入正确格式的邮箱</font></span><span id="errorInput"></span></dd>
			<dt></dt>
			<dd class="button"><input class="input-reg" type="submit" name="register" value="" /></dd>
		</dl>
	</form>
```

**Ajax（异步 JavaScript 和 XML ）**：验证用户名是否已被注册，页面部分数据刷新，而无需加载整个网页，提高网站的访问效率。

```javascript
/**
 * Ajax检查用户是否已经被注册
 * 异步：发送请求时不等返回结果，由回调函数处理结果
 * @returns {Boolean}
 */
function isExists() {
	var xmlHttp;	//定义 xmlHttp  XmlHttpRequest对象从服务器异步获取数据后通过javascript修改页面局部信息
	try {
		//根据不同浏览器初始化不同的xmlHttp浏览器对象
		//IE6以上浏览器
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			//FireFox
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				//IE5.5+
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("您的浏览器不支持Ajax");
				return false;
			}
		}
	}
	var username = document.getElementById("username").value;	//获取用户名
	/**
	 * open(提交方式[get|post]，url（servlet路径），同步或异步[false|true])
	 * RegisterServlet： servlet路径
	 * 打开和后台服务器的链接
	 */
	xmlHttp.open("POST", "RegisterServlet?action=check&username="+username, true);	//路径中不能有空格
	xmlHttp.send(null);	//传送数据
	/**
	 * onreadystatechange:调用回调函数
	 * readyState：请求状态，代码是未初始化，1：初始化，2：发送请求，3：开始接受数据，4：接受结果完毕
	 * status: http状态码 200成功，404路径错误，500后台代码错误,
	 */
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState==4 && xmlHttp.status==200) {
			var usernull = document.getElementById("usernull");
			var result = xmlHttp.responseText;	//接受服务器端传过来的数据，写出来的数据都是String类型
			if(result == "true") {
				usernull.innerHTML = "<font color='red'>当前用户名已被注册</font>";
				return false;
			} else if(result == "false") {
				usernull.innerHTML = "<font color='green'>当前用户名可用</font>";
				return true;
			}
		}
	}
}
```



### Login

![image](https://github.com/codeYoke/my-picture/blob/master/bookStore/book2.png)

**LoginServlet中doGet方法：**

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");//获取页面传过来的参数
		String password = request.getParameter("password");
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
```



## 网站首页及搜索图书模块

![image](https://github.com/codeYoke/my-picture/blob/master/bookStore/book3.png)



### **分页展示**

Jstl标签库 c：forEach，循环遍历展示图书信息。

```jsp
<c:forEach var="book" items="${books }">
					<tr>
						<td><input type="checkbox" name="bookId" value="${book.bid }" /></td>
						<td class="title">${book.bookname }</td>
						<input type="hidden" name="title" value = "${book.bid }:${book.bookname}"/>
						<td>￥${book.b_price }</td>
						<input type="hidden" name="price" value = "${book.bid }:${book.b_price}"/> <!-- b_price跟数据库字段名相对应 -->
						<td>${book.stock }</td>
						<input type="hidden" name="stock" value = "${book.bid }:${book.stock}"/>
						<td class="thumb"><img src="${book.image }" /></td>
						<input type="hidden" name="image" value = "${book.bid }:${book.image}"/>
					</tr>
</c:forEach>
```

```jsp
<!--分页开始-->
			<%if(request.getAttribute("current") != null) { %>
			
			<div class="page-spliter">
				<a href="SearchServlet">首页</a>
				<%for(int i = 1; i <= totalPage; i++) { %>
					<%if(i==no) { %>	<!-- 如果不是当前页显示为链接 -->
							<span class="current"><%=i %></span>
							<%continue;} %>
						<a href="SearchServlet?currentPage=<%=i %>"><%=i %></a>
				<%} %>
				<a href="SearchServlet?currentPage=<%=totalPage %>">尾页</a>
			</div>
			
			<%} %>
<!--分页结束-->
```

**servlet处理**

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String currentPage = request.getParameter("currentPage");//获取当前页
		int no = currentPage == null?1:Integer.parseInt(currentPage);//如果当前页为空，则默认为1，否则转化为相应的int
		
//		String currentSearchPage = request.getParameter("currentSearchPage");
		String bookname = request.getParameter("keywords");//获取你输入的书名		
		List<Book> books = null;
		if(bookname==null || bookname.equals("")){
			books=bookbiz.findAll(3, no);//查询所有。3：代表每页显示的条数，no：当前显示页面
			request.setAttribute("totalPage", (bookbiz.count()/3 + 1));//将总页面数存入request
			
		} else {
			books = bookbiz.findBookByName(bookname,3, no);//根据书名模糊查询出数据
			System.out.println(books.size());
			request.setAttribute("totalPage", (books.size()/3 + 1));//将总页面数存入request
		}
		
		request.setAttribute("books", books);//将查询出的数据存入request
		request.setAttribute("current", no);//将当前页存入request	
		request.getRequestDispatcher("main.jsp").forward(request, response);//页面转发	
}
```

### 关键字匹配查询

**输入“J”关键字，展示搜索结果**

![image](https://github.com/codeYoke/my-picture/blob/master/bookStore/book4.png)



## 购物车模块

### 增

![image](https://github.com/codeYoke/my-picture/blob/master/bookStore/book4.gif)

**注意：**

当库存不足时，防止库存数被减到负值。

```java
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
					if(image[j].indexOf(bids[i]+":")<0) {
						continue;
					}
					if(price[j].indexOf(bids[i]+":")<0) {
						continue;
					}
					if(stock[j].indexOf(bids[i]+":")<0) {
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
```



### 删

移除操作

```java
protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取图书id
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
```



### 改

1. **前端shopping.jsp页面，添加onblur失去焦点事件，当鼠标离开输入框时执行JavaScript代码，调用update方法。**

```jsp
<c:set value="1" var="count"></c:set>
        <c:forEach var="book" items="${bookcart }">
            <tr>
                <input type="hidden" id="hidden_bid_${count }" name="hidden_bid_${count }"
                       value="${book.bid }"/>
                <td class="thumb"><img src="${book.image }" /></td>
                <td class="title">${book.bookname }</td>
                <td><input class="input-text" type="text" id="nums_${count }" 
                           name="nums_${count }" value="${book.count }" onblur="update(${bookcart_count}, ${count }, ${book.bid })"/></td> 	<!-- onblur事件 -->
                <input type="hidden" id="hidden_${count }" 
                       name="hidden_${count }" value="${book.price }"/>
                <td>￥<span id="price_${count }"></span></td>
                <input type="hidden" id="hidden_book_total_price_${count }" name="hidden_book_total_price_${count }" />
                <td><span id="remove_${count }"><a href="#" onclick="del(${book.bid})">移除</a></span></td> <!-- href链接， -->
            </tr>
         <c:set value="${count+1 }" var="count"></c:set>
</c:forEach>
```

2. **异步更新图书购买量数据，并向控制层Servlet中返回一个“update”**

```javascript
/**
 * Ajax修改购物车中的数量
 */
function update(size_str, i, bid_str) {
	//初始化XMLHttpRequest对象
	var xmlHttp;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				e.message();
				alert("你的浏览器不支持Ajax");
			}
		}
	}
	//2.获取修改后的数量
	var num_str = document.getElementById("nums_"+i);
	//3.打开服务器链接
	xmlHttp.open("POST", "ModifyCartServlet?action=update&bid="+bid_str+"&count="+num_str.value, true);
	//4.传值 ，无参传null值
	xmlHttp.send(null);
	//5.设置回调函数
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			count(size_str);//调用Servlet中的方法
		}
	}
}
```

3. **servlet中通过前端返回的请求调用相应的方法。**

```java
String action = request.getParameter("action");
if(action.equals("update")) {
    updateCart(request, response);
}
else if(action.equals("remove")) {
    remove(request, response);
}
```

4. **控制层Servlet更新购物车数据，并修改库存。**

```java
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
```



### 查

与搜索图书模块类似



## 我的订单模块

1. 获取订单信息，存入List中

   ```java
   <%
   	List orders = (List) request.getAttribute("orders");		
   %>
   ```

2. 订单展示

```jsp
<c:forEach var="order" items="${orders }">
    <tr>
        <td id="id_${td_id}">${order.oid }</td>
        <td id="user_${td_id}">${order.username }</td>						
        <td id="crdt_${td_id}">${order.createdate }</td>
        <td id="total_${td_id}">${order.total_price }</td>
        <td class="thumb"><img src="${order.image }" /></td>					
        <td>${order.bookname }</td>
        <td>${order.b_price }</td>
        <td>${order.total_price/order.b_price }</td>
    </tr>
</c:forEach>
```



## 用户退出

**当用户退出登录，清空session。**

```java
<%--用户退出登录 --%>
<%
	request.getSession().removeAttribute("loginuser");
	request.getSession().removeAttribute("bookcart");
	request.getSession().removeAttribute("bookcart_count");
	response.sendRedirect("login.jsp");
%>
```



## 一些注意细节

1. **为防止游客非法访问页面，在每一个前端页面body之后加上登录检测代码。**

```jsp
<%
	String username = (String)session.getAttribute("loginuser");
	if(username == null) {
	response.sendRedirect("login.jsp");
	}
%>
```

2. **当库存不足时，防止库存数被减到负值。**

```java
if(oneStock > 0) {//如果库存大于0
	//...
}
```

3. **href中加上空链接，否则该页面中的session不会刷新，必须手动刷新。**

```jsp
<span id="remove_${count }"><a href="#" onclick="del(${book.bid})">移除</a></span>
```


## 致谢

感谢何老师、Java web课程老师的指导，并感谢同学们的相互帮助！
