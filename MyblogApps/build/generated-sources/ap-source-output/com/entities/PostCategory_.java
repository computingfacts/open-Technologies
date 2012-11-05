package com.entities;

import com.entities.Blogcategory;
import com.entities.Post;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-10T13:21:15")
@StaticMetamodel(PostCategory.class)
public class PostCategory_ { 

    public static volatile SingularAttribute<PostCategory, Long> id;
    public static volatile SingularAttribute<PostCategory, String> category;
    public static volatile SingularAttribute<PostCategory, Post> post;
    public static volatile SingularAttribute<PostCategory, Number> count;
    public static volatile SingularAttribute<PostCategory, Blogcategory> blogCategory;
    public static volatile ListAttribute<PostCategory, Post> list;

}