<div class="navbar navbar-inverse" role="navigation">
	<div class="navbar-header">
		<div class="logo">
			<h1>Synnex Training System</h1>
		</div>
		<div class="userInfo">
			<img src="${pageContext.request.contextPath}/css/images/user_head.jpg"/>
			${sessionScope.USER_IN_SESSION.loginname}&emsp;
			<a href="#updatePasswordModal" data-toggle="modal" >
				<i class="fa fa-cog"></i>
				update password
			</a>
			<a href="#logoutModal" data-toggle="modal">
				<i class="fa fa-sign-out"></i>
				Sign Out
			</a>
		</div>
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
	</div>
</div>