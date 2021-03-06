
var vm = avalon.define({
	$id : "areaCtrl",
	selectId : [],
	tableTop : {
		regioncode : "归属地代码",
		region : "名称",
		description : "简介",
		option : "操作",
	},
	areaname:"",
	isDemonstration:"",
	msg : "",
	notify : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	data : {},
	keyid : "",
	select : {
		region : "",
		regioncode : "",
		parentcode : ""
	},
	power : {
		addpower : 0,
		editpower : 0,
		delpower : 0,
		apppower : 0
	},
	checkAll : function() {
		if (this.checked) {
			for(var i=0;i<vm.data.length;i++){
        		vm.selectId.push(vm.data[i].regioncode);
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
		keyid = request.queryString["keyid"];
		vm.select.parentcode = keyid;
		vm.keyid=keyid;
		
		//从cookie获取页码
		var areaPage = $.cookie('areaPageNumber');
		areaPage==null?1:areaPage;
		
		Public.ajaxPost(Public.rootPath() + '/area/areaListPage', {
			'page' : areaPage,
			"AreaSelectVO" : vm.select.$model,
			"areaname":vm.areaname,
			"isDemonstration":vm.isDemonstration
		}, function(json) {
        	$("#checkalls").removeAttr("checked");
			vm.data = json.data.page.list;
			vm.power=json.data.rp;
			vm.selectId = [];
			
			THISPAGE.initPage(json.data.page);
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
            	$.cookie('areaPageNumber', index, {path: '/'}); 
            	
				Public.ajaxPost(Public.rootPath() + '/area/areaListPage', {
					'page' : index,
					"AreaSelectVO" : vm.select.$model,
					"areaname":vm.areaname,
					"isDemonstration":vm.isDemonstration
				}, function(json) {
		        	$("#checkalls").removeAttr("checked");
					vm.data = json.data.page.list;
					vm.power=json.data.rp;
					vm.selectId = [];
				});
			}
		});
	},
	addEvent : function() {
		$("#add").click(function(t) {// 添加	
			location.href="areaadd.html?parentcode="+vm.select.parentcode;
		});
		
		$("#edit").click(function(t) {// 查看			
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn", "请选择要编辑的区域");
			} else {
				
				if (vm.selectId.length == 1) {
					window.location = "areaadd.html?areaId=" + vm.selectId[0];
				} else {
					top.openeasydialog("warn", "请不要一次选择多个区域编辑");
				}
			}
		});

		$("#delete").click(function(t) {
			//var msg;
			var length = vm.selectId.length;
			if(length==0){
				vm.msg = "请选择要删除的区域";
				top.openeasydialog("warn",vm.msg);
				return ;
			} else { 
				Public.ajaxPost(Public.rootPath() + "/area/delete",
						"regioncodes="+ vm.selectId, function(json) {
					if(json.status==001){
						vm.msg = "确认要删除吗？";
						top.openeasydialog(
								"warning", vm.msg, 
								function(type) {
									if (type) {
										Public.ajaxPost(Public.rootPath() + "/area/delete",
												"regioncodes="+ vm.selectId, function(json) {
											if (json.status == 001) {
												top.openeasydialog("success",json.msg, function(item, dialog){
													dialog.close();
													THISPAGE.loadGrid();
													window.parent.frames[0].location.reload();
												});
											} else {
												top.openeasydialog("error","删除区域时出错");
											}
										});
								}
							
						});
					}else{
						vm.msg = json.msg;
						top.openeasydialog("warn",vm.msg);
						return ;
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
	$("#checkDemonstration").click(function(){
		
		var areaname=$("#areaname").val();
		var isDemonstration=$("#isDemonstration").val();
		vm.areaname=areaname;
		vm.isDemonstration=isDemonstration;
		Public.ajaxPost(Public.rootPath() + '/area/areaListPage', {
			'page' : 1,
			"AreaSelectVO" : vm.select.$model,
			"areaname":vm.areaname,
			"isDemonstration":vm.isDemonstration
		}, function(json) {
        	$("#checkalls").removeAttr("checked");
			vm.data = json.data.page.list;
			vm.power=json.data.rp;
			vm.selectId = [];
			
			THISPAGE.initPage(json.data.page);
		});
	
	});
});
