package com.entities;

import com.entities.Blogadmin;
import com.entities.Post;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-10T13:21:15")
@StaticMetamodel(Author.class)
public class Author_ { 

    public static volatile SingularAttribute<Author, byte[]> authorImage;
    public static volatile SingularAttribute<Author, String> authorEmail;
    public static volatile SingularAttribute<Author, Blogadmin> blogAdminId;
    public static volatile ListAttribute<Author, Post> postList;
    public static volatile SingularAttribute<Author, String> authorName;
    public static volatile SingularAttribute<Author, String> authorWebsite;
    public static volatile SingularAttribute<Author, Integer> authorId;
    public static volatile SingularAttribute<Author, String> authorPassword;

}