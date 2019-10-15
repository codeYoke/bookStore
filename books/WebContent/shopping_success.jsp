<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="UTF-8">
<title>鲲鹏网上书城</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>

</head>
<body>
<%
	String username = (String)session.getAttribute("loginuser");
	if(username == null) {
	response.sendRedirect("login.jsp");
	}
%>

<!-- 引入 -->
<jsp:include page="main_head.jsp"></jsp:include>

<div id="content" class="wrap">
	<div class="success">
		<div class="information">
			<p>恭喜：添加成功！</p>
			<p><a href="shopping.jsp">点此查看购物车详情&gt;&gt;</a></p>
		</div>
	</div>
</div>

<div id="footer" class="wrap">
	鲲鹏网上书城 &copy; 版权所有
</div>

</body>
</html>