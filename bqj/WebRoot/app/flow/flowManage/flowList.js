var vm = avalon.define({
	$id : "flowCtrl",
	selectId : [],
	tableTop : {
		title : "标题",
		time : "上报时间",
		user:"上报人员（单位）"
	},
	msg : "",
	notify : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	data : {},
	status : "",
	type : "",
	power : {
		addpower : 0,
		editpower : 0,
		delpower : 0,
		checkpower : 0
	},
	checkAll : function() {
		if (this.checked) {
			for ( var i = 0; i < vm.data.length; i++) {
				vm.selectId.push(vm.data[i].keyid+'&'+vm.data[i].flowtype);
			}
			$(".itemCheckBox").prop("checked", "true");
		} else {
			vm.selectId.clear();
			$(".itemCheckBox").removeAttr("checked");
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
		vm.status = request.queryString["status"];
		if(vm.status==1){
			vm.tableTop.time="审核时间";
		}
		//从cookie获取页码
		var flowPage = $.cookie('flowPageNumber');
		flowPage==null?1:flowPage;
		
		Public.ajaxPost(Public.rootPath() + '/flow/flowList', {
			
			'page' :flowPage,
			"status" : 0
			}, function(json) {
			if (json.status == 001) {
	        	$("#checkalls").removeAttr("checked");
				vm.data = json.data.list;
				//vm.power = json.data.rp;
				vm.selectId = [];
				THISPAGE.initPage(json.data);
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
				// 存储页码到cookie 
            	$.cookie('flowPageNumber', index, {path: '/'}); 
            	
				Public.ajaxPost(Public.rootPath() + '/flow/flowList', {
					'page' : index,
					"status" : 0
				}, function(json) {
					if (json.status == 001) {
			        	$("#checkalls").removeAttr("checked");
						vm.data = json.data.list;
						//vm.power = json.data.rp;
						vm.selectId = [];
						THISPAGE.initPage(json.data);
					} else {
						top.openeasydialog("error", json.msg);
					}
				});
			}
		});
	},
	addEvent : function() {
	}
};

function openReadFlow(id){
	
	//修改成已读状态
	Public.ajaxPost(Public.rootPath() + '/flow/openReadFlow', {
		'flowid' : id
	}, function(json) {
		if (json.status == 001) {
        	
		} else {
			top.openeasydialog("error", json.msg);
		}
	});
	
}

function pageChange(flowtype,id,id1,id2,id3){
	openReadFlow(id);
	

				 if(flowtype=="zoneDefence"){
						if(vm.status==1){//已审核
							window.location = "../../zoneDefence/zdManage/zdAdd.html?keyid="+id+"&opType=2&status=1" ;
						} else {//待审核
							window.location = "../../zoneDefence/zdManage/zdAdd.html?keyid="+id+"&opType=1&status=0" ;
						}
					}
					if(flowtype=="activity"){
						if(vm.status==1){//已审核
							window.location = "../../activity/activityManage/activityAdd.html?keyid="+id+"&opType=2&status=1&ispublic=0" ;
						} else {//待审核
							window.location = "../../activity/activityManage/activityAdd.html?keyid="+id+"&opType=1&status=0&ispublic=0";
						}
					}
					if(flowtype=="a_alarm"){
						if(vm.status==1){
							window.location = "../../appealalarm/alarmManage/alarmDetail.html?keyid="+id+"&opType=2&status=1&ispublic=0" ;
						} else {
							window.location = "../../appealalarm/alarmManage/alarmDetail.html?keyid="+id+"&opType=1&status=0&ispublic=0";
						}
					}
					if(flowtype=="a_appeal"){
						if(vm.status==1){
							window.location = "../../appealalarm/appealManage/appealDetail.html?keyid="+id+"&opType=2&status=1&ispublic=0" ;
						} else {
							window.location = "../../appealalarm/appealManage/appealDetail.html?keyid="+id+"&opType=1&status=0&ispublic=0" ;
						}
					}
					if(flowtype=="n_dynamic"){
						if(vm.status==1){
							window.location = "../../news/dynamicManage/dynamicDetail.html.html?keyid="+id+"&opType=2&status=1&ispublic=0" ;
						} else {
							window.location = "../../news/dynamicManage/dynamicDetail.html?keyid="+id+"&opType=1&status=0&ispublic=0" ;
						}
					}
					if(flowtype=="n_events"){
						if(vm.status==1){
							window.location = "../../news/eventsManage/eventsDetail.html?keyid="+id+"&opType=2&status=1&ispublic=0" ;
						} else {
							window.location = "../../news/eventsManage/eventsDetail.html?keyid="+id+"&opType=1&status=0&ispublic=0" ;
						}
					}
					if(flowtype=="c_case"){
						if(vm.status==1){
							window.location = "../../case/caseManage/caseDetail.html?keyid="+id+"&opType=2&status=1&ispublic=0" ;
						} else {
							window.location = "../../case/caseManage/caseDetail.html?keyid="+id+"&opType=1&status=0&ispublic=0" ;
						}
					}
					if(flowtype=="bulletin"){
						if(vm.status==1){//已审核
							window.location = "../../culture/bulletinManage/bulletinAdd.html?keyid="+id+"&opType=2&status=1" ;
						} else {//待审核
							window.location = "../../culture/bulletinManage/bulletinAdd.html?keyid="+id+"&opType=1&status=0" ;
						}
					}
					if(flowtype=="other"){
						if(vm.status==1){//已审核
							window.location = "../../culture/otherManage/otherAdd.html?keyid="+id+"&opType=2&status=1" ;
						} else {//待审核
							window.location = "../../culture/otherManage/otherAdd.html?keyid="+id+"&opType=1&status=0" ;
						}
					}
					if(flowtype=="show"){
						if(vm.status==1){//已审核
							window.location = "../../culture/showManage/showAdd.html?keyid="+id+"&opType=2&status=1" ;
						} else {//待审核
							window.location = "../../culture/showManage/showAdd.html?keyid="+id+"&opType=1&status=0" ;
						}
					}
					if(flowtype=="silhouette"){
						if(vm.status==1){//已审核
							window.location = "../../culture/silhouetteManage/silhouetteAdd.html?keyid="+id+"&opType=2&status=1&sourceid="+id1;
						} else {//待审核
							window.location = "../../culture/silhouetteManage/silhouetteAdd.html?keyid="+id+"&opType=1&status=0&sourceid="+id1;
						}
					}
					if(flowtype=="video"){
						if(vm.status==1){//已审核
							window.location = "../../culture/videoManage/videoAdd.html?keyid="+id+"&opType=2&status=1&videoimgid="+id2+"&videosourceid="+id3 ;
						} else {//待审核
							window.location = "../../culture/videoManage/videoAdd.html?keyid="+id+"&opType=1&status=0&videoimgid="+id2+"&videosourceid="+id3 ;
						}
					}
					if(flowtype=="formula"){
						if(vm.status==1){//已审核
							window.location = "../../case/formulaManage/formulaAdd.html?keyid="+id+"&opType=2&status=1" ;
						} else {//待审核
							window.location = "../../case/formulaManage/formulaAdd.html?keyid="+id+"&opType=1&status=0" ;
						}
					}
					if(flowtype=="document"){
						if(vm.status==1){//已审核
							window.location = "../../docNotice/docManage/docAdd.html?keyid="+id+"&opType=2&status=1" ;
						} else {//待审核
							window.location = "../../docNotice/docManage/docAdd.html?keyid="+id+"&opType=1&status=0" ;
						}
					}
					if(flowtype=="proposal"){
						if(vm.status==1){//已审核docNotice/noticeManage/noticeAdd.html
							window.location = "../../dataStatistics/proposalManage/proposalAdd.html?keyid="+id+"&opType=2&status=1" ;
						} else {//待审核
							window.location = "../../dataStatistics/proposalManage/proposalAdd.html?keyid="+id+"&opType=1&status=0" ;
						}
					}
					if(flowtype=="statistics"){
						if(vm.status==1){//已审核docNotice/noticeManage/noticeAdd.html
							window.location = "../../dataStatistics/statisticsManage/statisticsAdd.html?keyid="+id+"&opType=2&status=1" ;
						} else {//待审核
							window.location = "../../dataStatistics/statisticsManage/statisticsAdd.html?keyid="+id+"&opType=1&status=0" ;
						}
					}
					if(flowtype=="ledger"){
						if(vm.status==1){//已审核docNotice/noticeManage/noticeAdd.html
							window.location = "../../dataStatistics/ledgerManage/ledgerAdd.html?keyid="+id+"&opType=2&status=1" ;
						} else {//待审核
							window.location = "../../dataStatistics/ledgerManage/ledgerAdd.html?keyid="+id+"&opType=1&status=0" ;
						}
					}
					if(flowtype=="notice"){
						if(vm.status==1){//已审核docNotice/noticeManage/noticeAdd.html
							window.location = "../../docNotice/noticeManage/noticeAdd.html?keyid="+id+"&opType=2&status=1" ;
						} else {//待审核
							window.location = "../../docNotice/noticeManage/noticeAdd.html?keyid="+id+"&opType=1&status=0" ;
						}
					}
			
	
					
	
}
function ReadFlow(id){
	readFlow(id);
	THISPAGE.init();
}
function FlowApprove(flowtype,id){
	flowApprove(flowtype,id);
	THISPAGE.init();
}

//批量审核通过
function FlowApproves(){
	if(vm.selectId.length==0){
		top.openeasydialog("error", "请至少选择一条记录!");
		return;
	}
	
	var sueecss=0;
	var err=0;
	for(var i=0;i<vm.selectId.length;i++){
		var id=vm.selectId[i].split('&')[0];
		var type=vm.selectId[i].split('&')[1];
		if(flowApprove(type,id,true)){
			sueecss++;
		}else{
			err++;
		}
	}
	
	alert('操作成功'+sueecss+'条记录,失败'+err+'条！');
	THISPAGE.init();
}
//批量审核未通过
function ReadFlows(){
	if(vm.selectId.length==0){
		top.openeasydialog("error", "请至少选择一条记录!");
		return;
	}
	var sueecss=0;
	var err=0;
	for(var i=0;i<vm.selectId.length;i++){
		var id=vm.selectId[i].split('&')[0];
		var type=vm.selectId[i].split('&')[1];
		if(readFlow(id,true)){
			sueecss++;
		}else{
			err++;
		}
	}
	
	alert('操作成功'+sueecss+'条记录,失败'+err+'条！');
	THISPAGE.init();
}
$(document).ready(function () {
    	THISPAGE.init();
});