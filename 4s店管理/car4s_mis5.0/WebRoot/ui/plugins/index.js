$(function () {
    //日历
    $("#datepicker").datepicker();
    //左边菜单
    $('.one').click(function () {
        $('.one').removeClass('one-hover');
        $(this).addClass('one-hover');
        $(this).parent().find('.kid').toggle();
    });
    //隐藏菜单
    var l = $('.left_c');
    var r = $('.right_c');
    var c = $('.Conframe');
    $('.nav-tip').click(function () {
        if (l.css('left') == '8px') {
            l.animate({
                left: -300
            }, 500);
            r.animate({
                left: 21
            }, 500);
            c.animate({
                left: 29
            }, 500);
            $(this).animate({
                "background-position-x": "-12"
            }, 300);
        } else {
            l.animate({
                left: 8
            }, 500);
            r.animate({
                left: 260
            }, 500);
            c.animate({
                left: 268
            }, 500);
            $(this).animate({
                "background-position-x": "0"
            }, 300);
        };
    });
    //横向菜单
    $('.top-menu-nav li').click(function () {
        $('.kidc').hide();
        $(this).find('.kidc').show();
        
    });
    $('.kidc').bind('mouseleave', function () {
        $('.kidc').hide();
    });
    //菜单全部展开
    $('#mm1').click(function () {
       $('.kid').show();
       $('.one').removeClass('one-hover');
    });
    //菜单全部收起
    $('#mm2').click(function () {
       $('.kid').hide();
       $('.one').removeClass('one-hover');
    });

});