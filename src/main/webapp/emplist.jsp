<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>emplist</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.2.1.min.js"></script>
		<script type="text/javascript">
			$(function () {
				fenye(1);
			});
			function fenye(page){
                //设置当前页码
			    $('#nowPage').val(page);
                //控制上一页是否出现
                if (page == 1) {
                    $('#sbutton').css("display","none");
                }else{
                    $('#sbutton').css("display","inline");
                }

			    //查询数据
				$.post('${pageContext.request.contextPath}/emp/empQueryAllSplitPage','page='+page,function (data) {
                    $('#tbody').empty();
					for (var i in data['emps']) {
						var emp = data['emps'][i];
						var tr = $('<tr>').attr("class","row1");
						var id = $('<td>'+emp.id+'</td>');
						var name = $('<td>'+emp.name+'</td>');
						var salary = $('<td>'+emp.salary+'</td>');
						var age = $('<td>'+emp.age+'</td>');
						var operating = $(`<td><a href="${pageContext.request.contextPath}/emp/empDelete?id=`+emp.id+`">delete emp</a>&nbsp;<a href="${pageContext.request.contextPath}/emp/empQueryOne?id=`+emp.id+`"> update emp</a></td>`);
						tr.append(id).append(name).append(salary).append(age).append(operating);
						$('#tbody').append(tr);
					}
					//控制下一页是否出现
                    if (page >= data['pageCount']) {
                        $('#xbutton').css("display","none");
                    }else{
                        $('#xbutton').css("display","inline");
                    }
                });
			}
			function controllerPage(pageVal) {
                //设置当前页码
                var nowPage = $('#nowPage').val();
                //查询数据
                if ('+1' == pageVal) {
                    fenye(Number(nowPage)+1);
                }else if ('-1' == pageVal) {
                    fenye(Number(nowPage)-1);
                }
			}
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
						Welcome!
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								Name
							</td>
							<td>
								Salary
							</td>
							<td>
								Age
							</td>
							<td>
								Operation
							</td>
						</tr>
						<tbody id="tbody"></tbody>
					</table>
					<button onclick="controllerPage('-1')" id="sbutton" >
						上一页
					</button>
					<%--存放当前页码,和显示当前页码--%>
					<span id="nowPage"></span>
					<%--存放总页数--%>
					<input type="hidden" id="total">
					<button onclick="controllerPage('+1')" id="xbutton">
						下一页
					</button><br><br>

					<p>
						<input type="button" class="button" value="Add Employee" onclick="location='${pageContext.request.contextPath}/addEmp.jsp'"/>
					</p>

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
