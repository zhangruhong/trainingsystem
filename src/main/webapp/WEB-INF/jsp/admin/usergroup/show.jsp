<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>UsergroupAdmin</title>
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

					<div class="row">
						<div class="col-md-12">
							<div class="btn-group pull-right" id="templatemo_sort_btn">
								<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">新建分组</button>
							</div>

							<div class="table-responsive">
								<h4 class="margin-bottom-15">第1期英语培训分组情况</h4>
								<table class="table table-striped table-hover table-bordered ">
									<div id="actiontip" align="center"></div>
									<thead>
										<tr>
											<th>#id</th>
											<th>Group Name</th>
											<th>Discription</th>
											<th>其他二</th>
											<th>其他三</th>
											<th>其他四</th>
											<th>组员管理</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody id="tbodyusergroup">
										<c:forEach items="${usergroups}" var="term">
											<tr>
												<td>${term["id"]}</td>
												<td>${term["name"]}</td>
												<td>${term["description"]}</td>
												<td>me@company.com</td>
												<td><a href="#" class="btn btn-default">Edit</a></td>
												<td><a href="#" class="btn btn-link">Delete</a></td>
												<td><button class="btn btn-primary btn-default" data-toggle="modal" data-target="#member_Manage_Modal">组员管理</button></td>
												<%-- <a href="连接到分组管理的页面aaa${term['id']}" class="btn btn-default">组员管理</a> --%>
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
															<li><a onclick="deleteUserGroup(${term['id']})">删除/解散该组</a></li>
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
								<c:forEach begin="1" end="${totolpages}" var="iterm">
									<li><a href="show?page=${iterm}">${iterm}<span class="sr-only">(current)</span></a></li>
								</c:forEach>
								<!-- <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li> -->
								<li><a href="showall?page=${totolpages}">&raquo;</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!--新建 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<form class="form-horizontal" role="form">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">第一届英语培训——新建分组</h4>
							</div>
							<div class="modal-body">

								<div class="form-group">
									<label for="inputPassword" class="col-sm-2 control-label"> 分组名称 </label>
									<div class="col-sm-10">
										<input class="form-control" id="nameInput" name="name" type="text" placeholder="请输入分组名称  如：C组、MyCIS组">
									</div>
								</div>
								<div class="form-group">
									<label for="disabledTextInput" class="col-sm-2 control-label"> 培训简介 </label>
									<div class="col-sm-10">
										<input type="text" id="descriptionInput" name="description" class="form-control" placeholder="请输入分组简介/简单描述">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button id="addterm" type="button" class="btn btn-primary" onclick="addTerm()" data-dismiss="modal">提交</button>
							</div>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>

			<!--组员管理  模态框（Modal） -->
			<div class="modal fade" id="member_Manage_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				data-backdrop="static">
				<div class="modal-dialog">
					<form class="form-horizontal" role="form">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">第一届英语培训——xxx组员管理</h4>
							</div>
							<div class="modal-body">
								<table class="form-inline" role="form">
									<div class="input-group">
										 <input type="text" class="form-control" id="name" placeholder="请输入英文名"> <span
											class="input-group-btn">
											<button type="submit" class="btn btn-primary">添加组员</button>
										</span>
									</div>
								</table>
								<table class="table table-striped table-hover">
									<thead>
										<tr>
											<th>#id</th>
											<th>loginname</th>
											<th>phoneno</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Tanmay</td>
											<td>Bangalore</td>
											<td>560001</td>
											<td><button type="button" class="btn btn-danger btn-sm">移除</button></td>
										</tr>
										<tr>
											<td>Sachin</td>
											<td>Mumbai</td>
											<td>400003</td>
											<td><button type="button" class="btn btn-danger btn-sm">移除</button></td>
										</tr>
										<tr>
											<td>Uma</td>
											<td>Pune</td>
											<td>411027</td>
											<td><button type="button" class="btn btn-danger btn-sm">移除</button></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</div>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>

			<!-- 页脚 -->
			<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
		</div>
		<script src="http://libs.useso.com/js/jquery/2.0.3/jquery.min.js"></script>
		<script src="http://libs.useso.com/js/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
		<script src="${pageContext.request.contextPath}/js/usergroup.js"></script>
</body>
</html>