<#include "base.ftl"/>
<#macro head>
    <title>${Post.getTitle()}</title>
</#macro>
<#macro content>
    <div class="row align-items-center" style="margin-left: 400px; margin-top: 30px">

        <div class="card" style="width: 50rem; margin-bottom: 100px;">
            <div class="card-header" >
                <img src="${Post.getImagesRef()}">
            </div>

            <div class="card-body" >
                    <p>
                        <h3>${Post.getTitle()}</h3>
                        ${Post.getText()}
                    </p>
                    <p>Автор - ${Post.getUser().getName() + " " + Post.getUser().getSurname()}</p>
            </div>
            <#list Comments as com>
                <div>
                    <p>${com.getUser().getName()} ${com.getUser().getSurname()}</p>
                    <p>${com.getText()}</p>
                    <h6>${com.getDate()}</h6>
                </div>
            </#list>
            <div>
                <form action="/comment" method="get">
                    <input name="comment" type="text" placeholder="Comment">
                    <input name="idPost" value="${Post.getId()}" type="hidden">
                    <button type="submit">Опубликовать комментарий</button>
                </form>
            </div>
        </div>
        </div>

</#macro>
<@main/>