function addCourseToTerm() {
	var mydata = '{"name":"' + $("#nameInput").val() + '","starttime":"' + $('#starttimeInput').val() + '","endtime":"' + $('#endtimeInput').val()
			+ '","location":"' + $('#localInput').val() + '","dictionary1":"' + $('#descriptionInput').val() + '","content":"'
			+ $('#contentInput').val() + '","goal":"' + $('#goalInput').val() + '","trainer":{"loginname":"' + $('#trainerInput').val()
			+ '"},"dictionary":{"id":"' + $('#dictionaries option:selected').val() + '"}}';
	$.ajax({
		type : "POST",
		url : "add",
		data : mydata,
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			if (data.success == true) {
				var terms = data.terms;
			} else {
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert("-" + XMLHttpRequest.status + "-" + XMLHttpRequest.readyState + "-" + textStatus + "-" + errorThrown);
		}
	});
}
function createShowingTable(data) {
	// 此处需要让其动态的生成一个table并填充数据
	var tableStr = "";
	$.each(data, function(n, term) {
		var termstring = "<tr><td>" + term.id + "</td>" + "<td>" + term.name + "</td>" + "<td>" + term.description + "</td>" + "<td>" + '功能待定'
				+ "</td>" + "<td><a href='" + '#连接到编辑页面（暂不支持）' + " class='btn btn-default'>" + 'Edit' + "</a></td>" + "<td><a href='" + '#连接到删除页面'
				+ " class='btn btn-link'>" + 'Delete' + "</a></td>" + "<td><a href='" + term.id + "/usergroup/show' class='btn btn-default'>"
				+ '分组管理' + "</a></td>" + "<td>" +
				// <!-- Split button -->
				"<div class='btn-group'>" + "<button type='button' class='btn btn-info'>Action</button>"
				+ "<button type='button' class='btn btn-info dropdown-toggle' data-toggle='dropdown'>"
				+ "<span class='caret'></span> <span class='sr-only'>Toggle Dropdown</span> </button>" + "<ul class='dropdown-menu' role='menu'>"
				+ "<li><a href='" + '#功能待定' + "'>Bootstrap</a></li>'" + "<li><a href='" + '#功能待定' + "'>Bootstrap</a></li>'" + "<li><a href='"
				+ '#功能待定' + "'>Bootstrap</a></li>'" + "</ul> </div> </td>" + "</tr>";
		tableStr = tableStr + termstring;
	});
	// 将动态生成的table添加的事先隐藏的div中.
	$("#tbodyterms").html(tableStr);
}

$(function() {
	jQuery('#starttimeInput').datetimepicker({
		step : 10,
		format : 'Y-m-d H:i:s',
		formatTime : 'H:i',
		formatDate : 'Y-m-d'
	});
	jQuery('#endtimeInput').datetimepicker({
		step : 10,
		format : 'Y-m-d H:i:s',
		formatTime : 'H:i',
		formatDate : 'Y-m-d'
	});
});

Date.parseDate = function(input, format) {
	return moment(input, format).toDate();
};
Date.prototype.dateFormat = function(format) {
	return moment(this).format(format);
};
/*
 * 课程类别 写页面刷新代码 通过js更新数据
 */