<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="navbar-collapse collapse templatemo-sidebar">
	<ul class="templatemo-sidebar-menu">
		<li><div align="center"><font size="15">Admin</font></div></li>
		<li class="sub open">
            <a href="javascript:;">
              <i class="fa fa-database"></i>User Management<div class="pull-right"><span class="caret"></span></div>
            </a>
            <ul class="templatemo-submenu">
              <li><a href="/trainingsystem/admin/user/show"><i class="fa fa-home"></i>User Information</a></li>
              <li><a href="/trainingsystem/admin/term/showallforuser"><i class="fa fa-home"></i>Manage user by term</a></li>
            </ul>
          </li>
		<li><a href="/trainingsystem/admin/term/showall"><i class="fa fa-cubes"></i>Term and Course</a></li>
		<!-- <li><a href="/trainingsystem/admin/practice/view"><i class="fa fa-cubes"></i>练习题管理</a></li> -->
	</ul>
</div>