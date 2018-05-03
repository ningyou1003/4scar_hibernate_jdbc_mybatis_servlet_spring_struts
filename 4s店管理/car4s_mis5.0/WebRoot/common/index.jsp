<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${_title}</title>
    <link rel="stylesheet" type="text/css" href="${_css}/admin-all.css" />
    <link rel="stylesheet" type="text/css" href="${_css}/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${_css}/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="${_css}/base.css" />
    <script type="text/javascript" src="${_plugins}/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${_plugins}/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="${_js}/index.js"></script>
<!-- 引入弹出窗口JS库 -->
<script type="text/javascript" 
        src="${_plugins}/lhgdialog/lhgdialog.min.js?self=true"></script>
<script type="text/javascript">
	var dialog;
	function showDialog(url,title){
		//alert(1);
	     dialog = $.dialog({
	        title:title,
	     	width: '500px',
	    	height:'200px',
	    	content: 'url:'+url
		 });
	  }
	//result格式:  班级ID,班级名称
	function closeDialog(){//关闭窗口
	   dialog.close();//关闭窗口
    } 
</script>
</head>
<body>
    <div class="warp">
        <!--头部开始-->
        <div class="top_c">
            <div class="top-menu">
<%--                 <ul class="top-menu-nav">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">查询界面<i class="tip-up"></i></a>
                        <ul class="kidc">
                            <li><a target="Conframe" href="Template/find-form.html">表单样式</a></li>
                            <li><a target="Conframe" href="Template/find-alert.html">弹出窗口</a></li>
                            <li><a target="Conframe" href="Template/find-order.html">查询排序</a></li>
                            <li><a target="Conframe" href="Template/find-1.html">查询结果一</a></li>
                            <li><a target="Conframe" href="Template/find-2.html">查询结果二</a></li>
                        </ul>
                    </li>
                    <li><a href="#">维护界面<i class="tip-up"></i></a>
                        <ul class="kidc">
                            <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-add.html">增加</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-edit.html">修改</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/Maintain-del.html">删除</a></li>
                        </ul>
                    </li>
                    <li><a href="#">表单风格<i class="tip-up"></i></a>
                        <ul class="kidc">
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-Master-slave.html">主从表单</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-collapse.html">折叠表单</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs.html">标签式表单</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-tree.html">树+表单</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-guide.html">向导</a></li>
                            <li><b class="tip"></b><a target="Conframe" href="Template/form-tabs-list.html">标签页+列表</a></li>
                        </ul>
                    </li>
                    <li><a href="#">提示<i class="tip-up"></i></a>
                        <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">权限提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">出错提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">警告提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">询问提示</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框一</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/Alert.html">对话框二</a></li>
                    </ul>
                    </li>
                    <li><a href="#">辅助信息<i class="tip-up"></i></a>
                        <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">寻访记录</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">数据说明</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">操作记录</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="Template/formstyle.html">提示</a></li>
                    </ul>
                    </li>
                </ul> --%>
            </div>
            <div class="top-nav">上午好，欢迎您，${sessionScope.user.name}！&nbsp;&nbsp;
              <a href="javascript:showDialog('${_cxt}/user_pwdupdate.do?id=${sessionScope.user.id}','');">修改密码</a> 
              | <a href="${_cxt}/sys_logout.do">安全退出</a></div>
        </div>
        <!--头部结束-->
        <!--左边菜单开始-->
        <div class="left_c left">
            <center>
              <h1>系统菜单&nbsp;&nbsp;
                 <a href="javascript:void(0);" id="mm1" 
                    style="color: #0b9cd3;font-size:13px;font-family: fzqt;">展开</a>
                 <a href="javascript:void(0);" id="mm2" 
                    style="color: #0b9cd3;font-size:13px;font-family: fzqt;">收起</a>
            </h1>
            </center>
            <div class="acc">
               <s:iterator value="#session.menu">
                <div>
                    <a class="one"><s:property value="key.name"/></a>
                    <ul class="kid">
                       <s:iterator value="value" var="menu">
                        <li><b class="tip"></b>
                            <a target="Conframe" 
                               href='<s:property value="#menu.url"/>'>
                             <s:property value="#menu.name"/></a>
                        </li>
                       </s:iterator>
                    </ul>
                 </div>
                </s:iterator>
                <!-- <div id="datepicker"></div> -->
            </div>

        </div>
        <!--左边菜单结束-->
        <!--右边框架开始-->
        <div class="right_c">
            <div class="nav-tip" onclick="javascript:void(0)">&nbsp;</div>
        </div>
        <div class="Conframe">
            <iframe name="Conframe" id="Conframe" src="${_cxt}/common/welcome.jsp"></iframe>
        </div>
        <!--右边框架结束-->
        <!--底部开始-->
        <div class="bottom_c">Copyright &copy;2010-2016 南宁易唐科技有限公司 版权所有</div>
        <!--底部结束-->
    </div>
</body>
</html>
