<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <#list Posts as p>
        <div>
            <a href = "http://localhost:8080/post?id=${p.getId()}">
                <img src="${p.getImagesRef()}" alt = "Image"
                     width = "250"
                     height= "300"/>
                <div>
                    <p>${p.getTitle()}</p>
                    <p>${p.getDate()}</p>
                </div>
            </a>
        </div>
    </#list>
</body>
</html>