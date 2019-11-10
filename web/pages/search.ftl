<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
    <p><input id="query" oninput="f()"/></p>
    <div id="res"></div>
        <script type="application/javascript">
            function f() {
                if ($("#query").val().length >= 1) {
                    $.ajax({
                        url: "/dosearch",
                        data: {"query": $("#query").val()},
                        dataType: "json",
                        success: function (msg) {
                            if (msg.objects.length > 0) {
                                $("#res").html("");
                                for ( var i = 0; i < msg.objects.length; i++) {
                                    $("#res").append("<li>" + msg.objects[i].title + "</li>");
                                }
                            } else {
                                $("#res").html("No results..");
                            }
                        }
                    })
                }
                else {
                    $("#res").html("");
                }
            }
        </script>
</body>
</html>