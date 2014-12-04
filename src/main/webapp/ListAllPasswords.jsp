<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/getSearchPassword.js"
	charset="gb2312"></script>
<script type="text/javascript" src="js/passwordInfoDetails.js"
	charset="gb2312"></script>
<script type="text/javascript" src="js/checkboxSelect.js"
	charset="gb2312"></script>
<script type="text/javascript" src="js/clip/copyContext.js"
	charset="gb2312"></script>
<script type="text/javascript" src="js/clip/ZeroClipboard.js"
	charset="gb2312"></script>
</head>
<body>
	<div id="logo">
		<h1>
			<a href="#">Password Management System</a>
		</h1>
	</div>
	<div class="menu">
		<ul>
			<li class="first"></li>
		</ul>
		<div class="search">
			<input id="searchKeyWord" type="text" value="search name&url"
				onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
				onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
				style="color: #999999" /> <input id="imageField" type="image"
				src="css/images/img10.jpg" onclick="search()" />
		</div>
	</div>
	<hr />
	<!-- start page -->
	<div class="page">
		<!-- content -->
		<h2>Passwords List</h2>
		<div class="left_content">
			<div class="post">
				<a href="#" id="btnCreate" onclick="createPasswordInfo()">Create</a>
				<a href="#" id="btnDeleteSelect" onclick="deleteSelect()">
					Delete</a>
				<div id="infotable_div"></div>
				<p class="meta"></p>
			</div>
			<!-- left -->
		</div>
		<div class="midarea">
			<%@include file="InfoDetails.jsp"%>
		</div>
	</div>
	<!-- end page -->
	<div class="footer">
		<p class="legal">Copyright (c) 2013 Password Management System.</p>
	</div>


</body>
</html>