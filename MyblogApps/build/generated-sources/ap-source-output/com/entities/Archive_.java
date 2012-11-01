package com.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-10T13:21:15")
@StaticMetamodel(Archive.class)
public class Archive_ { 

    public static volatile SingularAttribute<Archive, Long> id;
    public static volatile SingularAttribute<Archive, Number> count;
    public static volatile SingularAttribute<Archive, Number> theYear;
    public static volatile SingularAttribute<Archive, String> monthName;
    public static volatile SingularAttribute<Archive, Date> theMonth;

}