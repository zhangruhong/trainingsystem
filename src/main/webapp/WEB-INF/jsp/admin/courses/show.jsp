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
								<button class="btn btn-primary " data-toggle="modal" data-target="#addDictionary">Create Dictionary</button>
								<button class="btn btn-primary " data-toggle="modal" data-target="#myModal">Create Course</button>
							</div>

							<div class="table-responsive">
								<h4 class="margin-bottom-15">Courses Plan</h4>
								<table class="table table-striped table-hover table-bordered ">
									<div id="actiontip" align="center"></div>
									<thead>
										<tr>
											<th>Dictionary</th>
											<th>Course Name</th>
											<th>summary</th>
											<th>Trainer</th>
											<th>Time</th>
											<th>place</th>
											<th>Manage</th>
										</tr>
									</thead>
									<tbody id="tbodyterms" class="text-left">
										<c:forEach items="${pageResult.rows}" var="item">
											<tr>
												<td>${item["dictionary"]["name"]}</td>
												<td>${item["name"]}</td>
												<td>${item["content"]}</td>
												<td>${item["trainer"]["loginname"]}</td>
												<td>${item["starttime"]}<br />~${item["endtime"]}
												</td>
												<td>${item["location"]}</td>
												<td><a class="btn btn-primary btn-default"
													href="${pageContext.request.contextPath}/admin/term/${item['term']['id']}/courses/${item['id']}/attendstatus/show">Progress</a>
													<button class="btn btn-primary btn-default" data-toggle="modal" data-target="#updateCourse" onclick="loadCourse(${item['id']})">Edit</button>
													<button class="btn btn-primary btn-default" data-toggle="modal" data-target="#deleteconfirm" onclick="setdelvalue(${item['id']})">Delete</button>
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
			<!--新建课程 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<form class="form-horizontal" role="form" id="courseaddform">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Create Course</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Dictionary</label>
									<div class="col-sm-10">
										<select id="dictionaries" class="form-control">
											<option value="-1" selected="selected">select a dictionary</option>
											<c:forEach items="${dictionaries}" var="term">
												<option value="${term['id'] }">${term['name'] }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Name </label>
									<div class="col-sm-10">
										<input class="form-control" id="nameInput" name="name" type="text" placeholder="Input Course Name">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Trainer</label>
									<div class="col-sm-10">
										<input class="form-control" id="trainerInput" name="trainer" type="text" placeholder="Input Course Trainer">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">StartTime</label>
									<div class="col-sm-10">
										<input class="form-control" id="starttimeInput" name="starttime" type="text" placeholder="Input Course Trainer">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">End Time</label>
									<div class="col-sm-10">
										<input class="form-control" id="endtimeInput" name="endtime" type="text" placeholder="Input Course Trainer">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Place</label>
									<div class="col-sm-10">
										<input class="form-control" id="localInput" name="location" type="text" placeholder="Input Course Place">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Summary</label>
									<div class="col-sm-10">
										<input type="text" id="descriptionInput" name="description" class="form-control" placeholder="Input Course summary">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Content</label>
									<div class="col-sm-10">
										<input type="text" id="contentInput" name="content" class="form-control" placeholder="Input Course Content">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">goal</label>
									<div class="col-sm-10">
										<input type="text" id="goalInput" name="goal" class="form-control" placeholder="Input Course goal">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								<button id="addterm" type="button" class="btn btn-primary" onclick="addCourseToTerm()" data-dismiss="modal">Submit</button>
							</div>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>

			<!--新建课程分类模态框-->
			<div class="modal fade" id="addDictionary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<form id="addDictionaryForm" class="form-horizontal" role="form">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Create Course Dictionary</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="inputPassword" class="col-sm-2 control-label">Name</label>
									<div class="col-sm-10">
										<input class="form-control" id="dictionnarynameInput" name="name" type="text" placeholder="Input Courses Dictionary Name">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								<button id="addterm" type="submit" class="btn btn-primary">Submit</button>
							</div>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>

			<!--修改课程 模态框-->
			<div class="modal fade" id="updateCourse" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
				<div class="modal-dialog">
					<form class="form-horizontal" role="form" id="courseaddform">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Edit Course</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Course Dictionary </label>
									<div class="col-sm-10">
										<select id="dictionaries_update" class="form-control" name="dictionaries">
											<c:forEach items="${dictionaries}" var="term">
												<option value="${term['id'] }">${term['name'] }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Course Name </label>
									<div class="col-sm-10">
										<input class="form-control" id="idInput_update" name="id" type="hidden"> <input class="form-control" id="nameInput_update"
											name="name" type="text" placeholder="Input Course Name">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Course Trainer</label>
									<div class="col-sm-10">
										<input class="form-control" id="trainerInput_update" name="trainer" type="text" placeholder="Input Course Trainer">
									</div>
								</div>
								<div class="form-group form-inline">
									<label for="inputlabel" class="col-sm-2 control-label">Course Time</label>
									<div class="col-sm-10">
										<input class="form-control" id="starttimeInput_update" name="starttime" type="text" placeholder="Select Course Time"> —— <input
											class="form-control" id="endtimeInput_update" name="endtime" type="text" placeholder="Select Course Time">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Course Place</label>
									<div class="col-sm-10">
										<input class="form-control" id="localInput_update" name="location" type="text" placeholder="Input Course Place ">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Content</label>
									<div class="col-sm-10">
										<input type="text" id="contentInput_update" name="content" class="form-control" placeholder="Input Course Content">
									</div>
								</div>
								<div class="form-group">
									<label for="inputlabel" class="col-sm-2 control-label">Goal</label>
									<div class="col-sm-10">
										<input type="text" id="goalInput_update" name="goal" class="form-control" placeholder="Input Course Goal">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								<button id="updateterm_update" type="submit" class="btn btn-primary">Submit</button>
							</div>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>

			<!-- 确认删除 模态框（Modal） -->
			<div class="modal fade" id="deleteconfirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Delete Confirm ?</h4>
						</div>
						<div class="modal-body">Unrecoverable if you delete this course!</div>
						<div class="modal-footer">
							<button id="cancaltodelete" type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
							<button id="yestodelete" type="button" class="btn btn-primary" value="">Yes</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
		</div>
	</div>
	<!-- 页脚 -->
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
	</div>
	<script src="http://libs.useso.com/js/jquery/2.0.3/jquery.min.js"></script>
	<script src="http://libs.useso.com/js/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<!-- <script src="http://apps.bdimg.com/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="http://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
	<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
	<script src="${pageContext.request.contextPath}/js/course.js"></script>
	<script src="${pageContext.request.contextPath}/timepicker/jquery.datetimepicker.js"></script>
	<script src="http://momentjs.com/downloads/moment.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/validator/js/bootstrapValidator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/personValidate.js"></script>
</body>
</html>
