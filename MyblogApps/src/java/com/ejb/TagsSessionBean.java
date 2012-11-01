/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Tags;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joseph
 */
@Stateless
public class TagsSessionBean implements TagsSessionBeanLocal {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Tags readTags(int tagId) {

        return manager.find(Tags.class, tagId);
    }

    @Override
    public void createTags(Tags tags) {
        if (tags != null) {
            manager.persist(tags);
        }
    }

    @Override
    public void updateTags(Tags tags) {
        if (tags != null) {
            manager.merge(tags);
        }
    }

    @Override
    public void deleteTags(int tagId) {
        Tags theTag = manager.find(Tags.class, tagId);
        if (theTag != null) {
            manager.remove(theTag);
        }
    }

    @Override
    public List<Tags> getTags() {
        List<Tags> allTags = manager.createNamedQuery("Tags.findAll").getResultList();

        return allTags;
    }
}
