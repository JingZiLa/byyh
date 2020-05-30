<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link href="<%=request.getContextPath() %>/H-ui.admin/static/h-ui/css/H-ui.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=request.getContextPath() %>/H-ui.admin/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet"
	type="text/css" />
<link href="<%=request.getContextPath() %>/H-ui.admin/static/h-ui.admin/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="<%=request.getContextPath() %>/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet"
	type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>萤火电商后台管理系统</title>
<meta name="keywords"
	content="萤火电商后台管理系统">
<meta name="description"
	content="萤火电商后台管理系统">
</head>
<body>
	<input type="hidden" id="TenantId" name="TenantId" value="" />
	<div class="header"></div>
	<div class="loginWraper">
		<div id="loginform" class="loginBox">
			<form class="form form-horizontal" action="${pageContext.request.contextPath}/user_login.action" method="post">
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
					<div class="formControls col-xs-8">
						<input id="" name="user_code" type="text" placeholder="账户"
							class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
					<div class="formControls col-xs-8">
						<input id="" name="user_password" type="password" placeholder="密码"
							class="input-text size-L">
					</div>
				</div>
				<s:actionerror/>
				
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<button type="submit" class="btn btn-success radius size-L" >&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;</button>
						<a href="H-ui.admin/register.jsp" button class="btn btn-success radius size-L">&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;</a>
					</div>
				</div>
			</form>
			
		</div>
	</div>
	<div class="footer">广州白云</div>
	<script type="text/javascript" src="<%=request.getContextPath() %>/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
	<!--此乃百度统计代码，请自行删除-->
	<!-- <script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script> -->