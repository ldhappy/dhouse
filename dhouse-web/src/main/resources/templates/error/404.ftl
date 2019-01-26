<!DOCTYPE html>
<html lang=zh-CN>
<head>
    <#include "../common/assign.ftl">
    <#include "../common/meta.ftl">
    <title>${web.title}</title>
    <#include "../common/css.ftl">

    <#include "../common/js.ftl">

</head>
<body class=home-template>
<#include "../common/header.ftl">
<#include "../common/nav.ftl">
<section class=content-wrap>
    <div class=container>
        <div class="error">
            <img src="${web.baseUrl}/image/404.jpg">
        </div>
    </div>
</section>
<#include "../common/footer.ftl">
</body>
</html>