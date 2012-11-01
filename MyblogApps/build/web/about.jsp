<%-- 
    Document   : about
    Created on : 10-Aug-2011, 18:42:41
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Me | An Open Technology Advocate</title>
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
                <p id="siteinfo"  >
                    <a href="" > it's all about the source code....</a>
                </p>
                <nav id="sitenav">
                    <ul>
                        <li><a href="/home.jsp">Home</a></li>
                        <li class="current"><a href="/about.jsp">About</a></li>
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
                <section id="main">
                    <section id="normalheader">

                    </section>
                    <div id="leftcontainer">
                        <h2 class="mainheading"></h2>
                        <article class="post" style="font-family:  Helvetica, sans-serif;" >
                            <p>This website is a platform I developed to experiment with technology with the purpose of providing open source software programs ranging from mobile apps to enterprise business applications.</p>
                            <p>I am passionate about Information Technology and with a special interest in computational intelligence, web technologies, Data mining, algorithms & data structures, software design patterns and cloud computing.</p>
                            <p>It is my utmost interest to share my experience in these areas via this website and also to learn from other software experts who share the same interest.</p>
                            <p>Having developed several enterprise projects using Java and other web technologies, I decided to implement some of the experiences in developing this website. This blogging system leverages the capabilities of Java EE technologies(EJB,JSP, glassfish application server) with MySQL database and I hope to make the source code available in due time.</p>
                            <p>However, when I am not programming or doing anything computer related, I enjoy playing games like chess, making music, writing and travelling.</p>
                            <p>I would like to connect with you, so feel free to <a href="/contact.jsp">contact me</a> if you share similar interest or would need my services in any way.</p>
                        </article>
                        <div class="clear"></div>
                    </div>
                </section>
                <section id="sidebar">
                    <div id="sidebarwrap">
                        <h2>About Me</h2>
                        <img src="images/joe.jpg" width="80"px height="60"px alt="img" class="alignleft"></img>
                        <p id="intro" >I am a software developer with immense passion for computing information engineering. This Blogging system was developed to demonstrate some of the capabilities of Java EE technologies, and i would be using this platform to share some of my computing experiences over the years.</p>
                        <h2>Connect with me</h2>
                        <div id="social_icons_2">
                            <a href="http://twitter.com/josefsamz" title="Follow computingfacts on Twitter"><img src="images/twitter_32.png" alt="Follow computingfacts on Twitter" title="Follow computingfacts on Twitter"/></a>&nbsp;<a href="http://www.facebook.com/pages/Computingfacts/240780319316788?sk=wall" title="Follow Computingfacts on FaceBook"><img src="images/facebook_32.png" alt="Follow Computingfacts on FaceBook" title="Follow Computingfacts on FaceBook"/></a>&nbsp;<a href="http://www.linkedin.com/pub/joseph-o/30/99b/663" title="View my Linkedin Profile"><img src="images/linkedin_32.png" alt="view my Linkedin profile" title="View my Linkedin profile"/></a> &nbsp;   
                            
                                                        <!--
                            Skype 'Skype Me™!' button
                            http://www.skype.com/go/skypebuttons
                            -->
                            <script type="text/javascript" src="http://download.skype.com/share/skypebuttons/js/skypeCheck.js"></script>
                            <a href="skype:joeddy_77?call"><img src="http://download.skype.com/share/skypebuttons/buttons/call_blue_white_124x52.png" style="border: none;" width="124" height="52" alt="Skype Me™!" /></a>



                            <script src="http://widgets.twimg.com/j/2/widget.js"></script>
                            <script>
                                new TWTR.Widget({
                                    version: 2,
                                    type: 'profile',
                                    rpp: 4,
                                    interval: 6000,
                                    width: 250,
                                    height: 300,
                                    theme: {
                                        shell: {
                                            background: '#365763',
                                            color: '#ffffff'
                                        },
                                        tweets: {
                                            background: '#304147',
                                            color: '#129de8',
                                            links: '#3cb5b5'
                                        }
                                    },
                                    features: {
                                        scrollbar: false,
                                        loop: true,
                                        live: true,
                                        hashtags: true,
                                        timestamp: true,
                                        avatars: false,
                                        behavior: 'default'
                                    }
                                }).render().setUser('josefsamz').start();
                            </script>

                        </div>
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
    </body>
</html>
