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
		<jsp:include page="/WEB-INF/jsp/trainer/navigate.jsp"></jsp:include>

		<div class="template-page-wrapper">
			<!-- 菜单 -->
			<jsp:include page="/WEB-INF/jsp/trainer/menu.jsp"></jsp:include>
			<!--/.navbar-collapse -->

			<div class="templatemo-content-wrapper">
				<div class="templatemo-content">

					<ol class="breadcrumb">
						<li><a href="index.html">trainer Panel</a></li>
						<li><a href="#">Manage practice</a></li>
						<li class="active">add</li>
					</ol>

					<div class="row">
						<div class="col-md-12">

							<div class="table-responsive">
								<h4 class="margin-bottom-15">${course.name}  Course 练习添加</h4>
							</div>
							<!-- 富文本编辑器 -->
							<div>
								<input type="hidden" id="courseId" value="${course.id}">
								<input type="hidden" id="coursePractise" value="${course.practise}">
								<!-- 加载编辑器的容器 -->
								<script id="container" name="editcontent" type="text/plain" style="width: 900px; height: 450px;"></script>
								
								<button type="button" class="btn btn-default" style="margin-left: 850px;">返回</button>
								<button type="button" class="btn btn-primary" onclick="uploadHtml();">提交更改</button>
								
							</div>
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
		<!-- 配置文件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
		<!-- 编辑器源码文件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
		<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
		<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
		<script src="${pageContext.request.contextPath}/js/practice.js"></script>
	</div>
</body>
</html>