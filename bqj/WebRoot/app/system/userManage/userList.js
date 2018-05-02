var vm = avalon.define({
    $id: "userCtrl",
    selectId: [],
    tableTop: {
        UserName: "用户名",
        RelaName: "真实姓名",
        RegionID : "所属区域",
        Mobile: "联系电话",
        Email: "Email",
        option : "操作"
    },
    keyid : "",// 区域id
    data: {},
    select: {
        name: "",
        department: "",
        role: "",
        regionid : ""
    },
    resetPwd : 0,
    selectRole: {},
    selectUser: {},

    power: {
        addpower: 0,
        editpower: 0,
        delpower: 0,
        apppower: 0
    },
    webroot : Public.rootPath()
    ,
    showInputMsg : function(username,keyid){
    	top.opendialog(username + "-名片", 430, 335, 'system/userManage/QRcode.html?keyid=' + keyid, false);
    },
    checkAll : function() {
        if (this.checked) {
        	for(var i=0;i<vm.data.length;i++){
        		vm.selectId.push(vm.data[i].keyid);
        	}  
        	//$(".itemCheckBox").prop("checked", "true");
        } else {
        	vm.selectId.clear();
        	//$(".itemCheckBox").removeAttr("checked");
        }
    }
});

avalon.filters.networkFilter = function (str) {// str为管道符之前计算得到的结果，默认框架会帮你传入，此方法必须返回一个值
    if (str == 1) {
        return "限内网";
    } else {
        return "可内外网";
    }
}

THISPAGE = {
    init: function () {
        this.initDom();
        this.loadGrid();
        this.addEvent();
    },

    initDom: function () {
    },

    loadGrid: function () {
    	var request = Public.urlRequest();
		keyid = request.queryString["keyid"];
//		vm.select.regionid = keyid;
		//从cookie获取页码
		var userPage = $.cookie('userPageNumber');
		userPage==null?1:userPage;
		
        Public.ajaxPost(Public.rootPath() + '/user/userListPage', {
            'page': userPage,
            'UserSelectVO': vm.select.$model,
            'regionId' : keyid
        }, function (json) {
            if (json.status == 001) {
            	$("#checkalls").removeAttr("checked");
                vm.data = json.data.page.list;
                vm.power=json.data.rp;
                vm.resetPwd = json.data.resetPwd;
                vm.selectRole = json.data.roleList;
                vm.selectId = [];
                THISPAGE.initPage(json.data.page);
            } else {
                alert(json.msg);
            }
        });
    },

    initPage: function (pageData) {
        // 加载分页控件
        $("#PageInfo").pagePlugin({
            totalPage: pageData.totalPage,
            pageNumber: pageData.pageNumber,
            totalRow: pageData.totalRow,
            requst: function (index) {
            	// 存储页码到cookie 
            	$.cookie('userPageNumber', index, {path: '/'}); 
            	
                Public.ajaxPost(Public.rootPath() + '/user/userListPage', {
                    'page': index,
                    'UserSelectVO': vm.select.$model,
                    'regionId' : keyid
                }, function (json) {
                    if (json.status == 001) {
			        	$("#checkalls").removeAttr("checked");
                        vm.data = json.data.page.list;
                        vm.power=json.data.rp;
                        vm.selectId = [];
                    } else {
                        alert(json.msg);
                    }
                });
            }
        });
    },

    addEvent: function () {
        $("#edit").click(function (t) {// 查看
            if (vm.selectId.length == 0) {
            	top.openeasydialog("warn", "请选择要编辑的用户");
            } else {
                if (vm.selectId.length == 1) {
                    window.location = "useradd.html?keyid=" + vm.selectId[0];
                } else {
                    top.openeasydialog("warn", "请不要一次选择多个用户编辑");
                }
            }
        });

        $("#delete").click(function (t) {
        	var msg;
        	if(vm.selectId.length==0){
        		msg = "请选择要删除的用户";
        		top.openeasydialog("warn", msg);
        		return ;
        	} else {
        		msg = "确认要删除吗？";
        	}
        	top.openeasydialog("warning", msg,function(type){
        		if(type){
        			 Public.ajaxPost(Public.rootPath() + "/user/deleteUser",
        						"keyids=" + vm.selectId, function (json) {
        						    if (json.status == 001) {
        						        THISPAGE.loadGrid();
        						    } else {
        						    	top.openeasydialog("error", "删除用户时出错！");
        						    }
        						});
        		}
        	});
        });

        $("#SelButton").click(function () {
            THISPAGE.loadGrid();
        });

        $("#check").click(function () {
            if ($("#sel_div").is(":hidden")) {
                $("#sel_div").fadeIn();
            } else {
                $("#sel_div").fadeOut();
            }
        });
        
    }
    
};

function resetpw(keyid){
	 Public.ajaxPost(Public.rootPath() + '/user/resetPw', {
         'keyid': keyid
     }, function (json) {
         if (json.status == 001) {
	         $("#checkalls").removeAttr("checked");
	         top.openeasydialog("success", " 重置密码成功！");
         } else {
             alert(json.msg);
         }
     });
}

