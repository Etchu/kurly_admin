$(function(){
	$("#login_btn").click(function(){
		let data = {
				id:$("#user_id").val(),
				pwd:$("#user_pwd").val()
		}
		
		$.ajax({
			url:"/login",
			type:"post",
			contentType:"application/json",
			data:JSON.stringify(data),
			success:function(result){
				if(result.status == 'failed') {
					alert(result.message);
				}
				else {
					location.href = "/products";
				}
			},
			error:function(e) {
				alert("에러");
				console.log(e);
			}
		})
	})
})