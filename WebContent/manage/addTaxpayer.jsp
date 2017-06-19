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
<title>添加办税专员</title>
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
        <div class="content">
            <div title="纳税人信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">添加纳税人信息</span></div>
                <form id="addTaxpayer">
                <table class="kv-table">
                    <tbody>
                    <tr>
                        <td class="kv-label">纳税人识别号</td>
                        <td class="kv-content"><input type="text" name="payerCode" placeholder="纳税人识别号" class="easyui-validatebox" data-options="required:true"></td>
                        <td class="kv-label">纳税人名称</td>
                        <td class="kv-content"><input type="text" name="payerName" placeholder="纳税人名称"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营地址</td>
                        <td class="kv-content"><input type="text" name="bizAddress" placeholder="生产经营地址"></td>
                        <td class="kv-label">经营地电话</td>
                        <td class="kv-content"><input type="text" name="bizAddressPhone" placeholder="生产经营地电话"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select name="taxOrganid" id="taxOrganid">
                                <option value="-1" id="selectOrgan">请选择所属税务机关</option>                         
                            </select>
                        </td>
                        <td class="kv-label">行业</td>
                        <td class="kv-content">
                            <select name="industryId" id="industryId">
                                <option value="-1" id="selectIndustry">请选择纳税人行业</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营范围</td>
                        <td class="kv-content">
                            <input type="text" name="bizScope" placeholder="生产经营范围">
                        </td>
                        <td class="kv-label">票种核定</td>
                        <td class="kv-content">
                            <select name="invoiceType">
                                <option value="-1">请选择发票种类</option>
                                <option value="1">增值税普通发票</option>
                                <option value="2">增值税专用发票</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">法人代表人</td>
                        <td class="kv-content">
                            <input type="text" name="legalPerson" placeholder="法人姓名">
                        </td>
                        <td class="kv-label">法人身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="legalIdCard" placeholder="法人代表身份证号码">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">主管财务</td>
                        <td class="kv-content">
                            <input type="text" name="finaceName" placeholder="主管财务">
                        </td>
                        <td class="kv-label">财务身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="finaceIdCard" placeholder="财务身份证号">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">办税专员</td>
                        <td class="kv-content">
                            <select name="taxerName" id="taxerName">
                                <option value="-1">请选择办税专员</option>
                            </select>
                        </td>
                        <td class="kv-label">录入日期</td>
                        <td class="kv-content"><input type="text" name="recordDate" placeholder="录入日期" style="width: 210px;height: 34px;" value="<%=new SimpleDateFormat("y-MM-dd").format(new Date())%>" readonly="readonly"></td>
                    </tr>
                    <tr>
                    	<td class="kv-label">录入人员</td>
                        <td class="kv-content">
                            <select name="userId" id="userId">
                                <option value="-1">请选择录入人员</option>
                            </select>
                        </td>
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
		//ajax请求获取外键值
		$.get("allIndustry.do",{},function(data){
			var industryId = $("#industryId")
			$.each(data,function(index, val){
				industryId.append("<option value='"+val.id+"'>"+val.industryName+"</option>")
			})
		},"json")
		$.post("allTaxOrgan.do",{},function(data){
			var organ = $("#taxOrganid")
			$.each(data,function(index, val){
				organ.append("<option value='"+val.id+"'>"+val.organName+"</option>")
			})
		},"json")
		
		$.post("allTaxer.do",{},function(data){
    		var taxerName = $("#taxerName")
    		$.each(data,function(index, val){
    			taxerName.append("<option value='"+val.id+"'>"+val.taxerName+"</option>")
    		})
    	},"json")
    	$.post("allUser.do",{},function(data){
    		var userId = $("#userId")
    		$.each(data,function(index, val){
    			userId.append("<option value='"+val.id+"'>"+val.username+"</option>")
    		})
    	},"json")
	})
	
	//为重置按钮添加事件
	$("#setBtn").bind("click",function(){
    	$("#addTaxpayer").form('reset');
	})
	//保存按钮
	$("#save").bind("click",function(){
		var state = $("#addTaxpayer").form("validate");
		if(state){
			$.post("addPayer.do",$("#addTaxpayer").serialize(),function(result){
				if(result.success){
					$.messager.alert('提示信息',result.msg,'info',function(){
						parent.$("#topWindow").window("close");
					});
				}
			},"json")
		}
	})
</script>
</body>
</html>


