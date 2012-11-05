/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Message;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joseph
 */
@Local
public interface MessageSessionBeanLocal {
    
       Message readMessage(int msgId);

    void createMessage(Message msg);

    void updateMessage(Message msg);

    void deleteMessage(int msgId);

    List<Message> getMessages();
    
}
