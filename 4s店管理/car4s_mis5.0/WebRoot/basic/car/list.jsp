<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>整车列表</title>
   <script type="text/javascript">
   var dialog;
   function showPic(picPath,brand,series){
      var url = '<img src="/'+ picPath
      			+'" width="560" height="384" />';
      alert(url);
      dialog = $.dialog({
    	title: '查看图片(' + brand +'-'+ series +')',
    	lock: true,
    	content: url,
    	padding: 0
	  });
   }
   function closePic(){
     dialog.close();
   }
</script>
</head>
<body>
    <div class="alert alert-info">
                   当前位置<b class="tip"></b>基础数据<b class="tip"></b>整车列表
    </div>
    <form id="fm1" action="${_cxt}/car_list.do"  method="post">
    <!-- 隐藏域 用于判断是不是提交表单-->
    <s:hidden name="commit" value="true"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         查询条件 <i class="tip-up"></i>
                 <div style="color:red;float: right;">
	             ${msg}
	             </div>
              </th>
            </tr>
          </thead>
          <tbody>
           <tr>
	           <td align="right">汽车品牌:</td>
	           <td align="left">
	             <s:textfield name="car.brand" cssClass="ipt"/>
	           </td>
	           <td align="right">车系:</td>
	           <td align="left">
	             <s:textfield name="car.series" cssClass="ipt"/>
	           </td>
         </tr>
         <tr>
           <td align="right">汽车类型:</td>
           <td align="left">
             <s:select name="car.type" 
                       list="#application.CAR_TYPE" cssClass="ipt"
                       headerKey="0" headerValue="--请选择--"
                       listKey="key" listValue="value">
             </s:select>  
           </td>
           <td align="right">排量:</td>
           <td align="left">
             <s:select name="car.volume" 
                       list="#application.CAR_VOL" cssClass="ipt"
                       headerKey="0" headerValue="--请选择--"
                       listKey="key" listValue="value">
             </s:select>  
           </td>
         </tr>
         <tr>
		   <td align="right">颜色:</td>
           <td align="left">
             <s:textfield name="car.color" cssClass="ipt"/>
           </td>
           <td align="right"></td>
           <td align="left">
                   <input class="btn" type="submit" value="查询" />
                   <input class="btn" type="button" onclick="clean();" value="清空" />
                   <input class="btn" type="button" value="新增" 
                          onclick="location.href='${_cxt}/basic/car/add.jsp'"/>
                </td>
            </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="4%">记录ID</th>
	        <th width="8%">汽车品牌-车系</th>
	        <th width="6%">类型</th>
	        <th width="4%">排量</th>
	        <th width="6%">颜色</th>
	        <th width="6%">产地</th>
	        <th width="6%">价格</th>
	        <th width="6%">上市时间</th>
	        <th width="8%">操作</th>
	     </tr>
        </thead>
        <tbody>
            <%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <!-- 删除链接变量 -->
		     <s:url id ="delete" action="car_delete">
		        <s:param name="car.id">${v.id}</s:param>
		     </s:url>
		     <!-- 更新链接变量 -->
		     <s:url id ="update" action="car_update">
		        <s:param name="car.id">${v.id}</s:param>
		     </s:url>	     
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>${v.id}</td>
		        <td>
		          <a href="#" 
		          onclick="showPic('${v.picPath}','${v.brand}','${v.series}');">
                   ${v.brand}－${v.series}</a>
		        </td>
		        <td>
		           <mt:tran data="${applicationScope.CAR_TYPE}" value="${v.type}"/>
		        </td>
		        <td>
		           <mt:tran data="${applicationScope.CAR_VOL}" value="${v.volume}"/>
		        </td>
		        <td>${v.color}</td>
		        <td>${v.proPlace}</td>
		        <td>${v.price}万</td>
		        <td>
		           <s:date name="#v.createDate" format="yyyy-MM-dd"/>
		        </td>
		        <td>
		          <s:a href="%{delete}" cssClass="btn btn-mini btn-danger"
		               onclick="return confirm('你确定删除？');">删除</s:a> 
	              <s:a href="%{update}" cssClass="btn btn-mini btn-primary">更新</s:a> 
		        </td>
	     </tr>
         </s:iterator>
        </tbody>
    </table>
    <center>
    <%@ include file="/common/pager.jsp"%>
    </center>
    </form>
</body>
</html>

