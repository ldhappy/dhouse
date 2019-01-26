<!DOCTYPE html>
<html lang=zh-CN>
<head>
    <#include "../common/assign.ftl">
    <#include "../common/meta.ftl">
    <title>${web.title}</title>
    <#include "../common/css.ftl">
    <link rel=stylesheet href=${web.baseUrl}/css/content.css>
    <#include "../common/js.ftl">

</head>
<body class=home-template>
<#include "../common/header.ftl">
<#include "../common/nav.ftl">
<section class=content-wrap>
    <div class=container>
        <div class=row>
            <main class="col-sm-8 main-content col-sm-offset-2">
                <#include "default_content.ftl">
            </main>
        </div>
    </div>
</section>
<#include "../common/footer.ftl">
</body>
</html>