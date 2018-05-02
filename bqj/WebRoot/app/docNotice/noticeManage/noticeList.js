var vm = avalon.define({
	$id : "noticeCtrl",
	selectId : [],
	tableTop : {
		noticetitle : "通知标题",
		publishtime : "发布时间",
		publisher : "发布人",
		option : "操作"
	},
	msg : "",
	notify : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	data : {
		keyid : ""
	},
	regioncode : "",
	power : {
		addpower : 0,
		editpower : 0,
		delpower : 0,
		checkpower : 0
	},
	enroldata:{
		keyid:"",
		type:1,
		flowid:"",
		
		name:"",
		sex:"",
		peoples:"",
		phone:"",
		duty:"",
		transportation:"",
		arrivetime:"",
		arrivesite:"",
		ps:""
	},
	checkAll : function() {
		if (this.checked) {
			for ( var i = 0; i < vm.data.length; i++) {
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
	loadGrid : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.regioncode = vm.data.keyid = request.queryString["keyid"];
		}
		//从cookie获取页码
		var docNoticePage = $.cookie('docNoticePageNumber');
		docNoticePage==null?1:docNoticePage;
		
		Public.ajaxPost(Public.rootPath() + '/docNotice/noticeList', {
			'page' : docNoticePage
			}, function(json) {
			if (json.status == 001) {
	        	$("#checkalls").removeAttr("checked");
				vm.data = json.data.page.list;
				vm.power = json.data.rp;
				vm.selectId = [];
				THISPAGE.initPage(json.data.page);
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
            	$.cookie('docNoticePageNumber', index, {path: '/'}); 
            	
				Public.ajaxPost(Public.rootPath() + '/docNotice/noticeList', {
					'page' : index
				}, function(json) {
					if (json.status == 001) {
			        	$("#checkalls").removeAttr("checked");
						vm.data = json.data.page.list;
						vm.power = json.data.rp;
						vm.selectId = [];
					} else {
						top.openeasydialog("error", json.msg);
					}
				});
			}
		});
	},
	addEvent : function() {
		$("#edit").click(function(t) {// 查看
			if (vm.selectId.length == 0) {
				top.openeasydialog("warn", "请选择要编辑的通知");
			} else {
				if (vm.selectId.length == 1) {
					window.location = "noticeAdd.html?keyid=" + vm.selectId[0];
				} else {
					top.openeasydialog("warn", "请不要一次选择多个通知编辑");
				}
			}
		});

		$("#add").click(function(t) {// 添加
			window.location = "noticeAdd.html" ;
		});

		$("#delete").click(
				function(t) {

					if (vm.selectId.length == 0) {
						top.openeasydialog("warn", "请选择要删除的通知");
					} else {
						top.openeasydialog("warning", "确认要删除吗？",
								function(yes) {
									if (yes) {
										Public.ajaxPost(Public.rootPath()
												+ "/docNotice/delete", "keyids="
												+ vm.selectId, function(json) {
											if (json.status == 001) {
												THISPAGE.loadGrid();
												window.parent.parent.frames["rightFrame"].frames["showmessage"].location.reload();
											} else {
												top.openeasydialog("error",
														"删除通知时出错");
											}
										});
									}
								});
					}
				});
	}
};
/*function approve(keyid){
	flowSend("notice", keyid);
}*/
function approve(keyid){
	flowSend("notice", keyid);
}
function issued(keyid){
	$.cookie("issued",true,{ path: '/' });
	flowissued("notice", keyid);
}

function summation(){
	Public.ajaxPost(Public.rootPath()
			+ "/docNotice/summation", [], function(json) {
		if (json.status == 001) {
			var h="";
			for(var i=0;i<json.data.length;i++){
				h="<tr><td>"+json.data[i].time+"</td><td>"+json.data[i].sum+"</td></tr>"+h;
			}
			$("#huizong").html(h);
			$("#huizongdiv").css("display","");
		} else {
			top.openeasydialog("error",
					"汇总异常");
		}
	});
	
	
}


//弹出报名界面
function baoming(id,title){
	vm.enroldata.flowid=id;//获得当前要报名事项的id
	$("#enrols").html("");
	Public.ajaxPost(Public.rootPath()+ "/enrol/list", 
			{flowid:vm.enroldata.flowid},
			function(json) {
		if (json.status == 001) {
			for ( var i = 0; i < json.data.length; i++) {
			var html="";
			html+="<tr><td >"+json.data[i].name+"</td>";
			if (json.data[i].sex==0) {
				json.data[i].sex="男";
			}else {
				json.data[i].sex="女";
			}
			html+="<td >"+json.data[i].sex+"</td>";
			html+="<td >"+json.data[i].peoples+"</td>";
			html+="	<td >"+json.data[i].phone+"</td>";
			html+="	<td >"+json.data[i].duty+"</td>";
			html+="	<td >"+json.data[i].transportation+"</td>";
			html+="	<td >"+json.data[i].arrivetime+"</td>";
			html+="	<td >"+json.data[i].arrivesite+"</td>";
			html+="	<td >"+json.data[i].ps+"</td>";
			html+="<td >";
			html+="	<ul class=\"toolbar\">";
			html+="	<li id=\"deleteEnrol\" onclick=\"deleteenrol(this)\">";
			html+="		<span>";
			html+="			<img  src=\"../../../images/t03.png\" />";
			html+="		</span>";
			html+="	</li>";
			html+="</ul>";
			html+="<input type=\"hidden\" name=\"enrolkey\" value=\""+json.data[i].keyid+"\"/>";
			html+="</td><tr>";
			$("#enrols").append(html);
			}
			
		} else {
			top.openeasydialog("error",
					name+"报名异常");
		}
		
		
	});
	
	$("#mengban").css("display","");
	$("#ernol").css("display","");
	$("#ernoltitle").html(title+'-报名');
	
}
//新建新一行来添加报名人
function newTrErnol(){
	var html="";
	html+="<tr><td ><input style=\"width: 130px;margin: 0 0;padding: 0 0;\" name=\"enrolname\"  type=\"text\" maxlength=\"20\" class=\"dfinput\"  placeholder=\"姓名\" /></td>";
	html+="<td ><select name=\"enrolsex\"  style=\"width: 40px;height: 32px;border: 1px solid #a7b5bc;background-color: #fff;\">";
	html+="					<option  value=\"0\">男</option>";
	html+="					<option  value=\"1\">女</option>";
	html+="				</select></td>";
	html+="<td ><input style=\"width: 80px;margin: 0 0;padding: 0 0;\"   type=\"text\" maxlength=\"20\"  class=\"dfinput\"  placeholder=\"民族\" /></td>";
	html+="	<td ><input style=\"width: 110px;margin: 0 0;padding: 0 0;\"   type=\"text\" maxlength=\"11\"  class=\"dfinput\"  placeholder=\"联系方式\" onkeyup=\"this.value=this.value.replace(\/[^\\d]\/g,'') \" onafterpaste=\"this.value=this.value.replace(\/[^\\d]\/g,'') \" /></td>";
	html+="	<td><input style=\"width: 95px;margin: 0 0;padding: 0 0;\"   type=\"text\" maxlength=\"50\"  class=\"dfinput\"  placeholder=\"职务\" /></td>";
	html+="	<td><input style=\"width: 95px;margin: 0 0;padding: 0 0;\"   type=\"text\" maxlength=\"50\"  class=\"dfinput\"  placeholder=\"交通方式\" /></td>";
	html+="	<td><input  type=\"text\"  class=\"dfinput\"  maxlength=\"250\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',firstDayOfWeek:1})\" style=\"width: 150px;margin: 0 0;padding: 0 0;\" /></td>";
	html+="<td><input style=\"width: 95px;margin: 0 0;padding: 0 0;\"  type=\"text\" maxlength=\"50\"  class=\"dfinput\"  placeholder=\"到达地点\" /></td>";
	html+="<td><input style=\"width: 95px;margin: 0 0;padding: 0 0;\"  type=\"text\" maxlength=\"200\"  class=\"dfinput\"  placeholder=\"备注\" /></td>";
	html+="<td >";
	html+="	<ul class=\"toolbar\">";
	html+="	<li id=\"deleteEnrol\" onclick=\"deleteenrol(this)\">";
	html+="		<span>";
	html+="			<img  src=\"../../../images/t03.png\" />";
	html+="		</span>";
	html+="	</li>";
	html+="</ul>";
	html+="</td></tr>";
	$("#enrols").append(html);
}

function deleteenrol(b){
	var id=$(b).parent().next().val();
	if (id!=undefined) {
		top.openeasydialog("warning", "确认要删除吗？",function(yes){
		if(yes){
		Public.ajaxPost(Public.rootPath()+ "/enrol/remove", 
				{eid:id},
				function(json) {
			if (json.status == 001) {
				$(b).parent().parent().parent().remove();
			} else {
				top.openeasydialog("error",
						json.msg);
			}
		});
		}
		});
	}else{
		$(b).parent().parent().parent().remove();
	}
	
}

function saveernol(){
	var trs=$("#enrols").children();
	for(var i=0;i<trs.length;i++){
		var b=false;
		if (trs.eq(i).children().eq(9).children().eq(1).val()==undefined) {
//			alert('保存');
			//名字
			var name=trs.eq(i).children().eq(0).children().eq(0).val();
			if ( name==undefined || name.length==0) {
				trs.eq(i).children().eq(0).children().eq(0).css("border","2px solid red");
				b=true;
			}else{
				trs.eq(i).children().eq(0).children().eq(0).css("border","1px solid #a7b5bc");
			}
			//性别
			var sex=trs.eq(i).children().eq(1).children().eq(0).val();
			//民族
			var peoples=trs.eq(i).children().eq(2).children().eq(0).val();
			if ( peoples==undefined ||  peoples.length==0 ) {
				//alert('民族空了');
				peoples="";
			}
			//联系方式
			var phone=trs.eq(i).children().eq(3).children().eq(0).val();
			var reg = new RegExp("^[0-9]*$");
			if ( phone==undefined || phone.length<7  || !reg.test(phone)) {
				trs.eq(i).children().eq(3).children().eq(0).css("border","2px solid red");
				//alert('联系方式空了');
				b=true;
			}else{
				trs.eq(i).children().eq(3).children().eq(0).css("border","1px solid #a7b5bc");
			}
			//职务
			var duty=trs.eq(i).children().eq(4).children().eq(0).val();
			if (duty==undefined ||  duty.length==0) {
				//alert('职务空了');
			}
			//交通方式
			var transportation=trs.eq(i).children().eq(5).children().eq(0).val();
			if (transportation==undefined ||  transportation.length==0) {
				//alert('交通方式空了');
			}
			//到达时间
			var arrivetime=trs.eq(i).children().eq(6).children().eq(0).val();
			if (arrivetime==undefined ||  arrivetime.length==0) {
				trs.eq(i).children().eq(6).children().eq(0).css("border","2px solid red");
				//alert('时间空了');
				b=true;
			}else{
				trs.eq(i).children().eq(6).children().eq(0).css("border","1px solid #a7b5bc");
			}
			//到达地点
			var arrivesite=trs.eq(i).children().eq(7).children().eq(0).val();
			if (arrivesite==undefined ||  arrivesite.length==0) {
				trs.eq(i).children().eq(7).children().eq(0).css("border","2px solid red");
				//alert('职务空了');
				b=true;
			}else{
				trs.eq(i).children().eq(7).children().eq(0).css("border","1px solid #a7b5bc");
			}
			//备注
			var ps=trs.eq(i).children().eq(8).children().eq(0).val();
			
			if (b) {
				continue;
			}
			
			vm.enroldata.name=name;
			vm.enroldata.sex=sex;
			vm.enroldata.peoples=peoples;
			vm.enroldata.phone=phone;
			vm.enroldata.duty=duty;
			vm.enroldata.transportation=transportation;
			vm.enroldata.arrivetime=arrivetime;
			vm.enroldata.arrivesite=arrivesite;
			vm.enroldata.ps=ps;
//			alert(name+","+sex+","+peoples+","+phone+","+duty+","+transportation+","+arrivetime+","+arrivesite+","+ps);
//			savepost(trs.eq(i),vm.enroldata);
			$.ajax({
				  type: 'POST',
				  url: Public.rootPath()+ "/enrol/add",
				  data: vm.enroldata.$model,
				  dataType: "json",
				  async: false,
				  success: function(json) {
						if (json.status == 001) {
							var html="";
							html+="<td >"+name+"</td>";
							if (sex==0) {
								sex="男";
							}else {
								sex="女";
							}
							html+="<td >"+sex+"</td>";
							html+="<td >"+peoples+"</td>";
							html+="	<td >"+phone+"</td>";
							html+="	<td >"+duty+"</td>";
							html+="	<td >"+transportation+"</td>";
							html+="	<td >"+arrivetime+"</td>";
							html+="	<td >"+arrivesite+"</td>";
							html+="	<td >"+ps+"</td>";
							html+="<td >";
							html+="	<ul class=\"toolbar\">";
							html+="	<li id=\"deleteEnrol\" onclick=\"deleteenrol(this)\">";
							html+="		<span>";
							html+="			<img  src=\"../../../images/t03.png\" />";
							html+="		</span>";
							html+="	</li>";
							html+="</ul>";
							html+="<input type=\"hidden\" name=\"enrolkey\" value=\""+json.data+"\"/>";
							html+="</td>";
							trs.eq(i).html(html);
							
						} else {
							top.openeasydialog("error",
									name+"报名异常");
						}
					}
				  
				});
		}else {
//			alert("已纯在");
		}
	}
}

//弃用
function savepost(t,data){
	
	Public.ajaxPost(Public.rootPath()+ "/enrol/add", 
			data.$model,
			function(json) {
		if (json.status == 001) {
			var html="";
			html+="<td >"+data.name+"</td>";
			if (data.sex==0) {
				data.sex="男";
			}else {
				data.sex="女";
			}
			html+="<td >"+data.sex+"</td>";
			html+="<td >"+data.peoples+"</td>";
			html+="	<td >"+data.phone+"</td>";
			html+="	<td >"+data.duty+"</td>";
			html+="	<td >"+data.transportation+"</td>";
			html+="	<td >"+data.arrivetime+"</td>";
			html+="	<td >"+data.arrivesite+"</td>";
			html+="	<td >"+data.ps+"</td>";
			html+="<td >";
			html+="	<ul class=\"toolbar\">";
			html+="	<li id=\"deleteEnrol\" >";
			html+="		<span>";
			html+="			<img onclick=\"deleteenrol(this)\" src=\"../../../images/t03.png\" />";
			html+="		</span>";
			html+="	</li>";
			html+="</ul>";
			html+="<input type=\"hidden\" name=\"enrolkey\" value=\""+json.data+"\"/>";
			html+="</td>";
			t.html(html);
			
		} else {
			top.openeasydialog("error",
					name+"报名异常");
		}
	});
}


function btback(){
	$("#huizongdiv").css("display","none");
	$("#mengban").css("display","none");
	$("#ernol").css("display","none");
}



$(document).ready(function () {
    	THISPAGE.init();
    	var areaLevel=$.cookie("areaLevel");
    	if (areaLevel!=null ) {
    		if (areaLevel==3 || areaLevel== 4) {
    			$("#issued").css("display","none");
    		}else{
    			$("#issued").css("display","");
    		}
    	}
});
