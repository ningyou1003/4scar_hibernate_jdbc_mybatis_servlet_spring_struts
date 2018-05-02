/**
* Created by ym on 15-3-10.
*/
var vm = avalon.define({
    $id: "leftCtrl",
    menuList: {},
    menuName: "政务办公",
    localindex: "1",
    slide: "down"
});
function changeMenu(index) {
    if (vm.localindex == "1" || vm.localindex != index) {
        if (index == '' || index == null || index == undefined) {
            index = vm.localindex;
        }
        var html = "";
        Public.ajaxPost(Public.rootPath() + '/loadLeft', {
            'index': 0
        }, function (data) {
            vm.menuName = data.name;
            vm.menuList = data.child;
            vm.localindex = index;
            var elemValue = "";
            html += "<ul class=\"ce\"  id=\"contentUl\" >";
            for (var i = 0; i < vm.menuList.length; i++) {
                html += "<li><img src=" + vm.menuList[i].img + " align=\"absMiddle\">";
                //            	var a = vm.menuList[i].name = NewElemValue(vm.menuList[i].name);
                elemValue = vm.menuList[i].name;
                html += "<a href=\"#\">" + elemValue + "<img class=\"more\" src=\"../images/more.png\"/></a>";
                html += "<ul class=\"er\">";
                for (var j = 0; j < vm.menuList[i].child.length; j++) {
                    html += "<li class=\"e_li\">";
                    var l = vm.menuList[i].child[j].child.length;
                    var linkUrl0 = "";
                    //            		exp = "";
                    elemValue = vm.menuList[i].child[j].name;
                    if (l == 0) {
                        linkUrl0 = "class=\"linkUrl\" tag=\""
							+ vm.menuList[i].child[j].name + "@"
							+ vm.menuList[i].child[j].url + "\"";
                        if (elemValue == "邮件管理") {//这里做了特殊判断，点击这个菜单直接跳转到网易邮箱
                            html += "<a id='usermial' target=\"_blank\" >" + elemValue + "</a>";
                            binUsermial(); //给usermial绑定一个URL
                        } else {
                            html += "<a  href=\"#\" " + linkUrl0 + ">" + elemValue + "</a>";
                        }
                    } else {
//                        if (regionId == "glin"&&elemValue == "管理体系") {
//                        }
                        html += "<a  href=\"#\">" + elemValue + "</a>";
                        html += "<ul class=\"thr\">";
                        for (var k = 0; k < vm.menuList[i].child[j].child.length; k++) {
                            elemValue = vm.menuList[i].child[j].child[k].name;
                            linkUrl0 = "class=\"linkUrl\" tag=\""
    							+ vm.menuList[i].child[j].child[k].name + "@"
    							+ vm.menuList[i].child[j].child[k].url + "\"";
                            html += "<li> <a  href=\"#\" " + linkUrl0 + ">" + elemValue + "</a> </li>";
                        }
                        html += "</ul>";
                    }
                    html += "</li>";
                }
                html += "</ul>";
                html += "</li>";

            }
            html += "</ul>";
            $("#content").html(html);
            $(".ce > li > a").click(function () {
                $(this).addClass("xz").parents().siblings().find("a").removeClass("xz");
                $(this).parents().siblings().find(".er").hide(300);
                $(this).siblings(".er").toggle(300);
                $(this).parents().siblings().find(".er > li > .thr").hide().parents().siblings().find(".thr_nr").hide();

            })

            $(".er > li > a").click(function () {
                $(this).addClass("sen_x").parents().siblings().find("a").removeClass("sen_x");
                $(this).parents().siblings().find(".thr").hide(300);
                $(this).siblings(".thr").toggle(300);
            })

            $(".thr > li > a").click(function () {
                $(this).addClass("xuan").parents().siblings().find("a").removeClass("xuan");
                $(this).parents().siblings().find(".thr_nr").hide();
                $(this).siblings(".thr_nr").toggle();
            })

            $(".linkUrl").click(function () {
                var val = $(this).attr("tag");
                var vals = val.split('@');
                if ("浏览官网" == vals[0]) {
                    top.open(vals[1]);
                } else {
                    if (vals[1] != "null" && vals[1] != "") {
                        AddTabUrl(vals[0], vals[1]);
                    }
                }


            });

        });
    }
}

//跳转到当前用户网易邮箱
function binUsermial() {
    var url = Public.rootPath() + "/mailbox/sso";
    Public.ajaxPost(url, "", function (json) {
        $("#usermial").attr('href', json.data.url);
    });
}

changeMenu();

$.ligerMethos.Accordion.reload = function (html) {
    this.accordion.html(html);
    this._render();
};

function AddTabUrl(Title, Value) {
    window.parent.frames["rightFrame"].AddItems(Title, Value);
}

//function NewElemValue(value) {
//    var exp = "";
//    if (value.length < 6) {
//        var l = 6 - value.length;

//        for (var i = 0; i < 2 * l; i++) {
//            exp += "&nbsp;";
//        }
//        value = exp + value + exp;
//    }
//    return value;
//}
//$(document).ready(function (e) { 
//    $("#accordion1").ligerAccordion({
//        height: window.document.body.scrollHeight - window.document.body.offsetHeight
//    });
//});
function changeDivSize(ele) {
    var x = window.parent.document.getElementById("leftFrame").clientWidth; //frame宽度
    var y = document.documentElement.clientHeight; //获取页面可见高度  

    var topHeight = document.getElementById("lefttop").clientHeight;
    ele.style.height = (y - topHeight) + "px";
    ele.style.width = x + "px";
    /* var oDiv = document.getElementById("navtab1");
    var a = oDiv.lastChild.firstChild;
    a.style.size = h + "px";  */
}
$(function () {
    var a, b;
    window.onload = function () {
        b = document.getElementById("scrollDiv");
        a = document.getElementById("content");
        changeDivSize(b);
    }
    window.onresize = function () {
        changeDivSize(b);
        //		top.changeDivHeight(b);//未起作用？？
    }
})
