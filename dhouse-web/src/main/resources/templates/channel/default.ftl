<!DOCTYPE html>
<html lang=zh-CN>
<head>
    <#include "../common/assign.ftl">
    <#include "../common/meta.ftl">
    <title>${web.title}</title>
    <#include "../common/css.ftl">
    <link rel=stylesheet href=${web.baseUrl}/css/channel.css>
    <#include "../common/js.ftl">

</head>
<body class=home-template>
<#include "../common/header.ftl">
<#include "../common/nav.ftl">
<section class=content-wrap>
    <div class=container>
        <div class=row>
            <#include "../common/sidebar_channel_tree.ftl">
            <#include "default_content_list.ftl">
        </div>
    </div>
</section>
<#include "../common/footer.ftl">
</body>
</html>