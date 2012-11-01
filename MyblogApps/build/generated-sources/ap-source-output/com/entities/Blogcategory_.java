package com.entities;

import com.entities.Post;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-10T13:21:15")
@StaticMetamodel(Blogcategory.class)
public class Blogcategory_ { 

    public static volatile SingularAttribute<Blogcategory, Integer> blogCategoryId;
    public static volatile ListAttribute<Blogcategory, Post> postList;
    public static volatile SingularAttribute<Blogcategory, String> blogCategory;

}