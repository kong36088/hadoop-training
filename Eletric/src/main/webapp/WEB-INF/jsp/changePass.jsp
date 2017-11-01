<!DOCTYPE html>
<html lang="ch">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>java 课程设计</title>

    <!-- Bootstrap Core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/static/css/sb-admin.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/static/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">20
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/contact/phone">通讯录管理系统</a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> 联系人管理<b
                        class="caret"></b></a>
                <ul class="dropdown-menu alert-dropdown">
                    <li>
                        <a href="/contact/addPhone.html">新建联系人</a>
                    </li>
                    <li>
                        <a href="/contact/phone.html">删改联系人</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="/contact/groupsManage.html"><i class="fa fa-bell"></i> 分组管理</a>
            </li>
            <li>
                <a href="/contact/data"><i class="fa fa-user"></i> 导入与导出 </a>
            </li>
            <li>
                <a href="/contact/logout.html"><i class="fa fa-user"></i>注销</a>
            </li>
            <li>
                <a href="/contact/changePass.html"><i class="fa fa-user"></i>修改密码</a>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav" id="groups">
                <li class="">
                    <a href="/contact/phone.html">所有联系人</a>
                </li>
                <script id="tpl-groups" type="text/template">
                    {@each groups as item,k}
                    <li>
                        <a href="/contact/phoneByGroup?id=${item.id}">${item.name}</a>
                    </li>
                    {@/each}
                </script>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>
    <div id="page-wrapper">

        <div class="container-fluid">
            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        用户密码修改
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="javascript:void(0);"> 通讯录管理系统</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> 用户密码修改
                        </li>
                    </ol>
                </div>
                <div class="col-sm-7">
                    <form class="form-horizontal" id="submit-form">
                        <p>
                            <label for="opass" class=""><span style="color:red"> * </span> 旧密码</label>
                            <input type="password" class="form-control required" id="opass" name="opass"
                                   placeholder="旧密码"
                                   style="width:20%;height:auto;min-width:400px;">
                        </p>
                        <p>
                            <label for="newpass" class=""><span style="color:red"> * </span> 新密码</label>
                            <input type="password" class="form-control required" id="newpass" name="newpass"
                                   placeholder="新密码"
                                   style="width:20%;height:auto;min-width:400px;">
                        </p>
                        <p>
                            <label for="repass" class=""><span style="color:red"> * </span> 确认密码</label>
                            <input type="password" class="form-control required" id="repass" name="repass"
                                   placeholder="确认密码"
                                   style="width:20%;height:auto;min-width:400px;">
                        </p>
                        <div class="">
                            <button class="btn btn-success" id="form-submit-btn" type="button">提交</button>
                        </div>
                    </form>
                </div>

            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->


<!-- jQuery -->
<script src="/static/js/jquery-1.11.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/http.js"></script>
<script src="/static/js/juicer-min.js"></script>
<script src="/static/js/init.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $("#form-submit-btn").on("click", function () {
            var opass = $("input[name='opass']").val();
            var newpass = $("input[name='newpass']").val();
            var repass = $("input[name='repass']").val();
            if (opass && newpass && repass) {
                if (newpass != repass) {
                    alert("两次输入密码不一致");
                    $("#input[name='newpass']").focus();
                    return false;
                }
                var data = {
                    opass: opass,
                    newpass: newpass
                };
                var result = http.httpPost("/data/changePass", data);
                if (result==1) {
                    alert("密码修改成功");
                    window.location.href = "/contact/phone";
                } else {
                    alert("密码错误！");
                }
            } else {
                alert("请完整输入");
                return false;
            }
        });
    });
</script>
</body>
</html>