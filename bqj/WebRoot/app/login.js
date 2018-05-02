//var serverRootPath = "http://www.imindsoft.com:456/bqj";
var serverRootPath = "http://192.168.1.77:8080/bqj";
//var serverRootPath = "http://192.168.1.78:8081/zsjcode";
var codeUrl = serverRootPath + "/captcha?random=";

var model = avalon.define({
	$id : "loginCtrl",
	data : {
		userno : "",
		pwd : "",
		validCode : "",
		randomCodeKey : "",
		autoLogin : ""
	},
	password : "",
	errMsg : "",
	vcUrl : codeUrl,
	vcV : false,
	submit : function() {

		if ($("#CheckBox1").prop("checked")) {// 如果选择中记住密码，
			model.data.autoLogin = true;
		}
		model.errMsg = "";
		if (model.data.userno == "") {
			model.errMsg = "帐号不能为空！";
			return;
		}
		if (model.data.pwd == "") {
			model.errMsg = "密码不能为空！";
			return;
		}
		if (model.data.validCode == "") {
			model.errMsg = "验证码不能为空！";
			return;
		}
		Public.ajaxPost(serverRootPath + '/login', model.data.$model, function(
				data) {
			if (data.success == false) {
				$("#ImageCheck").click();
				model.data.validCode = "";
				model.data.pwd = "";
				model.errMsg = data.msg;
			} else {
				var orgCookie = data.data.zsj_org_cookie;
				if(orgCookie!=null && orgCookie!="" && orgCookie!=undefined){
					$.cookie('zsj_org_cookie', orgCookie, {path: '/'});
				}
												
				$.cookie('zsj_cookie', data.data.zsj_cookie, {path: '/'});
				$.cookie('zsj_root_cookie', data.data.zsj_root_cookie, {path: '/'});
				$.cookie('autoLogin', data.data.autoLogin, {path: '/'});
				$.cookie('pagesize', data.data.pagesize, {path: '/'});
				//添加用户区域id
				$.cookie('regionCode', data.data.regionCode, {path: '/'});
				$.cookie('areaLevel', data.data.areaLevel, {path: '/'});
				var cookieString = $.cookie('zsj_cookie');
				if (cookieString != '' && cookieString != null
						&& cookieString != undefined) {
					window.location = "index.html";
				}
			}
		});
	}
});

THISPAGE = {
	init : function() {
		this.initEvent();

		model.data.randomCodeKey = Math.random();
		$("#ImageCheck").attr('src', model.vcUrl + model.data.randomCodeKey);
	},
	initEvent : function() {
		$("#ImageCheck").click(function() {
			model.data.randomCodeKey = Math.random();
			$("#ImageCheck").attr('src', model.vcUrl + model.data.randomCodeKey);
		});
		$("#RefImageCheck").click(function() {
			model.data.randomCodeKey = Math.random();
			$("#ImageCheck").attr('src', model.vcUrl + model.data.randomCodeKey);
		});
		$("#pwd").keyup(function() {
			if (event.keyCode == 13) {
				model.submit();
			}
		});
		$("#userno").keyup(function() {
			if (event.keyCode == 13) {
				model.submit();
			}
		});
		$("#userno").click(function(){
			model.data.userno = "";
		});
		$("#validCode").keyup(function() {
			if (event.keyCode == 13) {
				model.submit();
			}
		});
		$("#validCode").click(function(){
			model.data.validCode = "";
		});
	}
};

function changeDivSize(ele){       
	var x = window.parent.document.getElementById("leftFrame").clientWidth;//frame宽度
    var y = document.documentElement.clientHeight;//获取页面可见高度  
    
	var topHeight = document.getElementById("lefttop").clientHeight; 
	ele.style.height=(y-topHeight) + "px";  
    ele.style.width=x + "px";  
    /* var oDiv = document.getElementById("navtab1");
    var a = oDiv.lastChild.firstChild;
    a.style.size = h + "px";  */   
}

//n-m的随机整数
function suijiint(n,m){
	var w=m-n;
	w=w*Math.random();
	w=Math.floor(w);
	return n+w;
}


    
        

$(document).ready(function() {
	
	THISPAGE.init();
	$(".loginpwd").focus(function(){  
        if($(this).attr('id')=='pwd1'){  
            $(this).hide();  
            $('#pwd').show();  
            $('#pwd').focus();  
        }  
    }); 
	
	
	
});
