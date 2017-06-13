<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="static/css/base.css">
<link rel="stylesheet" type="text/css"
	href="static/easyui/uimaker/easyui.css">
<link rel="stylesheet" type="text/css"
	href="static/easyui/uimaker/icon.css">
<link rel="stylesheet" type="text/css" href="static/css/edit.css">

</head>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<body>
	<div class="container">
		<div class="content">
			<div title="纳税人信息" data-options="closable:false" class="basic-info">
				<div class="column">
					<span class="current">修改密码</span>
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">密码</td>
							<td class="kv-content"><input type="password"
								id="oldPassword" name="oldPassword" placeholder="密码" maxlength="20">
								<div id="error1" class="alert alert-error"
										style="display: inline; padding: 3px;">
										<i class="iconfont">&#xe62e;</i> <span id="info1"></span>
								</div>
							</td>
						</tr>
						<tr>
							<td class="kv-label">新密码</td>
							<td class="kv-content"><input type="password"
								id="newPassword" name="newPassword" placeholder="新密码" maxlength="20">
								<div id="error2" class="alert alert-error"
										style="display: inline; padding: 3px;">
										<i class="iconfont">&#xe62e;</i> <span id="info2"></span>
								</div>
							</td>
						</tr>
						<tr>
							<td class="kv-label">确认密码</td>
							<td class="kv-content"><input type="password"
								id="validatePassword" name="validatePassword" placeholder="确认密码" maxlength="20">
								<div id="error3" class="alert alert-error"
										style="display: inline; padding: 3px;">
										<i class="iconfont">&#xe62e;</i> <span id="info3"></span>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn-selection">
				<a href="javascript:void(0);" class="easyui-linkbutton save-btn"
					id="modify_pwd" data-options="selected:true">保存</a> <a
					href="javascript:void(0);" class="easyui-linkbutton reset-btn"
					data-options="selected:true" id="reset">重置</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/js/calendar.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function(){
		$("#error1").hide();
		$("#error2").hide();
		$("#error3").hide();
  		$("#modify_pwd").bind("click",function(){
  			changePassword();
  		})
  		$("#reset").bind("click",function(){
  			$("input").val("");
  		})

	})
	var changePassword = function(){
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		var validatePassword = $("#validatePassword").val();
		if(!oldPassword || oldPassword.length < 6){
			$("#error1").show();
			$("#info1").text("密码最少为6位").css("color","red");
			return;
		}else{
			$("#error1").hide();
		}
		if(!newPassword || newPassword.length < 6){
			$("#error2").show();
			$("#info2").text("新密码最少为6位").css("color","red");
			return;
		}else{
			$("#error2").hide();
		}
		if(validatePassword != newPassword){
			$("#error3").show();
			$("#info3").text("两次密码不一样").css("color","red");
			return;
		}else{
			$("#error3").hide();
		}
		$.messager.confirm('信息','确定修改？',function(r){
		    if (r){
		    	//发送修改密码的post请求
				$.post("modifypassword.do",{"oldPassword":oldPassword,"newPassword":newPassword,"validatePassword":validatePassword},function(result){
					if(result.success){
						$("input").val("");
						$("#error1").hide();
		    			parent.$("#win").window("close");
					} else {
						$("#error1").show();
						$("#info1").text("原密码错误").css("color","red");
					}
				},"json");
		    }
		});
		
		}
</script>
</html>
