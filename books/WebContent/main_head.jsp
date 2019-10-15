<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


</head>
<body>
<%
	String bookname = request.getParameter("bookname");
	String username = (String)session.getAttribute("loginuser");
	if(username == null) {
		response.sendRedirect("login.jsp");
	}
%>

<!--头部导航条开始-->
<div id="header" class="wrap">
	<div id="logo">鲲鹏网上书城</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<!-- ; EL取值：${sessionScope.loginuser} -->
				<li class="current"><font color="BLACK">欢迎您，<strong><%=session.getAttribute("loginuser") %></strong></font>&nbsp;&nbsp;&nbsp;</li>
				<li><a href="SearchServlet">首页</a></li>
				<li><a href="ShowOrderServlet">我的订单</a></li>
				<li><a href="shopping.jsp">购物车</a></li>
				<li><a href="logout.jsp">注销</a></li>
			</ul>
		</div>
		<form method="post" name="search" action="SearchServlet">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
			<%-- <%session.setAttribute("isSearch", "true"); %>	<!-- 标记是否点击搜索 --> --%>
		</form>
	</div>
</div>

</body>
</html>