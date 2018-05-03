<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>厂商新增页面</title>
</head>
<body>
    <form action="${_cxt}/supplier_add.do" method="post">
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">厂商新增</th>
            </tr>
          </thead>
          <tbody>
         <tr>
           <td align="right">供应商名称:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="supplier.name"/>
           </td>
           <td align="right"></td>
           <td align="left"></td>
         </tr>
         <tr>
           <td align="right">联系人:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="supplier.contacts"/>
           </td>
           <td align="right">联系电话:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="supplier.contactTel"/>
           </td>
         </tr>
         <tr>
           <td align="right">开户银行:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="supplier.bankName"/>
           </td>
           <td align="right">银行账号:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="supplier.bankAccount"/>
           </td>
         </tr>
         <tr>
           <td align="right">备注:</td>
           <td align="left" colspan="3">
             <s:textarea name="supplier.remark" cols="105"/>
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

