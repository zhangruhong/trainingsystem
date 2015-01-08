<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>TermAdmin</title>
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
			<!--/.navbar-collapse -->

			<div class="templatemo-content-wrapper">
				<div class="templatemo-content">

					<ol class="breadcrumb">
						<li><a href="index.html">Admin Panel</a></li>
						<li><a href="#">Manage Users</a></li>
						<li class="active">Tables</li>
					</ol>

					<div class="row">
						<div class="col-md-12" style="height: 578px">
							<div class="btn-group pull-right" id="templatemo_sort_btn">
								<button class="btn btn-primary  btn-default " data-toggle="modal" data-target="#myModal">Create Term</button>
							</div>

							<div class="table-responsive" style="height: 478px">
								<h4 class="margin-bottom-15">Term Information</h4>
								<table class="table table-striped table-hover table-bordered ">
								<div id="actiontip" align="center"></div>
									<thead>
										<tr>
											<th>Term Name</th>
											<th>Discription</th>
											<th>其他三</th>
											<th>课程管理</th>
											<th>分组管理</th>
										</tr>
									</thead>
 									<tbody id="tbodyterms">
										<c:forEach items="${pageResult.rows}" var="term">
											<tr>
												<td>${term["name"]}</td>
												<td>${term["description"]}</td>
												<td><a href="#" class="btn btn-default">Edit</a></td>
												<td><a href="${term['id']}/courses/showall" class="btn btn-primary btn-default">课程管理</a></td>
												<td><a href="${term['id']}/usergroup/show" class="btn btn-primary btn-default">分组管理</a></td>
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
										<li><a href="showall?page=1">&emsp;&nbsp;first&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage-1<=0}">
										<li class="disabled"><a href="#">Previous</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage-1>0}">
										<li><a href="showall?page=${pageResult.currentPage-1}">Previous</a></li>
									</c:if>
									<li><a href="" style="color: black;">&emsp;&nbsp;${pageResult.currentPage}/${pageResult.totalPages}&emsp;&nbsp;</a></li>
									<c:if test="${pageResult.currentPage+1>pageResult.totalPages}">
										<li class="disabled"><a href="#">&emsp;&nbsp;next&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage+1<=pageResult.totalPages}">
										<li><a href="showall?page=${pageResult.currentPage+1}">&emsp;&nbsp;next&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage+1>pageResult.totalPages}">
										<li class="disabled"><a href="#">&emsp;&nbsp;last&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage+1<=pageResult.totalPages}">
										<li><a href="showall?page=${pageResult.totalPages}">&emsp;&nbsp;last&emsp;&nbsp;</a></li>
									</c:if>
								</ul>
							</div>
							<!-- page end  -->
						</div>
					</div>
				</div>
			</div>
			<!-- 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<form id="createTermForm" class="form-horizontal" role="form" action="showall">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">新建培训计划</h4>
							</div>
							<div class="modal-body">

								<div class="form-group">
									<label for="inputPassword" class="col-sm-2 control-label"> 培训名称 </label>
									<div class="col-sm-10">
										<input class="form-control" id="nameInput" name="name" type="text" placeholder="请输入培训计划名称">
									</div>
								</div>
								<div class="form-group">
									<label for="disabledTextInput" class="col-sm-2 control-label"> 培训简介 </label>
									<div class="col-sm-10">
										<input type="text" id="descriptionInput" name="description" class="form-control" placeholder="请输入培训计划简介/简单描述">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button id="addterm" type="submit" class="btn btn-primary">提交</button>
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
	<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
	<script src="${pageContext.request.contextPath}/js/term.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/validator/js/bootstrapValidator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/personValidate.js"></script>
</body>
</html>