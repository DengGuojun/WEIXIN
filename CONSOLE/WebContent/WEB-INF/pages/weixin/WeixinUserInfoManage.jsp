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
	WeixinUserInfoBean bean=(WeixinUserInfoBean)request.getAttribute("WeixinUserInfoBean");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信公众号用户管理</title>
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
			<li><a href="WeixinUserInfoList.do?accountId=<%=accountId %>&openId=<%=bean.getOpenId() %>">微信公众号用户管理</a>&nbsp;>&nbsp;</li>
			<li><%=accountName %>&nbsp;>&nbsp;</li>
			<li><%=bean.getNickName() %></li>
		</ul>
	</div>
			<input type="hidden" name="accountId" id="accountId" value="<%=accountId %>"  >
			<input type="hidden" name="openId" id="openId" value="<%=bean.getOpenId()%>"  >
			<div class="modify_form">
			<p>
		      <em class="int_label">公众号：</em>
		      <em><%=accountName %></em>
			</p>
		    <p>
		      <em class="int_label">_id：</em>
		      <em><%=bean.get_id() %></em>
			</p>
			<p>
		      <em class="int_label">openId：</em>
		      <em><%=bean.getOpenId() %></em>
			</p>
			<p>
		      <em class="int_label">昵称：</em>
		      <em><%=bean.getNickName() %></em>
			</p>
			<p>
		      <em class="int_label">头像：</em>
		      <em><img width="100px" src="<%=bean.getHeadImageUrl() %>"></em>
			</p>
			<p>
		      <em class="int_label">性别：</em>
		      <em><%=GenderConfig.GENDER_MAP.get(bean.getSex()) %></em>
			</p>
			<p>
		      <em class="int_label">地区：</em>
		      <em><%=bean.getCountry()+bean.getProvince()+bean.getCity() %></em>
			</p>
			<p>
		      <em class="int_label">语言：</em>
		      <em><%=bean.getLanguage() %></em>
			</p>
			<p>
		      <em class="int_label">是否关注者：</em>
		      <em><%=Constants.STATUS_VALID==bean.getIsSubscribe()? "是":"否"%></em>
			</p>
			<p>
		      <em class="int_label">最后关注时间：</em>
		      <em><%=bean.getSubscribeTime()>0? DateKit.formatDate(new Date(bean.getSubscribeTime()), DateKit.DEFAULT_DATE_TIME_FORMAT):"--"%></em>
			</p>
			<p>
		      <em class="int_label">最后取消关注时间：</em>
		      <em><%=bean.getUnsubscribeTime()>0? DateKit.formatDate(new Date(bean.getUnsubscribeTime()), DateKit.DEFAULT_DATE_TIME_FORMAT):"--"%></em>
			</p>
			<p>
		      <em class="int_label">备注：</em>
		      <em><%=bean.getRemark()%></em>
			</p>
			<p>
		      <em class="int_label">标签列表：</em>
			　<%for(Long tag : bean.getTagIdList()){ %>
				<em><%=tag%></em>
			<%} %>
			</p>
		</div>
<%-- 		<%if(Constants.STATUS_VALID==bean.getIsSubscribe()&&(adminUserHelper.hasPermission(WeixinResource.WEIXIN_USER_INFO, OperationConfig.UPDATE)||adminUserHelper.hasPermission(WeixinResource.WEIXIN_USER_INFO, OperationConfig.CREATE))){ %>
		<p>用户操作:</p>
			<div class="modify_form">
			<input type="button" name="modifyRemark" id="modifyRemark" class="modifysubbtn" value="修改备注" />
			</div>
		<%} %> --%>
		<div class="div_center">
	  	    <input type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">
		</div>
		
</body>
<script>
var accountId = $("#accountId").val();
var openId = $("#openId").val();
$(document).ready(function() {
	var readonly = '${readOnly}';
	if(readonly=='true') {
		disablePageElement();
	}
	
	$("#modifyRemark").click(
			function() {
				$.fancybox.open({
					href : 'WeixinUserInfoRemark.do?accountId='+accountId+'&openId='+openId,
					type : 'iframe',
					width : 450,
					minHeight : 200
			});
		});
});
</script>
</html>