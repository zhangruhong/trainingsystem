<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<head>
<meta charset="utf-8">
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
						<li><a href="#">Manage Courses</a></li>
						<li class="active">Tables</li>
					</ol>

					<div class="row">
						<div class="col-md-12">

							<div class="pull-right" id="templatemo_sort_btn">
								<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addDictionary">新建分类</button>
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
												<td>${term["dictionary"]["name"]}</td>
												<td>${term["name"]}</td>
												<td>${term["content"]}</td>
												<td>${term["trainer"]["loginname"]}</td>
												<td>${term["starttime"]}<br />~${term["endtime"]}
												</td>
												<td>${term["location"]}</td>
												<td>
													<button class="btn btn-primary btn-default" data-toggle="modal" data-target="#updateCourse" onclick="loadCourse(${term['id']})">编辑</button>
													<button class="btn btn-primary btn-default" data-toggle="modal" data-target="#deleteconfirm" onclick="setdelvalue(${term['id']})">删除</button>
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

		</div>
	</div>
	<!-- 页脚 -->
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
	</div>
	<!-- <script src="http://libs.useso.com/js/jquery/2.0.3/jquery.min.js"></script>
	<script src="http://libs.useso.com/js/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
	<script src="http://apps.bdimg.com/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="http://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
		
   
    	
	</script>
</body>
</html>
