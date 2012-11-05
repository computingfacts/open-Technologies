/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.entities.Blogcategory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Joseph
 */
@Stateless
public class BlogCategorySessionBean implements BlogCategorySessionBeanLocal {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Blogcategory readCategory(int categoryId) {
        Blogcategory category = manager.find(Blogcategory.class, categoryId);
        return category;
    }

    @Override
    public void createCategory(Blogcategory category) {
        manager.persist(category);
    }

    @Override
    public void updateCategory(Blogcategory category) {
        manager.merge(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Blogcategory category = manager.find(Blogcategory.class, categoryId);
        manager.remove(category);
    }

    @Override
    public List<Blogcategory> getCategories() {

        return manager.createNamedQuery("Blogcategory.findAll").getResultList();


    }
//    @Override
//    public List<Object[]> getObject() {
//        String q="SELECT c FROM Blogcategory c JOIN c.post p";
//       // String q = "SELECT c, COUNT(p) AS pcount FROM BlogCategory fetch c.post p ORDER BY pcount DESC";
//        TypedQuery<Object[]> query = manager.createQuery(
//               q , Object[].class);
//        List<Object[]> results = query.getResultList();
//        for (Object[] result : results) {
//            System.out.println("session: " + result[0] + ", session: " + result[1]);
//        }
//        
//        return results;
//    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
