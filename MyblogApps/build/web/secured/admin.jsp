<%-- 
    Document   : admin
    Created on : 12-Aug-2011, 20:57:03
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <meta name="robots" content="NOINDEX, NOFOLLOW"></meta>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
         <script language="JavaScript" src="http://www.geoplugin.net/javascript.gp" type="text/javascript"></script>
        <title>Blog Administration Page</title>
        <link rel="stylesheet" type="text/css" href="./css/style1.css" />
        
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <title>Computing facts | Blog</title>
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
        
                        <jsp:useBean id="blogController" class="com.controller.BlogController" scope="session" />
                <c:set var="blog" scope="request" value="${blogController.category}"/>

                <c:set var="comment" scope="request" value="${blogController.comment}"/>
                <c:set var="post" scope="request" value="${blogController.post}"/>

                <jsp:setProperty name="comment" property="*" />

                <jsp:setProperty name="blog" property="*" />
                <jsp:setProperty name="post" property="*" />

                <c:choose>
                    <c:when test="${not empty param.add}">
                        <% blogController.createBlogCategory();%>
                    </c:when>

                    <c:when test="${not empty param.edit}">
                        <% blogController.findBlogCategory();%>
                    </c:when>

                    <c:when test="${not empty param.update}">
                        <% blogController.updateBlogCategory();%>
                    </c:when>

                    <c:when test="${not empty param.delete}">
                        <%blogController.deleteBlogCategory();%>
                    </c:when>


                    <c:when test="${not empty param.editComment}">
                        <% blogController.findComment();%>
                    </c:when>

                    <c:when test="${not empty param.updateComment}">
                        <% blogController.updateComment();%>
                    </c:when>

                    <c:when test="${not empty param.deleteComment}">
                        <%blogController.deleteComment();%>
                    </c:when>

                </c:choose>
                
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
                        <li><a href="/secured/newBlog.jsp">new Blog</a></li>
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

                        </section>
                        
                         <h6 style="border-color:#104258;font:bold " >Manage Blog Category</h6>                       

                <form method="post" action="admin.jsp">
                    <table style="padding-bottom:inherit;border:#2a708e  " >
                        <tr>
                            <td>Category:</td>
                            <td><input type="text" name="blogCategory" size="35" value="${blog.blogCategory}" /></td>
                        </tr>
                    </table>

                    <input type="hidden" name="blogCategoryId" value="${blog.blogCategoryId}"/>
                    <input type="submit" name="add" value="Add" onsubmit="document.getElementsByName(blogCategory) = null" />
                    <input type="submit" name="update" value="Update"/>
                </form>
                     <hr style=" border-color: #2a708e " >
                   
                <table border="0">

                    <tr>
                        <th>Blog Category</th><th style="color:#2a708e" >operations</th>
                    </tr>                       
                    <c:forEach var="c" items="${blogController.categoryList}">
                        <tr>
                           
                            <td> <ul>
                                    <li><a href="#">${c.blogCategory}</a></li>
                                </ul></td>
                            <td>
                                <form method="post" action="admin.jsp" style="margin: 0">
                                    <input type="hidden" name="blogCategoryId" value="${c.blogCategoryId}"/>
                                    <input type="submit" name="edit" value="Edit"/>
                                    <input type="submit" name="delete" value="Delete"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
                  

                <hr style=" border-color: #2a708e " >         
                <h6 style="border-color:#104258;font:bold " > Manage Comments</h6>

                    <form method="post" action="admin.jsp">
                        <table style="padding-bottom:inherit;border:#2a708e  " >
                            <tr>
                                <td><label for="approved" >Approve Comment </label> </td>
                                <td><input type="checkbox" name="approved" value="true${comment.setApproved("value")}"></input></td>
                                <td><label for="comment" ></label> </td>

                                <td><textarea name="comment" id="comment" value="${comment.getComment()}" cols="39" rows="8" >${comment.getComment()}</textarea> </td>
                            </tr>
                        </table>

                        <input type="hidden" name="commentsId" value="${comment.commentsId}"/>

                        <input type="submit" name="updateComment" value="Update Comment"/>
                    </form>
                         <hr style=" border-color: #2a708e " > 
                        
                    <table border="0">
                        <tr>
                            <th>Name</th><th>Email</th><th>Web page</th><th style="color:#2a708e" >Operations</th>
                        </tr>

                        <c:forEach var="cm" items="${blogController.commentList}">

                            <tr>
                                <td>${cm.writerName}</td>
                                <td>${cm.writerEmail}</td>
                                <td>${cm.url}</td>
                                <td>
                                    <form method="post" action="admin.jsp" style="margin: 0">
                                        <input type="hidden" name="commentsId" value="${cm.commentsId}"/>
                                        <input type="submit" name="editComment" value="Edit Comment"/>
                                        <input type="submit" name="deleteComment" value="Delete Comment"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                        
                        
                         <p><a href="/secured/newBlog.jsp" >Manage Post</a></p>
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
                                <li><a href="home.jsp?blogCategory=${c.getCategory()}" id="blogCategory" name="blogCategory" class="blogCategory" >${c.getCategory()} (${c.count})</a></li>



                            </ul>
                        </c:forEach>

                        <h2>Archives</h2>
                        <c:forEach var="a" items="${postBean.postArchive}">

                            <ul>
                                <li><a href="home.jsp?archive=${a.monthName}">${a.monthName} ${a.theYear} (${a.count})</a></li>
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
                <div class="copyright">
                    2010 &copy; Your Copyright Information Goes Here
                </div>
                     <div class="credit">
                <a href="http://computingfacts.com">supporting open source software development</a></div>
            </div>
        </footer>
    </body>
</html>
