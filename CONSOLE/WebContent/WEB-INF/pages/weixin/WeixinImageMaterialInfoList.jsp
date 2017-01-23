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
				<div class="main-hd">
					<h2>素材管理</h2>
					<div class="title-tab">
						<ul class="tab-navs title-tab">
							<li class="tab-nav first"><a href="javascript:;" class="" >图文消息</a></li>
							<li class="tab-nav selected"><a href="javascript:;">图片</a></li>
							<li class="tab-nav"><a href="javascript:;">语音</a></li>
							<li class="tab-nav"><a href="javascript:;">视频</a></li>
						</ul>
					</div>
				</div>
				<form action="WeixinMaterialInfoList.do?mediaType=image" method="post" >
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
					<div class="img-pick-panel">
						<div class="inner-container-box side-r cell-layout">
							<div class="inner-main">
								<div class="bd">
									<div class="media-list">

										<div class="global-mod float-layout">
											<div class="global-info">
												<span class="group-name global-info-ele">未分组</span>
												<!-- 选择其他分组 -->
												<a href="javascript:;" class="global-info-ele ">重命名</a>
												<a href="javascript:;" class="global-info-ele ">删除分组</a>
											</div>
											<div class="global-extra">
												<div class="upload-box align-right r">
													<span class="upload-area">
														<a href="" class="btn btn-primary">本地上传</a>
														<input type="file" name="file" id="file"/>
													</span>
												</div>
												<div class="mini-tips weak-text iocn-after img-water-tips r">
													大小不超过5M，
													<span>已开启图片水印</span>
													<i class="icon-msg-mini ask"></i>
												</div>
											</div>
										</div>
										
										<div class="oper-group group">
											<div class="frm-controls oper-ele l">
												<label for="" class="frm-checkbox-label">
													<input type="checkbox" class="frm-checkbox">
													<i class="icon-checkbox"></i>
													<span class="lbl-content">全选</span>
												</label>
											</div>
											<a href="" class="btn btn-default btn-disabled oper-ele l">移动分组</a>
											<a href="" class="btn btn-default btn-disabled oper-ele l">删除</a>
										</div>
										
										<div class="group img-pick">
										<!-- 无图片 -->
										<%if(list.size() < 1){ %>
											<div class="empty-tips" style="display:none">该分组暂时没有图片素材 </div>
										<%}else{ %>
										<!-- 有图片 -->
											<ul class="group">
											 <%for(WeixinMaterialInfoBean bean:list){%> 
												<li class="img-item">
													<div class="img-item-bd">
														<img src="<%=bean.getUrl() %>" alt="" class="pic"><span class="check-content">
															<label for="" class="frm-checkbox-label">
																<input type="checkbox" class="frm-checkbox">
																<i class="icon-checkbox"></i>
																<span class="lbl-content"><%=bean.getMediaId() %></span>
															</label></span>
													</div>
													<div class="msg-card-ft">
														<ul class="grid-line msg-card-opr-list">
															<li class="grid-item w-3-1 msg-card-opr-item">
																<a href="">
																	<span class="msg-card-opr-item-inner">
																		<i class="icon18-common edit-gray">编辑</i>
																		<span class="vm-box"></span>
																	</span>
																</a>
															</li>
															<li class="grid-item w-3-1 msg-card-opr-item">
																<a href="">
																	<span class="msg-card-opr-item-inner">
																		<i class="icon18-common move-gray">移动分组</i>
																		<span class="vm-box"></span>
																	</span>
																</a>
															</li>
															<li class="grid-item w-3-1 msg-card-opr-item">
																<a href="">
																	<span class="msg-card-opr-item-inner">
																		<i class="icon18-common del-gray">删除</i>
																		<span class="vm-box"></span>
																	</span>
																</a>
															</li>
														</ul>
													</div>
												</li>
											<%} %>
											</ul>
										<%} %>
										</div>

										<div class="pagination-wrp"></div>
									</div>
								</div>
							</div>
					    </div>
					</div>
				</div>
				</form>
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
			</div>
		</div>
	</div>
</body>
</html>