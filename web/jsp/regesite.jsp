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
    <script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <script>
        $(document).ready(function() {
            // Initialize checkbox styling
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%'
            });

            // Initialize datepickers
            const datepickerConfig = {
                autoclose: true,
                language: 'en'
            };

            $('#datepicker-a3, #datepicker-a6').datepicker(datepickerConfig);

            // Form validation
            const validationRules = {
                username: {
                    required: 'Username cannot be empty',
                    async validate(value) {
                        if (!value) return false;

                        const response = await $.get('/UserServlet', {
                            action: 'usernamecheck',
                            user_name: value,
                            timestamp: Date.now()
                        });

                        return response === 1 ? 'Username already exists' : 'Username is available';
                    }
                },
                nickname: {
                    required: 'Nickname cannot be empty'
                },
                user_Password: {
                    required: 'Password cannot be empty'
                },
                user_rePassword: {
                    required: 'Confirm password cannot be empty',
                    validate(value, form) {
                        const password = form.querySelector('[name="user_Password"]').value;
                        return value === password ? true : 'Passwords do not match';
                    }
                }
            };

            // Clear error on focus
            $('form input').on('focus', function() {
                $(this).next('span')
                    .removeClass('error')
                    .empty();
            });

            // Validate on blur
            $('form input').on('blur', async function() {
                const field = $(this);
                const fieldName = field.attr('name');
                const errorSpan = field.next('span');

                if (!validationRules[fieldName]) return;

                const rule = validationRules[fieldName];

                // Check required
                if (!field.val() && rule.required) {
                    errorSpan
                        .html(rule.required)
                        .addClass('error');
                    return;
                }

                // Run custom validation
                if (rule.validate) {
                    const result = await rule.validate(field.val(), field[0].form);

                    if (result !== true) {
                        errorSpan
                            .html(result)
                            .addClass('error');
                    } else {
                        errorSpan
                            .removeClass('error')
                            .empty();
                    }
                }
            });
        });
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
