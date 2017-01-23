<%@page import="com.lpmas.weixin.api.config.ReceiveEventConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.lpmas.system.bean.StoreInfoBean"%>
<%@page import="com.lpmas.system.bean.SysApplicationInfoBean"%>
<%@page import="com.lpmas.weixin.console.config.WeixinResource"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@page import="com.lpmas.framework.util.DateKit"%>
<%@page import="com.lpmas.weixin.bean.*"%>
<%@page import="com.lpmas.weixin.config.*"%>
<%@page import="com.lpmas.weixin.console.config.*"%>
<%@page import="com.lpmas.admin.config.OperationConfig"%>
<%@page import="com.lpmas.admin.business.AdminUserHelper"%>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@page import="com.lpmas.weixin.client.config.WeixinQrcodeConfig"%>
<%
	int accountId = ParamKit.getIntParameter(request, "accountId", 0);
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二维码生成</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/common.js"></script>
</head>
<body class="article_bg">
    <div class="article_tit">
		<a href="javascript:history.back()" ><img src="<%=STATIC_URL %>/images/back_forward.jpg"/></a> 
		<ul class="art_nav">
			<li><a href="WeixinQrcodeInfoList.do?accountId=<%=accountId%>">二维码生成</a></li>
		</ul>
	</div>
	<form action="WeixinQrcodeInfoManage.do?accountId=<%=accountId%>" method="post" name="formData" id="formData" onsubmit="javascript:return checkForm('formData');">
	<div class="modify_form">
			<p>
		      <em class="int_label"><span>*</span>应用场景ID:</em>
		      <input type="text" name="appSceneId" id="appSceneId" size="50" value="" checkStr="应用场景ID;text;true;;50" />
			</p>
			<p>
				<em class="int_label">二维码类型:</em>
				<select name="qrcodeType" id="qrcodeType">
					<%for(StatusBean<String,String> qrcodeType:WeixinQrcodeConfig.QRCODE_TYPE_LIST){
						String type = ParamKit.getParameter(request, "qrcodeType", "").trim();
					%>
						<option value="<%=qrcodeType.getStatus()%>" <%=qrcodeType.getStatus().equals(type) ? "selected" : "" %>><%=qrcodeType.getValue() %></option>
					<%}%>
				</select>
			</p>
			<p>
		      <em class="int_label">回调队列名:</em>
		      <input type="text" name="callBackQueue" id="callBackQueue" size="50" value="" checkStr="回调队列名;text;true;;50" />
			</p>
			<p>
		      <em class="int_label">有效时长(秒):</em>
		      <input type="text" name="expireSeconds" id="expireSeconds" size="50" value="" checkStr="有效时长(秒);num;true;;50" />
			</p>
	</div>
		<div class="div_center">
		  	<%if ((adminUserHelper.hasPermission(WeixinResource.WEIXIN_QRCODE_INFO, OperationConfig.CREATE)||adminUserHelper.hasPermission(WeixinResource.WEIXIN_QRCODE_INFO, OperationConfig.UPDATE))) {%>
		    	<input type="submit" name="create" id="create" class="modifysubbtn" value="创建" />
			<%}%>
	    <input type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">
	</div>
	</form>
</body>
</html>