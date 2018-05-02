var vm = avalon.define({
	$id : "silhouetteCtrl",
	selectId : [],
	tableTop : {
		title : "标题",
//		content : "内容",
		user : "录入人",
		releasetime : "发布时间",
		status : "审核状态",
		operation : "操作",
	},
	year : "",
	msg : "",
	regionCode : "",
	parentCode : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	data : {},
	selectType : ["","否","是"],
	type : "",
	select : {
		title : "",
		isPublic : "",
	},
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
		vm.regionCode = request.queryString["regionCode"];
		vm.parentCode = request.queryString["parentCode"];
		vm.year = request.queryString["year"];
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
		Public.ajaxPost(Public.rootPath() + '/silhouette/List', {
			'pageSize' : 8,
			'regionCode' : vm.regionCode,
			'parentCode' : vm.parentCode,
			'year' : vm.year,
			'cultureSelectVO' : vm.select.$model,
			}, function(json) {
			if (json.status == 001) {
				$("#loading").hide();
				$("#silhouette").show();
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
				Public.ajaxPost(Public.rootPath() + '/silhouette/List', {
					'page' : index,
					'pageSize' : 8,
					'regionCode' : vm.regionCode,
					'parentCode' : vm.parentCode,
					'year' : vm.year,
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
					window.location = "silhouetteAdd.html?keyid=" + vm.selectId[0];
				} else {
					top.openeasydialog("warn", "请不要一次选择多个内容编辑");
				}
			}
		});*/

		$("#add").click(function(t) {// 添加
			window.location = "silhouetteAdd.html?regionCode=" + vm.regionCode + "&year=" + vm.year ;
		});

/*		$("#delete").click(
				function(t) {

					if (vm.selectId.length == 0) {
						top.openeasydialog("warn", "请选择要删除的内容");
					} else {
						top.openeasydialog("warning", "确认要删除吗？",
								function(yes) {
									if (yes) {
										Public.ajaxPost(Public.rootPath()
												+ "/silhouette/delete", "keyids="
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
				});*/
		
		$("#editShow").click(function() {
			$(".delete").hide();
			$(".edit").toggle();
		});
		
		$("#deleteShow").click(function() {
			$(".edit").hide();
			$(".delete").toggle();
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

//编辑
function edit(keyid, sourceid) {
	window.location.href = "silhouetteAdd.html?keyid=" + keyid + "&regionCode=" + vm.regionCode + "&year=" + vm.year + "&sourceid=" + sourceid;
}

//删除
function deletes(keyid) {
	top.openeasydialog("warning", "确认要删除吗？",
			function(yes) {
				if (yes) {
					Public.ajaxPost(Public.rootPath()
							+ "/silhouette/delete", "keyids="
							+ keyid, function(json) {
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

//上报
function report(keyid) {
	flowSend("silhouette",keyid);
}

//转到详情页
function byDetail(keyid) {
	window.location.href = "silhouetteDetail.html?keyid=" + keyid + "&regionCode=" + vm.regionCode + "&year=" + vm.year;
}

//返回区域文件夹
function backRegion() {
	window.location.href = "silhouetteIndex.html?year=" + vm.year;
}

$(document).ready(function () {
    	THISPAGE.init();
});
