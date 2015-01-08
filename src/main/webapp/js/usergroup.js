function addTerm() {
	var mydata = '{"name":"' + $("#nameInput").val() + '","description":"' + $('#descriptionInput').val() + '"}';
	$.ajax({
		type : "POST",
		url : "add",
		data : mydata,
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			var terms = data.terms;
			if (data.success == true) {
				//createShowingTable(terms);
				location.reload(true);
				$("#actiontip")
						.html(
								"<div class='alert alert-success alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>Success to add data!</div>");
			} else {
				$("#actiontip").html(
						"<div class='alert alert-warning alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>"
								+ data.msg + '<br/>' + terms.name + '<br/>' + terms.description + "</div>");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#actiontip")
					.html(
							"<div class='alert alert-danger alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>error！failed to add！Pls. contact davisz@synnex.com。</div>");
		}
	});
}
function createShowingTable(data) {
	// 此处需要让其动态的生成一个table并填充数据
	var tableStr = "";
	$.each(data, function(n, term) {
		var termstring = "<tr><td>" 
		+ term.name
		+ "</td>" 
		+ "<td>" + term.description + "</td>" + "<td>" 
		+ '<button class="btn btn-primary btn-default" data-toggle="modal" data-target="#member_Manage_Modal"  onclick="setgroupidvalue('+term.id+')">Member Management</button></td>'
		+'<td><a href="#" class="btn btn-default btn-danger" onclick="deleteUserGroup('+term.id+')">Delete</a></td>'
		+'</tr>';
		tableStr = tableStr + termstring;
	});
	// 将动态生成的table添加的事先隐藏的div中.
	$("#tbodyusergroup").html(tableStr);
}

function deleteUserGroup(groupid) {
	$
			.ajax({
				type : "GET",
				url : groupid + "/delete",
				dataType : "json",
				contentType : "application/json; charset=utf8",
				success : function(data) {
					data = data.termmap;
					var terms = data.terms;
					if (data.success == true) {
						createShowingTable(terms);
						$("#actiontip").html(
								"<div class='alert alert-success alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>"
										+ data.msg + "</div>");
					} else {
						$("#actiontip").html(
								"<div class='alert alert-warning alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>"
										+ data.msg + "</div>");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$("#actiontip")
							.html(
									"<div class='alert alert-danger alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>error！failed to add！Pls. contact davisz@synnex.com。</div>");
				}
			});
}

function setgroupidvalue(groupid) {
	$("#groupidvalue").text(groupid);
	loadUsersOfGroup();
}

function addUserToGroup() {
	var groupidvalue = $("#groupidvalue").text();
	var loginname = $("#addusertogroup_name").val();
	$.ajax({
		type : "GET",
		url : groupidvalue + "/add/"+loginname,
		data : "",
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			var terms = data.terms;
			if (data.success == true) {
				alert("Success！" + data.msg);
				loadUsersOfGroup();
			} else {
				alert("Failed" + data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Error!");
		}
	});
}

function loadUsersOfGroup() {
	var groupidvalue = $("#groupidvalue").text();
	var url = groupidvalue + "/users";
	$.getJSON(url, function(data, status, xhr) {
		if (status == "success") {
			data = data.termmap
			if (data.success == true) {
				data = data.terms;
				$("#show_users_of_group").html("");
				$.each(data, function(index, term) {
					var trvarmodel = $("<tr></tr>");
					var tdvar = $("<td></td>");
					var tdvar1 = tdvar.clone().text(term.loginname);
					var tdvar2 = tdvar.clone().text(term.email);
					var tdvar3 = tdvar.clone().text(term.phoneno);
					var tdvar4 = '<button type="button" class="btn btn-danger btn-sm" onclick="deleteUserfromGroup(' + groupidvalue + ',' + term.id
							+ ') ;return false">Remove</button>';
					trvarmodel.append(tdvar1, tdvar2, tdvar3, tdvar4)
					$("#show_users_of_group").append(trvarmodel);
				})
				
			} else {
//				alert("没有获取到记录" + data.msg)
				//此处应该清空
				$("#show_users_of_group").html ('<tr><td colspan="4" align="center">'+data.msg+'</td></tr>');
//				$("#show_users_of_group").html ("额额"+data.msg);
			}
		} else {
			// 标识网络连接状态没有成功
			alert("status:" + status + "data:" + data);
		}

	})
}

function deleteUserfromGroup(groupid, userid) {
	$.ajax({
		type : "GET",
		url : groupid + "/delete/"+userid,
		data : "",
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			var terms = data.terms;
			if (data.success == true) {
				alert("Remove Success！" + data.msg);
				loadUsersOfGroup();
			} else {
				alert("Remove Failed！" + data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Error");
		}
	});
}

/*******************************************************************************************************************************************
 * @serializedParams looks like "prop1=value1&prop2=value2". Nested property like 'prop.subprop=value' is also supported
 ******************************************************************************************************************************************/
function paramString2obj(serializedParams) {
	var obj = {};
	function evalThem(str) {
		var attributeName = str.split("=")[0];
		var attributeValue = str.split("=")[1];
		if (!attributeValue) {
			return;
		}

		var array = attributeName.split(".");
		for (var i = 1; i < array.length; i++) {
			var tmpArray = Array();
			tmpArray.push("obj");
			for (var j = 0; j < i; j++) {
				tmpArray.push(array[j]);
			}
			;
			var evalString = tmpArray.join(".");
			// alert(evalString);
			if (!eval(evalString)) {
				eval(evalString + "={};");
			}
		}
		;
		eval("obj." + attributeName + "='" + attributeValue + "';");

	}
	;
	var properties = serializedParams.split("&");
	for (var i = 0; i < properties.length; i++) {
		evalThem(properties[i]);
	}
	;
	return obj;
}
$.fn.form2json = function() {
	var serializedParams = this.serialize();
	var obj = paramString2obj(serializedParams);
	return JSON.stringify(obj);
}
//新建分组验证
$(function(){
	$('#createGroupForm').bootstrapValidator({
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
                        message: 'The term name is required and can\'t be empty'
                    }
                }
            }
        }
	}).on('success.form.bv', function(e) {
		$('#myModal').modal('hide');
		addTerm();
		return false;
    });
});