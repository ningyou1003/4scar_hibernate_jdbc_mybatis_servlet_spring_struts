<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript"> 
//跳到第i页 
function gotoPage(i)  
{  
    var currentForm = document.getElementById("fm1");
    var currentPage = document.getElementById("currentPage");
    if(i!=-1){
      currentPage.value=i;
    }
    //总页数 
    var totalPages = <s:property value="page.totalPages"/>;
    if(currentPage.value < 1){
      alert("跳转页数必须大于0!");
      return;
    }
    if(currentPage.value > totalPages){
      alert("跳转页数必须小于总页数!");
      return;
    }
    //提交表单
    currentForm.submit(); 
    } 
</script>
   <a href="#" onclick="gotoPage(1)">首页</a>
   <s:if test="page.hasPrevious">
        <a href="#" onclick="gotoPage(<s:property value="page.prePage"/>)">上一页</a>  
   </s:if>
   <s:else>上一页</s:else>
   <s:if test="page.hasNext">
        <a href="#" onclick="gotoPage(<s:property value="page.nextPage"/>)">下一页</a>  
   </s:if>
   <s:else>下一页</s:else>
   <a href="#" onclick="gotoPage(<s:property value='page.totalPages'/>)">未页</a>
   <a href="#" onclick="gotoPage(-1)">点击跳转到</a>
   <s:textfield id="currentPage" name="page.currentPage" size="1"></s:textfield>页 
        当前第<b><s:property value="page.currentPage"/></b>页
        总记录数:<b><s:property value="page.totalRows"/></b>条
        总页数:<b><s:property value="page.totalPages"/></b>页
    
