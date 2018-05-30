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
<style>
/* css reset */
* {
	margin: 0px;
	padding: 0px;
	font-family: "Courier New";
}
/* 主面板 */
#container {
	width: 80%;
	margin: 0 auto;
	border: 1px solid #DDD;
	height: 1000px;
}
/* banner */
#header {
	width: 100%;
	height: 150px;
	background-color: #01BF9D;
	font-size: 50px;
	line-height: 50px;
	/*vertical-align: bottom;*/
}

#header .title {
	background-color: transparent;
	float: left bottom;
}
/* 导航 */
#nav li {
	display: inline;
	float: left;
	width: 20%;
}

#nav li a {
	display: block;
	/*width: 150px;*/
	height: 30px;
	line-height: 30px;
	font-size: 15px;
	text-align: center;
	text-decoration: none;
	border: 1px solid #EEE;
	/*border-width:0px 1px 1px 1px;*/
	color: black;
}
/*鼠标高亮*/
#nav li a:hover {
	border: 0px;
	background-color: #01BF9D;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
	color: white;
	/*height:200px;*/
	font-weight: bold;
}

#login,#individuationSettings {
	background-color: #01BF9D;
	text-align: center;
	margin: 130px auto;
	border: 1px solid #AAA;
	height: 250px;
	width: 400px
}

#login form table,#individuationSettings form table {
	margin: 45px auto;
	vertical-align: center;
}

hr {
	color: #EEE;
}

.left {
	text-align: left;
}

.right {
	text-align: right;
}

p.title {
	font-weight: bold;
	padding: 2px 5px;
	background-color: #01A187;
}

input[type="text"],input[type="password"] {
	/*background-color:#01BF9D;*/
	background: transparent;
	border-width: 0px;
	border-bottom: 1px solid black;
	width: 100%;
}

input[type="text"]:focus,input[type="password"]:focus {
	border-bottom-color: red;
}

.button {
	border-width: 0px;
	/*background-color:transparent;*/
	background-color: #01A187;
	/*border:1px solid grey;*/
	cursor: pointer;
	padding: 8px 15px;
	border-radius: 3px;
}

.button:hover {
	/*background-color:grey*/
	/*border:1px solid grey;*/
	color: white;
}

label {
	cursor: pointer;
}

.required {
	color: red;
	font-size: 13px;
	font-weight: bold;
}

.select {
	background: transparent;
	border: 0px;
	border-bottom: 1px solid black;
	width: 100%;
}

.tipMessage {
	color: red;
	font-size: 13px;
}
</style>
</head>

<body>

	<!-- 
  <form action="loginServlet" method="post">
  	用户名：<input type="text" name="username" value="${param.username }" />${requestScope.usernameError }<br/>
  	密&nbsp;&nbsp;码：<input type="password" name="passwd" value="${param.passwd }" />${requestScope.passwdError }<br/>
  	<input type="submit" value="登录" />${requestScope.loginError}
  </form>
  -->

	<div id="container">

		
		<ul id="nav">
			<li><a href="#">首页</a></li>
			<li><a href="#">随笔</a></li>
			<li><a href="#">相册</a></li>
			<li><a href="#">留言板</a></li>
			<li><a href="#">关于我</a></li>
		</ul>





		<div id="login">
			<p class="left title">登录</p>
			<!-- <hr> -->
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
							<td><span class="tipMessage">${requestScope.usernameError }</span></td>
						</tr>
						<tr>
							<td><label for="passwd">密&nbsp;&nbsp;码：</label></td>
							<td><input type="password" id="passwd" name="passwd"
								value="${param.passwd }"></td>
							<td><span class="tipMessage">${requestScope.passwdError }</span></td>
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













</body>
</html>
