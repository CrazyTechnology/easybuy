<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>京东购物车</title>
    <link rel="shortcut icon" href="img/tit_ico.ico"/>
    <link rel="stylesheet" type="text/css" href="css/shoping.css">

    <style>
        * {
            margin: 0;
            padding: 0;
            color: #666;
            font-size: 12px;
        }

        ul, ol {
            list-style: none;
        }

        a {
            text-decoration: none;
            color: #000;
        }

        .cen {
            margin: 0 auto;
        }

        /*.fr{float:right;}*/
        .fl {
            float: left;
        }

        .dlbj {
            width: 100%;
            height: 104px;
        }

        .dlbj .dlts {
            width: 948px;
            height: 25px;
            padding: 10px 20px;
            border: 1px solid green;
            background: #FFFDEE;
        }

        .dlbj .dlts .text {
            width: 948px;
            height: 25px;
        }

        .dlbj .dlts .text .jg {
            width: 16px;
            height: 16px;
            background: url('images/dlts/1.png') no-repeat 1px 1px;
            margin-left: -362px;
        }

        .dlbj .dlts .text .ms {
            width: 345px;
            height: 23px;
            font-size: 13px;
            line-height: 23px;
            text-align: center;
            margin-left: -342px;
            color: orange;
        }

        .dlbj .dlts .text .bt {
            width: 68px;
            height: 25px;
            font-size: 12px;
            line-height: 25px;
            text-align: center;
            background: red;
            margin-left: 5px;
            border-radius: 10%;
            color: #FFF;
            cursor: pointer;
        }

        .dlbj .yf {
            width: 990px;
            height: 26px;
            margin-top: 13px;
        }

        .dlbj .yf .yf1 {
            width: 80px;
            height: 26px;
            text-align: center;
            line-height: 26px;
            float: left;
            margin-right: 20px;
        }

        .dlbj .yf .yf2 {
            width: 82px;
            height: 26px;
            text-align: center;
            line-height: 26px;
            float: left;
        }

        .dlbj .yf .yf1 a {
            font-size: 16px;
            color: red;
        }

        .dlbj .yf .yf2 a {
            font-size: 16px;
        }

        .dlbj .yf .sd {
            width: 201px;
            height: 26px;
            float: left;
            margin-left: 607px;
        }

        .dlbj .yf .sd .ps {
            width: 48px;
            height: 26px;
            text-align: left;
            line-height: 25px;
            float: left;
        }

        .dlbj .yf .sd .mine-area {
            width: 150px;
            height: 24px;
            float: left;
            border: 1px solid #666;
            overflow: hidden;
        }

        .dlbj .yf .sd .mine-area input {
            width: 150px;
            height: 24px;
            border: none;
        }


    </style>

</head>

<body>
<!-- 头部 -->
<div class="hbj cen">
    <div class="header cen">
        <div class="song fl"><a href="">送至：北京 <b>></b></a>
            <div class="baitiao"></div>
            <div class="dis">
                <ul class="dis-dis">
                    <li><a id="beijing" href="">北京</a></li>
                    <li><a href="">上海</a></li>
                    <li><a href="">天津</a></li>
                    <li><a href="">重庆</a></li>
                    <li><a href="">河北</a></li>
                    <li><a href="">河南</a></li>
                    <li><a href="">江苏</a></li>
                    <li><a href="">湖南</a></li>
                    <li><a href="">甘肃</a></li>
                    <li><a href="">西安</a></li>
                    <li><a href="">广州</a></li>
                    <li><a href="">深圳</a></li>
                    <li><a href="">山东</a></li>
                    <li><a href="">大连</a></li>
                    <li><a href="">云南</a></li>
                    <li><a href="">浙江</a></li>
                    <li><a href="">河北</a></li>
                    <li><a href="">河南</a></li>
                    <li><a href="">江苏</a></li>
                    <li><a href="">湖南</a></li>
                    <li><a href="">甘肃</a></li>
                    <li><a href="">西安</a></li>
                    <li><a href="">广州</a></li>
                    <li><a href="">深圳</a></li>
                    <li><a href="">山东</a></li>
                    <li><a href="">大连</a></li>
                    <li><a href="">云南</a></li>
                    <li><a href="">浙江</a></li>
                    <li><a href="">湖南</a></li>
                    <li><a href="">甘肃</a></li>
                    <li><a href="">西安</a></li>
                    <li><a href="">广州</a></li>

                </ul>
            </div>
        </div>
        <ul class="ever fr">
            <li><a class="" href="">你好，请登录</a></li>
            <li><a class="zhuce" href="">免费注册</a></li>
            <div class="shugang"></div>
            <li><a class="" href="">我的订单</a></li>
            <div class="shugang"></div>
            <li class="li"><a class="myjd" href="">我的京东 <b>></b></a>
                <div class="baitiao"></div>
                <div class="myjingdong">
                    <div class="mjd_top">
                        <div class="touxiang fl"><img src="img/jd/wdjd.jpg"></div>
                        <div class="please fl">
                            <p class="s1">你好，请登录</p>
                            <span class="s2">优惠券</span>
                            <span class="s3">|</span>
                            <span class="s4">消息</span>
                        </div>
                    </div>
                    <div class="mjd_under">
                        <div class="list1">
                            <p class="li1">待处理订单</p>
                            <p class="li1">咨询回复</p>
                            <p class="li1">降价商品</p>
                            <p class="li1">返修退换货</p>
                        </div>
                        <div class="list1">
                            <p class="li1">我的京东</p>
                            <p class="li1">我的京豆</p>
                            <p class="li1">我的理财</p>
                            <p class="li1">京东白条</p>
                        </div>

                    </div>
                </div>

            </li>
            <div class="shugang"></div>
            <li><a class="" href="">京东会员</a></li>
            <div class="shugang"></div>
            <li><a class="" href="">企业采购</a></li>
            <div class="shugang"></div>
            <li class="li">
                <a class="ponejd" href="">
                    <div class="img fl"><img src="img/xiaotu/tubiao.jpg"></div>
                    手机京东 <b>></b>
                </a>
                <div class="phone"><img src="img/jd/ponejd.jpg"></div>

            </li>
            <div class="shugang"></div>
            <li class="li"><a class="carejd" href="">关注京东
                <b>></b></a>
                <div class="phone"><img class="image" src="img/jd/carejd.jpg"></div>
            </li>
            <div class="shugang"></div>
            <li class="li"><a class="sever" href="">客户服务 <b>></b></a>
                <div class="baitiao"></div>
                <div class="khfw">
                    <p class="kehu">客户</p>
                    <p class="ls">
                        <span class="l">帮助中心</span>
                        <span class="l">售后服务</span>
                    </p>
                    <p class="ls">
                        <span class="l">在线客服</span>
                        <span class="l">意见建议</span>
                    </p>
                    <p class="ls">
                        <span class="l">客服邮箱</span>
                    </p>
                    <p class="shhu">商户</p>
                    <p class="ls">
                        <span class="l">京东商学院</span>
                        <span class="l">商家入驻</span>
                    </p>
                </div>

            </li>
            <div class="shugang"></div>
            <li class="li"><a class="net" href="">网站导航 <b>></b></a>
                <div class="baitiao"></div>
                <div class="images"><img class="image" src="img/jd/net.jpg"></div>
            </li>

        </ul>
    </div>
</div>


<div class="caozuo cen">
    <div class="left fl">
        <img src="img/xiaotu/jd.jpg" class="jd">
    </div>
    <div class="midder fl">
        <div class="sousuo">
            <input type="text" value="自营">
            <button>搜索</button>
        </div>
    </div>
</div>
<!-- 登录提示 -->
<div class="dlbj">
    <div class="dlts cen">
        <div class="text">
            <div class="jg fl"></div>
            <div class="ms fl">您还没有登录！登录后购物车的商品将保存到您账号中</div>
            <div class="bt fl">立即登录</div>
        </div>
    </div>
    <div class="yf cen">
        <div class="yf1 fl"><a href="">全部商品 4</a></div>
        <div class="yf2 fl"><a href="">京东大药房</a></div>
        <div class="sd">
            <div class="ps">配送至:</div>
            <div class="mine-area"><input id="city" value="甘肃省平凉市"></div>
        </div>
    </div>
</div>
<!-- 全部商品 -->
<div class="midbj">
    <div class="mid cen">
        <div class="mid1">
            <div class="a1 fl">
                <div class="input fl">
                    <input id="kz" type="checkbox" name="love[]">
                </div>
                <div class="qx fl">全选</div>
            </div>
            <div class="a2 fl">商品</div>
        </div>
        <div class="zybj">
            <div class="zy cen">
                <div class="input fl ">
                    <input type="checkbox" name="love[]">
                </div>
                <div class="zyan fl">京东自营</div>
                <div class="zyr fl"> 钻石会员满￥79.00免运费，其他会员满￥99.00免运费
                </div>
            </div>
        </div>
        <div class="zytbj">
            <div class="zyt cen">
                <div class="input1 fl">
                    <input type="checkbox" name="love[]" id="in">
                </div>
                <div class="zyti fl">
                    <ul>
                        <li class="a fl"><a href="xq.html"><img src="images/font/1.jpg" alt=""></a></li>
                        <li class="b fl"><a href="">联想（ThinkPad）轻薄系列E450(20DCA07JCD)14英寸笔记本电脑(i3-5005U 4G 500G 2G独显
                            win</a></li>
                        <li class="c fl"></li>
                        <li class="d fl "><a href="">增值保障</a></li>
                    </ul>
                </div>
                <div class="zytm fl">
                    <div class="on">颜色：小新Air12【人气畅销版</div>
                </div>
                <div class="zytn fl">
                    <div class="fi">3299.00</div>
                    <div class="tw">
                        <div class="t1 fl">促销优惠</div>
                        <!-- <div class="t2 fl">＞</div> -->
                    </div>
                </div>
                <div class="zyto fl">
                    <div class="z1 fl">-</div>
                    <input type="text" value="1" class="z2 fl">
                    <div class="z3 fl">+</div>
                    <div class="z4 ">有货</div>
                </div>
                <div class="zytp fl">
                    <input type="text" value="3358.00" id="danji">
                </div>
                <div class="zytq fl">
                    <div class="z5">删除</div>
                    <div class="z6"><a href=""> 移到我的关注</a></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="jsbj">
    <div class="js cen">
        <div class="js1 fl">
            <input type="checkbox" name="love[]" id="zk">
            <div class="js1_1 fl">全选</div>
        </div>
        <div class="js2 fl"><a href="">删除选中的商品</a></div>
        <div class="js3 fl"><a href="">移到我的关注</a></div>
        <div class="js4 fl"><a href="">清除下柜商品</a></div>
        <div class="js5 fl">
            <div class="js6 fl">
                <div class="sj1 fl">已有选择的商品</div>
                <div class="sj2 fl">></div>
            </div>
            <div class="sjb">
                <div class="sjb1">普通商品1件</div>
                <div class="sjb2">
                    <div class="sjb3 fl"></div>
                    <div class="sjb4 fl"><img src="images/font/1.jpg" alt=""></div>
                    <div class="sjb5 fl"></div>
                </div>

            </div>

            <div class="js7 fl">
                <div class="y1">
                    <div class="y3 fl">总价:</div>
                    <div class="y4 fl">
                        <input type="text" value="3358" love="[]" id="zong">
                    </div>
                </div>
                <div class="y2">
                    <div class="y5 fl">已节省：</div>
                    <div class="y6 fl">￥0.00</div>
                </div>
            </div>
            <div class="js8 fl">去结算</div>
        </div>
    </div>
</div>

<div class="cnbj">
    <div class="cn cen">
        <div class="cn1 fl"><a href="">猜你喜欢</a></div>
        <div class="cn2 fl"><a href="">我的关注</a></div>
        <div class="cn3 fl"><a href="">最近浏览</a></div>
        <div class="cn4 fl">
            <ul id="ul">
                <li class="bg"></li>
                <li></li>
                <li></li>
            </ul>
        </div>
    </div>
</div>

<div class="zhbj">
    <div class="zh cen">
        <button class="prev">&lt;</button>
        <button class="next">&gt;</button>
        <div class="zh1 fl">
            <ul>
                <li class="sh"><img src="images/cn/1.jpg" alt=""></li>
                <li class="zh">thinkPad 31P7410 USB光电鼠标</li>
                <li class="jg">￥39.00</li>
            </ul>
            <div class="xi">
                <div class="l fl"></div>
                <div class="r fl">加入购物车</div>
            </div>
        </div>
        <div class="zh2 fl">
            <ul>
                <li class="o1"><img src="images/cn/2.jpg" alt=""></li>
                <li class="o2">联想（LenovoK5819 超薄 巧克力键盘 经久耐用 防泼溅设计 黑色</li>
                <li class="o3">￥69.00</li>
            </ul>
            <div class="o4">
                <div class="l1 fl"></div>
                <div class="r1 fl">加入购物车</div>
            </div>
        </div>
        <div class="zh3 fl">
            <ul>
                <li class="p1"><img src="images/cn/3.jpg" alt=""></li>
                <li class="p2">戴尔（DELL）MS111光电鼠标FWCMD USB</li>
                <li class="p3">￥39.90</li>
            </ul>
            <div class="p4">
                <div class="l1 fl"></div>
                <div class="r1 fl">加入购物车</div>
            </div>
        </div>
        <div class="zh4 fl">
            <ul>
                <li class="q1"><img src="images/cn/4.jpg" alt=""></li>
                <li class="q2">联想鼠标有线m120电脑鼠标有线usb光电 通用鼠标 台式机笔记本USB鼠标</li>
                <li class="q3">￥29.90</li>
            </ul>
            <div class="q4">
                <div class="l1 fl"></div>
                <div class="r1 fl">加入购物车</div>
            </div>
        </div>
    </div>
</div>


<!-- 页尾链接 -->

<div class="wei-banner cen">
    <ul class="banner cen">
        <li><img src="img/xiaotu/duo.jpg"></li>
        <li><img src="img/xiaotu/kuai.jpg"></li>
        <li><img src="img/xiaotu/hao.jpg"></li>
        <li><img src="img/xiaotu/sheng.jpg"></li>
    </ul>
</div>
<div class="sever cen">
    <ul>
        <li>购物指南</li>
        <li><a class="a" href="">购物流程</a></li>
        <li><a class="a" href="">会员介绍</a></li>
        <li><a class="a" href="">生活旅行/团购</a></li>
        <li><a class="a" href="">常见问题</a></li>
        <li><a class="a" href="">大家电</a></li>
        <li><a class="a" href="">联系客服</a></li>
    </ul>
    <ul>
        <li>配送方式</li>
        <li><a class="a" href="">上门自提</a></li>
        <li><a class="a" href="">211限时达</a></li>
        <li><a class="a" href="">配送服务查询</a></li>
        <li><a class="a" href="">配送费收取标准</a></li>
        <li><a class="a" href="">海外配送</a></li>

    </ul>
    <ul>
        <li>支付方式</li>
        <li><a class="a" href="">货到付款</a></li>
        <li><a class="a" href="">在线支付</a></li>
        <li><a class="a" href="">分期付款</a></li>
        <li><a class="a" href="">邮局汇款</a></li>
        <li><a class="a" href="">公司转账</a></li>

    </ul>
    <ul>
        <li>售后服务</li>
        <li><a class="a" href="">售后政策</a></li>
        <li><a class="a" href="">价格保护</a></li>
        <li><a class="a" href="">退款说明</a></li>
        <li><a class="a" href="">返修/退换货</a></li>
        <li><a class="a" href="">取消订单</a></li>

    </ul>
    <ul>
        <li>特色服务</li>
        <li><a class="a" href="">夺宝岛</a></li>
        <li><a class="a" href="">DIY装机</a></li>
        <li><a class="a" href="">延保服务</a></li>
        <li><a class="a" href="">京东E卡</a></li>
        <li><a class="a" href="">京东通信</a></li>
        <li><a class="a" href="">京东JD+</a></li>
    </ul>
    <ul>
        <li>京东自营覆盖区县</li>
        <p>京东已向全国2654个区县提供自营配送服务，支持货到付款、POS机刷卡和售后上门服务。</p>

        <li><a class="a" href="">查看详情></a></li>
    </ul>
</div>

<div class="links cen">
    <div class="lj cen">
        <a href="">关于我们</a>
        <a class="a" href="">联系我们</a>
        <a class="a" href="">联系客服</a>
        <a class="a" href="">商家入驻</a>
        <a class="a" href="">营销中心</a>
        <a class="a" href="">手机京东</a>
        <a class="a" href="">友情链接</a>
        <a class="a" href="">销售联盟</a>
        <a class="a" href="">京东社区</a>
        <a class="a" href="">京东公益</a>
        <a class="a" href="">English Site</a>
        <a class="a" href="">Contact Us</a>
    </div>
    <div class="lianjie cen">
        <a class="c" href=""><img src="img/xiaotu/guohui.jpg"> 京公网安备 11000002000088号</a>
        <a class="b" href="">京ICP证070359号</a>
        <a class="a" href="">互联网出版许可证编号新出网证(京)字150号</a>
        <a class="a" href="">出版物经营许可证</a>
        <a class="b" href="">新出发京零 字第大120007号
            互联网出版许可证编号新出网证(京)字150号</a>
        <a class="a" href="">出版物经营许可证</a>
        <a class="a" href="">网络文化经营许可证京网文[2014]2148-348号</a>
        <a class="b" href=""> 违法和不良信息举报电话：4006561155
            Copyright © 2004 - 2016 京东JD.com 版权所有</a>
        <a class="b" href="">Copyright © 2004 - 2016 京东JD.com 版权所有 | 消费者维权热线：4006067733</a>
        <a class="b" href="">京东公益</a>
        <a class="b" href="">京东旗下网站：<a class="c" href="">京东钱包</a></a>
    </div>
    <div class="bottom-banner cen">
        <a href=""><img src="img/xiaotu/s1.jpg"></a>
        <a href=""><img src="img/xiaotu/s2.jpg"></a>
        <a href=""><img src="img/xiaotu/s3.jpg"></a>
        <a href=""><img src="img/xiaotu/s4.jpg"></a>
        <a href=""><img src="img/xiaotu/s5.jpg"></a>
        <a href=""><img src="img/xiaotu/s6.jpg"></a>
    </div>
</div>


</body>


<script src="js/jquery.min.js"></script>
<script src="js/jquery-migrate-1.1.0.min.js"></script>


<script src="js/shop.js"></script>
<script src="js/Popt.js"></script>
<script src="js/cityJson.js"></script>
<script src="js/citySet.js"></script>

</html>