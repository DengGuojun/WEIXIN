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
    List<AutoReplyMessageInfoBean> list =(List<AutoReplyMessageInfoBean>)request.getAttribute("AutoReplyMessageList");
    Integer accountId = ParamKit.getIntParameter(request, "accountId", accountList.get(0).getAccountId());
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自动回复规则列表</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type='text/javascript' src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/ui.js"></script>
</head>
<body class="article_bg">
	<p class="article_tit">自动回复规则列表</p>
	<form action="AutoReplyMessageInfoList.do" method="post" >
		<div class="search_form">
		       <em class="int_label">微信账号：</em>
			  <select name="accountId" id="accountId">
				<%for(WeixinAccountInfoBean account:accountList){%>
					<option value="<%=account.getAccountId()%>" <%=accountId == account.getAccountId() ? "selected" : "" %>/><%=account.getAccountName() %></option>
				<%}%>
				</select>
				<em class="int_label">自动回复名称：</em>
            <input type="text" name="replyName" id="replyName" value="<%=ParamKit.getParameter(request, "replyName", "").trim()%>">
				<em class="em1">状态：</em>
             <select name="status" id="status">
    	     <%int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
    	        for(StatusBean<Integer, String> statusBean:Constants.STATUS_LIST){ %>
                <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==status)?"selected":"" %>><%=(statusBean.getStatus()==Constants.STATUS_VALID)?"有效":"无效" %></option>
             <%} %>
            </select>
                <input type="submit" name="search" value="查找" class="search_btn_sub" >			
		</div>
		<table width="100%" border="0"  cellpadding="0" class="table_style">
    <tr>
      <th>微信账号</th>
      <th>自动回复名称</th>
      <th>自动回复类型</th>
      <th>自动回复规则</th>
      <th>状态</th>
      <th>操作</th>
    </tr>
    <%for(AutoReplyMessageInfoBean bean:list){%> 
    <tr>
      <td><%=MapKit.getValueFromMap(bean.getAccountId(), accountMap) %></td>
      <td><%=bean.getReplyName() %></td>
      <td><%=AutoReplyMessageConfig.AR_TYPE_MAP.get(bean.getReplyType())%></td>
      <td><%=bean.getMessageMatchRule() %></td>
      <td><%=Constants.STATUS_MAP.get(bean.getStatus())%></td>
      <td align="center">
      <%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_AUTO_REPLY, OperationConfig.UPDATE)) {%>
	  	<a href="AutoReplyMessageInfoManage.do?replyId=<%=bean.getReplyId() %>&accountId=<%=bean.getAccountId() %>">修改</a>
	  <%} %>
      </td>
    </tr>	
    <%} %>
  </table>
	</form>
<ul class="page_info">
<li class="page_left_btn">
   <%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_AUTO_REPLY, OperationConfig.CREATE)) {%>
  <input type="button" name="button" id="button" value="新建"  onclick="javascript:location.href='AutoReplyMessageInfoManage.do?accountId=<%=accountId %>'">
  <%} %>
</li>
<%@ include file="../include/page.jsp" %>
</ul>		
</body>
</html>