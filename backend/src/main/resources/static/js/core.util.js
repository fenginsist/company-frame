/*CoreUtil*/
/*工具类，类似java静态工具类*/
var CoreUtil = (function () {
    var coreUtil = {};
    /*ajax请求*/
    coreUtil.sendAjax = function (url, params, func, method, headers, noAuthorityFunc, async, contentType) {
        var roleSaveLoading = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        layui.jquery.ajax({
            url: url,
            cache: false,
            async: async == undefined ? true : async,
            data: params,
            type: method == undefined ? "POST" : method,
            contentType: contentType == undefined ? 'application/json; charset=UTF-8' : contentType,
            dataType: "json",
            // 添加 请求头
            beforeSend: function (request) {
                if (headers == undefined) {
                    // headers 为空时，请求头为 空 ，headers 为 undefined
                } else if (headers) {
                    request.setRequestHeader("authorization", CoreUtil.getData("access_token"));
                    request.setRequestHeader("refresh_token", CoreUtil.getData("refresh_token"));
                } else {
                    request.setRequestHeader("authorization", CoreUtil.getData("access_token"));
                }
            },
            // 成功回调函数
            /*success: function (res) {
                top.layer.close(roleSaveLoading);
                if (typeof func == "function") {
                    if (res.code == 0) {
                        if (func != null && func != undefined) {
                            func(res);
                        }
                    } else {
                        layer.msg(res.msg)
                    }
                }
            },*/
            success: function (res) {
                top.layer.close(roleSaveLoading);
                if (typeof func == "function") {
                    if (res.code == 4010001) { // 凭证过期重新登录
                        layer.msg(res.msg);
                        // layer.msg("凭证过期请重新登录")
                        top.window.location.href = "/index/login"
                    } else if (res.code == 4010002) { //根据后端提示刷新token
                        /* 记录要重复刷新的参数 */
                        var reUrl = url;
                        var reParams = params;
                        var reFunc = func;
                        var reMethod = method;
                        var reHeaders = headers;
                        var reContentType = contentType;
                        var reAsync = async;
                        var reNoAuthorityFunc = noAuthorityFunc;
                        /*刷新token 然后存入缓存*/
                        CoreUtil.sendAjax("/api/user/token", null, function (res) {
                            if (res.code == 0) {
                                CoreUtil.setData("access_token", res.data);
                                /* 刷新成功后继续重复请求 */
                                CoreUtil.sendAjax(reUrl, reParams, reFunc, reMethod, reHeaders, reNoAuthorityFunc, reContentType, reAsync);
                            } else {
                                layer.msg("凭证过期请重新登录");
                                top.window.location.href = "/index/login"
                            }
                        }, "GET", true)
                    } else if (res.code == 0) {// 成功的数据
                        if (func != null && func != undefined) {
                            func(res);
                        }
                    } else if (res.code == 4030001){
                        if (noAuthorityFunc != null && noAuthorityFunc != undefined) {
                            noAuthorityFunc(res);
                        }
                    } else {
                        layer.msg(res.msg)
                    }
                }
            },
            // 失败回调函数
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                top.layer.close(roleSaveLoading);
                if (XMLHttpRequest.status == 404) {
                    top.window.location.href = "/index/404";
                } else {
                    layer.msg("服务器好像除了点问题！请稍后试试");
                }
            }
        });
    };

    /*存入本地缓存*/
    coreUtil.setData = function (key, value) {
        layui.sessionData('LocalData', {
            key: key,
            value: value
        })
    };

    /*从本地缓存拿数据*/
    coreUtil.getData = function (key) {
        var localData = layui.sessionData('LocalData');
        return localData[key];
    };

    /*格式化时间格式*/
    coreUtil.formattime = function (val) {
        if (val == null || val == undefined) {
            return "";
        }
        var date = new Date(val);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        month = month > 9 ? month : ('0' + month);
        var day = date.getDate();
        day = day > 9 ? day : ('0' + day);
        var hh = date.getHours();
        hh = hh > 9 ? hh : ('0' + hh);
        var mm = date.getMinutes();
        mm = mm > 9 ? mm : ('0' + mm);
        var ss = date.getSeconds();
        ss = ss > 9 ? ss : ('0' + ss);
        var time = year + '-' + month + '-' + day + ' ' + hh + ':' + mm + ':' + ss;
        return time;
    };
    return coreUtil;
})(CoreUtil, window);

/*
参数说明
url:要请求的接口地址
params:传递给后端的数据
func：请求响应成功回掉方法(function)
method:请求的方式例如 GET/POST/PUT/DELETED 等
async：是否是异步请求；async的默认方式是true,即异步方式；async设置为false时,为同步方式
contentType：类型编码；默认为：application/json; charset=UTF-8
* */