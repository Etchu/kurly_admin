<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/resources/css/reset.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.structure.min.css" integrity="sha512-oM24YOsgj1yCDHwW895ZtK7zoDQgscnwkCLXcPUNsTRwoW1T1nDIuwkZq/O6oLYjpuz4DfEDr02Pguu68r4/3w==" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css" integrity="sha512-9h7XRlUeUwcHUf9bNiWSTO9ovOWFELxTlViP801e5BbwNJ5ir9ua6L20tEroWZdm+HFBAWBLx2qH4l4QHHlRyg==" crossorigin="anonymous" />
	<link rel="stylesheet" href="/resources/css/member_list.css" />
	
	
	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous"></script>
	<script src="/resources/js/member_list.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/includes/left_menu.jsp" %>
	<div class="wrap">
		<h1><i class="fas fa-users"></i>회원관리</h1>
		<div class="search_area">
			<div class="search_date">
				<span>가입일</span>
				<input type="text" id="startDate" autocomplete="off"/>
				<span>-</span>
				<input type="text" id="endDate" autocomplete="off"/>
			</div>			
			<div class="search_keyword">
				<select id="target">
					<option value="1">아이디</option>
					<option value="2">이름</option>
				</select>
				<input type="text" id="keyword" />
				<button id="search">검색</button>
			</div>
		</div>
		<div class="member_contents">
			<div class="contents_header">
				<div>번호</div>
				<div>아이디</div>
				<div>이메일</div>
				<div>이름</div>
				<div>가입일</div>
				<div>회원유형</div>
				<div>회원등급</div>
				<div>사용상태</div>
			</div>
			<div class="member_list">
				<c:forEach items="${memberList}" var="member">
					<div class="member_item">
						<div>${member.mkm_seq}</div>
						<div>${member.mkm_id}</div>
						<div>${member.mkm_email}</div>
						<div>${member.mkm_name}</div>
						<div>2021-04-08</div>
						<div>
							<select id="type" disabled>
								<c:if test="${member.mkm_type == 0}">
									<option value="0" selected>관리자</option>
									<option value="1">정상유저</option>
									<option value="2">휴면유저</option>
								</c:if>
								<c:if test="${member.mkm_type == 1}">
									<option value="0">관리자</option>
									<option value="1"selected>정상유저</option>
									<option value="2">휴면유저</option>
								</c:if>
								<c:if test="${member.mkm_type == 2}">
									<option value="0">관리자</option>
									<option value="1">정상유저</option>
									<option value="2"selected>휴면유저</option>
								</c:if>
							</select>
						</div>
						<div>
							<select id="grade" disabled>
								<c:if test="${member.mkm_grade == 0}">
									<option value="0" selected>웰컴</option>
									<option value="1">일반</option>
									<option value="2">프렌즈</option>
									<option value="3">화이트</option>
									<option value="4">라벤더</option>
									<option value="5">퍼플</option>
									<option value="6">더 퍼플</option>
								</c:if>
								<c:if test="${member.mkm_grade == 1}">
									<option value="0">웰컴</option>
									<option value="1" selected>일반</option>
									<option value="2">프렌즈</option>
									<option value="3">화이트</option>
									<option value="4">라벤더</option>
									<option value="5">퍼플</option>
									<option value="6">더 퍼플</option>
								</c:if>
								<c:if test="${member.mkm_grade == 2}">
									<option value="0">웰컴</option>
									<option value="1">일반</option>
									<option value="2" selected>프렌즈</option>
									<option value="3">화이트</option>
									<option value="4">라벤더</option>
									<option value="5">퍼플</option>
									<option value="6">더 퍼플</option>
								</c:if>
								<c:if test="${member.mkm_grade == 3}">
									<option value="0">웰컴</option>
									<option value="1">일반</option>
									<option value="2">프렌즈</option>
									<option value="3" selected>화이트</option>
									<option value="4">라벤더</option>
									<option value="5">퍼플</option>
									<option value="6">더 퍼플</option>
								</c:if>
								<c:if test="${member.mkm_grade == 4}">
									<option value="0">웰컴</option>
									<option value="1">일반</option>
									<option value="2">프렌즈</option>
									<option value="3">화이트</option>
									<option value="4" selected>라벤더</option>
									<option value="5">퍼플</option>
									<option value="6">더 퍼플</option>
								</c:if>
								<c:if test="${member.mkm_grade == 5}">
									<option value="0">웰컴</option>
									<option value="1">일반</option>
									<option value="2">프렌즈</option>
									<option value="3">화이트</option>
									<option value="4">라벤더</option>
									<option value="5" selected>퍼플</option>
								<option value="6">더 퍼플</option>
								</c:if>
								<c:if test="${member.mkm_grade == 6}">
									<option value="0">웰컴</option>
									<option value="1">일반</option>
									<option value="2">프렌즈</option>
									<option value="3">화이트</option>
									<option value="4">라벤더</option>
									<option value="5">퍼플</option>
									<option value="6" selected>더 퍼플</option>
								</c:if>
							</select>
						</div>
						<div>
							<select id="status" disabled>
								<c:if test="${member.mkm_status == 0}">
									<option value="0" selected>휴면</option>
									<option value="1">정상</option>
									<option value="2">차단</option>
								</c:if>
								<c:if test="${member.mkm_status == 1}">
									<option value="0">휴면</option>
									<option value="1" selected>정상</option>
									<option value="2">차단</option>
								</c:if>
								<c:if test="${member.mkm_status == 2}">
									<option value="0">휴면</option>
									<option value="1">정상</option>
									<option value="2" selected>차단</option>
								</c:if>
							</select>
						</div>
						<a href="#" class="modify">
							<i class="fas fa-pencil"></i>
							<i class="fas fa-download"></i>
						</a>
						<a href="#" class="delete"><i class="fas fa-trash"></i></a>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="pager_area">
			<button class="prev-10">
				<i class="fas fa-chevron-left"></i>
				<i class="fas fa-chevron-left"></i>
			</button>
			<button class="prev">
				<i class="fas fa-chevron-left"></i>
			</button>
			
			<div class="pagers"></div>
			
			<button class="next">
				<i class="fas fa-chevron-right"></i>
			</button>
			<button class="next-10">
				<i class="fas fa-chevron-right"></i>
				<i class="fas fa-chevron-right"></i>
			</button>
		</div>
	</div>
</body>
</html>