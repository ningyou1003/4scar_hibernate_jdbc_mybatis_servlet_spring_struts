
var vm = avalon.define({
	$id : "userDetailCtrl",
	user : {
		keyid : "",
		username : "",
		relaname : "",
		password : "",
		regionname : "",// 2015.04.10
		regionid : "",// 2015.04.10
		rolesid : "",
		rolesname : "",
		mobile : "",
		email : "",
	}
});

THISPAGE = {
	init : function() {
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "userList.html";
		});
	},
	initData : function() {
		var request = Public.urlRequest();
		vm.user.keyid = request.queryString["keyid"];	
			Public.ajaxPost(Public.rootPath() + "/user/userDetail", "keyid="
					+ vm.user.keyid, function(json) {
				if (json.status == 001) {
					vm.user=json.data.user;
					vm.user.rolesname = json.data.role.name;
				} else {
					window.parent.parent.parent.parent.openeasydialog("warn",
							json.msg);
				}
			});
		

	}
};

$(document).ready(function() {
	THISPAGE.init();
});
