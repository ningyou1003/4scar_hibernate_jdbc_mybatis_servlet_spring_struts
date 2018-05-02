/**
 * Created by ym on 16-5-19.
 */
var table;
var kid;
var cType;
function flowApprove(type, id){
	Public.ajaxPost(Public.rootPath() + "/flow/approve", {
    	"FlowType" : type,
    	"FlowId" : id
    }, function(json) {
		if (json.status == 001) {
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
//				
			});
		} else {
			top.openeasydialog("error",json.msg);
		}
	});
	
}
//批量审核通过
function flowApprove(type, id,b){
	var ok=null;
    $.ajax({async:false//要设置为同步的，要不CheckUserName的返回值永远为false
        ,url:Public.rootPath() + "/flow/approve"
        ,type:"POST"
        ,data:{"FlowType" : type,
        	"FlowId" : id}
        ,success:function(json) {
		if (json.status == 001) {
			ok=true;
		} else{
			ok=false;
		}
        }
        });
	return ok;
	
	
}

function readFlow(id){
	
	Public.ajaxPost(Public.rootPath()+"/flow/readFlow",
			 {flowid:id},
			 function(json){ 
				 if (json.status == 001) {
						top.openeasydialog("success", json.msg, function(item, dialog){
							dialog.close();
//							
						});
					} else {
						top.openeasydialog("error",json.msg);
					}
				 
			 });
}
//批量未审核通过
function readFlow(id,b){	
	var ok=null;
    $.ajax({async:false//要设置为同步的，要不CheckUserName的返回值永远为false
        ,url:Public.rootPath() + "/flow/readFlow"
        ,type:"POST"
        ,data:{flowid:id}
        ,success:function(json) {
		if (json.status == 001) {
			ok=true;
		} else{
			ok=false;
		}
        }
        });
	return ok;
	
}


function flowNewApprove(type, id){
	Public.ajaxPost(Public.rootPath() + "/flow/newapprove", {
    	"FlowType" : type,
    	"FlowId" : id
    }, function(json) {
		if (json.status == 001) {
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
			});
		} else {
			top.openeasydialog("error",json.msg);
		}
	});
}

function flowSend(type, id, typeid){
    //寮瑰嚭浜哄憳閫夋嫨妗�
    top.openSelUser("选择上报人员", 480, 510,
    		"flow/flowManage/selectUser.html",
                false, $("#personsid").val(), $("#personsname").val(), function (item, dialog) {
                    dialog.frame.$("#ok").trigger("click");
                    var UserId = [];
                    UserId = dialog.frame.$("#keyids").val();
                    if(UserId!=""){
                    	dialog.close();
    				    Public.ajaxPost(Public.rootPath() + "/flow/flowSend", {
    				    	"FlowType" : type,
    				    	"FlowId" : id,
    				    	"UserId" : UserId
    				    }, function(json) {
    						if (json.status == 001) {
    							top.openeasydialog("success","上报成功！",function(item, dialog){
    								if(type=="activity"){
        								location.href=htmlRootPath+"/app/activity/activityManage/activityList.html?type="+typeid;
        							}
    								if(type=="zoneDefence"){
        								location.href=htmlRootPath+"/app/zoneDefence/zdManage/zdList.html" ;
        							} else {
        								dialog.close();
        							}
/*    								if(type == "bulletin") {
    									location.href = htmlRootPath + "/app/culture/bulletinManage/bulletinList.html";
    								}
    								if(type == "other") {
    									location.href = htmlRootPath + "/app/culture/otherManage/otherList.html";
    								}
    								if(type == "show") {
    									location.href = htmlRootPath + "/app/culture/showManage/showList.html";
    								}
    								if(type == "silhouette") {
    									location.href = htmlRootPath + "/app/culture/silhouetteManage/silhouetteList.html";
    								}
    								if(type == "video") {
    									location.href = htmlRootPath + "/app/culture/videoManage/videoList.html";
    								}
    								if(type == "ledger") {
    									location.href = htmlRootPath + "/app/dataStatistics/ledgerManage/ledgerList.html";
    								}
    								if(type == "statistics") {
    									location.href = htmlRootPath + "/app/dataStatistics/statisticsManage/statisticsList.html";
    								}
    								if(type == "proposal") {
    									location.href = htmlRootPath + "/app/dataStatistics/proposalManage/proposalList.html";
    								}*/
    							});
    						} else {
    							top.openeasydialog("error",json.msg);
    						}
    					});
                    }
				    
                }, 
             null);
}

function flowissued(type, id, typeid){
    //寮瑰嚭浜哄憳閫夋嫨妗�
    top.openSelUser("閫夋嫨浜哄憳", 480, 510,
    		"flow/flowManage/selectUser.html",
                false, $("#personsid").val(), $("#personsname").val(), function (item, dialog) {
                    dialog.frame.$("#ok").trigger("click");
                    var UserId = [];
                    UserId = dialog.frame.$("#keyids").val();
                    if(UserId!=""){
                    	dialog.close();
    				    Public.ajaxPost(Public.rootPath() + "/flow/flowIssued", {
    				    	"FlowType" : type,
    				    	"FlowId" : id,
    				    	"UserId" : UserId,
    				    	"issued" : true
    				    }, function(json) {
    						if (json.status == 001) {
    							top.openeasydialog("success","下发成功！",function(item, dialog){
    								if(type=="activity"){
        								location.href=htmlRootPath+"/app/activity/activityManage/activityList.html?type="+typeid;
        							}
    								if(type=="zoneDefence"){
        								location.href=htmlRootPath+"/app/zoneDefence/zdManage/zdList.html" ;
        							} else {
        								dialog.close();
        							}
    							});
    						} else {
    							top.openeasydialog("error",json.msg);
    						}
    					});
                    }
				    
                }, 
             null);
}

function flowNewSend(type, id){//缂栬緫椤甸潰璋冪敤
    top.openSelUser("上报人员选择", 480, 510,
    		"flow/flowManage/selectUser.html",
                false, $("#personsid").val(), $("#personsname").val(), function (item, dialog) {
                    dialog.frame.$("#ok").trigger("click");
                    var UserId = [];
                    UserId = dialog.frame.$("#keyids").val();
                    if(UserId!=""){
                    	dialog.close();
    				    Public.ajaxPost(Public.rootPath() + "/flow/flowSend", {
    				    	"FlowType" : type,
    				    	"FlowId" : id,
    				    	"UserId" : UserId
    				    }, function(json) {
    						if (json.status == 001) {
    							table =json.data.flowtype;
    							kid = json.data.flowid;
    							top.openeasydialog("success",json.msg,function(){
    								if(table=="a_alarm"){
        								location.href=htmlRootPath+"/app/appealalarm/alarmManage/alarmList.html";
        							}
    								if(table=="a_appeal"){
    									location.href=htmlRootPath+"/app/appealalarm/appealManage/appealList.html";
    								}
    								if(table=="n_dynamic"){
    									location.href=htmlRootPath+"/app/news/dynamicManage/dynamicList.html";
    								}
    								if(table=="n_events"){
    									location.href=htmlRootPath+"/app/news/eventsManage/eventsList.html";
    								}
    								if(table=="n_history"){
    									location.href=htmlRootPath+"/app/news/historyManage/historyList.html";
    								}
    								if(table=="n_leader"){
    									location.href=htmlRootPath+"/app/news/leaderManage/leaderList.html";
    								}
    								if(table=="c_case"){
        								getType(table,kid);
        							}
    							});
    						} else {
    							top.openeasydialog("error",json.msg);
    						}
    					});
                    }
				    
                }, 
             null);
}

function getType(table,kid){
	Public.ajaxPost(
    		Public.rootPath() + '/flow/getType', {
    			"Ftable" : table,
		    	"Kid" : kid
    			}, function (json) {
	            if (json.status == "001") {
	            	cType = json.data;
	            	location.href=htmlRootPath+"/app/case/caseManage/caseList.html?type="+cType;
	            } else {
	                alert(json.msg);
	            }
    		}
    );
}


function flowBack(type, id){

}

$(function(){
var regionid=$.cookie("regionCode");
if (regionid!=null && regionid=='450000000000') {
	$("#report").css("display","none");
}else {
	$("#report").css("display","");
}
});