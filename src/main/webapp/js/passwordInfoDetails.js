//详细密码信息展示
function infoDetails() {
	// 显示详细信息div，添加和修改div隐藏
	$("#infoDetails").show();
	$("#table_infoDetails").show();
	$("#controlInfoDetails").hide();

	// 删除和修改按钮可用
	useDelbtn();
	useModifybtn();

	$.ajax({
		type : "GET",
		url : "infoDetails",
		data : "id=" + this.parentElement.id,
		dataType : "json",
		success : function(data) {
			var object = data.name;
			$("#name").text(object.name);
			$("#username").text(object.username);
			$("#password").text(object.password);
			$("#url").text(object.url);
			$("#url").attr("href", object.url);
			$("#info").text(object.info);
			$("#infoId").text(object.id);
		},
		error : function(data) {
			alert("something is wrong");
		}
	});
}

// 添加密码
function createPasswordInfo() {
	// 详细信息div隐藏，添加模块显示，提交按钮绑定添加事件
	$("#infoDetails").show();
	$("#table_infoDetails").hide();
	$("#controlInfoDetails").show();
	$("div#controlInfoDetails :text").val("");
	$("#infoId").text("");
	$("#control_submit").unbind();
	$("#control_submit").bind("click", create_sumbit);
	// 删除和修改按钮不能用
	notUseDelbtn();
	notUseModifybtn();
}
function create_sumbit(e) {
	var isNull = isAllNull();
	if (isNull == false) {
		var params = {
			"name" : $("#txt_name").val(),
			"username" : $("#txt_username").val(),
			"password" : $("#txt_password").val(),
			"url" : $("#txt_url").val(),
			"info" : $("#txt_info").val()
		};
		$.ajax({
			type : "POST",
			url : "createPasswordInfo",
			data : params,
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function() {
				$("#infoDetails").hide();
				search();
			},
			error : function() {
				alert("error");
			}
		});
	}
}

// 删除
function deletePasswordInfo() {
	var isDelete = window.confirm("Are you sure to delete?");
	if (isDelete) {
		var params = {
			id : $("#infoId").text()
		};
		$.ajax({
			type : "POST",
			url : "deletePasswordInfo",
			data : params,
			dataType : "json",
			success : function() {
				$("#btnDelete").unbind("click");
				$("#infoDetails").hide();
				search();
			},
			error : function() {
				alert("Error!");
			}
		});
	}
}
function deleteSelect() {
	var isDelete = window.confirm("Are you sure to delete the selected items?");
	if (isDelete) {
		var ids = new Array();
		var i = 0;
		$(".table_input").each(function() {
			if ($(this).attr("checked")) {
				ids[i] = Number($(this).val());
				i++;
			}
		});
		var params = {
			selectIds : ids
		};
		$.ajax({
			type : "POST",
			url : "deleteSelectPasswordInfos",
			data : params,
			dataType : "json",
			traditional : true,
			success : function() {
				search();
			},
			error : function() {
				alert("Error!");
			}
		});
	}
}

// 修改
function modifyPasswordInfo() {
	$("#table_infoDetails").hide();
	$("#controlInfoDetails").show();

	$("#txt_name").val($("#name").text());
	$("#txt_username").val($("#username").text());
	$("#txt_password").val($("#password").text());
	$("#txt_url").val($("#url").text());
	$("#txt_info").val($("#info").text());

	$("#control_submit").unbind();
	$("#control_submit").bind("click", modify_sumbit);

}
function modify_sumbit() {
	var isNull = isAllNull();
	if (isNull == false) {
		var id = Number($("#infoId").text());
		var params = {
			"id" : id,
			"name" : $("#txt_name").val(),
			"username" : $("#txt_username").val(),
			"password" : $("#txt_password").val(),
			"url" : $("#txt_url").val(),
			"info" : $("#txt_info").val()
		};
		$.ajax({
			type : "POST",
			url : "modifyPasswordInfo",
			data : params,
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function() {
				$("#infoDetails").hide();
				search();
			},
			error : function() {
				alert("error");
			}
		});
	}
}

function cancel() {
	$("#infoDetails").hide();
}

/**
 * the btn of delete can use
 */
function useDelbtn() {
	$("#btnDelete").bind("click", deletePasswordInfo);
	$("#btnDelete").css({
		"text-decoration" : "underline",
		"cursor" : "auto",
		"color" : "#346086"
	});
}
/**
 * the btn of delete cannot use
 */
function notUseDelbtn() {
	$("#btnDelete").unbind("click");
	$("#btnDelete").css({
		"text-decoration" : "none",
		"cursor" : "default",
		"color" : "#7D838E"
	});
}
/**
 * the btn of modify can use
 */
function useModifybtn() {
	$("#btnModify").bind("click", modifyPasswordInfo);
	$("#btnModify").css({
		"text-decoration" : "underline",
		"cursor" : "auto",
		"color" : "#346086"
	});
}
/**
 * the btn of delete cannot use
 */
function notUseModifybtn() {
	$("#btnModify").unbind("click");
	$("#btnModify").css({
		"text-decoration" : "none",
		"cursor" : "default",
		"color" : "#7D838E"
	});
}
