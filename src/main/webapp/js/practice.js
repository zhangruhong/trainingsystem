$(function() {
	//----------   trainer的jsp        ---------------------//
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
	
	//----------   admin的jsp        ---------------------//
	$("#sec_btn_admin").on("click", function(){
		var name = $("#sec_box_admin").val();
		if(name != null && name != ""){
			var mydata = '{"loginname":"' + name + '"}';
			$.ajax({
				type : "POST",
				url : "search",
				data : 	mydata,
				dataType : "json",
				contentType : "application/json; charset=utf8",
				success : function(data) {
					//debugger
					data = data.termmap;
					if (data.success == true) {
						if(data.terms.length == 0){
							$("tbody > tr").remove();
							$(".input_message").show();
							$(".input_message span").text(" the name which you input does not exist");
						} else{
							var practices = data.terms;
							var tableHtml = null;
							$.each(practices, function(i, practice){
								var practiceScore = practice.score;
								if(practice.score==0){
									practiceScore = "未打分";
								}
								var practiceScoreDescription = practice.scoreDescription;
								if(practice.scoreDescription==null){
									practiceScoreDescription = "未评价";
								}
						        var trHtml = "<tr><td>"+ (i+1) +"</td><td>"+name+"</td><td>"+ practice.course.name +"</td><td>"+
						        practiceScore+"</td><td>"+practiceScoreDescription+"</td></tr>";
						        tableHtml = trHtml + tableHtml;
							});  
							$(".input_message").hide();
							$("tbody").html(tableHtml);
						}
					}
				}
			});
		}else{
			$("tbody > tr").remove();
			$(".input_message").show();
			$(".input_message span").text("please input search condition!");
		}
	});
});
