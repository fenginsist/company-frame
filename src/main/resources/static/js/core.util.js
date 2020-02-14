/*CoreUtil*/
/*工具类，类似java静态工具类*/
var CoreUtil = (function () {
    var coreUtil = {};
    /*ajax请求*/
    coreUtil.sendAjax = function (url, params, ft, method, headers, async, contentType) {
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
                    // headers 为空时，请求头为空
                } else if (headers) {
                    request.setRequestHeader("authorization", CoreUtil.getData("access_token"));
                    request.setRequestHeader("refresh_token", CoreUtil.getData("refresh_token"));
                } else {
                    request.setRequestHeader("authorization", CoreUtil.getData("access_token"));
                }
            },
            // 成功回调函数
            success: function (res) {
                top.layer.close(roleSaveLoading);
                if (typeof ft == "function") {
                    if (res.code == 0) {
                        if (ft != null && ft != undefined) {
                            ft(res);
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
ft：请求响应成功回掉方法(function)
method:请求的方式例如 GET/POST/PUT/DELETED 等
async：是否是异步请求；async的默认方式是true,即异步方式；async设置为false时,为同步方式
contentType：类型编码；默认为：application/json; charset=UTF-8
* */