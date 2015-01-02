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
						<li><a href="index.html">trainee Panel</a></li>
						<li><a href="#">term view</a></li>
						<li class="active">course view</li>
					</ol>

					<div class="row">
						<div class="col-md-12">

							<div class="table-responsive">
								<h4 class="margin-bottom-15">课程查看</h4>
								<table class="table table-striped table-hover table-bordered ">
									<thead>
										<tr>
											<th>课程分类</th>
											<th>课程名字</th>
											<th>简介</th>
											<th>时间</th>
											<th>地点	 </th>
											<th>目标</th>
											<th>习题管理</th>
										</tr>
									</thead>
									<tbody id="tbodycourses" class="text-left">
										<c:forEach items="${courses}" var="course">
											<tr>
												<td>${course["dictionary"]["name"]}</td>
												<td>${course["name"]}</td>
												<td>${course["content"]}</td>
												<td>${course["starttime"]}<br />~${course["endtime"]}
												<td>${course["location"]}</td>
												<td>${course["goal"]}</td>
												<td>
													<c:if test="${course['practiseStatus'] == 0}">
														<span style="color: red;">未布置习题</span>
													</c:if> 
													<c:if test="${course['practiseStatus'] == 1}">
														<a href="${pageContext.request.contextPath}/trainee/practice/commit?id=${course['id']}" class="btn btn-default">习题管理</a>
													</c:if>
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

			<!-- Modal -->
			<div class="modal fade" id="practiceManageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				data-backdrop="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="practiceModalH4"></h4>
						</div>
						<div class="modal-body">
           					
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
		<script src="${pageContext.request.contextPath}/js/viewPractice.js"></script>
	</div>
</body>
</html>