<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="icon-home"></i>
            <a href="index.html">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a href="/user/toUserPage.do" class="ajaxify">角色管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span>角色列表</span>
        </li>
    </ul>
</div>
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet light ">
            <div class="portlet-title">
                <div class="caption font-dark">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject bold uppercase">角色列表</span>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-toolbar">
                    <div class="row">
                        <div class="col-md-1">
                            <div class="btn-group">
                                <a class="btn sbold green ajaxify" href="/user/toUserAddPage.do"> 新增
                                    <i class="fa fa-plus"></i>
                                </a>
                            </div>
                        </div>
                        <div class="col-md-offset-5 col-md-6">
                            <div class="btn-group pull-right">
                                <button class="btn green  btn-outline dropdown-toggle" data-toggle="dropdown">导出
                                    <i class="fa fa-angle-down"></i>
                                </button>
                                <ul class="dropdown-menu pull-right">
                                    <li>
                                        <a href="javascript:;">
                                            <i class="fa fa-file-excel-o"></i>导出用户表格</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <table id="userList" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr role="row">
                        <th>id</th>
                        <th>名称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="auth" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                菜单列表
                <div class="modal-body">
                    <input type="hidden" name="id" id="id"/>
                    <div id="authTree" class="tree-demo">
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-md-offset-4 col-md-4">
                        <button class="btn btn-primary" onclick="saveAuth();">确定</button>
                        <button class="btn btn-cancel" onclick="hideModal();">取消</button>
                    </div>
                    <div class="col-md-4">

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        table = $("#userList")
        table.DataTable({
            "language": {
                url: "assets/global/plugins/datatables/datatable_i18n_cn.json"
            },
            columns: [
                {
                    "data": "id",
                    "defaultContent": "-"
                },
                {
                    "data": "name",
                    "defaultContent": "-"
                },
                {
                    "data": "id",
                    "defaultContent": "-",
                    "render": function (data, type, full, meta) {
                        var html = "<a href='role/toDetail.do?id="
                                        + data
                                        + "' class='btn btn-primary ajaxify'>详情</a>"
                                        + "<a href='user/toEdit.do?id="
                                        + data
                                        + "' class='btn btn-warning ajaxify'>编辑</a>"
                                        + "<button onclick='deleteById(\"" + data + "\");' class='btn btn-danger' >删除</button>"
                                        + "<button onclick='authorization(\"" + data + "\");' class='btn btn-danger' >授权</button>"

                                ;
                        return html;
                    }
                }
            ],
            "ordering": false,
            "lengthMenu": [[5, 10, 20], [5, 10, 20]],
            "pageLength": 10,
            "pagingType": "bootstrap_full_number",
            "processing": true,
            "serverSide": true,
            "searching": false,
            "deferRender": true,//延迟加载
            "stateSave": true,//状态保存
            "autoWidth": true,//自动计算列宽
            "ajax": {
                "dataSrc": "data",
                "type": "post",
                "url": "role/list.do"
            }
        });
    });

    //授权信息
    function authorization(data) {
        $("#id").val(data);
        $("#auth").modal("show");
        //初始化jstree控件
        bindJsTree("authTree", "/common/getMenuTree.do?parentId=0", true);
    }

    //隐藏信息
    function hideModal() {
        $("#auth").modal("hide");
    }

    function saveAuth() {
        //获取选中的节点id
        var nodes = $("#authTree").jstree("get_checked");
        var id = $("#id").val();
        $.ajax({
            type: "post",
            url: "/role/saveAuth.do",
            dataType: "json",
            async: false,
            data: {"authId": nodes + "", "roleId": id},
            success: function (data) {
                if (data > 0) {
                    $("#auth").modal("hide");
                    table.DataTable().draw();
                }
            },
            error: function () {
                alert("网络延时!");
            }
        });

    }

</script>