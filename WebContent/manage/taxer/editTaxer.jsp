<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="static/css/base.css" >
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" type="text/css" href="static/css/edit.css">
</head>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/js/calendar.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"></script>
<body>
    <div class="container" id="win">
        <div class="content">
            <div title="修改办税专员信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">修改办税专员信息</span></div>
                <form id="addTaxer">
                <table class="kv-table">
                    <tbody>
                    <tr>
                        <td class="kv-label">工号</td>
                        <td class="kv-content"><input value="${taxer.taxerCode }" type="text" name="taxerCode" placeholder="工号" class="easyui-validatebox" data-options="required:true"></td>
                        <td class="kv-label">办税专员名称</td>
                        <td class="kv-content"><input value="${taxer.taxerName }" type="text" name="taxerName" placeholder="办税专员名称" class="easyui-validatebox" data-options="required:true"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">地址</td>
                        <td class="kv-content"><input value="${taxer.address }" type="text" name="address" placeholder="地址"></td>
                        <td class="kv-label">电话</td>
                        <td class="kv-content"><input value="${taxer.mobile }" type="text" name="mobile" placeholder="电话" class="easyui-validatebox" data-options="required:true"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select name="organId" id = "organId" value="${taxer.organId }" style="width: 210px;height: 34px;" class="easyui-validatebox">
                                <option value="-1" >请选择所属税务机关</option> 
                                                         
                            </select>
                        </td>
                        <td class="kv-label">有效标志</td>
                        <td class="kv-content">
                         <input type="text" value="${taxer.state }" name="state" placeholder="有效标志">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">性别</td>
                        <td class="kv-content">
                            <select name="sex" id = "sex" value="${taxer.sex }"style="width: 210px;height: 34px;">
                            	<option value="男">男</option>
                            	<option value="女">女</option>
                            </select>
                        </td>
                        <td class="kv-label">生日</td>
                        <td class="kv-content">
                            <input value="${taxer.birthday }" type="date" name="birthday" placeholder="生日" style="width: 210px;height: 34px;">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">电子邮件</td>
                        <td class="kv-content">
                            <input value="${taxer.email }" type="email" name="email" placeholder="电子邮件" style="width: 210px;height: 34px;">
                        </td>
                        <td class="kv-label">上级领导</td>
                        <td class="kv-content">
                            <input value="${taxer.mgr }" type="number" name="mgr" placeholder="上级领导" style="width: 210px;height: 34px;" class="easyui-validatebox" data-options="required:true">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">录入人员</td>
                        <td class="kv-content">
                            <input value="${taxer.recordUserId }" type="text" name="recordUserId" placeholder="录入人员">
                        </td>
                        <td class="kv-label">录入日期</td>
                        <td class="kv-content"><input type="text" value="${taxer.recordDate }" name="recordDate" placeholder="录入日期" style="width: 210px;height: 34px;" ></td>
                    </tr>
                    </tbody>
                </table>
                </form>
            </div>
            <div class="btn-selection">
                <a href="javascript:void(0);" class="easyui-linkbutton save-btn" data-options="selected:true" id="save">保存</a>
                <a href="javascript:void(0);" class="easyui-linkbutton reset-btn" data-options="selected:true" id="setBtn">重置</a>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    //ajax请求获取外键值
	$.post("allTaxOrgan.do",{},function(data){
		var organ = $("#organId")
		$.each(data,function(index, val){
			organ.append("<option value='"+val.id+"'>"+val.organName+"</option>")
		})
	},"json")
	$.post("allTaxSour.do",{},function(data){
		var organ = $("#selectOrgan")
		$.each(data,function(index, val){
			organ.append("<option value='"+val.organId+"'>"+val.organName+"</option>")
		})
	},"json")
    </script>
</body>
</html>