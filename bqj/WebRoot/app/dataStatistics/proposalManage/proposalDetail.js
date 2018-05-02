var vm = avalon.define({
	$id : "proposalDetailCtrl",
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
					window.location = "proposalList.html";
			});
			$("#adopt").click(function() {
				flowApprove("proposal", vm.data.keyid);
			});
			$("#report").click(function() {
				flowSend("proposal", vm.data.keyid);
			});
			$("#fallback").click(function() {
				flowBack("proposal", vm.data.keyid);
			});
		},
		initData : function() {
			var request = Public.urlRequest();
			opType = request.queryString["opType"];
			var status = request.queryString["status"];
			if(opType == 1 && status != 1) {
				document.getElementById("adopt").style.display="";
				document.getElementById("report").style.display="";
				document.getElementById("fallback").style.display="";
			}
			if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
				vm.data.keyid = request.queryString["keyid"];
				Public.ajaxPost(Public.rootPath() + "/proposal/detailData", "keyId="
						+ vm.data.keyid, function(json) {
					if (json.status == 001) {
						vm.data=json.data.proposal;
						
						$("#title").html(vm.data.title);
						
						$("#content").html(vm.data.content);
						$("#content").html($("#content").html()+json.data.attachment[0].preview);
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
	infoAttList("proposal", vm.data.keyid);//获取附件列表
});