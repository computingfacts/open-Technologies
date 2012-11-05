/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Message;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joseph
 */
@Stateless
public class MessageSessionBean implements MessageSessionBeanLocal {

        @PersistenceContext
    private EntityManager manager;
        
    @Override
    public Message readMessage(int msgId) {
        return manager.find(Message.class, msgId);
    }

    @Override
    public void createMessage(Message msg) {
        if(msg != null){
            manager.persist(msg);
        }
    }

    @Override
    public void updateMessage(Message msg) {
        if(msg != null){
            manager.merge(msg);
        }
    }

    @Override
    public void deleteMessage(int msgId) {
        Message msg = manager.find(Message.class, msgId);
        if(msg != null){
            manager.remove(msg);
        }
    }

    @Override
    public List<Message> getMessages() {
        List<Message> messages = manager.createNamedQuery("Message.findAll").getResultList();
        return messages;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
