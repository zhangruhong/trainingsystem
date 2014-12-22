function addTerm() {
	var mydata = '{"name":"' + $("#nameInput").val() + '","description":"'
			+ $('#descriptionInput').val() + '"}';
	$
			.ajax({
				type : "POST",
				url : "add",
				data : mydata,
				dataType : "json",
				contentType : "application/json; charset=utf8",
				success : function(data) {
					data = data.termmap;
					var terms = data.terms;
					if (data.success == true) {
						createShowingTable(terms);
						$("#actiontip")
								.html(
										"<div class='alert alert-success alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>数据添加成功！</div>");
					} else {
						$("#actiontip")
								.html(
										"<div class='alert alert-warning alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>"
												+ data.msg
												+ '<br/>'
												+ terms.name
												+ '<br/>'
												+ terms.description + "</div>");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$("#actiontip")
							.html(
									"<div class='alert alert-danger alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>网络或兼容性错误！添加失败！请练习davisz@synnex.com。</div>");
				}
			});
}
function createShowingTable(data) {
	// 此处需要让其动态的生成一个table并填充数据
	var tableStr = "";
	$
			.each(
					data,
					function(n, term) {
						var termstring = "<tr><td>"
								+ term.id
								+ "</td>"
								+ "<td>"
								+ term.name
								+ "</td>"
								+ "<td>"
								+ term.description
								+ "</td>"
								+ "<td>"
								+ '功能待定'
								+ "</td>"
								+ "<td><a href='"
								+ '#连接到编辑页面（暂不支持）'
								+ " class='btn btn-default'>"
								+ 'Edit'
								+ "</a></td>"
								+ "<td><a href='"
								+ '#连接到删除页面'
								+ " class='btn btn-link'>"
								+ 'Delete'
								+ "</a></td>"
								+ "<td><a href='"
								+ "href111"
								+ " class='btn btn-default'>"
								+ '组员管理'
								+ "</a></td>"
								+ "<td>"
								+
								// <!-- Split button -->
								"<div class='btn-group'>"
								+ "<button type='button' class='btn btn-info'>Action</button>"
								+ "<button type='button' class='btn btn-info dropdown-toggle' data-toggle='dropdown'>"
								+ "<span class='caret'></span> <span class='sr-only'>Toggle Dropdown</span> </button>"
								+ "<ul class='dropdown-menu' role='menu'>"
								+ "<li><a href='" + '#功能待定'
								+ "'>Bootstrap</a></li>'" + "<li><a href='"
								+ '#功能待定' + "'>Bootstrap</a></li>'"
								+ "<li><a onclick='deleteUserGroup(" + term.id
								+ ")'>删除/解散该组</a></li>'" + "</ul> </div> </td>"
								+ "</tr>";
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
						$("#actiontip")
								.html(
										"<div class='alert alert-success alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>"
												+ data.msg + "</div>");
					} else {
						$("#actiontip")
								.html(
										"<div class='alert alert-warning alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>"
												+ data.msg + "</div>");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$("#actiontip")
							.html(
									"<div class='alert alert-danger alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>网络或兼容性错误！删除失败！请练习davisz@synnex.com。</div>");
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
		type : "POST",
		url : groupidvalue + "/add",
		data : loginname,
		dataType : "json",
		contentType : "application/json; charset=utf8",
		success : function(data) {
			data = data.termmap;
			var terms = data.terms;
			if (data.success == true) {
				alert("成功！" + data.msg);
				loadUsersOfGroup();
				// createShowingTable(terms);
				// $("#actiontip").html(
				// "<div class='alert alert-success alert-dismissable'> <button
				// type='button' class='close' data-dismiss='alert'
				// aria-hidden='true'>&times;</button>"
				// + data.msg + "</div>");
			} else {
				// $("#actiontip").html(
				// "<div class='alert alert-warning alert-dismissable'> <button
				// type='button' class='close' data-dismiss='alert'
				// aria-hidden='true'>&times;</button>"
				// + data.msg + "</div>");
				alert("失败" + data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// $("#actiontip")
			// .html(
			// "<div class='alert alert-danger alert-dismissable'> <button
			// type='button' class='close' data-dismiss='alert'
			// aria-hidden='true'>&times;</button>网络或兼容性错误！删除失败！请练习davisz@synnex.com。</div>");
			alert("错误");
		}
	});
}

function loadUsersOfGroup() {
	var groupidvalue = $("#groupidvalue").text();
	var url = groupidvalue + "/users";
	$.getJSON(url,function(data,status,xhr){
		if(status=="success"){
			data=data.termmap
			if(data.success==true){
				data=data.terms;
				var trvar = $("<tr></tr>");
				$.each(data,function(index,term){
					var tdvar = $("<td></td>");
					var tdvar1 = tdvar.clone().text(term.id);
					var tdvar2 = tdvar.clone().text(term.loginname);
					var tdvar3 = tdvar.clone().text(term.email);
					var tdvar4 = tdvar.clone().text(term.phoneno);
					tdvar = tdvar.append(tdvar1, tdvar2, tdvar3,tdvar4);
					trvar = trvar.append(tdvar);
				})
				$("#show_users_of_group").html(trvar);
			}else{
				alert("没有获取到记录"+data.msg)
			}
		}else{
			//标识网络连接状态没有成功
			alert("status:"+status+"data:"+data);
		}
		
	})
		
		
		
	
}