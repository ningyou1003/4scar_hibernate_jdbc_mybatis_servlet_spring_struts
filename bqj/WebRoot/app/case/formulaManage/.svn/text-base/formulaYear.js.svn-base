var vm = avalon.define({
    	$id : "formulaYearCtrl",
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
			Public.ajaxPost(Public.rootPath() + '/formulayear/yearList' , {
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
					Public.ajaxPost(Public.rootPath() + '/formulayear/yearList', {
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
				top.openbuttondialog("年份增加",
						150,500,"case/formulaManage/formulaYearAdd.html",
						false,function(item,dialog) {
							Public.ajaxPost(Public.rootPath() + '/formulayear/addFormulaYear', {
								'region':dialog.frame.$("#year").val(),
							},function(json) {
								if(json.status == "001") {
									dialog.close();
									window.location = "formulaYear.html";
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
	window.location = "formulaIndex.html?year=" + year;
}

//编辑年份
function edit(keyid) {
	top.openbuttondialog("年份编辑",
			150,500,"case/formulaManage/formulaYearAdd.html?keyid=" + keyid,
			false,function(item,dialog) {
				Public.ajaxPost(Public.rootPath() + '/formulayear/editFormulaYear', {
					'keyid' : keyid,
					'region':dialog.frame.$("#year").val(),
				},function(json) {
					if(json.status == "001") {
						dialog.close();
						window.location = "formulaYear.html";
					} else {
						alert(json.msg);
					}
				});
	});
}

function deletes(keyid) {
	top.openeasydialog("warning", "确认要删除吗？",
			function(yes) {
				if (yes) {
					Public.ajaxPost(Public.rootPath()
							+ "/formulayear/deleteFormulaYear", "keyid="
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
		 
		
$(document).ready(function () {
	THISPAGE.init();
});