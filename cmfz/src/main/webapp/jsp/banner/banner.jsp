<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<table id="dg_banner"></table>
<div id="dd_banner" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        addBanner();
                    }
                },{
                    text:'关闭',
                    handler:function(){
                        closeBanner();
                    }
                }]">

    <form id="ff" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">标题:</label>
            <input id="name" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        图片：<input class="easyui-filebox" name="file1" style="width:150px">
    </form>
</div>
<script>
    var tb = [{
        iconCls: 'icon-add',
        text: '添加',
        handler: function () {
            //alert('编辑按钮')
            $('#dd_banner').dialog('open');

        }
    }, '-', {
        iconCls: 'icon-edit',
        text: '修改',
        handler: function () {
            $('#dg_banner').edatagrid('saveRow');
        }
    }, '-', {
        iconCls: 'icon-delete',
        text: '删除',
        handler: function () {
            //alert('帮助按钮')
            $('#dg_banner').edatagrid('destroyRow');
        }
    }, '-', {
        iconCls: 'icon-save',
        text: '保存',
        handler: function () {
            //alert('帮助按钮')
            $('#dg_banner').edatagrid('saveRow');
        }
    }];
    $('#dg_banner').edatagrid({
        method: "get",
        url: "${pageContext.request.contextPath}/banner/selectAll",
        saveUrl: "${pageContext.request.contextPath}/banner/updateStatus",
        updateUrl: "${pageContext.request.contextPath}/banner/updateStatus",
        destroyUrl: "${pageContext.request.contextPath}/banner/delete",
        toolbar: tb,
        fit: true,
        pagination: true,
        pageSize: 3,
        pageList: [3, 6, 9, 11],
        columns: [[
            {field: 'title', title: '标题', width: 250, editor: {type: "text", options: {precision: 1}}},
            {field: 'status', title: '状态', width: 250, editor: {type: "text", options: {precision: 1}}},
            {field: 'imgPath', title: '路径', width: 250},
            {field: 'addTime', title: '时间', width: 250}
        ]],
        view: detailview,
        detailFormatter: function (rowIndex, rowData) {
            return '<table><tr>' +
                '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/jsp/banner/img/' + rowData.imgPath + '" style="height:50px;"></td>' +
                '<td style="border:0">' +
                '<p>标题: ' + rowData.title + '</p>' +
                '<p>状态: ' + rowData.status + '</p>' +
                '</td>' +
                '</tr></table>';
        },
        onDestroy: function () {
            $('#dg_banner').edatagrid("load");
        },
        destroyMsg: {
            norecord: {    // 在没有记录选择的时候执行
                title: '警告',
                msg: '请至少选择一行.'
            },
            confirm: {       // 在选择一行的时候执行
                title: '确认',
                msg: '确定要删除吗?'
            }
        }
    });


    function addBanner() {
        $('#ff').form('submit', {
            url: '${pageContext.request.contextPath}/banner/insert',
            success: function (data) {
                data = JSON.parse(data);
                if (data.isInsert) {
                    $("#dd_banner").dialog("close");
                    $('#dg_banner').datagrid("reload");
                }
            }
        });
    }

    function closeBanner() {
        $("#dd_banner").dialog("close")
    }

</script>

