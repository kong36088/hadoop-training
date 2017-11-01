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
                <li class="active">
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
                        新增联系人
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="javascript:void(0);"> 通讯录管理系统</a>
                        </li>
                        <li class="">
                            <i class="fa fa-file"></i> 新增联系人
                        </li>
                    </ol>
                </div>
                <div class="col-sm-7">
                    <form class="form-horizontal" id="submit-form">
                        <p>
                            <button type="button" class="btn btn-info" id="add-image-btn">添加头像</button>
                        </p>
                        <p>
                            <img id="form-image" src="/uploads/head/head.jpg" style="height:300px;width:auto;"/>
                            <input type="hidden" id="image" name="image">
                        </p>
                        <p>
                            <label for="name" class=""><span style="color:red"> * </span> 姓名</label>
                            <input type="text" class="form-control required" id="name" name="name" placeholder="姓名"
                                   style="width:20%;height:auto;min-width:200px;">
                        </p>
                        <p>
                            <label for="number"><span style="color:red"> * </span> 移动电话</label>
                            <input type="text" class="form-control required" id="number" name="number"
                                   placeholder="移动电话" style="width:30%;height:auto;min-width:400px;">
                        </p>
                        <p>
                            <label for="number2" class="">即时通信工具及号码</label>
                            <input type="text" class="form-control" id="number2" name="number2" placeholder="即时通信工具及号码"
                                   style="width:30%;height:auto;min-width:400px;">
                        </p>
                        <p class="">
                            <label for="email" class="">电子邮箱</label>
                            <input type="text" class="form-control" id="email" name="email" placeholder="电子邮箱"
                                   style="width:30%;height:auto;min-width:300px;">
                        </p>
                        <p class="">
                            <label for="homePage" class=" ">个人主页</label>
                            <input type="text" class="form-control" id="homePage" name="homePage" placeholder="个人主页"
                                   style="width:30%;height:auto;min-width:500px;">
                        </p>
                        <p class="">
                            <label for="birthday" class=" ">生日</label>
                            <input type="text" class="form-control" id="birthday" name="birthday" placeholder="生日"
                                   style="width:30%;height:auto;min-width:200px;">
                        </p>
                        <p class="">
                            <label for="workAddress" class=" ">工作单位</label>
                            <input type="text" class="form-control" id="workAddress" name="workAddress"
                                   placeholder="工作单位" style="width:30%;height:auto;min-width:500px;">
                        </p>
                        <p class="">
                            <label for="homeAddress" class="">家庭住址</label>
                            <input type="text" class="form-control" id="homeAddress" name="homeAddress"
                                   placeholder="家庭住址" style="width:30%;height:auto;min-width:500px;">
                        </p>
                        <p class="">
                            <label for="postCode" class=" ">邮编</label>
                            <input type="text" class="form-control" id="postCode" name="postCode" placeholder="邮编"
                                   style="width:30%;height:auto;width:200px;">
                        </p>
                        <p class="">
                            <label for="mark" class=" ">备注</label>
                            <textarea class="form-control" id="mark" name="mark"
                                      style="min-height: 100px;width: 600px;"></textarea>
                        </p>
                        <div class="">
                            <button class="btn btn-success" id="form-submit" type="button">修改</button>
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

<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog"
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
                        <img id="weixin_show" style="height:300px;margin-top:10px;margin-bottom:8px;"
                             src="/uploads/head/head.jpg" data-holder-rendered="true">
                        <div class="progress progress-striped active" role="progressbar" aria-valuemin="10"
                             aria-valuemax="100" aria-valuenow="0">
                            <div id="weixin_progress" class="progress-bar progress-bar-success" style="width:0%;"></div>
                        </div>
                        <div class="caption" align="center">
                            <span id="weixin_upload" class="btn btn-primary fileinput-button">
                                <span>上传</span>
                                <input type="file" id="weixin_image" name="weixin_image" multiple>
                            </span>
                            <a id="weixin_cancel" href="javascript:void(0)" class="btn btn-warning" role="button"
                               style="display:none">删除</a>
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
    $(document).ready(function () {
        var request = GetRequest();
        init();
        function init() {
            var id = request["id"];
            var result = http.httpGet("/data/phone/" + id, null);
            var inputs = $("#submit-form").find("input");
            inputs.each(function () {
                var input_name = $(this).attr("name");
                $(this).val(result[0][input_name]);
            });
            $("#mark").val(result[0].mark);
            if (result[0].image) {
                $("#form-image").attr("src", result[0].image);
            }
        }

        $("#form-submit").on("click", function () {
            var form_dom = $("#submit-form");
            var inputs = form_dom.find("input");
            var data = {};
            var mark = form_dom.find("textarea").val();
            if (mark) {
                data.mark = mark;
            }
            data.id=request["id"];
            inputs.each(function () {
                var val = $(this).val();
                if ($(this).hasClass("required") && !val) {
                    $(this).focus();
                    alert($(this).attr("placeholder") + "不能为空");
                    return false;
                }
                if (val) {
                    var name = $(this).attr("name");
                    data[name] = val;
                }
            });
            result = http.httpPut("/data/phone", data);
            if (result) {
                alert("修改成功");
                window.location.href = "/contact/phone";
            } else {
                alert("修改失败");
            }
        });
        $("#add-image-btn").on("click", function () {
            $("#uploadModal").modal('show');
        });
        $("#uploadModal").find("button[name='submit']").on("click", function () {
            var src = $("#weixin_show").attr("src");
            $("#form-image").attr("src", src);
            $("#image").val(src);
            $("#uploadModal").modal("hide");
        })
        $("#weixin_cancel").on("click", function () {
            $("#weixin_image").attr("src", "/uploads/head/head.jpg");
            $("#weixin_progress").css('width', '0%');
            $("#weixin_upload").css({display: ""});
            $("#weixin_cancel").css({display: "none"});
        });
        $("#weixin_image").fileupload({
            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
            maxFileSize: 999000,
            url: '/data/upload',
            sequentialUploads: true,
            previewCrop: true
        }).on('fileuploadprogress', function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $("#weixin_progress").css('width', progress + '%');
            $("#weixin_progress").html(progress + '%');
        }).on('fileuploaddone', function (e, data) {
            d = data.result;
            console.log(e);
            console.log(data);
            if (d.status == 1) {
                $("#weixin_show").attr("src", d.url);
                $("#weixin_upload").css({display: "none"});
                $("#weixin_cancel").css({display: ""});
            } else {
                alert(d.msg);
            }
        }).on('fileuploadfail', function (e, data) {
            console.log(e);
            console.log(data);
            alert(data.msg);
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

    });
</script>
</body>
</html>