function addTerm() {
	var mydata = '{"name":"' + $("#nameInput").val() + '","description":"' + $('#descriptionInput').val() + '"}';
	$
			.ajax({
				type : "POST",
				url : "add",
				data : mydata,
				dataType : "json",
				contentType : "application/json; charset=utf8",
				success : function(data) {
					data = data.termmap;
					if (data.success == true) {
						var terms = data.terms;
						createShowingTable(terms);
						$("#actiontip")
								.html(
										"<div class='alert alert-success alert-dismissable'> <button type='button' class='close' data-dismiss='alert'  aria-hidden='true'>&times;</button>数据添加成功！</div>");

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
function createShowingTable(data) {
	// 此处需要让其动态的生成一个table并填充数据
	var tableStr = "";
	$.each(data, function(n, term) {
		var termstring = "<tr><td>" + term.id + "</td>" + "<td>" + term.name + "</td>" + "<td>" + term.description + "</td>" + "<td>" + '功能待定'
				+ "</td>" + "<td><a href='" + '#连接到编辑页面（暂不支持）' + " class='btn btn-default'>" + 'Edit' + "</a></td>" + "<td><a href='" + '#连接到删除页面'
				+ " class='btn btn-link'>" + 'Delete' + "</a></td>" + "<td><a href='" + term.id + "/usergroup/show' class='btn btn-default'>" + '分组管理'
				+ "</a></td>" + "<td>" +
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