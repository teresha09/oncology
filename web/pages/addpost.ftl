<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form method="post" enctype="multipart/form-data">
        <input name="title" type="text" placeholder="Title">
        <input name="text" type="text" placeholder="Text">
        <p><label>
                <select size="1" name ="category">
                        <option value="Рак">Рак</option>
                        <option value="Лечение">Лечение</option>
                        <option value="Диагностика">Диагностика</option>
                        <option value="Новости">Новости</option>
                    </select>
            </label>
        </p>
        <input name="file" type="file">
        <button type="submit">Батон</button>
    </form>
</body>
</html>