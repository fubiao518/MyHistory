<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加办税专员</title>
</head>
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
            <div title="办税专员信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">添加办税专员信息</span></div>
                <form id="addTaxer">
                <table class="kv-table">
                    <tbody>
                    <tr>
                        <td class="kv-label">工号</td>
                        <td class="kv-content"><input type="text" name="taxerCode" placeholder="工号" class="easyui-validatebox" data-options="required:true"></td>
                        <td class="kv-label">办税专员名称</td>
                        <td class="kv-content"><input type="text" name="taxerName" placeholder="办税专员名称" class="easyui-validatebox" data-options="required:true"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">地址</td>
                        <td class="kv-content"><input type="text" name="address" placeholder="地址"></td>
                        <td class="kv-label">电话</td>
                        <td class="kv-content"><input type="text" name="mobile" placeholder="电话" class="easyui-validatebox" data-options="required:true"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select name="organId" id = "organId" style="width: 210px;height: 34px;">
                                <option value="-1" >请选择所属税务机关</option> 
                            </select>
                        </td>
                        <td class="kv-label">有效标志</td>
                        <td class="kv-content">
                         <input type="text" name="state" placeholder="有效标志">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">性别</td>
                        <td class="kv-content">
                            <select name="sex" id = "sex" style="width: 210px;height: 34px;">
                            	<option value="男">男</option>
                            	<option value="女">女</option>
                            </select>
                        </td>
                        <td class="kv-label">生日</td>
                        <td class="kv-content">
                            <input type="date" name="birthday" placeholder="生日" style="width: 210px;height: 34px;">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">电子邮件</td>
                        <td class="kv-content">
                            <input type="email" name="email" placeholder="电子邮件" style="width: 210px;height: 34px;">
                        </td>
                        <td class="kv-label">上级领导</td>
                        <td class="kv-content">
                            <select name="mgr" id = "mgr" style="width: 210px;height: 34px;">
                                <option value="-1" >请选择上级领导</option> 
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">录入人员</td>
                        <td class="kv-content">
                            <input type="text" name="recordUserId" placeholder="录入人员">
                        </td>
                        <td class="kv-label">录入日期</td>
                        <td class="kv-content"><input type="text" name="recordDate" placeholder="录入日期" style="width: 210px;height: 34px;" value="<%=new SimpleDateFormat("y-MM-dd").format(new Date())%>"></td>
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
    $(function(){
    	$.post("allTaxOrgan.do",{},function(data){
    		var organ = $("#organId")
    		$.each(data,function(index, val){
    			organ.append("<option value='"+val.id+"'>"+val.organName+"</option>")
    		})
    	},"json")
    	$.post("allTaxer.do",{},function(data){
    		var mgr = $("#mgr")
    		$.each(data,function(index, val){
    			mgr.append("<option value='"+val.id+"'>"+val.taxerName+"</option>")
    		})
    	},"json")
    })
    //下拉框验证
/*     $.extend($.fn.validatebox.defaults.rules, {

        select: {
           validator: function(value, param) {
               return $(param[0]).find("option:contains('"+value+"')").val() != '-1';  
           },
           message: "该输入项是必填项！"
       }
   }); */
    //重置按钮
    $("#setBtn").bind("click",function(){
    	$("#addTaxer").form('reset');
	})
	
	//提交按钮
	$("#save").bind("click",function(){
		var state = $("#addTaxer").form("validate");
		if(state){
			$.post("addTaxer.do",$("#addTaxer").serialize(),function(result){
				if(result.success){
					$.messager.alert('提示信息',result.msg,'info',function(){
						
					});
				}
			},"json")
		} else{
			$.messager.alert('警告！','请填写完整信息','info');
		}
	})
	
    </script>
</body>
</html>