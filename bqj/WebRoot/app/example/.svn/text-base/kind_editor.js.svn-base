var vm = avalon.define({
	$id : "keCtrl",
	data : {
		keyid : "",
		content : ""
	},
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	submit : function(){
		//$("#base_form").trigger("validate");
		postData();
	}
});
var kindeditor;

THISPAGE = {
	init : function() {
		this.initDom();
		this.initData();
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/xxxx/xxxx", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data.act;
					kindeditor.html(vm.data.content);
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		}
	},
	initDom : function() {
	}
};
function postData() {
	vm.data.content = kindeditor.html();
	var url= Public.rootPath() + "/example/save";
	Public.ajaxPost(url, vm.data.$model, function(json) {
		if (json.status == 001) {
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
			});
		} else {
			top.openeasydialog("error", json.msg);
		}
	});
}
function KE(){
	kindeditor = KindEditor.create('textarea[name="content"]', {
		resizeType : 1,
		uploadJson : Public.rootPath() + "/ke/fileUpload",
		fileManagerJson : Public.rootPath() + "/ke/fileManager",
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		allowImageRemote : false,
		cssPath : Public.rootPath() + "/assets/js/plugins/kindeditor/plugins/image/imgAuto.css",//图片自适应编辑器
		/*items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']*/
		afterChange : function(e){
			df();
		}
	});
}
$(document).ready(function(e) {
	KE();
	THISPAGE.init();
});
