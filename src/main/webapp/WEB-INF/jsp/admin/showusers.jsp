<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>Dashboard Tables, Free Admin Template</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/templatemo_main.css">
</head>

<body>
	<div id="main-wrapper">
	
		<!-- 导航 -->
		<jsp:include page="/WEB-INF/jsp/admin/navigate.jsp"></jsp:include>
		
		<div class="template-page-wrapper">
		
			<!-- 菜单 -->
			<jsp:include page="/WEB-INF/jsp/admin/menu.jsp"></jsp:include>
			
			<!--/.navbar-collapse -->

			<div class="templatemo-content-wrapper">
				<div class="templatemo-content">
					<ol class="breadcrumb">
						<li><a href="index.html">Admin Panel</a></li>
						<li><a href="#">Manage Users</a></li>
						<li class="active">Tables</li>
					</ol>
					<h1>Manage Users</h1>
					<p>Here goes tables and users.</p>

					<div class="row margin-bottom-30">
						<div class="col-md-12">
							<ul class="nav nav-pills">
								<li class="active"><a href="#">New Users <span class="badge">42</span></a></li>
								<li><a href="#">Active Users <span class="badge">107</span></a></li>
								<li><a href="#">Expired Users <span class="badge">3</span></a></li>
							</ul>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="btn-group pull-right" id="templatemo_sort_btn">
								<button type="button" class="btn btn-default">Sort by</button>
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
									<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">First Name</a></li>
									<li><a href="#">Last Name</a></li>
									<li><a href="#">Username</a></li>
								</ul>
							</div>

							<div class="table-responsive">
								<h4 class="margin-bottom-15">Another Table of Existing Users</h4>
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th>#id</th>
											<th>loginname</th>
											<th>email</th>
											<th>phoneno</th>
											<th>å·²å®æç¶æ</th>
											<th>Delete?</th>
											<th>Edit</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${users}" var="user" varStatus="status">
											<tr>
												<td>${user["id"]}</td>
												<td>${user["loginname"]}</td>
												<td>${user["email"]}</td>
												<td>${user["phoneno"]}</td>
												<td>等待优化</td>
												<td><a href="#" class="btn btn-link">Delete</a></td>
												<td><a href="#" class="btn btn-default">Edit</a></td>
												<td>
													<!-- Split button -->
													<div class="btn-group">
														<button type="button" class="btn btn-info">Action</button>
														<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
															<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
														</button>
														<ul class="dropdown-menu" role="menu">
															<li><a href="#">Bootstrap</a></li>
															<li><a href="#">Font Awesome</a></li>
															<li><a href="#">jQuery</a></li>
														</ul>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<ul class="pagination pull-right">
								<li class="disabled"><a href="#">&laquo;</a></li>
								<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
								<li><a href="#">2 <span class="sr-only">(current)</span></a></li>
								<li><a href="#">3 <span class="sr-only">(current)</span></a></li>
								<li><a href="#">4 <span class="sr-only">(current)</span></a></li>
								<li><a href="#">5 <span class="sr-only">(current)</span></a></li>
								<li><a href="#">&raquo;</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Are you sure you want to sign out?</h4>
						</div>
						<div class="modal-footer">
							<a href="sign-in.html" class="btn btn-primary">Yes</a>
							<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 页脚 -->
			<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
			
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
</body>
</html>