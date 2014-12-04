function handleCheckbox() {
	var checkboxes = $("#table_infolist :checkbox");
	var allCount = checkboxes.length;
	var hasChecked = false;
	for ( var i = 0; i < allCount; i++) {
		var checkbox = checkboxes[i];
		if ($(checkbox).attr("checked")) {
			$("#isSelectAll > a").text("deselect");
			hasChecked = true;
			break;
		}
	}
	if (!hasChecked) {
		$("#isSelectAll > a").text("select-all");
	}

}

function selectCheckbox() {
	var checkboxes = $("#table_infolist :checkbox");
	var isSelectAll = true;
	var title = $("#isSelectAll > a").text();
	if (title == "select-all") {
		selectType(true, checkboxes);
		$("#isSelectAll > a").text("deselect");
	} else {
		selectType(false, checkboxes);
	}
	handleCheckbox();
}

function selectType(isSelectAll, checkboxes) {
	for ( var i = 0; i < checkboxes.length; i++) {
		var checkbox = checkboxes[i];
		var isSelect = $(checkbox).attr("checked");
		if (isSelectAll) {
			$(checkbox).attr("checked", true);
		} else {
			$(checkbox).attr("checked", !isSelect);
		}
	}
}