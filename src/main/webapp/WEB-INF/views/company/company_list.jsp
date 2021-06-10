<!-- category/list.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마켓컬리 : 브랜드(업체) 관리</title>
	<link rel="stylesheet" href="/resources/css/category_list.css" />
	
	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="/resources/js/company.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/includes/left_menu.jsp" %>
	<div class="wrap">
		<div class="header_area">
			<h1><i class="fas fa-building"></i>브랜드(업체) 관리</h1>
			<div class="header_right">
				<div class="search_box">
					<input type="text" id="search_keyword"/>
					<img src="/resources/images/DM_20210324195650_057.PNG" />
				</div>
				<button id="add_category">브랜드(업체) 추가</button>
			</div>
		</div>
		<div class="list">
			<div class="list_header">
				<div>NO</div>
				<div>브랜드(업체) 명</div>
				<div>등록일</div>
				<div>제품 수</div>
				<div>수정</div>
				<div>삭제</div>
			</div>
			<div class="list_area">
				<c:forEach items="${list }" var="item">
					<div class="list_item">
						<div class="item_no">${item.mkb_seq}</div>
						<div class="item_name">${item.mkb_name}</div>
						<div class="item_reg_date">
							${item.mkb_reg_date}
						</div>
						<div class="item_count">123</div>
						<div class="item_modify">
							<button type="button" class="modify_btn">수정</button>
						</div>
						<div class="item_delete">
							<button type="button" class="delete_btn">삭제</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div class="category_add_modal">
		<div class="modal_content">
			<h1><i class="fas fa-building"></i><span>브랜드(업체) 추가</span></h1>
			<div class="input_row">
				<p>브랜드(업체) 명</p>
				<input type="text" id="category_name" placeholder="브랜드(업체) 명을 입력하세요"/>
			</div>
			<div class="input_row">
				<button id="save">저장</button>
				<button id="modify">수정</button>
				<button id="cancel">취소</button>
			</div>
		</div>
	</div>
</body>
</html>