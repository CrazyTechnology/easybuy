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
            <a href="/user/toUserPage.do" class="ajaxify">会员管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span>会员列表</span>
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
                    <span class="caption-subject bold uppercase">会员列表</span>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-toolbar">
                    <div class="row">
                        <div class="col-md-1">
                            <div class="btn-group">
                                <a  class="btn sbold green ajaxify" href="/user/toUserAddPage.do"> 新增
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
                        <th>姓名</th>
                        <th>性别</th>
                        <th>手机号</th>
                        <th>邮箱</th>
                        <th>地址</th>
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
<script type="text/javascript">
    $(function(){
        table=$("#userList")
        table.DataTable({
            "language" : {
                url : "assets/global/plugins/datatables/datatable_i18n_cn.json"
            },
            columns: [
                        {
                            "data": "username",
                            "defaultContent": "-"
                        },
                        {
                            "data": "sex",
                            "defaultContent": "-"
                        },
                        {
                            "data": "phone",
                            "defaultContent": "-"
                        },
                        {
                            "data": "email",
                            "defaultContent": "-"
                        },{
                            "data": "address",
                            "defaultContent": "-"
                        },
                         {
                             "data" : "id",
                             "defaultContent" : "-",
                             "render" : function(data, type, full, meta) {
                                 var html="<a href='user/toDetail.do?id="
                                         + data
                                         + "' class='btn btn-primary ajaxify'>详情</a>"
                                         +"<a href='user/toEdit.do?id="
                                         + data
                                         + "' class='btn btn-warning ajaxify'>编辑</a>"
                                         + "<button onclick='deleteById(\"" + data + "\");' class='btn btn-danger' >删除</button>";
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
                    "autoWidth" : true,//自动计算列宽
                    "ajax": {
                        "dataSrc": "data",
                        "type": "post",
                        "url": "user/list.do"
                        }
                });
    });

    function deleteById(id) {
        //删除用户
        $.ajax({
            type: "post",
            url: "/user/deleteUser.do",
            dataType: "json",
            async: false,
            data: {"id": id},
            success: function (data) {
                if (data > 0) {
                    userAlert("删除成功！");
                    table.DataTable().draw()
                } else {
                    userAlert("删除失败！");
                }
            },
            error: function () {
                alert("error!");
            }
        });
    }
</script>