<%-- 
    Document   : problems
    Created on : 19-Nov-2011, 14:21:54
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="demo.css" />

        <script type="text/javascript" src="js/Markdown.Converter.js"></script>
        <script type="text/javascript" src="js/Markdown.Sanitizer.js"></script>
        <script type="text/javascript" src="js/Markdown.Editor.js"></script>
        <title>JSP Page</title>
    </head>
        <body>
        <div class="wmd-panel">
            <div id="wmd-button-bar"></div>
            <textarea class="wmd-input" id="wmd-input">

            </textarea>
        </div>
        <div id="wmd-preview" class="wmd-panel wmd-preview"></div>

        <br /> <br />
        <marquee>I'm the ghost from the past!</marquee>




        <script type="text/javascript">
            (function () {
                var converter1 = Markdown.getSanitizingConverter();
                var editor1 = new Markdown.Editor(converter1);
                editor1.run();
                
                var converter2 = new Markdown.Converter();

                converter2.hooks.chain("preConversion", function (text) {
                    return text.replace(/\b(a\w*)/gi, "*$1*");
                });

                converter2.hooks.chain("plainLinkText", function (url) {
                    return "This is a link to " + url.replace(/^https?:\/\//, "");
                });
                
                var help = function () { alert("Do you need help?"); }
                
                var editor2 = new Markdown.Editor(converter2, "-second", { handler: help });
                
                editor2.run();
            })();
        </script>
    </body>
</html>
