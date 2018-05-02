var vm = avalon.define({
	$id : "pointCtrl",
	data : {
		keyid : "",
		regionid : "",
		lng : "",
		lat : "",
		level : "",
		title : "",
		important : "",
		address : "",
		content : "",
		outernumber : "",
		time : "",
		hasproblem : "",
		issolved : "",
		checkpicture : "",
		checkname : "",
		settle : "",
		nationalcheckpoint : ""
	},
	selectedLevel :"",
	Level : ["自治区","市","县/区"],
	currentIndex : 0,
	records : {},
	submit : function(){
		$("#base_form").trigger("validate");
	}
});
var flag = 0;
var opt = "add";
THISPAGE = {
	init : function() {
		this.initDom();
		this.initData();
		this.initEvent();
		
	},
	initEvent : function() {
		$("#addCheck").click(function() {
			addTag(flag, opt);
			flag++ ;
		});
		$("#checkRecords").bind("mouseout", function () {
			var a = JSON.stringify(getCheckValue());
			$("#valStr").val(a);
			var s = $("#valStr").val();
		});
	},
	initData : function() {
		var keyId = getUrlParam("id");
	    if(keyId!=null && keyId!="null"){
	        $("#keyId").val(keyId);
	        Public.ajaxPost(Public.rootPath() + "/record/map/info", {"keyId":keyId}, function (json) {
	            if (json.status == 001) {
	            	vm.data = json.data.rm;
	            	vm.records = json.data.recordList;
	                if(vm.data.important==1){
	                	$("#important").prop("checked",true);
	                }
	                if(vm.data.nationalcheckpoint==1){
	                    $("#nationalcheckpoint").attr("checked", true);
	                }
	                var length = vm.records.length;
	                flag = length;
	                if(length>0){
	                	opt = "edit";
	                	for(var i=0;i<length;i++){
		                	addTag(i, opt);
		                }
	                }
	                opt = "add";
	                
	            } else {
	                top.openeasydialog("error", "获取信息失败");
	            }
	        });
	    }else{
	    	Public.ajaxGet(Public.rootPath() + "/user/info",{}, function(json) {
				vm.data.level = json.data.level;
			});
	    	
	        $("#lng").val(getUrlParam("lng"));
	        $("#lat").val(getUrlParam("lat"));
	        vm.data.lng = $("#lng").val();
	        vm.data.lat = $("#lat").val();
	        var geoc = new BMap.Geocoder();
	        var point = new BMap.Point($("#lng").val(),$("#lat").val());
	        geoc.getLocation(point, function(rs){
	            var addComp = rs.addressComponents;
	            $("#address").val(addComp.city + addComp.district + addComp.street + addComp.streetNumber);
	        });
	    }
	},
	initDom : function() {
	}
};

function addTag(k, opt){	
	var inputId = "check"+k;
	var str = "#"+inputId;
	var html = '';
	if(opt=="edit"){
		if(k==0){
			html += '<li class="selectTagW">';
		} else {
			html += '<li class="">';
		}
	} 
	if(opt=="add"){
		$("#tagsW li").removeClass("selectTagW");
		html += '<li class="selectTagW">';
	}
	html += '<a href="JavaScript:void()" onclick="selectTagW(\'tagContentW'+k+'\',this)" >第'+(k+1)+'次检查</a></li>';
	$("#tagsW").append(html);
	var contentHtml = '';
	if(opt=="edit"){
		if(k==0){
			contentHtml += '<div id="tagContentW'+k+'" class="tagContentW selectTagW" ms-if-loop="currentIndex == $index" >';
		} else {
			contentHtml += '<div id="tagContentW'+k+'" class="tagContentW" ms-if-loop="currentIndex == $index" style="display: none;">';
		}
	}
	if(opt=="add"){
		$("#tagContentW .tagContentW").css("display","none");
		contentHtml += '<div id="tagContentW'+k+'" class="tagContentW selectTagW" ms-if-loop="currentIndex == $index" >';
	}
	contentHtml += '<div style="height: auto;"><table style="width: 100%"><tbody>';
	/*var tagContentHtml = '<br/><tr><td><ul class="forminfo"><li class="row-item"><input name="keyid" id="keyid" value="'+vm.records[k].keyid+'" style="display: none;/></li></ul></td></tr>';
	tagContentHtml += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">时间 ：</label><input name="time" type="text" id="time" value="'+vm.records[k].time+'" class="dfinput" onfocus="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,firstDayOfWeek:1})" style="width: 260px"/></li></ul></td></tr>';
	tagContentHtml += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">出动人次 ：</label><input name="outernumber" type="text" id="outernumber" value="'+vm.records[k].outernumber+'" class="dfinput" maxlength="250" style="width: 260px"/><font color="red">*</font></li></ul></td></tr>';
	tagContentHtml += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">是否发现问题 ：</label><input type="radio" name="hasproblem" value="1" ms-duplex-string="el.hasproblem">是<input type="radio" name="hasproblem" checked value="0" ms-duplex-string="el.hasproblem">否</li></ul></td></tr>';
	tagContentHtml += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">问题描述 ：</label><textarea id="description" name="description" cols="20" rows="10" class="dfinput" style="height:100px; width:260px;">'+vm.records[k].description+'</textarea></li></ul></td></tr>';
	tagContentHtml += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">是否解决问题 ：</label><input type="radio" name="issolved" value="1" ms-duplex-string="el.issolved">是<input type="radio" name="issolved" checked value="0" ms-duplex-string="el.issolved">否</li></ul></td></tr>';
	tagContentHtml += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">检查照片 ：</label><input name="checkPicture" type="text" id="checkPicture" value="'+vm.records[k].checkPicture+'" class="dfinput" maxlength="250" data-rule="required;" style="width: 260px"/><font color="red">*</font></li></ul></td></tr>';
	contentHtml += tagContentHtml;*/
	contentHtml += getTagContent(k)
	contentHtml += '</tbody></table></div></div>';
	$("#tagContentW").append(contentHtml);
	var realRelateId = vm.data.keyid + k;
	initUpload("map", realRelateId);
}

function deleteTag(id){
	var str = "#"+id;
	$(str).closest('tr').remove();
}

function selectTagW(showContent, selfObj) {
    var tag = document.getElementById("tagsW").getElementsByTagName("li");
    var taglength = tag.length;
    for (i = 0; i < taglength; i++) {
        tag[i].className = "";
    }
    selfObj.parentNode.className = "selectTagW";
    for (i = 0; j = document.getElementById("tagContentW" + i); i++) {
        j.style.display = "none";
        if ("tagContentW" + i == showContent) window.GridsumSnapshotID = i;
    }
    document.getElementById(showContent).style.display = "block";
}

function getTagContent(k){
	var val ;
	if(vm.records[k] == null || vm.records[k]==undefined){
		val = {keyid:"", time:"", outernumber:"", hasproblem:0, description:"", issolved:0,settle:"", checkPicture:"",checkname:""};
	}else{
		val = vm.records[k];
	}
	if(val.description==null) val.description="";
	if(val.checkname==null) val.checkname="";
	if(val.outernumber==null) val.outernumber="";
	if(val.time==null) val.time="";
	if(val.settle==null)val.settle="";
	if(val.checkPicture==null) val.checkPicture="";
	var html = '<br/><tr><td><ul class="forminfo"><li class="row-item"><input name="keyid" id="keyid" value="'+val.keyid+'" style="display: none;/></li></ul></td></tr>';
	html += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">时间 ：</label><input name="time" type="text" id="time" value="'+val.time+'" class="dfinput" onfocus="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,firstDayOfWeek:1})" style="width: 260px"/></li></ul></td></tr>';
	html += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">检查人员 ：</label><input name="checkName" type="text" id="checkName" value="'+val.checkname+'" class="dfinput" maxlength="250" style="width: 260px"/><font color="red">*</font></li></ul></td></tr>';
	html += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">出动人次 ：</label><input name="outernumber" type="text" id="outernumber" value="'+val.outernumber+'" class="dfinput" maxlength="250" style="width: 260px"/><font color="red">*</font></li></ul></td></tr>';
	html += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">是否发现问题 ：</label> ';
	if(val.hasproblem=='1'){
		html += '<input type="radio" id="hasproblem" name="hasproblem'+val.keyid+'" value="1" checked >是<input type="radio" id="hasproblem" name="hasproblem'+val.keyid+'" value="0" >否';
	}
	else{
		html += '<input type="radio" id="hasproblem" name="hasproblem'+val.keyid+'" value="1" >是<input type="radio" id="hasproblem" name="hasproblem'+val.keyid+'" value="0" checked>否';
	}
	
	html += '</li></ul></td></tr>';
	html += '<script type="text/javascrpt"> var radiovar = document.getElementsByTagName("input"); for(var i=0;i<radiovar.length;i++){if()}</script>';
	html += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">问题描述 ：</label><textarea id="description" name="description" cols="20" rows="10" class="dfinput" style="height:100px; width:260px;">'+val.description+'</textarea></li></ul></td></tr>';
	html += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">是否解决问题 ：</label>';
	if(val.issolved=='1'){
		html += '<input type="radio" id="issolved" name="issolved'+val.keyid+'" value="1" checked>是<input type="radio" id="issolved" name="issolved'+val.keyid+'" value="0" >否';
	} else {
		html += '<input type="radio" id="issolved" name="issolved'+val.keyid+'" value="1" >是<input type="radio" id="issolved" name="issolved'+val.keyid+'" value="0" checked>否';
	}
	html += '</li></ul></td></tr>';
	html += '<tr><td><ul class="forminfo"><li class="row-item"><label for="">解决情况 ：</label><textarea id="settle" name="settle" cols="20" rows="10" class="dfinput" style="height:100px; width:260px;">'+val.settle+'</textarea></li></ul></td></tr>';
	html += '<tr><td><ul class="forminfo"><li class="row-item">';
	//html += '<label for="">检查照片 ：</label><input name="checkPicture" type="text" id="checkPicture" value="' + val.checkPicture + '" class="dfinput" maxlength="250" data-rule="required;" style="width: 260px"/><font color="red">*</font>';
	html += '<label for="">检查照片 ：</label><div style="display: inline; float: left;"><form>';
	html += '<div style="display: inline;"><span id="spanButtonPlaceholder"></span>';
	html += '<input id="btnUpload' + vm.data.keyid + k + '" type="button" value="上  传" onclick="startUploadFile();" class="btn" style="height:28px;width:50px"/>';
	html += '<input id="btnCancel' + vm.data.keyid + k + '" type="button" value="取  消" onclick="cancelUpload();" disabled="disabled" class="btn1" style="height:28px;width:50px"/></div>';
	html += '</form>';
	html += '<div id="divFileProgressContainer" style="display: none;"></div>';
	html += '<div id="thumbnails' + vm.data.keyid + k + '"><table id="infoTable' + vm.data.keyid + k + '" border="0" width="530" style="display: inline; padding: 2px;"></table>';
	html += '</div>';
	html += '</div>';
	html += '</li></ul></td></tr>';
	html += '<tr><td><ul id="attList' + vm.data.keyid + k + '" class="imgList"><li></li></ul></td></tr>';
//	html += '<tr><td><ul class="forminfo"><li class="row-item" id="kindeditor" ><label for="msg"> 活动内容 ：</label><textarea name="content" style="width:400px;height:300px;clear: both;"></textarea></li></ul></td></tr>';

	return html;
}

function getCheckValue(){
	 var o = {recordsList:[]};
	 $('#checkRecords').find('table').each(function () {
		 var item = {}; 
		 $('input', this).each(function () { 
			 
			 if(this.id=="hasproblem"||this.id=="issolved"){
//			 if((this.name.indexOf("hasproblem") > 0)||(this.name.indexOf("issolved") > 0)){
				 var temp = document.getElementsByName(this.name);
				  for(var i=0;i<temp.length;i++)
				  {
				     if(temp[i].checked)
				    	 item[this.id] = temp[i].value;
				  }
			 } else {
				 item[this.name] = this.value ;
			 }
		 }); 
		 $('textarea', this).each(function () { 
			 item[this.name] = this.value ;
		 }); 
		 o.recordsList.push(item)
	 });
	 return o;
	    
}
// 获取url中的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}

function initValidator() {
	$("#base_form").validator({
		messages : {
			required : "请填写{0}"
		},
		display : function(e) {
			var text = $(e).closest(".row-item").find("label").text().trim();
			return text.substring(0, text.length - 2);
		},
		/*valid : function() {
			postData();
		},*/
		ignore : ":hidden",
		theme : "yellow_bottom",
		timely : 1,
		stopOnError : true
	});
}
        

$(document).ready(function(e) {
	// 需要先初始化表单验证配置
	initValidator();
	THISPAGE.init();

});
