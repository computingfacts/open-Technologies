<%-- 
    Document   : contact
    Created on : 11-Aug-2011, 20:24:43
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <title>Contact | An Open Technology Advocate</title>
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
        <jsp:useBean id="postBean" class="com.controller.BlogController" scope="session" />



        <c:set var="blogMessage" scope="request" value="${postBean.getMessage()}"/>

        <jsp:setProperty name="blogMessage" property="*" />



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

            <c:when test="${not empty param.add}">
                <%
                    String random = request.getParameter("random");

                    postBean.setRandomInput(random);

                %>
                <%postBean.createMessage();%>

            </c:when>



        </c:choose>


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
    </head>
    <body>
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
                        <li class="current"><a href="/contact.jsp">Contact</a></li>

                    </ul>

                </nav>
            </section>
            <header id="pageheader">
                <h1 style="color:darkred " >
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
                <section id="main">
                    <div id="leftcontainer">
                        <section id="normalheader" class="header2">
                            
                        </section>
                        <h2>Contact Me</h2>
                        <section style="" id="contactInfo" >
                            <p style="   " >Please fee free to follow me on twitter
                            <a href="http://twitter.com/josefsamz" class="twitter-follow-button">Follow @josefsamz</a>
                            <script src="http://platform.twitter.com/widgets.js" type="text/javascript"></script></p>
                            
                            <p></p>
                            
                        <p style="">However, you can send me an email on this email address <a href="mailto:joseph@computingfacts.com">joseph[at]computingfacts.com.</a> or use the contact box below. I look forward to hearing from you.</p>
                        </section>
                        
                        <hr></hr>
                        <article class="post">
                            <!--Important--><div class="clear"></div>

                            <form action="contact.jsp" method="post" id="commentform" class="form" name="comment_form" >
                                <p class="textfield">
                                    <input name="senderName" id="senderName" required="true" value="${blogMessage.senderName}"   size="22" tabindex="1" type="text">
                                    <label for="senderName">
                                        <small>Name (required)</small>
                                    </label>
                                </p>
                                <p class="textfield">
                                    <input name="senderEmail" id="senderEmail" required="true" value="${blogMessage.senderEmail}"   size="22" tabindex="2" type="Email">
                                    <label for="senderEmail">
                                        <small>Mail (will not be published) required)</small>
                                    </label>
                                </p>
                                <p class="textfield">
                                    <input name="senderUrl" id="senderUrl" value="${blogMessage.senderUrl}"   size="22" tabindex="3" type="text">
                                    <label for="senderUrl">
                                        <small>Website</small>
                                    </label>
                                </p>
                                <p class="text-area">
                                    <textarea name="theMessage" id="theMessage" value="${blogMessage.theMessage}"  placeholder="max word length 450" maxlength="450"  cols="50" rows="10" tabindex="4">
                                    </textarea>
                                </p>
                                <p class="textfield">
                                    Please enter the displayed letters <label style="color:darkred;font-size:large  " >${postBean.getRandomString()} </label>&nbsp;<input name="random" id="random" value="${postBean.randomInput}" type="text" ></input>
                                </p>
                                <p>
                                    <input name="add" id="submit" tabindex="5" type="image" value="Send" src="images/submit.png">
                                    <input name="messageId" value="${blogMessage.messageId}"   type="hidden">
                                </p>
                                <div class="clear"></div>

                            </form>
                        </article>
                    </div>
                </section>
                <section id="sidebar">
                    <div id="sidebarwrap">
                        <h2>Most Recent Posts</h2>
                        <c:forEach var="r" items="${postBean.recentPostList}">

                            <ul>
                                <li><a href="blog.jsp?title=${r.title}" >${r.title}</a></li>
                            </ul>
                        </c:forEach>
                        <h2>Categories</h2>

                        <c:forEach var="c" items="${postBean.postsInCategory}">
                            <input type="hidden" name="blogCategory" value="${c.blogCategory}" ></input>


                            <ul>
                                <li><a href="index.jsp?blogCategory=${c.getCategory()}" id="blogCategory" name="blogCategory" class="blogCategory" >${c.getCategory()} (${c.count})</a></li>



                            </ul>
                        </c:forEach>

                        <h2>Archives</h2>
                        <c:forEach var="a" items="${postBean.postArchive}">

                            <ul>
                                <li><a href="index.jsp?archive=${a.monthName}">${a.monthName} ${a.theYear} (${a.count})</a></li>
                            </ul>
                        </c:forEach>

                        <h2>Tag Cloud</h2>
                        <hr style="font-weight: bold; color: #2a708e" ></hr>
                        <c:forEach var="t" items="${postBean.tagList}">
                            <input type="hidden" name="tag" value="${t.tagId}" ></input>

                            <a href="blog.jsp?title=${t.tagLink}" style="font-size:${t.tagWeight}px " id="tag" class="tag" >${t.tagName}</a>

                        </c:forEach>
                    </div>
                </section>



                <div class="clear"></div>
            </div>

        </div>
        <footer id="pagefooter">
            <div id="footerwrap">
                <div id="footerwrap">
                    <div class="copyright">
                        2011 &copy; computingfacts.com
                    </div>
                    <div class="credit">
                        <a href="http://computingfacts.com">supporting open source software development</a></div>
                </div>
            </div>
        </footer>
    </body>
</html>
