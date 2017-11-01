<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>通讯录管理系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="/static/css/reset.css">
        <link rel="stylesheet" href="/static/css/supersized.css">
        <link rel="stylesheet" href="/static/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>
    <body>
        <div class="page-container">
            <h1>登录</h1>
            <h1  style="font-size:20px;color:red;margin-top:15px;" th:text="${errMessage}"></h1>
            <form action="/login/login" method="post">
                <input type="text" name="username" class="username" placeholder="用户名">
                <input type="password" name="password" class="password" placeholder="密码">
                <button type="submit">提交</button>
                <a href="/login/register"><button type="button">注册</button></a>
                <div class="error"><span>+</span></div>
            </form>
        </div>

        <!-- Javascript -->
        <script src="/static/js/jquery-1.11.3.min.js"></script>
        <script src="/static/js/supersized.3.2.7.min.js"></script>
        <script src="/static/js/supersized-init.js"></script>
        <script src="/static/js/scripts.js"></script>
    </body>

</html>


