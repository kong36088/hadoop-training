jQuery(document).ready(function () {
        $('#submit').click(function () {
            var username = $('.username').val();
            var password = $('.password').val();
            var repass = $('.repass').val();
            if (!username||username=='') {
                $("form").find('.error').fadeOut('fast', function () {
                    $(this).css('top', '27px');
                });
                $("form").find('.error').fadeIn('fast', function () {
                    $(this).parent().find('.username').focus();
                });
                return false;
            }
            if (!password||password=='') {
                $("form").find('.error').fadeOut('fast', function () {
                    $(this).css('top', '96px');
                });
                $("form").find('.error').fadeIn('fast', function () {
                    $(this).parent().find('.password').focus();
                });
                return false;
            }
            if (!repass||repass=='') {
                $("form").find('.error').fadeOut('fast', function () {
                    $(this).css('top', '165px');
                });
                $("form").find('.error').fadeIn('fast', function () {
                    $(this).parent().find('.repass').focus();
                });
                return false;
            }
            if(password!=repass){
                alert("两次密码不一致");
                return false;
            }
            if(!checkUsername()){
                return false;
            }
            var result=http.httpPost("/login/register",{username:username,password:password});
            if(result==1){
                alert("注册成功");
                window.location.href="/login/login";
            }else{
                alert("注册失败");
            }
        });
        $(".username").blur(function () {
            checkUsername();
        });
        function checkUsername() {
            var username = $(".username").val();
            var result = http.httpPost("/login/checkUsername", {username: username});
            if (result != 1) {
                alert("用户名已存在");
                return false;
            }else{
                return true;
            }
        }
        $('.page-container form .username, .page-container form .password,.page-container form .repass').keyup(function () {
            $(this).parent().find('.error').fadeOut('fast');
        });

    }
)
;
