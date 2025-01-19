$(function() {
	const form = $("#myForm").Validform({
		tiptype: 2 // Initialize Validform
	});

	form.addRule([
		{
			ele: "#passWord",
			datatype: "*3-12",
			nullmsg: "Please enter a password",
			errormsg: "Password must be 3-12 characters long"
		},
		{
			ele: "#c_passWord",
			datatype: "*",
			recheck: "passWord",
			nullmsg: "Please confirm your password",
			errormsg: "Passwords do not match"
		},
		{
			ele: "#name",
			datatype: "*2-15",
			nullmsg: "Please enter your name",
			errormsg: "Name must be 2-15 characters long"
		},
		{
			ele: "#sex",
			datatype: "*",
			nullmsg: "Please select your gender",
			errormsg: "Please select your gender"
		},
		{
			ele: "#age",
			datatype: "n1-2",
			nullmsg: "Please enter your age",
			errormsg: "Age must be a 1-2 digit number"
		},
		{
			ele: "#tell",
			datatype: "/^13[0-9]{9}$|17[0-9]{9}$|14[0-9]{9}&|15[0-9]{9}$|18[0-9]{9}$/",
			nullmsg: "Please enter your phone number",
			errormsg: "Please enter a valid phone number"
		},
		{
			ele: "#address",
			datatype: "*1-100",
			nullmsg: "Please enter your address",
			errormsg: "Address must not exceed 100 characters"
		}
	]);
});