var manager;
var vm = avalon.define({
	$id : "orgdetailCtrl",
	labor: {},//2015.6.19新增
	data : {
		tempflag : false,//待审核
		tempsave : false,//暂存
		ischeck : false,//是否是审核
		issame : false,//是否相同
		org_fullname : "",//部门中文全称
		org_shutcut : "",//部门中文简称
		org_fullname_abroad :"",//部门英文全称
		org_shutcut_abroad : "",//部门英文简称
		workarea : "",// 工作职能
		address : "",// 通讯地址		
		zipcode : "",//邮政编码
		phone : "",//联系电话
		type : "",
		portraiture : "",//图文传真
		website : "",//官方网站
		email : "",//电子邮箱
		webchat : "",//官方微信号
		twitter : "",//官方微博
		regioncode : "",//区域码
		regionname :"",//区域名字 2015.6.6新加
		parent_orgid : "",//上级单位
		under_orgid : "",//下级单位
		supname:"",//上级机构
		supname_e:"",//上级机构英文
		supshortname:"",//上级机构简称
		supshortname_e:"",//上级机构英文简称
		suprelation:"",//上级机构的关系属性
		subname:"",//下级机构
		subname_e:"",//下级机构英文
		subshortname:"",//下级机构简称
		subshortname_e:"",//下级机构简称英文
		subrelation:"",//下级机构的关系属性
	},
	lselectId : [],
	type:{},
	orgLeader : {//发送请求的数据,需要参数
		userid : '',
		orgid : '',
	},
	depdata:{
		keyid : "",
		departmentname : "",
		departmenton : "",
		parentname:"",
		parentid : "",
		departmentworkarea : "",
		departmentleader : "",
		departmentphone : "",
		orgkeyid:"",
		orgname:"",
		deporder : ""
	},
	selectId : {},
	orgTreeData:{},
	orgTreeData2:[],
	click:null,
	selectType:"",
	msg : "",
	notify : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	parentid : "",
	keyid : "",
	areaName: "",//2015.6.16添加
    areaCode: "",//2015.6.16添加
	submit : function(){
		$("#base_form").trigger("validate");
	}
});

THISPAGE = {
		init : function() {
			this.initData();
			this.initDom();
			this.initEvent();
			this.addEvent();
			loadDepGrid();
			loadLabor();
		},
		
		initData : function() {//初始化数据
			var request = Public.urlRequest();
			if (request.queryString["keyid"] != null
					&& request.queryString["keyid"].length > 0) {
				vm.keyid = request.queryString["keyid"];
				
				Public.ajaxPost(Public.rootPath() + "/org/editData", "keyid="
						+ vm.keyid, function(json) {
					if (json.status == 001) {
						vm.data=json.data;
					} else {
						vm.msg = "机构信息读取失败";
						$("#notify").fadeIn(200);
					}
				});
				
				Public.ajaxPost(Public.rootPath() + "/dic/getDicNameByType", "selectType="+"单位性质"
						, function(json) {
					if (json.status == 001) {
						vm.type=json.data;
					} else {
						top.openeasydialog("error", json.msg);
					}
				});
			}
		},
		
		initEvent : function() {//返回按钮
			$("#btback").click(function() {
				window.location = "orgList.html";
			});
			$("#maingrid").click(function(e) {
				manager._onClick(e);
			});
		},

		initDom : function() {
			document.getElementById('org_fullname').focus();
		},
		
		addEvent : function(){
			$("#applyBtn").click(//提交审核
					function() {
						Public.ajaxPost(Public.rootPath() + '/org/apply', {"id":vm.keyid}, function (json) {
							if (json.status == 001) {
								top.openeasydialog("success",json.msg);
								vm.data.status=1;
							} else {
								top.openeasydialog("error", json.msg);
							}
						});
					}
			);

			$("#acceptBtn").click(//通过
				function() {
					Public.ajaxPost(Public.rootPath() + '/org/acceptChange', {"id":vm.keyid}, function (json) {
						if (json.status == 001) {
							top.openeasydialog("success",json.msg);
							vm.data.status=0;
						} else {
							top.openeasydialog("error", json.msg);
						}
					});
				}
			);

			$("#rejectBtn").click(//拒绝
				function() {
					Public.ajaxPost(Public.rootPath() + '/org/rejectChange', {"id":vm.keyid}, function (json) {
						if (json.status == 001) {
							top.openeasydialog("success",json.msg);
							vm.data.status=-1;
						} else {
							top.openeasydialog("error", json.msg);
						}
					});
				}
			);
						
		}
			
	};
	function orgPickLeader(){
		top.openbuttondialog("选择领导",400, 400, 
				"system/orgManage/orgPickLeader.html?orgid="+vm.keyid,false, function(item, dialog) {
					dialog.frame.$("#ok").trigger("click");
					vm.orgLeader.orgid = vm.keyid;
					vm.orgLeader.userid=dialog.frame.$("#keyids").val();
					dialog.frame.$("#clear").trigger("click");
					if(!vm.orgLeader.userid.length>0){//如果不选择，
						dialog.close();
						return;
					}
					Public.ajaxPost(Public.rootPath() + '/orgleader/orgaddleader', vm.orgLeader.$model, function (json) {
	                    if (json.status == 001) {
	                        dialog.close();
	                        loadLabor();//显示新增的记录
	                    } else {
	                    	top.openeasydialog("error", json.msg);
	                    }
	                });
	            });
		
	}
	function orgAddDep() {
		top.openbuttondialog("添加部门", 450, 550,
			"system/orgManage/depAdd.html?orgid="+vm.keyid,false, function(item, dialog) {
				vm.depdata.departmentname = dialog.frame.$("#departmentname").val();
				vm.depdata.departmenton = dialog.frame.$("#departmenton").val();
				vm.depdata.parentname = dialog.frame.$("#parentname").val();
				vm.depdata.parentid = dialog.frame.$("#parentid").val();
				vm.depdata.departmentworkarea = dialog.frame.$("#departmentworkarea").val();
				vm.depdata.departmentleader = dialog.frame.$("#departmentleader").val();
				vm.depdata.departmentphone = dialog.frame.$("#departmentphone").val();
				vm.depdata.deporder = dialog.frame.$("#departmentorder").val();
				vm.depdata.orgkeyid=vm.keyid;
				vm.depdata.orgname=vm.data.org_fullname;
				if(vm.depdata.departmentname==""||vm.depdata.departmentname.length<0){
					top.openeasydialog("warn", "请输入部门名称！");
                	return;
				}
				Public.ajaxPost(Public.rootPath() + "/org/dep/add", vm.depdata.$model, function(json) {
					if (json.status == "001") {
						vm.depdata=json.data;
						dialog.close();
						manager.loadData(vm.keyid);
					} else {
						top.openeasydialog("error",json.msg);
					}
				});
			    
			});
	}
	
	function depDepartment(rowindex){//部门删除功能
		top.openeasydialog("warning","确认要删除部门吗？", function(yes) {
					if (yes) {
						var selectedRow=manager.getSelectedRow();
						Public.ajaxPost(Public.rootPath()
								+ "/org/dep/deleteOrgDep", "keyid="
								+ selectedRow.keyid, function(json) {
							if (json.status == 001) {
								manager.deleteRow(rowindex);	
							}else if(json.status == 800){
								top.openeasydialog("error",json.msg);
							}else if(json.status == 801){
								top.openeasydialog("error",json.msg);
							}else {
								top.openeasydialog("error","删除部门时出错");
							}
						});
					}
		});
    }
	
	function editDepartment(rowindex){//部门编辑功能
		var selectedRow=manager.getSelectedRow();
		top.openbuttondialog("编辑部门", 500, 600,
				"system/orgManage/depAdd.html?keyid="+selectedRow.keyid,
				false, function(item, dialog) {
					vm.depdata.departmentname = dialog.frame.$("#departmentname").val();
					vm.depdata.departmenton = dialog.frame.$("#departmenton").val();
					vm.depdata.parentname = dialog.frame.$("#parentname").val();
					vm.depdata.parentid = dialog.frame.$("#parentid").val();
					vm.depdata.departmentworkarea = dialog.frame.$("#departmentworkarea").val();
					vm.depdata.departmentleader = dialog.frame.$("#departmentleader").val();
					vm.depdata.departmentphone = dialog.frame.$("#departmentphone").val();
					vm.depdata.keyid = dialog.frame.$("#keyid").val();
					vm.depdata.deporder = dialog.frame.$("#departmentorder").val();
					vm.depdata.orgkeyid=vm.keyid;
					vm.depdata.orgname=vm.data.org_fullname;
					if(vm.depdata.departmentname==""||vm.depdata.departmentname.length<0){
						top.openeasydialog("warn", "请输入部门名称！");
	                	return;
					}
					Public.ajaxPost(Public.rootPath() + "/org/dep/edit", vm.depdata.$model, function(json) {
						if (json.status == "001") {
							vm.depdata=json.data;
							dialog.close();
							manager.loadData(vm.keyid);
						} else {
							top.openeasydialog("error",json.msg);
						}
					});
				});
 }
	
	function postData() {//点击确认保存后展示数据的方法,postData
		var url = null;
		if (vm.keyid != "" && vm.keyid != null && vm.keyid != undefined) {
			url = Public.rootPath() + "/org/edit";
		} else {
			url = Public.rootPath() + "/org/add";
		}
		Public.ajaxPost(url, vm.data.$model, function(json) {
			if (json.status == 001) {
				//vm.data=json.data;
				top.openeasydialog("success",json.msg);
				vm.data.status=2;
			} else {
				top.openeasydialog("error", json.msg);
			}
		});

	}
	
	function selectArea(){
        top.openbuttondialog(
                "选择区域", 400, 400,
                "system/areaManage/regionTreePickByUserRe.html", false,
                function(item, dialog) {
                    vm.data.regionname = dialog.frame.$("#region").val();
                    vm.data.regioncode = dialog.frame.$("#regioncode").val();
                    dialog.hide();
                });
    }

	function initValidator() {//初始化验证
		$("#base_form").validator({
			messages : {
				required : "请填写{0}"
			},
			display : function(e) {
				var text = $(e).closest(".row-item").find("label").text().trim();
				return text.substring(0, text.length - 2);
			},
			valid : function() {
				postData();
			},
			ignore : ":hidden",
			theme : "yellow_bottom",
			timely : 1,
			stopOnError : true
		});
	}
	
	function deleteleader(keyid){//删除领导方法
		top.openeasydialog("warning","确认要删除吗",function(yes){
			if(yes){
				var url = "";
    			url=Public.rootPath() + "/orgleader/delete";	            			
    			 Public.ajaxPost(url,"keyid=" + keyid, function (json) {
    				 if (json.status == 001) {  					 	
    					loadLabor();  					 	
    					} else {
    							top.openeasydialog("error", json.msg);
    						}
    					});
		            }
		});
	}
	
	function loadLabor(){//显示领导方法
        Public.ajaxPost(
        		Public.rootPath() + '/orgleader/listLeader', {'orgid': vm.keyid}, function (json) {
		            if (json.status == 001) {
		                vm.labor = json.data;//把后台返回的值赋给vm.labor，这样在html页面就可以用 ms-repeat="vm.labor"绑定数据循环操作，就可以用el点出对应的字段了
		            } else {
		                alert(json.msg);
		            }
        		}
        );
    }
	
	function loadDepGrid(){
		manager = $("#maingrid").ligerGrid({
			columns : [
			           	{display : 'KeyId',name : 'keyid',width : '20%',align : 'left',editor : {type : 'text'}},
						{display : '部门名称',name : 'departmentname',width : '15%',align : 'left',editor : {type : 'text'}},
						{display : '部门工作职能',name : 'departmentworkarea',width : '15%',align : 'left',editor : {type : 'text'}},
						{display : '部门领导',name : 'departmentleader',width : '15%',align : 'left',editor : {type : 'text'}},
						{display : '部门联系电话',name : 'departmentphone',width : '15%',align : 'left',editor : {type : 'text'}},
						{display : '排序',name : 'deporder',width : '15%',align : 'left',editor : {type : 'text'}},
						{display : '操作',name : 'type',align : 'left',editor : {type : 'text'},
									render : function(rowdata, rowindex, value) {
										if("isorgdep"==value && vm.data.issame){
											var h = "";											
						                    if (!rowdata._editing)//显示"编辑删除"字段的判断,非空
						                    {
						                    	h += "<a id='editdepartment' href='javascript:editDepartment(" + rowindex + ")'>修改</a> ";
						                        h += "<a id='depdepartment' href='javascript:depDepartment(" + rowindex + ")'>删除</a> "; 
						                    }
						                    return h;
										}
										
									}
						}
						],
		                width : '95%',
						height : '97%',				
						url : Public.rootPath() + "/org/dep/loadOrgDepTree2?orgid="+vm.keyid,
						dataAction: 'local',//本地排序
						usePager : false,
						method : 'get',
						alternatingRow : false,
						tree : {
							columnName : 'departmentname', idField:'keyid', parentIDField:'parentid'
						},
						checkbox : false,				
						autoCheckChildren : false,
						onAfterShowData : function(currentData) {//默认显示第一级
							for ( var i = 0; i < currentData.Rows.length; i++) {
								var row = currentData.Rows[i];
								if (row.children.length > 0) {
									this.collapse(row);
								}
							}
						}
		});
		manager.toggleCol("keyid", false); // 是否显示该列
	}
	
	$(document).ready(function(e) {
		// 需要先初始化表单验证配置
		initValidator();
		THISPAGE.init();
	});