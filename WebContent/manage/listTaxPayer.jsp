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
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>纳税人管理</title>
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
 	<div class="container">
		<table id="dg"></table>
	</div>
      <div id="tb" style="padding:0 30px;">
        纳税人识别号: <input class="easyui-textbox" type="text" name="payerCode" id="payerCode" style="width:166px;height:35px;line-height:35px;"/>
        纳税人名称: <input class="easyui-textbox" type="text" name="payerName" id="payerName" style="width:166px;height:35px;line-height:35px;"/>
        <a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="javascript:void(0);" id="setBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
        <a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">添加纳税人</a>
      </div>
    </div>

    <script type="text/javascript">
	$('#dg').datagrid({
	    url:"taxPayerList.do",
	    rownumbers:true,
	    loadMsg:"数据加载中...",
	    pagination:true,
	    toolbar:"#tb",
	    columns:[[
			{field:'payerCode',title:'纳税人识别号',width:100},
			{field:'payerName',title:'纳税人名称',width:100},
			{field:'organName',title:'所属税务机关',width:100},
			{field:'industryName',title:'所属行业',width:100},
			{field:'legalPerson',title:'法人代表',width:100},
			{field:'legalIdCard',title:'法人身份证号码',width:100},
			{field:'finaceName',title:'主管财务',width:100},
			{field:'finaceIdCard',title:'财务身份证号码',width:100},
			{field:'taxerName',title:'办税人员',width:100},
			{field:'recordDate',title:'录入日期',width:100},
			{field:'operation',title:'操作',width:100,
				formatter: function(value,row,index){
					return"<a href='javascript:void(0);' onclick='edit("+row.id+")'>修改</a>|<a href='javascript:void(0);' onclick='deletePayer("+row.id+")'>删除</a>";
				}
			}
	    ]]
	});
        //为搜索按钮添加事件处理函数
    $("#searchBtn").bind("click",function(){
		var payerName = $("#payerName").val();
		var payerCode = $("#payerCode").val();
		$('#dg').datagrid('load',{
			payerName:payerName,
			payerCode:payerCode
		});
	})
        //为重置按钮添加事件处理函数
    $("#setBtn").bind("click",function(){
		$("#payerName").textbox("setValue","");
		$("#payerCode").textbox("setValue","");
		$('#dg').datagrid('load',{});
	})
        //为添加纳税人添加事件处理函数
       $(function(){
           $("#addBtn").on("click",function(e){
              openTopWindow({
                  width : 750,
                  height : 600,
                  title : "新增纳税人",
                  url : "manage/addTaxpayer.jsp"
              });
           });

       });
       	//为删除添加事件
       	 var deletePayer = function (id) {
       		$.messager.confirm('信息','确定删除？',function(r){
       			if(r){
       				//发送请求
       				$.post("deletePayer.do",{"id":id},function(result){
       					if(result.success){
       						$.messager.alert('提示信息',result.msg,'info',function(){
       							$('#dg').datagrid('load',{});
       						});
       					}else{
       						$.messager.alert('提示信息',result.msg,'info',function(){
       							$('#dg').datagrid('load',{});
       						});
       						
       					}
       				},"json")
       			}
       		})
       	} 
       //为修改添加事件
     	var edit = function(id) {
     	    
     	        openTopWindow({
     	            width : 750,
     	            height : 600,
     	            title : "修改纳税人",
     	            url : "editPayer.do?id="+id
     	        });

     	}
           /**
            *打开在父窗口中打开window
            */
           function openTopWindow(options){
               options = !options ? {} :options;
               options.width = !options.width ? 500 : options.width;
               options.height = !options.height ? 400 : options.height;
               options.url = !options.url ? "404.html" : options.url;
               options.title = !options.title ? "" : options.title;

               parent.$("#topWindow").window({
                   title : options.title,
                   width: options.width,
                   height: options.height,
                   content : "<iframe scrolling='no' frameborder='0' border='0' height='100%' width='100%' src='"+options.url+"'></iframe>",
                   modal:true,
                   resizable:false,
                   collapsible:false
               });
           }
    </script>
</body> 
</html>
