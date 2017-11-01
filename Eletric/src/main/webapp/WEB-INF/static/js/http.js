$(function () {
    http = {
        httpGet: function (url) {
            var result;
            $(".loading").css('display', 'block');
            $.ajax({
                type: "GET",
                url: url,
                async: false,
                error: function (xmlobject, error) {
                    console.log(error);
                },
                success: function (data, status) {
                    result = data;
                },
                complete: function () {
                    $(".loading").css('display', 'none');
                },
            });
            return result;
        },
        httpPost: function (url, data) {
            var result;
            $(".loading").css('display', 'block');
            $.ajax({
                type: "POST",
                url: url,
                data: data,
                async: false,
                error: function (xmlobject, error) {
                    console.log(error);
                },
                success: function (data, status) {
                    result = data;
                },
                complete: function () {
                    $(".loading").css('display', 'none');
                },
            });
            return result;
        }, httpPut: function (url, data) {
            var result;
            $(".loading").css('display', 'block');
            $.ajax({
                type: "PUT",
                url: url,
                data: data,
                async: false,
                error: function (xmlobject, error) {
                    console.log(error);
                },
                success: function (data, status) {
                    result = data;
                },
                complete: function () {
                    $(".loading").css('display', 'none');
                },
            });
            return result;
        }, httpDelete: function (url, data) {
            var result;
            $(".loading").css('display', 'block');
            $.ajax({
                type: "DELETE",
                url: url,
                data: data,
                async: false,
                error: function (xmlobject, error) {
                    console.log(error);
                },
                success: function (data, status) {
                    result = data;
                },
                complete: function () {
                    $(".loading").css('display', 'none');
                },
            });
            return result;
        }
    };

});