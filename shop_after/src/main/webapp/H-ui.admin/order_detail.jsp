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
	
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/H-ui.admin/static/h-ui.admin/css/style.css" />
<style type="text/css">
a:hover {
	text-decoration: none;
}
</style>
<script type="text/javascript">
	$(function() {
		$('.order_addtime').datepick({
			dateFormat : 'yy-mm-dd'
		});
		$('.order_end_time').datepick({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
<SCRIPT language=javascript>
	function to_page(page) {
		if (page) {
			$("#page").val(page);
		}
		document.customerForm.submit();

	}
</SCRIPT>
<title>商品列表</title>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span>
	订单列表
	<s:if test="findStatus == '1' ">
		待发货订单
	</s:if> 
	<s:if test="findStatus == '2' ">
		待收货订单
	</s:if> 
	<s:if test="findStatus == '3' ">
		待收款订单
	</s:if> 
	<s:if test="findStatus == '4' ">
		已完成
	</s:if> 
	<s:if test="findStatus == '5' ">
		已取消
	</s:if> 
	<span class="c-gray en">&gt;</span>
	订单详情 <a
		class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a> </nav>
	<div class="page-container">
			<table
				class="table table-border table-bordered table-bg table-hover " style="margin-top: 30px;">
				<thead>
					<tr class="text-c">
						<th width="20%" style= "height: 20px; font-size: 16px; background-color: #19A97B;">下单时间</th>
						<th width="20%" style= "height: 20px; font-size: 16px; background-color: #19A97B;">付款</th>
						<th width="20%" style= "height: 20px; font-size: 16px; background-color: #19A97B;">发货</th>
						<th width="20%" style= "height: 20px; font-size: 16px; background-color: #19A97B;">收货</th>
						<th width="20%" style= "height: 20px; font-size: 16px;background-color: #19A97B;">订单结束</th>
					</tr>
				</thead>
				
				<thead >
					<tr class="text-c">
						<td><s:date name="order_addtime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>付款于:<s:date name="pay_time" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>发货于:<s:date name="issue_cargo_time" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>收货于:<s:date name="put_cargo_time" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>订单结束于:<s:date name="put_cargo_time" format="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</thead>
			</table>
		<div class="mt-20">
			
			<table
				class="table table-border table-bordered table-bg table-hover " style="margin-top: 30px;">
				<thead>
					<tr class="text-c" style="width: 100%">
						<th width="100%" style= "height: 30px; font-size: 16px; text-align: left;background-color: gainsboro;">基本信息:</th>
					</tr>
				</thead>
			</table>
			<table class="table table-border table-bordered table-hover " style="margin-top: 30px;">
				<thead>
					<tr class="text-c" style="width: 100%">
						<th width="100">订单号</th>
						<th width="100">应付款(￥/人民币)</th>
						<s:if test="pay_status == 1">
							<th width="100">实付款(￥/人民币)</th>
						</s:if>
						<th width="100">买家</th>
						<th width="100">交易状态</th>
					</tr>
				</thead>

				<thead >
					<tr class="text-c">
						<td><s:property value="order_detail_number" /></td>
						<td>￥: <s:property value="due_money" /><br/>(含运费：￥0.00)</td>
						<s:if test="pay_status == 1">
							<td>￥: <s:property value="actual_money" /><br/>(含运费：￥0.00)</td>
						</s:if>
						<td>
						<s:property value="user.user_name" />
								<br/>
								(用户ID:<s:property value="user.user_id"/>)
						
						</td>
						<td class="td-status">付款状态: <s:if test="pay_status == 0 ">
								<span class="label label-defaunt radius" style="margin: 5px 0;">
									待付款 </span>&nbsp;&nbsp;
							</s:if> <s:if test="pay_status == 1 ">

								<span class="label label-success radius" style="margin: 5px 0;">
									已付款 </span>&nbsp;&nbsp;&nbsp;&nbsp;
							</s:if> 发货状态:
							<s:if test="issue_cargo_status == 0 ">
								<span class="label label-defaunt radius" style="margin: 5px 0;">
									待发货 </span>&nbsp;&nbsp;&nbsp;&nbsp;

							</s:if> <s:if test="issue_cargo_status == 1 ">
								<span class="label label-success radius" style="margin: 5px 0;">
									已发货 </span>&nbsp;&nbsp;&nbsp;&nbsp;
							</s:if> 收货状态:
							<s:if test="put_cargo_status == 0 ">
								<span class="label label-defaunt radius" style="margin: 5px 0;">
									待收货 </span>
							</s:if> <s:if test="put_cargo_status == 1 ">
								<span class="label label-success radius" style="margin: 5px 0;">
									已收货 </span>
							</s:if> 
						</td>
					</tr>
				</thead>
			</table>
			
			
			<table
				class="table table-border table-bordered table-bg table-hover " style="margin-top: 30px;">
				<thead>
					<tr class="text-c" style="width: 100%">
						<th width="100%" style= "height: 30px; font-size: 16px; text-align: left;background-color: gainsboro;">商品信息:</th>
					</tr>
				</thead>
			</table>
			<table class="table table-border table-bordered table-hover " style="margin-top: 30px;">
				<thead>
					<tr class="text-c" style="width: 100%">
						<th width="100">商品名称</th>
						<th width="100">商品图片</th>
						<th width="100">商家</th>
						<th width="100">商品单价(人民币)</th>
						<th width="100">商品数量</th>
						<th width="100">商品总金额(人民币)</th>
					</tr>
				</thead>

				<thead>
					<s:iterator value="order">
					<tr class="text-c">
						<td><s:property value="mdse.mdse_name" /></td>
						<td><%-- <a href="${ pageContext.request.contextPath }/orderDetail_mdseDetail.action?order_detail_id=<s:property value="mdse.mdse_id" />" style="margin-left: -10%;">
								<span style="margin: 5px 0; border: 1px solid green; color: green;padding: 5px; ">商品详情
								</span>
							</a> --%>
							<img width="80" height="80" class="picture-thumb"
										src="/imgUrl/<s:property value="mdse.mdse_imge"/>">
						</td>
						<td><s:property value="mdse.user.user_name" /></td>
						<td><s:property value="mdse.mdse_price" /></td>
						<td><s:property value="mdse.mdse_quantity" /></td>
						<td><s:property value="mdse.mdse_quantity * mdse.mdse_price" /><br/>(含运费：￥0.00)</td>
					</tr>
					</s:iterator>
				</thead>
			</table>
			
			<table
				class="table table-border table-bordered table-bg table-hover " style="margin-top: 30px;">
				<thead>
					<tr class="text-c" style="width: 100% ">
						<th width="100%" style= "height: 30px; font-size: 16px; text-align: left;background-color: gainsboro;">收货信息:</th>
					</tr>
				</thead>
			</table>
			<table class="table table-border table-bordered table-hover " style="margin-top: 30px;">
				<thead>
					<tr class="text-c" style="width: 100%">
						<th width="100">收货人</th>
						<th width="100">收货电话：</th>
						<th width="100">收货地址</th>
					</tr>
				</thead>

				<thead>
				<s:iterator value="order" status="st">
					<s:if test="#st.index == 0">
						<tr class="text-c">
							<td><s:property value="user.user_name" /></td>
							<td><s:property value="user.user_phone" /></td>
							<td>国家:<s:property value="user.user_state" />省份:<s:property value="user.user_province" />&nbsp;&nbsp;&nbsp;&nbsp;城市:<s:property value="user.user_city" /><br/>
							详细地址:
							<s:property value="user.user_address" /></td>
							
						</tr>
					</s:if>
					</s:iterator>
				</thead>
			</table>
			<s:if test="pay_status == 1">
				<table
				class="table table-border table-bordered table-bg table-hover " style="margin-top: 30px;">
				<thead>
					<tr class="text-c" style="width: 100%">
						<th width="100%" style= "height: 30px; font-size: 16px; text-align: left; background-color: gainsboro;">付款信息:</th>
					</tr>
				</thead>
			</table>
			<table class="table table-border table-bordered table-hover " style="margin-top: 30px;">
				<thead>
					<tr class="text-c" style="width: 100%">
						<th width="100">应付金额</th>
						<th width="100">支付方式</th>
						<th width="100">支付流水号</th>
						<th width="100">付款状态</th>
						<th width="100">付款时间</th>
					</tr>
				</thead>

				<thead>
					<tr class="text-c">
						<td><s:property value="due_money" /></td>
						<td>
							<s:if test="order_payment == 1">
								<i class="Hui-iconfont">&#xe694;</i> 微信支付
							</s:if>
							<s:if test="order_payment == 2">
								<i class="Hui-iconfont">&#xe730;</i> 支付宝
							</s:if>
							<s:if test="order_payment == 3">
								<i class="Hui-iconfont">&#xe670;</i> 网银
							</s:if>
						</td>
						<td><s:property value="" />--</td>
						<td><s:if test="pay_status == 0 ">
									 	<span class="label label-defaunt radius" style="margin: 5px 0;">
											待付款 
										</span>
									</s:if> 
									<s:if test="pay_status == 1 ">
									
									<span class="label label-success radius" style="margin: 5px 0;">
											已付款
										</span>
									</s:if></td>
							<td><s:date name="order_addtime" format="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</thead>
			</table>
			</s:if>
			<s:if test="issue_cargo_status == 1">
					<table
						class="table table-border table-bordered table-bg table-hover " style="margin-top: 30px;">
						<thead>
							<tr class="text-c" style="width: 100%;">
								<th width="100%" style= "height: 30px; font-size: 16px; text-align: left;  background-color: gainsboro;">发货信息:</th>
							</tr>
						</thead>
					</table>
					<table class="table table-border table-bordered table-hover " style="margin-top: 30px;">
						<thead>
							<tr class="text-c" style="width: 100%">
								<th width="100">物流公司</th>
								<th width="100">物流单号</th>
								<th width="100">发货状态</th>
								<th width="100">发货时间</th>
							</tr>
						</thead>
		
						<thead>
							<tr class="text-c">
								<td>
									<s:if test="order_logisticscorp == 1">
										顺丰快递
									</s:if>
									<s:if test="order_logisticscorp == 2">
										圆通快递
									</s:if>
									<s:if test="order_logisticscorp == 3">
										中通快递
									</s:if>
								</td>
								<td><s:property value="order_logistics_odd" /></td>
								<td>
									<s:if test="issue_cargo_status == 0 ">
												<span class="label label-defaunt radius" style="margin: 5px 0;"> 待发货
												</span> 
											
											</s:if>
											<s:if test="issue_cargo_status == 1 ">
												<span class="label label-success radius" style="margin: 5px 0;"> 已发货
												</span> 
											</s:if>
								</td>
								<td><s:date name="issue_cargo_time" format="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</thead>
					</table>
				</s:if>
			
			<nav class="breadcrumb"  style="margin-top: 30px;"> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<!-- <a href="javascript:;" 
					class="btn btn-success radius"> <i class="Hui-iconfont">&#xe6e2;</i>
						修改订单
				</a> -->
		<a class=" btn btn-danger radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="${ pageContext.request.contextPath }/orderDetail_deleteOrder.action?order_detail_id=<s:property value="order_detail_id" />" title="删除订单">删除订单</a></nav>
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

<script type="text/javascript" src="${ pageContext.request.contextPath }/H-ui.adminlib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
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
		function status_stop(obj, id) {
			layer.confirm('确认修改该货物状态吗？',
							function(index) {
								$.ajax({
									type : 'POST',
									url : '${pageContext.request.contextPath }/mdse_findAll.action',
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
		/*图片-下架*/status_stop
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