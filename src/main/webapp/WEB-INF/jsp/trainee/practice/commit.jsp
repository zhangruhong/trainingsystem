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
			<jsp:include page="/WEB-INF/jsp/trainee/menu.jsp"></jsp:include>
			<!--/.navbar-collapse -->

			<div class="templatemo-content-wrapper">
				<div class="templatemo-content">

					<ol class="breadcrumb">
						<li><a href="index.html">trainee Panel</a></li>
						<li><a href="${pageContext.request.contextPath}/trainee/term/view">Term View</a></li>
						<li>Course View</li>
						<li>Manage Practice</li>
						<li class="active">Write practice</li>
					</ol>

					<div class="row">
						<div class="col-md-12">
							<!-- 标签页 -->
							<ul id="myTab" class="nav nav-tabs">
								<li class="active"><a href="#seePractice" data-toggle="tab">${course.name} Course 练习查看 </a></li>
								<li><a href="#commitPractice" data-toggle="tab">${course.name} Course 练习提交 </a></li>
							</ul>

							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade in active" id="seePractice">
									<input type="hidden" id="courseId" value="${course.id}" /> 
									<input type="hidden" id="coursePractise" value='${course.practise}' />
									<script id="trainerContainer" name="editcontent" type="text/plain" style="width: 900px; height: 450px;"></script>
									<br>
									<button type="button" class="btn btn-default" style="margin-left: 940px;">Back</button>
								</div>
								<div class="tab-pane fade" id="commitPractice">
									<!-- 加载编辑器的容器 -->
									<input type="hidden" id="practiceId" value="${practice.id}" /> 
									<input type="hidden" id="practiceContent" value='${practice.content}' />
									<script id="traineeContainer" name="editcontent" type="text/plain" style="width: 900px; height: 450px;"></script>
									<br>
									<button id="commitButton" type="button" class="btn btn-primary" style="margin-left: 860px;">Commit</button>
									<button type="button" class="btn btn-default">Back</button>
								</div>
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
							<h4 class="modal-title" id="myModalLabel">你已经成功提交，稍后trainer会为你打分</h4>
						</div>
						<div class="modal-footer">
							<a href="${pageContext.request.contextPath}/trainee/${course.term.id}/course/view" class="btn btn-primary">确定</a>
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