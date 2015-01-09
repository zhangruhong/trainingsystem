<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<head>
<meta charset="utf-8">
<title>Trainee attendance</title>
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
						<li><a href="/trainingsystem/admin/term/showall">Admin Panel</a></li>
						<li><a href="#">Manage attendance</a></li>
						<li class="active">Trainee attendance</li>
					</ol>
					<div class="row">
						<div class="col-md-12">

							<div class="table-responsive">
								<h4 class="margin-bottom-15">Trainee Attendance</h4>
								<table class="table table-striped table-hover table-bordered ">
									<div id="actiontip" align="center"></div>
									<thead>
										<tr>
											<th>Trainee</th>
											<th>Attendance</th>
											<th>Comments</th>
										</tr>
									</thead>

									<tbody id="tbodyterms" class="text-left">
										<c:if test="${!empty usercoursemap}">
											<c:forEach items="${usercoursemap}" var="item" varStatus="vs">
												<tr>
													<td>${item.key}</td>
													<%-- <td>${nowDate-item["course"]["endtime"]>0?item[attendCourseStatus]==0?"完成":"缺席":"课程还没有结束"}</td> --%>
													<td><select class="form-control" disabled="disabled">
															<option value="0" ${item.value.attendCourseStatus==0?'selected':''}>Normal</option>
															<option value="1" ${item.value.attendCourseStatus==1?'selected':''}>Absence</option>
															<option value="2" ${item.value.attendCourseStatus==2?'selected':''}>Leave</option>
													</select></td>
													<td><textarea class="form-control " rows="1" disabled="disabled">${item.value.description}</textarea></td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${empty usercoursemap}">
											<tr>
												<td colspan="3" align="center">Sorry, no course attendance information in database, the reason may be the teacher did not update
													this course attendance status</td>
											</tr>
										</c:if>
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
										<li><a href="show?page=1">&emsp;&nbsp;first&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage-1<=0}">
										<li class="disabled"><a href="#">Previous</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage-1>0}">
										<li><a href="show?page=${pageResult.currentPage-1}">Previous</a></li>
									</c:if>
									<li><a href="" style="color: black;">&emsp;&nbsp;${pageResult.currentPage}/${pageResult.totalPages}&emsp;&nbsp;</a></li>
									<c:if test="${pageResult.currentPage+1>pageResult.totalPages}">
										<li class="disabled"><a href="#">&emsp;&nbsp;next&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage+1<=pageResult.totalPages}">
										<li><a href="show?page=${pageResult.currentPage+1}">&emsp;&nbsp;next&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage+1>pageResult.totalPages}">
										<li class="disabled"><a href="#">&emsp;&nbsp;last&emsp;&nbsp;</a></li>
									</c:if>
									<c:if test="${pageResult.currentPage+1<=pageResult.totalPages}">
										<li><a href="show?page=${pageResult.totalPages}">&emsp;&nbsp;last&emsp;&nbsp;</a></li>
									</c:if>
								</ul>
							</div>
							<!-- page end  -->
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
						alert("Succuss");
					},
					error : function(e) {
						alert("Error：" + e);
					}
				});
			});
		});
	</script>


</body>
</html>
