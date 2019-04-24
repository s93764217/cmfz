<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<script>
    $(function () {

        /*  var tb = [{
              iconCls: 'icon-tip',
              text: '专辑详情',
              handler: function () {
                  $("#detailDialog").dialog("open");
                  showdetails();

              }
          }, '-', {
              iconCls: 'icon-add',
              text: '添加专辑',
              handler: function () {
                  $("#dd_Album").dialog("open");
              }
          }, '-', {
              iconCls: 'icon-add',
              text: '添加章节',
              handler: function () {
                  $("#dd_chapter").dialog("open");
              }
          }, '-', {
              iconCls: 'icon-save',
              text: '下载音频',
              handler: function () {
                  exportFile();
              }
          }];*/

        $('#tt_album').treegrid({
            //后台Controller查询所有专辑以及对应的章节集合
            url: '${pageContext.request.contextPath}/album/selectAllAlbum',
            idField: 'id',//用来标识标识树节点   主干树与分支树节点  ID不能有相同  并且使用相同的字段  否则ID冲突
            treeField: 'title',//用来定义树节点   树形表格上要展示的信息   注意使用相同的字段 用来展示对应节点名称
            toolbar: "#albumToolBar",
            pagination: true,
            fit: true,
            fitColumns: true,
            onDblClickRow: function (row) {
                audioPlay(row);
            },
            columns: [[
                {field: 'title', title: '名字', width: 180},
                {field: 'downloadPath', title: '下载路径', width: 60},
                {field: 'size', title: '章节大小', width: 60},
                {field: 'duration', title: '章节的时长', width: 80}
            ]]
        });
        $("#detailDialog").dialog({
            width: 300,
            height: 500,
            closed: true,
            modal: true,
            title: '详情页'
        })
    })


    function addalbumclick() {
        $("#dd_Album").dialog("open");
    }

    function addchapterclick() {
        $("#dd_chapter").dialog("open");
    }

    function downloadclick() {
        exportFile();
    }

    function audioPlay(row) {
        var audio = document.getElementById("chapterplayer");
        audio.src = '${pageContext.request.contextPath}/jsp/album/mp3/' + row.downloadPath;
        audio.play();
    }

    function addAlbum() {
        $('#ff_album').form('submit', {
            url: '${pageContext.request.contextPath}/album/insert',
            success: function (data) {
                data = JSON.parse(data);
                if (data.isInsert) {
                    alert("添加成功");
                    $("#dd_Album").dialog("close");
                    $('#tt_album').treegrid("load");
                } else {
                    alert("添加失败");
                }
            }
        });
    }

    function closeAlbum() {
        $("#dd_Album").dialog("close");
    }

    function addChapter() {
        $('#ff_chapter').form('submit', {
            url: '${pageContext.request.contextPath}/album/insertChapter',
            success: function (data) {
                data = JSON.parse(data);
                if (data.isInsertChapter) {
                    alert("添加成功");
                    $("#dd_chapter").dialog("close");
                    $('#tt_album').treegrid("load");
                } else {
                    alert("添加失败");
                }
            }
        });

    }

    function showdetails() {
        var a = $('#tt_album').treegrid("getSelected");
        if (a == null) {
            alert("请至少选中一个专辑");
        } else {

            if (a.albumId != null) {
                var id = a.albumId;
                a = $('#tt_album').treegrid("find", id);
            }
            $("#detailDialog").dialog("open");
            $("#detailForm").form("load", a);
        }

    }

    function exportFile() {
        var file = $('#tt_album').treegrid("getSelected");
        window.location.href = "${pageContext.request.contextPath}/album/downloadFile?fileName=" + file.downloadPath + "&title=" + file.title;
    }

    function exportAlbum() {
        window.location.href = "${pageContext.request.contextPath}/album/export";
    }
</script>
<table id="tt_album" style="width:600px;height:400px"></table>
<div id="albumToolBar">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
       onclick="showdetails()">专辑详情</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true"
       onclick="addalbumclick()">添加专辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
       onclick="addchapterclick()">添加章节</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true"
       onclick="downloadclick()">下载音频</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true"
       onclick="exportAlbum()">导出到Excel</a>
    <audio src="" controls id="chapterplayer"></audio>
</div>
<div id="detailDialog">
    <form id="detailForm">
        专辑名称：<input type="text" name="title" readonly><br/>
        章节数量：<input type="text" name="amount" readonly><br/>
        专辑评分：<input type="text" name="score" readonly><br/>
        专辑作者：<input type="text" name="author" readonly><br/>
        专辑播音：<input type="text" name="announcer" readonly><br/>
        上传时间：<input type="date" name="releaseDate" readonly><br/>
        图片路径：<input type="text" name="imgPath" readonly><br/>
        专辑简介：<textarea name="introduction" readonly style="height: 100px;width: 250px"></textarea>
    </form>
</div>
<div id="dd_Album" class="easyui-dialog" title="添加专辑" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        addAlbum();
                    }
                },{
                    text:'关闭',
                    handler:function(){
                        closeAlbum();
                    }
                }]">

    <form id="ff_album" method="post" enctype="multipart/form-data">
        <div>
            <label for="title">标题:</label>
            <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true"/><br/>
            <label for="amount">集数:</label>
            <input id="amount" class="easyui-validatebox" type="number" name="amount"
                   data-options="required:true"/><br/>
            <label for="score">评分:</label>
            <input id="score" class="easyui-validatebox" type="number" name="score" data-options="required:true"/><br/>
            <label for="author">作者:</label>
            <input id="author" class="easyui-validatebox" type="text" name="author" data-options="required:true"/><br/>
            <label for="announcer">播音:</label>
            <input id="announcer" class="easyui-validatebox" type="text" name="announcer" data-options="required:true"/><br/>
            <label for="introduction">简介:</label><br/>
            <textarea id="introduction" style="height: 100px;width: 200px" name="introduction"></textarea>
        </div>
        封面：<input class="easyui-filebox" name="file1" style="width:150px">
    </form>
</div>
<div id="dd_chapter" class="easyui-dialog" title="添加章节" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        addChapter();
                    }
                },{
                    text:'关闭',
                    handler:function(){
                        closeChapter();
                    }
                }]">

    <form id="ff_chapter" method="post" enctype="multipart/form-data">
        <div>
            <label for="ctitle">标题:</label>
            <input id="ctitle" class="easyui-validatebox" type="text" name="ctitle" data-options="required:true"/><br/>
            <label for="cc1">专辑:</label>
            <input id="cc1" name="id" class="easyui-combobox" data-options="
                valueField: 'id',
                textField: 'title',
                url: '${pageContext.request.contextPath}/album/selectAll'
            "/>
        </div>
        文件：<input class="easyui-filebox" name="file1" style="width:150px">
    </form>
</div>