<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/filterStr.js" charset="gb2312"></script>

<div id="infoDetails" class="body_textarea" style="display: none">
	<a href="#" id="btnCreate" onclick="createPasswordInfo()">Create</a> <a
		href="#" id="btnDelete">Delete</a> <a href="#" id="btnModify">Modify</a>
	<a href="#" id="closeDetails" onclick="cancel()">Close</a><label
		id="infoId" style="visibility: hidden"></label> <br />
	<div id="table_infoDetails" style="border-top: 1px dashed #000;">
		<table>
			<tr>
				<td>name:</td>
				<td><label id="name"></label></td>
			</tr>
			<tr>
				<td>username:</td>
				<td><label id="username" data-clipboard-target="username"></label></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><label id="password" data-clipboard-target="password"></label></td>
			</tr>
			<tr>
				<td>url:</td>
				<td><a id="url" target="_blank"></a></td>
			</tr>
			<tr>
				<td>info:</td>
				<td><label id="info"></label></td>
			</tr>
		</table>
	</div>
	<div id="controlInfoDetails" style="display: none">
		<table>
			<tr>
				<td>name:</td>
				<td><input type="text" id="txt_name" class="name"
					onBlur="isNull(this)" /><label class="name"></label></td>
			</tr>
			<tr>
				<td>username:</td>
				<td><input type="text" id="txt_username" class="username"
					onBlur="isNull(this)" /><label class="username"></label></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><input type="text" id="txt_password" class="password"
					onBlur="isNull(this)" /><label class="password"></label></td>
			</tr>
			<tr>
				<td>url:</td>
				<td><input type="text" id="txt_url" class="url" /><label
					class="url"></label></td>
			</tr>
			<tr>
				<td>info:</td>
				<td><input type="text" id="txt_info" class="info" /><label
					class="info"></label></td>
			</tr>
			<tr>
				<td><input type="button" value="submit" id="control_submit" /></td>
				<td><input type="button" value="cancel" id="control_cancel"
					onclick="cancel()" /></td>
			</tr>
		</table>
	</div>
</div>