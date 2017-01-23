<%@page import="com.lpmas.weixin.api.bean.response.material.ArticleResponseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.lpmas.system.bean.StoreInfoBean"%>
<%@page import="com.lpmas.system.bean.SysApplicationInfoBean"%>
<%@page import="com.lpmas.weixin.console.config.WeixinResource"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@page import="com.lpmas.weixin.bean.*"%>
<%@page import="com.lpmas.weixin.console.config.*"%>
<%@page import="com.lpmas.weixin.config.*"%>
<%@page import="com.lpmas.admin.config.OperationConfig"%>
<%@page import="com.lpmas.admin.business.AdminUserHelper"%>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@page import="com.lpmas.admin.bean.*"%>
<%
	WeixinMaterialInfoBean bean=(WeixinMaterialInfoBean)request.getAttribute("WeixinMaterialInfoBean");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	ArticleResponseBean articleResponseBean = bean.getArticleList().get(0);
	int accountId = ParamKit.getIntParameter(request, "accountId",0);
%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图文素材管理</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery.js"></script>
</head>
<body>
			<p>
		      <em>标题：</em>
			　<input type="text" name="title" id="title" size="50" value="<%=articleResponseBean.getTitle()%>"/> 
			</p>
			<p>
		      <em>缩略图：</em>
			　<input type="text" name="thumbMediaId" id="thumbMediaId" size="50" value="<%=articleResponseBean.getThumbMediaId() %>"/>
			</p>
			<p>
		      <em>作者：</em>
			　<input type="text" name="author" id="author" size="50" value="<%=articleResponseBean.getAuthor() %>"/> 
			</p>
			<p>
		      <em>内容：</em>
			　<input type="text" name="content" id="content" size="50" value="<%=articleResponseBean.getContent() %>"/>
			</p>
			<p>
		      <em>原文链接：</em>
			　<input type="text" name="contentSourceUrl" id="contentSourceUrl" size="50" value="<%=articleResponseBean.getContentSourceUrl() %>"/>
			</p>
		<div>
            <input type="button" name="submit" id="submit"  value="提交" />
	  	    <input type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">
	  </div>

		
</body>
<script type="text/javascript">
$(document).ready(function(){
	$('#submit').click(function(e){
		var title = $('#title').val();
		var thumbMediaId = $('#thumbMediaId').val();
		var author = $('#author').val();
		var content = $('#content').val();
		var contentSourceUrl = $('#contentSourceUrl').val();
		
		//表单提交
		 $.ajax({
	            url: 'WeixinNewsMaterialInfoManage.do',
	            data: {
	            	accountId: '<%=accountId%>',
	            	title_0: title,
	            	thumbMediaId_0: thumbMediaId,
	            	author_0: author,
	            	content_0: content,
	            	contentSourceUrl_0: contentSourceUrl,
	            },
	            type: 'POST',
	            success: function (json) {
	                if (json.code == '1') {
	                    window.location = 'WeixinNewsMaterialInfoList.do?accountId=<%=accountId%>';
	                }
	                else if (json.code == '0') {
	                    alert(json.message);
	                }
	            }
	        })
	});
});
</script>
</html>