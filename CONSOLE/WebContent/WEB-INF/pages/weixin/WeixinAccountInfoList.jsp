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
    List<WeixinAccountInfoBean> list =(List<WeixinAccountInfoBean>)request.getAttribute("WeixinAccountInfoList");
	Integer appId = ParamKit.getIntParameter(request, "appId", 0);
	Integer storeId = ParamKit.getIntParameter(request, "storeId", 0);
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信帐号（公众号）信息</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type='text/javascript' src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/ui.js"></script>
</head>
<body class="article_bg">
	<p class="article_tit">微信帐号（公众号）信息</p>
	<form action="WeixinAccountInfoList.do" method="post" >
		<div class="search_form">
			<em class="em1">公众号名称：</em>
			<input type="text" name="accountName" id="accountName" value="<%=ParamKit.getParameter(request, "accountName", "")%>">	
			<em class="em1">公众号类型：</em>
             <select name="accountType" id="accountType">
             <option value=""></option>
    	     <%
    	           int accountType = ParamKit.getIntParameter(request, "accountType",0);
    	           for(StatusBean<Integer, String> type:WeixinConfig.ACCOUNT_TYPE_LIST){ %>
                  <option value="<%=type.getStatus() %>" <%=(type.getStatus()==accountType)?"selected":"" %>><%=type.getValue() %></option>
             <%} %>
            </select>
			 <em class="em1">状态：</em>
             <select name="status" id="status">
    	     <%
    	           int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
    	           for(StatusBean<Integer, String> statusBean:Constants.STATUS_LIST){ %>
                  <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==status)?"selected":"" %>><%=statusBean.getValue() %></option>
             <%} %>
            </select>
                <input type="submit" name="search" value="查找" class="search_btn_sub" >			
		</div>
		<table width="100%" border="0"  cellpadding="0" class="table_style">
    <tr>
      <th>公众号ID</th>
      <th>公众号名称</th>
      <th>公众号类型</th>
      <th>状态</th>
      <th>操作</th>
    </tr>
    <%
    for(WeixinAccountInfoBean bean:list){%> 
    <tr>
      <td><%=bean.getAccountId() %></td>
      <td><%=bean.getAccountName() %></td>
      <td><%=WeixinConfig.ACCOUNT_TYPE_MAP.get(bean.getAccountType())%></td>
      <td><%=Constants.STATUS_MAP.get(bean.getStatus())%></td>
      <td align="center">
        <a href="/weixin/WeixinAccountInfoManage.do?accountId=<%=bean.getAccountId()%>">修改</a>
      </td>
    </tr>	
    <%} %>
  </table>
	</form>
<ul class="page_info">
<li class="page_left_btn">
  	<input type="button" name="button" id="button" value="新建"  onclick="javascript:location.href='WeixinAccountInfoManage.do'">
</li>
<%@ include file="../include/page.jsp" %>
</ul>		
</body>
</html>