var vm = avalon.define({
	$id : "formulaCtrl",
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
	},
	
	select : {
        name : "",
        ispublic : "",
        ftype : ""
	},
	ispublic : [//是否公开
	            {value:"",name:"所有状态"},
	            {value:"1",name:"公开"},
	            {value:"0",name:"不公开"}],
    ftype : [//案件类型
	            {value:"",name:"所有类型"},
	            {value:"1",name:"刑事案件"},
	            {value:"0",name:"行政案件"}],
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
		vm.year = request.queryString["year"];
		Public.ajaxPost(Public.rootPath() + '/formula/List', {
			'pageIndex' : 0,
			'regionCode' : vm.regionCode,
			'year' : vm.year,
			'FormulaSelectVO': vm.select.$model,
			"ispublic" : vm.select.ispublic,
			"ftype" : vm.select.ftype,
			'pageSize': 8
			}, function(json) {
			if (json.status == 001) {
	        	$("#checkalls").removeAttr("checked");
				vm.data = json.data.page.list;
				vm.power = json.data.rp;
				vm.selectId = [];
				var len = vm.data.length;
				 for (i = 0; i < len; i++) {
					 var title = vm.data[i].title;
					 if (title.length > 15) {
						 title = title.substring(0, 15) + "…";
						 vm.data[i].title = title;
	                    }
				 }
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
				Public.ajaxPost(Public.rootPath() + '/formula/List', {
					'page' : index,
					'regionCode' : vm.regionCode,
					'year' : vm.year,
					'FormulaSelectVO': vm.select.$model,
					"ispublic" : vm.select.ispublic,
					"ftype" : vm.select.ftype,
					'pageSize': 8
				}, function(json) {
					if (json.status == 001) {
			        	$("#checkalls").removeAttr("checked");
						vm.data = json.data.page.list;
						vm.power = json.data.rp;
						vm.selectId = [];
						var len = vm.data.length;
						 for (i = 0; i < len; i++) {
							 var title = vm.data[i].title;
							 if (title.length > 15) {
								 title = title.substring(0, 15) + "…";
								 vm.data[i].title = title;
			                    }
						 }
					} else {
						top.openeasydialog("error", json.msg);
					}
				});
			}
		});
	},
	addEvent : function() {

		$("#add").click(function(t) {// 添加
			window.location = "formulaAdd.html?regionCode=" + vm.regionCode + "&year=" + vm.year ;
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
												+ "/formula/delete", "keyids="
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
		$("#SelButton").click(function() {//查询功能,显示数据
			THISPAGE.loadGrid();
		});

		$("#check").click(function() {//查询功能开始隐藏查询条件,点击出现
			if ($("#sel_div").is(":hidden")) {
				$("#sel_div").fadeIn();
			} else {
				$("#sel_div").fadeOut();
			}
		});

	}};
//上报
function report(keyid) {
	flowSend("formula",keyid);
}

//返回区域文件夹
function backRegion() {
	window.location.href = "formulaIndex.html?year=" + vm.year;
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