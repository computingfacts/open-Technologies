package com.entities;

import com.entities.Post;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-10T13:21:15")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Boolean> approved;
    public static volatile SingularAttribute<Comment, String> writerName;
    public static volatile SingularAttribute<Comment, String> writerEmail;
    public static volatile SingularAttribute<Comment, Date> publishedDate;
    public static volatile SingularAttribute<Comment, Integer> commentsId;
    public static volatile SingularAttribute<Comment, String> comment;
    public static volatile SingularAttribute<Comment, String> url;
    public static volatile SingularAttribute<Comment, Post> postId;

}