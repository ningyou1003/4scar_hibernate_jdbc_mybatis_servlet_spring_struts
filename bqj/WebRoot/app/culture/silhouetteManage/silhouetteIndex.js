var vm = avalon.define({
	$id : "silhouetteIndexCtrl",
	data : [],
	year : "",
	
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
			vm.year = request.queryString["year"];
			Public.ajaxPost(Public.rootPath() + '/area/getRegionListToSilhouette' , {
				'pageIndex' : 0
			},function(json){
				if(json.status == 001) {
					vm.data = json.data;
				} else {
					alert(json.msg);
				}
			});
		},
		addEvent : function() {
			
		},
};


//转到墙报页
function nextPage(regionCode,parentCode) {
	window.location.href = "silhouettePage.html?regionCode=" + regionCode + "&parentCode=" + parentCode +"&year=" + vm.year;
}

//返回年份页
function backYear() {
	window.location.href = "silhouetteYear.html";
}

$(document).ready(function () {
	THISPAGE.init();
});
