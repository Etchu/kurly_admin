// product.js
$(function(){
	$.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년'
    });
	
	$("#product_add").click(function(){
		modalReset();
		$(".prod_insert_modal").show();
		$(".modal_content h1 span").html("상품추가");
		$("#save").show();
		$("#modify").hide();
		
		$.ajax({
			url:"/category_list",
			type:"get",
			success:function(data) {
				$("#prod_category").html(""); // #prod_category 안쪽의 내용을 삭제
				for(let i=0; i<data.length; i++) {
					let template = '<option value="'+data[i].mkpc_seq+'">'+data[i].mkpc_name+'</option>';
					$("#prod_category").append(template);
				}
			}
		});
		
		$.ajax({
			url:"/company_list",
			type:"get",
			success:function(data) {
				$("#prod_brand").html(""); // #prod_category 안쪽의 내용을 삭제
				for(let i=0; i<data.length; i++) {
					let template = '<option value="'+data[i].mkb_seq+'">'+data[i].mkb_name+'</option>';
					$("#prod_brand").append(template);
				}
			}
		})
		
	})
	$("#save").click(function(){
		let data = {
		  "mkp_name": $("#prod_name").val(),
		  "mkp_sub_name": $("#prod_sub_name").val(),
		  "mkp_bi_seq": $("#prod_brand option:selected").val(),
		  "mkp_ci_seq": $("#prod_category option:selected").val(),
		  "mkp_price": $("#prod_price").val(),
		  "mkp_discount": $("#prod_discount").prop("checked")?1:0,
		  "mkp_discount_rate": $("#prod_discount_value").val(),
		  "mkp_kurly_only": $("#prod_only").prop("checked")?1:0,
		  "mkp_point_rate": $("#prod_point_value").val(),
		  "mkp_unit": $("#prod_unit").val(),
		  "mkp_weight": $("#prod_weight").val(),
		  "mkp_early_delivery": $("#prod_early").prop("checked")?1:0,
		  "mkp_delivery": $("#prod_delivery").prop("checked")?1:0,
		  "mkp_packing_type": $("#prod_packing").val(),
		  "mkp_allergy_info": $("#allergy_info").val(),
		  "mkp_exp_date": $("#prod_expire_date").val(),
		  "mkp_relation_seq": null
		}
		
		console.log(JSON.stringify(data));
		
		$.ajax({
			url:"/product",
			type:"put",
			contentType:"application/json",
			data:JSON.stringify(data),
			success:function(data){
				let form = $("#prod_img_form");
				let formData = new FormData(form[0]);
				console.log("/product_img/"+data.prod_seq+"?brand="+$("#prod_brand option:selected").html()
						+"&name="+$("#prod_name").val());
				$.ajax({
					url:"/product_img/"+data.prod_seq+"?brand="+$("#prod_brand option:selected").html()
						+"&name="+$("#prod_name").val(),
					type:"put",
					data:formData,
					contentType:false,
					processData:false,
					success:function(){
						alert("저장됨");
						//$(".prod_insert_modal").hide();
						location.reload();
						
					}
				})
			},
			error:function(){
				alert("에러");
			}
		})
		
	});
	$("#modify").click(function(){
		$(".prod_insert_modal").hide();
	});
	$("#cancel").click(function(){
		$(".prod_insert_modal").hide();
	});
	
	$(".prod_delete button").click(function(){
		if(!confirm("삭제하시겠습니까?")) return;
		let seq = $(this).attr("data-seq");
		$.ajax({
			url:"/product/"+seq,
			type:"delete",
			success:(data) => {
				alert(data.message);
				location.reload();
			}
		})
	})
	
	$(".prod_modify button").click(function(){
		modalReset();
		$(".prod_insert_modal").show();
		$(".modal_content h1 span").html("상품수정");
		$("#save").hide();
		$("#modify").show();
		
		let seq = $(this).attr("data-seq");
		$.ajax({
			url:"/product/"+seq,
			type:"get",
			success:function(data) {
				console.log(data.product);
				let product = data.product;
				$("#modify").attr("data-seq", product.mkp_seq);
				$("#prod_name").val(product.mkp_name);
				$("#prod_sub_name").val(product.mkp_sub_name);
				$("#prod_price").val(product.mkp_price);
				$("#prod_discount").prop("checked", product.mkp_discount == 1);
				$("#prod_discount_value").val(product.mkp_discount_rate);
				$("#prod_early").prop("checked", product.mkp_early_delivery == 1);
				// console.log(product.mkp_exp_date.split("T")[0]);
				if(product.mkp_exp_date != null && product.mkp_exp_date != '') {
					// mkp_exp_date의 값이 null이 아니면서 들어있는 값이 빈 값이 아닐 때 setting
					$("#prod_expire_date").val(product.mkp_exp_date.split("T")[0]);
					$("#prod_expire_chk").prop("checked", true);
				}
				$("#prod_only").prop("checked", product.mkp_kurly_only == 1);
				$("#prod_delivery").prop("checked", product.mkp_delivery == 1);
				$("#prod_unit").val(product.mkp_unit);
				$("#prod_weight").val(product.mkp_weight);
				$("#prod_packing").val(product.mkp_packing_type);
				$("#allergy_info").val(product.mkp_allergy_info);
				$("#prod_img_form > input").val();
				if(product.mkp_point_rate != null && product.mkp_point_rate != ''){
					$("#prod_point").prop("checked", true);
					$("#prod_point_value").val(product.mkp_point_rate);
				}
				// input type="file"은 입력된 값을 변경할 수 없다.
				if(product.image_name != null){
					$(".prev_img").html(product.image_name);
				}
				else {
					$(".prev_img").html("이미지 없음");
				}
				
				$.ajax({
					url:"/category_list",
					type:"get",
					success:function(data) {
						console.log(data);
						$("#prod_category").html(""); // #prod_category 안쪽의 내용을 삭제
						for(let i=0; i<data.length; i++) {
							let template = '<option value="'+data[i].mkpc_seq+'">'+data[i].mkpc_name+'</option>';
							$("#prod_category").append(template);
						}
						$("#prod_category option").prop("selected", false);
						$("#prod_category").val(product.mkp_ci_seq).prop("selected", true);
					}
				});
				$.ajax({
					url:"/company_list",
					type:"get",
					success:function(data) {
						$("#prod_brand").html(""); // #prod_category 안쪽의 내용을 삭제
						for(let i=0; i<data.length; i++) {
							let template = '<option value="'+data[i].mkb_seq+'">'+data[i].mkb_name+'</option>';
							$("#prod_brand").append(template);
						}
						$("#prod_brand option").prop("selected", false);
						$("#prod_brand").val(product.mkp_bi_seq).prop("selected", true);
					}
				});
			},
			error:function(e){
				alert("에러");
				console.log(e);
			}
		})
	})
	
	$("#modify").click(function(){
		if(!confirm("수정하시겠습니까?")) return;
		let data = {
		  "mkp_seq":$(this).attr("data-seq"),
		  "mkp_name": $("#prod_name").val(),
		  "mkp_sub_name": $("#prod_sub_name").val(),
		  "mkp_bi_seq": $("#prod_brand option:selected").val(),
		  "mkp_ci_seq": $("#prod_category option:selected").val(),
		  "mkp_price": $("#prod_price").val(),
		  "mkp_discount": $("#prod_discount").prop("checked")?1:0,
		  "mkp_discount_rate": $("#prod_discount_value").val(),
		  "mkp_kurly_only": $("#prod_only").prop("checked")?1:0,
		  "mkp_point_rate": $("#prod_point_value").val(),
		  "mkp_unit": $("#prod_unit").val(),
		  "mkp_weight": $("#prod_weight").val(),
		  "mkp_early_delivery": $("#prod_early").prop("checked")?1:0,
		  "mkp_delivery": $("#prod_delivery").prop("checked")?1:0,
		  "mkp_packing_type": $("#prod_packing").val(),
		  "mkp_allergy_info": $("#allergy_info").val(),
		  "mkp_exp_date": $("#prod_expire_date").val(),
		  "mkp_relation_seq": null
		}
		let form = $("#prod_img_form");
		let formData = new FormData(form[0]);
		$.ajax({
			url:"/product_img/"+data.mkp_seq+"?brand="+$("#prod_brand option:selected").html()
				+"&name="+$("#prod_name").val(),
			type:"put",
			data:formData,
			contentType:false,
			processData:false,
			success:function(){
				console.log("이미지 전송 끝");
			}
		})
		// ajax 통신
		$.ajax({
			url:"/product",
			type:"patch",
			contentType:"application/json",
			data:JSON.stringify(data),
			success:function(data){
				alert(data.message);
				location.reload();
			},
			error:function(e){
				console.log(e);
				alert("에러");
			}
		})
	})
	
	function modalReset() {
		$("#prod_name").val("");
		$("#prod_sub_name").val("");
		$("#prod_price").val("");
		$("#prod_discount").prop("checked", false);
		$("#prod_discount_value").val("");
		$("#prod_early").prop("checked", false);
		$("#prod_expire_date").val("");
		$("#prod_expire_chk").prop("checked", false);
		$("#prod_only").prop("checked", false);
		$("#prod_delivery").prop("checked", false);
		$("#prod_unit").val("");
		$("#prod_weight").val("");
		$("#prod_packing").val("");
		$("#allergy_info").val("");
		$("#prod_img_form > input").val("");
		$("#prod_point").prop("checked", false);
		$("#prod_point_value").val("");
	}
	
	$("#prod_expire_date").datepicker();
	$(".modal_content").draggable();
})

















