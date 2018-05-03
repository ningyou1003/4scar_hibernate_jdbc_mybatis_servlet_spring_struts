//表单折叠
function collapse(obj) {
	var i = $(obj).find('i');
	if (i.attr('class') == 'tip-down') {
		i.attr('class', 'tip-up');// 图标向上
	} else {
		i.attr('class', 'tip-down'); // 图标向下
	}
	// 隐藏或显示切换
	$(obj).parent().find('tbody').toggle();
}