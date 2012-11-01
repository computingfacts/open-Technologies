/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Joseph
 */
@Entity
public class PostCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;
    private Number count;
    @OneToOne
    private Blogcategory blogCategory;
    
    @ManyToOne
    private Post post;
    
    @OneToMany
    private List<Post> list = new ArrayList<Post>();
  

    public PostCategory() {
    }

    public PostCategory(Long id) {
        this.id = id;
    }

    public PostCategory(Blogcategory blogCategory, Number count) {
        this.count = count;
        this.blogCategory = blogCategory;
    }

    
    public PostCategory(String cat, Number c) {
        this.category = cat;
        this.count = c;
 

    }
    
    public PostCategory(String cat, Post post){
        this.category = cat;
        list.add(post);
        
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }

    public Blogcategory getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(Blogcategory blogCategory) {
        this.blogCategory = blogCategory;
    }

    public List<Post> getList() {
        return list;
    }

    public void setList(List<Post> list) {
        this.list = list;
    }



    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

 

 
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;

    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostCategory)) {
            return false;
        }
        PostCategory other = (PostCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PostCategory{" + "id=" + id + ", category=" + category + ", count=" + count + ", blogCategory=" + blogCategory + ", post=" + post + ", list=" + list + '}';
    }

}
