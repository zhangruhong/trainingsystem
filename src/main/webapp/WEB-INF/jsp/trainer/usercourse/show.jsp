<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<head>
<meta charset="utf-8">
<title>学员出勤管理</title>
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
								<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">保存更改</button>
							</div>

							<div class="table-responsive">
								<h4 class="margin-bottom-15">出勤管理</h4>
								<table class="table table-striped table-hover table-bordered ">
									<div id="actiontip" align="center"></div>
									<thead>
										<tr>
											<th>学员</th>
											<th>出勤状态</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody id="tbodyterms" class="text-left">
									<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set> 
										<c:forEach items="${users}" var="item">
											<tr>
												<td>${item["loginname"]}</td>
												<%-- <td>${nowDate-item["course"]["endtime"]>0?item[attendCourseStatus]==0?"完成":"缺席":"课程还没有结束"}</td> --%>
												<td>下拉选项</td>
												<td>等待添加</td>
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
