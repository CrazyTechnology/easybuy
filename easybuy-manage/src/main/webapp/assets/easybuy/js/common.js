/**
 * Created by ming on 2016/12/18.
 */
/**
 * 获取url中的参数
 * @returns
 * @author kry
 */
function starGrade(ele,event){  //星级选择
    $(ele).on(event,"span",function(){
        var indexSpan=$(this).index();
        var parents=$(this).parents(".star");
        $(parents).find("span").each(function(index){
            if(index<=indexSpan){
                $(this).addClass("active");
            }else{
                $(this).removeClass("active");
            }
        });
        $(parents).attr("data-grade",indexSpan+1);
    })
}
function getUrlParam() {
    var url = window.location.search;
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        var obj = new Object();
        if(strs.length>1){
            for (i = 0; i < strs.length; i++) {
                if(strs[i].indexOf("=")<1){
                    return null;
                }
                var key = strs[i].split("=")[0]
                var value = unescape(strs[i].split("=")[1]);
                obj[key] = value;
            }
        }else{
            if(str.indexOf("=")<1){
                return null;
            }
            var key = str.split("=")[0]
            var value = unescape(str.split("=")[1]);
            obj[key] = value;
        }
        return obj;
    }
    return null;
}

/**
 * 获取cookie的值
 * @returns
 * @author kry
 */
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1)
                c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}
/**
 * 设置cookie的值
 * @author kry
 */
function setCookie(c_name, value,expiredays) {
    var exdate = new Date();
    if(expiredays==null){
        expiredays = 1;//如果未设置cookie时间，暂时设置一天超时
    }
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(value)
        + ("; expires=" + exdate.toGMTString());
}
/**
 * 设置cookie的值，时间单位为秒
 * @author kry
 */
function setCookieSeconds(c_name, value,seconds) {
    var exdate = new Date();
    if(seconds==null){
        seconds = 60;//如果未设置cookie时间，暂时设置一分钟超时
    }
    exdate.setSeconds(exdate.getSeconds() + seconds);
    document.cookie = c_name + "=" + escape(value)
        + ("; expires=" + exdate.toGMTString());
}

/**
 * 清除指定cookie
 * @author kry
 */
function delCookie(name) {// 为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间
    var date = new Date();
    date.setTime(date.getTime() - 10000);
    document.cookie = name + "=a; expires=" + date.toGMTString();
}

/**
 * 清除所有cookie
 * @author kry
 */
function clearCookie(){
    var keys=document.cookie.match(/[^ =;]+(?=\=)/g);
    if (keys) {
        for (var i =  keys.length; i--;)
            document.cookie=keys[i]+'=0;expires=' + new Date( 0).toUTCString()
    }
}

/**
 * 提示框组件，需要初始化
 * 暂时在index页面进行了初始化
 * @author kry
 */
var Confirm = {
    modalContainerId: '#modal-container',
    modalBackgroundId: '#modal-background',
    modalMainId: '#confirm-modal',
    defaultCallback:null,
    customButton: {
        '确定': {
            'primary': true,
            'callback': function() {
                if(Confirm.defaultCallback){
                    Confirm.defaultCallback();
                }
                Confirm.hide();
            }
        }
    },
    customEvent: null,

    init: function(size) {
        var self = this;
        var ElemHtml = '';

        $(self.modalMainId).remove();

        ElemHtml = '<div id="confirm-modal" class="modal fade" role="dialog" tabindex="-1">' + '<div class="modal-dialog modal-' + size + '">' + '<div class="modal-content">' + '<div class="modal-header">' + '<button id="modal-upper-close" class="close modal-close" aria-label="Close" type="button">' + '<span aria-hidden="true">×</span>' + '</button>' + '<h4 id="modal-title" class="modal-title">Modal Title</h4>' + '</div>' + '<div id="modal-body" class="modal-body"> Modal Message </div>' + '<div id="modal-footer" class="modal-footer">' + '</div>' + '</div>' + '</div>' + '</div>' + '<div id="modal-background" class=""></div>';

        $('body').append(ElemHtml);
    },

    addCustomButtons: function() {
        var self = this;
        var condition = true;

        $('.modal-custom-button').remove();

        closeButton = '';

        if (self.customButton)
            closeButton = '<button id="modal-close" type="button" class="btn btn-default modal-custom-button">取消</button>';
        else {
            self.customButton = {
                '确定': {
                    'primary': true,
                    'callback': function() {
                        if(Confirm.defaultCallback){
                            Confirm.defaultCallback();
                        }
                        Confirm.hide();
                    }
                }
            };
        }

        $.each(self.customButton, function(key, val) {
            buttonName = key.replace(/ /g, '');

            var ElemHtml = '';
            var ButtonState = 'btn-default';

            if (val['primary'])
                ButtonState = 'btn-primary';
            if (buttonName.toLowerCase() == 'okay' || buttonName.toLowerCase() == 'ok')
                closeButton = '';

            if (buttonName.toLowerCase() == 'delete' || buttonName.toLowerCase() == 'remove')
                ButtonState = 'btn-danger';

            ElemHtml = '<button id="button-' + buttonName.toLowerCase() + '" type="button" class="btn modal-custom-button ' + ButtonState + '">' + buttonName + '</button>';

            $('#modal-footer').append(ElemHtml);

            self.addCustomButtonEvents(buttonName.toLowerCase(), val['callback']);
        });
        $('#modal-footer').append(closeButton);
        $('#modal-upper-close').unbind();
        $('#modal-upper-close').bind('click', function(e) {
            e.preventDefault();
            if(self.defaultCallback){
                self.defaultCallback();
            }
            self.hide();
        });

        $('#modal-close').unbind();
        $('#modal-close').bind('click', function(e) {
            e.preventDefault();
            if(self.defaultCallback){
                self.defaultCallback();
            }
            self.hide();
        });
    },

    addCustomButtonEvents: function(customButtonId, callback) {
        var self = this;

        $('#button-' + customButtonId).unbind();
        $('#button-' + customButtonId).bind('click', function(e) {
            e.preventDefault();
            callback();
            self.hide();
        });
    },

    show: function(title, message, customEvent,defaultCallback) {
        var self = this;

        if (title)
            $('#modal-title').html(title);

        if (message)
            $('#modal-body').html(message);
        self.defaultCallback = defaultCallback;
        self.customButton = customEvent;

        $(self.modalMainId).addClass('in');
        $(self.modalBackgroundId).addClass('modal-backdrop fade in');
        $(self.modalMainId).css({
            'display': 'block',
            'padding-right': '17px'
        });
        self.addCustomButtons();
    },

    hide: function() {
        var self = this;

        $(self.modalMainId).removeClass('in');
        $(self.modalBackgroundId).removeClass('modal-backdrop fade in');

        $(self.modalMainId).css('display', 'none');
    }
};

/**
 * 自定义confirm
 * @param content 提示内容
 * @param callback 点击确认按钮后的回调函数
 * @author kry
 */
function userConfirm(content,callback,defaultCallback){
    Confirm.show('提示', content, {
        '确定' : {
            'primary' : true,
            'callback' : callback
        }
    },defaultCallback);
}

/**
 * 自定义的alert样式
 * @param content 提示内容
 * @param callback 点击提示框的回调
 * @author kry
 */
function userAlert(content,callback){
    Confirm.show('提示', content,null,callback);
}

/**
 * 初始化表格的多选框，一般在表格初始化完成后调用
 * @param obj 表格的dom或者jq对象
 * @author kry
 */
function initCheckbox(obj){
    $(obj).find(".group-checkable").change(function() {
        var set = jQuery(this).attr("data-set");
        var checked = jQuery(this).is(":checked");
        jQuery(set).each(function() {
            if (checked) {
                if(!$(this).is(":disabled")){
                    $(this).prop("checked", true);
                    $(this).parents('tr').addClass("active");
                }
            } else {
                $(this).prop("checked", false);
                $(this).parents('tr').removeClass("active");
            }
        });
        $(set).uniform();
    });
    obj.on('change', 'tbody tr .checkboxes', function () {
        $(this).parents('tr').toggleClass("active");
    });
    $(".group-checkable").uniform();
}


/**
 * 用于在不打断程序正常执行的情况下显示提示数据
 * @param config
 * @return
 */
var Toast = function(config){
    this.context = config.context==null?$('body'):config.context;//上下文
    this.message = config.message;//显示内容
    this.time = config.time==null?3000:config.time;//持续时间
    this.left = config.left;//距容器左边的距离
    this.top = config.top==null?(document.documentElement.clientHeight+document.body.scrollTop-100)+"px":config.top;//距容器上方的距离
    this.init();
}
var msgEntity;
Toast.prototype = {
    //初始化显示的位置内容等
    init : function(){
        $("#toastMessage").remove();
        $(window).unbind("scroll",scrollDiv);
        //设置消息体
        var msgDIV = new Array();
        msgDIV.push('<div id="toastMessage">');
        msgDIV.push('<span>'+this.message+'</span>');
        msgDIV.push('</div>');
        msgEntity = $(msgDIV.join('')).appendTo(this.context);
        //设置消息样式
        var left = this.left == null ? this.context.width()/2-msgEntity.find('span').width()/2 : this.left;
        var top = this.top == null ? '50px' : this.top;
        msgEntity.css({position:'absolute',top:top,'z-index':'99',left:left,'background-color':'black',color:'white','font-size':'18px',padding:'10px',margin:'10px'});
        msgEntity.hide();
    },
    //显示动画
    show :function(callback){
        $(window).scroll(scrollDiv);
        msgEntity.fadeIn(this.time/2);
        msgEntity.fadeOut(this.time/2);
        setTimeout(function(){
            $(window).unbind("scroll",scrollDiv);
            if(callback){
                callback();
            }
        },this.time);
    }
}
function scrollDiv(){
    msgEntity.css({top:(document.documentElement.clientHeight+document.body.scrollTop-100)+"px"});
}

function showAlert(content,callback){
    new Toast({message:content}).show(callback);
}


/**
 * 初始化下拉级联方法
 * @param formObj 表格的dom或者jq对象
 * @param comboObjs 第一个参数是表单对象，后面的为级联对象，不固定参数，有多少个级联，传多少个
 * @author kry
 *
 */
var cascades = [];//用来储存不同的级联组件的参数信息
function formatCascade(formObj) {
    var funParams = arguments;


    //为除了最底级的下拉列表添加 添加onchange事件————————————
    for (var i = 2; i < funParams.length; i++) {
        var initComboObj = funParams[i - 1];
        if(i == 2){
            initComboObj = funParams[funParams.length - 1];
        }
        var placeholderText = "请选择";
        if($(initComboObj).attr("title")){
            placeholderText+=$(initComboObj).attr("title");
        }
        $(initComboObj).select2({
            language: "zh-CN",
            placeholder: placeholderText,
            minimumResultsForSearch: -1,
            width:"100%",
            allowClear: true
        });

        $(funParams[i - 1]).change(
            (function(i){
                return function (e,r) {
                    if(r)return false;
                    var val = $(funParams[i - 1]).val();
                    isLoadFunc(funParams[i], val);
                    //清除此级联 下面级别的数据
                    for (var j = i; j < funParams.length; j++) {
                        if (j != i) {
                            $(funParams[j]).empty();
                            //$(funParams[j]).combobox("loadData", []);
                        }
                        //$(funParams[j]).combobox("clear");
                        $(funParams[j]).val("").trigger("change",[true]);
                    }
                }
            })(i)
        );
    }


    //把一级菜单全查出来
    initCombo(funParams[1]);
//	var exist = false;
//	isExist:for(var i = 0;i<cascades.length;i++){
//		for(var j = 1;j<cascades.length;j++){
//			for(var k = 1;k<funParams.length;k++){
//				if(cascades[i][j].selector=funParams[k].selector){
//					exist = true;
//					break isExist;
//				}
//			}
//		}
//	}
//	if($.inArray(funParams, cascades)<0){
//		cascades[cascades.length] = funParams;
//	}
    cascades[cascades.length] = funParams;
    //列表页面点击 修改数据时根据行数据 加载下拉列表数据
    $(formObj).form({onLoadSuccess:function (data) {
        for (var j = 0; j < cascades.length; j++) {
            for (var i = cascades[j].length-1; i > 1; i--) {
                var pIdVal = data[$(cascades[j][i - 1]).attr("name")];
                if(pIdVal){
                    isLoadFunc(cascades[j][i], pIdVal);
                }else{
                    var parentId = isLoadFunc(cascades[j][i], pIdVal,data[$(cascades[j][i]).attr("name")]);
                    data[$(cascades[j][i - 1]).attr("name")]=parentId;
                }
                $(cascades[j][i]).val(data[$(cascades[j][i]).attr("name")]).trigger("change",[true]);
            }
            //给一级下拉框赋值
            $(cascades[j][1]).val(data[$(cascades[j][1]).attr("name")]).trigger("change",[true]);
        }

        //加载完级联数据  如果有 就调用自定义的 表单数据加载完成的方法
        if(window[$(formObj).attr("id")+"OnLoadSuccess"]){
            window[$(formObj).attr("id")+"OnLoadSuccess"](data,formObj);
        }
    }});
}

/**
 * 判断是否有自定义加载数据方法
 * @param next 需要加载数据的combobox对象
 * @param val parentId的值
 */
function isLoadFunc(next, pval,val) {
    var funcName = $(next).attr("loadFunc");
    if (funcName) {
        //调用自定义加载方法
        window[funcName]($(next), pval,val);
    } else {
        if(!pval&&val){
            var dataParam = getDataParams(next, val);
            if (dataParam) {
                pval = getParentIdVal($(next), dataParam);
                return pval;
            } else {
                return false;
            }
        }else{
            var dataParam = getDataParams(next, pval);
            if (dataParam) {
                comboboxLoad($(next), dataParam);
            } else {
                return false;
            }
        }
    }
}

/**
 * 拼装成传到后台的参数
 * @param obj 需要加载数据的combobox对象
 * @param pIdVal parentId的值
 */
function getDataParams(obj, pIdVal) {
    var cascadeParam = splitParams(obj);
    if (cascadeParam.length == 0) {
        return null;
    }
    var pId = "";
    var descVal = "";
    var where = "";
    if (cascadeParam.length > 3) {
        pId = cascadeParam[3];
    }
    if (cascadeParam.length > 4) {
        descVal = cascadeParam[4];
    }
    if ($(obj).attr("where")) {
        where = $(obj).attr("where");
    }

    var dataP = {"id":cascadeParam[0], "name":cascadeParam[1], "tableName":cascadeParam[2], "parentId":pId, "parentIdVal":pIdVal, "desc":descVal,"whereStr":where};
    return dataP;
}

//获取table属性的参数
function splitParams(obj) {
    if($(obj).attr("dict")){
        $(obj).attr("parentIdVal",$(obj).attr("dict"));
        return ["id","item_name","td_base_dict","code"];
    }
    if($(obj).attr("dictName")){
        $(obj).attr("parentIdVal",$(obj).attr("dictName"));
        return ["item_name","item_name","td_base_dict","code"];
    }
    var param = $(obj).attr("cascade");
    if (!param) {
        console.log("id为："+$(obj).attr('id')+" 的下拉框没有写 cascade 属性");
        return [];
    }
    var params = param.split(",,");
    if (params < 3) {
        console.log("id为："+$(obj).attr('id')+" 的下拉框的 cascade 属性少写了");
        return [];
    }
    return params;
}

/**
 * 为下拉列表装载数据
 * @param obj 需要加载数据的combobox对象
 * @param dataParam 传到后台的参数
 */
function comboboxLoad(obj, dataParam) {
    var url = "api/common/getComboboxOption.do";
    $.ajax({type:"post", url:url, dataType:"json",async:false, data:dataParam, success:function (data) {
        if (data && data.code == 0) {
            var jsondata = data.data;
            $(obj).empty();
            $(obj).append("<option value=''></option>");
            $(jsondata).each(function(index){
                $(obj).append("<option value='"+this.id+"'>"+this.text+"</option>");
            });
            if(window[$(obj).attr("id")+"_onLoadOver"]){
                window[$(obj).attr("id")+"_onLoadOver"]();
            }
        }
    }, error:function () {
        alert("error");
        console.log("可能 可能是id为："+$(obj).attr('id')+" 的 cascade 属性写的有错误");
    }});
}


/**
 * 获取父级id的值根据自身的id值
 * @param obj 需要加载数据的combobox对象
 * @param dataParam 传到后台的参数
 */
function getParentIdVal(obj,dataParam) {
    var url = "api/common/getParentidVal.do";
    var result = "";
    $.ajax({type:"post", url:url, async:false, dataType:"json", data:dataParam, success:function (data) {
        if (data && data.code == 0) {
            var jsondata = data.data;
            $(obj).empty();
            $(obj).append("<option value=''></option>");
            $(jsondata).each(function(index){
                $(obj).append("<option value='"+this.id+"'>"+this.text+"</option>");
            });
            if(window[$(obj).attr("id")+"_onLoadOver"]){
                window[$(obj).attr("id")+"_onLoadOver"]();
            }
            result = jsondata[0].parentId;
        }
    }, error:function () {
        alert("error");
    }});
    return result;
}

function initCombo(combobox){
    var placeholderText = "请选择";
    if($(combobox).attr("title")){
        placeholderText+=$(combobox).attr("title");
    }
    $(combobox).select2({
        language: "zh-CN",
        placeholder: placeholderText,
        minimumResultsForSearch: -1,
        width:"100%",
        allowClear: true
    });
    var params = splitParams(combobox);
    if (params.length == 0) {
        return;
    }
    if($(combobox).attr("parentIdVal")){
        isLoadFunc(combobox,$(combobox).attr("parentIdVal"));
    }else{
        isLoadFunc(combobox,"");
    }
    if($(combobox).attr("value")){
        $(combobox).val($(combobox).attr("value")).trigger("change",[true]);
    }
}
function initCombos(comboboxs){
    $(comboboxs).each(function(){
        initCombo(this);
    });
}

function initBrandsCombo(obj){
    $(obj).select2({
        language: "zh-CN",
        placeholder: '请选择品牌',
        maximumSelectionLength:-1,
        width:"100%",
        allowClear:true,
        ajax: {
            url: "api/common/getBrands.do",
            type:"post",
            dataType: 'json',
            delay: 250,
            data: function (data) {
                return {
                    search: data.term, //查询的字符
                    page: data.page  //页码
                };
            },
            processResults: function (data,params) {
                return {results:data.result};
            }
        }
    });
}

//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑上面是下拉框级联公用方法↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

//以指定的Json数据，初始化JStree控件
//treeName为树div名称，url为数据源地址，checkbox为是否显示复选框，loadedfunction为加载完毕的回调函数
function bindJsTree(treeName, url, checkbox, loadedfunction) {
    var control = $('#' + treeName)
    control.data('jstree', false);//清空数据，必须
    var isCheck = arguments[2] || false; //设置checkbox默认值为false
    if (isCheck) {
        //复选框树的初始化
        $.getJSON(url, function(data) {
            control.jstree({
                'plugins' : [ "checkbox" ], //出现选择框
                'checkbox' : {
                    cascade : "",
                    three_state : true
                }, //不级联
                'core' : {
                    'data' : data,
                    "themes" : {
                        "responsive" : false
                    }
                }
            }).bind('loaded.jstree', loadedfunction);
        });
    } else {
        //普通树列表的初始化
        $.getJSON(url, function(data) {
            control.jstree({
                'core' : {
                    'data' : data,
                    "themes": {
                        "responsive": false
                    }
                }
            });
        });
    }
}


//初始化select2下拉框

function initSelect2(obj, modal, url, title) {
    $(obj).select2({
        language: "zh-CN",
        placeholder: '请选择' + title,
        maximumSelectionLength: -1,
        width: "100%",
        dropdownParent: $(modal),
        allowClear: true,
        ajax: {
            url: url,
            type: "post",
            dataType: 'json',
            delay: 250,
            data: function (data) {
                return {
                    search: data.term, //查询的字符
                    page: data.page  //页码
                };
            },
            processResults: function (data, params) {
                return {results: data.result};
            }
        }
    });
}