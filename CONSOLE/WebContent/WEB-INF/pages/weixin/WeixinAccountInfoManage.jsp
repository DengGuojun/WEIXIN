<%@page import="com.lpmas.framework.util.StringKit"%>
<%@page import="com.lpmas.framework.util.ReflectKit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.lpmas.system.bean.StoreInfoBean"%>
<%@page import="com.lpmas.system.bean.SysApplicationInfoBean"%>
<%@page import="com.lpmas.weixin.console.config.WeixinResource"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@page import="com.lpmas.weixin.bean.*"%>
<%@page import="com.lpmas.weixin.console.config.*"%>
<%@page import="com.lpmas.weixin.config.*"%>
<%@page import="com.lpmas.admin.config.OperationConfig"%>
<%@page import="com.lpmas.admin.business.AdminUserHelper"%>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@page import="com.lpmas.admin.bean.*"%>
<%
    WeixinAccountInfoBean bean=(WeixinAccountInfoBean)request.getAttribute("WeixinAccountInfoBean");
    WeixinConfigBean weixinConfigBean=(WeixinConfigBean)request.getAttribute("WeixinConfigBean");
    String encodeId = (String)request.getAttribute("EncodeId");
    List<SysApplicationInfoBean> appList  = (List<SysApplicationInfoBean>) request.getAttribute("SysApplicationList");
    List<StoreInfoBean> storeList  = (List<StoreInfoBean>) request.getAttribute("StoreList");
    List<AdminGroupInfoBean> adminGroupInfoList  = (List<AdminGroupInfoBean>) request.getAttribute("AdminGroupInfoList");
	int accountId = bean.getAccountId();

%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信帐号管理</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=STATIC_URL %>/js/common.js"></script>
</head>
<body class="article_bg">
    <div class="article_tit">
		<a href="javascript:history.back()" ><img src="<%=STATIC_URL %>/images/back_forward.jpg"/></a> 
		<ul class="art_nav">
			<li><a href="WeixinAccountInfoList.do">微信帐号信息</a>&nbsp;>&nbsp;</li>
			<% if(accountId > 0) {%>
			<li><%=bean.getAccountName() %>&nbsp;>&nbsp;</li>
			<li>修改账号信息</li>
			<%}else{ %>
			<li>新建账号信息</li>
			<%}%>
		</ul>
	</div>
<form action="WeixinAccountInfoManage.do" method="post" name="formData" id="formData" onsubmit="javascript:return checkForm('formData');">
			 <input type="hidden" name="accountId" id="accountId" value="<%=accountId %>"/>
			<div class="modify_form">
			<p>
		      <em class="int_label"><span>*</span>公众号名称：</em>
		      <input type="text" name="accountName" id="accountName" size="50" value="<%=bean.getAccountName() %>" checkStr="公众号名称;text;true;;100" />
			</p>
		    <p>
		      <em class="int_label"><span>*</span>公众号代码：</em>
		      <input type="text" name="accountCode" id="accountCode" size="50" value="<%=bean.getAccountCode() %>" checkStr="公众号代码;text;true;;100" />
			</p>
			<p>
		      <em class="int_label">加密认证字符串：</em>
		      <em><%=encodeId %></em>
			</p>
			<p>
		      <em class="int_label"><span>*</span>微信用户ID：</em>
		      <input type="text" name="weixinUserId" id="weixinUserId" size="50" value="<%=bean.getWeixinUserId() %>" checkStr="微信用户ID;text;true;;100" />
			</p>
			<p>
		      <em class="int_label"><span>*</span>微信号：</em>
		      <input type="text" name="weixinAccountId" id="weixinAccountId" size="50" value="<%=bean.getWeixinAccountId() %>" checkStr="微信号;text;true;;100" />
			</p>
			<p>
		      <em class="int_label"><span>*</span>公众号类型：</em>
		      <select name="accountType" id="accountType" checkStr="公众号类型;text;true;;100">
			      <option value="">请选择</option>
			     <%Integer accountType = bean.getAccountType(); %>
				<%for(StatusBean<Integer, String> statusBean : WeixinConfig.ACCOUNT_TYPE_LIST){%>
					<option value="<%=statusBean.getStatus() %>" <%=accountType == statusBean.getStatus() ? "selected" : "" %>/><%=statusBean.getValue() %></option>
				<%}%>
				</select>
			</p>
			<p>
			<em class="int_label">应用：</em>
			<select name="appInfoId" id="appInfoId">
			<option value="">请选择</option>
			     <%Integer appId = bean.getAppId(); %>
				<%for(SysApplicationInfoBean appInfoBean:appList){%>
					<option value="<%=appInfoBean.getAppId()%>" <%=appId == appInfoBean.getAppId() ? "selected" : "" %>/><%=appInfoBean.getAppName() %></option>
				<%}%>
				</select>
			</p>
			<p>
			<em class="int_label">商店：</em>
			  <select name="storeId" id="storeId" >
			  	<option value="">请选择</option>
			  	<%Integer storeId = bean.getStoreId() ;%>
				<%for(StoreInfoBean storeInfoBean:storeList){%>
					<option value="<%=storeInfoBean.getStoreId()%>" <%=storeId == storeInfoBean.getStoreId() ? "selected" : "" %>/><%=storeInfoBean.getStoreName() %></option>
				<%}%>
				</select>
			</p>
			<p>
		      <em class="int_label"><span>*</span>用户组ID：</em>
			  <select name="groupId" id="groupId" checkStr="用户组ID;text;true;;100">
			  	<option value="">请选择</option>
			  	<%Integer groupId = bean.getGroupId() ;%>
				<%for(AdminGroupInfoBean adminGroupInfoBean : adminGroupInfoList){%>
					<option value="<%=adminGroupInfoBean.getGroupId() %>" <%=groupId == adminGroupInfoBean.getGroupId() ? "selected" : "" %>/><%=adminGroupInfoBean.getGroupName() %></option>
				<%}%>
			  </select>
			</p>
			<p>
		      <em class="int_label"><span>*</span>微信公众号AppID：</em>
		      <input type="text" name="appId" id="appId" size="50" value="<%=weixinConfigBean.getAppId() %>" checkStr="微信公众号AppID;text;true;;100" /> 
			</p>
			<p>
		      <em class="int_label"><span>*</span>微信公众号Secret：</em>
		      <input type="text" name="appSecret" id="appSecret" size="50" value="<%=weixinConfigBean.getAppSecret() %>" checkStr="微信公众号Secret;text;true;;100" />
			</p>
			<p>
		      <em class="int_label"><span>*</span>消息接入Token：</em>
		      <input type="text" name="token" id="token" size="50" value="<%=weixinConfigBean.getToken() %>" checkStr="消息接入Token;text;true;;100" /> 
			</p>
			<p>
		      <em class="int_label"><span>*</span>消息加密密钥：</em>
		      <input type="text" name="cryptoKey" id="cryptoKey" size="50" value="<%=weixinConfigBean.getCryptoKey() %>" checkStr="消息加密密钥;text;true;;100" />
			</p>
			<p>
		      <em class="int_label"><span>*</span>消息分发队列名：</em>
		      <input type="text" name="queueName" id="queueName" size="50" value="<%=weixinConfigBean.getQueueName() %>" checkStr="消息分发队列名;text;true;;100" />
			</p>
			<p>
	          <em class="int_label">有效状态：</em>
	          <input type="checkbox" name="status" id="status" value="<%=Constants.STATUS_VALID %>" <%=(bean.getStatus()==Constants.STATUS_VALID)?"checked":"" %>/>
	        </p>
		</div>
		<div class="div_center">
            <input type="submit" name="submit" id="submit" class="modifysubbtn" value="提交" />
	  	    <input type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">
	  </div>
</form>
		
</body>
</html>