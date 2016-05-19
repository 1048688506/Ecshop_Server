<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	/**
	* 搜索
	**/
	function searchOrders(){
		//url: "";
		var seller_name=$("#select1 option:selected")
		console.info(seller_name)
		$('#dg').datagrid({url:'../appuser/getOrderListByTerm.do',queryParams:{
			 seller_name:$('#key').val(),
			 status:$('#status').combobox('getValue'),
			 startTime:$("#start_time").datebox('getValue'),
			 endTime :$("#end_time").datebox('getValue'),
			 startPrice:$("#start_price").val(),
			 endPrice:$("#end_price").val() }
		});
	}
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="订单管理" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="../appuser/getOrderList.do" fit="true" toolbar="#tb" >
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="order_id" width="50" align="center">编号</th>
    		<th field="order_sn" width="100" align="center">订单号</th>
    		<th field="seller_name" width="100" align="center">店铺名称</th>
    		<th field="add_time" width="100" align="center" >下单时间</th>
    		<th field="buyer_name" width="100" align="center">买家姓名</th>
    		<th field="goods_amount" width="50" align="center" >商品总金额</th>
    		<th field="status" width="50" align="center" >订单状态</th>
    		<th field="order_amount" width="50" align="center" >订单折扣价</th>
    		<th field="payment_name" width="50" align="center" >支付名</th>
    		<th field="pay_time" width="100" align="center" >支付时间</th>
    		<th field="consignee" width="50" align="center" >收件人</th>
    		<th field="address" width="50" align="center" >送货地址</th>
    		<th field="phone_tel" width="50" align="center" >座机电话</th>
    		<th field="phone_mob" width="50" align="center" >手机电话</th>
    		<th field="shipping_name" width="50" align="center" >送货方式</th>
    		<th field="zipcode" width="50" align="center" >邮编</th>
    		<th field="region_name" width="50" align="center" >地区名</th>
    		<th field="goods_name" width="50" align="center" >商品名</th>
    		<th field="price" width="50" align="center" >商品单价</th>
    		<th field="quantity" width="50" align="center" >数量</th>
    		<th field="is_valid" width="50" align="center" >是否有效</th>
    	</tr>
    </thead>
</table>
	<div id="tb">
	<form action="../appuser/excel.do" method = "post">
	<div>
	 <button id = "btn"  name = "btn">导出Excel</button>
	</div>
	<div>
	 	<select id="select1" class="easyui-combobox"  name="dropdown" style="width:100px;">
    	 <option value="store_name">店铺名称</option>
   	<!-- 	 <option value="seller_name">卖家名称</option>
   	 	 <option value="buyer_name">买家名称</option>
   		 <option value="order_sn">订单号</option> -->
		</select>:
		<input type="text"   name = "seller_name" id="key"  size="10" onkeydown="if(event.keyCode==13) searchUser()"/>
		&nbsp;
		<input id="status" class="easyui-combobox" name="status" data-options="valueField:'value',textField:'text',data:[{'value':'11','text':'待付款'},
		{'value':'1','text':'已提交'},{'value':'20','text':'待发货'},{'value':'30','text':'已发货'},{'value':'40','text':'以完成'},{'value':'0','text':'已取消'}]" size="15" onkeydown="if(event.keyCode==13) searchOrders()" />  
		&nbsp;下单时间从：&nbsp;<input type="text" class="easyui-datebox"  name="startTime"  id="start_time"  size="15" onkeydown="if(event.keyCode==13) searchOrders()"/>
		&nbsp;至：&nbsp;<input type="text" class="easyui-datebox"  name="endTime"  id="end_time"  size="15" 	onkeydown="if(event.keyCode==13) searchOrders()"/>
		&nbsp;订单金额从：&nbsp;<input type="text" name="startPrice"  id="start_price"  size="10" onkeydown="if(event.keyCode==13) searchOrders()"/>
		&nbsp;至：&nbsp;<input type="text" name="endPrice"  id="end_price"  size="10" onkeydown="if(event.keyCode==13) searchOrders()"/>
		<a href="javascript:searchOrders()" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
	</form>
</div>
</body>
</html>