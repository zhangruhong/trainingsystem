<!-- Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Are you sure you want to sign out?</h4>
			</div>
			<div class="modal-footer">
				<a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">Yes</a>
				<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
			</div>
		</div>
	</div>
</div>

<!-- Moda2 -->
<div class="modal fade" id="updatePasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<form class="form-horizontal" role="form">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">update password</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="inputlabel" class="col-sm-2 control-label"> old password </label>
						<div class="col-sm-10">
							<input class="form-control" id="oldPd" type="text" placeholder="please input old password">
						</div>
					</div>
					<div class="form-group">
						<label for="inputlabel" class="col-sm-2 control-label"> new password </label>
						<div class="col-sm-10">
							<input class="form-control" id="newPd" type="text" placeholder="please input new password">
						</div>
					</div>
					<div class="form-group">
						<label for="inputlabel" class="col-sm-2 control-label"> confirm password </label>
						<div class="col-sm-10">
							<input class="form-control" id="confirmPd" type="text" placeholder="please input confirm password">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="${pageContext.request.contextPath}/updatepassword" class="btn btn-primary">Yes</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
				</div>
			</div>
		</form>
	</div>
</div>

<footer class="templatemo-footer">
	<div class="templatemo-copyright">
		<p>
			Copyright &copy; 2084 synnex <a href="http://www.synnex.com/" title="聚思力信息技术有限公司" target="_blank">聚思力信息技术有限公司</a> - More Information <a
				href="http://www.cssmoban.com/" target="_blank" title="聚思力信息技术有限公司">聚思力信息技术有限公司</a>
		</p>
	</div>
</footer>