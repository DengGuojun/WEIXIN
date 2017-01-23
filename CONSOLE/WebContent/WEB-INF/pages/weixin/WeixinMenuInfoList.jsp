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
<%@page import="com.lpmas.framework.util.*"%>
<%
	boolean hasDefaultMenu = (boolean)request.getAttribute("hasDefaultMenu");
    List<WeixinMenuInfoBean> list =(List<WeixinMenuInfoBean>)request.getAttribute("WeixinMenuInfoList");
    List<WeixinAccountInfoBean> accountList =(List<WeixinAccountInfoBean>)request.getAttribute("accountList");
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
<title>微信公众号菜单信息</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type='text/javascript' src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/ui.js"></script>
</head>
<body class="article_bg">
	<p class="article_tit">微信公众号菜单信息</p>
	<form action="WeixinMenuInfoList.do" method="post" >
		<div class="search_form">
		       <em class="int_label">微信账号：</em>
			  <select name="accountId" id="accountId">
				<%for(WeixinAccountInfoBean account:accountList){%>
					<option value="<%=account.getAccountId()%>" <%=accountId == account.getAccountId() ? "selected" : "" %>/><%=account.getAccountName() %></option>
				<%}%>
				</select>
			<em class="em1">菜单名称：</em>
			<input type="text" name="menuName" id="menuName" value="<%=ParamKit.getParameter(request, "menuName", "")%>"/>
			 <em class="em1">状态：</em>
             <select name="status" id="status">
    	     <%
    	           int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
    	           for(StatusBean<Integer, String> statusBean:Constants.STATUS_LIST){ %>
                  <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==status)?"selected":"" %>><%=statusBean.getValue() %></option>
             <%} %>
            </select>
             <%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.SEARCH)) {%>
		    	<input type="submit" name="search" value="查找" class="search_btn_sub" >	
			<%}%>
		</div>
		<table width="100%" border="0"  cellpadding="0" class="table_style">
    <tr>
      <th>菜单ID</th>
      <th>菜单名称</th>
      <th>菜单类型</th>
      <th>状态</th>
      <th>操作</th>
    </tr>
    <%
    for(WeixinMenuInfoBean bean:list){%> 
    <tr>
      <td><%=bean.getMenuId() %></td>
      <td><%=bean.getMenuName() %></td>
      <td><%=WeixinMenuConfig.MENU_TYPE_MAP.get(bean.getMenuType())%></td>
      <td><%=Constants.STATUS_MAP.get(bean.getStatus())%></td>
      <td align="center">
		<%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.UPDATE)) {%>
		    <a href="WeixinMenuInfoManage.do?menuId=<%=bean.getMenuId()%>&accountId=<%=accountId%>">修改</a>
		<%}%>
		<%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.CREATE)) {%>
		    | <a href="javascript:void(0)" onclick="sync(<%=bean.getMenuId()%>,this)">同步</a>
		<%}%>
      </td>
    </tr>	
    <%} %>
  </table>
	</form>
<ul class="page_info">
<li class="page_left_btn">
		<%if (adminUserHelper.hasPermission(WeixinResource.WEIXIN_MENU_INFO, OperationConfig.CREATE) && !hasDefaultMenu) {%>
		    <input type="button" name="button" id="button" value="新建"  onclick="javascript:location.href='WeixinMenuInfoManage.do?accountId=<%=accountId%>'">
		<%}%>
</li>
<%@ include file="../include/page.jsp" %>
</ul>		
</body>
<script>
function sync(menuId,ele) {
	var a_ele = $(ele);
	var text = a_ele.text();
	var onclick = a_ele.attr('onclick');
	a_ele.text('正在同步');
	a_ele.removeAttr('onclick');
	
        $.ajax({
            url: 'WeixinMenuInfoCreate.do?accountId=<%=accountId%>&menuId='+menuId,
            contentType: false,
            processData: false,
            type: 'POST',
            success: function (data) {
                console.log(data);
                a_ele.text(text);
                a_ele.attr('onclick',onclick);
                if(data.code == '1'){
                		alert(data.message);
                		window.location.href= 'WeixinMenuInfoList.do'
                }else{
                		alert(data.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(errorThrown);
                alert(errorThrown);
                a_ele.text(text);
                a_ele.attr('onclick',onclick);
            }
        });
}
</script>
</html>