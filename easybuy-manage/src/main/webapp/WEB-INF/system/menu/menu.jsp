<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-01-09
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="icon-home"></i>
            <a href="index.do">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a href="/menu/toList.do" class="ajaxify">菜单管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span>菜单列表</span>
        </li>
    </ul>
</div>
<div class="row">
    <div class="col-md-3">
        <div class="portlet light ">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-social-dribbble font-blue-sharp"></i>
                    <span class="caption-subject font-blue-sharp bold uppercase">菜单关系图</span>
                </div>
            </div>
            <div class="portlet-body">
                <input type="hidden" id="parentId" value="6">
                <div id="menuTree" class="tree-demo">

                </div>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet light ">
            <div class="portlet-title">
                <div class="caption font-dark">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject bold uppercase">菜单列表</span>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-toolbar">
                    <div class="row">
                        <div class="col-md-1">
                            <div class="btn-group">
                                <button class="btn sbold green" onclick="showModal();"> 新增
                                    <i class="fa fa-plus"></i>
                                </button>
                            </div>
                        </div>
                        <div class="col-md-offset-5  col-md-6">
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
                        <th>名称</th>
                        <th>标识</th>
                        <th>类别</th>
                        <th>菜单URL</th>
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

<div class="modal fade" id="menuModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                添加菜单
                <div class="modal-body">
                    <form class="form-horizontal" id="addMenu" novalidate="novalidate">
                        <input type="hidden" name="parentId" id="parent" value="6">
                        <div class="form-group">
                            <label class="control-label col-md-3">名称：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="name" data-required="1" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">标识：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="permission" data-required="1" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">类型：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <div class="col-md-6">
                                    <input type="radio" name="type" data-required="1" value="1" class="form-control">菜单
                                </div>
                                <div class="col-md-6">
                                    <input type="radio" name="type" data-required="1" value="0" class="form-control">按钮
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">菜单URL：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="url" data-required="1" class="form-control"></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div class="col-md-offset-4 col-md-4">
                        <button class="btn btn-primary" onclick="saveMenu();">确定</button>
                        <button class="btn btn-cancel" onclick="hideModal();">取消</button>
                    </div>
                    <div class="col-md-4">

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="menuEditModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                添加菜单
                <div class="modal-body">
                    <form class="form-horizontal" id="editMenu" novalidate="novalidate">
                        <input type="hidden" name="id">
                        <div class="form-group">
                            <label class="control-label col-md-3">名称：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="name" data-required="1" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">标识：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="permission" data-required="1" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">菜单URL：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="url" data-required="1" class="form-control"></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div class="col-md-offset-4 col-md-4">
                        <button class="btn btn-primary" onclick="saveEditMenu();">确定</button>
                        <button class="btn btn-cancel" onclick="hideEditmodal();">取消</button>
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
        table = $("#userList");
        table.DataTable({
            "language": {
                url: "assets/global/plugins/datatables/datatable_i18n_cn.json"
            },
            columns: [
                {
                    "data": "name",
                    "defaultContent": "-"
                },
                {
                    "data": "permission",
                    "defaultContent": "-"
                },
                {
                    "data": "type",
                    "render": function (data) {
                        var html = "";
                        if (data == 1) {
                            html = "菜单";
                        } else {
                            html = "按钮";
                        }
                        return html;
                    },
                    "defaultContent": "-"
                },
                {
                    "data": "url",
                    "defaultContent": "-"
                },
                {
                    "data": "id",
                    "defaultContent": "-",
                    "render": function (data, type, full, meta) {
                        var html = "<a href='menu/toDetail.do?id="
                                + data
                                + "' class='btn btn-primary ajaxify'>详情</a>"
                                + "<button onclick='editMenu(" + meta.row + ")' class='btn btn-warning'>编辑</button>"
                                + "<button type='button' class='btn btn-danger' onclick='deleteUser(\"" + data + "\");'>删除</button>"
                        return html;
                    }
                }
            ],
            "ordering": false,
            "lengthMenu": [[5, 10, 20], [5, 10, 20] // change per page values here
            ],
            // set the initial value
            "pageLength": 10,
            "pagingType": "bootstrap_full_number",
            // set first column as a default sort by asc
            "processing": true,
            "serverSide": true,
            "searching": false,
            "deferRender": true,//延迟加载
            "stateSave": true,//状态保存
            "autoWidth": true,//自动计算列宽
            "ajax": {
                "dataSrc": "data",
                "type": "post",
                "url": "/menu/list.do",
                "data": function (d) {
                    d['params'] = {
                        "parentId": $.trim($("#parentId").val())
                    };
                }
            }
        });
        //初始化jstree控件
        bindJsTree("menuTree", "/common/getMenuTree.do?parentId=0&type=1");
    });
    $('#menuTree').on("changed.jstree", function (e, data) {
        var id = data.instance.get_node(data.selected[0]).id;
        $("#parentId").val(id);
        $("#parent").val(id); //新增菜单parentId
        table.DataTable().draw();
    });


    /**
     * 保存菜单
     */
    function saveMenu() {
        $("[name='parentId']").val($("#parentId").val());//设置parentId
        var formdata = $("#addMenu").serialize();
        console.log(formdata);
        $.ajax({
            type: "post",
            url: "/menu/save.do",
            dataType: "json",
            async: false,
            data: formdata,
            success: function (data) {
                if (data > 0) {
                    $("#addMenu").modal("hide");
                    table.DataTable().draw();
                }
            },
            error: function () {
                alert("网络延时!");
            }
        });


    }


    /**
     * 编辑菜单
     */
    function saveEditMenu() {
        var formdata = $("#editMenu").serialize();
        console.log(formdata);
        $.ajax({
            type: "post",
            url: "/menu/edit.do",
            dataType: "json",
            async: false,
            data: formdata,
            success: function (data) {
                if (data > 0) {
                    $("#menuEditModal").modal("hide");
                    table.DataTable().draw();
                }
            },
            error: function () {
                alert("网络延时!");
            }
        });
    }


    //隐藏modal
    function hideModal() {
        $("#menuModal").modal("hide");
    }

    //显示添加modal
    function showModal() {
        $("#menuModal").modal("show");
    }

    //显示编辑modal
    function editMenu(index) {
        $("#editMenu").form("clear");
        var data = table.DataTable().row(index).data();
        $("#editMenu").form("load", data);
        $("#menuEditModal").modal("show");
    }

    //隐藏编辑modal
    function hideEditmodal() {
        $("#menuEditModal").modal("hide");
    }

</script>