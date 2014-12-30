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