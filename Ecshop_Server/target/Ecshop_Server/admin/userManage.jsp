<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	/**
	* 搜索
	**/
	function searchUser(){
		$('#dg').datagrid('load' ,{
			 "user.username":$('#s_userName').val() 
			
		} );
		console.info($('#s_userName').val()) ;
	}
	
	function openUserAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加用户信息");
		
		url="user_saveUser.action";
	}
	/**
	* 保存新用户
	**/
	function saveUser(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result = eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","保存失败");
					return;
				}
			}
		});
	}
	/**
	* 对从数据库中取出的datetime数据进行展现
	**/
	 function formatterDateType(val,row){
		 if(val!=null){	
			var year=parseInt(val.year)+1900;  
		    var month=(parseInt(val.month)+1);  
		    month=month>9?month:('0'+month);  
		    var date=parseInt(val.date);  
		    date=date>9?date:('0'+date);  
		    var hours=parseInt(val.hours);  
		    hours=hours>9?hours:('0'+hours);  
		    var minutes=parseInt(val.minutes);  
		    minutes=minutes>9?minutes:('0'+minutes);  
		    var seconds=parseInt(val.seconds);  
		    seconds=seconds>9?seconds:('0'+seconds);  
		    var time=year+'-'+month+'-'+date+' '+hours+':'+minutes+':'+seconds;  
		    return time;
		 }
		 return ;
	} 
		/**
		*	回显数据模块代码
		*
		**/
	  function resetValue(){
		$("#username").val("");
		$("#nickname").val("");
		$("#password").val("");
		
		
		$("#sex").combobox("setValue","");
		$("#birthday").datebox("setValue","");
		$("#dentityCode").val("");
		
		$("#mobile").val("");
		$("#address").val("");
	}  	
	
	
	
	/**
	* 开启关闭dialog
	**/
	function closeUserDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	/**
	* 对用户信息进行修改
	**/
	function openUserModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑用户信息");
		$("#nickname").val(row.nickname);
		$("#username").val(row.username);
		$("#password").val(row.password);
		$("#email").val(row.email);
		$("#phone").val(row.phone);
		
		/*
		复选框和日期框暂不加入
		$("#sex").combobox("setValue",row.sex);
		$("#birthday").datebox("setValue",row.birthday);
		*/
		url="user_saveUser.action?user.id="+row.id;
	}
	/*
	*
	* 删除用户
	*/
	function deleteUser(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){ 
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("user_deleteUsers.action",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","数据已成功删除！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
	
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="用户管理" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="user_list.action" fit="true" toolbar="#tb" >
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="id" width="50" align="center">编号</th>
    		<th field="username" width="100" align="center">用户名</th>
    		<th field="nickname" width="100" align="center">昵称</th>
    		<th field="password" width="100" align="center">密码</th>
    		<th field="email" width="100" align="center">邮箱</th>
    		<th field="phone" width="50" align="center" >电话</th>
    		<th field="registerDate" width="100" align="center" formatter="formatterDateType">注册日期</th> 
    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
		&nbsp;用户名：&nbsp;<input type="text" name="user.username"  id="s_userName"  size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
		<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 300px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons" >
  <form id="fm" method="post">
  	<table cellspacing="8px;">
  		<tr>
  			<td>昵称：</td>
  			<td><input type="text" id="nickname" name="user.nickname" class="easyui-validatebox" required="true"/></td>
  		</tr>
  		<tr>
  			<td>用户名：</td>
  			<td><input type="text" id="username" name="user.username" class="easyui-validatebox" required="true"/></td>
  		</tr>
  		<tr>
  			<td>密码：</td>
  			<td><input type="text" id="password" name="user.password" class="easyui-validatebox" required="true"/></td>
  		</tr>
  		<tr>
  			<td>邮箱：</td>
  			<td><input type="text" id="email" name="user.email" class="easyui-validatebox" required="true"/></td>
  		</tr>
  		<tr>
  			<td>联系电话：</td>
  			<td><input type="text" id="phone" name="user.phone" class="easyui-validatebox" required="true"/></td>
  		</tr>
  	</table>
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
	<a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>

</body>
</html>