<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.lpmas.system.bean.StoreInfoBean"%>
<%@page import="com.lpmas.system.bean.SysApplicationInfoBean"%>
<%@page import="com.lpmas.weixin.bean.*"%>
<%@page import="com.lpmas.weixin.config.*"%>
<%@page import="com.lpmas.weixin.console.config.*"%>
<%@page import="com.lpmas.admin.config.OperationConfig"%>
<%@page import="com.lpmas.admin.business.AdminUserHelper"%>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%
    List<WeixinMaterialInfoBean> list =(List<WeixinMaterialInfoBean>)request.getAttribute("WeixinMaterialInfoList");
    List<WeixinAccountInfoBean> accountList =(List<WeixinAccountInfoBean>)request.getAttribute("accountList");
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
<title>微信后台管理 － 素材管理</title>
</head>
<body>
<div class="page-media-list">
		<div class="container cell-layout">
			<div class="col-main">
			<form action="WeixinMaterialInfoList.do?mediaType=video" method="post" >
				<div class="main-hd">
					<h2>素材管理</h2>
					<div class="title-tab">
						<ul class="tab-navs title-tab">
							<li class="tab-nav first"><a href="javascript:;" class="" >图文消息</a></li>
							<li class="tab-nav"><a href="javascript:;">图片</a></li>
							<li class="tab-nav"><a href="javascript:;">语音</a></li>
							<li class="tab-nav selected"><a href="javascript:;">视频</a></li>
						</ul>
					</div>
					 <div class="search-bar">
						<span class="frm-input-box search with-del append">
							<a class="del-btn"><i class="icon-search-del"></i></a>
							<a class="frm-input-append"><i class="search-gray"></i></a>
							<select name="accountId" id="accountId" >
				             <%for(WeixinAccountInfoBean account:accountList){%>
				             <option value="<%=account.getAccountId()%>" <%=accountId==account.getAccountId() ? "selected" : "" %>/><%=account.getAccountName() %></option>
				             <%}%>
				            </select>
				            <input type="submit" name="search" value="查找" class="search_btn_sub" >	
						</span>
					</div>
					<div class="highlight-box">
						<ul class="links">
							<li class="links-item selected"><a>视频</a></li>
							<li class="links-item"><a>小视频</a></li>
						</ul>
					</div>
				</div>
				<div class="main-bd video"> <!-- smallvideo 小视频class -->
					<div class="search-bar dn"></div><!-- searchbox -->
					<div class="sub-title-bar">
						<div class="info">
							<h3><span>视频列表</span>（共<span><%=PAGE_BEAN.getTotalRecordNumber() %></span>个）</h3>
						</div>
						<div class="opr">
							<a class="btn btn-add btn-primary">新建视频</a>
						</div>
					</div>
					</div>
						<!-- 删除 -->
				<div class="popover pos-center dn">
					<div class="popover-inner">
						<div class="popover-content">
							<p class="tl">确定删除此素材？</p>
						</div>
						<div class="popover-bar">
							<a href="" class="btn btn-primary">确定</a>
							<a href="" class="btn btn-default">取消</a>
						</div>
						<i class="popover-arrow popover-arrow-out"></i>
						<i class="popover-arrow popover-arrow-in"></i>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>