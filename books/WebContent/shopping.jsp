<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" import="java.util.*" %>
<!-- 导入Book类 -->
<%@ page import="com.entity.Book" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>

<script type="text/javascript">
/**
 * 计算购物车内价格信息
 */
function count(size_str) {
	var size = parseFloat(size_str);
	//alert(size);
	var total_price = 0;
	for (var i = 1; i <= size; i++) {
		
		var num = document.getElementById("nums_"+i);//获取当前文本框中的数量
		var price = document.getElementById("price_"+i);//获取当前图书的单价
		var hidden_price = document.getElementById("hidden_book_total_price_"+i);//获取单个图书的总价格
		var book_price_str = document.getElementById("hidden_"+i);//
		//alert("fdsafds");
		var count = parseFloat(num.value);//
		
		var book_price = parseFloat(book_price_str.value);
		
		price.innerHTML = count*book_price;//在页面td中显示价格
		
		hidden_price.value = count*book_price;//方便提交订单用
		total_price = total_price + count*book_price;//计算总价格
		console.log("总价"+total_price);
		
	}
	
	var hidden_total_price = document.getElementById("hidden_total_price");
	hidden_total_price.value = total_price;
	document.getElementById("total_price").innerHTML = total_price;
}

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
			count(size_str);
		}
	}
}

/**
 * Ajax移除图书
 */
function del(bid_str) {
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
	//var num_str = document.getElementById("nums_"+i);
	//3.打开服务器链接
	xmlHttp.open("POST", "ModifyCartServlet?action=remove&bid="+bid_str, true);
	//4.传值 ，无参传null值
	xmlHttp.send(null);
	//5.设置回调函数
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			//alert("fdsafd");
			window.location="shopping.jsp";
		}
	}
}

</script>


</head>
<body onload="count(${sessionScope.bookcart_count})">
<!-- 引入 -->
<jsp:include page="main_head.jsp"></jsp:include>
<%--
<%
	List bookcart = (List)request.getSession().getAttribute("bookcart");
	
	double allPrice = 0;
	double oneAllPrice = 0;
	if(bookcart != null) {	//购物车非空
		for(int i = 0; i< bookcart.size(); i++) {
			Book book = (Book)bookcart.get(i);
			oneAllPrice = book.getCount()*book.getPrice();
			allPrice += oneAllPrice;
		} 
	}
	request.setAttribute("allPrice", allPrice);
%>
 --%>

<%
	String username = (String)session.getAttribute("loginuser");//用户先登录才可访问购物车
	if(username == null) {
	response.sendRedirect("login.jsp");
}
%>
 
<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="AddOrderServlet">
			<table>
				<tr class="title">
					<th class="view">图片预览</th>
					<th>书名</th>
					<!-- <th class="danjia">单价</th> -->
					<th class="nums">数量</th>
					<th class="price">价格</th>
					<th class="nums">操作</th>
				</tr>
				
				<c:set value="1" var="count"></c:set>
				<c:forEach var="book" items="${bookcart }">
				<tr>
					<input type="hidden" id="hidden_bid_${count }" name="hidden_bid_${count }"
						value="${book.bid }"/>
					<td class="thumb"><img src="${book.image }" /></td>
					<td class="title">${book.bookname }</td>
					<td><input class="input-text" type="text" id="nums_${count }" 
						name="nums_${count }" value="${book.count }" onblur="update(${bookcart_count}, ${count }, ${book.bid })"/></td>
					<input type="hidden" id="hidden_${count }" 
						name="hidden_${count }" value="${book.price }"/>
					<td>￥<span id="price_${count }"></span></td>
					<input type="hidden" id="hidden_book_total_price_${count }" name="hidden_book_total_price_${count }" />
					<td><span id="remove_${count }"><a href="#" onclick="del(${book.bid})">移除</a></span></td> <!-- href链接， -->
				</tr>
				<c:set value="${count+1 }" var="count"></c:set>
				</c:forEach> 
			</table>
			<input type="hidden" value="${count }" id="count" name="count" />	
			<div class="button">
				<h4>总价：￥<span id="total_price"></span>元</h4> <%--<%=request.getAttribute("allPrice") %> --%>
				<input type="hidden" id="hidden_total_price" name="hidden_total_price"/>
				<input class="input-chart" type="submit" name="submit" value=""/>
			</div>
		</form>
	</div>
</div>

</body>
</html>