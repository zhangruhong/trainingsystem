function isNull(e) {
	var str = $.trim($(e).val());
	var className = $(e).attr("class");
	if (str == "") {
		$("label." + className).text("not null");
	}
}

function isAllNull() {
	var texts = $("div#controlInfoDetails :text");
	var result = false;
	for ( var i = 0; i < texts.length; i++) {
		var str = texts[i];
		if ($.trim($(str).val()) == "") {
			$("label." + $(str).attr("class")).text("not null");
			result = true;
		}
	}
	return result;
}

/**
 * made the length of url less than 20, info less than 10.
 */
function handleStrInListTable() {
	$("a.table_url").each(function() {
		var str = $(this).text();
		if(str.length > 20)
			str = str.substr(0,20) + "...";
		$(this).text(str);
	});
	$("td.table_info").each(function() {
		var str = $(this).text();
		if(str.length > 10)
			str = str.substr(0,10) + "...";
		$(this).text(str);
	});
}
