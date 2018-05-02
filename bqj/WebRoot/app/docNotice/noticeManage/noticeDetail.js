var vm = avalon.define({
	data : {
		keyid : "",
		title : "",
		content : "",
		sourceflowid:""
	},
});

THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "noticeList.html";
		});
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/docNotice/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					$("#div1_title").append(vm.data.title);
					$("#div1_content").append(vm.data.content);
					allenrol();
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		}

	},
	initDom : function() {
	}
};

function allenrol(){
	$("#enrols").html("");
	var sourceflowid=vm.data.sourceflowid;
	if(sourceflowid==null){
		sourceflowid=vm.data.keyid;
	}
	Public.ajaxPost(Public.rootPath()+ "/enrol/all", 
			{flowid:sourceflowid},
			function(json) {
		if (json.status == 001) {
			for ( var i = 0; i < json.data.length; i++) {
			var html="";
			html+="<tr><td >"+json.data[i].name+"</td>";
			if (json.data[i].sex==0) {
				json.data[i].sex="男";
			}else {
				json.data[i].sex="女";
			}
			html+="<td >"+json.data[i].sex+"</td>";
			html+="<td >"+json.data[i].peoples+"</td>";
			html+="	<td >"+json.data[i].phone+"</td>";
			html+="	<td >"+json.data[i].duty+"</td>";
			html+="	<td >"+json.data[i].transportation+"</td>";
			html+="	<td >"+json.data[i].arrivetime+"</td>";
			html+="	<td >"+json.data[i].arrivesite+"</td>";
			html+="	<td >"+json.data[i].ps+"</td><tr>";
			$("#enrols").append(html);
			}
			
		} else {
			top.openeasydialog("error",
					name+"报名异常");
		}
		
		
	});
}

$(document).ready(function(e) {
	THISPAGE.init();
	infoAttList("d_notice", vm.data.keyid);
	
});
