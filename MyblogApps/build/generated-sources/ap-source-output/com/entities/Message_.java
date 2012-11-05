package com.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-10T13:21:15")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, String> senderName;
    public static volatile SingularAttribute<Message, String> senderUrl;
    public static volatile SingularAttribute<Message, Integer> messageId;
    public static volatile SingularAttribute<Message, String> senderEmail;
    public static volatile SingularAttribute<Message, Date> messageDate;
    public static volatile SingularAttribute<Message, String> theMessage;

}