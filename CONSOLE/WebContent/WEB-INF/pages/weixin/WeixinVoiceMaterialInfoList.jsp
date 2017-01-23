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
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.web.ParamKit"%>
<%@ page import="java.sql.Timestamp"%>
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
				<div class="main-hd">
					<h2>素材管理</h2>
					<div class="title-tab">
						<ul class="tab-navs title-tab">
							<li class="tab-nav first"><a href="javascript:;" class="" >图文消息</a></li>
							<li class="tab-nav"><a href="javascript:;">图片</a></li>
							<li class="tab-nav selected"><a href="javascript:;">语音</a></li>
							<li class="tab-nav"><a href="javascript:;">视频</a></li>
						</ul>
					</div>
				</div>
				<form action="WeixinMaterialInfoList.do?mediaType=voice" method="post" >
				<div class="main-bd">
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
					<div class="sub-title-bar">
						<div class="info"></div>
						<div class="opr">
							<a class="btn btn-add btn-primary">新建语音</a>
						</div>						
					</div>
					
					<div class="media-list group voice-media">
						<!-- 无语音 -->
						<%if(list.size() < 1){ %>
						<div class="empty-tips" style="display: none">暂无数据，可点击右上角新建按钮创建</div>
						<!-- 有语音 -->
						<%}else{ %>
						<%for(WeixinMaterialInfoBean bean:list){%> 
						<div class="msg-card audio-msg-card media-card mini no-opr-border">
							<div class="msg-card-inner">
								<div class="msg-card-bd">
									<div class="audio-msg-wrp card file-wrp cover">
										<div class="audio-msg">
											<div class="icon-audio-wrp">
												<span class="icon-audio-msg"></span>
											</div>
											<div class="audio-content">
												<div class="audio-title"><%=bean.getMediaId() %></div>
												<div class="audio-length"></div>												
												<div class="audio-date">创建于:<%=DateKit.formatDate(Long.valueOf(bean.getUpdateTime())*1000, DateKit.DEFAULT_DATE_TIME_FORMAT)%></div>												
											</div>
										</div>
									</div>
								</div>
								<div class="msg-card-ft">
									<ul class="grid-line msg-card-opr-list">
										<li class="grid-item w-3-1 msg-card-opr-item">
											<a href="#"><span class="msg-card-opr-item-inner">
												<i class="icon18-common download-gray"></i>
											</span></a>
										</li>
										<li class="grid-item w-3-1 msg-card-opr-item">
											<a href="#"><span class="msg-card-opr-item-inner">
												<i class="icon18-common edit-gray"></i>
											</span></a>
										</li>
										<li class="grid-item w-3-1 msg-card-opr-item no-extra">
											<a href="#"><span class="msg-card-opr-item-inner">
												<i class="icon18-common del-gray"></i>
											</span></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<%} %>
						<%} %>
					</div>
				</div>	
				</form>
			</div>
		</div>
	</div>
</body>
</html>