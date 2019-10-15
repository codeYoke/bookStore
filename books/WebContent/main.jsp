<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 导入core标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>鲲鹏网上书城</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>

<script type="text/javascript" src="js/valiadate.js"></script>

<script type="text/javascript">
/**
 * 获取复选框是否被选中
 */
function checkSel() {
	var sel = document.getElementsByName("bookId");
	var isSel = false;
	for(var i = 0; i < sel.length; i++) {
		if(sel[i].checked) {
			isSel = true;
		}
	}
	if(isSel == false) {
		alert("请选择图书");
	}		
	return isSel;
}
</script>

</head>
<body>
<%
	String bookname = (String)request.getAttribute("keywords");
	String username = (String)session.getAttribute("loginuser");
	if(username == null) {
		response.sendRedirect("login.jsp");
	}	
	
	int no = 0;
	int totalPage = 0;
	List books = (List)request.getAttribute("books");
	if(request.getAttribute("current") != null) {
		no = (Integer)request.getAttribute("current");//获取当前页面数
		totalPage = (Integer)request.getAttribute("totalPage");//获取总页面数
	}
%>


<!-- 引入 -->
<jsp:include page="main_head.jsp"></jsp:include>

<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="CartServlet" id="myform">
			<table>
				<tr class="title">
					<th class="checker"></th>
					<th>书名</th>
					<th class="price">价格</th>
					<th class="store">库存</th>
					<th class="view">图片预览</th>
				</tr>
				
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
				
			</table>
			<%-- <%out.println(request.getAttribute("current")); %> --%>
			<!--分页开始-->
			<%if(request.getAttribute("current") != null) { %>
			
			<div class="page-spliter">
				<a href="SearchServlet?keywords=<%=bookname%>">首页</a>
				<%for(int i = 1; i <= totalPage; i++) { %>
					<%if(i==no) { %>	<!-- 如果不是当前页显示为链接 -->
							<span class="current"><%=i %></span>
							<%continue;} %>
						<a href="SearchServlet?currentPage=<%=i %>&keywords=<%=bookname%>"><%=i %></a>
				<%} %>
				<a href="SearchServlet?currentPage=<%=totalPage %>&keywords=<%=bookname%>">尾页</a>
			</div>
			
			<%} %>
			<!--分页结束-->
			<div class="button"><input class="input-btn" type="submit" name="submit" value="" onclick="return checkSel()"/></div>
		</form>
	</div>
</div><!--中间内容结束-->

<div id="footer" class="wrap">
	鲲鹏网上书城 &copy; 版权所有
</div>

</body>
<%--

<!-- 
	1.首先js判断是否有选择商品
	2.在servlet中的获取所有的被选中的商品的id、name、price、image、stock（数组）
	3.判断购物车中是否有值，无值则创建一个购物车 List bookcart， 购物车中有值，修改购物车中商品数量+1， book表中的库存-1
		//修改商品库存的方法
	4.将获取的商品添加到bookcart中，将bookcart存放到session中
	5.用jstl标签的c：forEach取购物车的值
	
 -->
 
 <!-- 
 	el表达式特点，自动转化类型
 	语法：${bean.name} 或者 ${bean['name']}
 	
 	属性无值时返回""
 	el的取值范围：依照顺序从page，request，session，application范围内查找,找到后就不会继续找下去
 	pageScope[jsp页面上下文].键、requestScope[请求范围内].键、sessionScope[会话范围内].键、applicationScope[应用程序范围内].键
  -->
  
  <!-- 
  	jstl标准标签库->核心标签->通用标签、条件标签、迭代标签
  	通用标签：set 设置指定范围内的变量值<c:set var="变量名" value="${100+1}" scope="作用域">
  	remove：删除指定范围内的变量<c:remove var="变量名" scope="作用域">
  	out计算表达式并显示结果<c:out value="${变量名}">
  	
  	条件标签：if 
  	语法：<c:if test="${判断条件表达式返回true|false}" var="变量名[用于保存返回的true|false]" scope="指定var变量的作用域">
  	<c:if test="${requestScope.current!=null}"></c:if>
  	
  	迭代标签：实现对集合中对象的遍历<c:forEach>
  	语法：<c:forEach items="${要遍历的集合对象}" var="指定集合类的数据变量名称" 
  		begin="指定从集合的第几位开始" end="到第几位结束" step="指定迭代步长" varStatus="获取迭代中的信息变量名"></c:forEach>
   		
   -->

 --%>
</html>