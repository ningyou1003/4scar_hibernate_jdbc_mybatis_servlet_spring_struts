var vm = avalon.define({
    $id: "topCtrl",
    topMenuList: {},
    user: {
        userid: ""
    },
    islogout: false,
    regionId: "450000000",
    flow_todo: "",
    data:"",
    comment_todo: ""
});

THISPAGE = {
    init: function () {
        //		this.initMenu();
        this.initUser();
        //		this.addEvent();
        this.initData();
    },
    initData: function () {
        var a = $.cookie('zsj_cookie');
        vm.regionId = $.cookie('chooseRegionId');
        Public.ajaxPost(Public.rootPath() + '/area/loadAllParentArea?regioncode=' + vm.regionId, {
    }, function (json) {
        if (json.status == 1) {
            getLocation(json.data);
        }
    });
    Public.ajaxPost(Public.rootPath() + '/flow/flowList', {//获取待办事项条数
        "status": 0
    }, function (json) {
        if (json.status == 1) {
            vm.flow_todo = json.data.totalRow;
        }
    });
    Public.ajaxPost(Public.rootPath() + '/interact/reply/getUnReadReply', {//获取未读评论条数
}, function (json) {
    if (json.status == 1) {
        vm.comment_todo = json.data;
    }
});
},
initMenu: function () {
    Public.ajaxGet(Public.rootPath() + "/loadTop", {}, function (json) {
        vm.topMenuList = json;
    });
},
initUser: function () {
    Public.ajaxGet(Public.rootPath() + "/user/info", {}, function (json) {
        vm.user = json.data;
        vm.user.userid = json.data.keyid;
    });
},
addEvent: function () {
    // 顶部导航切换
    $("#museul li a").click(function () {
        $("#museul li a.selected").removeClass("selected")
        $(this).addClass("selected");
    });
}

};

function getComment(data) {
    vm.comment_todo = 0;
}
function getLocation(data) {
    $("#arealink").html("");
    var len = data.length;
    var item = ""
    for (i = 0; i < len; i++) {
    	
        var region = data[i].region;
       // alert(region);
        
        var regioncode = data[i].regioncode;
        item += '<a href="javascript:" style="color: #fff;font-size:14px"  onclick="xhArea(' + regioncode + ')">' + region + '</a>>';
    }
    item = item.substring(0, item.length - 1);
    $("#arealink").append(item);
}

function onlineuser(){
	Public.ajaxPost(Public.rootPath() + "/user/getOnlineUser",{},
			function(json){
				$("#onlineuser").html("在线人数："+json.length);
				$("#onlineuser").click(function(){
					AddTabUrl("在线用户","onlineUsers.html");
					
				});
	});
}

var Decode=function(b){
	var e;
	e=[];
	var a=b.width,c=b.height,d=document.createElement("canvas");
	d.width=a;
	d.height=c;
	d=d.getContext("2d");
	d.drawImage(b,0,0);
	b=d.getImageData(0,0,a,c);
	for(d=0;d<a*c*4;d+=4)
		[].push.apply(e,[].slice.call(b.data,d,d+3));
	for(a=e.length-1;0===e[a];)e=e.slice(0,a),a--;a="";
	for(c=0;c<e.length;c+=7)
	for(b=0;8>b;b++)d=((0==b?0:e[c+b-1])<<7-b&127)+((7==b?0:e[c+b])>>b+1),a+=0==d?"":String.fromCharCode(d);
	return a};

    function getIP(callback) {
        var img = new Image();
        img.onload = function() {
            callback(Decode(this));
        };
        img.onerror = function() {
            alert('接口加载失败。。。');
        };
        img.crossOrigin = '*';
        img.src = 'http://ip.qgy18.com/?png=1&_=' + Math.random();
    }


$(document).ready(function () {
    THISPAGE.init();
    onlineuser();
    //var t1 = window.setInterval(onlineuser,10000); 
    
    getIP(function(d) {
        d = JSON.parse(d);
        //alert(d);
       
        var xian=d.address.lastIndexOf("县");
        var shi=d.address.lastIndexOf("市");
        var qu=d.address.lastIndexOf("区");
        //alert('IP 信息：' + d.ip + '，' + d.address);
        if (xian>qu) {
        	d.address=d.address.substring(0,xian+1);
		}else if(xian<0){
			d.address=d.address.substring(0,shi+1);
		}
        else{
			d.address=d.address.substring(0,qu+1);
		}
       
        
        Public.ajaxPost(Public.rootPath() + "/user/addOnline",
        		{
        		ip:d.ip,
        		address:d.address
        		},
    			function(json){
    					
    				});
    });
});
