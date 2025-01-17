<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Register</title>
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <script>
        $(function () {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%'
            });
        });
        $(document).ready(function () {
            $('#datepicker-a3').datepicker({
                autoclose: true,
                language: 'zh-CN'
            });
        });


        $(document).ready(function () {
            $('#datepicker-a6').datepicker({
                autoclose: true,
                language: 'zh-CN'
            });
        });

        function ForcusItem(obj) {
            $(obj).next('span').html('').removeClass('error');
        }

        function check(obj) {
            var msg_box = $(obj).next('span');
            switch ($(obj).attr('name')) {
                case "username":
                    if (obj.value == "") {
                        msg_box.html('*username can not be null');
                        msg_box.addClass('error');
                    } else {
                        var url = "/UserServlet?action=usernamecheck&user_name=" + encodeURI($(obj).val()) + "&" + new Date().getTime();
                        $.get(url, function (data) {
                            console.log(data)
                            if (data == 1) {
                                msg_box.html('*username已存在');
                                msg_box.addClass('error');
                            } else {
                                msg_box.html('*username可使用');
                                msg_box.html().removeClass('error');
                            }
                        });
                    }
                    break;
                case "nickname": {
                    if (obj.value == "") {
                        msg_box.html('*Nickname can not be null');
                        msg_box.addClass('error');
                    }
                    break;
                }
                case  "user_Password": {
                    if (obj.value == "") {
                        msg_box.html('*password can not be null');
                        msg_box.addClass('error');
                    }
                    break;
                }
                case  "user_rePassword": {
                    if (obj.value == "") {
                        msg_box.html('*Confirm password can not be null');
                        msg_box.addClass('error');
                    } else if ($(obj).val() != $('input[name="user_Password"]').val()) {
                        msg_box.html('两次输入的password不一致');
                        msg_box.addClass('error');

                    }
                    break;
                }
            }
        }
    </script>

</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-box-body">
        <p class="login-box-msg">New Register</p>

        <form action="${pageContext.request.contextPath}/UserServlet?action=regeiste" method="post">
            <div class="form-group has-feedback">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                <input type="text" class="form-control" placeholder="username" name="username" onfocus="ForcusItem(this)"
                       onblur="check(this)">
                <span></span>
            </div>
            <div class="form-group has-feedback">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                <input type="text" class="form-control" placeholder="Nickname" name="nickname" onfocus="ForcusItem(this)"
                       onblur="check(this)">
                <span></span>
            </div>
            <div class="form-group has-feedback">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                <input type="email" class="form-control" placeholder="Email" name="user_Email">
                <span></span>
            </div>
            <div class="form-group has-feedback">
                <span>Gender:</span>
                <input type="radio" value="Man" name="user_Sex" checked>Man
                <input type="radio" value="Woman" name="user_Sex">Woman
            </div>

            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="Phone" name="user_Mobile">
                <span class="glyphicon glyphicon-phone form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                <input type="password" class="form-control" placeholder="password" name="user_Password"
                       onfocus="ForcusItem(this)" onblur="check(this)">
                <span></span>
            </div>
            <div class="form-group has-feedback">
                <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                <input type="password" class="form-control" onblur="check(this)" placeholder="Confirm password"
                       name="user_rePassword" onfocus="ForcusItem(this)" onblur="check(this)">
                <span></span>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <button type="submit" class="btn btn-primary btn-block btn-flat" onclick="checkbox()">Register</button>
                </div>
            </div>
        </form>
        <a href="${pageContext.request.contextPath}/jsp/login.jsp" class="text-center">I Have a account, Login now</a>
    </div>
</div>
</body>

</html>
