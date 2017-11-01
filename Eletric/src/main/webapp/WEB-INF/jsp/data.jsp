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

    <link rel="stylesheet" href="/static/css/jquery.fileupload.css">
    <link rel="stylesheet" href="/static/css/jquery.fileupload-ui.css">

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
            <li class="active">
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
                <li class=>
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
                        通讯录导入导出
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="javascript:void(0);"> 通讯录管理系统</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-file"></i> 通讯录导入导出
                        </li>
                    </ol>
                </div>
                <div class="col-sm-7">
                    <p>
                    <h1>
                        CSV
                    </h1>
                    <a href="javascript:void(0)" target="_blank">
                        <button type="button" class="btn btn-info" id="csv-input">CSV格式导入</button>
                    </a>
                    <a href="/data/downloadCsv" target="_blank">
                        <button type="button" class="btn btn-success" id="csv-output">CSV格式导出</button>
                    </a>
                    </p>
                    <p>
                    <h1>
                        VCard
                    </h1>
                    <button type="button" class="btn btn-info" id="vcard-input">vCard格式导入</button>
                    <a href="javascript:void(0);" target="_blank" id="vcard_out">
                        <button type="button" class="btn btn-success" id="vcard-output">vCard格式导出</button>
                    </a>
                    </p>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->


    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<div class="modal fade" id="uploadModalCsv" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    上传头像
                </h4>
            </div>
            <div class="modal-body">
                <div class="row fileupload-buttonbar" style="padding-left:15px;">
                    <div class="thumbnail">
                        <div class="progress progress-striped active" role="progressbar" aria-valuemin="10"
                             aria-valuemax="100" aria-valuenow="0">
                            <div class="progress-bar progress-bar-success weixin_progress" style="width:0%;"></div>
                        </div>
                        <div class="caption" align="center">
                            <span id="weixin_upload_csv" class="btn btn-primary fileinput-button">
                                <span>上传</span>
                                <input type="file" id="weixin_image_csv" name="weixin_image" multiple>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button name="submit" class="btn btn-info">确认</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- jQuery -->
<script src="/static/js/jquery-1.11.3.min.js"></script>
<!-- file upload -->
<script src="/static/js/jquery.ui.widget.js"></script>
<script src="/static/js/jquery.fileupload.js"></script>
<script src="/static/js/jquery.iframe-transport.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/http.js"></script>
<script src="/static/js/juicer-min.js"></script>
<script src="/static/js/init.js"></script>
<script type="application/javascript">
    $(function () {
        init();
        function init() {
            id = http.httpGet("/data/getUserId");
            var href = "http://localhost/vcard/index.php?user_id=" + id;
            $("#vcard_out").attr("href", href);
        }
        $("#csv-output").on("click", function () {
            var result = http.httpGet("/data/downloadCsv");
            if(result.status==1){
                $(this).parent().attr("href", result.url);
            }else{
                alert(result.msg);
                return false;
            }
        });

        $("#csv-input").on("click", function () {
            $("#uploadModalCsv").modal('show');
        });
        $("#vcard-input").on("click", function () {
            $("#uploadModal").modal('show');
        });
        $("#weixin_image_csv").fileupload({
            url: "/data/uploadCsv",
            maxFileSize: 999000,
            sequentialUploads: true
        }).on('fileuploadprogress', function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $(".weixin_progress").css('width', progress + '%');
            $(".weixin_progress").html(progress + '%');
        }).on('fileuploaddone', function (e, data) {
            d = data.result;
            if (d.status == 1) {
                $("#weixin_upload_csv").css({display: "none"});
                alert(d.msg);
                location.reload();
            } else {
                alert(d.msg);
            }
        }).on('fileuploadfail', function (e, data) {
            alert(data.msg);
        });

        //确定键
        $("button[name='submit']").on("click",function () {
            $("#uploadModalCsv").modal('hide');
            $("#uploadModalVCard").modal('hide');
        })
    })
</script>
</body>
</html>