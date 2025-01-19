$(function(){
	var form=$("#myForm").Validform({
		tiptype:2,//validform初始化
	});
	form.addRule([
		{
			ele:"#passWord",
			datatype:"s4-8",
			nullmsg:"Please enter password",
			errormsg:"password must be 4-8 characters long.，Please reenter！"
		},
		{
			ele:"#c_passWord",
			datatype:"*",
			recheck:"passWord",
			mullmsg:"please input confirm password！",
			errormsg:"Two different password, Please enter again！"
		},
		{
			ele:"#name",
			datatype:"s2-8",
			mullmsg:"Please enter name！",
			errormsg:"name must be 2-8 characters long.！"
		}
	]);
});

