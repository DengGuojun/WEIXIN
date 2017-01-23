<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.lpmas.system.bean.StoreInfoBean"%>
<%@page import="com.lpmas.system.bean.SysApplicationInfoBean"%>
<%@page import="com.lpmas.weixin.console.config.WeixinResource"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@page import="com.lpmas.weixin.bean.*"%>
<%@page import="com.lpmas.admin.config.OperationConfig"%>
<%@page import="com.lpmas.admin.business.AdminUserHelper"%>
<%
	WeixinConfigBean bean=(WeixinConfigBean)request.getAttribute("WeixinConfigBean");
	List<WeixinAccountInfoBean> accountList  = (List<WeixinAccountInfoBean>) request.getAttribute("accountList");
	String accountName = (String)request.getAttribute("accountName");
	int accountId = ParamKit.getIntParameter(request, "accountId",0);
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	String token = (String) request.getAttribute("AccessToken");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信公众号参数信息</title>
<%@ include file="../include/header.jsp"%>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.js"></script>
</head>
<body class="article_bg">
	<p class="article_tit">微信公众号参数信息</p>
	<form id="configForm" action="WeixinAccessTokenManage.do" method="get" >
		<div class="search_form">
		      <em class="int_label">微信账号：</em>
			  <select name="accountId" id="accountId">
				<%for(WeixinAccountInfoBean account:accountList){%>
					<option value="<%=account.getAccountId()%>" <%=accountId==account.getAccountId() ? "selected" : "" %>><%=account.getAccountName() %></option>
				<%}%>
				</select>
				<%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_APP_CONFIG, OperationConfig.SEARCH)) {%> 
				<input type="submit" id="search" name="search" value="查找" class="search_btn_sub" >
				<%}%>
			
		</div>
	</form>
	<form action="WeixinAccessTokenManage.do" method="post" name="formData" id="formData" onsubmit="javascript:return checkForm('formData');">
			<input type="hidden" name="accountId" value="<%=accountId %>"  >
		<div class="modify_form">
		    <p>
		      	<em class="int_label">应用名：</em>
			　	<em><%=accountName %></em>
			</p>
			<p>
				<em class="int_label">AccessToken：</em>
			　     <em><%=token %></em>
			</p>
			<div class="div_center">
		<%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_APP_CONFIG, OperationConfig.UPDATE)) {%>
		 <input type="submit" name="manage" id="manage" class="modifysubbtn" value="刷新" />
		<%}%>
	</div>
		</div>
	</form>
		
</body>
</html>