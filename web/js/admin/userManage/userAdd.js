//Register表单验证
$(function(){
	var form=$("#myForm").Validform({
		tiptype:2,//validform初始化
	});

	form.addRule([
		{
			ele:"#userName",
		    datatype:"*2-15",
		    ajaxurl:"jsp/admin/UserManageServlet?action=find",
		    nullmsg:"*请输入username！",
		    errormsg:"*username为2-15个字符，请重新输入！"
		},
		{
			ele:"#passWord",
			datatype:"*3-12",
			nullmsg:"*请输入password！",
			errormsg:"*password为3-12位字符，请重新输入"
		},
		{
			ele:"#c_passWord",
			datatype:"*",
			recheck:"passWord",
			mullmsg:"*please input confirm password！",
			errormsg:"*两次输入的password不一致，请重新输入！"
		},
		{
			ele:"#name",
		    datatype:"*2-15",
		    nullmsg:"请输入Name！",
		    errormsg:"Name为2-15个字符,请重新输入！"
		},
		{
			ele:"#sex",
		    datatype:"*",
		    nullmsg:"Please select Gender！",
		    errormsg:"Please select Gender！"
		},
		{
			ele:"#age",
		    datatype:"n1-2",
		    nullmsg:"请输入年龄",
		    errormsg:"年龄为1-2位数字，请重新输入！"
		},
		{
			ele:"#tell",
		    datatype:"/^13[0-9]{9}$|17[0-9]{9}$|14[0-9]{9}&|15[0-9]{9}$|18[0-9]{9}$/",
		    nullmsg:"请输入Telephone号码",
		    errormsg:"Telephone号码输入不正确，请重新输入！"
		},
		{
			ele:"#address",
		    datatype:"*1-100",
		    nullmsg:"请输入Address！",
		    errormsg:"请输入Address长度过长（100）！"
		}
	]);

});
