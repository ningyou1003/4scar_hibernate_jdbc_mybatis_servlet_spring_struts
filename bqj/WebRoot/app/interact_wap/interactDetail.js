var vm = avalon.define({
    $id: "interactDetailCtrl",
    msg: "",
    data: {},
    datas: {},
    dat: {
        userid: "",
        username: "",
        postid: "",
        content: ""

    },
    keyid: "",
    select: {
        title: ""
    },

    submit: function () {
        $("#TForm").trigger("validate");
    }

});

THISPAGE = {
    init: function () {
        this.loadIndex();
        this.loadGrid();
    },
    loadIndex: function () {
        Public.ajaxPost(Public.rootPath() + '/interact/post/postDetail', {
            'id': vm.keyid
        }, function (json) {
            if (json.status == 001) {
                vm.data = json.data;
                $("#postContent").html(replace_em(vm.data.content));
                var hasreply = vm.data.hasreply;
                var haslike = vm.data.haslike;
                $("#log_reply_count").html(hasreply);
                $("#love_count").html(haslike);
                $("#time").html(vm.data.createtime);
                var userid = vm.data.userid
                var _id = vm.data.id;
                if (vm.dat.userid == userid) {
                    $("#delspan").html('<a href="javascript:;" id=' + _id + ' onclick="delHD(this);" style="font-size:12px; padding-right:5px">删除</a>');
                }
            } else {
                alert(json.msg);
            }
        });

    },
    loadGrid: function () {
        Public.ajaxPost(Public.rootPath() + '/interact/reply/getReplyByPostId?postId=' + vm.keyid, {
            'pageIndex': 0,
            'statisticsSelectVO': vm.select.$model
        }, function (json) {
            if (json.status == 001) {
                vm.data = json.data.list;
                var len = vm.data.length
                var html = "";
                for (i = 0; i < len; i++) {
                    var userid = vm.data[i].userid;
                    var _id = vm.data[i].id;
                    var username = vm.data[i].username;
                    var content = replace_em(vm.data[i].content);
                    var createtime = vm.data[i].createtime;

                    html += '<li>';
                    html += '<table class="ostable">';
                    html += '<tbody>';
                    html += '<tr>';
                    html += '<td class="TweetReplyBody">';
                    html += '<div class="post">';
                    html += '评论人：<a href="Myinteract.html?userId=' + userid + '" target="_self">' + username + '</a><br>' + content;
                    html += '</div>';
                    html += '<div class="opts">';
                    html += '<span class="links">';
                    if (vm.dat.userid == userid) {
                        html += '<a href="javascript:;" id=' + _id + ' onclick="delPL(this);" style="font-size:12px; padding-right:5px">删除</a>';
                    }
                    html += '<a href=javascript:reply_rtweet("' + userid + '","' + username + '")>回复</a>';
                    html += '</span>' + createtime + '';
                    html += '</div>';
                    html += '</td>';
                    html += '</tr>';
                    html += '</tbody>';
                    html += '</table>';
                    html += '</li>';
                }

                $("#postlist").html(html);
                THISPAGE.initPage(json.data.totalPage, json.data.pageNumber, json.data.totalRow);
            } else {
                alert(json.msg);
            }
        });

    },
    initPage: function (totalPage, pageNumber, totalRow) {
        // 加载分页控件
        $("#PageInfo").pagePlugin({
            totalPage: totalPage,
            pageNumber: pageNumber,
            totalRow: totalRow,
            requst: function (index) {
                Public.ajaxPost(Public.rootPath() + '/interact/reply/getReplyByPostId?postId=' + vm.keyid, {
                    'page': index,
                    'statisticsSelectVO': vm.select.$model
                }, function (json) {
                    if (json.status == 001) {
                        vm.data = json.data.list;
                        var len = vm.data.length
                        var html = "";
                        for (i = 0; i < len; i++) {
                            var userid = vm.data[i].userid;
                            var _id = vm.data[i].id;
                            var username = vm.data[i].username;
                            var content = replace_em(vm.data[i].content);
                            var createtime = vm.data[i].createtime;

                            html += '<li>';
                            html += '<table class="ostable">';
                            html += '<tbody>';
                            html += '<tr>';
                            html += '<td class="TweetReplyBody">';
                            html += '<div class="post">';
                            html += '<a href="Myinteract.html?userId=' + userid + '" target="_self">' + username + '</a><br>' + content;
                            html += '</div>';
                            html += '<div class="opts">';
                            html += '<span class="links">';
                            if (vm.dat.userid == userid) {
                                html += '<a href="javascript:;" id=' + _id + ' onclick="delPL(this);" style="font-size:12px; padding-right:5px">删除</a>';
                            }
                            html += '<a href=javascript:reply_rtweet("' + username + '")>回复</a>';
                            html += '</span>' + createtime + '';
                            html += '</div>';
                            html += '</td>';
                            html += '</tr>';
                            html += '</tbody>';
                            html += '</table>';
                            html += '</li>';
                        }

                        $("#postlist").html(html);
                    } else {
                        alert(json.msg);
                    }
                });
            }
        });
    }
};

function postData() {
    if ($("#TXT_Tweet_Text").val() == "") {
        //top.openeasydialog("warn", "请填写内容！");
        alert("请填写内容！");
        return;
    }
    var TXT_str = replace_hf($("#TXT_Tweet_Text").val());
    vm.dat.content = TXT_str;
    Public.ajaxPost(Public.rootPath() + '/interact/reply/add', vm.dat.$model, function (json) {
        if (json.status == 001) {
            //top.openeasydialog("success", "发布成功！");
            alert("发布成功！");
            $("#TXT_Tweet_Text").val("");
            THISPAGE.init();
        } else {
            alert(json.msg);
        }
    });

}

//点赞
function makeAsLove(obj) {
    var objid = $(obj).attr("id");
    var dianzancookieString = $.cookie('zsj_cookie_dianzan1');
    if (dianzancookieString != null && dianzancookieString != "" && dianzancookieString != undefined) {
        var num = dianzancookieString.split("&")[1];
        var _id = dianzancookieString.split("&")[0];
        if (num > 2 && _id == objid) {
            //top.openeasydialog("warn", "点赞次数太多了！");
            alert("点赞次数太多了！");
            return;
        }
        if (num == 1 && _id == objid) {
            Public.ajaxPost(Public.rootPath() + '/interact/post/minusHaslike', { "id": objid }, function (json) {
                if (json.status == 001) {
                    $.cookie('zsj_cookie_dianzan1', objid + "&" + 2, { path: '/' });
                    THISPAGE.init();
                } else {
                    alert(json.msg);
                }
            });
        }
        else {
            Public.ajaxPost(Public.rootPath() + '/interact/post/addHasLike', { "id": objid }, function (json) {
                if (json.status == 001) {
                    THISPAGE.init();
                    $.cookie('zsj_cookie_dianzan1', objid + "&" + 1, { path: '/' });
                } else {
                    alert(json.msg);
                }
            });
        }
    }
    else {
        Public.ajaxPost(Public.rootPath() + '/interact/post/addHasLike', { "id": objid }, function (json) {
            if (json.status == 001) {
                $.cookie('zsj_cookie_dianzan1', objid + "&" + 1, { path: '/' });
                THISPAGE.init();
            } else {
                alert(json.msg);
            }
        });
    }
}

//删除评论
function delPL(obj) {
//    top.openeasydialog("warning", "确认要删除这条评论吗？", function (yes) {
//        if (yes) {
//            var objid = $(obj).attr("id");
//            Public.ajaxPost(Public.rootPath() + '/interact/reply/deleteById', { "id": objid }, function (json) {
//                if (json.status == 001) {
//                    THISPAGE.init();
//                } else {
//                    top.openeasydialog("error", json.msg);
//                }
//            });
//        }
//    });
    var msg = "确认要删除这条评论吗？";
    if (confirm(msg) == true) {
        var objid = $(obj).attr("id");
        Public.ajaxPost(Public.rootPath() + '/interact/reply/deleteById', { "id": objid }, function (json) {
            if (json.status == 001) {
                THISPAGE.init();
            } else {
                alert(json.msg);
            }
        });
    }
    else {
        return false;
    }
   
}
//删除互动
function delHD(obj) {
//    top.openeasydialog("warning", "确认要删除这条互动吗？", function (yes) {
//        if (yes) {
//            var objid = $(obj).attr("id");
//            Public.ajaxPost(Public.rootPath() + '/interact/post/deleteById', { "id": objid }, function (json) {
//                if (json.status == 001) {
//                    window.location.href = "Myinteract.html?userId=" + vm.dat.userid;
//                } else {
//                    top.openeasydialog("error", json.msg);
//                }
//            });
//        }
//    });

    var msg = "确认要删除这条互动吗？";
    if (confirm(msg) == true) {
        var objid = $(obj).attr("id");
        Public.ajaxPost(Public.rootPath() + '/interact/post/deleteById', { "id": objid }, function (json) {
            if (json.status == 001) {
                window.location.href = "Myinteract.html?userId=" + vm.dat.userid;
            } else {
                alert(json.msg);
            }
        });
    }
    else {
        return false;
    }
}

function replace_em(str) { //表情符号替换成图片

    str = str.replace(/\[em_([0-9]*)\]/g, '<img src="qqface/face/$1.gif" border="0" />');
    return str;
}

function replace_hf(str) { //替换回复
    if (str.indexOf("回复 @") >= 0) {
        var str_before = str.split(":")[0];
        var str_after = str.replace(str_before + ":", "");
        str_before = str_before.replace("回复 ", '');
        var _userid = $("#replyUserid").val();
        str = '回复 <a href="Myinteract.html?userId=' + _userid + '">' + str_before + '</a>' + str_after;
    }
    return str;
}

$(document).ready(function () {
    var request = Public.urlRequest();
    vm.keyid = request.queryString["keyid"];
    vm.dat.postid = vm.keyid;

    Public.ajaxGet(Public.rootPath() + "/user/info", {}, function (json) {
        vm.dat.userid = json.data.keyid;
        vm.dat.username = json.data.relaname;
        THISPAGE.init();
    });

});
