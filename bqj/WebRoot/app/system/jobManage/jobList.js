var vm = avalon.define({
	$id : "jobCtrl",
	selectId : [],
	tableTop : {
		name : "职务名称",
		msg : "职责",
		region : "所在地区",
		regioncode : "所属区域",
		order : "排序"
	},
	regioncode : "",
	msg : "",
	notify : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	data : {keyid : ""},
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
    },
});

THISPAGE = {
	init : function() {
		this.loadGrid();
		this.addEvent();
	},
	loadGrid : function() {
		var request = Public.urlRequest();
		if(request.queryString["keyid"] !=null && request.queryString["keyid"].length >0){
			vm.regioncode = vm.data.keyid = request.queryString["keyid"];
		}
		Public.ajaxPost(Public.rootPath() + '/job/jobList', {
			'page' : 1,
			'regionCode' : vm.regioncode
		}, function(json) {
			if (json.status == 001) {
	        	$("#checkalls").removeAttr("checked");
				vm.data = json.data.page.list;
				vm.power=json.data.rp;
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
				Public.ajaxPost(Public.rootPath() + '/job/jobList', {
					'page' : index,
					'regionCode' :vm.regioncode
				}, function(json) {
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
	addEvent : function() {
		$("#add").click(
				function(){
					location.href = "jobadd.html?regioncode=" + vm.regioncode;
				}
		);
		
		$("#delete").click(
				function(t) {
					if (vm.selectId.length == 0) {
						top.openeasydialog("warn","请选择要删除的职务");
					} else {
						top.openeasydialog("warning",
								"确认要删除吗？", function(yes) {
									if (yes) {
										Public.ajaxPost(Public.rootPath()
												+ "/job/deletejob", "keyids="
												+ vm.selectId, function(json) {
											if (json.status == 001) {
												THISPAGE.loadGrid();
											} else {
												top.openeasydialog("error","删除职务时出错");
											}
										});
									}
								});
					}

				});
		$("#edit").click(
				function(t) {// 查看
					if (vm.selectId.length == 0) {
						top.openeasydialog("warn",
								"请选择要编辑的职务");
					} else {
						if (vm.selectId.length == 1) {
							window.location = "jobadd.html?keyid="+ vm.selectId[0];
						} else {
							top.openeasydialog("warn",
									"请不要一次选择多个职务编辑");
						}
					}
				});
	},
};

$(document).ready(function() {
	THISPAGE.init();
});
