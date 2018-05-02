var vm = avalon.define({
	$id : "zdCtrl",
	selectId : [],
	tableTop : {
		title : "联防标题",
		ispublic : "是否公开",
		createTime : "发布时间",
//		status : "审批状态",
		inputperson : "录入人",
		reportperson : "上报人",
		option : "操作"
	},
	msg : "",
	notify : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	data : {
		keyid : ""
	},
	regioncode : "",
	power : {
		addpower : 0,
		editpower : 0,
		delpower : 0,
		checkpower : 0
	},
	select : {//查询条件
		title : "",
		ispublic : ""
	},
	ispublic : [//是否公开
            {value:"",name:"所有状态"},
            {value:"1",name:"公开"},
            {value:"0",name:"不公开"}],
	checkAll : function() {
		if (this.checked) {
			for ( var i = 0; i < vm.data.length; i++) {
				vm.selectId.push(vm.data[i].keyid);
			}
		} else {
			vm.selectId.clear();
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
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.regioncode = vm.data.keyid = request.queryString["keyid"];
		}
		//从cookie获取页码
		var zoneDefencePage = $.cookie('zoneDefencePageNumber');
		zoneDefencePage==null?1:zoneDefencePage;
		
		Public.ajaxPost(Public.rootPath() + '/zoneDefence/zoneDefenceList', {
			'page' : zoneDefencePage,
			"title" : vm.select.title,
			"ispublic" : vm.select.ispublic
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
            	$.cookie('zoneDefencePageNumber', index, {path: '/'}); 
            	
				Public.ajaxPost(Public.rootPath() + '/zoneDefence/zoneDefenceList', {
					'page' : index,
					"title" : vm.select.title,
					"ispublic" : vm.select.ispublic
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
				top.openeasydialog("warn", "请选择要编辑的区域联防");
			} else {
				if (vm.selectId.length == 1) {
					window.location = "zdAdd.html?keyid=" + vm.selectId[0];
				} else {
					top.openeasydialog("warn", "请不要一次选择多个区域联防编辑");
				}
			}
		});

		$("#add").click(function(t) {// 添加
			window.location = "zdAdd.html" ;
		});

		$("#delete").click(
				function(t) {

					if (vm.selectId.length == 0) {
						top.openeasydialog("warn", "请选择要删除的区域联防");
					} else {
						top.openeasydialog("warning", "确认要删除吗？",
								function(yes) {
									if (yes) {
										Public.ajaxPost(Public.rootPath()
												+ "/zoneDefence/delete", "keyids="
												+ vm.selectId, function(json) {
											if (json.status == 001) {
												THISPAGE.loadGrid();
											} else {
												top.openeasydialog("error",
														"删除区域联防时出错");
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

function approve(keyid){
	flowSend("zoneDefence", keyid);
}

$(document).ready(function () {
    	THISPAGE.init();
});
