var vm = avalon.define({
	$id : "otherPointsCtrl",
	selectId : [],
	msg : "",
	notify : "",
	msgImg : "../../../images/ticon.png",
	data : {},
	keyid : "",
	select : {
        name : "",
        b_kind : "",
	},
	regionId :"",
	type :"",
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
		var request = Public.urlRequest();
		keyid = request.queryString["keyid"];
		type = request.queryString["type"];
		Public.ajaxPost(Public.rootPath() + '/points/listPage', {
			'page': 1,
            'PointsSelectVO': vm.select.$model,
            'type': type
        }, function (json) {
            if (json.status == 001) {
            	$("#checkalls").removeAttr("checked");
                vm.data = json.data.aPage.page.list;
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
                Public.ajaxPost(Public.rootPath() + '/points/listPage', {
                    'page': index,
                    'PointsSelectVO': vm.select.$model,
                    'type': type
                }, function (json) {
                    if (json.status == 001) {
			        	$("#checkalls").removeAttr("checked");
                        vm.data = json.data.aPage.page.list;
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
			window.location = "otherPointsDetail.html?type=other";
			
		});
		
		$("#edit").click(function(t) {
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn","请选择要编辑的内容");
			} else {
				if (vm.selectId.length == 1) {
					window.location = "otherPointsDetail.html?keyid=" + vm.selectId[0];
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
	            			url=Public.rootPath() + "/points/delete";	            			
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
		
	}};
$(document).ready(function() {
	THISPAGE.init();
});
