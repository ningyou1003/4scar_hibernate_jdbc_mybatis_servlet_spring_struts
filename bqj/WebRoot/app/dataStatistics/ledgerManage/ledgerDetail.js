var vm = avalon.define({
	$id : "ledgerDetailCtrl",
	data : {
		keyid : "",
		title: "",
		releasetime: "",
	},
	contnet : {
		preview : "",
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
					window.location = "ledgerList.html";
			});
			$("#adopt").click(function() {
				flowApprove("ledger", vm.data.keyid);
			});
			$("#report").click(function() {
				flowSend("ledger", vm.data.keyid);
			});
			$("#fallback").click(function() {
				flowBack("ledger", vm.data.keyid);
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
				Public.ajaxPost(Public.rootPath() + "/ledger/detailData", "keyid="
						+ vm.data.keyid, function(json) {
					if (json.status == 001) {
						vm.data=json.data.ledger;
						vm.content = json.data.attachment[0];
						$("#title").html(vm.data.title);
						$("#content").html(vm.content.preview);
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
	infoAttList("ledger", vm.data.keyid);//获取附件列表
});