function addtranee() {
	var mydata = '{"loginname":"' + $("#nameInput").val() + '","phoneno":"' + $('#phoneInput').val() + '","email":"' + $('#emailInput').val() + '","role":"2"}';
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