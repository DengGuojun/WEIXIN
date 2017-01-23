<%@page import="com.lpmas.weixin.api.config.ReceiveEventConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.lpmas.system.bean.StoreInfoBean"%>
<%@page import="com.lpmas.system.bean.SysApplicationInfoBean"%>
<%@page import="com.lpmas.weixin.console.config.WeixinResource"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>

<%@page import="com.lpmas.weixin.bean.*"%>
<%@page import="com.lpmas.weixin.config.*"%>
<%@page import="com.lpmas.weixin.console.config.*"%>
<%@page import="com.lpmas.admin.config.OperationConfig"%>
<%@page import="com.lpmas.admin.business.AdminUserHelper"%>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%
	Integer accountId = ParamKit.getIntParameter(request, "accountId", 0);
    AutoReplyMessageInfoBean bean=(AutoReplyMessageInfoBean)request.getAttribute("AutoReplyMessageInfoBean");
	int replyId = bean.getReplyId() ;
	String readOnly = ParamKit.getParameter(request, "readOnly","false").trim();
	request.setAttribute("readOnly", readOnly);
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自动回复规则管理</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/common.js"></script>
</head>
<body class="article_bg">
    <div class="article_tit">
		<a href="javascript:history.back()" ><img src="<%=STATIC_URL %>/images/back_forward.jpg"/></a> 
		<ul class="art_nav">
			<li><a href="AutoReplyMessageInfoList.do?accountId=<%=accountId%>">自动回复规则列表</a>&nbsp;>&nbsp;</li>
			<% if(replyId > 0) {%>
			<li><%=bean.getReplyName() %>&nbsp;>&nbsp;</li>
			<li>修改自动回复信息</li>
			<%}else{ %>
			<li>新建自动回复信息</li>
			<%}%>
		</ul>
	</div>
<form action="AutoReplyMessageInfoManage.do" method="post" name="formData" id="formData" onsubmit="javascript:return checkForm('formData');">
			<input type="hidden" name="accountId" value="<%=accountId%>"  >
			<input type="hidden" name="replyId" id="replyId" value="<%=replyId %>"/>
			<div class="modify_form">
		    <p>
		      <em class="int_label"><span>*</span>自动回复名称：</em>
		      <input type="text" name="replyName" id="replyName" size="50" value="<%=bean.getReplyName() %>" checkStr="自动回复名称;text;true;;100" />
			</p>
		    <p>
      		<em class="int_label"><span>*</span>自动回复类型：</em>
				<select id="replyType" name="replyType" checkStr="自动回复类型;txt;true;;100">
						<option value="">请选择</option>
						<%int replyTypeVar = bean.getReplyType();
						  for (StatusBean<Integer, String> replyType : AutoReplyMessageConfig.AR_TYPE_LIST) {%>
						<option value="<%=replyType.getStatus()%>" <%=replyTypeVar == replyType.getStatus() ? "selected" : ""%>><%=replyType.getValue()%></option>
						<%}%>
				</select>
			</p>
		    <p>
		      <em class="int_label">消息匹配规则:</em>
		      <input type="text" name="messageMatchRule" id="messageMatchRule" size="50" value="<%=bean.getMessageMatchRule() %>" checkStr="消息匹配规则;text;false;;100" />
			</p>
			<p>
		      <em class="int_label"><span>*</span>消息内容:</em>
		      <textarea  name="messageContent" id="messageContent" checkStr="消息内容;text;true;;2000" cols="45" rows="10"><%=bean.getMessageContent() %></textarea>
			</p>
			<p>
		      <em class="int_label">消息描述:</em>
		      <input type="text" name="messageDesc" id="messageDesc" size="50" value="<%=bean.getMessageDesc() %>" checkStr="消息描述;text;false;;100" />
			</p>
			<p>
      		<em class="int_label"><span>*</span>优先级：</em>
      		<input type="text" name="priority" id="priority" value="<%=bean.getPriority()%>"  checkStr="优先级;num;true;;6"/>
			</p>
			<p>
	          <em class="int_label">有效状态：</em>
	          <input type="checkbox" name="status" id="status" value="<%=Constants.STATUS_VALID %>" <%=(bean.getStatus()==Constants.STATUS_VALID)?"checked":"" %>/>
	        </p>
		</div>
		<div class="div_center">
		  	<%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_AUTO_REPLY, OperationConfig.CREATE)||adminUserHelper.hasPermission(WeixinResource.WEIXIN_AUTO_REPLY, OperationConfig.UPDATE)) {%>
		    	<input type="submit" name="submit" id="submit" class="modifysubbtn" value="提交" />
			<%}%>
	  	    <input type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">

	</div>
	</form>
</body>
<script>
$(document).ready(function() {
	var readonly = '${readOnly}';
	if(readonly=='true') {
		disablePageElement();
	}
});

</script>
</html>