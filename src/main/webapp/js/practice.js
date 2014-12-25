$(function() {
	var ue = UE.getEditor('container');
	
	var practise = $("#coursePractise").val();
	if(practise!=null && practise!=""){
		ue.setContent(practise);
	}
	
	function uploadHtml() {
		var html = ue.getContent();
		var courseId = $("#courseId").val();
		$.ajax(url, {
			type : "POST",
			url : "${pageContext.request.contextPath }/trainer/practice/save",
			data : 	{"id":courseId,"content":html},
			dataType : "json",
			contentType : "application/json; charset=utf8",
			success : function(data) {
				
			}
		});
	}
});
