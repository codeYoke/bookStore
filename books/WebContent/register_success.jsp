<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>鲲鹏网上商城</title>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<%
	String username = (String)session.getAttribute("loginuser");
	if(username == null) {
	response.sendRedirect("login.jsp");
	}
%>

<div id="header" class="wrap">
	<div id="logo">鲲鹏网上书城</div>
	<div id="navbar">
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="register">
	<div class="title">
		<h2>欢迎注册鲲鹏网上商城</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="past">1.填写注册信息</li>
			<li class="last">2.注册成功</li>
		</ul>
	</div>
	<div class="success">
		<div class="information">
			<p>恭喜：注册成功！</p>
			<p><a href="login.jsp">点此进入用户中心&gt;&gt;</a></p>
		</div>
	</div>
</div>
<div id="footer" class="wrap">
	鲲鹏网上商城 &copy; 版权所有
</div>
</body>
</html>