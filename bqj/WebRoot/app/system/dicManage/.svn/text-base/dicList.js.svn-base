var vm = avalon.define({
	$id : "dicCtrl",
	selectId : [],
	tableTop : {
		dictionaryname : "字典值",
		dictionarytype : "字典类型",
		orderid : "排序",
	},
	msg : "",
	notify : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	data : {},
	selectdata : {},
	selectType : "",
	power : {
		addpower : 0,
		editpower : 0,
		delpower : 0,
		apppower : 0
	},
	checkAll : function() {
		if (this.checked) {
			for(var i=0;i<vm.data.length;i++){
	        	vm.selectId.push(vm.data[i].keyid);  
        	}     
		} else {
        	vm.selectId.clear();
		}
	}
});

THISPAGE = {
	init : function() {
		this.loadGrid();
		this.addEvent();
	},
	loadGrid : function() {
		Public.ajaxPost(Public.rootPath() + '/dic/dicList', {
			'pageIndex' : 0,
			"selectType" : vm.selectType
		}, function(json) {
			if (json.status == 001) {
	        	$("#checkalls").removeAttr("checked");
				vm.data = json.data.page.list;
				vm.power = json.data.rp;
				vm.selectdata = json.data.dicGroup;
				vm.selectId = [];
				THISPAGE.initPage(json.data.page);
			} else {
				top.openeasydialog("error", json.msg);
			}
		});
	},
	initPage : function(pageData) {
		// 加载分页控件
		$("#PageInfo").pagePlugin({
			totalPage : pageData.totalPage,
			pageNumber : pageData.pageNumber,
			totalRow : pageData.totalRow,
			requst : function(index) {
				Public.ajaxPost(Public.rootPath() + '/dic/dicList', {
					'page' : index,
					"selectType" : vm.selectType
				}, function(json) {
					if (json.status == 001) {
			        	$("#checkalls").removeAttr("checked");
						vm.data = json.data.page.list;
						vm.power = json.data.rp;
						vm.selectId = [];
					} else {
						top.openeasydialog("error", json.msg);
					}
				});
			}
		});
	},
	addEvent : function() {
		$("#edit").click(function(t) {// 查看
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn", "请选择要编辑的数据字典");
			} else {
				if (vm.selectId.length == 1) {
					window.location = "dicadd.html?keyid=" + vm.selectId[0];
				} else {
					top.openeasydialog("warn", "请不要一次选择多个数据字典编辑");
				}
			}
		});

		$("#delete").click(
				function(t) {
					if (vm.selectId.length == 0) {
						top.openeasydialog("warn", "请选择要删除的数据字典");
					} else {
						top.openeasydialog("warning", "确认要删除吗？", function(
								yes) {
							if (yes) {
								Public.ajaxPost(Public.rootPath()
										+ "/dic/deleteDic", "keyids="
										+ vm.selectId, function(json) {
									if (json.status == 001) {
										THISPAGE.loadGrid();
									} else {
										top
												.openeasydialog("error",
														"删除数据字典时出错");
									}
								});
							}
						});
					}

				});

		$("#SelButton").click(function() {
			THISPAGE.loadGrid();
		});
		
		$("#check").click(function() {
			if ($("#sel_div").is(":hidden")) {
				$("#sel_div").fadeIn();
			} else {
				$("#sel_div").fadeOut();
			}
		});
	}
};

$(document).ready(function() {
	THISPAGE.init();
});
