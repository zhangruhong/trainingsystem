$(function() {
	//----------   trainer的jsp        ---------------------//
	var ue = UE.getEditor('container');
	
	var practise = $("#coursePractise").val();
	if(practise!=null && practise!=""){
		practise=practise.toString().replace(new RegExp('(["\"])', 'g'),"\\\"");
		console.debug(practise);
		ue.ready(function() {
			ue.setContent(practise);
		});
	}
	
	$("#uploadButton").on("click", function(){
		var html = ue.getContent();
		var courseId = $("#courseId").val();
		html=html.toString().replace(new RegExp('(["\"])', 'g'),"\\\""); 
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
	
	
	//----------   trainee的jsp        ---------------------//
	var trainerUE = UE.getEditor('trainerContainer',{
		readonly : true
	});
	var traineeUE = UE.getEditor('traineeContainer');
	if(practise!=null && practise!=""){
		trainerUE.ready(function() {
			trainerUE.setContent(practise);
		});
	}
	
	var practice = $("#practiceContent").val();
	if(practice!=null && practice!=""){
		traineeUE.ready(function() {
			traineeUE.setContent(practice);
		});
	}
	
	$("#commitButton").on("click", function(){
		var html = traineeUE.getContent();
		var practiceId = $("#practiceId").val();
		var mydata = '{"id":"' + practiceId + '","content":"' + html + '"}';
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
