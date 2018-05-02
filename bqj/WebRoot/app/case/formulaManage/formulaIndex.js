var vm = avalon.define({
	$id : "formulaIndexCtrl",
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
			Public.ajaxPost(Public.rootPath() + '/formula/regionList' , {
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


//转到公式列表页
function nextPage(regionCode) {
	window.location.href = "formulaPage.html?regionCode=" + regionCode + "&year=" + vm.year;
}

//返回年份页
function backYear() {
	window.location.href = "formulaYear.html";
}

$(document).ready(function () {
	THISPAGE.init();
});
