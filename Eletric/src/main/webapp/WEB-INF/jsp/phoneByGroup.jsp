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
        <div class="navbar-header">
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
                        查看联系人
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="javascript:void(0);"> 通讯录管理系统</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> 查看联系人
                        </li>
                    </ol>
                </div>

                <table class="table table-hover">
                    <caption>联系人列表</caption>
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>手机号码</th>
                        <th>即时通信工具及号码</th>
                        <th>电子邮箱</th>
                        <th>所属组</th>
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
                <label>组名</label>

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

<script id="tpl-phones" type="text/template">
    {@each phones as item,k}
    <tr>
        <td>${item.name}</td>
        <td>${item.number}</td>
        {@if item.number2}
        <td>${item.number2}</td>
        {@else}
        <td>无</td>
        {@/if}
        {@if item.email}
        <td>${item.email}</td>
        {@else}
        <td>无</td>
        {@/if}
        {@if item.group}
        <td><span class="label label-default">${item.group}</span></td>
        {@else}
        <td><span class="label label-default">无</span></td>
        {@/if}
        <td>
            <a href="/contact/phoneDetail?id=${item.id}">
                <button type="button" class="btn btn-success">
                    查看联系人
                </button>
            </a>
            <a href="/contact/phoneModify?id=${item.id}">
                <button type="button" class="btn btn-info" name="table-btn-change">
                    修改联系人
                </button>
            </a>
            <button type="button" class="btn btn-info" name="table-btn-add-group">
                <input type="hidden" value="${item.id}"/>
                &nbsp;修改分组&nbsp;
            </button>
            <button type="button" class="btn btn-danger" name="table-btn-delete">
                <input type="hidden" value="${item.id}"/>
                删除联系人
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
        var request = GetRequest();
        var group_id = request['id'];
        var loading = {
            showLoading: function () {
                $(".loading").attr("display", "block");
            },
            hideLoading: function () {
                $(".loading").attr("display", "none");
            }
        }

        init();
        initChange();
        function init() {
            loading.showLoading();
            var tpl_phones = $("#tpl-phones").html();
            var result = http.httpGet("/data/groupPhone?id=" + group_id);
            //获取电话信息
            if (result != 0) {
                var data = {
                    phones: result
                };
                var html = juicer(tpl_phones, data);
                $("#content").append(html);
            } else {
                $("#content").append("<td>暂时没有联系人</td>");
            }
            //获取组名
            var result = http.httpGet("/data/group/" + group_id);
            if (result != 0) {
                $(".page-header").text(result[0].name);
            }
            loading.hideLoading();
            bindDelete();
        }

        function bindDelete() {
            $("button[name='table-btn-delete']").on("click", function () {
                if (confirm("确认删除联系人？")) {
                    var id = $(this).find("input").val();
                    var result = http.httpDelete("/data/phone?id=" + id, null);
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

        function initChange() {
            var tpl = '{@each group as item,k}' +
                    '<p><input type="checkbox" name="group" value="${item.id}"> ${item.name}</p>' +
                    '{@/each}';
            var result = http.httpGet("/data/group");
            var data = {
                group: result
            };
            var html = juicer(tpl, data);
            $(".modal-body").append(html);
        }

        $("button[name='table-btn-add-group']").on("click", function () {
            $("#changeModal").modal('show');
            $("#changeModal").find("input[name='id']").val($(this).find("input").val());
        });
        $("#change-btn").on("click", function () {
            var phone_id = $("#changeModal").find("input[name='id']").val();
            var result = http.httpDelete("/data/groupsRecord?id=" + phone_id, null);
            if (result != 1) {
                alert("修改失败");
                return false;
            }
            $('input[name="group"]:checked').each(function () {
                var group_id = $(this).val();
                var r = http.httpPost("/data/groupsRecord", {phoneId: phone_id, groupId: group_id});
            });
            location.reload();
        });


        function GetRequest() {
            var url = location.search; //获取url中"?"符后的字串
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for (var i = 0; i < strs.length; i++) {
                    theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
                }
            }
            return theRequest;
        }

        // var 参数1,参数2,参数3,参数N;
        // 参数N = Request['参数N'];

    });
</script>
</body>
</html>