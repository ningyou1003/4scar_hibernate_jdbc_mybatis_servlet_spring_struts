var vm = avalon.define({
	$id : "useraddCtrl",
	data : {
		keyid : "",
		username : "",
		relaname : "",
		regionname : "",// 2015.04.10
		regionid : "",// 2015.04.10
		rolesid : "",
		rolesname : "",
		mobile : "",
		address : "",
		email : "",
		password : "",
		repeatpassword : ""
	},
	Role : {},//可供选择的角色
	selectedRole : "",
	regioncode : "",
	rolesId : "",
	password : {
		id : "",
		password : "",
		newpassword : "",
		repeatnewpassword : ""
	},
	submitPassword : function() {
		Public.ajaxPost(Public.rootPath() + "/user/editPassword",
				vm.password.$model, function(json) {
					if (json.status == 001) {
						top.openeasydialog("success", json.msg);
					} else {
						top.openeasydialog("error", json.msg);
					}
				});
	},
	submit : function() {
		$("#base_form").trigger("validate");
	},
	roleDialog : null
});

THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			document.getElementById("pwd").style.display = "none";
			document.getElementById("rpwd").style.display = "none";
			document.getElementById("pwdChange").style.display = "";
			vm.password.id = vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/user/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data = json.data.user;
					vm.selectedRole = json.data.role.name;
					vm.roleId = json.data.role.keyid;
					vm.Role = json.data.roleList;
				} else {
					top.openeasydialog("error", json.msg);
				}
			});
		} else {
			Public.ajaxGet(Public.rootPath() + "/user/info",{}, function(json) {
				vm.data.regionid = json.data.regionid;
			});
		}

	},
	initDom : function() {
		document.getElementById('UserName').focus();
	},
	initEvent : function() {

		$("#btback").click(function() {
			window.location = "userList.html";
		});
		$("#region").click(
				function() {				
					top.openbuttondialog("选择区域", 400, 400,
							"system/areaManage/regionTreePick.html", false,
							function(item, dialog) {
								vm.data.regionid= dialog.frame.$(
										"#regioncode").val();
								vm.data.regionname = dialog.frame.$("#region")
										.val();
								vm.data.level = dialog.frame.$("#operlevel").val();
								dialog.close();
								$("#region").isValid();
								vm.data.rolesname = "";
								vm.data.rolesid = "";
								Public.ajaxGet(Public.rootPath() + "/role/loadRoleByLevel",{
				            		"roleLevel" : vm.data.level
				            	}, function(json) {
				    				vm.Role = json.data;
				    				if(vm.Role.length>0){
				    					vm.selectedRole = vm.Role[0].name;
				    				}
				    			});
							});
				});
	}
};

function postData() {
	var url = null;
	if (vm.data.keyid != "" && vm.data.keyid != null
			&& vm.data.keyid != undefined) {
		url = Public.rootPath() + "/user/edit";
	} else {
		url = Public.rootPath() + "/user/add";
	}
	for(var i=0;i<vm.Role.length;i++){
		if(vm.selectedRole==vm.Role[i].name){
			vm.rolesId = vm.Role[i].keyid;
		}
	}
	Public.ajaxPost(url, {
		 'user.keyId': vm.data.keyid,
         'user.username': vm.data.username,
         'user.relaname': vm.data.relaname,
         'user.regionid': vm.data.regionid,
         'user.mobile': vm.data.mobile,
         'user.email': vm.data.email,
         'user.password': vm.data.password,
         'rolesId': vm.rolesId
	}, function(json) {
		if (json.status == 001) {
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/system/userManage/userList.html";
			});
		} else {
			top.openeasydialog("error", json.msg);
		}
	});

}
function initValidator() {
	$("#base_form").validator({
		messages : {
			required : "请填写{0}",
			match: {
                eq: "两次输入密码不一致"
            }
		},
		display : function(e) {
			var text = $(e).closest(".row-item").find("label").text().trim();
			return text.substring(0, text.length - 2);
		},
		valid : function() {
			postData();
		},
		ignore : ":hidden",
		theme : "yellow_right",
		timely : 1,
		stopOnError : true
	});
}

$(document).ready(function() {
	initValidator();
	THISPAGE.init();
});

