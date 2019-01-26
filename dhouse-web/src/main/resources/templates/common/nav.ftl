<script>
    var topChannelId = 0;
    var currentChannelId = 0;
    var channelList= [];
    <#if (channel.id)??>
    <#--存在栏目对象时-->
        currentChannelId = ${channel.id};
    </#if>
    <#if (channel.path)??>
    <#--存在栏目对象时-->
        <#list ((channel.path?remove_beginning("|"))?remove_ending("|"))?split("|") as channelId>
            channelList[${channelId?index}]=${channelId};
        </#list>
        if(channelList.length > 0){
            topChannelId = channelList[0];
        }
    <#else>

    </#if>
    $(document).ready(function(){
        $.ajax({url:"${web.baseUrl}/ajax/channel/queryNav",success:function(result){
                var navHtml = "";
                for (var i=0;i<result.length;i++)
                {
                    navHtml += "<li role=presentation id=nav_li_"+result[i].id+"><a href=${web.baseUrl}/channel/"+result[i].id+" title=\""+result[i].name+"\" >"+ result[i].name+"</a></li>"
                }
                //导航选中状态样式 class="nav-current"
                $("#ul_nav").html(navHtml);
                if(topChannelId != 0){
                    $("#nav_li_"+topChannelId).addClass("nav-current");
                }

            }});
    });
</script>
<nav class=main-navigation>
    <div class=container>
        <div class=row>
            <div class=col-sm-12>
                <div class=navbar-header><span class="nav-toggle-button collapsed" data-toggle=collapse
                                               data-target=#main-menu><span class=sr-only>Toggle navigation</span> <i
                        class="fa fa-bars"></i></span></div>
                <div class="collapse navbar-collapse" id=main-menu>
                    <ul class=menu id="ul_nav">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>
