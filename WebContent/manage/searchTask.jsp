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
       <table id="dg" style="width:100%;height:529px" data-options="
                rownumbers:true,
                singleSelect:false,
                autoRowHeight:false,
                pagination:true,
                fitColumns:true,
                striped:true,
                checkOnSelect:false,
                selectOnCheck:false,
                collapsible:true,
                toolbar:'#tb',
                pageSize:10">
            <thead>
                <tr>
                    <th field="taskName" width="110">任务名称</th>
                    <th field="payerCode" width="112">纳税人识别号</th>
                    <th field="payerName" width="226">纳税人名称</th>
                    <th field="industryName" width="170">行业</th>
                    <th field="bizScope" width="130">经营范围</th>
                    <th field="executeTime" width="136">执行时间</th>
                    <th field="recordTime" width="120">录入时间</th>
                    <th field="overDays" width="105">录入超时(天)</th>
                    <th field="operation" width="105">操作</th>
                </tr>
            </thead>
        </table>
      <div id="tb" style="padding:0 30px;">
        <div class="conditions">
            <span class="con-span">纳税人识别号: </span><input class="easyui-textbox" type="text" name="code" style="width:166px;height:35px;line-height:35px;">
            <span class="con-span">纳税人名称: </span><input class="easyui-textbox" type="text" name="name" style="width:166px;height:35px;line-height:35px;">
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
            <a href="javascript:void(0);" class="easyui-linkbutton more" iconCls="icon-more">更多</a>
        </div>
        <div class="conditions hide">
            <span class="con-span">税务机关: </span><select class="easyui-combobox" style="width:166px;height:35px;line-height:35px;"><option>请选择税务机关</option></select>
            <span class="con-span">任务开始时间: </span><input class="easyui-datebox" type="text" name="code" style="width:166px;height:35px;line-height:35px;">
            <span class="con-span">任务结束时间: </span><input class="easyui-datebox" type="text" name="code" style="width:166px;height:35px;line-height:35px;">
            <span class="con-span">行业: </span><select class="easyui-combobox" style="width:166px;height:35px;line-height:35px;"><option>请选择行业</option></select>
        </div>
      </div>
    </div>
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
            (function($){
            function pagerFilter(data){
                if ($.isArray(data)){   // is array
                    data = {
                        total: data.length,
                        rows: data
                    }
                }
                var target = this;
                var dg = $(target);
                var state = dg.data('datagrid');
                var opts = dg.datagrid('options');
                if (!state.allRows){
                    state.allRows = (data.rows);
                }
                if (!opts.remoteSort && opts.sortName){
                    var names = opts.sortName.split(',');
                    var orders = opts.sortOrder.split(',');
                    state.allRows.sort(function(r1,r2){
                        var r = 0;
                        for(var i=0; i<names.length; i++){
                            var sn = names[i];
                            var so = orders[i];
                            var col = $(target).datagrid('getColumnOption', sn);
                            var sortFunc = col.sorter || function(a,b){
                                return a==b ? 0 : (a>b?1:-1);
                            };
                            r = sortFunc(r1[sn], r2[sn]) * (so=='asc'?1:-1);
                            if (r != 0){
                                return r;
                            }
                        }
                        return r;
                    });
                }
                var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
                var end = start + parseInt(opts.pageSize);
                data.rows = state.allRows.slice(start, end);
                return data;
            }

            var loadDataMethod = $.fn.datagrid.methods.loadData;
            var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
            $.extend($.fn.datagrid.methods, {
                clientPaging: function(jq){
                    return jq.each(function(){
                        var dg = $(this);
                        var state = dg.data('datagrid');
                        var opts = state.options;
                        opts.loadFilter = pagerFilter;
                        var onBeforeLoad = opts.onBeforeLoad;
                        opts.onBeforeLoad = function(param){
                            state.allRows = null;
                            return onBeforeLoad.call(this, param);
                        }
                        var pager = dg.datagrid('getPager');
                        pager.pagination({
                            onSelectPage:function(pageNum, pageSize){
                                opts.pageNumber = pageNum;
                                opts.pageSize = pageSize;
                                pager.pagination('refresh',{
                                    pageNumber:pageNum,
                                    pageSize:pageSize
                                });
                                dg.datagrid('loadData',state.allRows);
                            }
                        });
                        $(this).datagrid('loadData', state.data);
                        if (opts.url){
                            $(this).datagrid('reload');
                        }
                    });
                },
                loadData: function(jq, data){
                    jq.each(function(){
                        $(this).data('datagrid').allRows = null;
                    });
                    return loadDataMethod.call($.fn.datagrid.methods, jq, data);
                },
                deleteRow: function(jq, index){
                    return jq.each(function(){
                        var row = $(this).datagrid('getRows')[index];
                        deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
                        var state = $(this).data('datagrid');
                        if (state.options.loadFilter == pagerFilter){
                            for(var i=0; i<state.allRows.length; i++){
                                if (state.allRows[i] == row){
                                    state.allRows.splice(i,1);
                                    break;
                                }
                            }
                            $(this).datagrid('loadData', state.allRows);
                        }
                    });
                },
                getAllRows: function(jq){
                    return jq.data('datagrid').allRows;
                }
            })
        })(jQuery);

        function getData(){
            var rows = [];
            for(var i=1; i<=800; i++){
                rows.push({
                    taskName: '税源信息采集',
                    payerCode:'100210222',
                    payerName: '南京天泽星网股份有限公司',
                    industryName: '信息技术',
                    bizScope: '光纤通信设备配件',
                    executeTime: '2017-01-20',
                    recordTime: '2017-01-21',
                    overDays:'1',
                    operation: '<a href="">查看</a> <a href="">修改</a> <a href="">删除</a>'
                });
            }
            return rows;
        }
        
        $(function(){
            $('#dg').datagrid({data:getData()}).datagrid('clientPaging');
        });    


        $(".more").click(function(){
            $(this).closest(".conditions").siblings().toggleClass("hide");
        });
    </script>
</body> 
</html>
