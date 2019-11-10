<#include "base.ftl"/>
<#macro head>
    <title>${Category}</title>
</#macro>
<#macro content>
<div class="row align-items-center" style="margin-left: 400px; margin-top: 30px">
    <#list Posts as pos>
        <div class="card-deck" style="">
            <#list pos as s>
                <div class="card">
                    <a href="/post?id=${s.getId()}">
                        <img src="${s.getImagesRef()}" class="card-img-top" alt="..." height="240">
                        <div class="card-body">
                            <h5 class="card-title">${s.getTitle()}</h5>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">${s.getDate()}</small>
                        </div>
                    </a>
                </div>
            </#list>
        </div>
    </#list>
</div>
</#macro>
<@main/>