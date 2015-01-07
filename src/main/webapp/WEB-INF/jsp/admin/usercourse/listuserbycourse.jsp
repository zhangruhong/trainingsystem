<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<head>
<meta charset="utf-8">
<title>学员出勤情况</title>
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

							<div class="table-responsive">
								<h4 class="margin-bottom-15">出勤状况</h4>
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
										<c:if test="${!empty usercoursemap}">
											<c:forEach items="${usercoursemap}" var="item" varStatus="vs">
												<tr>
													<td>${item.key}</td>
													<%-- <td>${nowDate-item["course"]["endtime"]>0?item[attendCourseStatus]==0?"完成":"缺席":"课程还没有结束"}</td> --%>
													<td><select class="form-control" disabled="disabled">
															<option value="0" ${item.value.attendCourseStatus==0?'selected':''}>正常</option>
															<option value="1" ${item.value.attendCourseStatus==1?'selected':''}>缺勤</option>
															<option value="2" ${item.value.attendCourseStatus==2?'selected':''}>请假</option>
													</select></td>
													<td><textarea class="form-control " rows="1" disabled="disabled">${item.value.description}</textarea></td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${empty usercoursemap}">
											<tr>
												<td colspan="3" align="center">抱歉，数据库里没有该课程的出勤信息，原因可能是老师还没有更新这门课的出勤状态</td>
											</tr>
										</c:if>
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
	<script src="http://libs.useso.com/js/jquery/2.0.3/jquery.min.js"></script>
	<script src="http://libs.useso.com/js/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<!-- <script src="http://apps.bdimg.com/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="http://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
	<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/validator/js/bootstrapValidator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/personValidate.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#savechange1").click(function() {
				//序列化表单元素，返回json数据  
				var params = $("#bashupdate").serializeArray();
				/* var jsonobj = $("#bashupdate").form2json(); */

				//也可以把表单之外的元素按照name value的格式存进来  
				//params.push({name:"hello",value:"man"});  
				$.ajax({
					type : "POST",
					url : "update",
					dataType : "json",
					contentType : "application/json; charset=utf8",
					data : params,
					success : function(data) {
						alert("成功");
					},
					error : function(e) {
						alert("出错：" + e);
					}
				});
			});
		});
	</script>


</body>
</html>
