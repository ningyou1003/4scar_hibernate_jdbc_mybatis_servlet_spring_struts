var vm = avalon.define({
    	$id : "silhouetteYearCtrl",
		data : [],
		power : {
			addpower : 0,
			editpower : 0,
			delpower : 0,
			checkpower : 0
		},
});
		
THISPAGE = {
		init : function() {
			this.initDom();
			this.loadGrid();
			this.addEvent();
		},
		initDom : function(){
			
		},
		loadGrid : function() {
			var request = Public.urlRequest();
			Public.ajaxPost(Public.rootPath() + '/silhouetteYear/yearList' , {
				'pageIndex' : 0
			},function(json){
				if(json.status == 001) {
					vm.data = json.data.page.list;
					vm.power = json.data.rp;
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
					Public.ajaxPost(Public.rootPath() + '/silhouetteYear/yearList', {
						'page' : index,
						'regionCode' : vm.keyid
					}, function(json) {
						if (json.status == 001) {
							vm.data = json.data.page.list;
							vm.power = json.data.rp;
						} else {
							top.openeasydialog("error", json.msg);
						}
					});
				}
			});
		},
		addEvent : function() {
			$("#add").click(function() {
				top.openbuttondialog("墙报年份增加",
						150,500,"culture/silhouetteManage/silhouetteYearAdd.html",
						false,function(item,dialog) {
							Public.ajaxPost(Public.rootPath() + '/silhouetteYear/addSilhouetteYear', {
								'region':dialog.frame.$("#year").val(),
							},function(json) {
								if(json.status == "001") {
									dialog.close();
									window.location = "silhouetteYear.html";
									window.parent.cums_leftFrame.location.reload();//重载墙报树信息
								} else {
									alert(json.msg);
								}
							});
				});
			});
			
			$("#editShow").click(function() {
				$(".delete").hide();
				$(".edit").toggle();
			});
			
			$("#deleteShow").click(function() {
				$(".edit").hide();
				$(".delete").toggle();
			});
			
		},
};

//转到区域文件夹
function nextRegion(region) {
	var year = region.substring(0,4);
	window.location = "silhouetteIndex.html?year=" + year;
}

//编辑年份
function edit(keyid) {
	top.openbuttondialog("墙报年份编辑",
			150,500,"culture/silhouetteManage/silhouetteYearAdd.html?keyid=" + keyid,
			false,function(item,dialog) {
				Public.ajaxPost(Public.rootPath() + '/silhouetteYear/editSilhouetteYear', {
					'keyid' : keyid,
					'region':dialog.frame.$("#year").val(),
				},function(json) {
					if(json.status == "001") {
						dialog.close();
						window.location = "silhouetteYear.html";
						window.parent.cums_leftFrame.location.reload();//重载墙报树信息
					} else {
						alert(json.msg);
					}
				});
	});
}

//删除年份
function deletes(keyid,region) {
	var year = region.substring(0,4);
	top.openeasydialog("warning", "确认要删除吗？(警告:一旦删除墙报无法找回,请慎重!)",
			function(yes) {
				if (yes) {
					Public.ajaxPost(Public.rootPath()
							+ "/silhouetteYear/deleteSilhouetteYear", {
						    'keyid': keyid,
						    'year' : year,
						    }, function(json) {
						if (json.status == 001) {
							THISPAGE.loadGrid();
							window.parent.cums_leftFrame.location.reload();//重载墙报树信息
						} else {
							top.openeasydialog("error",
									"删除时出错");
						}
					});
				}
			});
}
		 
		
$(document).ready(function () {
	THISPAGE.init();
});