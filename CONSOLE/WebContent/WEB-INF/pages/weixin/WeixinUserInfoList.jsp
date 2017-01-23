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
	int accountId = ParamKit.getIntParameter(request, "accountId",accountList.get(0).getAccountId());
	Map<Integer,String> accountMap = ListKit.list2Map(accountList, "accountId","accountName");
    List<WeixinUserInfoBean> list =(List<WeixinUserInfoBean>)request.getAttribute("WeixinUserInfoList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信公众号关注者列表</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type='text/javascript' src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/ui.js"></script>
</head>
<body class="article_bg">
	<p class="article_tit">微信公众号关注者列表</p>
	<form action="WeixinUserInfoList.do" method="post" >
		<div class="search_form">
		      <em class="int_label">微信账号：</em>
			  <select name="accountId" id="accountId">
				<%for(WeixinAccountInfoBean account:accountList){%>
				    <option value="<%=account.getAccountId()%>" <%=accountId==account.getAccountId() ? "selected" : "" %>/><%=account.getAccountName() %></option>
				<%}%>
				</select>
				<em class="int_label">昵称：</em>
				<input type="text" id="nickName" name="nickName" value="<%=ParamKit.getParameter(request, "nickName", "")%>">
				<em class="em1">是否关注者：</em>
             <select name="isSubscribe" id="isSubscribe">
    	     <%
    	           int isSubscribe = ParamKit.getIntParameter(request, "isSubscribe", Constants.STATUS_VALID);
    	           for(StatusBean<Integer, String> statusBean:Constants.STATUS_LIST){ %>
                  <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==isSubscribe)?"selected":"" %>><%=(statusBean.getStatus()==Constants.STATUS_VALID)?"关注者":"非关注者" %></option>
             <%} %>
            </select>
                <input type="submit" name="search" value="查找" class="search_btn_sub" >			
		</div>
		<table width="100%" border="0"  cellpadding="0" class="table_style" >
    <tr>
      <th>微信账号</th>
      <th>头像</th>
      <th>昵称</th>
      <th>openId</th>
      <th>地址</th>
      <th>是否关注</th>
      <th>关注时间</th>
      <th>取消关注时间</th>
      <th>操作</th>
    </tr>
    <%
    for(WeixinUserInfoBean bean:list){
    	String subscribeTime = "--";
    	String unsubscribeTime = "--";
    	try{
    		subscribeTime = DateKit.formatDate(bean.getSubscribeTime(), DateKit.DEFAULT_DATE_TIME_FORMAT);
    		if(bean.getUnsubscribeTime()>0){
    			unsubscribeTime = DateKit.formatDate(bean.getUnsubscribeTime(), DateKit.DEFAULT_DATE_TIME_FORMAT);
    		}
    	}catch(Exception e){
    	}
    %> 
    <tr>
      <td><%=MapKit.getValueFromMap(bean.getAccountId(), accountMap) %></td>
      <td><img width="50px" src="<%=bean.getHeadImageUrl() %>"></td>
      <td><%=bean.getNickName() %></td>
      <td><%=bean.getOpenId() %></td>
      <td><%=bean.getCountry()+bean.getProvince()+bean.getCity() %></td>
      <td><%=Constants.STATUS_VALID==bean.getIsSubscribe()? "是":"否"%></td>
      <td><%=subscribeTime%></td>
      <td><%=unsubscribeTime%></td>
      <td align="center">
       	<a href="WeixinUserInfoManage.do?openId=<%=bean.getOpenId()%>&accountId=<%=bean.getAccountId()%>">修改</a>
      </td>
    </tr>	
    <%} %>
  </table>
	</form>
<ul class="page_info">
<li class="page_left_btn">
  <input type="button" name="button" id="button" value="刷新"  onclick="javascript:location.href='WeixinUserInfoRefresh.do?accountId=<%=accountId %>'">
</li>
<%@ include file="../include/page.jsp" %>
</ul>		
</body>
</html>