<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@page import="com.entity.Item" %>
<%@page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>

</head>
<body>
<%
	String username = (String)session.getAttribute("loginuser");
	if(username == null) {
	response.sendRedirect("login.jsp");
}
%>

<jsp:include page="main_head.jsp"></jsp:include>

<%
	List orders = (List) request.getAttribute("orders");		
%>

<div id="content" class="wrap">
	<div class="list orderList">
			<table>
				<tr class="title">
					<th class="orderId">订单编号</th>					
					<th class="userName">收货人</th>					
					<th class="createTime">下单时间</th>
					<th class="status">总价</th>
					<th>订单商品</th>
					<th>商品名称</th>
					<th>商品单价</th>
					<th class="price">商品数量</th>
				</tr>
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
				
			</table>
	</div>
</div>
<div id="footer" class="wrap">
	鲲鹏网上书城 &copy; 版权所有
</div>

</body>
</html>