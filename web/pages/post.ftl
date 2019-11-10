<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#if Post??>
    <img src="${Post.getUser().getProfilePicture()}" width="50" height="75" alt = "avatar">
    <h4>${Post.getUser().getName() + " " + Post.getUser().getSurname()}</h4>
    <h1>${Post.getTitle()}</h1>
    <p>${Post.getText()}</p>
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
</#if>
</body>
</html>