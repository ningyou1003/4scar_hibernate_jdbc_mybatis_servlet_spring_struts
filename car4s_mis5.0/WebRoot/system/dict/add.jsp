<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>字典新增页面</title>
</head>
<body>
    <form action="${_cxt}/dict_add.do" method="post">
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         字典新增 <i class="tip-up"></i>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td align="right">字典名称:</td>
              <td align="left">
                 <s:textfield name="dict.dictName" cssClass="ipt"/>
              </td>
              <td align="right">可用标签:</td>
              <td align="left">
                 <s:select list="#application.SYS_USE_FLAG" 
                           name="dict.useFlag" cssClass="ipt"
                           headerKey="0" headerValue="--请选择--"/>  
               </td>           
            </tr>
            <tr>
              <td align="right">字典KEY:</td>
              <td align="left">
                 <s:textfield name="dict.key" cssClass="ipt"/>
              </td>
              <td align="right">字典VALUE:</td>
              <td align="left">
                 <s:textfield name="dict.value" cssClass="ipt"/>
              </td>
            </tr>
            <tr>
                <td colspan="2"></td>
                <td colspan="2" align="center">
                   <input class="btn" type="submit" value="添加" />
                   <input class="btn" type="reset" value="清空" />
                   <input class="btn" type="button" value="返回" onclick="history.back()"/>
                </td>
            </tr>
          </tbody>
      </table>
      </form>
</body>
</html>

