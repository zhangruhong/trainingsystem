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
	
		<div class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<div class="logo">
					<h1>Dashboard - Admin Template</h1>
				</div>
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
			</div>
		</div>
		<div class="template-page-wrapper">
			<div class="navbar-collapse collapse templatemo-sidebar">
				<ul class="templatemo-sidebar-menu">
					<li>
						<form class="navbar-form">
							<input type="text" class="form-control" id="templatemo_search_box" placeholder="Search..."> <span class="btn btn-default">Go</span>
						</form>
					</li>
					<li><a href="index.html"><i class="fa fa-home"></i>Dashboard</a></li>
					<li class="sub"><a href="javascript:;"> <i class="fa fa-database"></i> Nested Menu
							<div class="pull-right">
								<span class="caret"></span>
							</div>
					</a>
						<ul class="templatemo-submenu">
							<li><a href="#">Aenean</a></li>
							<li><a href="#">Pellentesque</a></li>
							<li><a href="#">Congue</a></li>
							<li><a href="#">Interdum</a></li>
							<li><a href="#">Facilisi</a></li>
						</ul></li>
					<li><a href="data-visualization.html"><i class="fa fa-cubes"></i><span class="badge pull-right">9</span>Data Visualization</a></li>
					<li><a href="maps.html"><i class="fa fa-map-marker"></i><span class="badge pull-right">42</span>Maps</a></li>
					<li class="active"><a href="#"><i class="fa fa-users"></i><span class="badge pull-right">NEW</span>Manage Users</a></li>
					<li><a href="preferences.html"><i class="fa fa-cog"></i>Preferences</a></li>
					<li><a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>Sign Out</a></li>
				</ul>
			</div>
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
											<th>已完成状态</th>
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
												<td>等待增加状态</td>
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

			<footer class="templatemo-footer">
				<div class="templatemo-copyright">
					<p>
						Copyright &copy; 2084 Your Company Name Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a> - More Templates <a
							href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a>
					</p>
				</div>
			</footer>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
</body>
</html>