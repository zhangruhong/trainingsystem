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
				location.reload(true);
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
	$.ajax({
		type : "POST",
		url : "/trainingsystem/admin/dictionary/add",
		data : mydata,
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			if (data.success == true) {
				location.reload(true);
				$("#actiontip")
						.html(
								"<div class='alert alert-success alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>Success to add</div>");

			} else {
				$("#actiontip")
						.html(
								"<div class='alert alert-warning alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>error！failed to add！Pls. contact davisz@synnex.com。</div>");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#actiontip")
					.html(
							"<div class='alert alert-danger alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>error！failed to add！Pls. contact davisz@synnex.com。</div>");
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
				$("#dictionaries_update").val(data["dictionary"]["id"]);
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

function updateCourse() {
	var mydata = '{"name":"' + $("#nameInput_update").val() + '","id":"' + $('#idInput_update').val() + '","starttime":"'
			+ $('#starttimeInput_update').val() + '","endtime":"' + $('#endtimeInput_update').val() + '","location":"'
			+ $('#localInput_update').val() + '","content":"' + $('#contentInput_update').val() + '","goal":"' + $('#goalInput_update').val()
			+ '","trainer":{"loginname":"' + $('#trainerInput_update').val() + '"},"dictionary":{"id":"'
			+ $('#dictionaries_update option:selected').val() + '"}}';
	var url = $('#idInput_update').val() + "/update";
	$.ajax({
		type : "POST",
		url : url,
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
//删除课程操作
$("#yestodelete").click(function() {
	var deleteurl = $("#yestodelete").val();
	$.getJSON(deleteurl, function(data, status, xhr) {
		if (status == "success") {
			data = data.termmap
			if (data.success == true) {
				location.reload(true);
			}
		}
	})
});

//点击删除按钮 准备工作
function setdelvalue(id) {
	var urldelete= id + "/delete";
	$("#yestodelete").val(urldelete);
}
//course添加界面验证
$(function(){
	$('#courseaddform').bootstrapValidator({
		message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
            	validators: {
            		notEmpty: {
            			message: 'The course name is required and can\'t be empty'
            		}
            	}
            },	
            trainer: {
            	validators: {
            		notEmpty: {
            			message: 'The trainer is required and can\'t be empty'
            		}
            	}
            },	
            starttime: {
            	validators: {
            		notEmpty: {
            			message: 'The start time is required and can\'t be empty'
            		}
            	}
            },	
            endtime: {
            	validators: {
            		notEmpty: {
            			message: 'The end time is required and can\'t be empty'
            		}
            	}
            },	
            location: {
            	validators: {
            		notEmpty: {
            			message: 'The course location is required and can\'t be empty'
            		}
            	}
            },	
            content: {
            	validators: {
            		notEmpty: {
            			message: 'The course content is required and can\'t be empty'
            		}
            	}
            }
        }
	}).on('success.form.bv', function(e) {
		$('#myModal').modal('hide');
		updateCourse();
		return false;
    });	
	//dictionary添加界面验证
	$('#addDictionaryForm').bootstrapValidator({
		message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	name: {
                validators: {
                    notEmpty: {
                        message: 'The dictionary name is required and can\'t be empty'
                    }
                }
        	}
        }
	}).on('success.form.bv', function(e) {
		$('#addDictionary').modal('hide');
		addDictionary();
		return false;
    });
});