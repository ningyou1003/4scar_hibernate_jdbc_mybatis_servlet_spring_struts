<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>客户新增页面</title>
</head>
<body>
    <form action="${_cxt}/customer_add.do" method="post">
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         客户新增 <i class="tip-up"></i>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td align="right">客户姓名:</td>
              <td align="left">
                 <s:textfield name="customer.name" cssClass="ipt"/>
              </td>
              <td align="right">性别:</td>
              <td align="left">
                 <s:select name="customer.sex"
                       list="#application.SYS_SEX" cssClass="ipt"
                       headerKey="0" headerValue="--请选择--"/>
              </td>              
            </tr>
            <tr>
              <td align="right">身份证:</td>
              <td align="left">
                 <s:textfield name="customer.idNo" cssClass="ipt"/>
              </td>
              <td align="right">联系电话:</td>
              <td align="left">
                 <s:textfield name="customer.contactTel" cssClass="ipt"/>
              </td>
            </tr>
            <tr>
              <td align="right">职业:</td>
              <td align="left">
                 <s:textfield name="customer.vocation" cssClass="ipt"/>
              </td>
              <td align="right">工作单位:</td>
              <td align="left">
                 <s:textfield name="customer.workunit" cssClass="ipt"/>
              </td>
            </tr>
            <tr>
              <td align="right">联系地址:</td>
              <td align="left">
                 <s:textfield name="customer.address" cssClass="ipt"/>
              </td>
              <td align="right">备注:</td>
              <td align="left">
                 <s:textfield name="customer.remark" cssClass="ipt"/>
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

