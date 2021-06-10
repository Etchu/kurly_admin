// category.js
$(function(){
	$("#add_category").click(function(){
		$(".category_add_modal").addClass("open");
		$("#save").show();
		$("#modify").hide();
		$(".modal_content h1 span").html("카테고리 추가");
		//$(".category_add_modal").show();
		//$(".category_add_modal").css("display", "block");
	});
	$("#save").click(function(){
		if($("#category_name").val() == '') {
			return;
		}
		// $(".category_add_modal").removeClass("open");
		// $("#category_name").val("");
		// ajax 호출
		$.ajax({
			url:"/api/insert_category?name="+$("#category_name").val(),
			type:"get",
			success:function(data) {
				alert(data.message);
				if(data.status == 'success'){
					$(".category_add_modal").removeClass("open");
					$("#category_name").val("");
					location.reload();
				}
			}
		})
	})
	$("#cancel").click(function(){
		$(".category_add_modal").removeClass("open");
		//$(".category_add_modal").hide();
		//$(".category_add_modal").css("display", "");
		$("#category_name").val("");
	})
	
	
	$(".delete_btn").click(function(){
		let name = $(this).parent().parent().find(".item_name").html();
		
		if(confirm(name+" 카테고리를 삭제하시겠습니까?") == false)
			return;
			 
		let seq = $(this).parent().parent().find(".item_no").html();
		$.ajax({
			url:"/category?seq="+seq,
			type:"delete",
			success:function(data){
				alert(data.message);
				location.reload();
			}
		})
	})
	
	$(".modify_btn").click(function(){
		window.modify_seq = $(this).parent().parent().find(".item_no").html();
		
		let name = $(this).parent().parent().find(".item_name").html();
		window.originalName = name;
		$("#modify").prop("disabled", true);
		//alert(seq);
		$(".category_add_modal").addClass("open");
		$("#save").hide();
		$("#modify").show();
		$("#category_name").val(name);
		$(".modal_content h1 span").html("카테고리 수정");
	})
	
	$("#modify").click(function(){
		if(confirm("수정하시겠습니까?") == false)
			return;
			
		$.ajax({
			url:"/category/"+window.modify_seq+"?name="+$("#category_name").val(),
			type:"patch",
			success:function(data){
				alert(data.message);
				if(data.status == 'success') {
					location.reload();
				}
			},
			error:function(){
				alert("에러");
			}
		})
	})
	
	$("#category_name").on("input", function(){
		if(window.originalName == $("#category_name").val() || $("#category_name").val() == ''){
			$("#modify").prop("disabled", true);
		}
		else {
			$("#modify").prop("disabled", false);
		}
	})
})























