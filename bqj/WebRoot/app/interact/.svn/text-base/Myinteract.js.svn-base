

function replace_em(str) { //表情符号替换成图片

    str = str.replace(/\[em_([0-9]*)\]/g, '<img src="qqface/face/$1.gif" border="0" />');
    return str;
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

//删除互动
function delHD(obj) {
    top.openeasydialog("warning", "确认要删除这条互动吗？", function (yes) {
        if (yes) {
            var objid = $(obj).attr("id");
            Public.ajaxPost(Public.rootPath() + '/interact/post/deleteById', { "id": objid }, function (json) {
                if (json.status == 001) {
                    window.location.href = "Myinteract.html?userId=" + vm.dat.userid;
                } else {
                    top.openeasydialog("error", json.msg);
                }
            });
        }
    });

}

var url = GetQueryString("userId");

//点赞
function makeAsLove(obj) {
    var objid = $(obj).attr("id");
    var dianzancookieString = $.cookie('zsj_cookie_dianzan1');
    if (dianzancookieString != null && dianzancookieString != "" && dianzancookieString != undefined) {
        var num = dianzancookieString.split("&")[1];
        var _id = dianzancookieString.split("&")[0];
        if (num > 2 && _id == objid) {
            top.openeasydialog("warn", "点赞次数太多了！");
            return;
        }
        if (num == 1 && _id == objid) {
            Public.ajaxPost(Public.rootPath() + '/interact/post/minusHaslike', { "id": objid }, function (json) {
                if (json.status == 001) {
                    $.cookie('zsj_cookie_dianzan1', objid + "&" + 2, { path: '/' });
                    THISPAGE.init();
                } else {
                    top.openeasydialog("error", json.msg);
                }
            });
        }
        else {
            Public.ajaxPost(Public.rootPath() + '/interact/post/addHasLike', { "id": objid }, function (json) {
                if (json.status == 001) {
                    window.location.href = "interactList.html";
                    $.cookie('zsj_cookie_dianzan1', objid + "&" + 1, { path: '/' });
                } else {
                    top.openeasydialog("error", json.msg);
                }
            });
        }
    }
    else {
        alert(dianzancookieString);
        Public.ajaxPost(Public.rootPath() + '/interact/post/addHasLike', { "id": objid }, function (json) {
            if (json.status == 001) {
                $.cookie('zsj_cookie_dianzan1', objid + "&" + 1, { path: '/' });
                THISPAGE.init();
            } else {
                top.openeasydialog("error", json.msg);
            }
        });
    }
}

//删除评论
function delPL(obj) {
    top.openeasydialog("warning", "确认要删除这条评论吗？", function (yes) {
        if (yes) {
            var objid = $(obj).attr("id");
            Public.ajaxPost(Public.rootPath() + '/interact/reply/deleteById', { "id": objid }, function (json) {
                if (json.status == 001) {
                    //                    window.location.href = "interactList.html";

                } else {
                    top.openeasydialog("error", json.msg);
                }
            });
        }
    });

}

//阅读评论
function readPL(obj) {
    var objid = $(obj).attr("id");
    var postid = $(obj).attr("postid");
    Public.ajaxPost(Public.rootPath() + '/interact/reply/isRead', { "id": objid }, function (json) {
        if (json.status == 001) {
            var obj = parent.parent.frames["topFrame"].window;
            obj.getComment();
            window.location.href = "interactDetail.html?keyid=" + postid;
        } else {
            top.openeasydialog("error", json.msg);
        }
    });
}




$(document).ready(function () {
    var request = Public.urlRequest();
    vm.userId = request.queryString["userId"];
    var stype = request.queryString["type"];
    if (stype != "") {
        $('#two').addClass('active');
        $('#one').removeClass('active');

        $('#listsHD').hide();
        $('#listsPL').show();
    }

    THISPAGE.init();
});






var vm = avalon.define({
    $id: "myInteractCtrl",
    userId: ""
});
THISPAGE = {
    init: function () {
        this.loadGrid();
        this.loadGridPL();
    },
    loadGrid: function () {
        Public.ajaxPost(Public.rootPath() + '/interact/post/getPostByUserId', { 'userId': vm.userId }, function (json) {
            if (json.status == 001) {
                var data = json.data.list;
                var len = data.length;
                var html = "";
                for (i = 0; i < len; i++) {
                    var id = data[i].id;
                    var username = data[i].username;
                    var content = replace_em(data[i].content);
                    var createtime = data[i].createtime;
                    var hasreply = data[i].hasreply;
                    var haslike = data[i].haslike;

                    html += '<li>';
                    html += '<div class="tweet">';
                    html += '<p class="txt" style="margin: 10px 0">';
                    html += '<a href="" target="_self">' + username + '</a> : <span class="txtcontent">' + content + '</span></p>';
                    html += '<p class="outline" style="font-size: 9pt;color:#888">';
                    html += '发布于 ' + createtime + ' (<a href="interactDetail.html?keyid=' + id + '" target="_self" style="font-size:12px;">' + hasreply + '评</a>)';
                    html += '<!--添加点赞功能 Start-->';
                    html += '<a href="javascript:;" id=' + id + ' onclick="makeAsLove(this);"><span class="love" style="float:right"><span class="tweet-vote-link"><i class="icon-svg icon-thumbs-o-up"></i></span><span class="loveOfCount" style="font-size:12px;">';
                    html += haslike + '</span></a> </span>';
                    html += '  <!--添加点赞功能 END-->';
                    html += '<a href="javascript:;" id=' + id + ' onclick="delHD(this);" style="font-size:12px; padding-right:5px">删除</a></p>';
                    html += '</div>';
                    html += ' </li>';
                }

                $("#lists").html(html);
                THISPAGE.initPage(json.data.totalPage, json.data.pageNumber, json.data.totalRow);
            } else {
                alert(json.msg);
            }
        });
    },
    loadGridPL: function () {
        Public.ajaxPost(Public.rootPath() + '/interact/reply/getReplyByUserId', { 'userId': vm.userId }, function (json) {
            if (json.status == 001) {
                var datas = json.data.list;
                var lens = datas.length;
                var htmls = "";
                for (i = 0; i < lens; i++) {
                    var contents = replace_em(datas[i].content);
                    var createtimes = datas[i].createtime;
                    var usernames = datas[i].username;
                    var isread = datas[i].isread;
                    htmls += "<li>" + usernames + "：<p style='font-size: 10pt;color:#888;padding-top: 10px;padding-bottom: 10px;'>评论了互动</p>";
                    htmls += "<span style='font-size: 10pt'>" + contents + "</span><dl style='background:#f0f0f0;padding:12px;margin-top: 8px;'><dt style='font-size: 10pt;height: 24px;color: #4eaa4c;'>" + datas[i].pusername + "</dt><dd style='color: #3c3c3c;font-size: 9pt;line-height: 20px;'>" + replace_em(datas[i].pcontent) + "</dd></dl><p style='font-size: 9pt;color:#888;padding-top: 10px;'>发布于 ";
                    htmls += createtimes + "<a style='font-size:12px;float: right;' href='javascript:;' id=";
                    htmls += datas[i].id + " onclick='delPL(this);' >删除</a>";

                    htmls += "<a style='font-size:12px;float: right;' href='javascript:;' id=" + datas[i].id + " postid=" + datas[i].postid + " onclick='readPL(this);'>";
                    if (isread == 0) {
                        htmls += "<span style='color:#f00;font-size:12px;'>&nbsp;未读&nbsp;</span>";
                    }
                    else {
                        htmls += "<span style='color:green;font-size:12px;'>&nbsp;已读&nbsp;</span>";
                    }
                    htmls += "查看原文&nbsp;&nbsp;</a></p></li>";
                }
                $("#comments").html(htmls);
                THISPAGE.initPagePL(json.data.totalPage, json.data.pageNumber, json.data.totalRow);

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
                Public.ajaxPost(Public.rootPath() + '/interact/post/getPostByUserId', {
                    'userId': vm.userId,
                    'page': index
                }, function (json) {
                    if (json.status == 001) {
                        var data = json.data.list;
                        var len = data.length;
                        var html = "";
                        for (i = 0; i < len; i++) {
                            var id = data[i].id;
                            var username = data[i].username;
                            var content = replace_em(data[i].content);
                            var createtime = data[i].createtime;
                            var hasreply = data[i].hasreply;
                            var haslike = data[i].haslike;
                            html += '<li>';
                            html += '<div class="tweet">';
                            html += '<p class="txt" style="margin: 10px 0">';
                            html += '<a href="" target="_self">' + username + '</a> : <span class="txtcontent">' + content + '</span></p>';
                            html += '<p class="outline" style="font-size: 9pt;color:#888">';
                            html += '发布于 ' + createtime + ' (<a href="interactDetail.html?keyid=' + id + '" target="_self" style="font-size:12px;">' + hasreply + '评</a>)';
                            html += '<!--添加点赞功能 Start-->';
                            html += '<a href="javascript:;" id=' + id + ' onclick="makeAsLove(this);"><span class="love" style="float:right"><span class="tweet-vote-link"><i class="icon-svg icon-thumbs-o-up"></i></span><span class="loveOfCount" style="font-size:12px;">';
                            html += haslike + '</span></a> </span>';
                            html += '  <!--添加点赞功能 END-->';
                            html += '<a href="javascript:;" id=' + id + ' onclick="delHD(this);" style="font-size:12px; padding-right:5px">删除</a></p>';
                            html += '</div>';
                            html += ' </li>';
                        }
                        $("#lists").html(html);
                    } else {
                        alert(json.msg);
                    }
                });
            }
        });
    },
    initPagePL: function (totalPage, pageNumber, totalRow) {
        // 加载分页控件
        $("#PageInfoPL").pagePlugin({
            totalPage: totalPage,
            pageNumber: pageNumber,
            totalRow: totalRow,
            requst: function (index1) {
                Public.ajaxPost(Public.rootPath() + '/interact/reply/getReplyByUserId', {
                    'userId': vm.userId,
                    'page': index1
                }, function (json) {
                    if (json.status == 001) {
                        var datas = json.data.list;
                        var lens = datas.length;
                        var htmls = "";
                        for (i = 0; i < lens; i++) {
                            var contents = replace_em(datas[i].content);
                            var createtimes = datas[i].createtime;
                            var usernames = datas[i].username;
                            htmls += "<li>" + usernames + "：<p style='font-size: 9pt;color:#888;padding-top: 10px;padding-bottom: 10px;'>评论了互动</p>" + contents + "<p style='font-size: 9pt;color:#888;padding-top: 10px;'>发布于 " + createtimes + "<a style='font-size:12px;float: right;' href='javascript:;' id=" + datas[i].id + " onclick='delPL(this);' >删除</a><a style='font-size:12px;float: right;' href='javascript:;' id=" + datas[i].id + " postid=" + datas[i].postid + " onclick='readPL(this);'  >查看原文&nbsp;&nbsp;</a></p></li>";
                        }
                        $("#comments").html(htmls);
                    }
                    else {
                        alert(json.msg);
                    }
                });
            }
        });
    }

};
