$(function() {
	// ---------- trainer的view.jsp ---------------------//
	
	// ---------- trainer的viewDetail.jsp ---------------------//
	$("input[name='viewPracticeContent']").on("click", function(){
		var pContent=$(this).next().val();
		$('#practiceViewModal').modal('show');
		var practiceUE = UE.getEditor('practiceViewContainer',{
			toolbars:['fullscreen'],
			initialFrameWidth:560 , //初始化编辑器宽度
	        initialFrameHeight:300 ,
	        readonly : true
		});
		practiceUE.ready(function() {
			practiceUE.setContent(pContent);
		});
	});
	$("input[name='inputScore']").on("click", function(){
		var practiceId=$(this).next().val();
		$("#practiceId").val(practiceId);
		$('#inputScoreModal').modal('show');
	});
	$("#confirmScore").on("click", function(){
		var mydata = '{"id":"' + $("#practiceId").val() + '","score":"' + $("#practiceScore").val() + '"}';
		$.ajax({
			type : "POST",
			url : "inputScore",
			data : 	mydata,
			dataType : "json",
			contentType : "application/json; charset=utf8",
			success : function(data) {
				data = data.termmap;
				if (data.success == true) {
					location.reload(); 
				}
			}
		});
	});
});