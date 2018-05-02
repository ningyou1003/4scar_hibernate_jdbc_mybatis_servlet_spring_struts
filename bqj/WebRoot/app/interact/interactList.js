var vm = avalon.define({
    $id: "interactCtrl",
    msg: "",
    data: {},
    datas: {},
    dat: {
        userid: "",
        content: ""
    },
    keyid: "",
    select: {
        title: ""
    },
    subSystemsRendered: function () {
    
    }

});

THISPAGE = {
    init: function () {
        this.loadHot();
        this.loadGrid();
        this.addEvent();
    },
    loadHot: function () {
        Public.ajaxPost(Public.rootPath() + '/interact/post/getMostReplyPost', {
            'pageIndex': 0,
            'statisticsSelectVO': vm.select.$model
        }, function (json) {
            if (json.status == 001) {
                vm.datas = json.data;
                var len = vm.datas.length
                var html = "";
                for (i = 0; i < len; i++) {
                    var username = vm.datas[i].username;
                    var userid = vm.datas[i].userid;
                    var content = replace_em(vm.datas[i].content);
                    var createtime = vm.datas[i].createtime;
                    var hasreply = vm.datas[i].hasreply;
                    var haslike = vm.datas[i].haslike;
                    html += '<li>';
                    html += '<div class="tweet">';
                    html += '<p class="txt">';
                    html += '<a href="Myinteract.html?userId=' + userid + '" target="_self">' + username + '</a> : <span class="txtcontent">' + content + '</span></p>';
                    html += '<p class="outline">';
                    html += '发布于 ' + createtime + ' (<a href="interactDetail.html?keyid=' + vm.datas[i].id + '" target="_self" style="font-size:12px">' + hasreply + '评</a>)';
                    html += '<!--添加点赞功能 Start-->';
                    html += '<a href="javascript:;" id=' + vm.datas[i].id + ' onclick="makeAsLove(this);"><span class="love"><span class="tweet-vote-link"><i class="icon-svg icon-thumbs-o-up"></i></span><span class="loveOfCount">';
                    html += haslike + '</span></a> </span>';
                    html += '  <!--添加点赞功能 END-->';
                    html += '</p>';
                    html += '</div>';
                    html += ' </li>';

                }

                $("#hots").html(html);
            } else {
                alert(json.msg);
            }
        });
    },
    loadGrid: function () {
        //var request = Public.urlRequest();
        Public.ajaxPost(Public.rootPath() + '/interact/post/postList', {
            'pageIndex': 0,
            'statisticsSelectVO': vm.select.$model
        }, function (json) {
            if (json.status == 001) {
                vm.data = json.data.list;
                var len = vm.data.length;
                var html = "";
                for (i = 0; i < len; i++) {
                    var username = vm.data[i].username;
                    var userid = vm.data[i].userid;
                    var content = replace_em(vm.data[i].content);
                    var createtime = vm.data[i].createtime;
                    var hasreply = vm.data[i].hasreply;
                    var haslike = vm.data[i].haslike;
                    html += '<li>';
                    html += '<div class="tweet">';
                    html += '<p class="txt">';
                    html += '<a href="Myinteract.html?userId=' + userid + '" target="_self">' + username + '</a> : <span class="txtcontent">' + content + '</span></p>';
                    html += '<p class="outline">';
                    html += '发布于 ' + createtime + ' (<a href="interactDetail.html?keyid=' + vm.data[i].id + '" target="_self" style="font-size:12px">' + hasreply + '评</a>)';
                    html += '<!--添加点赞功能 Start-->';
                    html += '<a href="javascript:;" id=' + vm.data[i].id + ' onclick="makeAsLove(this);"><span class="love"><span class="tweet-vote-link"><i class="icon-svg icon-thumbs-o-up"></i></span><span class="loveOfCount">';
                    html += haslike + '</span></a> </span>';
                    html += '  <!--添加点赞功能 END-->';
                    html += '</p>';
                    html += '</div>';
                    html += ' </li>';

                }
                $("#tiles").html(html);
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
                Public.ajaxPost(Public.rootPath() + '/interact/post/postList', {
                    'page': index,
                    'statisticsSelectVO': vm.select.$model
                }, function (json) {
                    if (json.status == 001) {
                        vm.data = json.data.list;
                        var len = vm.data.length;
                        var html = "";
                        for (i = 0; i < len; i++) {
                            var username = vm.data[i].username;
                            var userid = vm.datas[i].userid;
                            var content = replace_em(vm.data[i].content);
                            var createtime = vm.data[i].createtime;
                            var hasreply = vm.data[i].hasreply;
                            var haslike = vm.data[i].haslike;
                            html += '<li>';
                            html += '<div class="tweet">';
                            html += '<p class="txt">';
                            html += '<a href="Myinteract.html?userId=' + userid + '" target="_self">' + username + '</a> : <span class="txtcontent">' + content + '</span></p>';
                            html += '<p class="outline">';
                            html += '发布于 ' + createtime + ' (<a href="interactDetail.html?keyid=' + vm.data[i].id + '" target="_self">' + hasreply + '评</a>)';
                            html += '<!--添加点赞功能 Start-->';
                            html += '<a href="javascript:;" id=' + vm.data[i].id + ' onclick="makeAsLove(this);"><span class="love"><span class="tweet-vote-link"><i class="icon-svg icon-thumbs-o-up"></i></span><span class="loveOfCount">';
                            html += haslike + '</span></a> </span>';
                            html += '  <!--添加点赞功能 END-->';
                            html += '</p>';
                            html += '</div>';
                            html += ' </li>';

                        }
                        $("#tiles").html(html);
                    } else {
                        alert(json.msg);
                    }
                });
            }
        });
    },
    addEvent: function () {
    }
};

function postData() {
    $("#TweetFormPopupWraper").hide();
    var vals = $("#TXT_Tweet_Text").val();
    if (vals == "" && $("#TweetImageObj").attr("src") == "") {
        top.openeasydialog("warn", "请填写内容！");
        return;
    }
    else {
        if ($("#TweetImageObj").attr("src") != "") {
            vm.dat.content = $("#TXT_Tweet_Text").val() + '<br/><img src="' + $("#TweetImageObj").attr("src") + '" border="0">';
        }
    }
    Public.ajaxPost(Public.rootPath() + '/interact/post/add', vm.dat.$model, function (json) {
        if (json.status == 001) {
            top.openeasydialog("success", "发布成功！");
            $("#TXT_Tweet_Text").val("");
            $("#TweetImageObj").attr("src", "");
            $("#img_network_url").val("");
            THISPAGE.init();
            //window.location = "interactList.html" ;
        } else {
            top.openeasydialog("error", json.msg);
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
                    THISPAGE.init();
                    $.cookie('zsj_cookie_dianzan1', objid + "&" + 1, { path: '/' });
                } else {
                    top.openeasydialog("error", json.msg);
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
                top.openeasydialog("error", json.msg);
            }
        });
    }
}


function replace_em(str) { //表情符号替换成图片

    str = str.replace(/\[em_([0-9]*)\]/g, '<img src="qqface/face/$1.gif" border="0" />');
    return str;
}

$(document).ready(function () {
    THISPAGE.init();
    Public.ajaxGet(Public.rootPath() + "/user/info", {}, function (json) {
        vm.dat.userid = json.data.keyid;
    });
    initUpload("interactList", vm.keyid);
});


