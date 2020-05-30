<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/css/H-ui.css"/>
<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/css/H-ui.admin.css"/>
<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/font/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui.admin/skin/default/skin.css"
	id="skin" />
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/H-ui.admin/lib/layui/layui.css" />
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->

<style type="text/css">

			#province select {
				margin-left: 10px;
				width: 100px
			}
</style>
<title>添加用户</title>
</head>
<body>
<div class="pd-20">
  <div class="Huiform">
  <nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		用户管理 <span class="c-gray en">&gt;</span> 修改用户<a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
    <form action="${ pageContext.request.contextPath }/user_editUser.action" method="post" style="margin-left: 25%; margin-top: 3%;" enctype="multipart/form-data">
   		<s:hidden name="user_id" value="user_id"/>
   		<s:hidden name="user_status" value="user_status"/>
      <table class="table table-bg">
        <tbody>
        <tr>
            <th width="100" class="text-r"><span class="c-red">*</span> 用户账号：</th>
            <td>
            
            <input type="text" style="width:200px" class="input-text" value="" placeholder="<s:property value="user_code"/>" id="user-name" name="user_code" datatype="*2-16" nullmsg="用户账号不能为空"></td>
          </tr>
          <tr>
            <th width="100" class="text-r"><span class="c-red">*</span> 用户名：</th>
            <td><input type="text" style="width:200px" class="input-text" value="" placeholder="<s:property value="user_name"/>" id="user-name" name="user_name" datatype="*2-16" nullmsg="请输入用户名"></td>
          </tr>
          <tr>
            <th width="100" class="text-r"><span class="c-red">*</span> 用户密码：</th>
            <td><input type="text" style="width:200px" class="input-text" value="" placeholder="<s:property value="user_password"/>" id="user-name" name="user_password" datatype="*2-16" nullmsg="用户密码不能为空"></td>
          </tr>
          <tr>
            <th class="text-r"><span class="c-red">*</span> 性别：</th>
            <td>
            <s:if test="user_sex == 1">
            	<label>
                <input type="radio" name="user_sex" value="1" id="six_0" >
            	    男</label>
            	<label>
                <input name="user_sex" type="radio" id="six_1" value="0" checked>
              	  女</label>
            </s:if>
            <s:if test="user_sex == 0">
            	<label>
                <input type="radio" name="user_sex" value="1" id="six_0" checked>
            	    男</label>
            	    <label>
                <input type="radio" name="user_sex" value="1" id="six_0" >
            	    女</label>
            </s:if>
              </td>
          </tr>
          <tr>
            <th class="text-r"><span class="c-red">*</span> 手机：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="<s:property value="user_phone"/>" id="user-tel" name="user_phone"></td>
          </tr>
          <tr>
            <th class="text-r">邮箱：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="<s:property value="user_email"/>" id="user-email" name="user_email"></td>
          </tr>
          <tr>
            <th class="text-r">用户头像：</th>
            <td>
           		 <img alt="" src="/imgUrl/<s:property value="user_headimge"/>" style="width:100px;height: 100px;">
            	<input type="file" class="" name="upload" >
            </td>
          </tr>
          <tr>
            <th class="text-r">地址：</th>
            <td> <div id="province"> </div><br/>
            
            <input type="text" style="width:300px" class="input-text" value="<s:property value="user_address"/>" placeholder="<s:property value="user_address"/>" id="user-address" name="user_address"></td>
          </tr>
          <tr>
            <th class="text-r">简介：</th>
            <td><textarea class="input-text" name="user_info" id="user-info" style="height:100px;width:300px;">
            	<s:property value="user_info"/>
            </textarea></td>
          </tr>
          <tr>
            <th></th>
            <td><button class="btn btn-success radius" type="submit"><i class="icon-ok"></i> 确定修改</button></td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/js/Validform_v5.3.2_min.js"></script> 
<script type="text/javascript" src="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/js/H-ui.admin.js"></script> 
<script type="text/javascript"
		src="${ pageContext.request.contextPath }/H-ui.admin/lib/layui/layui.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/js/provincesData.js"></script>
	
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/js/jquery.provincesCity.js"></script>
<script type="text/javascript">
/*调用插件*/
$(function(){
	$("#province").ProvinceCity();
});

$(".Huiform").Validform(); 
</script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>