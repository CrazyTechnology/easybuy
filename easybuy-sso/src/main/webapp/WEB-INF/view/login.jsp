<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>京东-欢迎登录</title>
    <link rel="icon" href="//www.jd.com/favicon.ico"/>
    <link type="text/css" rel="stylesheet" href="/css/login.css"/>
    <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
<div class="w">
    <div id="logo">
        <a href="http://localhost:8081/index.do" clstag="passport|keycount|login|01">
            <img src="//misc.360buyimg.com/lib/img/e/logo-201305-b.png" alt="京东" width="170" height="60"/>
        </a><b></b>
    </div>
</div>
<form id="formlogin" method="post" onsubmit="return false;">
    <div class=" w1" id="entry">
        <div class="mc " id="bgDiv">
            <div id="entry-bg" clstag="passport|keycount|login|02" style="width: 511px; height: 455px; position: absolute; left: -44px; top: -44px; background: url(/images/544a11d3Na5a3d566.png) 0px 0px no-repeat;">
            </div>
            <div class="form ">
                <div class="item fore1">
                    <span>用户名</span>
                    <div class="item-ifo">
                        <input type="text" id="loginname" name="username" class="text"  tabindex="1" autocomplete="off"/>
                        <div class="i-name ico"></div>
                        <label id="loginname_succeed" class="blank invisible"></label>
                        <label id="loginname_error" class="hide"><b></b></label>
                    </div>
                </div>
                <script type="text/javascript">
                    setTimeout(function () {
                        if (!$("#loginname").val()) {
                            $("#loginname").get(0).focus();
                        }
                    }, 0);
                </script>
                <div id="capslock"><i></i><s></s>键盘大写锁定已打开，请注意大小写</div>
                <div class="item fore2">
                    <span>密码</span>
                    <div class="item-ifo">
                        <input type="password" id="nloginpwd" name="password" class="text" tabindex="2" autocomplete="off"/>
                        <div class="i-pass ico"></div>
                        <label id="loginpwd_succeed" class="blank invisible"></label>
                        <label id="loginpwd_error" class="hide"></label>
                    </div>
                </div>
                <div class="item login-btn2013">
                    <input type="button" class="btn-img btn-entry" id="loginsubmit" value="登录" tabindex="8" clstag="passport|keycount|login|06"/>
                </div>
            </div>
        </div>
        <div class="free-regist">
            <span><a href="/sso/toRegister.do" clstag="passport|keycount|login|08">免费注册&gt;&gt;</a></span>
        </div>
    </div>
</form>
<div id="footer-2013">
    <div class="links"><a href="http://www.jd.com/intro/about.aspx" target="_blank" rel="nofollow">关于我们</a>|<a href="http://www.jd.com/contact/" target="_blank" rel="nofollow">联系我们</a>|<a href="http://zhaopin.jd.com/" target="_blank" rel="nofollow">人才招聘</a>|<a href="http://www.jd.com/contact/joinin.aspx" target="_blank" rel="nofollow">商家入驻</a>|<a href="http://jzt.jd.com" target="_blank" rel="nofollow">营销中心</a>|<a href="http://app.jd.com/" target="_blank" rel="nofollow">手机淘淘</a>|<a href="http://club.jd.com/links.aspx" target="_blank">友情链接</a>|<a href="http://media.jd.com/" target="_blank">销售联盟</a>|<a target="_blank" href="http://club.jd.com/">淘淘社区</a>|<a target="_blank" href="http://gongyi.jd.com">淘淘公益</a>|<a target="_blank" href="http://en.jd.com/">English Site</a></div>
</div>
<script type="text/javascript">
    var redirectUrl = "${redirect}";
    var LOGIN = {
        checkInput:function() {
            if ($("#loginname").val() == "") {
                alert("用户名不能为空");
                $("#loginname").focus();
                return false;
            }
            if ($("#nloginpwd").val() == "") {
                alert("密码不能为空");
                $("#nloginpwd").focus();
                return false;
            }
            return true;
        },
        doLogin:function() {
            $.post("/user/login", $("#formlogin").serialize(),function(data){
                if (data.status == 200) {
                    alert("登录成功！");
                    if (redirectUrl == "") {
                        location.href = "http://localhost:8082";
                    } else {
                        location.href = redirectUrl;
                    }
                } else {
                    alert("登录失败，原因是：" + data.msg);
                    $("#loginname").select();
                }
            });
        },
        login:function() {
            if (this.checkInput()) {
                this.doLogin();
            }
        }

    };
    $(function(){
        $("#loginsubmit").click(function(){
            LOGIN.login();
        });
    });
</script>
</body>
</html>