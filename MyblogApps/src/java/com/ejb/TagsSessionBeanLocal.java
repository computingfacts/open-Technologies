/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Tags;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joseph
 */
@Local
public interface TagsSessionBeanLocal {

    Tags readTags(int tagId);

    void createTags(Tags tags);

    void updateTags(Tags tags);

    void deleteTags(int tagId);

    List<Tags> getTags();
}
