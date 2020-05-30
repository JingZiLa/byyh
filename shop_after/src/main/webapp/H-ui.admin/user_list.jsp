<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
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
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户管理</title>
<style type="text/css">
a:hover {
	text-decoration: none;
}
</style>
<script type="text/javascript">
function to_page(page) {
	if (page) {
		$("#page").val(page);
	}
	$('#forms').submit();
}
$(function() {
	$('.user_addtime').datepick({
		dateFormat : 'yy-mm-dd'
	});
	$('.user_end_time').datepick({
		dateFormat : 'yy-mm-dd'
	});
});
</script>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		用户管理 <span class="c-gray en">&gt;</span> 用户列表 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<form action="${ pageContext.request.contextPath }/user_findAll.action"
			method="post" id="forms">
	<div class="pd-20">
	
		<div class="text-c">
			日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
				id="datemin" class="input-text Wdate user_addtime" style="width: 120px;" name="user_addtime" 
				value="<s:date name='user_addtime' format="yyyy-MM-dd"/>"
				/>
			- <input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})"
				id="datemax" class="input-text Wdate user_end_time" style="width: 120px;"
				value="<s:date name='user_end_time' format="yyyy-MM-dd"/>" name="user_end_time"
				/>
			<input type="text" class="input-text" style="width: 250px"
				placeholder="输入会员名称、电话、邮箱" id="" name="user_name" value="<s:property value="user_name"/>">
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="icon-search"></i> 搜用户
			</button>

		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="icon-trash"></i> 批量删除</a> <a
				href="${ pageContext.request.contextPath }/user_addUserUI.action"
				onclick="user_add('550','','添加用户','user-add.html')"
				class="btn btn-primary radius"><i class="icon-plus"></i> 添加用户</a></span> <span
				class="r">共有用户：<strong><s:property
							value="totalCount" /></strong> 人
			</span>
		</div>
		<table
			class="table table-border table-bordered table-hover table-bg">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="80">用户ID</th>
					<th width="100">用户名称</th>
					<th width="150">用户头像</th>
					<th width="40">用户性别</th>
					<th width="90">用户手机</th>
					<th width="150">用户邮箱</th>
					<th width="">用户地址</th>
					<th width="130">加入时间</th>
					<th width="70">用户状态</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="list">
					<tr class="text-c">
						<td><input type="checkbox" value="1" name=""></td>
						<td><s:property value="user_id" /></td>

						<td><s:property value="user_name" /></td>
						<td><img src="/imgUrl/<s:property value="user_headimge"/>" style="width:100px; height:80px; "></td>
						<td><s:if test="user_sex == 0">
			男        
        </s:if> <s:if test="user_sex == 1">
			女      
        </s:if></td>
						<td><s:property value="user_phone" /></td>
						<td><s:property value="user_email" /></td>
						<td class="text-l"><s:property value="user_state" /> <s:property
								value="user_province" /> <s:property value="user_city" /> <s:property
								value="user_address" /></td>
						<td><s:date name="user_addtime" format="yyyy-MM-dd" /></td>
						<td class="user-status"><s:if test="user_status == 0">
								<span class="label label-success">正常</span>
							</s:if> <s:if test="user_status == 1">
								<span class="label label-defaunt">已冻结</span>
							</s:if></td>
						<td class="td-manage"><a style="text-decoration: none"
										onClick="picture_stop(this,<s:property value="user_id" />)" href="javascript:;"
										title="修改"> <i class="Hui-iconfont">&#xe6de;</i></a> <a
										style="text-decoration: none" class="ml-5"
										onClick="picture_edit('图库编辑','picture-add.jsp','10001')"
										href="${ pageContext.request.contextPath }/user_editUI.action?user_id=<s:property value="user_id" />" title="修改"><i class="Hui-iconfont">&#xe6df;</i></a>
										<a style="text-decoration: none" class="ml-5"
										href="${ pageContext.request.contextPath }/user_deleteUser.action?user_id=<s:property value="user_id" />" title="删除"> <i
											class="Hui-iconfont">&#xe6e2;</i></a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l" style="margin-left: 55%;"> <span class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有用户：<strong><s:property
							value="totalCount" /></strong>人&nbsp;&nbsp;
			</span> <span>总页数 <s:property value="totalPage" />页
			</span> &nbsp;&nbsp;&nbsp;&nbsp;每页显示 <select name="pageSize"
				onchange="to_page()">
					<option value="5" <s:if test="pageSize == 5">selected</s:if>>5</option>
					<option value="10" <s:if test="pageSize == 10">selected</s:if>>10</option>
					<option value="15" <s:if test="pageSize == 15">selected</s:if>>15</option>
			</select> 条 &nbsp;&nbsp; <s:if test="currPage != 1">
					<A href="javascript:to_page(1)">首页</A>
					<A href="javascript:to_page(<s:property value="currPage-1"/>)">前一页</A>]
					</s:if> &nbsp;&nbsp; <s:iterator var="i" begin="1" end="totalPage">
					<s:if test="#i == currPage">
						<s:property value="#i" />
					</s:if>
					<s:else>
						<a href="javascript:to_page(<s:property value="#i"/>)"> <s:property
								value="#i" />
						</a>
					</s:else>
				</s:iterator> &nbsp;&nbsp; <s:if test="currPage != totalPage">
					<A href="javascript:to_page(<s:property value="currPage+1"/>)">后一页</A>] 
									<A href="javascript:to_page(<s:property value="totalPage"/>)">尾页</A>]
									</s:if> 到 <input type="text" size="3" id="page" name="currPage" /> 页
				<a class="btn btn-primary radius" href="#" onclick="to_page()">
					GO </a>
			</span>
		</div>
	</div>
	</form>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/H-ui.admin/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/H-ui.admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/H-ui.admin/lib/datatables/1.10.15/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${ pageContext.request.contextPath }/H-ui.admin/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
			
		$('.table-sort').dataTable({
			"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
			"bStateSave" : true,//状态保存
			"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false,
				"aTargets" : [ 0, 8 ]
			} // 制定列不参与排序
			]
		});

		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}

		/*图片-查看*/
		function picture_show(title, url, id) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}

		/*图片-审核*/
		function picture_shenhe(obj, id) {
			layer
					.confirm(
							'审核文章？',
							{
								btn : [ '通过', '不通过' ],
								shade : false
							},
							function() {
								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a class="c-primary" onClick="picture_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
								$(obj)
										.parents("tr")
										.find(".td-status")
										.html(
												'<span class="label label-success radius">已发布</span>');
								$(obj).remove();
								layer.msg('已发布', {
									icon : 6,
									time : 1000
								});
							},
							function() {
								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a class="c-primary" onClick="picture_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
								$(obj)
										.parents("tr")
										.find(".td-status")
										.html(
												'<span class="label label-danger radius">未通过</span>');
								$(obj).remove();
								layer.msg('未通过', {
									icon : 5,
									time : 1000
								});
							});
		}

		/*图片-下架*/
		function picture_stop(obj, id) {
			layer.confirm('确认要冻结该用户吗？',
							function(index) {
								var url = "${pageContext.request.contextPath }/user_editStatus.action?user_id="+id;
									$.ajax({
							            type: "post",
							            url: url,
							            data: null,
							            dataType: "json",
							            async: false,
							            success: function (data) {
							            	
							            }
							        });
									$(obj)
									.parents("tr")
									.find(".td-manage")
									.prepend(
											'<a style="text-decoration:none" onClick="picture_stop(this,id)" href="javascript:;" title="解除冻结状态"><i class="Hui-iconfont">&#xe6de;</i></a>');
							$(obj)
									.parents("tr")
									.find(".user-status")
									.html(
											'<span class="label label-success radius">正常</span>');
								$(obj).remove();
								layer.msg('已冻结该用户', {
									icon : 5,
									time : 1000
								});
									location.reload();
							});
		}

		/*图片-发布*/
		function picture_start(obj, id) {
			layer
					.confirm(
							'确认要解除该用户的冻结状态吗？',
							function(index) {
								var url = "${pageContext.request.contextPath }/user_editStatus.action?user_id="+id;
								$.ajax({
						            type: "post",
						            url: url,
						            data: null,
						            dataType: "json",
						            async: false,
						            success: function (data) {
						            }
						        });
								
								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a style="text-decoration:none" onClick="(this,<s:property value="user_id" />)" href="javascript:;" title="解除冻结状态"><i class="Hui-iconfont">&#xe6de;</i></a>');
								$(obj)
										.parents("tr")
										.find(".user-status")
										.html(
												'<span class="label label-success radius">正常</span>');
								$(obj).remove();
								layer.msg('已解除该用户冻结状态', {
									icon : 6,
									time : 1000
								});
								location.reload();
							});
		}

		/*图片-申请上线*/
		function picture_shenqing(obj, id) {
			$(obj).parents("tr").find(".td-status").html(
					'<span class="label label-default radius">待审核</span>');
			$(obj).parents("tr").find(".td-manage").html("");
			layer.msg('已提交申请，耐心等待审核!', {
				icon : 1,
				time : 2000
			});
		}

		/*图片-编辑*/
		function picture_edit(title, url, id) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}

		/*图片-删除*/
		function picture_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				$.ajax({
					type : 'POST',
					url : '',
					dataType : 'json',
					success : function(data) {
						$(obj).parents("tr").remove();
						layer.msg('已删除!', {
							icon : 1,
							time : 1000
						});
					},
					error : function(data) {
						console.log(data.msg);
					},
				});
			});
		}
	</script>
</body>
</html>
