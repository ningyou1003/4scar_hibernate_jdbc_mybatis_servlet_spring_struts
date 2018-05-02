(function($) {

	$.fn.pagePlugin = function(data) {

		var pageInfo = this;

		var defaults = {
			pageNumber : 1,
			totalPage : 1,
			totalRow : 10,
			requst : function() {
			}
		};

		var config = $.fn.extend(defaults, data);

		var getPagehtml = function() {
			// 页面信息
			var a = "<div class=\"message\">";
			var b = "当前第<i class=\"blue\">" + config.pageNumber
					+ "</i>页,总共<i class=\"blue\">" + config.totalPage
					+ "</i>页,<i ";
			var c = "class=\"blue\">" + config.totalRow
					+ "</i>条记录</div>&nbsp;&nbsp;"
			pagehtml = "<div class=\"paginList\"><div>";
			// 开头
			if (config.pageNumber == 1) {
				var fistPage = "<li class=\"paginItemno\"><a>首页</a></li>";
				var beforePage = "<li class=\"paginItemno\"><a>上一页</a></li>";
			} else if (config.pageNumber >= 1) {
				var fistPage = "<li class=\"paginItem\" tag=\"1\"><a>首页</a></li>";
				var beforePage = "<li class=\"paginItem\" tag=\""
						+ (config.pageNumber - 1) + "\"><a>上一页</a></li>";
			}
			// 中间
			pagehtml = pagehtml + fistPage + beforePage;
			if ((config.pageNumber - 3) > 1) {
				pagehtml = pagehtml
						+ "<li class=\"paginItemno\" ><a>...</a></li>&nbsp;&nbsp;";
			}
			for ( var i = 1; i <= config.totalPage; i++) {
				// 显示当前页的前4页和后2页
				if (i <= (parseInt(config.pageNumber) + 3)
						&& i >= (config.pageNumber - 3)) {
					if (config.pageNumber == i) {
						pagehtml = pagehtml
								+ "<li class=\"paginItemno\" tag=\"" + i
								+ "\" ><a>" + i + "</a></li>&nbsp;&nbsp;";
					} else {
						pagehtml = pagehtml + "<li class=\"paginItem\" tag=\""
								+ i + "\"><a>" + i + "</a></li>&nbsp;&nbsp;";
					}
				}
			}
			if ((parseInt(config.pageNumber) + 3) < config.totalPage) {
				pagehtml = pagehtml
						+ "<li class=\"paginItemno\" ><a>...</a></li>&nbsp;&nbsp;";
			}
			// 结尾
			if (config.pageNumber == config.totalPage || config.totalPage == 0) {
				var nextPage = "<li class=\"paginItemno\"><a>下一页</a></li>";
				var lastPage = "<li class=\"paginItemno\"><a>尾页</a></li></div>";
			} else {
				var nextPage = "<li class=\"paginItem\" tag=\""
						+ (++config.pageNumber) + "\"><a>下一页</a></li>";
				var lastPage = "<li class=\"paginItem\" tag=\""
						+ config.totalPage + "\"><a>尾页</a></li></div>";
			}
			pagehtml = pagehtml + nextPage + lastPage;
			pagehtml = pagehtml + a + b + c;
			pagehtml = pagehtml + "</div>";
			return pagehtml;
		};

		var pageChange = function(obj) {
			$(obj).empty();
			var pagehtml = getPagehtml();
			$(obj).html(pagehtml);

			$('.paginItem').click(function() {
				index = $(this).attr("tag");
				config.pageNumber = index;
				if (config.requst) {
					config.requst(index);
				}
				pageChange(pageInfo);
			});
		};

		pageChange(pageInfo);
	};
})(jQuery);