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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/timepicker/jquery.datetimepicker.css"/ >
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
						<li><a href="#">Manage Courses</a></li>
						<li class="active">Tables</li>
					</ol>

					<div class="row">
						<div class="col-md-12">
							<div class="btn-group pull-right" id="templatemo_sort_btn">
								<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">新建课程</button>
							</div>

							<div class="table-responsive">
								<h4 class="margin-bottom-15">课程计划</h4>
								<table class="table table-striped table-hover table-bordered ">
									<div id="actiontip" align="center"></div>
									<thead>
										<tr>
											<th>课程分类</th>
											<th>课程名字</th>
											<th>简介</th>
											<th>讲师</th>
											<th>时间</th>
											<th>地点</th>
											<th>课程管理</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody id="tbodyterms" class="text-left">
										<c:forEach items="${terms}" var="term">
											<tr>
												<td>${term["dictionary"]["value"]}</td>
												<td>${term["name"]}</td>
												<td>${term["content"]}</td>
												<td>${term["trainer"]["loginname"]}</td>
												<td> ${term["starttime"]}<br/>~${term["endtime"]}</td>
												<td>${term["location"]}</td>
												<td><a href="#" class="btn btn-default">Edit</a></td>
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
															<li><a href="${pageContext.request.contextPath}/trainer/practice/input?id=${term['id']}">管理练习</a></li>
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
									<li><a href="showall?page=${iterm}">${iterm}<span class="sr-only">(current)</span></a></li>
								</c:forEach>
								<!-- <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li> -->
								<li><a href="showall?page=${totolpages}">&raquo;</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<form class="form-horizontal" role="form" id="courseaddform" >
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">新建课程</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 课程名称 </label>
									<div class="col-sm-10">
										<input class="form-control" id="nameInput" name="name" type="text" placeholder="请输入课程名称">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 课程讲师 </label>
									<div class="col-sm-10">
										<input class="form-control" id="trainerInput" name="trainer" type="text" placeholder="请输入课程讲师">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 课程时间 </label>
									<div class="col-sm-10">
										<input class="form-control" id="starttimeInput" name="starttime" type="text" placeholder="请输入课程时间">
									</div>
									<label for="inputlabel" class="col-sm-2 control-label"> 课程时间 </label>
									<div class="col-sm-10">
										<input class="form-control" id="endtimeInput" name="endtime" type="text" placeholder="请输入课程时间">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 课程地点 </label>
									<div class="col-sm-10">
										<input class="form-control" id="localInput" name="location" type="text" placeholder="请输入课程地点 ">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 课程简介 </label>
									<div class="col-sm-10">
										<input type="text" id="descriptionInput" name="description" class="form-control" placeholder="请输入课程简介/简单描述">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 课程内容 </label>
									<div class="col-sm-10">
										<input type="text" id="contentInput" name="content" class="form-control" placeholder="请输入课程内容 简单描述">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label"> 课程目标 </label>
									<div class="col-sm-10">
										<input type="text" id="goalInput" name="goal" class="form-control" placeholder="请输入课程目标 简单描述">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button id="addterm" type="button" class="btn btn-primary" onclick="addCourseToTerm()" data-dismiss="modal">提交</button>
							</div>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>

<<<<<<< HEAD
			</div>
=======
		</div>
>>>>>>> refs/remotes/origin/master

		<!-- 页脚 -->
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
	</div>
	<script src="http://libs.useso.com/js/jquery/2.0.3/jquery.min.js"></script>
	<script src="http://libs.useso.com/js/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
	<script src="${pageContext.request.contextPath}/js/course.js"></script>
	<script src="${pageContext.request.contextPath}/timepicker/jquery.datetimepicker.js"></script>
	<!-- 配置文件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
</body>
</html>
