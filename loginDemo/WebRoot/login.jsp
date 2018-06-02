<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
	
<title>登录</title>
<style>

</style>


<style>
		.row{
			border:1px solid #ddd;//h3字体下边框
		}
  		.input-group{
			margin:10px 0px;//输入框上下外边距为10px,左右为0px
		}
		
		h3{
			padding:5px;
			border-bottom:1px solid #ddd;//h3字体下边框
		}
		
		li{
			list-style-type:square;//列表项图标为小正方形
			margin:10px 0;//上下外边距是10px
		}
		
		em{//强调的样式
			color:#c7254e;
			font-style: inherit;
			background-color: #f9f2f4;
		}
		.tipMessage{
			color:gray;
		}
		
  </style>
  </head>
  <body>
  <div class="container-fluid">
    <div class="row" style="margin:0px;">
		<div class="col-md-offset-4">
			<div style="text-align:center">
				<h3 style="text-align:center;">用户登录</h3>
			</div>

		
			<div>
			<form action="loginServlet" method="post">
				<table>
					<thead>
						<tr></tr>
					</thead>
					<tbody>
						<tr>
							<td><label for="username">用户名：</label></td>
							<td><input type="text" id="username" name="username"
								value="${param.username }"></td>
							
						</tr>
						<tr>
							<td><label for="passwd">密&nbsp;&nbsp;码：</label></td>
							<td><input type="password" id="password" name="password"
								value="${param.passwd }"></td>
							
						</tr>
						<tr>
							<td colspan="3">&nbsp;<span class="tipMessage">${requestScope.loginError}</span></td>
						</tr>
						<tr>
							<td colspan="3" align="center"><input type="submit" value="登录" class="button primary">&nbsp;&nbsp;&nbsp;&nbsp;<input class="button primary" type="reset" value="清空"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		</div>
	</div>
	













</body>
<script src="./javascript/bootstrap.min.js"></script>
	<script type="text/javascript" src="./javascript/jquery-3.2.1.js"></script>
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
