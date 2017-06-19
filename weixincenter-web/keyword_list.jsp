<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>关键词管理</title>
<%@include file="/WEB-INF/views/body/base.jsp" %>
<link rel="stylesheet" type="text/css" href="${path }/commons/admin_resources/css/publicCss.css">
<link rel="stylesheet" type="text/css" href="${path }/commons/admin_resources/css/reply_css/replyManagementCSS.css">  
<script type="text/javascript" src="${path }/commons/admin_resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
function validate_afc(tag){
	var id = "#keywordName"+tag;
	if($(id).val().length>0)
	{
		//alert(tag);
		//document.getElementById('afc'+tag).submit();
		$('#afc'+tag).submit();
	}else{
		alert("请填写关键词!");
		$(id).focus();
	}
	
}
function ConfirmDeleA(keywordId,ruleId)
{
   if(confirm("\n确认删除该规则吗?"))
   {     
   	location.href="${path}/wxKeyWord/wxkeyword_del/"+keywordId+"/"+ruleId;
   }     
}
</script>
</head>

<body>
<!--公共头部 start-->
<%@include file="/WEB-INF/views/body/top.jsp" %>
<!--公finish end-->

<!--公共头部导航 start-->
<div class="WeChat_nav">
    <div class="address">您所在的位置&nbsp;:&nbsp;账号信息&gt;回复管理&gt;<span>关键词管理<font color="red">(所属规则：${wxServiceRule.ruleName })</font></span></div>
</div>
<!--公共头finish end-->

<!--中心内容 start-->
<div class="WeChat_Content">
  <%@include file="/WEB-INF/views/body/left.jsp" %>
  <div class="conright">
      <div class="KeyWordsReply_conright">
        <h2 class="first_title">关键词列表</h2>
        <a href="${path }/wxKeyWord/keyword_new/${ruleId}"><button type="button" class="menu_button">添加</button></a>
        <button type="button" class="menu_button" onclick="Javascript:window.history.go(-1)">返回</button>
        <table>
        <tbody>
                <tr class="title">
                      <th>关键词编号</th>
                      <th>关键词触发标识</th>
                      <!-- <th>对应规则名称(编号)</th> -->
                      <th>添加日期</th>
                      <th>操作</th>
                 </tr>
                 <c:forEach items="${ wxKeyWordList}" var="wxKeyWordList" varStatus="status">
                 <form name="afc${status.index}" action="${path }/wxKeyWord/wxkeyword_update/${wxKeyWordList.id}" id="afc${status.index}" method="post">
                 	<tr class="contentFa">
                     <td class="comment">${wxKeyWordList.id }</td>
                     <td class="comment">
                       <input type="text" id="keywordName${status.index}" name="keywordName" class="Listinput" value="${wxKeyWordList.keywordName }">
                       <input type="hidden" name=ruleId class="Listinput" value="${wxKeyWordList.ruleId }">
                     </td>
                     <!-- <td class="comment">ceshi(123)</td> -->
                     <td class="comment"><fmt:formatDate value="${wxKeyWordList.gmtCreated }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                     <td class="comment commentlist">
                        <a href="javascript:validate_afc('${status.index}')" class="keywords-btn">保存</a>
                        <a href="javascript:ConfirmDeleA(${wxKeyWordList.id},${wxKeyWordList.id });" class="List_delete">删除</a>
                     </td>
                 </tr>
                 </form>
                 </c:forEach>
                 <!-- <tr class="contentFa">
                     <td class="comment">1</td>
                     <td class="comment">
                       <input type="text" class="Listinput">
                     </td>
                     <td class="comment">ceshi(123)</td>
                     <td class="comment">2015/05/15 12:10:29</td>
                     <td class="comment">
                        <a href="#" class="xiu-btn">编辑</a>
                        <a href="#" class="List_delete">删除</a>
                     </td>
                 </tr> -->
        </tbody>
     </table>
     <div class="information rounded">
       <ol></ol>
           	提示：每条规则可对应多条关键字，当用户触发关键字时，进行回复。 
     </div>
     </div>
  </div>
  <div class="clear"></div>
</div>
<!--中finish end-->

<!--公共底部 start-->
<!--公finish end-->
</body>
</html>
