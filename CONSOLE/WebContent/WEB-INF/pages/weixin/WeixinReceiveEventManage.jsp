<%@page import="java.util.Map.Entry"%>
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
    Integer accountId = ParamKit.getIntParameter(request, "accountId", 0);
	String accountName = (String)request.getAttribute("accountName");
	ReceiveEventBean bean=(ReceiveEventBean)request.getAttribute("ReceiveEventBean");
	Map<String, Object> contentMap = (Map<String, Object>)request.getAttribute("contentMap");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信公众号事件管理</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.js"></script>
</head>
<body class="article_bg">
    <div class="article_tit">
		<a href="javascript:history.back()" ><img src="<%=STATIC_URL %>/images/back_forward.jpg"/></a> 
		<ul class="art_nav">
			<li><a href="WeixinReceiveEventList.do?accountId=<%=accountId %>">微信公众号事件管理</a>&nbsp;>&nbsp;</li>
			<li><%=accountName %>&nbsp;>&nbsp;</li>
		</ul>
	</div>
			<input type="hidden" name=accountId id="accountId" value="<%=accountId %>"  >
			<div class="modify_form">
			 <p>
		      <em class="int_label">公众号：</em>
			　<em><%=accountName %></em>
			</p>
			<%for(Entry<String,Object> entry:contentMap.entrySet()){ %>
				<p>
			      <em class="int_label"><%=entry.getKey() %>：</em>
			      <em><%=entry.getValue() %></em>
				</p>
			<%} %>
		</div>
		<div class="div_center">
	  	    <input type="button" name="cancel" id="cancel" value="返回" onclick="javascript:history.back()">
		</div>
		
</body>
</html>