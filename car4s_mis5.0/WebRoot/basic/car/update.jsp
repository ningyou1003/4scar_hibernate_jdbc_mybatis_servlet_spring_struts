<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>整车更新页面</title>
</head>
<body>
    <form action="${_cxt}/car_update.do" 
          method="post" enctype="multipart/form-data">
     <!-- 隐藏域 用于判断是不是提交表单-->
     <s:hidden name="commit" value="true"/>
     <%-- 隐藏域 待更新记录的ID--%>
     <s:hidden name="car.id"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">部门更新</th>
            </tr>
          </thead>
          <tbody>
          <tr>
           <td align="right">品牌:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="car.brand"/>
           </td>
           <td align="right">车系:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="car.series"/>
           </td>
         </tr>
         <tr>
           <td align="right">车型:</td>
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
             <s:textfield cssClass="ipt" name="car.color"/>
           </td>
           <td align="right">产地:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="car.proPlace"/>
           </td>
         </tr>
         <tr>
           <td align="right">价格:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="car.price"/>
           </td>
           <td align="right">上市日期:</td>
           <td align="left">
             <input type="text" name="car.createDate" class="ipt"
                    value='<s:date name="car.createDate" format="yyyy-MM-dd"/>'/>
           </td>
         </tr>
         <tr>
           <td align="right">汽车图片:</td>
           <td align="left">
             <s:file name="fupload.upload" cssClass="ipt"/>
           </td>
           <td align="right"></td>
           <td align="left"></td>
         </tr>
         <tr>
           <td align="right">备注:</td>
           <td align="left" colspan="3">
             <s:textarea name="car.remark" cols="108"/>
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

