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
							<h3 class="margin-bottom-15">User Information</h3>
							<div class="pull-right" id="templatemo_sort_btn">
								<button class="btn btn-primary  " data-toggle="modal" data-target="#addtraineemodel">Create Trainee</button>
								<button class="btn btn-primary  " data-toggle="modal" data-target="#addtrainermodel">Create Trainer</button>
							</div>
							<div class="btn-group pull-left">
								<form class="navbar-form" style="padding-left: 0px" action="/trainingsystem/admin/user/show">
									<input type="text" class="form-control" id="sec_box_admin" placeholder="Search...by name" name="searchKey" /> <input type="submit"
										value="search" id="sec_btn_admin" class="btn btn-default" />
								</form>
							</div>
							<div class="table-responsive">

								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th>Role</th>
											<th>name</th>
											<th>email</th>
											<th>phoneno</th>
											<th>Manage</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pageResult.rows}" var="user" varStatus="status">
											<tr>
												<td>${user["role"]==1?"Trainer":"Trainee"}</td>
												<td>${user["loginname"]}</td>
												<td>${user["email"]}</td>
												<td>${user["phoneno"]}</td>
												<td>
													<button class="btn btn-primary btn-default" data-toggle="modal" data-target="#updateCourse" onclick="loadCourse(${term['id']})">Edit</button>
													<button class="btn btn-warning btn-default" data-toggle="modal" data-target="#deleteconfirm" onclick="setdelvalue(${term['id']})">Lock</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- page begin  -->
							<span style="font-weight: 700; float: left; margin-top: 30px; color: #0071e4;">All(${pageResult.totalRows})</span>
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
								<h4 class="modal-title" id="myModalLabel">Create Trainee</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> EName </label>
									<div class="col-sm-10">
										<input class="form-control" id="nameInput_trainee" name="name" type="text" placeholder="Input Trainee's English Name">
									</div>
								</div>

								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">TelPhone</label>
									<div class="col-sm-10">
										<input class="form-control" id="phoneInput_trainee" name="phone" type="text" placeholder="Input Trainee's Cell Phone No.">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> email </label>
									<div class="col-sm-10">
										<input class="form-control" id="emailInput_trainee" name="email" type="text" placeholder="Input Trainee's email ">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								<button id="addterm" type="button" class="btn btn-primary" onclick="addtranee()" data-dismiss="modal">Submit</button>
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
								<h4 class="modal-title" id="myModalLabel">Create Trainer</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Ename</label>
									<div class="col-sm-10">
										<input class="form-control" id="nameInput_trainer" name="name" type="text" placeholder="Input Trainer's English Name">
									</div>
								</div>

								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">TelPhone</label>
									<div class="col-sm-10">
										<input class="form-control" id="phoneInput_trainer" name="phone" type="text" placeholder="Input Trainer's Cell Phone No.">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> Email </label>
									<div class="col-sm-10">
										<input class="form-control" id="emailInput_trainer" name="email" type="text" placeholder="Input Trainer's email">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								<button id="addterm_trainer" type="button" class="btn btn-primary" onclick="addtraner()" data-dismiss="modal">Submit</button>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/validator/js/bootstrapValidator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/personValidate.js"></script>
</body>
</html>