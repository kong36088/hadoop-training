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
    <h1>注册</h1>
    <h1  style="font-size:20px;color:red;margin-top:15px;" th:text="${errMessage}"></h1>
    <form action="/login/register" method="post">
        <input type="text" name="username" class="username" placeholder="用户名">
        <input type="password" name="password" class="password" placeholder="密码">
        <input type="password" name="repass" class="repass" placeholder="重复密码">
        <button id="submit" type="button">提交</button>
        <div class="error"><span>+</span></div>
    </form>
</div>

<!-- Javascript -->
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/supersized.3.2.7.min.js"></script>
<script src="/static/js/supersized-init.js"></script>
<script src="/static/js/http.js"></script>
<script src="/static/js/register.js"></script>
</body>

</html>


