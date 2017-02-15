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
            <a href="/employee/toList.do" class="ajaxify">员工管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span>员工列表</span>
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
                    <span class="caption-subject bold uppercase">员工列表</span>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-toolbar">
                    <div class="row">
                        <div class="col-md-1">
                            <div class="btn-group">
                                <button class="btn sbold green" onclick="modalShow();"> 新增
                                    <i class="fa fa-plus"></i>
                                </button>
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
                        <th>姓名</th>
                        <th>性别</th>
                        <th>联系方式</th>
                        <th>部门</th>
                        <th>角色</th>
                        <th>登录名</th>
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
<div class="modal fade" id="deptUserModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                添加部门员工
                <div class="modal-body">
                    <form class="form-horizontal" id="deptUserInfo" novalidate="novalidate">
                        <div class="form-group">
                            <label class="control-label col-md-3">所属部门
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <select id="deptId" class="form-control select2" name="deptId" title="所属部门">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">用户角色
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <select id="roleId" class="form-control select2" name="roleId" title="用户角色">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">姓名：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="name" data-required="1" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">手机号：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="tel" name="tel" data-required="1" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">登录名：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="loginName" data-required="1" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">密码：
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="password" name="password" data-required="1" class="form-control"></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div class="col-md-offset-4 col-md-4">
                        <button class="btn btn-primary" onclick="saveDeptUser();">确定</button>
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
    $(function(){
        table = $("#userList");
        table.DataTable({
            "language" : {
                url : "assets/global/plugins/datatables/datatable_i18n_cn.json"
            },
            columns: [
                        {
                            "data": "name",
                            "defaultContent": "-"
                        },
                        {
                            "data": "sex",
                            "render": function (data) {
                                var html = "";
                                if (data == 0) {
                                    html = "女";
                                } else {
                                    html = "男";
                                }
                                return html;
                            },
                            "defaultContent": "-"
                        },
                {
                    "data": "tel",
                    "defaultContent": "-"
                },
                        {
                            "data": "deptName",
                            "defaultContent": "-"
                        },
                        {
                            "data": "roleName",
                            "defaultContent": "-"
                        },{
                    "data": "loginName",
                            "defaultContent": "-"
                        },
                         {
                             "data" : "id",
                             "defaultContent" : "-",
                             "render" : function(data, type, full, meta) {
                                 var html = "<a href='employee/toDetail.do?id="
                                         + data
                                         + "' class='btn btn-primary ajaxify'>详情</a>"
                                         + "<a href='employee/toEdit.do?id="
                                         + data
                                         + "' class='btn btn-warning ajaxify'>编辑</a>"
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
                    "autoWidth" : true,//自动计算列宽
                    "ajax": {
                        "dataSrc": "data",
                        "type": "post",
                        "url": "/employee/list.do"
                        }
                });
    });


    //显示模态窗口
    function modalShow() {
        $("#deptUserModal").modal("show");
        $("#deptUserModal").on("shown.bs.modal", function () {
            initSelect2($('#deptId'), $("#deptUserModal"), "/common/getDeptList.do", $('#deptId').attr("title"));
            initSelect2($('#roleId'), $("#deptUserModal"), "/common/getRoleList.do", $('#roleId').attr("title"));
        })
    }

    //隐藏添加部门窗口
    function hideModal() {
        $("#deptUserModal").modal("hide");
    }

    //保存部门用户信息
    function saveDeptUser() {
        var formdata = $("#deptUserInfo").serialize();
        $.ajax({
            type: "post",
            url: "/employee/saveDeptUser.do",
            dataType: "json",
            async: false,
            data: formdata,
            success: function (data) {
                debugger;
                if (data > 0) {
                    $("#deptUserModal").modal("hide");
                    table.DataTable().draw();
                }
            },
            error: function () {
                alert("error!");
            }
        });
    }



    //删除用户详情
    function deleteUser(userId) {
        alert(userId);
    }
</script>
<style>

    /*select2在Bootstrap的modal中默认被遮盖，现在强制显示在最前*/
    .select2-drop {
        z-index: 10050 !important;
    }

    .select2-search-choice-close {
        margin-top: 0 !important;
        right: 2px !important;
        min-height: 10px;
    }

    .select2-search-choice-close:before {
        color: black !important;
    }

    /*防止select2不会自动失去焦点*/
    .select2-container {
        z-index: 16000 !important;
    }

    .select2-drop-mask {
        z-index: 15990 !important;
    }

    .select2-drop-active {
        z-index: 15995 !important;
    }
</style>