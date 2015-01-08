<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>Trainer-practice</title>
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
			<jsp:include page="/WEB-INF/jsp/trainer/menu.jsp"></jsp:include>
			<!--/.navbar-collapse -->

			<div class="templatemo-content-wrapper">
				<div class="templatemo-content">

					<ol class="breadcrumb">
						<li>Trainer Panel</a></li>
						<li><a href="${pageContext.request.contextPath}/trainer/practice/view">Course Manage</a></li>
						<li class="active">Practice <c:if test="${course.practiseStatus==0}">Create</c:if>
							<c:if test="${course.practiseStatus==1}">Edit</c:if>
						</li>
					</ol>

					<div class="row">
						<div class="col-md-12">

							<div class="table-responsive">
								<h4 class="margin-bottom-15">${course.name} Course's Practice <c:if test="${course.practiseStatus==0}">Create</c:if>
							<c:if test="${course.practiseStatus==1}">Edit</c:if></h4>
							</div>
							<!-- 富文本编辑器 -->
							<div>
								<input type="hidden" id="courseId" value="${course.id}"/> 
								<input type="hidden" id="coursePractise" value='${course.practise}'/>
								<!-- 加载编辑器的容器 -->
								<script id="container" name="editcontent" type="text/plain" style="width: 900px; height: 450px;"></script>
								<br/>
								<a href="${pageContext.request.contextPath}/trainer/practice/view" class="btn btn-default" style="margin-left: 850px;">Back</a>
								<button id="uploadButton" type="button" class="btn btn-primary">Commit</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">You have arrange practice successfully</h4>
						</div>
						<div class="modal-footer">
							<a href="${pageContext.request.contextPath}/trainer/practice/view" class="btn btn-primary">confirm</a>
						</div>
					</div>
				</div>
			</div>

			<!-- 页脚 -->
			<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
		</div>
		<script src="http://libs.useso.com/js/jquery/2.0.3/jquery.min.js"></script>
		<script src="http://libs.useso.com/js/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
		<script src="${pageContext.request.contextPath}/js/practice.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/validator/js/bootstrapValidator.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/personValidate.js"></script>
	</div>
</body>
</html>