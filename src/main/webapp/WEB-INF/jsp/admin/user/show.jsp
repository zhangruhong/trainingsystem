<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<jsp:include page="/WEB-INF/jsp/common/navigate.jsp"></jsp:include>

		<div class="template-page-wrapper">

			<!-- 菜单 -->
			<jsp:include page="/WEB-INF/jsp/admin/menu.jsp"></jsp:include>

			<div class="templatemo-content-wrapper">
				<div class="templatemo-content">
					<!--导航-->
					<ol class="breadcrumb">
						<li><a href="index.html">Admin Panel</a></li>
						<li><a href="#">Manage Users</a></li>
						<li class="active">Tables</li>
					</ol>

					<div class="row">
						<div class="col-md-12">
							<div class="pull-right" id="templatemo_sort_btn">
								<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addtraineemodel">新建学员</button>
								<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addtrainermodel">新建讲师</button>
							</div>

							<div class="table-responsive">
								<h3 class="margin-bottom-15">学员信息</h3>
								<p>这里还须添加根据名字搜索user</p>
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th>角色</th>
											<th>loginname</th>
											<th>email</th>
											<th>phoneno</th>
											<th>管理</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pageResult.rows}" var="user" varStatus="status">
											<tr>
												<td>${user["role"]==1?"讲师":"学员"}</td>
												<td>${user["loginname"]}</td>
												<td>${user["email"]}</td>
												<td>${user["phoneno"]}</td>
												<td>
													<button class="btn btn-primary btn-default" data-toggle="modal" data-target="#updateCourse" onclick="loadCourse(${term['id']})">编辑</button>
													<button class="btn btn-primary btn-default" data-toggle="modal" data-target="#deleteconfirm" onclick="setdelvalue(${term['id']})">锁定</button>
												</td>
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
							<!-- page begin  -->
							<span style="font-weight: 700;float: left;margin-top: 30px;color: #0071e4;">All(${pageResult.totalRows})</span>
							<div style="float: right">
								<ul class="pagination">
									<c:if test="${pageResult.currentPage-1<=0}">
										<li class="disabled"><a href="#">&emsp;&nbsp;first&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage-1>0}">
										<li><a href="show?page=1">&emsp;&nbsp;first&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage-1<=0}">
										<li class="disabled"><a href="#">Previous</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage-1>0}">
										<li><a href="show?page=${pageResult.currentPage-1}">Previous</a></li>
									</c:if>
									<li><a href="" style="color: black;">&emsp;&nbsp;${pageResult.currentPage}/${pageResult.totalPages}&emsp;&nbsp;</a></li>
									<c:if test="${pageResult.currentPage+1>pageResult.totalPages}">
										<li class="disabled"><a href="#">&emsp;&nbsp;next&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage+1<=pageResult.totalPages}">
										<li><a href="show?page=${pageResult.currentPage+1}">&emsp;&nbsp;next&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage+1>pageResult.totalPages}">
										<li class="disabled"><a href="#">&emsp;&nbsp;last&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage+1<=pageResult.totalPages}">
										<li><a href="show?page=${pageResult.totalPages}">&emsp;&nbsp;last&emsp;&nbsp;</a></li>
									</c:if>
								</ul>
							</div>
							<!-- page end  -->
						</div>
					</div>
				</div>
			</div>

			<!--新建学员 模态框（Modal） -->
			<div class="modal fade" id="addtraineemodel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<form class="form-horizontal" role="form" id="courseaddform">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">新建学员</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 英文名 </label>
									<div class="col-sm-10">
										<input class="form-control" id="nameInput_trainee" name="name" type="text" placeholder="请输入英文名">
									</div>
								</div>

								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 手机 </label>
									<div class="col-sm-10">
										<input class="form-control" id="phoneInput_trainee" name="phone" type="text" placeholder="请输入手机 ">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> email </label>
									<div class="col-sm-10">
										<input class="form-control" id="emailInput_trainee" name="email" type="text" placeholder="请输入email ">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button id="addterm" type="button" class="btn btn-primary" onclick="addtranee()" data-dismiss="modal">提交</button>
							</div>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>


			<!--新建讲师 模态框（Modal） -->
			<div class="modal fade" id="addtrainermodel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<form class="form-horizontal" role="form" id="courseaddform">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">新建讲师</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 英文名 </label>
									<div class="col-sm-10">
										<input class="form-control" id="nameInput_trainer" name="name" type="text" placeholder="请输入英文名">
									</div>
								</div>

								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 手机 </label>
									<div class="col-sm-10">
										<input class="form-control" id="phoneInput_trainer" name="phone" type="text" placeholder="请输入手机 ">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> email </label>
									<div class="col-sm-10">
										<input class="form-control" id="emailInput_trainer" name="email" type="text" placeholder="请输入email ">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button id="addterm_trainer" type="button" class="btn btn-primary" onclick="addtraner()" data-dismiss="modal">提交</button>
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
	</div>

	<script src="http://libs.useso.com/js/jquery/2.0.3/jquery.min.js"></script>
	<script src="http://libs.useso.com/js/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<!-- <script src="http://apps.bdimg.com/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="http://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
	<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
	<script src="${pageContext.request.contextPath}/js/adminuser.js"></script>
</body>
</html>