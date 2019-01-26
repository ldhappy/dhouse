<#--无右侧边栏的响应式瀑布流模板-->
<!DOCTYPE html>
<html lang=zh-CN>
<head>
    <#include "../common/assign.ftl">
    <#include "../common/meta.ftl">
    <title>${web.title}</title>
    <#include "../common/css.ftl">
    <link rel=stylesheet href=${web.baseUrl}/css/responsive_waterfall.css />
    <#include "../common/js.ftl">
    <script src=${web.baseUrl}/js/masonry.pkgd.min.js></script>
    <script src=${web.baseUrl}/js/imagesloaded.pkgd.min.js></script>
</head>
<body class=home-template>
<#include "../common/header.ftl">
<#include "../common/nav.ftl">
<script>
    $(document).ready(function(){
        if(currentChannelId != 0){
            $.ajax({url:"${web.baseUrl}/ajax/content/queryContentList/"+currentChannelId,success:function(result){
                    var contentListHtml = "";
                    var width=0;
                    if(result.length >= 4){
                        width=3;
                    }else if(result.length == 3){
                        width=4;
                    }else if(result.length == 2){
                        width=6;
                    }else if(result.length == 1){
                        width=12;
                    }
                    if(result != null && result.length != 0){
                        contentListHtml += "<div class=\"row masonry-container\">";
                        for (var i=0;i<result.length;i++) {
                            contentListHtml += "<div class=\"col-sm-"+width+" item\"><a href=${web.baseUrl}/content/"+result[i].id+" title=\""+result[i].name+"\">";
                            if(result[i].titleImage != null && result[i].titleImage.length > 0){
                                contentListHtml += "<img src="+result[i].titleImage+" alt=\""+result[i].name+"\">";
                            }
                            contentListHtml += result[i].name
                            if(result[i].introduction != null && result[i].introduction.length > 0){
                                contentListHtml += "<span class=introduction>"+result[i].introduction+"</span>";
                            }
                            contentListHtml += "<time>"+new Date(result[i].createTime).Format("yyyy-MM-dd")+"</time>";
                            contentListHtml += "</a></div>";
                        }
                        contentListHtml += "</div>";
                        $("#content_list").html(contentListHtml);
                        var $container = $('.masonry-container');
                        $container.imagesLoaded( function () {
                            $container.masonry({
                                columnWidth: '.item',
                                itemSelector: '.item'
                            });
                        });
                    }

                }});
        }
    });
</script>
<section class=content-wrap>
    <div class=container>
        <main id="content_list" class="responsive-waterfall">

        </main>
    </div>
</section>
<#include "../common/footer.ftl">
</body>
</html>