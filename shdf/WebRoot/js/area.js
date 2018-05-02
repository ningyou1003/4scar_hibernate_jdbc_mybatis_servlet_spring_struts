window.onload=function(){ 
	loadAllParentArea();
	};
			//选择区域
        	function selArea(b){
        		$("#mengban").css("display","");
        		$("#areas").css("display","");
        		var htmlsrc="TopesallAreaTreeView.html";
        		if (b) {
        			htmlsrc='../TopesallAreaTreeView.html';
				}
        		var html='';
        		html+='<iframe id="areahtml" src="'+htmlsrc+'" style="width: 300px;height: 400px;border: 0px solid red;"></iframe>';
        		if($("#areahtmlDIV").html()==undefined || $("#areahtmlDIV").html().length<20){
        			$("#areahtmlDIV").html(html);
        		}
        		
        		//alert(1);
        	}
        	//返回，
        	function breaks(){
        		$("#mengban").css("display","none");
        		$("#areas").css("display","none");
        		
        	}
        	function onSelectArea(){
        	//alert(getCookie('regioncode'));
        	var regioncode=$("#areahtml").get(0).contentWindow.document.getElementById("regioncode").value;
        	var region=$("#areahtml").get(0).contentWindow.document.getElementById("region").value;
        		//alert(regioncode);
        		newSetCookie("regioncode",regioncode,{path:"/"});
        		breaks();
        		location.reload(true);
        		
        	}
        	
        	function loadAllParentArea(){
        		var regioncode=getCookie("regioncode");
        		//alert(regioncode);
        		if (regioncode==null) {
					regioncode="450000000000";
				}
        		var rootPath="http://222.216.1.196:38229/bqj";
        		Public.ajaxPost(rootPath+ '/area/loadAllParentArea?regioncode=' + regioncode, {
    			}, function (json) {
			        if (json.status == 1) {
			            var html=json.data[0].region;
			            //alert(json.data.length)
			            for(var i=1;i<json.data.length;i++){
			            	html+='>'+json.data[i].region;
			            }
			            
			            $("#areatxt").html(html);
			        }
			    });
        	}