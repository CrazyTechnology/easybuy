<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
            <a href="/department/toList.do" class="ajaxify">部门管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span>部门列表</span>
        </li>
    </ul>
</div>
<div class="row">
    <div class="col-md-3">
        <div class="portlet light ">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-social-dribbble font-blue-sharp"></i>
                    <span class="caption-subject font-blue-sharp bold uppercase">部门关系图</span>
                </div>
            </div>
            <div class="portlet-body">
                <div id="deptTree" class="tree-demo">

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
                    <span class="caption-subject bold uppercase">部门列表</span>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-toolbar">
                    <div class="row">
                        <div class="col-md-1">
                            <div class="btn-group">
                                <shiro:hasPermission name="depart:add">
                                <button class="btn sbold green" onclick="showModal();"> 新增
                                    <i class="fa fa-plus"></i>
                                </button>
                                </shiro:hasPermission>
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
                        <th>部门名称</th>
                        <th>上级部门</th>
                        <th>创建时间</th>
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
<div class="modal fade" id="deptModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                添加部门
                <div class="modal-body">
                    <form class="form-horizontal" id="deptInfo" novalidate="novalidate">
                        <div class="form-group">
                            <label class="control-label col-md-3">上级部门
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <select id="parentId" class="form-control select2" title="上级部门" name="parentId">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">部门名称
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="name" data-required="1" class="form-control"></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div class="col-md-offset-4 col-md-4">
                        <button class="btn btn-primary" onclick="saveDept();">确定</button>
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
        table=$("#userList")
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
                            "data": "parentName",
                            "defaultContent": "-"
                        },
                        {
                            "data": "createTime",
                            "defaultContent": "-"
                        },
                         {
                             "data" : "id",
                             "defaultContent" : "-",
                             "render" : function(data, type, full, meta) {
                                 var html = "<a href='department/toDetail.do?id="
                                         + data
                                         + "' class='btn btn-primary ajaxify'>详情</a>"
                                         + "<a href='department/toEdit.do?id="
                                         + data
                                         + "' class='btn btn-warning ajaxify'>编辑</a>"
                                         + "<a href='department/toAccountRulesDetail.do?id="
                                         + data
                                         + "' class='btn btn-danger ajaxify'>删除</a>"
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
                        "url": "/department/list.do"
                        }
                });

        //初始化jstree控件
        bindJsTree("deptTree", "/common/getDeptTree.do");
    });
    //显示添加部门窗口
    function showModal() {
        $("#deptModal").modal("show");
        $("#deptModal").on("shown.bs.modal", function () {
            //下拉框选择部门
            initSelect2($("#parentId"), $("#deptModal"), "/common/getDeptList.do", $("#parentId").attr("title"));
        })

    }

    //隐藏添加部门窗口
    function hideModal() {
        $("#deptModal").modal("hide");
    }

    function saveDept() {
        var formdata = $("#deptInfo").serialize();
        $.ajax({
            type: "post",
            url: "/department/saveDept.do",
            dataType: "json",
            async: false,
            data: formdata,
            success: function (data) {
                if (data > 0) {
                    alert("添加成功");
                }
            },
            error: function () {
                alert("error!");
            }
        });
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