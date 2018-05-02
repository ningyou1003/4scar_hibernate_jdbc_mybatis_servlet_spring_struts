var vm = avalon.define({
	$id : "mediaDetailCtrl",
	data : {
		keyid : "",
		title: "",
		releasetime: "",
		content : "",
	},
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
});
var opType;
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
					window.location = "mediaList.html";
			});
			$("#adopt").click(function() {
				flowApprove("media", vm.data.keyid);
			});
			$("#report").click(function() {
				flowSend("media", vm.data.keyid);
			});
			$("#fallback").click(function() {
				flowBack("media", vm.data.keyid);
			});
		},
		initData : function() {
			var request = Public.urlRequest();
			opType = request.queryString["opType"];
			var status = request.queryString["status"];
			if(opType == 1 && status != 1) {
				document.getElementById("adopt").style.display="";
				document.getElementById("report").style.display="inline";
				document.getElementById("fallback").style.display="";
			}
			if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
				vm.data.keyid = request.queryString["keyid"];
				Public.ajaxPost(Public.rootPath() + "/media/editData", "keyId="
						+ vm.data.keyid, function(json) {
					if (json.status == 001) {
						vm.data=json.data;
						$("#title").html(vm.data.title);
						$("#content").html(vm.data.content);
					} else {
						top.openeasydialog("error",json.msg);
					}
				});
			} 
		},
		initDom : function() {
		}
	};

$(document).ready(function(e) {
	THISPAGE.init();
	infoAttList("media", vm.data.keyid);//获取附件列表
});