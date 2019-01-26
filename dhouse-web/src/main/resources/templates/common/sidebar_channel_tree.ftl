<link rel=stylesheet href=${web.baseUrl}/css/accordion.css>
<script src=${web.baseUrl}/js/accordion.js></script>
<script>
    $(document).ready(function(){
        if(topChannelId != 0){
            $.ajax({url:"${web.baseUrl}/ajax/channel/queryChannelChildren/"+topChannelId,success:function(result){
                var accordionHtml = "";
                for (var i=0;i<result.length;i++) {
                    var children = result[i].children;
                    if(children != null && children.length > 0){
                        accordionHtml += "<li><div id=accordion_"+result[i].id+" class=\"link\">"+result[i].name+"<i class=\"fa fa-chevron-down\"></i></div>";
                        accordionHtml +="<ul class=\"submenu\">";
                        for(var j=0;j<children.length;j++){
                            accordionHtml +="<li><a href=${web.baseUrl}/channel/"+children[j].id+"  id=accordion_"+children[j].id+">"+children[j].name+"</a></li>"
                        }
                        accordionHtml +="</ul>";
                    }else{
                        accordionHtml += "<li><a href=${web.baseUrl}/channel/"+result[i].id+" id=accordion_"+result[i].id+"><div class=\"link\">"+result[i].name+"</div></a>";
                    }
                    accordionHtml +="</li>";

                }
                $("#accordion").html(accordionHtml);
                for (var i = 0; i < channelList.length; i++) {
                    $("#accordion_" + channelList[i]).addClass("current");
                }
                var accordion = new Accordion($('#accordion'), false);
            }});
        }
    });
</script>
<aside class="col-sm-4 sidebar">
    <ul id="accordion" class="accordion">

    </ul>
</aside>