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
<%@page import="com.lpmas.framework.web.ParamKit"%>
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
							<li class="tab-nav selected first"><a href="javascript:;" class="" >图文消息</a></li>
							<li class="tab-nav"><a href="javascript:;">图片</a></li>
							<li class="tab-nav"><a href="javascript:;">语音</a></li>
							<li class="tab-nav"><a href="javascript:;">视频</a></li>
						</ul>
					</div>
				</div>
				<form action="WeixinMaterialInfoList.do?mediaType=news" method="post" >
				<div class="main-bd">
				    <div class="search-bar">
						<span class="frm-input-box search with-del append">
							<a class="del-btn"><i class="icon-search-del"></i></a>
							<a class="frm-input-append"><i class="search-gray"></i></a>
							<select name="accountId" id="accountId" placeholder="微信账号" >
				             <%for(WeixinAccountInfoBean account:accountList){%>
				             <option value="<%=account.getAccountId()%>" <%=accountId==account.getAccountId() ? "selected" : "" %>/><%=account.getAccountName() %></option>
				             <%}%>
				            </select>
				            <input type="submit" name="search" value="查找" class="search_btn_sub" >	
						</span>
					</div>
					<div class="search-bar">
						<span class="frm-input-box search with-del append">
							<a class="del-btn"><i class="icon-search-del"></i></a>
							<a class="frm-input-append"><i class="search-gray"></i></a>
							<input type="text" class="frm-input" placeholder="标题/作者/摘要" />
						</span>
					</div>
					<div class="sub-title-bar">
						<div class="info">
							<h3><span>图文消息</span>（共<span><%=PAGE_BEAN.getTotalRecordNumber() %></span>条）</h3>
							<a class="btn btn-add btn-primary btn-new">新建图文消息</a>
						</div>
					</div>
					<%if(list.size() > 0){ %>
					<div class="appmsg-list">
					 <%for(WeixinMaterialInfoBean bean:list){%> 
						<div class="appmsg-col tj-item">
							<div class="inner">
								<!-- 第一种情况 只有一个图文 -->
								<%if(bean.getArticleList().size() == 1){%>
								<div class="appmsg">
									<div class="appmsg-content">
										<div class="appmsg-info">
											<em class="appmsg-date"><%=DateKit.formatDate(Long.valueOf(bean.getCreateTime())*1000, DateKit.DEFAULT_DATE_TIME_FORMAT)%></em>
											<div class="undone-tips">文章不完整</div>
											<!-- 当填写不完整的时候显示 -->
										</div>
									</div>									
									<div class="appmsg-item">
										<h4 class="appmsg-title"><a><%bean.getArticleList().get(0).getTitle();%></a></h4>
										<div class="appmsg-thumb-wrp"></div>
										<p class="appmsg-desc"><%bean.getArticleList().get(0).getDigest(); %></p>
									</div>
									<div class="appmsg-opr">
										<ul>
											<li class="appmsg-opr-item w-2-1">
												<a title="编辑">
													<i class="edit-icon" alt="编辑"></i>
												</a>
											</li>
											<li class="appmsg-opr-item w-2-1 grid-item no-extra">
												<a class="no-extra" title="删除">
													<i class="del-icon"></i>
												</a>
											</li>
										</ul>
									</div>
								</div>
								<%} %>
								<!-- 第二个情况 多个图文 -->
								<%if(bean.getArticleList().size() > 1){%>
								<div class="appmsg multi has-first-cover">
									<div class="appmsg-content">
										<div class="appmsg-info">
											<em class="appmsg-date"><%=DateKit.formatDate(Long.valueOf(bean.getUpdateTime())*1000, DateKit.DEFAULT_DATE_TIME_FORMAT)%></em>
											<div class="undone-tips">文章不完整</div>
											<!-- 当填写不完整的时候显示 -->
										</div>
									</div>
									<div class="cover-appmsg-item">
										<h4 class="appmsg-title"><a><%bean.getArticleList().get(0).getTitle();%></a></h4>
										<div class="appmsg-thumb-wrp"></div>
									</div>
									<div class="appmsg-item has-cover">
										<div class="appmsg-thumb-wrp"></div>
										<h4 class="appmsg-title">
											<a><%bean.getArticleList().get(0).getDigest(); %></a>
										</h4>
									</div>
									<%for(int i=1;i<bean.getArticleList().size(); ++i){ %>
									<div class="appmsg-item">
										<h4 class="appmsg-title">
											<span><%bean.getArticleList().get(i).getTitle();%></span>
										</h4>
									</div>
									<%} %>
									<div class="appmsg-opr">
										<ul>
											<li class="appmsg-opr-item w-2-1">
												<a title="编辑">
													<i class="edit-icon" alt="编辑"></i>
												</a>
											</li>
											<li class="appmsg-opr-item w-2-1 grid-item no-extra">
												<a class="no-extra" title="删除">
													<i class="del-icon"></i>
												</a>
											</li>
										</ul>
									</div>
								</div>
								<%} %>
							</div>
						</div>
					   <%}%>
					</div>
				<%}%>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>