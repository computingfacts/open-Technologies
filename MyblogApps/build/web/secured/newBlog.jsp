<%-- 
    Document   : newBlog
    Created on : 06-Aug-2011, 10:14:34
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <title>Computing Blog | Computingfacts</title>
        <link href="/secured/adminstyle.css" rel="stylesheet" type="text/css">
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

        <jsp:useBean id="postController" class="com.controller.BlogController" scope="session"/>

        <c:set var="post" scope="request" value="${postController.post}"/>

        <jsp:setProperty name="post" property="*" />



        <c:choose>
            <c:when test="${not empty param.add}">
                <%       String cat = request.getParameter("category");

                    postController.setPostCategoryId(Integer.parseInt(cat));
                    String author_id = request.getParameter("authorName");
                    postController.setPostAuthorId(Integer.parseInt(author_id));

                %>
                <% postController.createPost();%>
            </c:when>


            <c:when test="${not empty param.update}">
                <% postController.updatePost();%>
            </c:when>

            <c:when test="${not empty param.delete}">
                <% postController.deletePost();%>
            </c:when>

            <c:when test="${not empty param.edit}">
                <% postController.findPost();%>
            </c:when>


        </c:choose>
        <script type="text/javascript" src="./js/jquery-1.4.min.js"></script>
        <script type="text/javascript" src="./markitup/jquery.markitup.js"></script>

        <script type="text/javascript" src="./markitup/sets/html/set.js"></script>

        <script type="text/javascript" src="./markitup/sets/default/set.js"></script>

        <link rel="stylesheet" type="text/css" href="./markitup/skins/markitup/style.css" />
        <link rel="stylesheet" type="text/css" href="./markitup/sets/default/style.css" />
        <link rel="stylesheet" type="text/css" href="./markitup/sets/html/style.css" />



        <script type="text/javascript" >
            $(document).ready(function() {
                $("textarea").markItUp(mySettings);
            });
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
                        <li><a href="/secured/admin.jsp">Admin</a></li>
                        <li class="current"><a href="/contact.jsp">Contact</a></li>

                    </ul>

                </nav>
            </section>
            <header id="pageheader">
                <h1 style="color:darkred " >
                    Computing<span style="color:#2a708e " >facts</span>
                </h1>
                <div id="search">

                    <form action="#">

                        <div class="searchfield">


                            <input type="text" name="search" id="s">

                        </div>
                        <div class="searchbtn">
                            <input type="image" src="/secured/images/searchbtn.png" alt="search">
                        </div>

                    </form>

                </div>
            </header>
            <div id="contents">
                <section id="main">
                    <div id="leftcontainer">
                        <section id="normalheader" class="header2">
                            <h2>Create New Post</h2>
                    </div>

                    <section class="post">

                        <form method="post" action="newBlog.jsp">

                            <table>
                                <tr>
                                    <td><label for="title">Title : </label></td>
                                    <td><input type="text" name="title" placeholder="title of the article" value="${post.title}" id="title" size="60" class="blog_title" /></td>
                                </tr>

                                <tr>
                                    <td><label for="category">Category :</label></td>


                                    <td>
                                        <select name="category" id="category" size="1" class="category">
                                            <c:forEach var="c" items="${postController.categoryList}">
                                                <option value="${c.blogCategoryId}">${c.blogCategory}</option>


                                            </c:forEach>

                                        </select>
                                    </td>


                                </tr>

                                <tr>
                                    <td><label for="authorName">Author Name : </label></td>
                                    <td>
                                        <select name="authorName" id="authorName" size="1" class="blog_authorName">
                                            <c:forEach var="a" items="${postController.authorList}">
                                                <option value="${a.authorId}">${a.authorName}</option>

                                            </c:forEach>

                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="allowComments" >Allow Comment </label> </td>
                                    <td><input type="checkbox" name="allowComments" value="true${post.setAllowComments("value")}"></input></td>
                                </tr>
                                <tr>
                                    <td><label for="summary">Summary :</label></td>
                                    <td><textarea id="summary" placeholder="the content goes here" value="${post.summary}" name="summary" class="content" cols="40" rows="10" autofocus="summary" >${post.summary}</textarea></td>
                                </tr>
                                <tr>
                                    <td><label for="bcontent">Content :</label></td>

<!--                            <td><textarea id="wysiwyg" placeholder="the content goes here" value="${post.content}" name="content" rows="20" cols="50" class="content" autofocus="content" >${post.content}</textarea></td>-->
                                    <td><textarea id="content" placeholder="the content goes here" value="${post.content}" name="content" class="content" cols="50" rows="20" autofocus="content" >${post.content}</textarea></td>
                                </tr>

                            </table>

                            <input type="hidden" name="postId" value="${post.postId}"/>
                            <input type="hidden" name="postCategoryId" value="${postController.postCategoryId}"/>
                            <input type="submit" name="add" value="Add"/>
                            <input type="submit" name="update" value="Update"/>

                        </form>
                    </section>
                    <!--Important--><div class="clear"></div>

                    <section>
                        <table border="0">
                            <tr>
                                <th>Title</th><th>Date</th><th style="color:#2a708e" >Operations</th>
                            </tr>

                            <c:forEach var="p" items="${postController.postList}">

                                <tr>

                                    <td>${p.title}</td>
                                    <td>${p.publishedDate}</td>

                                    <td>
                                        <form method="post" action="newBlog.jsp" style="margin: 0">
                                            <input type="hidden" name="postId" value="${p.postId}"/>
                                            <input type="submit" name="edit" value="Edit"/>
                                            <input type="submit" name="delete" value="Delete"/>
                                        </form>
                                    </td>
                                </tr>

                            </c:forEach>

                        </table>
                    </section>


                </section>


                <section id="sidebar">
                    <!--                    <div id="sidebarwrap">-->

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

                    <!--                    </div>-->
                </section>



                <div class="clear"></div>
            </div>

        </div>
        <footer id="pagefooter">
            <div id="footerwrap">
                <div class="copyright">
                    2010 &copy; Your Copyright Information Goes Here
                </div>
                     <div class="credit">
                <a href="http://computingfacts.com">supporting open source software development</a></div>
            </div>
        </footer>
    </body>
</html>
