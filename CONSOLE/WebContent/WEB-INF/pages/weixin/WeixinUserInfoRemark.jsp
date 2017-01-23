<%@page import="com.lpmas.framework.util.DateKit"%>
<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.lpmas.system.bean.StoreInfoBean"%>
<%@page import="com.lpmas.system.bean.SysApplicationInfoBean"%>
<%@page import="com.lpmas.weixin.console.config.WeixinResource"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>

<%@page import="com.lpmas.weixin.bean.*"%>
<%@page import="com.lpmas.weixin.console.config.*"%>
<%@page import="com.lpmas.admin.config.OperationConfig"%>
<%@page import="com.lpmas.admin.business.AdminUserHelper"%>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%
	int accountId = ParamKit.getIntParameter(request, "accountId",0);
	String accountName = (String)request.getAttribute("accountName");
	WeixinUserInfoBean bean=(WeixinUserInfoBean)request.getAttribute("WeixinUserInfoBean");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户备注管理</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/common.js"></script>
</head>
<body class="article_bg">
    <div class="article_tit">
		<ul class="art_nav">
			<li>用户备注管理&nbsp;>&nbsp;</li>
			<li><%=accountName %>&nbsp;>&nbsp;</li>
			<li><%=bean.getNickName() %></li>
		</ul>
	</div>
	<form action="WeixinUserInfoRemark.do" method="post" name="formData" id="formData" onsubmit="javascript:return checkForm('formData');">
			<input type="hidden" name="accountId" value="<%=accountId %>"  >
			<input type="hidden" name="openId" value="<%=bean.getOpenId()%>"  >
			<div class="modify_form">
			
		      <em>备注：</em>
			　<input type="text" name="remark" value="<%=bean.getRemark()%>" checkStr="备注;text;true;;100" >
			
		</div>
		<div class="div_center">
		<%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_USER_INFO, OperationConfig.UPDATE)) {%>
		     <input type="submit" name="submit" id="submit" class="modifysubbtn" value="提交" />
		<%}%>
	  	    <input type="button" name="cancel" id="cancel" value="取消" onclick="javascript:try{ self.parent.jQuery.fancybox.close();}catch(e){}">
	</div>
	</form>
		
</body>
</html>