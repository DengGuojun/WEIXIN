<%@page import="com.lpmas.weixin.api.config.ReceiveEventConfig"%>
<%@page import="com.lpmas.framework.util.MapKit"%>
<%@page import="com.lpmas.framework.util.DateKit"%>
<%@page import="com.lpmas.framework.util.ListKit"%>
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
    List<ReceiveEventBean> list =(List<ReceiveEventBean>)request.getAttribute("ReceiveEventList");
    int accountId = ParamKit.getIntParameter(request, "accountId",accountList.get(0).getAccountId());
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信公众号事件列表</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type='text/javascript' src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/ui.js"></script>
<script type="text/javascript"
	src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
</head>
</head>
<body class="article_bg">
	<p class="article_tit">微信公众号事件列表</p>
	<form action="WeixinReceiveEventList.do" method="post" >
		<div class="search_form">
		      <em class="int_label">微信账号：</em>
			  <select name="accountId" id="accountId">
				<%for(WeixinAccountInfoBean account:accountList){%>
				    <option value="<%=account.getAccountId()%>" <%=accountId==account.getAccountId() ? "selected" : "" %>><%=account.getAccountName() %></option>
				<%}%>
				</select>
			  <em class="int_label">事件类型：</em>
			  <select name="event" id="event">
			  <option value=""></option>
				<%String event = ParamKit.getParameter(request, "event", "").trim();
				for(StatusBean<String,String> statusBean:ReceiveEventConfig.EVENT_LIST){%>
				    <option value="<%=statusBean.getStatus()%>" <%=event.equals(statusBean.getStatus()) ? "selected" : "" %>><%=statusBean.getValue()%></option>
				<%}%>
				</select>
				<em class="em1">查询开始时间：</em> 
			<input type="text"
				name="begDateTime" id="begDateTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-01-01',maxDate:'2020-01-01'})"
				value="<%=ParamKit.getParameter(request, "begDateTime", "")%>"
				size="20" checkStr="查询开始时间;date;false;;100" /> 
			<em class="em1">查询结束时间：</em> 
			<input type="text"
				name="endDateTime" id="endDateTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-01-01',maxDate:'2020-01-01'})"
				value="<%=ParamKit.getParameter(request, "endDateTime", "")%>"
				size="20" checkStr="查询开始时间;date;false;;100" /> 
            <input type="submit" name="search" value="查找" class="search_btn_sub" >			
		</div>
		<table width="100%" border="0"  cellpadding="0" class="table_style">
    <tr>
      <th>微信账号</th>
      <th>事件类型</th>
      <th>发生时间</th>
      <th>操作</th>
    </tr>
    <%
    for(ReceiveEventBean bean:list){
    %> 
    <tr>
      <td><%=MapKit.getValueFromMap(bean.getAccountId(), accountMap) %></td>
      <td><%=ReceiveEventConfig.EVENT_MAP.get(bean.getEvent()) %></td>
      <td><%=bean.getCreateTime()>0? DateKit.formatDate(new Date(bean.getCreateTime()), DateKit.DEFAULT_DATE_TIME_FORMAT):"--"%></td>
      <td align="center">
      	<a href="WeixinReceiveEventManage.do?_id=<%=bean.get_id().toHexString()%>&accountId=<%=bean.getAccountId()%>">查看</a>
      </td>
    </tr>	
    <%} %>
  </table>
	</form>
<ul class="page_info">
<%@ include file="../include/page.jsp" %>
</ul>		
</body>
</html>