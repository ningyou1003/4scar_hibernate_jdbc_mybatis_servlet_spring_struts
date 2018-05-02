
//JS操作cookies方法!
//写cookies

 function newSetCookie(key, value, options) {

    // key and value given, set cookie...
    if (arguments.length > 1 && (value === null || typeof value !== "object")) {
        options = jQuery.extend({}, options);

        if (value === null) {
            options.expires = -1;
        }

        if (typeof options.expires === 'number') {
            var days = options.expires, t = options.expires = new Date();
            t.setDate(t.getDate() + days);
        }

        return (document.cookie = [
            encodeURIComponent(key), '=',
            options.raw ? String(value) : encodeURIComponent(String(value)),
            options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
            options.path ? '; path=' + options.path : '',
            options.domain ? '; domain=' + options.domain : '',
            options.secure ? '; secure' : ''
        ].join(''));
    }

    // key and possibly options given, get cookie...
    options = value || {};
    var result, decode = options.raw ? function (s) { return s; } : decodeURIComponent;
    return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
};

function setCookie(name,value)
{
var Days = 30;
var exp = new Date();
exp.setTime(exp.getTime() + Days*24*60*60*1000);
document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/;domain=";
}

function SetCookie(name, value, expires, path, domain, secure) {
	alert(1);
	 var today = new Date();
	 today.setTime(today.getTime());
	 if(expires) { expires *= 86400000; }
	 var expires_date = new Date(today.getTime() + (expires));
	 document.cookie = name + "=" + escape(value)
	  + (expires ? ";expires=" + expires_date.toGMTString() : "")
	  + (path ? ";path=" + path : "")
	  + (domain ? ";domain=" + domain : "")
	  + (secure ? ";secure" : "");
	 document.cookie = "secure; path=/";
	}
function getCookie(name)
{
var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
if(arr=document.cookie.match(reg))
return unescape(arr[2]);
else
return null;
}
function delCookie(name)
{
var exp = new Date();
exp.setTime(exp.getTime() - 1);
var cval=getCookie(name);
if(cval!=null)
document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
//使用示例
//setCookie("name","hayden");
//alert(getCookie("name"));
//如果需要设定自定义过期时间
//那么把上面的setCookie　函数换成下面两个函数就ok;
//程序代码
function setCookie(name,value,time)
{
var strsec = getsec(time);
var exp = new Date();
exp.setTime(exp.getTime() + strsec*1);
document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getsec(str)
{
//alert(str);
var str1=str.substring(1,str.length)*1;
var str2=str.substring(0,1);
if (str2=="s")
{
return str1*1000;
}
else if (str2=="h")
{
return str1*60*60*1000;
}
else if (str2=="d")
{
return str1*24*60*60*1000;
}
}
//这是有设定过期时间的使用示例：
//s20是代表20秒
//h是指小时，如12小时则是：h12
//d是天数，30天则：d30
//setCookie("name","hayden","d1");