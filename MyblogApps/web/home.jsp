<%-- 
    Document   : home
    Created on : 25-July-2011, 19:14:39
    Author     : Joseph
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--        <meta name="robots" content="noarchive"></meta>-->

        <meta property="og:title" content="" />
        <meta property="og:type" content="website" />
        <meta property="og:url" content="" />
        <meta property="og:image" content="" />
        <meta property="og:site_name" content="" />
        <meta property="fb:admins" content="100000646351384" />

    <div id="fb-root"></div><script src="http://connect.facebook.net/en_US/all.js#appId=237807396242709&amp;xfbml=1"></script>




    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



    <script language="JavaScript" src="http://www.geoplugin.net/javascript.gp" type="text/javascript"></script>
    <script src="/js/button.js" type="text/javascript"></script>

    <title>Home | An Open Technology Advocate</title>
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



    <link rel="stylesheet" type="text/css" href="css/style1.css" />



    <jsp:useBean id="postBean" class="com.controller.BlogController" scope="session" />

    <c:set var="post" scope="request" value="${postBean.post}"/>

    <jsp:setProperty name="post" property="*" />

    <c:choose>


        <c:when test="${not empty param.blogCategory}">
            <%       String selected_category = request.getParameter("blogCategory");
                postBean.setSelected_category(selected_category);

            %>
            <%postBean.getPostByCategory();%>

        </c:when>
        <c:when test="${not empty param.archive}">
            <%       String selectedMonth = request.getParameter("archive");

                postBean.setSelecte_month(selectedMonth);
            %>
            <%postBean.getPostsPerMonth();%>

        </c:when>

        <c:when test="${not empty param.recent}">

            <%postBean.getRecentPostList();%>

        </c:when>



    </c:choose>

    <!--    
             Include required JS files 
            <script type="text/javascript" src="js/shCore.js"></script>
        
            
                At least one brush, here we choose JS. You need to include a brush for every 
                language you want to highlight
            
            <script type="text/javascript" src="js/shBrushJava.js"></script>
            <script type="text/javascript" src="js/shBrushSql.js"></script>
            <script type="text/javascript" src="js/shBrushJScript.js"></script>
            <script type="text/javascript" src="js/shBrushXml.js"></script>
            <script type="text/javascript" src="js/shBrushCss.js"></script>
        
             Include *at least* the core style and default theme 
            <link href="css/shCore.css" rel="stylesheet" type="text/css" />
            <link href="css/shThemeDefault.css" rel="stylesheet" type="text/css" />
            <link href="css/shCoreDefault.css" rel="stylesheet" type="text/css" />-->





    <script src="http://yandex.st/highlightjs/6.0/highlight.min.js"></script>
    <link rel="stylesheet" href="http://yandex.st/highlightjs/6.0/styles/default.min.css"></link>

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
    
            $("pre.collapse2").snippet("java",{style:"ide-eclipse",collapse:true,startCollapsed:false});
            //Snippet will have collapse functionality, but will be expanded by default
        });
    
    
    
    </script>

    <!-- google -->

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

    <!--    who among us
        <script>var _wau = _wau || []; _wau.push(["tab", "mfjh1f96ib11", "oo1", "right-lower"]);(function() { var s=document.createElement("script"); s.async=true; s.src="http://widgets.amung.us/tab.js";document.getElementsByTagName("head")[0].appendChild(s);})();</script>-->

    <!--pretty code highlighter-->
    <link href="prettify/prettify.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="prettify/prettify.js"></script>

</head>
<body onload="prettyPrint()" bgcolor="white" >


    <div id="bodywrap">
        <section id="pagetop">
            <p id="siteinfo"  >
            <marquee> <a href="/home.jsp" > it's all about the source code....</a></marquee>
            </p>
            <nav id="sitenav">
                <ul>
                    <li class="current"><a href="/home.jsp">Home</a></li>
                    <li><a href="/about.jsp">About</a></li>
                    <li><a href="#">Downloads</a></li>
                    <li><a href="/contact.jsp">Contact</a></li>

                </ul>

            </nav>
        </section>
        <header id="pageheader">
            <h1 style="color:darkred " >
                Computing<span style="color:#2a708e " >facts</span>
            </h1>
            <div id="search">

                <form action="#" >

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
            <section id="main">
                <section id="featured">
                    <h2 class="ftheading">Featured</h2>
                    <div class="ftwrap">
                        <div class="ftimg">

                            <img src="images/img1.jpg" width="204" height="128" alt="img1"></div>
                        <div class="fttxt">
                            <p id="intro">Welcome to Computingfacts web space, a platform for open source software development, knowledge sharing and networking for software application programmers</p>
                            <p id="intro" >This website is purely about software development using various cutting edge computing technologies.</p>
                        </div>
                    </div>

                </section>

                <div id="leftcontainer">
                    <h2 class="mainheading">
                        <!--                        Latest Post &lt; &gt;-->
                        Latest Post

                    </h2>
                    <%
                        int pageNumber = request.getParameter("pageNumber") == null ? 0 : Integer.parseInt(request.getParameter("pageNumber"));

                        if (pageNumber == -1 || pageNumber < 0) {
                            pageNumber = 0;
                        }
                        postBean.setPageNumber(pageNumber);

                    %>

                    <c:choose>
                        <c:when test="${empty param.blogCategory && empty param.archive}">
                            <c:forEach var="p" items="${postBean.approvedPostList}">
                                <article class="post">
                                    <header>
                                        <h3><a href="blog.jsp?title=${p[1]}" name="title" id="title" style="color:#2a708e " >${p[1]}</a></h3>
                                        <p class="postinfo">Published on <time pubdate="${p[11]}" >${p[11]}</time> under <a href="home.jsp?blogCategory=${p[10]}" id="category1" name="category1" >${p[10]} </a></p>
                                    </header>

                                    <div style="float: left; margin: 5px 15px 5px 0;">
                                        <script type="text/javascript">
                                            tweetmeme_url = 'http://www.computingfacts.com/blog.jsp?title=${p[1]}';
                                        </script>
                                        <script type="text/javascript" src="http://tweetmeme.com/i/scripts/button.js"></script>
                                    </div>

                                    <p><font style="font-size:medium;font-family: 'serif';" >${p[7]}.</font><a href="/blog.jsp?title=${p[1]}" name="title"  id="title" >Read More</a> </p>

                                    <footer>
                                        <span class="author" href="#">${p[8]}</span>
                                        <span class="permalink"><a href="/blog.jsp?title=${p[1]}" name="title"  id="title" >Read Full</a></span>
<!--                                        <span class="comments"><a href="/blog.jsp?title=${p[1]}">comments(${p[9]})</a></span>-->
                                        <span class="comments"><a href="/blog.jsp?title=${p[1]}#disqus_thread" data-disqus-identifier="${p[1]}"></a></span>
                                    </footer>
                                </article>

                            </c:forEach>
                        </c:when> 

                        <c:when test="${not empty param.blogCategory}">

                            <c:forEach var="p" items="${postBean.postByCategory}">
                                <article class="post">
                                    <header>
                                        <h3><a href="/blog.jsp?title=${p[1]}" name="title" id="title" style="color:#2a708e " >${p[1]}</a></h3>
                                        <p class="postinfo">Published on <time pubdate="${p[11]}" >${p[11]}</time> under <a href="/home.jsp?blogCategory=${p[10]}" id="category1" name="category1" >${p[10]} </a></p>
                                    </header>
                                    <div style="float: left; margin: 5px 15px 5px 0;">
                                        <script type="text/javascript">
                                            tweetmeme_url = 'http://www.computingfacts.com/blog.jsp?title=${p[1]}';
                                        </script>
                                        <script type="text/javascript" src="http://tweetmeme.com/i/scripts/button.js"></script>
                                    </div>
                                    <p><font style="font-size:medium  "  >${p[7]}. </font>  <a href="/blog.jsp?title=${p[1]}" name="title"  id="title" >Read More</a></p>

                                    <footer>
                                        <span class="author" href="#">${p[8]}</span>
                                        <span class="permalink"><a href="/blog.jsp?title=${p[1]}" name="title"  id="title" >Read Full</a></span>
<!--                                        <span class="comments"><a href="/blog.jsp?title=${p[1]}">comments(${p[9]})</a></span>-->
                                        <span class="comments"><a href="/blog.jsp?title=${p[1]}#disqus_thread" data-disqus-identifier="${p[1]}"></a></span>
                                    </footer>
                                </article>
                            </c:forEach>
                        </c:when> 


                        <c:when test="${not empty param.archive}">
                            <c:forEach var="p" items="${postBean.postsPerMonth}">

                                <article class="post">
                                    <header>
                                        <h3><a href="/blog.jsp?title=${p[1]}" name="title" id="title" style="color:#2a708e " >${p[1]}</a></h3>
                                        <p class="postinfo">Published on <time pubdate="${p[10]}" >${p[10]}</time></p>
                                    </header>
                                    <div style="float: left; margin: 5px 15px 5px 0;">
                                        <script type="text/javascript">
                                            tweetmeme_url = 'http://www.computingfacts.com/blog.jsp?title=${p[1]}';
                                        </script>
                                        <script type="text/javascript" src="http://tweetmeme.com/i/scripts/button.js"></script>
                                    </div>
                                    <p><font style="font-size:medium "  >${p[7]}. </font> <a href="/blog.jsp?title=${p[1]}" name="title"  id="title" >Read More</a></p>

                                    <footer>
                                        <span class="author" href="#">${p[8]}</span>
                                        <span class="permalink"><a href="/blog.jsp?title=${p[1]}" name="title"  id="title" >Read Full</a></span>
<!--                                        <span class="comments"><a href="/blog.jsp?title=${p[1]}">comments(${p[9]})</a></span>-->
                                        <span class="comments"><a href="/blog.jsp?title=${p[1]}#disqus_thread" data-disqus-identifier="${p[1]}"></a></span>
                                    </footer>
                                </article>
                            </c:forEach>

                        </c:when> 



                    </c:choose>

                    <div class="navigation">
                        <div class="alignleft"><a href="/home.jsp?pageNumber=<%= pageNumber - 1%>" id="prev" >&larr; Newer Entries</a></div>
                        <div class="alignright"><a href="/home.jsp?pageNumber=<%= pageNumber + 1%>" >Older Entries &rarr;</a></div>
                        <div class="clearfix"></div>
                    </div>

                    <!--                    <div class="wp-pagenavi">
                                            <span class="current"><a href="index.jsp?pageNumber=<%= pageNumber = 1%>" >1</a></span><a href="index.jsp?pageNumber=<%= pageNumber = 2%>" >2</a><a href="index.jsp?pageNumber=<%= pageNumber = 3%>" title="3">3</a><a href="index.jsp?pageNumber=<%= pageNumber = 4%>" title="4">4</a><a href="index.jsp?pageNumber=<%= pageNumber = 5%>" title="5">5</a><a href="index.jsp?pageNumber=<%= pageNumber = 6%>" title="6">6</a><a href="index.jsp?pageNumber=<%= pageNumber = 7%>" title="7">7</a><a href="index.jsp?pageNumber=<%= pageNumber = 8%>" title="8">8</a><a href="index.jsp?pageNumber=<%= pageNumber + 1%>">Next &raquo;</a><span class="extend">...</span><a href="index.jsp?pageNumber=<%= pageNumber - 1%>" title="Last &raquo;">Previous &raquo;</a></div>
                                        <div class="clear"></div>-->

                </div>
            </section>
            <section id="sidebar">
                <div id="sidebarwrap" class="pic" >
                    <h2><a href="about.jsp">About Me</a></h2>
                    <!--                    <img src="images/joe.jpg" width="80"px height="60"px alt="img" class="alignleft"></img>-->
                    <p id="intro" >I am a software developer with immense passion for computing information engineering. This Blogging system was developed to demonstrate some of the capabilities of Java EE technologies, and i would be using this platform to share some of my computing experiences over the years.<a href="about.jsp">Read More</a></p>
                    <fieldset>
                        <legend><h2>Stay Connected</h2></legend>
                        <!--                    <h2>Stay Connected</h2>-->
                        <!-- AddThis Button BEGIN -->
                        <div class="addthis_toolbox addthis_default_style addthis_32x32_style">
                            <a class="addthis_button_preferred_1"></a>
                            <a class="addthis_button_preferred_2"></a>
                            <a class="addthis_button_preferred_5"></a>
                            <a class="addthis_button_preferred_4"></a>
                            <a class="addthis_button_compact"></a>
                            <a class="addthis_counter addthis_bubble_style"></a>
                        </div>
                        <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-4e402ec8371a8862"></script>
                        <!-- AddThis Button END -->
                    </fieldset>

                    <h2>Most Recent Posts</h2>
                    <hr style="font-weight: bold; color: #2a708e" ></hr>
                    <c:forEach var="r" items="${postBean.recentPostList}">

                        <ul>
                            <li><a href="/blog.jsp?title=${r.title}" >${r.title}</a></li>
                        </ul>
                    </c:forEach>
                    <h2>Categories</h2>
                    <hr style="font-weight: bold; color: #2a708e" ></hr>
                    <c:forEach var="c" items="${postBean.postsInCategory}">
                        <input type="hidden" name="blogCategory" value="${c.blogCategory}" ></input>


                        <ul>
                            <li><a href="/home.jsp?blogCategory=${c.getCategory()}" id="blogCategory" name="blogCategory" class="blogCategory" >${c.getCategory()} (${c.count})</a></li>



                        </ul>
                    </c:forEach>

                    <h2>Archives</h2>
                    <hr style="font-weight: bold; color: #2a708e" ></hr>
                    <c:forEach var="a" items="${postBean.postArchive}">

                        <ul>
                            <li><a href="/home.jsp?archive=${a.monthName}">${a.monthName} ${a.theYear} (${a.count})</a></li>
                        </ul>
                    </c:forEach>


                    <h2>Tag Cloud</h2>
                    <hr style="font-weight: bold; color: #2a708e" ></hr>
                    <c:forEach var="t" items="${postBean.tagList}">
                        <input type="hidden" name="tag" value="${t.tagId}" ></input>

                        <a href="/blog.jsp?title=${t.tagLink}" style="font-size:${t.tagWeight}px " id="tag" class="tag" >${t.tagName}</a>

                    </c:forEach>

                </div>

                <!--Amazon link to great books -->
                <script type="text/javascript"><!--
                    amazon_ad_tag = "computingfact-21"; amazon_ad_width = "300"; amazon_ad_height = "250"; amazon_ad_link_target = "new"; amazon_ad_price = "retail"; amazon_ad_border = "hide"; amazon_color_border = "06496E"; amazon_color_background = "E6F0F1"; amazon_ad_categories = "adfe";//--></script>
                <script type="text/javascript" src="http://www.assoc-amazon.co.uk/s/ads.js"></script>
                <!-- end Amazon -->
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


    <!-- Share This -->
    <div id="sharethis" > <a href="http://www.addthis.com/bookmark.php?v=250&amp;pubid=ra-4e402ec8371a8862" onmouseover="return addthis_open(this, '', '[URL]', '[TITLE]');" onmouseout="addthis_close()" onclick="return addthis_sendto()"><img src="/images/button-sharethis.gif" alt="Share this page" width="28" height="86" /></a></div>
    <script type="text/javascript">	    var addthis_config = { "data_track_clickback": true };</script>
    <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js?#pubid=ra-4e402ec8371a8862"></script>
    <script type="text/javascript">	var addthis_offset_top = -86; var addthis_offset_left = -28;</script>

    <!-- START: Hide the Share This tab if view port is too small --> 
    <!--   <script type="text/javascript">
            //<![CDATA[
            function GetViewPort(Axis) {
                var viewportwidth; var viewportheight; if (typeof window.innerWidth != 'undefined')
                { viewportwidth = window.innerWidth, viewportheight = window.innerHeight }
                else if (typeof document.documentElement != 'undefined' && typeof document.documentElement.clientWidth != 'undefined' && document.documentElement.clientWidth != 0)
                { viewportwidth = document.documentElement.clientWidth, viewportheight = document.documentElement.clientHeight }
                else
                { viewportwidth = document.getElementsByTagName('body')[0].clientWidth, viewportheight = document.getElementsByTagName('body')[0].clientHeight }
                if (Axis == "W") { return viewportwidth; }
                else { return viewportheight; }
            }
            if (GetViewPort('W') < 1024) { document.getElementById('sharethis').style.display = "none"; }
            else { document.getElementById('sharethis').style.display = "block"; }
            //]]>
        </script>-->
    <!--     END: Hide the Share This tab if view port is too small -->



    <div class="totop">
        <h2><a href="#top">Go To Top</a></h2>
    </div>

    <footer id="pagefooter">
        <div id="footerwrap">
            <div class="copyright">
                2011 &copy; computingfacts.com
                &nbsp;  <a href="https://portal.eapps.com/aff.php?aff=059" target="_blank"><img src="https://portal.eapps.com//affiliate_resources/eapps_light_buy_color.png" style="background-color: rgb(182, 190, 232);" alt="GlassFish hosting" title="GlassFish hosting"/></a>

            </div>


            <div class="credit">
                <a href="http://computingfacts.com">supporting open source software development</a></div>
        </div>
    </footer>

    <!-- Finally, to actually run the highlighter, you need to include this JS on your page -->
    <script type="text/javascript">
        SyntaxHighlighter.all(); 
    </script>
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
