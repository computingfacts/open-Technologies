package com.entities;

import com.entities.Author;
import com.entities.Blogcategory;
import com.entities.Comment;
import com.entities.Tags;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-10T13:21:15")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, String> summary;
    public static volatile SingularAttribute<Post, String> content;
    public static volatile SingularAttribute<Post, Blogcategory> blogCategoryId;
    public static volatile ListAttribute<Post, Tags> tagsList;
    public static volatile SingularAttribute<Post, String> title;
    public static volatile SingularAttribute<Post, Boolean> allowComments;
    public static volatile SingularAttribute<Post, Date> publishedDate;
    public static volatile SingularAttribute<Post, Integer> postId;
    public static volatile ListAttribute<Post, Comment> commentList;
    public static volatile SingularAttribute<Post, Author> authorId;

}