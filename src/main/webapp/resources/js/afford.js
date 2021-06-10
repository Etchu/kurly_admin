$(function(){
	
	$.ajax({
		url:"/category_list",
		type:"get",
		success:function(data) {
			$("#category").html(""); // #prod_category 안쪽의 내용을 삭제
			$("#category").append('<option value="0">전체 카테고리</option>');

			for(let i=0; i<data.length; i++) {
				let template = '<option value="'+data[i].mkpc_seq+'">'+data[i].mkpc_name+'</option>';
				$("#category").append(template);
			}
			//$("#category option").prop("selected", false);
			//$("#category").val(product.mkp_ci_seq).prop("selected", true);
		}
	});
	
	$.ajax({
		url:"/company_list",
		type:"get",
		success:function(data) {
			$("#brand").html(""); // #prod_category 안쪽의 내용을 삭제
			$("#brand").append('<option value="0">전체 브랜드</option>');
			for(let i=0; i<data.length; i++) {
				let template = '<option value="'+data[i].mkb_seq+'">'+data[i].mkb_name+'</option>';
				$("#brand").append(template);
			}
			//$("#brand option").prop("selected", false);
			//$("#brand").val(product.mkp_ci_seq).prop("selected", true);
		}
	});
	
	$("#add_category").click(function(){
		$(".product_add_modal").show();
		
		getProducts();
	})
	
	$("#save").click(function(){
		//$(".product_add_modal").hide();
		
		if($(".modal_prod_item_sel:checked").length == 0) {
			alert("선택된 상품이 없습니다.");
			return;
		}
		
		let selected = $(".modal_prod_item_sel:checked");
		let selectedSeqList = new Array();
		
		console.log($(".modal_prod_item_sel:checked").eq(1));
		
		for(let i = 0; i < selected.length; i++) {
			let value = selected.eq(i).val();
			selectedSeqList.push(value);
		}
		console.log(selectedSeqList);
		
		$.ajax({
			url:"/afford",
			type:"put",
			contentType:"application/json",
			data:JSON.stringify(selectedSeqList),
			success:function(data){
				alert("저장 완료");
				location.reload();
			},
			else:function(e){
				console.log(e);
				alert("저장 실패");
			}
		})
	})
	
	$("#cancel").click(function(){
		$(".product_add_modal").hide();
	})
	
	$("#category").change(function(){
		getProducts();
		$("#selectAll").prop("checked", false);
	})
	
	$("#brand").change(function(){
		getProducts();
		$("#selectAll").prop("checked", false);
	})
	$("#search_btn").click(function(){
		getProducts();
	})
	
	$("#search_keyword_popup").keydown(function(e){
		if(e.keyCode == 13) {
			getProducts();
		}
	})
	
	$("#selectAll").change(function(){
		// alert("changed");
		//console.log($(this).prop("cheked"));
		if($(this).prop("checked")){
			$(".modal_prod_item_sel").prop("checked", true);
		}
		else {
			$(".modal_prod_item_sel").prop("checked", false);
		}
	})
	
	function getProducts() {
		let data = {
				keyword : $("#search_keyword_popup").val(),
				cate_seq : $("#category option:selected").val(),
				brand_seq : $("#brand option:selected").val()
		}
		
		$.ajax({
			url:"/product_afford_list",
			type:"post",
			contentType:"application/json",
			data:JSON.stringify(data),
			success:function(data){
				console.log(data);
				$(".modal_prod_item_wrap").html("");
				for(let i = 0; i<data.length; i++) {
					let template = 
						'<div class="modal_prod_item">'+
							'<div>'+
								'<input type="checkbox" class="modal_prod_item_sel" id="sel'+i+'" value="'+data[i].mkp_seq+'">'+
								'<label for="sel'+i+'"></label>'+	
							'</div>'+
							'<div>'+data[i].brand_name+'</div>'+
							'<div>'+data[i].category_name+'</div>'+
							'<div>'+data[i].mkp_name+'</div>'+
							'<div>'+data[i].mkp_price+'</div>'+
						'</div>';
				$(".modal_prod_item_wrap").append(template);
				}
			}
		});
	}
	
	$(".delete_btn").click(function(){
		
		if(!confirm("삭제하실?")) {return;}
		
		let seq = $(this).attr("data-seq");
		
		$.ajax({
			url:"/afford/"+seq,
			type:"delete",
			success:function(data) {
				alert("삭제 되었습니다.");
				location.reload();
			},
			error:function(e){
				console.log(e);
				alert("삭제 에러 발생");
			}
		})
	})
})