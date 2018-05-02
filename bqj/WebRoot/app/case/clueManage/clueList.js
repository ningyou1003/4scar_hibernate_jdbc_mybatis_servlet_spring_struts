var vm = avalon.define({
	$id : "clueCtrl",
	selectId : [],
	msg : "",
	notify : "",
	msgImg : "../../../images/ticon.png",
	data : {},
	keyid : "",
	select : {
        name : "",
        b_kind : "",
        ispublic : ""
	},
	ispublic : [//是否公开
	            {value:"",name:"所有状态"},
	            {value:"1",name:"公开"},
	            {value:"0",name:"不公开"}],
	type :"",
	regionId :"",
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
		var request = Public.urlRequest();
		vm.type = request.queryString["type"];
		
		//从cookie获取页码
		var cluePage = $.cookie('cluePageNumber');
		cluePage==null?1:cluePage;
		
		Public.ajaxPost(Public.rootPath() + '/case/listPage', {
			'page': cluePage,
            'CasesSelectVO': vm.select.$model,
            'type': vm.type,
            "ispublic" : vm.select.ispublic
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
            	
            	// 存储页码到cookie 
            	$.cookie('cluePageNumber', index, {path: '/'});
            	
                Public.ajaxPost(Public.rootPath() + '/case/listPage', {
                    'page': index,
                    'CasesSelectVO': vm.select.$model,
                    'type': vm.type,
                    "ispublic" : vm.select.ispublic
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
			window.location = "clueDetail.html?type="+vm.type;
			
		});
		
		/*$("#edit").click(function(t) {
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn","请选择要编辑的内容");
			} else {
				if (vm.selectId.length == 1) {
					window.location = "caseDetail.html?keyid=" + vm.selectId[0]+"&type="+vm.type;
				} else {
					top.openeasydialog("warn","请不要一次选择多个");
				}
			}
		});*/
		
		$("#delete").click(function (t){
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn","请选择要删除的内容");
			} else {				
					top.openeasydialog("warning", "确认要删除吗？",function(yes){
	            		if(yes){
	            			var url = "";
	            			url=Public.rootPath() + "/case/delete";	            			
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
	flowNewSend("c_case", keyid);
}
function edit(keyid){
	window.location = "clueDetail.html?keyid=" + keyid+"&type="+vm.type;
}
$(document).ready(function() {
	THISPAGE.init();
	
	var regionid=$.cookie("regionCode");
	if (regionid!=null && regionid=='450000000000') {
		$("#report").css("display","none");
	}else {
		$("#report").css("display","");
	}
});
