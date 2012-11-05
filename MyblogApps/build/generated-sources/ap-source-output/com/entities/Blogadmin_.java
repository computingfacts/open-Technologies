package com.entities;

import com.entities.Author;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-10T13:21:15")
@StaticMetamodel(Blogadmin.class)
public class Blogadmin_ { 

    public static volatile SingularAttribute<Blogadmin, String> username;
    public static volatile SingularAttribute<Blogadmin, Short> blogAdminId;
    public static volatile ListAttribute<Blogadmin, Author> authorList;
    public static volatile SingularAttribute<Blogadmin, String> password;

}