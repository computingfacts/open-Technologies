/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Author;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author Joseph
 */
@Local
public interface AuthorSessionBeanLocal {

    Author readAuthor(int authorId);

    void createAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(int authorId);

    Set<Author> getAuthors();
}
