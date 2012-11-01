package com.entities;

import com.entities.Post;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-10T13:21:15")
@StaticMetamodel(Tags.class)
public class Tags_ { 

    public static volatile SingularAttribute<Tags, Double> tagWeight;
    public static volatile SingularAttribute<Tags, String> tagName;
    public static volatile SingularAttribute<Tags, String> tagLink;
    public static volatile SingularAttribute<Tags, Integer> tagId;
    public static volatile SingularAttribute<Tags, Post> postId;

}