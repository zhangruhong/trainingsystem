<div class="navbar navbar-inverse" role="navigation">
	<div class="navbar-header">
		<div class="logo">
			<h1>Training System</h1>
		</div>
		<div class="userInfo">
			<img src="${pageContext.request.contextPath}/css/images/user_head.jpg"/>
			${sessionScope.USER_IN_SESSION.loginname}
			<a href="#updatePasswordModal" data-toggle="modal" >update password</a>
		</div>
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
	</div>
</div>