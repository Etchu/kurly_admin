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
	
	$("#startDate, #endDate").datepicker();
	
	let offset = getParam("offset");
    let curPage = Number(offset);
    let pageCnt = 0;
	
	$.ajax({
		url:"/member_cnt",
		type:"get",
		success:function(result) {
			pageCnt = Math.ceil(result.count/10);
			
			$(".pagers").html(""); // .pagers 내부 HTML 삭제
            let start = 0;
            if(curPage - 4 >= 0){
                start = curPage-4;
            }
            else {
                start = 0;
            }
            for(let i=start; i<pageCnt; i++){
                let template;
                if(offset == i){
                    template='<a href="/member?offset='+i+'" class="current">'+(i+1)+'</a>';
                }
                else {
                    template='<a href="/member?offset='+i+'">'+(i+1)+'</a>';
                }
                $(".pagers").append(template);
                if(i-start == 8){
                    break;
                }
            }	
		}
	})
	
	$("#prev").click(function(){
		let newOffset = Number(getParam("offset")) - 1;
		if(newOffset < 0) {return;}
		location.href="/member?offset=" +newOffset; 
	})
	$("#prev-10").click(function(){
		let newOffset = Number(getParam("offset")) - 10;
		if(newOffset < 0) {newOffset = 0;}
		location.href="/member?offset=" +newOffset;
	})
	$("#next").click(function(){
		let newOffset = Number(getParam("offset")) + 1;
		if(newOffset > pageCnt) {return;}
		location.href="/member?offset=" +newOffset;
	})
	$("#next-10").click(function(){
		let newOffset = Nmber(getParam("offset")) + 10;
		if(newOffset > pageCnt) {newOffset = pageCnt;}
		location.href="/member?offset=" +newOffset;
	})
	
	$(".modify").click(function(){
		let memberItem = $(this).parent();
		if(memberItem.find("select").prop("disabled") == true ){
			memberItem.find("select").prop("disabled", false);
			$(this).find("i").eq(0).css("display", "none");
			$(this).find("i").eq(1).css("display", "inline-block");
		}
		else {
			if(!confirm("저장하시겠?")) return;
			memberItem.find("select").prop("disabled", true);
			$(this).find("i").eq(0).css("display", "inline-block");
			$(this).find("i").eq(1).css("display", "none");
			// 수정 ajax 호출
			let data = {
					"seq":memberItem.find(".member_seq").html(),
					"type":memberItem.find("#type option:selected").val(),
					"grade":memberItem.find("#grade option:selected").val(),
					"status":memberItem.find("#status option:selected").val()
			}
			
			$.ajax({
				url:"/member",
				type:"patch",
				contentType:"application/json",
				data:JSON.stringify(data),
				success:function(result){
					alert(result.message);
					location.reload();
				},
				error:function(e) {
					alert("에러"),
					console.log(e);
				}
			})
		}
	})
	$(".delete").click(function(){
		if(!confirm("삭제하실?")) return;
		$.ajax({
			url:"/member/"+$(this).parent().find(".member_seq").html(),
			type:"delete",
			success:function(result) {
				alert(result.message);
				location.reload();
			},
			error:function(e){
				alert("에러");
				console.log(e);
			}
		})
	})
})

function getParam(sname) {
    var params = location.search.substr(location.search.indexOf("?") + 1);
    var sval = "";
    params = params.split("&");
    for (var i = 0; i < params.length; i++) {
        temp = params[i].split("=");
        if ([temp[0]] == sname) { sval = temp[1]; }
    }
    return sval;
}