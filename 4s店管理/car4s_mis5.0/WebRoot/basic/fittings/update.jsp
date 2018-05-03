<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>配件更新页面</title>
</head>
<body>
    <form action="${_cxt}/fittings_update.do" method="post">
     <!-- 隐藏域 用于判断是不是提交表单-->
     <s:hidden name="commit" value="true"/>
     <%-- 隐藏域 待更新记录的ID--%>
     <s:hidden name="fittings.id"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         配件更新<i class="tip-up"></i>
              </th>
            </tr>
          </thead>
          <tbody>
         <tr>
           <td align="right">配件名称:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="fittings.name"/>
           </td>
           <td align="right">品牌:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="fittings.brand"/>
           </td>
         </tr>
         <tr>
           <td align="right">型号:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="fittings.type"/>
           </td>
           <td align="right">价格(元):</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="fittings.price"/>
           </td>
         </tr>
         <tr>
           <td align="right">单位:</td>
           <td align="left">
             <s:select name="fittings.unit"
                       list="#application.CAR_UNIT" cssClass="ipt"
                       headerKey="0" headerValue="--请选择--"/>
           </td>
           <td align="right"></td>
           <td align="left"></td>
         </tr>
         <tr>
           <td align="right">备注:</td>
           <td align="left" colspan="3">
             <s:textarea name="fittings.remark" cols="105"/>
           </td>
         </tr>
         <tr>
            <td colspan="2"></td>
            <td colspan="2" align="center">
              <input class="btn" type="submit" value="更新" />
              <input class="btn" type="reset" value="清空" />
              <input class="btn" type="button" value="返回" onclick="history.back()"/>
            </td>
          </tr>
        </tbody>
      </table>
      </form>
</body>
</html>

