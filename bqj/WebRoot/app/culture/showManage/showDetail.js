var vm = avalon.define({
	$id : "showDetailCtrl",
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
					window.location = "showList.html";
			});
			$("#adopt").click(function() {
				flowApprove("show", vm.data.keyid);
			});
			$("#report").click(function() {
				flowSend("show", vm.data.keyid);
			});
			$("#fallback").click(function() {
				flowBack("show", vm.data.keyid);
			});
		},
		initData : function() {
			var request = Public.urlRequest();
			opType = request.queryString["opType"];
			var status = request.queryString["status"];
			if(opType == 1 && status != 1) {
				document.getElementById("adopt").style.display="";
				
				var regionid=$.cookie("regionCode");
				if (regionid!=null && regionid=='450000000000') {
					$("#report").css("display","none");
				}else {
					$("#report").css("display","inline");
				}
				document.getElementById("fallback").style.display="";
			}
			if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
				vm.data.keyid = request.queryString["keyid"];
				Public.ajaxPost(Public.rootPath() + "/show/editData", "keyId="
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
	infoAttList("show", vm.data.keyid);//获取附件列表
});