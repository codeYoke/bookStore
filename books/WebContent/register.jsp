<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
<title></title>
	
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<!-- 链入js文件，注册检验 -->
<script type="text/javascript" src="js/valiadate.js"></script>
	
</head>
<body>
<div id="header" class="wrap">
		<div id="logo">鲲鹏网上书城</div>
		<div id="navbar">
		</div>
	</div>
	<!--头部结束-->
<div id="register">
	<div class="title">
		<h2>欢迎注册鲲鹏网上书城</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.填写注册信息</li>
			<li class="unpass">2.注册成功</li>
		</ul>
	</div>
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
</div>	
<!--底部-->
<div id="footer" class="wrap">
	鲲鹏网上书城 &copy; 版权所有
</div>
</body>

</html>