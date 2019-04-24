<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>


<table id="dg_user"></table>

<div id="dd_userRegister" class="easyui-dialog" title="模拟注册弹出框" style="width:400px;height:300px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        register();
                    }
                },{
                    text:'关闭',
                    handler:function(){
                        closeRegisterDialog();
                    }
                }]">
    <form id="register_form">
        Your name:<input type="text" name="name"/><br>
        Your password:<input type="password" name="password"/><br>
        Your dharma:<input type="text" name="dharma"/><br>
        Your gender:男<input type="radio" value="0" name="sex"/>女<input type="radio" value="1" name="sex"/><br>
        Your province&city:<span>河南 郑州</span><br>
        Your sign:<input type="text" name="sign"/><br>
        Your phone:<input type="text" name="phone"/><br>
    </form>
</div>
<script>
    var tb = [{
        iconCls: 'icon-add',
        text: '注册',
        handler: function () {
            $('#dd_userRegister').dialog('open');

        }
    }];
    $('#dg_user').edatagrid({
        method: "post",
        url: "${pageContext.request.contextPath}/user/selectAll",
        toolbar: tb,
        pagination: true,
        fit: true,
        fitColumns: true,
        pageSize: 20,
        pageList: [20, 40, 80, 160],
        columns: [[
            {field: 'id', title: '编号'},
            {field: 'name', title: '姓名'},
            {field: 'password', title: '密码'},
            {field: 'salt', title: '盐值'},
            {field: 'dharma', title: '法名'},
            {
                field: 'sex', title: '性别',
                formatter: function (value) {
                    if (value == 0) {
                        return "男";
                    } else {
                        return "女";
                    }
                }
            },
            {field: 'province', title: '省份'},
            {field: 'city', title: '城市'},
            {field: 'sign', title: '签名'},
            {
                field: 'status', title: '状态',
                formatter: function (value) {
                    if (value == 0) {
                        return "正常";
                    } else {
                        return "冻结 ";
                    }
                }
            },
            {field: 'phone', title: '手机号'},
            {field: 'registerTime', title: '注册时间'},
            {field: 'guruId', title: '上师id'}
        ]]
    });

    function register() {
        $('#register_form').form('submit', {
            url: '${pageContext.request.contextPath}/user/register',
            success: function (data) {
                data = JSON.parse(data);
                if (data.isRegister) {
                    $("#register_form").form("reset");
                    $("#dd_userRegister").dialog("close");
                    $('#dg_user').edatagrid("reload");
                }
            }
        });
    }

    function closeRegisterDialog() {
        $("#register_form").form("reset");
        $("#dd_userRegister").dialog("close");
    }
</script>
