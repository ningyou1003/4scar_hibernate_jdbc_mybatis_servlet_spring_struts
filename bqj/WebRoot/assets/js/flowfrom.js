
$(function () {


});



//选择当前登录用户单位部门
function sel_dep(e) {
    top.openbuttondialog("选择部门", 450, 450,
            			"system/orgManage/orgDepTreePick.html",
            			false, function (item, dialog) {
            			    $("#" + e).val(dialog.frame.$("#departmentname").val());
            			    dialog.close();
            			});
}
//选择当前用户所属机构人员
function sel_User(e) {
    top.openbuttondialog("选择人员", 450, 450,
            			"system/orgManage/orgPickLeader.html",
                        false, function (item, dialog) {
                            dialog.frame.$("#ok").trigger("click");
                            $("#" + e).val(dialog.frame.$("#names").val());
                            dialog.close();
                        });
}

//选择下一步骤人员
function sel_nextUser(e, n) {
    top.openbuttondialog("选择人员", 450, 450,
            							"system/orgManage/orgPickLeader.html?keyids=" + $("#" + e).val(),
                                            false, function (item, dialog) {
                                                dialog.frame.$("#ok").trigger("click");
                                                $("#" + e).val(dialog.frame.$("#keyids").val());
                                                $("#" + n).val(dialog.frame.$("#names").val());
                                                dialog.close();
                                            });
}

//选择传阅人员-并触发发送事件
function sel_RealtUser(e, n) {
    top.openbuttondialog("选择人员", 450, 450,
            							"system/orgManage/orgPickLeader.html?keyids=" + $("#" + e).val(),
                                            false, function (item, dialog) {
                                                dialog.frame.$("#ok").trigger("click");
                                               
                                                $("#" + e).val(dialog.frame.$("#keyids").val());
                                                $("#" + n).val(dialog.frame.$("#names").val());
                                                theCirculationSend();
                                                dialog.close();
                                            });
}

//会签选择人员-并触发发送事件
function sel_RealtUser(e, n) {
    top.openbuttondialog("选择人员", 450, 450,
            							"system/orgManage/orgPickLeader.html?keyids=" + $("#" + e).val(),
                                            false, function (item, dialog) {
                                                dialog.frame.$("#ok").trigger("click");
                                                $("#" + e).val(dialog.frame.$("#keyids").val());
                                                $("#" + n).val(dialog.frame.$("#names").val());
                                                theHQsignsend();
                                                dialog.close();
                                            });
}



//===========js通用=================
//截断字符串
function cutLen(s, len) {
    if (s.length > len) { alert("超过了字符长度请重新输入!"); s = s.substring(len, s.Length - len); }
    return s;
}
//指定字符串最大长度 例如changeLen(##A##,100)
function changeLen(objId, len) { var b = document.getElementById(objId); b.value = cutLen(b.value, len); }

//自动签名，主要用于多个领带会签，签署已经后自动署名，如局领导签收步骤，领导在那个地方签署意见，就自动在旁边署名
function autoSignName(obj1, obj2) {
    if ($(obj1).val() != "") {
        $("#" + obj2).val(currUserAndTime);
    }
    else {
        $("#" + obj2).val("");
    }
}
var currUserAndTime = "";
//赋予表单权限
function getPermission(pData) {
    if (pData.stylePermission != null) {
        var p = pData.stylePermission; //权限表
        var currentDepName = pData.currentDepName; //当前部门
        var currentTime = pData.currentTime; //当前时间
        var currentUserName = pData.currentUserName; //当前人
        currUserAndTime = currentUserName + "  " + currentTime;
        var len = p.length;
        for (var i = 0; i < len; i++) {
            if (p[i].permission == 0) {//只读
                $("#" + p[i].id).attr("disabled", "disabled");
            }
            else if (p[i].permission == 2) {//不可见
                $("#" + p[i].id).css("display", "none");
            }
            else { //编辑--
                //$("#" + p[i].id).addClass("required"); //编辑的控件加上必填
                if (p[i].dataFld == "SYS_Current_UseName") //赋予当前人信息
                {
                    if ($("#" + p[i].id).val() == "")
                        $("#" + p[i].id).val(currentUserName);
                }
                else if (p[i].dataFld == "SYS_Current_UseNameAndDataTime") //赋予当前人和时间信息
                {
                    if ($("#" + p[i].id).val() == "")
                        $("#" + p[i].id).val(currentUserName + "  " + currentTime);
                }
                else if (p[i].dataFld == "SYS_Current_DeptName") //赋予部门信息
                {
                    if ($("#" + p[i].id).val() == "")
                        $("#" + p[i].id).val(currentDepName);
                }
                else if (p[i].dataFld == "SYS_Current_DataTime") //赋予部门信息
                {
                    if ($("#" + p[i].id).val() == "")
                        $("#" + p[i].id).val(currentTime);
                }
            }
        }
        SpecialPower(currentUserName); //特殊判断，领导可修改表单公共部分，不可修改其他领导的意见部分。
    }
}

//赋予表单值
function getStyleValue(pData) {
    if (pData.styleValue != null) {
        var p = pData.styleValue; //表单值
        var len = p.length;
        for (var i = 0; i < len; i++) {
            if ($("#_" + p[i].id.replace("txt", "") + "_").attr("type") == "checkbox") {
                $("#_" + p[i].id.replace("txt", "") + "_").attr("checked", "checked");
            }
            else {
                $("#_" + p[i].id.replace("txt", "") + "_").val(p[i].value);
            }
        }
    }
}