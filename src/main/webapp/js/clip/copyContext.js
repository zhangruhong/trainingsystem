$(copyToClipboard);
function copyToClipboard() {
	ZeroClipboard.config({
		moviePath : "js/clip/ZeroClipboard.swf",
		forceHandCursor : true
	});
	// init
	var clip_username = new ZeroClipboard($("#username"));
	clip_username.on('complete', function(client, args) {
		alert("Copied text to clipboard: " + args.text);
	});
	var clip_pw = new ZeroClipboard($("#password"));
	clip_pw.on('complete', function(client, args) {
		alert("Copied text to clipboard: " + args.text);
	});
}

function table_copyToClipboard() {
	ZeroClipboard.config({
		moviePath : "js/clip/ZeroClipboard.swf"
	});
	// init
	var clip_username = new ZeroClipboard($("td.table_username"));
	clip_username.on('complete', function(client, args) {
		alert("Copied text to clipboard: " + args.text);
	});
	var clip_pwd = new ZeroClipboard($(".table_pwd"));
	clip_pwd.on('complete', function(client, args) {
		alert("Copied text to clipboard: " + args.text);
	});
}
