<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/china.js"></script>

    <script type="text/javascript">
        <!--菜单处理-->
        $(function () {
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/menu/queryAllMenu",
                dataType: "JSON",
                success: function (data) {
                    // 遍历data，data为map类型
                    $.each(data, function (key1, value) {
                        // 遍历value，vlaue为list集合
                        $.each(value, function (index1, first) {
                            var c = "<div align='center'>";
                            // 遍历menus
                            $.each(first.menus, function (index2, second) {
                                // 将json对象转换长json字符串
                                var child = JSON.stringify(second);
                                // 拼接
                                c += "<p><a class='easyui-linkbutton' onclick='addTabs(" + child + ")'>" + second.title + "</a></p>";
                            })
                            c += "</div>";
                            // 添加手风琴
                            $('#aa').accordion('add', {
                                title: first.title,
                                iconCls: first.iconCls,
                                content: c,
                                selected: false
                            });
                        })
                    })
                }
            })
        })

        // 点击二级目录，添加选项卡
        function addTabs(child) {
            var isExists = $("#tt").tabs("exists", child.title);
            if (isExists) {
                $("#tt").tabs("select", child.title)
            } else {
                $("#tt").tabs("add", {
                    title: child.title,
                    closable: true,
                    href: "${pageContext.request.contextPath}/jsp/" + child.jsp_url
                })
            }
        }
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:xxxxx
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;咕哒子驰名商标</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">

    </div>
</div>
</body>
</html>