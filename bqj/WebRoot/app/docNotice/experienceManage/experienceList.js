var vm = avalon.define({
	$id : "experienceCtrl",
	selectId : [],
	tableTop : {
		experiencename : "文件名",
		uploader : "上传者",
		uploaddate : "上传时间",
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
		var experiencePage = $.cookie('experiencePageNumber');
		experiencePage==null?1:experiencePage;
		
		Public.ajaxPost(Public.rootPath() + '/experience/experienceList', {
			'page' : experiencePage
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
            	$.cookie('experiencePageNumber', index, {path: '/'}); 
            	
				Public.ajaxPost(Public.rootPath() + '/experience/experienceList', {
					'page' : index
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
				top.openeasydialog("warn", "请选择要编辑的文件");
			} else {
				if (vm.selectId.length == 1) {
					window.location = "experienceAdd.html?keyid=" + vm.selectId[0];
				} else {
					top.openeasydialog("warn", "请不要一次选择多个文件编辑");
				}
			}
		});

		$("#add").click(function(t) {// 添加
			window.location = "experienceAdd.html" ;
		});

		$("#delete").click(
				function(t) {

					if (vm.selectId.length == 0) {
						top.openeasydialog("warn", "请选择要删除的文件");
					} else {
						top.openeasydialog("warning", "确认要删除吗？",
								function(yes) {
									if (yes) {
										Public.ajaxPost(Public.rootPath()
												+ "/experience/delete", "keyids="
												+ vm.selectId, function(json) {
											if (json.status == 001) {
												THISPAGE.loadGrid();
											} else {
												top.openeasydialog("error",
														"删除文件时出错");
											}
										});
									}
								});

					}
				});

	}
};
/*function approve(keyid){
	flowSend("experienceument", keyid);
}*/
$(document).ready(function () {
    	THISPAGE.init();
});
