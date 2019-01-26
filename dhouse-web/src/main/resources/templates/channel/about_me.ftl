<!DOCTYPE html>
<html lang=zh-CN>
<head>
    <#include "../common/assign.ftl">
    <#include "../common/meta.ftl">
    <title>${web.title}</title>
    <#include "../common/css.ftl">
    <link rel=stylesheet href=${web.baseUrl}/css/about_me.css>
    <#include "../common/js.ftl">

</head>
<body class=home-template>
<#include "../common/header.ftl">
<#include "../common/nav.ftl">
<section class=content-wrap>
    <div class=container>
        <div class=row>
            <main class="col-sm-8 main-content">
                <article class=about-me>
                    <h1>爱美食的程序员</h1>
                    <img src="${web.baseUrl}/image/about_me.jpg" />
                </article>
            </main>
            <aside class="col-md-4 sidebar">
                <div class=widget><h4 class=title>联系方式</h4>
                    <div class="content community">
                        <div class="row" style="margin-bottom: 10px">
                            <div class="col-xs-3 bold">
                                QQ
                            </div>
                            <div class="col-xs-9">
                                397189351
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-3 bold">
                                邮箱
                            </div>
                            <div class="col-xs-9">
                                ldhappy831@126.com
                            </div>
                        </div>
                    </div>
                </div>
            </aside>
        </div>
    </div>
</section>
<#include "../common/footer.ftl">
</body>
</html>