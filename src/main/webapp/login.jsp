<%@page pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>login</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.2.1.min.js"></script>
		<script type="text/javascript">
			$(function () {
				/**
				 * 登录
				 */
				$('#loginButton').click(function () {
					var username = $('#username').val();
					var password = $('#password').val();
					$.post('${pageContext.request.contextPath}/user/userLogin','username='+username+"&password="+password,function (date) {
						if (date['status'] == 200) {
							//登录成功，跳转页面
							// window.location.href = "";
						}else{
							$('#spanMessage').text(date['message']);
						}
					})
				});


			})
		</script>
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="rightheader">
							<p>
								2009/11/20
								<br />
							</p>
						</div>
						<div id="topheader">
							<h1 id="title">
								<a href="#">main</a>
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						login
					</h1>
					<form method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									username:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="name" id="username" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									password:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="pwd" id="password" />
								</td>
							</tr>
						</table>
						<p>
							<input type="button" class="button" value="Submit &raquo;" id="loginButton" />
							<input type="button" class="button" onclick="location.href='${pageContext.request.contextPath}/regist.jsp'" value="Regist &raquo;" />
							<span style="color: red" id="spanMessage"></span>
						</p>
					</form>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
					ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
