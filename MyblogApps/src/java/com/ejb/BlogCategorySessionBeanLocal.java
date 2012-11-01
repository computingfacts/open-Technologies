/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import java.util.List;
import javax.ejb.Local;

import com.entities.Blogcategory;

/**
 *
 * @author Joseph
 */
@Local
public interface BlogCategorySessionBeanLocal {

    Blogcategory readCategory(int categoryId);

    void createCategory(Blogcategory category);

    void updateCategory(Blogcategory category);

    void deleteCategory(int categoryId);

    List<Blogcategory> getCategories();
    
 
}
