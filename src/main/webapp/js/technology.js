
function init(){
    $.post("/get/technologys",
        function (data,status){
            if(status=="success"){
                if(data["code"]==1){
                    var msg=JSON.parse(data["msg"]);
                    var num=msg["num"];
                    for(i=0;i<num;i++){
                        var tit=JSON.parse(msg["t"+i]);
                      show_title(tit);
                    }
                }
            }
            else{
                alert("获取数据失败:检查网络");
            }
        });
}
function  show_title(title){

    var divBox="<div class=\"titleBox\" id='titleBox'"+title['ID']+">\n" +
        "<figure>\n" +
        "<a href=\"/get/title?ID="+title['ID']+"\" title="+title['name']+" targer=\"_blank\">\n" +
        "<img src=\"images/logo.jpg\"  style=\"width: 167.5px; height: 137px; display: block;\" alt="+title['title']+">\n" +
        "</a>\n" +
        "</figure>\n" +
        "<ul>\n" +
        "<h3>\n" +
        "<a href=\"/get/title?ID="+title['ID']+"\" title="+title['title']+" target=\"_blank\">"+title['title']+"</a>\n" +
        "</h3>\n" +
        "<p>"+title['neirong']+"</p>\n" +
        "<p style='margin: 10px 0' class=\"author_info\">\n" +
        "<span class=\"info_author\">"+title['author']+"</span>\n" +
        "<span class=\"info_time\">"+title["time"]+"</span>\n" +
        "<span class=\"label_bottom f_r\" style=\"padding-left: 0;\">\n" +
        "<a href=\"javascript:void(0)\" onclick=\"return false;\" class=\"yz_zan\">"+title['zanNum']+"</a>\n" +
        "</span>\n" +
        "<span class=\"viewnum f_r\">"+title['viewNum']+"</span>\n" +
        "<span class=\"pingl f_r\" style=\"margin-right:0;\">\n" +
        "<a href=\"javascript:void(0)\" ><span id=\"liuyanId1\" class=\"cy_count\">"+title['pinglunNum']+"</span></a>\n" +
        "</span>\n" +
        "</p>\n" +
        "</ul>\n" +
        "</div>";
    $("article").append(divBox);
}
$(document).ready(function(){
  init();

});
