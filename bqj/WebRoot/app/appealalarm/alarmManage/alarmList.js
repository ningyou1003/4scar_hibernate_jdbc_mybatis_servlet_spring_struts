var vm = avalon.define({
	$id : "alarmCtrl",
	selectId : [],
	regionId :"",
	msg : "",
	notify : "",
	msgImg : "../../../images/ticon.png",
	data : {},
	keyid : "",
	select : {
		alarmname : "",
		regioncode :""
	},
	power : {
		addpower : 0,
		editpower : 0,
		delpower : 0,
		checkpower : 0
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
		this.initDom();
		this.loadGrid();
		this.addEvent();
	},
	
	initDom : function() {
	},
	
	loadGrid: function (){
		// 翻页保留 2018-01-02 ny start
		//从cookie获取页码
		var alPage = $.cookie('alPageNumber');
		alPage==null?1:alPage;
		// 翻页保留 2018-01-02 ny end
		
		Public.ajaxPost(Public.rootPath() + '/al/listPage', {
			'page': alPage,
            'AlarmSelectVO': vm.select.$model
        }, function (json) {
            if (json.status == 001) {
            	$("#checkalls").removeAttr("checked");
                vm.data = json.data.aPage.page.list;
                vm.power = json.data.aPage.rp;
                vm.selectId = [];
                THISPAGE.initPage(json.data.aPage.page);
            } else {
                alert(json.msg);
            }
        });
	},
	initPage: function (pageData) {
        // 加载分页控件
        $("#PageInfo").pagePlugin({
            totalPage: pageData.totalPage,
            pageNumber: pageData.pageNumber,
            totalRow: pageData.totalRow,
            requst: function (index) {
            	// 翻页保留 2018-01-02 ny start
            	// 存储页码到cookie 
            	$.cookie('alPageNumber', index, {path: '/'}); 
            	// 翻页保留 2018-01-02 ny end
                Public.ajaxPost(Public.rootPath() + '/al/listPage', {
                    'page': index,
                    'AlarmSelectVO': vm.select.$model
                }, function (json) {
                    if (json.status == 001) {
			        	$("#checkalls").removeAttr("checked");
                        vm.data = json.data.aPage.page.list;
                        vm.power = json.data.aPage.rp;
                        vm.selectId = [];
                    } else {
                        alert(json.msg);
                    }
                });
            }
        });
    },
    
    addEvent : function() {
		$("#add").click(function() {
			window.location = "alarmDetail.html";
			
		});
		
		$("#edit").click(function(t) {
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn","请选择要编辑的内容");
			} else {
				if (vm.selectId.length == 1) {
					window.location = "alarmDetail.html?keyid=" + vm.selectId[0];
				} else {
					top.openeasydialog("warn","请不要一次选择多个");
				}
			}
		});
		
		$("#delete").click(function (t){
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn","请选择要删除的内容");
			} else {				
					top.openeasydialog("warning", "确认要删除吗？",function(yes){
	            		if(yes){
	            			var url = "";
	            			url=Public.rootPath() + "/al/delete";	            			
	            			 Public.ajaxPost(url,"keyids=" + vm.selectId, function (json) {
	            				 if (json.status == 001) {
	            					 	THISPAGE.loadGrid();
	            					} else {
	            							top.openeasydialog("error", json.msg);
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

function report(keyid){
	flowNewSend("a_alarm", keyid);
}

$(document).ready(function() {
	THISPAGE.init();
});
