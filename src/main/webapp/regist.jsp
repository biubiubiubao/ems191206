<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>regist</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.2.1.min.js"></script>
		<script type="text/javascript">
			$(function () {
				$('#registButton').click(function () {
					//获取输入的数据
					var username = $('#username').val();
					var password = $('#password').val();
					var name = $('#name').val();
					var sex = $("input[name='sex']:checked").val();
					var number = $('#number').val();
					if (username && password && name && sex && number) {
						//注册Controller
						$.post('${pageContext.request.contextPath}/user/userRegist',
								{"username":username,"password":password,"name":name,
									"sex":sex,"number":number},function (data) {
							if (data['status'] == '200') {
							//注册成功
								window.location.href = '${pageContext.request.contextPath}/login.jsp';
							}else{
								$('#spanMessage').text(data['message']);
							}
						});
					}else{
						alert('请输入全部信息');
					}

				})
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
						注册
					</h1>
					<form action="login.jsp" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" id="username"  />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									真实姓名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" id="name" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" id="password" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									性别:
								</td>
								<td valign="middle" align="left">
									男
									<input type="radio" class="inputgri" name="sex" value="男" checked="checked"/>
									女
									<input type="radio" class="inputgri" name="sex" value="女"/>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" align="right">
									验证码:
									<img id="num" src="${pageContext.request.contextPath}/captcha/captcha" />
									<a href="javascript:;" onclick="document.getElementById('num').src = '${pageContext.request.contextPath}/captcha/captcha?'+(new Date()).getTime()">换一张</a>
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" id="number" />
								</td>
							</tr>
						</table>
						<p>
							<input type="button" class="button" value="Submit &raquo;" id="registButton" />
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
