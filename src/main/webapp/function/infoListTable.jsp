<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table id='table_infolist' class='table'>
	<tr>
		<th>name</th>
		<th>username</th>
		<th>password</th>
		<th>url</th>
		<th>info</th>
		<th id='isSelectAll'>selectAll</th>
	</tr>

	<c:forEach var="msgListForPerson" items="${list }">
		<c:forEach var="msgItem" items="${msgListForPerson.passwordInfos }">
			<tr id="${msgItem.id }">
				<td class='table_name'>${msgItem.name }</td>
				<td class='table_username'
					data-clipboard-text="${msgItem.username }">${msgItem.username }</td>
				<td class='table_pwd'
					data-clipboard-text="${msgItem.password }">${msgItem.password }</td>
				<td><a href="${msgItem.url }" target='_blank'
					class='table_url'>${msgItem.url }</a></td>
				<td class='table_info'>${msgItem.info }</td>
				<td><input class='table_input' name='selectIds' type="checkbox"
					value='${msgItem.id }' /></td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>