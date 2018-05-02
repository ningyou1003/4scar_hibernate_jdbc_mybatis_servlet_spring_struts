jQuery(function(){
//选项卡滑动切换通用
jQuery(function(){jQuery(".hoverTag .chgBtn").hover(function(){jQuery(this).parent().find(".chgBtn").removeClass("chgCutBtn");jQuery(this).addClass("chgCutBtn");var cutNum=jQuery(this).parent().find(".chgBtn").index(this);jQuery(this).parents(".hoverTag").find(".chgCon").hide();jQuery(this).parents(".hoverTag").find(".chgCon").eq(cutNum).show();})})

//选项卡点击切换通用
jQuery(function(){jQuery(".clickTag .chgBtn").click(function(){jQuery(this).parent().find(".chgBtn").removeClass("chgCutBtn");jQuery(this).addClass("chgCutBtn");var cutNum=jQuery(this).parent().find(".chgBtn").index(this);jQuery(this).parents(".clickTag").find(".chgCon").hide();jQuery(this).parents(".clickTag").find(".chgCon").eq(cutNum).show();})})

$(".navBox ul li:last").css("background","none");
$(".submenu").eq(0).css("left",40);
$(".submenu").eq(1).css("left",28);
$(".submenu").eq(2).css("left",-300);
$(".submenu").eq(3).css("left",-145);
$(".submenu").eq(4).css("left",-85);
$(".submenu").eq(5).css("left",-193);
$(".navBox li").hover(function(){$(this).addClass("nav_hover");$(".nav_cut .submenu").hide();},function(){$(this).removeClass("nav_hover");$(".nav_cut .submenu").show();});
$(".nav_cut").hover(function(){$(".nav_cut .submenu").show();});


jQuery(".slideBox1").slide({mainCell:".bd ul",effect:"fold",autoPlay:true});
$(".m2_font1").click(function(){$(".zt_m2con").css({"font-size":"18px","line-height":"36px"})});
$(".m2_font2").click(function(){$(".zt_m2con").css({"font-size":"16px","line-height":"32px"})});
$(".m2_font3").click(function(){$(".zt_m2con").css({"font-size":"14px","line-height":"30px"})});

})
//屏蔽页面错误
jQuery(window).error(function(){
  return true;
});
jQuery("img").error(function(){
  $(this).hide();
});