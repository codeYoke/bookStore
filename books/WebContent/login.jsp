<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<!-- 链入js，登录检验 -->
<script type="text/javascript" src="js/valiadate.js"></script>

<title>网上书城</title>	
</head>
<body>
	<div id="header" class="wrap">
	<div id="logo">鲲鹏网上书城</div>
	<div id="navbar">
	</div>
</div>
<div id="login">
	<h2>用户登陆</h2>
	<form method="post" action="LoginServlet" onsubmit="return checkUserName()">
		<dl>
			<dt>用户名：</dt>
			<dd><input class="input-text" type="text" id="username" name="username" value=""/><span id="uname"></span></dd>
			<dt>密　码：</dt>
			<dd><input class="input-text" type="password" id="password" name="password" value=""/><span  id="pwd"></span></dd>
			<dt>&nbsp;</dt>
			<dd class="button"><input class="input-btn" type="submit" name="submit" value="" /><input class="input-reg" type="button" name="register" value="" onclick="window.location='register.jsp';" /></dd>
		</dl>
	</form>
</div>
<div id="footer" class="wrap">
	鲲鹏网上书城 &copy; 版权所有
</div>
<%-- <jsp:include page="foot.html"></jsp:include> --%>


</body>
 </html>