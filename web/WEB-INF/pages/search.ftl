<#include "base.ftl"/>
<#macro head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</#macro>
<#macro content>
    <input id="query" style="width: 40%" oninput="f()"/>
    <div id="res"></div>
    <script type="application/javascript">
        function f() {
            if ($("#query").val().length >= 3) {
                $.ajax({
                    url: "/dosearch",
                    data: {"query": $("#query").val()},
                    dataType: "json",
                    success: function (msg) {
                        if (msg.objects.length > 0) {
                            $("#res").html("");
                            for ( var i = 0; i < msg.objects.length; i++) {
                                $("#res").append("<li><a href=\"/post?id=" +  + msg.objects[i].id + "\">" + msg.objects[i].title + "</a>" + "</li>");
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
</#macro>
<@main/>
