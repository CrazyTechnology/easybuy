<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="icon-home"></i>
            <a href="/index.do">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a href="/user/toUserPage.do" class="ajaxify">会员管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <span>会员新增</span>
        </li>
    </ul>
</div>
<div class="portlet light ">
    <div class="portlet-title">
        <div class="caption">
            <i class="icon-equalizer font-red-sunglo"></i>
            <span class="caption-subject font-red-sunglo bold uppercase">会员新增</span>
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form id="add_form"  class="form-horizontal" >
            <input type="hidden" name="photo" id="photo_url">
            <div class="form-body">
                <div class="form-group">
                    <label class="control-label col-md-3">用户姓名
                        <span class="required" aria-required="true"> * </span>
                    </label>
                    <div class="col-md-4">
                        <input type="text" name="name" data-required="1" placeholder="请输入用户姓名" class="form-control"> </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">手机号
                        <span class="required" aria-required="true"> * </span>
                    </label>
                    <div class="col-md-4">
                        <input type="text" name="phone" data-required="1" placeholder="请输入手机号" class="form-control"> </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">邮箱</label>
                    <div class="col-md-4">
                        <div class="input-group">
                             <span class="input-group-addon">
                                <i class="fa fa-envelope"></i>
                             </span>
                            <input type="text" name="email" class="form-control" placeholder="请输入邮箱"> </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">生日</label>
                    <div class="col-md-4">
                        <div class="input-group date date-picker" id="time" data-date-format="yyyy-mm-dd">
                            <input type="text" class="form-control" id="birthday"  name="birthday" readonly>
                            <span class="input-group-btn">
                                <button class="btn default" type="button">
                                    <i class="fa fa-calendar"></i>
                                </button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">居住地址
                        <span class="required"> *</span>
                    </label>
                    <div class="col-md-4">
                        <select class="form-control select2" name="address">
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">密码</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="password" name="password" class="form-control" placeholder="请输入密码">
                            <span class="input-group-addon">
                                <i class="fa fa-user"></i>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">上传头像</label>
                    <div class="col-md-4">
                        <input type="file"  class="file-loading" name="photo"  id="photo">
                    </div>
                </div>

            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-md-offset-3 col-md-4">
                        <button type="button" id="ok" class="btn green">提交</button>
                        <a  href="/user/toUserPage.do" class="btn default ajaxify">取消</a>
                    </div>
                </div>
            </div>
        </form>
        <!-- END FORM-->
    </div>
</div>
<script type="text/javascript">
    $('#time').datepicker({
        format:"yyyy-mm-dd",
        language: 'zh-CN'
    });
    $("#photo").fileinput({
        language:'zh',
        showUpload:false,
        showRemove:false,
        allowedFileExtensions: ['jpg','gif','png'],//接收的文件后缀
        enctype:"multipart/form-data",
        uploadUrl: "upload/userLogo.do", // server upload action
        maxFileCount: 1
    });
    $("#photo").fileinput().on('fileuploaded', function(event, data, previewId, index) {
       $("#photo_url").val(data.response.url);
    });

    $('select').select2({
        language: "zh-CN",
        placeholder: '请选择城市',
        maximumSelectionLength:-1,
        width:"100%",
        allowClear:true,
        ajax: {
            url: "common/getProvince.do",
            type:"post",
            dataType: 'json',
            delay: 250,
            data: function (data) {
                return {
                    search: data.term, //查询的字符
                };
            },
            processResults: function (data,params) {
                return {results:data.result};
            }
        }
    });

    $("#ok").click(function () {
       var formData= $("#add_form").serialize();
        $.ajax({
            type: "post",
            url: "/user/addUser.do",
            dataType: "text",
            async: false,
            data: formData,
            success: function (data) {
                if (data > 0) {
                    showAlert("新增会员成功");
                    ajaxHref("user/list.do");
                } else {
                    showAlert("新增会员失败");
                    ajaxHref("user/list.do");
                }

            },
            error: function () {
                alert("error!");
            }
        });
    });
    function showAlert(content,callback){
        new Toast({message:content}).show(callback);
    }
    var Toast = function(config){
        this.context = config.context==null?$('body'):config.context;//上下文
        this.message = config.message;//显示内容
        this.time = config.time==null?3000:config.time;//持续时间
        this.left = config.left;//距容器左边的距离
        this.top = config.top==null?(document.documentElement.clientHeight+document.body.scrollTop-100)+"px":config.top;//距容器上方的距离
        this.init();
    }
    function ajaxHref(url){
        var pageContentBody = $('.page-content .page-content-body');
        App.scrollTop();
        App.startPageLoading();
        $.ajax({
            type: "GET",
            cache: false,
            url: url,
            dataType: "html",
            success: function (res) {
                App.stopPageLoading();
                pageContentBody.html(res);
                Layout.fixContentHeight(); // fix content height
                App.initAjax(); // initialize core stuff
            },
            error: function (xhr, ajaxOptions, thrownError) {
                App.stopPageLoading();
                pageContentBody.html('<h4>Could not load the requested content.</h4>');
            }
        });
    }

</script>