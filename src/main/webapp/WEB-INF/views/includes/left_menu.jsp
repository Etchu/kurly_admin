<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must_revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/resources/css/reset.css" />
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="/resources/css/left_menu.css" />
	<!-- <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script> -->
	<script>
		$(function(){
			let menu_num = "${menu_num}";
			$(".menu a").eq(menu_num).addClass("current");
		})
		
		<c:if test="${user == null}">
			location.href="/";
		</c:if>
	</script>
</head>
<body>
	<div class="left_side">
		<a href="/products" id="logo">
			<img src="/resources/images/DM_20210324195650_002.PNG"/>
		</a>
		<c:if test="${user != null}">
			<div class="user_menu">
				<span>${user}</span>
				<button onclick="/logout">로그아웃</button>
			</div>
		</c:if>
		<div class="menu">
			<a href="/products"><i class="fas fa-box"></i>상품관리</a>
			<a href="/category"><i class="fas fa-sitemap"></i>카테고리관리</a>
			<a href="/recommand"><i class="fas fa-thumbs-up"></i>추천상품관리</a>
			<a href="/md_recommand"><i class="fas fa-user-check"></i>MD추천상품관리</a>
			<a href="/company"><i class="fas fa-building"></i>브랜드(업체)관리</a>
			<a href="/special"><i class="fas fa-won-sign"></i>특가상품관리</a>
			<a href="/afford"><i class="fas fa-hand-holding-usd"></i>알뜰상품관리</a>
			<a href="/member"><i class="fas fa-users"></i>회원관리</a>
		</div>
	</div>
</body>
</html>

