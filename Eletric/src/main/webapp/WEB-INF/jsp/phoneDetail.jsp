<!DOCTYPE html>
<html lang="ch">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>通讯录管理系统</title>

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
    <div id="page-wrapper">

        <div class="container-fluid">
            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12" id="content">
                    <h1 class="page-header">
                        详情
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="javascript:void(0);"> 通讯录管理系统</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> 联系人详情
                        </li>
                    </ol>

                </div>

            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<script id="tpl-detail" type="text/template">
    <table class="table table-hover table-bordered">
        {@if item}
        <tr>
            <td>头像</td>
            <td>
                <img src="${item.image}" height="200px" width="auto"/>
            </td>
        </tr>
        <tr>
            <td>姓名</td>
            <td>${item.name}</td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td>${item.number}</td>
        </tr>
        <tr>
            <td>即时通讯工具及号码</td>
            <td>${item.number2}</td>
        </tr>
        <tr>
            <td>电子邮箱</td>
            <td>${item.email}</td>
        </tr>
        <tr>
            <td>个人主页</td>
            <td>${item.homePage}</td>
        </tr>
        <tr>
            <td>生日</td>
            <td>${item.birthday}</td>
        </tr>
        <tr>
            <td>工作单位</td>
            <td>${item.workAddress}</td>
        </tr>
        <tr>
            <td>家庭地址</td>
            <td>${item.homeAddress}</td>
        </tr>
        <tr>
            <td>邮编</td>
            <td>${item.postCode}</td>
        </tr>
        <tr>
            <td>所属组</td>
            <td>${item.group}</td>
        </tr>
        <tr>
            <td>备注</td>
            <td>${item.mark}</td>
        </tr>

        </tbody>
        {@else}
        <td>暂时没有信息</td>
        {@/if}
    </table>
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
        var request=GetRequest();
        var id=request['id'];
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
            var tpl_phones = $("#tpl-detail").html();
            var result = http.httpGet("/data/phone/"+id);
            //获取电话信息
            if (result != 0) {
                $(".page-header").text(result[0].name);
                var data = {
                    item: result[0]
                };
                var html = juicer(tpl_phones, data);
                $("#content").append(html);
            }
            loading.hideLoading();
        }

        function GetRequest() {
            var url = location.search; //获取url中"?"符后的字串
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for(var i = 0; i < strs.length; i ++) {
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