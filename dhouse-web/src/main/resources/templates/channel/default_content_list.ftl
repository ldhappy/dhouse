<#--默认内容列表-->
<script>
    $(document).ready(function(){
        if(currentChannelId != 0){
            $.ajax({url:"${web.baseUrl}/ajax/content/queryContentList/"+currentChannelId,success:function(result){
                    var contentListHtml = "";
                    for (var i=0;i<result.length;i++) {
                        contentListHtml += "<li>";
                        contentListHtml += "<a href=${web.baseUrl}/content/"+result[i].id+" title=\"\"+result[i].name+\"\">"+result[i].name+"</a>";
                        contentListHtml += "<time>"+new Date(result[i].createTime).Format("yyyy-MM-dd")+"</time>\n";
                        contentListHtml += "</li>";
                    }
                    $("#content_list").html(contentListHtml);
                }});
        }
    });
</script>
<main class="col-sm-8 main-content">
    <article class=content-list>
        <ul id="content_list">
        </ul>
    </article>
</main>