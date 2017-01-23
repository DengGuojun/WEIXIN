<%@page import="com.lpmas.weixin.client.config.WeixinQrcodeConfig"%>
<%@page import="com.lpmas.framework.util.ListKit"%>
<%@page import="com.lpmas.framework.util.MapKit"%>
<%@page import="com.lpmas.framework.util.DateKit"%>
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
<%@ page import="com.lpmas.framework.page.*"  %>
<%
	List<WeixinAccountInfoBean> accountList =(List<WeixinAccountInfoBean>)request.getAttribute("accountList");
	Map<Integer,String> accountMap = ListKit.list2Map(accountList, "accountId","accountName");
    List<QrcodeInfoBean> list =(List<QrcodeInfoBean>)request.getAttribute("QrcodeInfoList");
    int accountId = ParamKit.getIntParameter(request, "accountId",accountList.get(0).getAccountId());
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	String type = ParamKit.getParameter(request, "qrcodeType", "").trim();
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二维码列表</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type='text/javascript' src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/ui.js"></script>
</head>
<body class="article_bg">
	<p class="article_tit">自动回复规则列表</p>
	<form action="WeixinQrcodeInfoList.do" method="post" >
		<div class="search_form">
		       <em class="int_label">微信账号：</em>
			  <select name="accountId" id="accountId">
				<%for(WeixinAccountInfoBean account:accountList){%>
				   <option value="<%=account.getAccountId()%>" <%=accountId==account.getAccountId() ? "selected" : "" %>/><%=account.getAccountName() %></option>
				<%}%>
				</select>
				<em class="int_label">应用场景ID：</em>
				<input type="text" id="appSceneId" name="appSceneId" value="<%=ParamKit.getParameter(request, "appSceneId", "")%>">
				<em class="int_label">场景ID：</em>
				<input type="text" id="sceneId" name="sceneId" value="<%=ParamKit.getParameter(request, "sceneId", "")%>">
				<em class="int_label">二维码类型：</em>
				
			  <select name="qrcodeType" id="qrcodeType">
				<%for(StatusBean<String,String> qrcodeType:WeixinQrcodeConfig.QRCODE_TYPE_LIST){%>
					<option value="<%=qrcodeType.getStatus()%>" <%=qrcodeType.getStatus().equals(type) ? "selected" : "" %>/><%=qrcodeType.getValue() %></option>
				<%}%>
				</select>
                <input type="submit" name="search" value="查找" class="search_btn_sub" >			
		</div>
		<table width="100%" border="0"  cellpadding="0" class="table_style">
    <tr>
      <th>微信账号</th>
      <th>二维码</th>
      <th>微信场景ID</th>
      <th>应用场景ID</th>
      <th>ticket</th>
      <th>创建时间</th>
      <!-- <th>操作</th> -->
    </tr>
    <%for(QrcodeInfoBean bean:list){%> 
    <tr>
      <td><%=MapKit.getValueFromMap(bean.getAccountId(), accountMap) %></td>
      <td><img id="qrcode" alt="" width="100px" src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=<%=bean.getTicket() %>"></td>
      <td><%=bean.getSceneId() %></td>
      <td><%=bean.getAppSceneId() %></td>
      <td><%=bean.getTicket() %></td>
      <td><%=DateKit.formatDate(bean.getCreateTime().getTime(), DateKit.DEFAULT_DATE_TIME_FORMAT)%></td>
    </tr>	
    <%} %>
  </table>
	</form>
<ul class="page_info">
<li class="page_left_btn">
   <%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_QRCODE_INFO, OperationConfig.CREATE)) {%>
  <input type="button" name="button" id="button" value="新建"  onclick="javascript:location.href='WeixinQrcodeInfoManage.do?accountId=<%=accountId %>'">
  <%} %>
</li>
<%@ include file="../include/page.jsp" %>
</ul>		
</body>
</html>