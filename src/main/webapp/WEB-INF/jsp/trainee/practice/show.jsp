<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>Training System search practice</title>
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
			<jsp:include page="/WEB-INF/jsp/trainee/menu.jsp"></jsp:include>

			<div class="templatemo-content-wrapper">
				<div class="templatemo-content">
					<!--导航-->
					<ol class="breadcrumb">
						<li>Trainee Panel</li>
						<li class="active">Practice View</li>
					</ol>

					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<h3 class="margin-bottom-15">Practice View</h3>
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th width="15%">Term Name</th>
											<th width="15%">Course Name</th>
											<th>Practice Manage</th>
											<th>score</th>
											<th>score description</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pageResult.rows}" var="practice" varStatus="status">
											<tr>
												<td>${practice.course.term.name}</td>
												<td>${practice.course.name}</td>
												<td>
													<c:if test="${practice.status == 0}">
														<a href="${pageContext.request.contextPath}/trainee/practice/commit?id=${practice.course.id}" class="btn btn-primary">Write Practice</a>
													</c:if> 
													<c:if test="${practice.status == 1 || practice.status==2}">
														<a href="${pageContext.request.contextPath}/trainee/practice/commit?id=${practice.course.id}" class="btn btn-primary">Modify Practice</a>
													</c:if>
												</td>
												<td>
													<c:if test="${practice.score==0}">No Grade</c:if>
													<c:if test="${practice.score!=0}">${practice.score}</c:if>
												</td>
												<td>
													<c:if test="${practice.scoreDescription==null}">No Description</c:if>
													<c:if test="${practice.scoreDescription!=null}">${practice.score}</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script src="${pageContext.request.contextPath}/js/practice.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/validator/js/bootstrapValidator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/personValidate.js"></script>
</body>
</html>