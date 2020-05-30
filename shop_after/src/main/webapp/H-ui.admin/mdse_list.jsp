<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
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
<script type="text/javascript">
	$(function() {
		$('.mdse_addtime').datepick({
			dateFormat : 'yy-mm-dd'
		});
		$('.mdse_end_time').datepick({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
<style type="text/css">
	a:hover {
		text-decoration: none;
	}
</style>
<title>商品列表</title>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span>
	商品列表 <a class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="page-container">
		<form
			action="${ pageContext.request.contextPath }/mdse_findAll.action"
			method="post" id="forms">
			<div class="text-c">
				商品名称： <input type="text" name="mdse_name" id="" placeholder=" 商品名称"
					style="width: 250px" class="input-text"
					value="<s:property value="mdse_name"/>"> 商品分类:
				<s:select list="list" name="sort.sort_id" headerKey=""
					headerValue="-请选择-" listKey="sort_id" listValue="sort_name"
					theme="simple" cssStyle=" height:30px" />
				添加时间： <input type="text"
					onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
					id="logmax" class="input-text Wdate mdse_end_time"
					style="width: 120px;" name="mdse_addtime"
					value="<s:date name='mdse_addtime' format="yyyy-MM-dd"/>" /> 到 <input
					type="text"
					onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
					id="logmax" class="input-text Wdate mdse_end_time"
					style="width: 120px;" name="mdse_end_time"
					value="<s:date name='mdse_end_time' format="yyyy-MM-dd"/>">
				<button name="" id="" class="btn btn-success" type="submit">
					<i class="Hui-iconfont">&#xe665;</i> 搜商品
				</button>
			</div>

			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"><a href="javascript:;" onclick="datadel()"
					class="btn btn-danger radius"> <i class="Hui-iconfont">&#xe6e2;</i>
						批量删除
				</a> <a class="btn btn-primary radius" href="${ pageContext.request.contextPath }/mdse_saveUI.action">
						<i class="Hui-iconfont">&#xe600;</i> 添加商品
				</a></span> <span class="r">共有商品：<strong><s:property
							value="totalCount" /></strong>种
				</span>
			</div>
			<div class="mt-20">
				<table
					class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="40"><input name="" type="checkbox" value=""></th>
							<th width="80">商品ID</th>
							<th width="100">商品图片</th>
							<th width="100">商品名称</th>
							<th width="100">商品详情</th>
							<th width="100">商品单价</th>
							<th width="100">商品数量</th>
							<th width="100">商品分类</th>
							<th width="150">商品状态</th>
							<th width="150">更新时间</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
						<tr class="text-c"  >
							<s:iterator value="list">
								<tr class="text-c">
									<td><input name="checkbox" type="checkbox" value="<s:property value="mdse_id"/>"></td>
									<td><s:property value="mdse_id" /></td>
									<td width="150">
									<img width="80" height="80" class="picture-thumb"
										src="/imgUrl/<s:property value="mdse_imge"/>"></td>
									<td><s:property value="mdse_name" /></td>
									<td><s:property value="mdse_mome" /></td>
									<td><s:property value="mdse_price" />/元</td>
									<td><s:property value="mdse_quantity" />/件</td>
									<td><s:property value="sort.sort_name" /></td>
									<td class="td-status"><span
										class="label label-success radius">上架中</span></td>
										
									<td><s:date name="mdse_addtime" format="yyyy-MM-dd" /></td>
									<td class="td-manage"><a style="text-decoration: none"
										onClick="picture_stop(this,<s:property value="mdse_id" />)" href="javascript:;"
										title="下架"> <i class="Hui-iconfont">&#xe6de;</i></a> <a
										style="text-decoration: none" class="ml-5"
										onClick="picture_edit('图库编辑','picture-add.jsp','10001')"
										href="${ pageContext.request.contextPath }/mdse_editUI.action?mdse_id=<s:property value="mdse_id" />" title="修改"><i class="Hui-iconfont">&#xe6df;</i></a>
										<a style="text-decoration: none" class="ml-5"
										href="${ pageContext.request.contextPath }/mdse_deleteMdse.action?mdse_id=<s:property value="mdse_id" />" title="删除"> <i
											class="Hui-iconfont">&#xe6e2;</i></a></td>
								</tr>
							</s:iterator>
					</tbody>


				</table>
				<div class="cl pd-5 bg-1 bk-gray mt-20">
					<span class="l" style="margin-left: 45%;"> <span class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有商品：<strong><s:property
									value="totalCount" /></strong>条&nbsp;&nbsp;
					</span> <span>总页数 <s:property value="totalPage" />页
					</span> &nbsp;&nbsp;&nbsp;&nbsp;每页显示 <select name="pageSize"
						onchange="to_page()">
							<option value="5" <s:if test="pageSize == 5">selected</s:if>>5</option>
							<option value="10" <s:if test="pageSize == 10">selected</s:if>>10</option>
							<option value="15" <s:if test="pageSize == 15">selected</s:if>>15</option>
					</select> 条 &nbsp;&nbsp;
					 <s:if test="currPage != 1">
									<A href="javascript:to_page(1)">首页</A>
									<A href="javascript:to_page(<s:property value="currPage-1"/>)">前一页</A>]
					</s:if> 
					&nbsp;&nbsp;  <s:iterator var="i" begin="1" end="totalPage">
									<s:if test="#i == currPage">
											<s:property value="#i" />
									</s:if>
									<s:else>
											<a href="javascript:to_page(<s:property value="#i"/>)">
											<s:property value="#i" />
											</a>
									</s:else>
									</s:iterator>
					&nbsp;&nbsp;
									<s:if test="currPage != totalPage">
									<A
											href="javascript:to_page(<s:property value="currPage+1"/>)">后一页</A>] 
									<A
											href="javascript:to_page(<s:property value="totalPage"/>)">尾页</A>]
									</s:if>
									到 <input type="text" size="3" id="page" name="currPage" />
									页 
									<a class="btn btn-primary radius" href="#" onclick="to_page()">
							GO </a>
					</span>
				</div>
		</form>
	</div>
	</div>

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
	function to_page(page) {
		if (page) {
			$("#page").val(page);
		}
		$('#forms').submit();
	}
	
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
			layer.confirm('确认要下架吗？',
							function(index) {
								$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="picture_start(this,id)" href="javascript:;" title="上架中"><i class="Hui-iconfont">&#xe603;</i></a>');
								$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
								$(obj).remove();
								layer.msg('已下架!', {
									icon : 5,
									time : 1000
								});
							});
		}

		/*图片-发布*/
		function picture_start(obj, id) {
			layer
					.confirm(
							'确认要上架吗？',
							function(index) {
								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a style="text-decoration:none" onClick="picture_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
								$(obj)
										.parents("tr")
										.find(".td-status")
										.html(
												'<span class="label label-success radius">上架中</span>');
								$(obj).remove();
								layer.msg('已上架!', {
									icon : 6,
									time : 1000
								});
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