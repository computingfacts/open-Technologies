<%-- 
    Document   : blog
    Created on : 03-Aug-2011, 22:37:02
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Computing Blog | An Open Technology Advocate</title>
        <link href="style.css" rel="stylesheet" type="text/css">
        <!--[if IE]>
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!--[if IE 6]>
        <script src="js/belatedPNG.js"></script>
        <script>
	DD_belatedPNG.fix('*');
        </script>
        <![endif]-->
        <script src="js/jquery-1.4.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/loopedslider.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript" charset="utf-8">
            $(function(){
                $('#slider').loopedSlider({
                    autoStart: 6000,
                    restart: 5000
                });
		
            });
        </script> 

        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <script language="JavaScript" src="http://www.geoplugin.net/javascript.gp" type="text/javascript"></script>

        <link rel="stylesheet" type="text/css" href="css/style1.css" />


        <jsp:useBean id="postBean" class="com.controller.BlogController" scope="session"/> 
        <c:set var="theComment" scope="request" value="${postBean.getComment()}"/>

        <jsp:setProperty name="theComment" property="*" />



        <c:set var="post" scope="request" value="${postBean.post}"/>

        <jsp:setProperty name="post" property="*" />


        <c:choose>

            <c:when test="${not empty param.edit}">

                <%postBean.getRelatedPost();%>
            </c:when>

            <c:when test="${not empty param.title}">
                <%
                    String selectedTitle = request.getParameter("title");

                    postBean.setSelected_title(selectedTitle);

                %>

                <%postBean.getRelatedPost();%>
            </c:when>


            <c:when test="${not empty param.comment}">
                <%
                    String random = request.getParameter("random");

                    postBean.setRandomInput(random);

                %>

                <%postBean.createComment();%>
            </c:when>

        </c:choose>

        <script src="http://yandex.st/highlightjs/6.0/highlight.min.js"></script>
        <link rel="stylesheet" href="http://yandex.st/highlightjs/6.0/styles/default.min.css"></link>




        <!--                 Include required JS files -->
        <!--                <script type="text/javascript" src="js/shCore.js"></script>-->

        <!--                
                            At least one brush, here we choose JS. You need to include a brush for every 
                            language you want to highlight-->
        <!--                
                        <script type="text/javascript" src="js/shBrushJava.js"></script>
                        <script type="text/javascript" src="js/shBrushJava.js"></script>
                        <script type="text/javascript" src="js/shBrushSql.js"></script>
                        <script type="text/javascript" src="js/shBrushJScript.js"></script>
                        <script type="text/javascript" src="js/shBrushXml.js"></script>
                        <script type="text/javascript" src="js/shBrushCss.js"></script>-->

        <!--                 Include *at least* the core style and default theme -->
        <!--                <link href="css/shCore.css" rel="stylesheet" type="text/css" />
                        <link href="css/shThemeDefault.css" rel="stylesheet" type="text/css" />-->

        <!--                <script type="text/javascript">
                            SyntaxHighlighter.all();
                        </script>
                
                        <script language="javascript">
                            dp.SyntaxHighlighter.ClipboardSwf = '/flash/clipboard.swf';
                            dp.SyntaxHighlighter.HighlightAll('code');
                        </script>-->

        <link rel="stylesheet" type="text/css" href="css/jquery.snippet.css" />

        <script type="text/javascript" src="js/jquery-1.6.2min.js"></script>
        <script type="text/javascript" src="js/jquery.snippet.js"></script>

        <script type="text/javascript" src="js/sh_xml.js"></script>
        <script type="text/javascript" src="js/sh_xml.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function(){
                $("pre.htmlCode").snippet("html");
                // Finds <pre> elements with the class "htmlCode"
                // and snippet highlights the HTML code within.
                $("pre.styles").snippet("css",{style:"greenlcd"});
                // Finds <pre> elements with the class "styles"
                // and snippet highlights the CSS code within
                // using the "greenlcd" styling.
                $("pre.js").snippet("javascript",{style:"random",transparent:true,showNum:false});
                // Finds <pre> elements with the class "js"
                // and snippet highlights the JAVASCRIPT code within
                // using a random style from the selection of 39
                // with a transparent background
                // without showing line numbers.
                $("pre.xml").snippet("xml",{style:"random",transparent:true,showNum:false});
                $("pre.java").snippet("java",{style:"ide-eclipse",transparent:true,showNum:true});
    
                $("pre.clipboard").snippet("java",{style:"peachpuff",clipboard:"js/ZeroClipboard"});
                //Clipboard path MUST be defined in order for "copy" functionality to be available.
                //Path can be relative to the document, root, or absolute (http://...) on the same domain.
                //Clipboard functionality can only be added on the initial Snippet call,
                //it cannot be dynamically added to a pre-existing snippet.

    
    
                $("pre.collapse3").snippet("java",{style:"ide-eclipse",collapse:true,
                    showMsg:"Show Me The Code",hideMsg:"Hide The Code"});
                //The expand/collapse messages will be "Custom Show My Code"
                //and "Custom Hide My Code", respectively.
                $("pre.collapse3").snippet("xml",{style:"ide-eclipse",collapse:true,
                    showMsg:"Show Me The Code",hideMsg:"Hide The Code"});
    
                $("pre.collapse2").snippet("java",{style:"ide-eclipse",collapse:true,startCollapsed:false});
                //Snippet will have collapse functionality, but will be expanded by default
            });
    
    
    
        </script>

        <script type="text/javascript" language="javascript" src="js/ratingsys.js"></script>

        <style type="text/css">
            #rateStatus{float:left; clear:both; width:100%; height:20px;}
            #rateMe{float:left; clear:both; width:100%; height:auto; padding:0px; margin:0px;}
            #rateMe li{float:left;list-style:none;}
            #rateMe li a:hover,
            #rateMe .on{background:url(star_on.gif) no-repeat;}
            #rateMe a{float:left;background:url(star_off.gif) no-repeat;width:12px; height:12px;}
            #ratingSaved{display:none;}
            .saved{color:red; }
        </style>


        <!-- google  -->

        <script type="text/javascript">
 
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-25142257-1']);
            _gaq.push(['_trackPageview']);
 
            (function() {
                var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
            })();
 
        </script>

        <!--        google adsence-->


        <!--        prettify syntax highlighter-->


        <link href="prettify/prettify.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="prettify/prettify.js"></script>



    </head>
    <body onload="prettyPrint()" bgcolor="white" >


        <div id="bodywrap">
            <section id="pagetop">
                <p id="siteinfo">
                    <a href="" > it's all about the source code....</a>
                </p>
                <nav id="sitenav">
                    <ul>
                        <li><a href="/home.jsp">Home</a></li>
                        <li><a href="/about.jsp">About</a></li>
                        <li><a href="#">Downloads</a></li>
                        <li><a href="/contact.jsp">Contact</a></li>

                    </ul>

                </nav>
            </section>
            <header id="pageheader">
                <h1 >
                    <a href="/home.jsp" style="color:darkred; ">  Computing<span style="color:#2a708e " >facts</span></a>
                </h1>
                <div id="search">

                    <form action="#">

                        <div class="searchfield">


                            <input type="text" name="search" id="s">

                        </div>
                        <div class="searchbtn">
                            <input type="image" src="images/searchbtn.png" alt="search">
                        </div>

                    </form>

                </div>
            </header>
            <div id="contents">

                <section id="mainblog">
                    <div id="leftcontainer">

                        <c:forEach var="s" items="${postBean.singlePostObject}">
                            <article class="post">
                                <header>
                                    <h3>${s[1]}</h3>

                                    <p class="postinfo">Published on <time>${s[10]}</time> By <a class="author" href="/about.jsp">${s[8]}</a></p>
<!--                                        &emsp;&bull;&emsp;<a href="#" class="postComment" >Comment on this (${s[9]}) </a></p>-->
                                </header>
                                <!-- AddThis Button BEGIN -->
                                <div class="addthis_toolbox addthis_default_style ">
                                    <a class="addthis_button_preferred_1"></a>
                                    <a class="addthis_button_preferred_2"></a>
                                    <a class="addthis_button_preferred_3"></a>
                                    <a class="addthis_button_preferred_4"></a>
                                    <a class="addthis_button_compact"></a>
                                    <a class="addthis_counter addthis_bubble_style"></a>
                                </div>
                                <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-4e402ec8371a8862"></script>
                                <!-- AddThis Button END -->
                                <div style="float: left; margin: 5px 15px 5px 0;">
                                    <script type="text/javascript">
                                        tweetmeme_url = 'http://www.computingfacts.com/blog.jsp?title=${s[1]}';
                                    </script>
                                    <script type="text/javascript" src="http://tweetmeme.com/i/scripts/button.js"></script>
                                </div>
                                <p><font style="font-size:medium " >${s[2]}</font></p>
                                <!--                                <hr style="font-weight: bold; color: #2a708e" ></hr>-->

                            </article>

                            <div>

                                <footer><div id="fb-root"></div><script src="http://connect.facebook.net/en_US/all.js#appId=237807396242709&amp;xfbml=1"></script><fb:like href="http://www.computingfacts.com/blog.jsp?title=${s[1]}" send="true" layout="button_count" width="150" show_faces="true" font="verdana"></fb:like></footer>
<!--                                <h3>${s[9]} Responses to <font style="color:dodgerblue " >${s[1]}</font> </h3>
                                <h2><a href="/blog.jsp?title=${s[1]}#disqus_thread"></a></h2>
                                <h1><a href="/blog.jsp?title=${s[1]}#disqus_thread" data-disqus-identifier="${s[1]}"></a></h1>
                                <div class="entry-sep"></div> -->

                            </div>
                            <div class="clear"></div>
                            <!--                            Important<div class="clear"></div>
                            <c:forEach var="cmt" items="${postBean.getCommentPerPost()}">
                                <section id="comments">

                                    <div id="commentlist">
                                        <article class="entry">
                                            <div class="avatar"> 
                                                <img src="http://1.gravatar.com/avatar/ad516503a11cd5ca435acc9bb6523536?s=38">
                                                <p class="author">
                                                    <span class="name"><a href="#">${cmt.getComment().getWriterName()}</a></span>
                                                    <time class="date">${cmt.getMonth()} ${cmt.getDay()}, ${cmt.getYear()}</time>

                                                </p>
                                            </div>
                                            <div class="comment">

                                                <p>${cmt.getComment().getComment()}</p>

                                            </div>
                                            <div class="clear"></div>
                                        </article>


                                    </div>
                            </c:forEach>
                       closing foreach here-->

                        </div>
                        <!--                    <div class='blog-pager' id='blog-pager'> 
                                                <span id='blog-pager-newer-link'> 
                                                    <a class='blog-pager-newer-link' href='#' id='Blog1_blog-pager-newer-link' title='Newer Posts'>Newer Posts</a> 
                                                </span> 
                                                <span id='blog-pager-older-link'> 
                                                    <a class='blog-pager-older-link' href='#' id='Blog1_blog-pager-older-link' title='Older Posts'>Older Posts</a> 
                                                </span> 
                                                <a class='home-link' href='/index.jsp'>Home</a> 
                                            </div> -->
                        <div class="entry-sep"></div> 

                        <!--                    <hgroup>
                                                <h3 id="respond">Please Leave a Comment</h3>
                                                <script style="font-style:italic;" language="Javascript"> 
                                                    document.write("<h4 style=font-style:italic;color:darkred >(No Spam Please! Your location is reported to be from  "+geoplugin_city()+", "+geoplugin_countryName() +")</h4>"); 
                                                </script>
                                            </hgroup>-->
                        <!--                    <h3 id="respond">Please Leave a Comment</h3>-->

                        <div id="disqus_thread"></div>
                        <script type="text/javascript">
                            /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
                            var disqus_shortname = 'computingfacts'; // required: replace example with your forum shortname
                            // The following are highly recommended additional parameters. Remove the slashes in front to                         use.
                            var disqus_identifier = 'www.computingfacts.com/blog.jsp?title=${s[1]}';
                            var disqus_url = 'http://computingfacts.com/blog.jsp?title=${s[1]}';
                            //var disqus_developer = 1;
                            /* * * DON'T EDIT BELOW THIS LINE * * */
                            (function() {
                                var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
                                dsq.src = 'http://' + disqus_shortname + '.disqus.com/embed.js';
                                (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
                            })();
                        </script>
                        <noscript>Please enable JavaScript to view the <a href="http://disqus.com/?ref_noscript"></a></noscript>
                        <a href="http://disqus.com" class="dsq-brlink"> <span class="logo-disqus"></span></a>

                    </c:forEach>          





                    <!--                    
                    
                                        <form action="blog.jsp" method="post" id="commentform" class="form" name="comment_form" >
                                            <p class="textfield">
                                                <input name="writerName" id="writerName" value="${theComment.writerName}" size="22" tabindex="1" type="text">
                                                <label for="writerName">
                                                    <small>Name (required)</small>
                                                </label>
                                            </p>
                                            <p class="textfield">
                                                <input name="writerEmail" id="writerEmail" value="${theComment.writerEmail}" size="22" tabindex="2" type="text">
                                                <label for="writerEmail">
                                                    <small>Mail (will not be published) required)</small>
                                                </label>
                                            </p>
                                            <p class="textfield">
                                                <input name="url" id="url" value="${theComment.url}" size="22" tabindex="3" type="text">
                                                <label for="url">
                                                    <small>Website</small>
                                                </label>
                                            </p>
                                            <p class="textfield">
                                                Please enter the displayed letters <label style="color:darkred;font-size:large  " >${postBean.getRandomString()} </label>&nbsp;<input name="random" id="random" value="${postBean.randomInput}" type="text" ></input>
                                            </p>
                                            <p>
                                                <small><strong>No Tags Allowed</strong> ....</small>
                                            </p>
                                            <p class="text-area">
                                                <textarea name="comment" id="comment" placeholder="max word length 450" maxlength="450" value="${theComment.comment}" cols="50" rows="10" tabindex="4">
                                                </textarea>
                                            </p>
                                            <p>
                                                <input name="comment" id="submit" tabindex="5" type="image" src="images/submit.png">
                    
                                                <input name="commentsId" value="${theComment.commentsId}" type="hidden"> 
                                                <input name="post_id" value="${post.postId}" type="hidden">
                                            </p>
                                            <div class="clear"></div>
                    
                                        </form>-->

                </section>

                <!--                  </div>-->
                <!--                </section>-->





                <section id="sidebarblog">
                    <div id="sidebarwrapblog"  >
                        <h2>Related Posts</h2>
                        <c:forEach var="c" items="${postBean.relatedPost}">
                            <ul>
                                <li><a href="blog.jsp?title=${c.title}" id="title" name="title" >${c.title}</a></li>

                            </ul>
                        </c:forEach> 
                        <h2>Categories</h2>

                        <c:forEach var="c" items="${postBean.postsInCategory}">
                            <input type="hidden" name="blogCategory" value="${c.blogCategory}" ></input>
                            <ul>
                                <li><a href="home.jsp?blogCategory=${c.getCategory()}" id="blogCategory" name="blogCategory" class="blogCategory" >${c.getCategory()} (${c.count})</a></li>
                            </ul>
                        </c:forEach>
                        <h2>Archives</h2>
                        <c:forEach var="a" items="${postBean.postArchive}">
                            <ul>
                                <li><a href="home.jsp?archive=${a.monthName}">${a.monthName} ${a.theYear} (${a.count})</a></li>
                            </ul>
                        </c:forEach>
                    </div>


                    <!--                ad begin-->
                    <script type="text/javascript"><!--
                        google_ad_client = "ca-pub-6965028166043243";
                        /* Ad */
                        google_ad_slot = "4731381806";
                        google_ad_width = 250;
                        google_ad_height = 250;
                        //-->
                    </script>
                    <script type="text/javascript"
                            src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
                    </script>

                    <!--end of ad-->

                </section>

                <div class="clear"></div>


            </div>



        </div>






        <footer id="pagefooter">
            <div id="footerwrap">
                <div class="copyright">
                    2011 &copy; computingfacts.com
                </div>
                <div class="credit">
                    <a href="http://computingfacts.com">supporting open source software development</a></div>
            </div>
        </footer>

        <!--disqus comment count-->
        <script type="text/javascript">
            /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
            var disqus_shortname = 'computingfacts'; // required: replace example with your forum shortname
            /* * * DON'T EDIT BELOW THIS LINE * * */
            (function () {
                var s = document.createElement('script'); s.async = true;
                s.type = 'text/javascript';
                s.src = 'http://' + disqus_shortname + '.disqus.com/count.js';
                (document.getElementsByTagName('HEAD')[0] || document.getElementsByTagName('BODY')[0]).appendChild(s);
            }());
        </script>
    </body>
</html>
