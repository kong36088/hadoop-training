<!DOCTYPE html>
<html lang="ch">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>通讯录管理系统</title>
    <style type="text/css">
        .loading {
            display: block;
            width: 160px;
            height: 56px;
            position: absolute;
            top: 40%;
            left: 50%;
            line-height: 56px;
            color: #fff;
            padding-left: 60px;
            font-size: 15px;
            background: #000 url(/static/img/progress.gif) no-repeat 10px 50%;
            opacity: 0.7;
            z-index: -10;
            -moz-border-radius: 20px;
            -webkit-border-radius: 20px;
            border-radius: 20px;
            filter: progid:DXImageTransform.Microsoft.Alpha(opacity=70);
        }
    </style>
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
            <li  class="active">
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
                <li>
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
    <div class="loading">loading...</div>
    <div id="page-wrapper">

        <div class="container-fluid">
            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        分组管理
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="javascript:void(0);"> 通讯录管理系统</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> 分组管理
                        </li>
                    </ol>
                </div>

                <table class="table table-hover">
                    <caption>分组列表</caption>
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="content">
                    </tbody>
                </table>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<div class="modal fade" id="changeModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    修改基础信息选项
                </h4>
            </div>
            <div class="modal-body">
                <label for="name">选项名称</label>
                <input class="form-control" type="text" id="name" name="name"/>
                <input type="hidden" name="id"/>
            </div>
            <div class="modal-footer">
                <button type="button" id="change-btn" class="btn btn-primary">
                    修改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script id="tpl-group" type="text/template">
    {@each groups as item,k}
    <tr>
        <td>${item.name}</td>
        <td>
            <button type="button" class="btn btn-info" name="table-btn-change">
                <input type="hidden" value="${item.id}"/>
                修改分组
            </button>
            <button type="button" class="btn btn-danger" name="table-btn-delete">
                <input type="hidden" value="${item.id}"/>
                删除分组
            </button>
        </td>
    </tr>
    {@/each}
</script>

<!-- jQuery -->
<script src="/static/js/jquery-1.11.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/http.js"></script>
<script src="/static/js/juicer-min.js"></script>
<script src="/static/js/init.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        var loading = {
            showLoading: function () {
                $(".loading").attr("display", "block");
            },
            hideLoading: function () {
                $(".loading").attr("display", "none");
            }
        }

        init();
        function init() {
            loading.showLoading();
            var tpl_phones = $("#tpl-group").html();
            var result = http.httpGet("/data/group");
            if (result != 0) {
                var data = {
                    groups: result
                };
                var html = juicer(tpl_phones, data);
                $("#content").append(html);
            } else {
                $("#content").append("<td>暂时没有</td>");
            }
            loading.hideLoading();
            bindInit();
        }

        function bindInit() {
            $("button[name='table-btn-delete']").on("click", function () {
                if (confirm("确认删除分组？")) {
                    var id = $(this).find("input").val();
                    var result = http.httpDelete("/data/group?id=" + id, null);
                    if (result == 1) {
                        alert("删除成功");
                        location.reload();
                    } else {
                        alert("删除失败")
                    }
                } else {
                    return false;
                }
            });
        }

        $("button[name='table-btn-change']").on("click", function () {
            loading.showLoading();
            var id = $(this).find("input").val();
            var result = http.httpGet("/data/group/" + id, null);
            console.log(result);
            if (result) {
                var modal = $("#changeModal").modal('show');
                modal.find("input[name='id']").val(result[0].id);
                modal.find("input[name='name']").val(result[0].name);
            }
            loading.hideLoading();
        });
        $("#change-btn").on("click", function () {
            var modal = $("#changeModal");
            var id = modal.find("input[name='id']").val();
            var name = modal.find("input[name='name']").val();
            var result = http.httpPut("/data/group", {id: id, name: name});
            if (result == 1) {
                location.reload();
            } else {
                alert("修改失败");
            }
        });

    });
</script>
</body>
</html>