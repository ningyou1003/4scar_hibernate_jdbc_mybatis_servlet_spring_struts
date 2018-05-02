var vm = avalon.define({
	$id : "silhouetteDetailCtrl",
	data : {
		keyid : "",
		title: "",
		releasetime: "",
		content : "",
	},
	regionCode : "",
	imgdata : {},
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
					window.location = "silhouettePage.html?regionCode=" + vm.regionCode + "&year=" + vm.year;
			});
//			$("#adopt").click(function() {
//				flowApprove("silhouette", vm.data.keyid);
//			});
			$("#report").click(function() {
				flowSend("silhouette", vm.data.keyid);
			});
//			$("#fallback").click(function() {
//				flowBack("silhouette", vm.data.keyid);
//			});
		},
		initData : function() {
			
			var request = Public.urlRequest();
			opType = request.queryString["opType"];
			var status = request.queryString["status"];
			
			if(opType == 1 && status != 1) {
				
				//document.getElementById("adopt").style.display="";
				//console.log(1);
				var regionid=$.cookie("regionCode");
				//console.log(2);
				if (regionid!=null && regionid=='450000000000') {
					$("#report").css("display","none");
				}else {
					$("#report").css("display","");
				}
				//console.log(3);
				//document.getElementById("fallback").style.display="";
				//console.log(4);
			}
		
			if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
				vm.data.keyid = request.queryString["keyid"];
				vm.regionCode = request.queryString["regionCode"];
				vm.year = request.queryString["year"];
				Public.ajaxPost(Public.rootPath() + "/silhouette/detailData", "keyId="
						+ vm.data.keyid, function(json) {
					if (json.status == 001) {
						vm.data=json.data.silhouette;
						vm.imgdata = json.data.imageList;
						$("#title").html(vm.data.title);
//						$("#content").html(vm.data.content);
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
	infoAttList("silhouette", vm.data.keyid);//获取附件列表
	var regionid=$.cookie("regionCode");
	if (regionid!=null && regionid=='450000000000') {
		$("#report").css("display","none");
	}else {
		$("#report").css("display","");
	}
	var request = Public.urlRequest();
	var status = request.queryString["status"];
	if (status!=null && status==0) {
		$("#flowApproved").attr("style","");
		$("#flowApproved").click(function() {
			flowApprove("silhouette", vm.data.keyid);
			var $number=$(window.parent.parent.topFrame.document.getElementById("flow_todo_number"));
			 $number.html($number.html()-1);
		});
		$("#noflowApproved").css("display","");
		
		$("#btback").click(function() {
			 window.location = "../../flow/flowManage/flowList.html?t="+Math.random();
		});
		$("#noflowApproved").click(function() {
			readFlow(vm.data.keyid);
			var $number=$(window.parent.parent.topFrame.document.getElementById("flow_todo_number"));
			 $number.html($number.html()-1);
		});
		$("#add").css("display","none");
	}
});