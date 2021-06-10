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
	<title>마켓컬리 : 관리자페이지</title>
	<link rel="stylesheet" href="/resources/css/reset.css" />
	<link rel="stylesheet" href="/resources/css/login.css" />
	
	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="/resources/js/login.js"></script>
	<script>
		<c:if test="${user != null}">
			location.href="/products";
		</c:if>
	</script>
</head>
<body>
	<div class="login_box">
		<img src="/resources/images/DM_20210324195650_002.PNG" />
		<div class="row">
			<span>아이디</span>
			<span><input type="text" id="user_id" autocomplete="off"/></span>
		</div>
		<div class="row">
			<span>비밀번호</span>
			<span><input type="password" id="user_pwd" /></span>
		</div>
		<div class="row">
			<button id="login_btn">로그인</button>
		</div>
	</div>
</body>
</html>