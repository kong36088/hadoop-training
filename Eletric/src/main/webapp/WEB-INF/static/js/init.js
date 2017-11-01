$(document).ready(function(){
    var result=http.httpGet("/data/group");
    if(result){
        var data={
            groups:result
        }
        var tpl=$("#tpl-groups").html();
        var html=juicer(tpl,data);
        $("#groups").append(html);
    }
});