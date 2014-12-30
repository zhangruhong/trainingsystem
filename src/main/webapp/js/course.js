function addCourseToTerm() {
	var mydata = '{"name":"' + $("#nameInput").val() + '","starttime":"' + $('#starttimeInput').val() + '","endtime":"' + $('#endtimeInput').val()
			+ '","location":"' + $('#localInput').val() + '","content":"' + $('#contentInput').val() + '","goal":"' + $('#goalInput').val()
			+ '","trainer":{"loginname":"' + $('#trainerInput').val() + '"},"dictionary":{"id":"' + $('#dictionaries option:selected').val() + '"}}';
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
};
function createShowingTable(data) {
	// 此处需要让其动态的生成一个table并填充数据
	location.reload(true);
};

function addDictionary() {
	var mydata = '{"name":"' + $("#dictionnarynameInput").val() + '"}';
	$
			.ajax({
				type : "POST",
				url : "/trainingsystem/admin/dictionary/add",
				data : mydata,
				dataType : "json",
				contentType : "application/json; charset=utf8",
				success : function(data) {
					data = data.termmap;
					if (data.success == true) {
						$("#actiontip")
								.html(
										"<div class='alert alert-success alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>数据添加成功！</div>");
						location.reload(true);

					} else {
						$("#actiontip")
								.html(
										"<div class='alert alert-warning alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>错误！添加失败！请联系davisz@synnex.com。</div>");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$("#actiontip")
							.html(
									"<div class='alert alert-danger alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>网络或兼容性错误！添加失败！请练习davisz@synnex.com。</div>");
					alert("-" + XMLHttpRequest.status + "-" + XMLHttpRequest.readyState + "-" + textStatus + "-" + errorThrown);
				}
			});
}

function loadCourse(id) {
	var url = "get?id=" + id;
	$.getJSON(url, function(data, status, xhr) {
		if (status == "success") {
			data = data.termmap
			if (data.success == true) {
				data = data.terms;
				$("#dictionaries_update").get(0).selectedIndex = data["dictionary"]["id"];
				$("#idInput_update").val(data["id"]);
				$("#nameInput_update").val(data["name"]);
				$("#trainerInput_update").val(data["trainer"]["loginname"]);
				$("#starttimeInput_update").val(data["starttime"]);
				$("#endtimeInput_update").val(data["endtime"]);
				$("#localInput_update").val(data["location"]);
				$("#contentInput_update").val(data["content"]);
				$("#goalInput_update").val(data["goal"]);
			} else {
				// alert("没有获取到记录" + data.msg)
				// 此处应该清空
				alert("拉取已有信息失败")
			}
		} else {
			// 标识网络连接状态没有成功
			alert("status:" + status + "data:" + data);
		}

	})
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