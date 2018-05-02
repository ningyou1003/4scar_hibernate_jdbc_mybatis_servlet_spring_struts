var htmlRootPath = getRootPath();
var a;
document.write("<link href=\"" + htmlRootPath + "/assets/css/style.css\" rel=\"stylesheet\" type=\"text/css\" />");
document.write("<script language='javascript' src='" + htmlRootPath + "/assets/js/libs/jquery-1.11.2.min.js' charset='utf-8'></script>");
document.write("<script language='javascript' src='" + htmlRootPath + "/assets/ui/layer/layer.min.js' charset='utf-8'></script>");
document.write("<script language='javascript' src='" + htmlRootPath + "/assets/js/jquery.cookie.js' charset='utf-8'></script>");
document.write("<script language='javascript' src='" + htmlRootPath + "/assets/js/plugins/page/page.js' charset='utf-8'></script>");
document.write("<script language='javascript' src='" + htmlRootPath + "/assets/js/select-ui.min.js' charset='utf-8'></script>");
document.write("<script language='javascript' src='" + htmlRootPath + "/assets/js/plugins/kindeditor/kindeditor-all.js' charset='utf-8'></script>");
document.write("<script language='javascript' src='" + htmlRootPath + "/assets/js/plugins/validator/jquery.validator.js' charset='utf-8'></script>");
document.write("<script language='javascript' src='" + htmlRootPath + "/assets/js/plugins/validator/local/zh_CN.js' charset='utf-8'></script>");
document.write("<link href=\"" + htmlRootPath + "/assets/js/plugins/validator/jquery.validator.css\" rel=\"stylesheet\" type=\"text/css\" />");
document.write("<script language='javascript' src='" + htmlRootPath + "/assets/js/avalon/avalon.js' charset='utf-8'></script>");
window.onload = function () {
    if (parent.document.getElementById("navtab1") != undefined && parent.document.getElementById("navtab1") != null) {
        a = parent.document.getElementById("navtab1").lastChild.lastChild;
        // changeDivHeight();
        top.changeDivHeight(a - 182);
    }

    if (parent.parent.document.getElementById("navtab1") != undefined && parent.parent.document.getElementById("navtab1") != null) {
        a = parent.parent.document.getElementById("navtab1").lastChild.lastChild;
        // changeDivHeight();
        top.changeDivHeight(a - 182);
    }

};
// 当浏览器窗口大小改变时，设置显示内容的高度
window.onresize = function () {
    if (a != null && a != undefined) {
        top.changeDivHeight(a - 182);
    }
};

function getRootPath() {
    // 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    // 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    // 获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}
var Business = Business || {};
var Public = Public || {};

Public.isIE6 = !window.XMLHttpRequest; // ie6

Public.urlRequest = function () {
    var apiMap = {};
    function request(queryStr) {
        var api = {};
        if (apiMap[queryStr]) {
            return apiMap[queryStr];
        }
        api.queryString = (function () {
            var urlParams = {};
            var e, d = function (s) {
                return decodeURIComponent(s.replace(/\+/g, " "));
            }, q = queryStr.substring(queryStr.indexOf('?') + 1), r = /([^&=]+)=?([^&]*)/g;
            while (e = r.exec(q))
                urlParams[d(e[1])] = d(e[2]);
            return urlParams;
        })();
        api.getUrl = function () {
            var url = queryStr.substring(0,
					queryStr.indexOf('?') + 1);
            for (var p in api.queryString) {
                url += p + '=' + api.queryString[p] + "&";
            }
            if (url.lastIndexOf('&') == url.length - 1) {
                return url.substring(0, url.lastIndexOf('&'));
            }
            return url;
        }
        apiMap[queryStr] = api;
        return api;
    }
    $.extend(request, request(window.location.href));
    return request;
}

Public.dateCheck = function () {
    $('.ui-datepicker-input').bind('focus', function (e) {
        $(this).data('original', $(this).val());
    }).bind('blur', function (e) {
        var reg = /((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/;
        var _self = $(this);
        setTimeout(function () {
            if (!reg.test(_self.val())) {
                parent.Public.tips({ type: 1, content: '日期格式有误！如：2013-08-08。' });
                _self.val(_self.data('original'));
            };
        }, 10)

    });
}
Public.rootPath = function () {
//    return "http://192.168.1.77:8080/bqj";
//    return "http://www.imindsoft.com:456/bqj_test";
	return "http://222.216.1.196:38229/bqj";
};
Public.localPath = function () {
//    return "http://192.168.1.77:8081";
	return "http://222.216.1.196:38229/bqj";
};
/* 获取URL参数值 */
Public.getRequest = Public.urlParam = function () {
    var param, url = location.search, theRequest = {};
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0, len = strs.length; i < len; i++) {
            param = strs[i].split("=");
            theRequest[param[0]] = decodeURIComponent(param[1]);
        }
    }
    return theRequest;
};
/*
* 通用post请求，返回json url:请求地址， params：传递的参数{...}， callback：请求成功回调
*/
Public.ajaxPost = function (url, params, callback, errCallback) {
    var mb = myBrowser(); //浏览器版本
    if ("FF" == mb) {//Firefox浏览器
        $.ajax({
            type: "POST",
            url: url,
            data: params,
            dataType: "json",
            beforeSend: function () {
                layer.load('加载中...', 10);
            },
            complete: function () {
                layer.closeAll();
            },
            //解决火狐浏览器请求失败问题，将withCredentials配置写进beforeSend函数，2016-08-25
            /*  xhrFields: {
            withCredentials: true
            }, */
            beforeSend: function (xhr) {
                xhr.withCredentials = true;
            },
            //crossDomain:true,
            success: function (data, status) {
                if (data.status == 300) {
                    top.openeasydialog("error", data.msg, function () {
                        top.location.reload();
                    });
                } else {
                    callback(data);
                }
            },
            error: function (err) {
                //            top.openeasydialog("error", "操作失败了哦，请检查您的网络链接！");
            },
            async: false
        });
    }
    else {
        $.ajax({
            type: "POST",
            url: url,
            data: params,
            dataType: "json",
            beforeSend: function () {
                layer.load('加载中...', 10);
            },
            complete: function () {
                layer.closeAll();
            },
            //解决火狐浏览器请求失败问题，将withCredentials配置写进beforeSend函数，2016-08-25
            xhrFields: {
                withCredentials: true
            },
            /* beforeSend: function (xhr) {
            xhr.withCredentials = true;
            },*/
            //crossDomain:true,
            success: function (data, status) {
                if (data.status == 300) {
                    top.openeasydialog("error", data.msg, function () {
                        top.location.reload();
                    });
                } else {
                    callback(data);
                }
            },
            error: function (err) {
                //            top.openeasydialog("error", "操作失败了哦，请检查您的网络链接！");
            }
        });
    }

};
Public.ajaxGet = function (url, params, callback, errCallback) {
    Public.ajaxPost(url, params, callback, errCallback);
};
/* 操作提示 */
Public.tips = function (options) { return new Public.Tips(options); }
Public.Tips = function (options) {
    var defaults = {
        renderTo: 'body',
        type: 0,
        autoClose: true,
        removeOthers: true,
        time: undefined,
        top: 10,
        onClose: null,
        onShow: null
    }
    this.options = $.extend({}, defaults, options);
    this._init();
    !Public.Tips._collection ? Public.Tips._collection = [this] : Public.Tips._collection.push(this);
}

Public.Tips.removeAll = function () {
    try {
        for (var i = Public.Tips._collection.length - 1; i >= 0; i--) {
            Public.Tips._collection[i].remove();
        }
    } catch (e) { }
}

Public.Tips.prototype = {
    _init: function () {
        var self = this, opts = this.options, time;
        if (opts.removeOthers) {
            Public.Tips.removeAll();
        }

        this._create();

        if (opts.autoClose) {
            time = opts.time || opts.type == 1 ? 5000 : 3000;
            window.setTimeout(function () {
                self.remove();
            }, time);
        }

    },

    _create: function () {
        var opts = this.options, self = this;
        if (opts.autoClose) {
            this.obj = $('<div class="ui-tips"><i></i></div>').append(opts.content);
        } else {
            this.obj = $('<div class="ui-tips"><i></i><span class="close"></span></div>').append(opts.content);
            this.closeBtn = this.obj.find('.close');
            this.closeBtn.bind('click', function () {
                self.remove();
            });
        };

        switch (opts.type) {
            case 0:
                this.obj.addClass('ui-tips-success');
                break;
            case 1:
                this.obj.addClass('ui-tips-error');
                break;
            case 2:
                this.obj.addClass('ui-tips-warning');
                break;
            default:
                this.obj.addClass('ui-tips-success');
                break;
        }

        this.obj.appendTo('body').hide();
        this._setPos();
        if (opts.onShow) {
            opts.onShow();
        }

    },
    _setPos: function () {
        var self = this, opts = this.options;
        if (opts.width) {
            this.obj.css('width', opts.width);
        }
        var h = this.obj.outerHeight(), winH = $(window).height(), scrollTop = $(window).scrollTop();
        // var top = parseInt(opts.top) ? (parseInt(opts.top) + scrollTop) :
        // (winH > h ? scrollTop+(winH - h)/2 : scrollTop);
        var top = parseInt(opts.top) + scrollTop;
        this.obj.css({ position: Public.isIE6 ? 'absolute' : 'fixed', left: '50%', top: top, zIndex: '9999', marginLeft: -self.obj.outerWidth() / 2 });
        window.setTimeout(function () {
            self.obj.show().css({ marginLeft: -self.obj.outerWidth() / 2 });
        }, 150);

        if (Public.isIE6) {
            $(window).bind('resize scroll', function () {
                var top = $(window).scrollTop() + parseInt(opts.top);
                self.obj.css('top', top);
            })
        }
    },

    remove: function () {
        var opts = this.options;
        this.obj.fadeOut(200, function () {
            $(this).remove();
            if (opts.onClose) {
                opts.onClose();
            }
        });
    }
};
// 数值显示格式转化
Public.numToCurrency = function (val, dec) {
    val = parseFloat(val);
    dec = dec || 2; // 小数位
    if (isNaN(val)) {
        return '0.00';
    }
    val = val.toFixed(dec).split('.');
    var reg = /(\d{1,3})(?=(\d{3})+(?:$|\D))/g;
    return val[0].replace(reg, "$1,") + '.' + val[1];
};
// 数值显示
Public.currencyToNum = function (val) {
    var val = String(val);
    if ($.trim(val) == '') {
        return 0;
    }
    val = val.replace(/,/g, '');
    val = parseFloat(val);
    return isNaN(val) ? 0 : val;
};
/* 批量绑定页签打开 */
Public.pageTab = function () {
    $(document).on('click', '[rel=pageTab]', function (e) {
        e.preventDefault();
        var right = $(this).data('right');
        if (right && !Business.verifyRight(right)) {
            return false;
        };
        var tabid = $(this).attr('tabid'), url = $(this).attr('href'), showClose = $(this).attr('showClose'), text = $(this).attr('tabTxt') || $(this).text(), parentOpen = $(this).attr('parentOpen');
        if (parentOpen) {
            parent.tab.addTabItem({ tabid: tabid, text: text, url: url, showClose: showClose });
        } else {
            parent.tab.addTabItem({ tabid: tabid, text: text, url: url, showClose: showClose });
        }
    });
};

Public.getDefaultPage = function () {
    var win = window.self;
    do {
        if (win.CONFIG) {
            return win;
        }
        win = win.parent;
    } while (true);
};

// 权限验证
Business.verifyRight = function (right) {
    var system = Public.getDefaultPage().SYSTEM;
    var isAdmin = system.user.isAdmin;
    var siExperied = system.isexpired;
    var rights = system.rights;
    if (isAdmin && !siExperied) {
        return true;
    };

    if (siExperied) {
        var html = [
				'<div class="ui-dialog-tips">',
				'<p>谢谢您使用本产品，您的当前服务已经到期，到期3个月后数据将被自动清除，如需继续使用请购买/续费！</p>',
				'<p style="color:#AAA; font-size:12px;">(续费后请刷新页面或重新登录。)</p>',
				'</div>'
			].join('');
        $.dialog({ width: 280, title: '系统提示', icon: 'alert.gif', fixed: true, lock: true, resize: false, ok: true, content: html });
        return false;
    } else {
        return true; // 权限判断
        if (rights.indexOf(right) > 0) {
            return true;
        } else {
            var html = [
				'<div class="ui-dialog-tips">',
				'<h4 class="tit">您没有该功能的使用权限哦！</h4>',
				'<p>请联系管理员为您授权！</p>',
				'</div>'
			].join('');
            $.dialog({ width: 240, title: '系统提示', icon: 'alert.gif', fixed: true, lock: true, resize: false, ok: true, ontent: html });
            return false;
        }
    };
};
/** 用户下拉框 */
Public.userCombo = function ($_obj, opts, type) {
    if ($_obj.length == 0) { return };
    var opts = $.extend(true, {
        data: function () {
            return rootPath + '/sso/user/list.json' + (type ? "?type=" + type : "");
        },
        ajaxOptions: {
            formatData: function (data) {
                return data.data.list;
            }
        },
        formatText: function (data) {
            return data.userno + ' ' + data.real_name;
        },
        value: 'id',
        defaultSelected: -1,
        editable: true,
        // extraListHtml: '<a href="javascript:void(0);" id="quickAddGoods"
        // class="quick-add-link"><i class="ui-icon-add"></i>新增商品</a>',
        maxListWidth: 500, cache: false, forceSelection: true, maxFilter: 10, trigger: false, listHeight: 182,
        listWrapCls: 'ui-droplist-wrap',
        callback: {
            onChange: function (data) {
                alert(data);
            },
            onListClick: function () {
            }
        },
        queryDelay: 0, inputCls: 'edit_subject', wrapCls: 'edit_subject_wrap', focusCls: '', disabledCls: '', activeCls: ''
    }, opts);
    return $_obj.combo(opts).getCombo();
};

//判断浏览器类型
function myBrowser() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1;
    if (isOpera) {
        return "Opera"
    }; //判断是否Opera浏览器
    if (userAgent.indexOf("Firefox") > -1) {
        return "FF";
    } //判断是否Firefox浏览器
    if (userAgent.indexOf("Chrome") > -1) {
        return "Chrome";
    }
    if (userAgent.indexOf("Safari") > -1) {
        return "Safari";
    } //判断是否Safari浏览器
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
        return "IE";
    }; //判断是否IE浏览器
}

Public.getNowFormatDate = function () {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}

/*
* 兼容IE8 数组对象不支持indexOf() create by guoliang_zou ,20140812
* 
* if(!Array.prototype.indexOf) { Array.prototype.indexOf = function(elt , from ) {
* var len = this.length >>> 0; var from = Number(arguments[1]) || 0; from =
* (from < 0) ? Math.ceil(from) : Math.floor(from); if (from < 0) from += len;
* for (; from < len; from++) { if (from in this && this[from] === elt) return
* from; } return -1; }; }
*/