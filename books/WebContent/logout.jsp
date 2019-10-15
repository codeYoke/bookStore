<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--用户退出登录 --%>
<%
	request.getSession().removeAttribute("loginuser");
	request.getSession().removeAttribute("bookcart");
	request.getSession().removeAttribute("bookcart_count");
	response.sendRedirect("login.jsp");
%>