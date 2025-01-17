$(function(){
	var form=$("#myForm").Validform({
		tiptype:2,//validform初始化
	});
	form.addRule([
		{
			ele:"#userName",
		    datatype:"/^[a-zA-Z]{1}[a-zA-Z0-9]{3,7}$/",
		    ajaxurl:"jsp/admin/AdminManageServlet?action=find",
		    nullmsg:"请输入username！",
		    errormsg:"Username must start with a letter and be 4-8 characters long.，请重新输入！"
		},
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



////ajax实时判断用户存在否
//var oldErr;
//var xmlHttp;
//var flag=true;//标记
//function ck_username(){
//		xmlHttp=getXmlHttp();
//
//		//username验证4-8位大小写字母和数字
//
//		if($("#userName").val()==null){
//			return;
//		}
//
//		var url="UserServlet?action=find&userName="+$("#userName").val();
//		xmlHttp.open("GET",url,true);
//		xmlHttp.onreadystatechange=function(){
//			if(xmlHttp.readyState==4 && xmlHttp.status==200){
//				var json=JSON.parse(xmlHttp.responseText);
//				var info=json['info'];
//				if(info==0){
//					oldErr=$(".ckerr").eq(0).html("*username已存在！请重新输入！").css("color","red");
//					flag=true;
//				}else{
//					if(oldErr!=null){
//						oldErr.html("√ 正确").css("color","green");
//						flag=false;
//					}
//				}
//			}
//		}
//		xmlHttp.send(null);
//	}
//
//
//
//function getXmlHttp(){
//	var xHttp;
//	if(window.XMLHttpRequest){
//		xHttp=new XMLHttpRequest();
//	}
//	if(window.ActiveXObject){
//		xHttp=new ActiveXObject("Microsoft.XMLHttp");
//	}
//	return xHttp;
//}
//
//
//
//
//function checkAdd(){
//	//拦截用户存在还Submit
//	if(flag){
//		$("#userName").focus();
//		return false;
//	}
//	if(oldErr!=null){
//		oldErr.html("√ 正确").css("color","green");
//	}
//
//	//username验证4-8位大小写字母和数字
//	var reg=/^[a-zA-Z]{1}[a-zA-Z0-9]{3,7}$/;
//	if(!reg.test($("#userName").val())){
//		$("#userName").focus();
//		oldErr=$(".ckerr").eq(0).html("*username以字母开头的4~8位字母数字组成,请重新输入!").css("color","red");
//		return false;
//	}
//
//	//password验证4-8位单字符
//	reg=/^(\w){4,8}$/;
//	if(!reg.test($("#passWord").val())){
//		$("#passWord").focus();
//		oldErr=$(".ckerr").eq(1).html("*password must be 4-8 characters long.，请重新输入！").css("color","red");
//		return false;
//	}
//	if($("#passWord_ck").val()!=$("#passWord").val()){
//		$("#passWord_ck").focus();
//		oldErr=$(".ckerr").eq(2).html("*两次password不一致，请重新输入！").css("color","red");
//		return false;
//	}
//	//Name验证2-8位
//	namelen=$("#name").val().length;
//	if(namelen<2 && namelen>8){
//		$("#name").focus();
//		oldErr=$(".ckerr").eq(3).html("*Name为2~8位，请重新输入！").css("color","red");
//		return false;
//	}
//
//	return true;
//}
//
