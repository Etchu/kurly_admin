<!-- products/list.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마켓컬리 : 관리자페이지</title>
	<link rel="icon" href="/resources/images/favicon.png" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.structure.min.css" integrity="sha512-oM24YOsgj1yCDHwW895ZtK7zoDQgscnwkCLXcPUNsTRwoW1T1nDIuwkZq/O6oLYjpuz4DfEDr02Pguu68r4/3w==" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css" integrity="sha512-9h7XRlUeUwcHUf9bNiWSTO9ovOWFELxTlViP801e5BbwNJ5ir9ua6L20tEroWZdm+HFBAWBLx2qH4l4QHHlRyg==" crossorigin="anonymous" />
	
	<link rel="stylesheet" href="/resources/css/product_list.css" />
	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous"></script>
	<script src="/resources/js/product.js"></script>
</head>
<body>
	<div class="wrap">
		<%@include file="/WEB-INF/views/includes/left_menu.jsp" %>
		<div class="right_side">
			<div class="right_side_header">
				<h1><i class="fas fa-box"></i>상품관리</h1>
				<div class="search_box">
					<input type="text" />
					<img src="/resources/images/DM_20210324195650_057.PNG" />
				</div>
				<button id="product_add"><i class="fas fa-box"></i> 상품추가</button>
			</div>
			
			<div class="product_list">
				<div class="prod_header">
					<div>NO</div>
					<div>이름</div>
					<div>이미지</div>
					<div>카테고리</div>
					<div>브랜드</div>
					<div>할인</div>
					<div>독점</div>
					<div>샛별</div>
					<div>택배</div>
					<div>조회수</div>
					<div>구매수</div>
					<div>등록일</div>
					<div>수정</div>
					<div>삭제</div>
				</div>
				<div class="products">
					<c:forEach items="${list }" var="item">
						<div class="product_item">
							<div class="prod_seq">${item.mkp_seq}</div>
							<div class="prod_name">${item.mkp_name}</div>
							<div class="prod_img">
								<c:if test="${item.image_uri == null }">
									<img src="/resources/images/noimage.jpg"/>
								</c:if>
								<c:if test="${item.image_uri != null }">
									<img src="${item.image_uri}"/>
								</c:if>
							</div>
							<div class="prod_cate">
								${item.category_name}
							</div>
							<div class="prod_brand">
								${item.brand_name}
							</div>
							<div class="prod_discount">
								<c:if test="${item.mkp_discount == 0}">
									<input type="checkbox" disabled />
								</c:if>
								<c:if test="${item.mkp_discount == 1}">
									<input type="checkbox" disabled checked/>
								</c:if>
							</div>
							<div class="prod_only">
								<c:if test="${item.mkp_kurly_only == 0}">
									<input type="checkbox" disabled />
								</c:if>
								<c:if test="${item.mkp_kurly_only == 1}">
									<input type="checkbox" disabled checked/>
								</c:if>
							</div>
							<div class="prod_early">
								<c:if test="${item.mkp_early_delivery == 0}">
									<input type="checkbox" disabled />
								</c:if>
								<c:if test="${item.mkp_early_delivery == 1}">
									<input type="checkbox" disabled checked/>
								</c:if>
							</div>
							<div class="prod_delivery">
								<c:if test="${item.mkp_delivery == 0}">
									<input type="checkbox" disabled />
								</c:if>
								<c:if test="${item.mkp_delivery == 1}">
									<input type="checkbox" disabled checked/>
								</c:if>
							</div>
							<div class="prod_count">${item.mkp_count}</div>
							<div class="prod_buy_count">${item.mkp_buy_count}</div>
							<div class="prod_reg_date">
								<fmt:formatDate value="${item.mkp_reg_date}" pattern="yyyy-MM-dd HH:mm" />
							</div>
							<div class="prod_modify">
								<button type="button" data-seq="${item.mkp_seq}">수정</button>
							</div>
							<div class="prod_delete">
								<button type="button" data-seq="${item.mkp_seq}">삭제</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	
	<div class="prod_insert_modal">
		<div class="modal_content">
			<h1>
				<i class="fas fa-box"></i>
				<span>상품추가</span>
			</h1>
			<p class="item_title">상품이름</p>
			<input type="text" id="prod_name"/>
			<p class="item_title">상품 부제목</p>
			<input type="text" id="prod_sub_name"/>
			<div class="cate_brand_area">
				<div class="category">
					<p class="item_title">카테고리</p>
					<select id="prod_category">
						
					</select>
				</div>
				<div class="brand">
					<p class="item_title">업체(브랜드)</p>
					<select id="prod_brand">
						
					</select>
				</div>
			</div>
			<div class="price_wrap">
				<div class="price_area">
					<p class="item_title">상품 가격</p>
					<input type="number" id="prod_price"/>
				</div>
				<div class="discount_area">
					<p class="item_title">상품 할인</p>
					<input type="checkbox" id="prod_discount"/>
					<input type="number" id="prod_discount_value"/>
				</div>
				<div class="point_area">
					<p class="item_title">적립</p>
					<input type="checkbox" id="prod_point"/>
					<input type="number" id="prod_point_value"/>
				</div>
			</div>
			<p class="item_title">배송정보</p>
			<div class="prod_delivery_info">
				<div class="prod_early_area">
					<input type="checkbox" id="prod_early"/>
					<label for="prod_early">샛별배송</label>
				</div>
				<div class="prod_delivery_area">
					<input type="checkbox" id="prod_delivery"/>
					<label for="prod_delivery">택배배송</label>
				</div>
				<div class="prod_only_area">
					<input type="checkbox" id="prod_only"/>
					<label for="prod_only">독점상품</label>
				</div>
			</div>
			
			<div class="prod_detail_info">
				<div class="prod_unit_area">
					<p class="item_title">단위</p>
					<input type="text" id="prod_unit"/>
				</div>
				<div class="prod_weight_area">
					<p class="item_title">무게</p>
					<input type="number" id="prod_weight"/>
				</div>
			</div>
			<div class="prod_detail_info">
				<div class="prod_packing_area">
					<p class="item_title">포장타입</p>
					<input type="text" id="prod_packing"/>
				</div>
				<div class="prod_expire_area">
					<p class="item_title">유통기한</p>
					<input type="checkbox" id="prod_expire_chk"/>
					<input type="text" id="prod_expire_date"/>
				</div>
			</div>
			<div class="prod_relation_area">
				<p class="item_title">연관상품</p>
				<div class="relation_list">
					<div class="relation_item_header">
						<div>번호</div>
						<div>브랜드</div>
						<div>제품명</div>
						<button id="relation_add"><i class="fas fa-plus"></i></button>
					</div>
					<div class="relation_item_wrap">
						<div class="relation_item">
							<div class="relation_no"></div>
							<div class="relation_brand"></div>
							<div class="relation_name"></div>
							<button class="relation_delete"><i class="fas fa-times"></i></button>
						</div>
					</div>
				</div>
			</div>
			<p class="item_title">알레르기 정보</p>
			<textarea id="allergy_info"></textarea>
			<p class="item_title">제품 이미지</p>
			<form id="prod_img_form">
				<!-- <input type="file" name="prod_img_file" accept="image/jpeg, image/png"/>  -->
				<span class="prev_img"></span>
				<input type="file" name="file" accept=".jpg, .png, .jpeg"/>
			</form>
			<div class="button_area">
				<button id="save">저장</button>
				<button id="modify">수정</button>
				<button id="cancel">취소</button>
			</div>
		</div>
	</div>
	
</body>
</html>


















