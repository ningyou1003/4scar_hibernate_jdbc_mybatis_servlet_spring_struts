var vm = avalon.define({
	$id : "orgCtrl",
	selectId : [],
	tableTop : {
		regioncode : "编码",
		org_fullname : "机构名称",
		workarea :"工作职能",
		address : "单位住址",
		phone : "联系电话",
		parent_orgid : "上级单位",
		relation : "关系属性",
		status : "状态",
	},
	regionId: "",//区域码id 2015.6.16
	msg : "",
	notify : "",
	msgImg : "../../../images/ticon.png",
	data : {},
	keyid : "",
	select : {
		orgname : "",//机构名
		orgcode : "",
	},
	
	 power: {
	        addpower: 0,
	        editpower: 0,
	        delpower: 0,
	        apppower: 0
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
		keyid = request.queryString["keyid"];//keyid是orgPage.html页面的参数,得到的是区域码
		vm.select.orgcode = keyid;
		vm.regionId=keyid;
		Public.ajaxPost(Public.rootPath() + '/org/orgListPage', {
			'page': 1,
            'OrgSelectVO': vm.select.$model
        }, function (json) {
            if (json.status == 200) {
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
	initPage: function (pageData) {
        // 加载分页控件
        $("#PageInfo").pagePlugin({
            totalPage: pageData.totalPage,
            pageNumber: pageData.pageNumber,
            totalRow: pageData.totalRow,
            requst: function (index) {
                Public.ajaxPost(Public.rootPath() + '/org/orgListPage', {
                    'page': index,
                    'OrgSelectVO': vm.select.$model
                }, function (json) {
                    if (json.status == 200) {
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
		$("#addOrg").click(function() {//2015.6.16添加
			top.openbuttondialog("新增机构",400, 600,
					"system/orgManage/orgName.html" , false, function(item, dialog) {
				if(dialog.frame.$("#org_fullname").val()==''||dialog.frame.$("#org_fullname").val()==null){
					top.openeasydialog("warn","机构中文名不能为空");
				}else{//非空才可以发送请求						
						Public.ajaxPost(Public.rootPath() + '/org/add', {
							'org_fullname': dialog.frame.$("#org_fullname").val(),
							'org_fullname_abroad': dialog.frame.$("#org_fullname_abroad").val(),
							'org_shutcut': dialog.frame.$("#org_shutcut").val(),
							'org_shutcut_abroad': dialog.frame.$("#org_shutcut_abroad").val(),
						}, function (json) {
							if (json.status == "001") {
								dialog.close();
								var path = 'orgDetail.html?keyid=' + json.data;
								window.location = path;
							} else {
								alert(json.msg);
							}
						});
				}
					});
			});
		
		$("#edit").click(function(t) {// 编辑
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn","请选择要编辑的机构");
			} else {
				if (vm.selectId.length == 1) {
					window.location = "orgDetail.html?keyid=" + vm.selectId[0];
				} else {
					top.openeasydialog("warn","请不要一次选择多个机构编辑");
				}
			}
		});
		
		$("#delete").click(function (t){
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn","请选择要删除的机构");
			} else {				
					top.openeasydialog("warning", "确认要删除吗？",function(yes){
	            		if(yes){
	            			var url = "";
	            			url=Public.rootPath() + "/org/deleteOrg";	            			
	            			 Public.ajaxPost(url,"keyids=" + vm.selectId, function (json) {
	            				 if (json.status == 200) {
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
$(document).ready(function() {
	THISPAGE.init();
});
