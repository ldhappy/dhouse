<#--默认内容列表-->
<article class=post>
    <div class=post-head><h1 class=post-title>${content.name}</h1>
        <div class=post-meta><time class=post-date datetime=${content.createTime?date} title=${content.createTime?date}>${content.createTime?date}</time>
        </div>
    </div>
    <div class=post-content>
        ${content.content}
    </div>
    <footer class="post-footer clearfix"></footer>
</article>