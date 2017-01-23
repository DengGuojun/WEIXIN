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
    List<WeixinPropertyConfigBean> list =(List<WeixinPropertyConfigBean>)request.getAttribute("WeixinTemplateList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
    List<WeixinAccountInfoBean> accountList =(List<WeixinAccountInfoBean>)request.getAttribute("accountList");
    Integer accountId = ParamKit.getIntParameter(request, "accountId", accountList.get(0).getAccountId());
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信公众号模板信息</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type='text/javascript' src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/ui.js"></script>
</head>
<body class="article_bg">
	<p class="article_tit">微信公众号模板信息</p>
	<form action="WeixinTemplateList.do" method="post" >
		<div class="search_form">
		      <em class="int_label">微信账号：</em>
			  <select name="accountId" id="accountId">
				<%for(WeixinAccountInfoBean account:accountList){%>
					<option value="<%=account.getAccountId()%>" <%=accountId == account.getAccountId() ? "selected" : "" %>/><%=account.getAccountName() %></option>
				<%}%>
				</select>
				<em class="int_label">模板编号：</em>
				<input type="text" name="templateCode" id="templateCode" value="<%=ParamKit.getParameter(request, "templateCode", "")%>">
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
      <th>属性配置ID</th>
      <th>类型</th>
      <th>模板编号</th>
      <th>状态</th>
      <th>操作</th>
    </tr>
    <%
    for(WeixinPropertyConfigBean bean:list){%> 
    <tr>
      <td><%=bean.getPropertyId() %></td>
      <td><%=WeixinPropertyConfig.PROPERTY_TYPE_MAP.get(bean.getPropertyType())%></td>
      <td><%=bean.getPropertyKey1()%></td>
      <td><%=Constants.STATUS_MAP.get(bean.getStatus())%></td>
      <td align="center">
        <%if(adminUserHelper.checkPermission(WeixinResource.WEIXIN_MATERIAL_INFO, OperationConfig.UPDATE)){ %>
        <a href="/weixin/WeixinTemplateManage.do?propertyId=<%=bean.getPropertyId()%>&accountId=<%=bean.getAccountId()%>">修改</a>
        <%} %>
      </td>
    </tr>	
    <%} %>
  </table>
	</form>
<ul class="page_info">
<li class="page_left_btn">
	<%if(adminUserHelper.checkPermission(WeixinResource.WEIXIN_MATERIAL_INFO, OperationConfig.CREATE)){ %>
        <input type="button" name="button" id="button" value="新建"  onclick="javascript:location.href='WeixinTemplateManage.do?accountId=<%=accountId%>'">
    <%} %>
</li>
<%@ include file="../include/page.jsp" %>
</ul>		
</body>
</html>