$(function() {
	var ue = UE.getEditor('container');
	
	var practise = $("#coursePractise").val();
	if(practise!=null && practise!=""){
		ue.ready(function() {
			ue.setContent(practise);
		});
	}
	
	$("#uploadButton").on("click", function(){
		var html = ue.getContent();
		var courseId = $("#courseId").val();
		var mydata = '{"id":"' + courseId + '","practise":"' + html + '"}';
		$.ajax({
			type : "POST",
			url : "save",
			data : 	mydata,
			dataType : "json",
			contentType : "application/json; charset=utf8",
			success : function(data) {
				data = data.termmap;
				if (data.success == true) {
					$("#confirmModal").modal('show');
				}
			}
		});
	});
});
