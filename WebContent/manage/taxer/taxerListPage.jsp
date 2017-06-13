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
<title>办税专员管理</title>
	<base href="<%=basePath%>">
    <link href="static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" href="static/css/taxpayer.css">
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="container">
	<table id="dg"></table>
</div>
<div id="tb" style="padding:0 30px;">
        纳税专员姓名: <input class="easyui-textbox" type="text" id="taxerName" name="taxerName" style="width:166px;height:35px;line-height:35px;"/>
        <a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="javascript:void(0);" id="setBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
        <a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">添加纳税人</a>
</div>

<script type="text/javascript">
	$('#dg').datagrid({
	    url:"taxerList.do",
	    rownumbers:true,
	    loadMsg:"数据加载中...",
	    pagination:true,
	    toolbar:"#tb",
	    columns:[[
	    	{field:'id',title:'办税专员id',width:100},
			{field:'taxerCode',title:'办税专员工号',width:100},
			{field:'taxerName',title:'办税专员姓名',width:100},
			{field:'sex',title:'办税专员性别',width:100},
			{field:'mobile',title:'办税专员手机号',width:100},
			{field:'birthday',title:'办税专员生日',width:100},
			{field:'address',title:'办税专员地址',width:100},
			{field:'organName',title:'所属税务机关',width:100},
			{field:'operation',title:'操作',width:100,
				formatter: function(value,row,index){
					return"<a href='javascript:void(0);' onclick='edit("+row.id+")'>修改</a>|<a href='javascript:void(0);' onclick='deleteTaxer("+row.id+")'>删除</a>";
				}
			}
	    ]]
	});
	
	//查询按钮添加事件
	$("#searchBtn").bind("click",function(){
		var taxerName = $("#taxerName").val();
		$('#dg').datagrid('load',{
			taxerName:taxerName
		});
	})
	//重置按钮添加事件
	$("#setBtn").bind("click",function(){
		$("#taxerName").textbox("setValue","")
	})
	
	//为删除添加事件
	var deleteTaxer = function (id) {
		$.messager.confirm('信息','确定删除？',function(r){
			if(r){
				
			}
		})
	}	
	//修改添加事件
	var edit = function(id) {
	    
	        openTopWindow({
	            width : 750,
	            height : 600,
	            title : "修改办税专员",
	            url : "manage/taxer/editTaxer.jsp"
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