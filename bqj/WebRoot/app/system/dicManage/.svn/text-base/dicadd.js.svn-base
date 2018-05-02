var vm = avalon.define({
	$id : "dicaddCtrl",
	data : {
		keyid : "",
		dictionaryname : "",
		dictionarytype : "",
		orderid : 0
	},
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	submit : function() {
		$("#base_form").trigger("validate");
	}
});

THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "dicList.html";
		});
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/dic/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data = json.data;
				} else {
					top.openeasydialog("error", "数据字典信息获取失败");
				}
			});
		}

	},
	initDom : function() {
		document.getElementById('txtDictionaryName').focus();
		Public.ajaxGet(Public.rootPath() + "/dic/dicAutoCompleteDate", {},
				function(json) {
					if (json.status == 001) {
						$('#txtDictionaryType').autocomplete(json.data, {
							max : 12, // 列表里的条目数
							minChars : 0, // 自动完成激活之前填入的最小字符
							width : 300, // 提示的宽度，溢出隐藏
							scrollHeight : 300, // 提示的高度，溢出显示滚动条
							matchContains : true, // 包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
							autoFill : false, // 自动填充
							formatItem : function(row, i, max) {
								return row.name;// 选择项
							},
							formatMatch : function(row, i, max) {
								return row.name;// 模糊查询项
							},
							formatResult : function(row) {
								return row.name;
							}
						}).result(function(event, row, formatted) {
							$("#txtDictionaryType").val(row.name);
						});
					} else {
						top.openeasydialog("error", "字典编辑获取字典枚举信息失败");
					}
				});
	}
};

function postData() {
	var url = null;
	if (vm.data.keyid != "" && vm.data.keyid != null
			&& vm.data.keyid != undefined) {
		url = Public.rootPath() + "/dic/edit";
	} else {
		url = Public.rootPath() + "/dic/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(json) {
		if (json.status == 001) {
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/system/dicManage/dicList.html";
			});
			
		} else {
			top.openeasydialog("error", json.msg);
		}
	});
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
		valid : function() {
			postData();
		},
		ignore : ":hidden",
		theme : "yellow_bottom",
		timely : 1,
		stopOnError : true
	});
}

$(document).ready(function() {
	initValidator();
	THISPAGE.init();
});
