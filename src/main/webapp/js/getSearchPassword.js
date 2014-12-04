function search() {
	var searchStr = $("#searchKeyWord").val();
	if ("search name&url" == searchStr)
		searchStr = "";
	$.ajax({
		type : "POST",
		url : "search",
		data : "searchKeyStr=" + searchStr,
		dataType : "text", // ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
		success : function(data) {
			$("#infotable_div").html(data);

			$("td.table_name").bind("click", infoDetails);
			$("td.table_name").mouseenter(function() {
				$(this.parentElement).css({
					"background-color" : "#B6C1D4",
					"cursor" : "Pointer"
				});
			});
			$("td.table_name").mouseleave(function() {
				$(this.parentElement).css({
					"background-color" : "white",
					"cursor" : "auto"
				});
			});

			$("input.table_input").mouseenter(function() {
				$(this.parentElement.parentElement).css({
					"background-color" : "#B6C1D4",
					"cursor" : "Pointer"
				});
			});
			$("input.table_input").mouseleave(function() {
				$(this.parentElement.parentElement).css({
					"background-color" : "white",
					"cursor" : "auto"
				});
			});

			// copy to clipboard
			table_copyToClipboard();
			// handle the str of url and info in the table
			handleStrInListTable();

			handleCheckboxLisener();
			$("#infoDetails").hide();
		},
		error : function(json) {
			alert("error=" + json);
			return false;
		}
	});
}

// 网页加载完毕后，调用search方法
$(search());

function handleCheckboxLisener() {
	// checkbox click lisener
	$(".table_input").each(function() {
		$(this).click(function() {
			var isAllSelected = true;
			$(".table_input").each(function() {
				if (!$(this).attr("checked"))
					isAllSelected = false;
			});
			if (isAllSelected == true) {
				$("#isSelectAll").text("notselect");
			} else
				$("#isSelectAll").text("selectAll");
		});
	});
	// select td lisener
	$("#isSelectAll").bind("click", function() {
		var text = $(this).text();
		if (text == "notselect") {
			$(".table_input").each(function() {
				$(this).attr("checked", false);
			});
			$(this).text("selectAll");
		} else {
			$(".table_input").each(function() {
				$(this).attr("checked", true);
			});
			$(this).text("notselect");
		}
	});
}
