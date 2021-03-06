var vm = avalon.define({
	$id : "otherCtrl",
	selectId : [],
	tableTop : {
		title : "标题",
		ispublic : "是否公开",
		createuser : "录入人",
		releasetime : "发布时间",
		status : "上报人",
		operation : "操作",
	},
	selectType : ["","否","是"],
	type : "",
	select : {
		title : "",
		isPublic : "",
	},
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	data : {},
	power : {
		addpower : 0,
		editpower : 0,
		delpower : 0,
		checkpower : 0
	},
	checkAll : function() {
		if (this.checked) {
			for ( var i = 0; i < vm.data.length; i++) {
				vm.selectId.push(vm.data[i].keyid);
			}
			$(".itemCheckBox").prop("checked", "true");
		} else {
			vm.selectId.clear();
			$(".itemCheckBox").removeAttr("checked");
		}
	}
});

THISPAGE = {
	init : function() {
		this.initDom();
		this.loadGrid();
		this.addEvent();
	},
	initDom : function() {
	},
	loadGrid : function() {
		var request = Public.urlRequest();
		//查询条件过滤
		if(vm.type != "") {
			if(vm.type == "否") {
				vm.select.isPublic = 0;
			} else {
				vm.select.isPublic = 1;
			}
		} else {
			vm.select.isPublic = "";
		}
		//从cookie获取页码
		var otherPage = $.cookie('otherPageNumber');
		otherPage==null?1:otherPage;
		
		Public.ajaxPost(Public.rootPath() + '/other/List', {
			'page' : otherPage,
			'cultureSelectVO' : vm.select.$model,
			}, function(json) {
			if (json.status == 001) {
	        	$("#checkalls").removeAttr("checked");
				vm.data = json.data.page.list;
				vm.power = json.data.rp;
				vm.selectId = [];
				THISPAGE.initPage(json.data.page);
			} else {
				alert(json.msg);
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
				// 存储页码到cookie 
            	$.cookie('otherPageNumber', index, {path: '/'});
            	
				Public.ajaxPost(Public.rootPath() + '/other/List', {
					'page' : index,
					'cultureSelectVO' : vm.select.$model,
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
/*		$("#edit").click(function(t) {// 查看
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn", "请选择要编辑内容");
			} else {
				if (vm.selectId.length == 1) {
					window.location = "otherAdd.html?keyid=" + vm.selectId[0];
				} else {
					top.openeasydialog("warn", "请不要一次选择多个内容编辑");
				}
			}
		});*/

		$("#add").click(function(t) {// 添加
			window.location = "otherAdd.html" ;
		});

		$("#delete").click(
				function(t) {

					if (vm.selectId.length == 0) {
						top.openeasydialog("warn", "请选择要删除的内容");
					} else {
						top.openeasydialog("warning", "确认要删除吗？",
								function(yes) {
									if (yes) {
										Public.ajaxPost(Public.rootPath()
												+ "/other/delete", "keyids="
												+ vm.selectId, function(json) {
											if (json.status == 001) {
												THISPAGE.loadGrid();
											} else {
												top.openeasydialog("error",
														"删除时出错");
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
//上报
function report(keyid) {
	flowSend("other",keyid);
}

$(document).ready(function () {
    	THISPAGE.init();
    	
    	var regionid=$.cookie("regionCode");
    	if (regionid!=null && regionid=='450000000000') {
    		$("#report").css("display","none");
    	}else {
    		$("#report").css("display","");
    	}
});
