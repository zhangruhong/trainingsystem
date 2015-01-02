function addtranee() {
	var mydata = '{"loginname":"' + $("#nameInput_trainee").val() + '","phoneno":"' + $('#phoneInput_trainee').val() + '","email":"' + $('#emailInput_trainee').val() + '","role":"2"}';
	$.ajax({
		type : "POST",
		url : "add",
		data : mydata,
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			if (data.success == true) {
				location.reload(true);
			} else {
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert("-" + XMLHttpRequest.status + "-" + XMLHttpRequest.readyState + "-" + textStatus + "-" + errorThrown);
		}
	});
};
function addtraner() {
	var mydata = '{"loginname":"' + $("#nameInput_trainer").val() + '","phoneno":"' + $('#phoneInput_trainer').val() + '","email":"' + $('#emailInput_trainer').val() + '","role":"1"}';
	$.ajax({
		type : "POST",
		url : "add",
		data : mydata,
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			if (data.success == true) {
				location.reload(true);
			} else {
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert("-" + XMLHttpRequest.status + "-" + XMLHttpRequest.readyState + "-" + textStatus + "-" + errorThrown);
		}
	});
};