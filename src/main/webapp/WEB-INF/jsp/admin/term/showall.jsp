<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>TermAdmin</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/templatemo_main.css">
<!-- <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet"> -->
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
								<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">新建计划</button>
							</div>

							<div class="table-responsive">
								<h4 class="margin-bottom-15">培训总体信息</h4>
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th>#id</th>
											<th>Term Name</th>
											<th>Discription</th>
											<th>其他二</th>
											<th>其他三</th>
											<th>进度</th>
											<th>分组管理</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${terms}" var="term">
											<tr class="danger">
												<td>${term["id"]}</td>
												<td>${term["name"]}</td>
												<td>${term["description"]}</td>
												<td>me@company.com</td>
												<td><a href="#" class="btn btn-default">Edit</a></td>
												<td><a href="#" class="btn btn-link">Delete</a></td>
												<td><a href="连接到分组管理的页面aaa${term['id']}" class="btn btn-default">分组管理</a></td>
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

			<!-- 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">新建培训计划</h4>
						</div>
						<div class="modal-body">在这里添加一些文本</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary">提交更改</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>

			<!-- 页脚 -->
			<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
		</div>
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<%-- <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> --%>
		<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
</body>
</html>