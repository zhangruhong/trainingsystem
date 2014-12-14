function addTerm() {
	var mydata = '{"name":"' + $("#nameInput").val() + '","description":"'
			+ $('#descriptionInput').val() + '"}';
	$.ajax({
		type : "POST",
		url : "add",
		data : mydata,
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			if (data.success == true) {
				var terms = data.terms;
				alert("添加成功！" + terms[1].name);

			} else {
				alert("添加失败！" + data.terms[0].name + "-" + data.success + "-"
						+ (data.success == "true"));
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("网络连接出错！");
			alert("XMLHttpRequest.status:" + XMLHttpRequest.status);
			alert("XMLHttpRequest.readyState:" + XMLHttpRequest.readyState);
			alert("textStatus:" + textStatus);
		}
	});
}