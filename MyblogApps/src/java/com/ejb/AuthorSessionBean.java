/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Author;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joseph
 */
@Stateless
public class AuthorSessionBean implements AuthorSessionBeanLocal {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Author readAuthor(int authorId) {
        Author author = manager.find(Author.class, authorId);
        return author;
    }

    @Override
    public void createAuthor(Author author) {
        if (author != null) {
            manager.persist(author);
        }
    }

    @Override
    public void updateAuthor(Author author) {
        if (author != null) {
            manager.merge(author);
        }
    }

    @Override
    public void deleteAuthor(int authorId) {
        Author author = manager.find(Author.class, authorId);
        manager.remove(author);
    }

    @Override
    public Set<Author> getAuthors() {
        List<Author> authors = manager.createNamedQuery("Author.findAll").getResultList();
        Set<Author> uniqueAuthors =  new HashSet<Author>();
        uniqueAuthors.addAll(authors);
        return uniqueAuthors;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
