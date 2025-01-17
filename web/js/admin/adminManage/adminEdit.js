$(function(){
	var form=$("#myForm").Validform({
		tiptype:2,//validform初始化
	});
	form.addRule([
		{
			ele:"#passWord",
			datatype:"s4-8",
			nullmsg:"请输入password",
			errormsg:"password must be 4-8 characters long.，请重新输入！"
		},
		{
			ele:"#c_passWord",
			datatype:"*",
			recheck:"passWord",
			mullmsg:"please input confirm password！",
			errormsg:"两次输入的password不一致，请重新输入！"
		},
		{
			ele:"#name",
			datatype:"s2-8",
			mullmsg:"请输入Name！",
			errormsg:"name must be 2-8 characters long.，请重新输入！"
		}
	]);
});

