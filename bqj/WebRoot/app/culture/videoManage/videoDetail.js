var vm = avalon.define({
	$id : "videoDetailCtrl",
	data : {
		keyid : "",
		title: "",
		releasetime: "",
		content : "",
		videourl: "",
	},
	videodata : {
		path : "",
		url : "",
	},
	typeid : "",
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
});
var opType;
var url = {};
THISPAGE = {
		init : function() {
			this.initDom();
			this.initEvent();
			this.initData();
		},
		initEvent : function() {
			$("#btback").click(function() {
				if(opType == 1)
					window.location = "../../flow/flowManage/flowList.html?status=0";
				else if(opType == 2)
					window.location = "../../flow/flowManage/flowList.html?status=1";
				else
					window.location = "videoList.html?typeid=" + vm.typeid;
			});
			$("#adopt").click(function() {
				flowApprove("video", vm.data.keyid);
			});
			$("#report").click(function() {
				flowSend("video", vm.data.keyid);
			});
			$("#fallback").click(function() {
				flowBack("video", vm.data.keyid);
			});
		},
		initData : function() {
			var request = Public.urlRequest();
			opType = request.queryString["opType"];
			var status = request.queryString["status"];
			vm.typeid = request.queryString["typeid"];
			if(opType == 1 && status != 1) {
				document.getElementById("adopt").style.display="";
				var regionid=$.cookie("regionCode");
				if (regionid!=null && regionid=='450000000000') {
					$("#report").css("display","none");
				}else {
					$("#report").css("display","");
				}
				document.getElementById("fallback").style.display="";
			}
			if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
				vm.data.keyid = request.queryString["keyid"];
				Public.ajaxPost(Public.rootPath() + "/video/detailData", "keyId="
						+ vm.data.keyid, function(json) {
					if (json.status == 001) {
						vm.data=json.data.video;
						vm.videodata= json.data.attachment[0];
						openvideo();
						$("#loading").hide();
						$("#detail").show();
						$("#title").html(vm.data.title);
//						$("#content").html(vm.data.content);
					} else {
						top.openeasydialog("error",json.msg);
					}
				});
			};
		},
		initDom : function() {
		}
	};

var filepath = vm.videodata.url;
var flashvars = {
    f: filepath,
    c: 0,
    b: 1
};

function openvideo() {
    var filepath = vm.videodata.url;
    var flashvars = {
        f: filepath,
        c: 0,
        b: 1,
    };
    var params = { bgcolor: '#FFF', allowFullScreen: true, allowScriptAccess: 'always', wmode: 'transparent' };
    CKobject.embedSWF('../../../assets/js/ckplayer/ckplayer/ckplayer.swf', 'a1', 'ckplayer_a1', '800', '450', flashvars, params);

    /*
    CKobject.embedSWF(播放器路径,容器id,播放器id/name,播放器宽,播放器高,flashvars的值,其它定义也可省略);
    下面三行是调用html5播放器用到的
    */
    var video = ['http://movie.ks.js.cn/flv/other/1_0.mp4->video/mp4', 'http://www.ckplayer.com/webm/0.webm->video/webm', 'http://www.ckplayer.com/webm/0.ogv->video/ogg'];
    var support = ['iPad', 'iPhone', 'ios', 'android+false', 'msie10+false'];
    CKobject.embedHTML5('a1', 'ckplayer_a1', 800, 450, video, flashvars, support);
};

function closelights() {//关灯
    alert(' 本演示不支持开关灯');
}
function openlights() {//开灯
    alert(' 本演示不支持开关灯');
}

$(document).ready(function(e) {
	THISPAGE.init();
//	infoAttList("vidoe", vm.data.keyid);//获取附件列表
});