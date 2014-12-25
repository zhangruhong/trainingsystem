function addCourseToTerm() {
//	 var mydata = $("#courseaddform").form2json();
//	var mydata = {
//		'name' : 'hibernate',
//		'starttime' : '2014-12-25 10:00',
//		'endtime' : '2014-12-25 12:00',
//	};
	var mydata = '{"name":"' + $("#nameInput").val() 
				+ '","trainerloginname":"' + $('#trainerInput').val()  
				+ '","starttime":"' + $('#starttimeInput').val() 
				+ '","endtime":"' + $('#endtimeInput').val() 
				+ '","location":"' + $('#localInput').val() 
				+ '","description":"' + $('#descriptionInput').val() 
				+ '","content":"' + $('#contentInput').val() 
				+ '","goal":"' + $('#goalInput').val() +'"}';
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
//	return obj;
}

$(function() {
	jQuery('#starttimeInput').datetimepicker({
		step : 10
	});
	jQuery('#endtimeInput').datetimepicker({
		step : 10
	});
});
