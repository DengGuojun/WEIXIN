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
	WeixinMenuInfoBean bean=(WeixinMenuInfoBean)request.getAttribute("WeixinMenuInfoBean");
	int menuId = bean.getMenuId();
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信公众号菜单管理</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/common.js"></script>
</head>
<body class="article_bg">
    <div class="article_tit">
		<a href="javascript:history.back()" ><img src="<%=STATIC_URL %>/images/back_forward.jpg"/></a> 
		<ul class="art_nav">
			<li><a href="WeixinMenuInfoList.do">微信公众号菜单信息</a>&nbsp;>&nbsp;</li>
			<% if(menuId > 0) {%>
			<li><%=bean.getMenuName() %>&nbsp;>&nbsp;</li>
			<li>修改菜单信息</li>
			<%}else{ %>
			<li>新建菜单信息</li>
			<%}%>
		</ul>
	</div>
<form action="WeixinMenuInfoManage.do" method="post" name="formData" id="formData" onsubmit="javascript:return checkForm('formData');">
			<input type="hidden" name="accountId" value="<%=accountId%>"  >
			 <input type="hidden" name="menuId" id="menuId" value="<%=menuId %>"/>
			<div class="modify_form">
			<p>
		      <em class="int_label">公众号：</em>
			　<em><%=ParamKit.getAttribute(request, "accountName", true) %></em>
			</p>
		    <p>
		      <em class="int_label"><span>*</span>菜单名称：</em>
			　<input type="text" name="menuName" id="menuName" size="50" value="<%=bean.getMenuName() %>" checkStr="菜单名称;text;true;;100" />
			</p>
			<p>
		      <em class="int_label"><span>*</span>菜单内容：</em>
		      <textarea  name="menuContent" id="menuContent" cols="60" rows="20" checkStr="菜单内容;txt;true;;10000"><%=bean.getMenuContent() %></textarea>
		    </p>
			<p>
	          <em class="int_label">有效状态：</em>
	          <input type="checkbox" name="status" id="status" value="<%=Constants.STATUS_VALID %>" <%=(bean.getStatus()==Constants.STATUS_VALID)?"checked":"" %>/>
	        </p>
		</div>
		<div class="div_center">
		  	<%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.CREATE)||adminUserHelper.hasPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.UPDATE)) {%>
		    	<input type="submit" name="submit" id="submit" class="modifysubbtn" value="提交" />
			<%}%>
	  	    <input type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">

	</div>
	</form>
		
</body>
</body>
</html>