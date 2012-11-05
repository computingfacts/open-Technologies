/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joseph
 */
@Entity
@Table(name = "blogcategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blogcategory.findAll", query = "SELECT b FROM Blogcategory b"),
    @NamedQuery(name = "Blogcategory.findByBlogCategoryId", query = "SELECT b FROM Blogcategory b WHERE b.blogCategoryId = :blogCategoryId"),
    @NamedQuery(name = "Blogcategory.findByBlogCategory", query = "SELECT b FROM Blogcategory b WHERE b.blogCategory = :blogCategory")})
public class Blogcategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "blogCategoryId")
    private Integer blogCategoryId;
    @Size(max = 100)
    @Column(name = "blogCategory")
    private String blogCategory;
    @OneToMany(mappedBy = "blogCategoryId")
    private List<Post> postList;

    public Blogcategory() {
    }

    public Blogcategory(Integer blogCategoryId) {
        this.blogCategoryId = blogCategoryId;
    }

    public Integer getBlogCategoryId() {
        return blogCategoryId;
    }

    public void setBlogCategoryId(Integer blogCategoryId) {
        this.blogCategoryId = blogCategoryId;
    }

    public String getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(String blogCategory) {
        this.blogCategory = blogCategory;
    }

    @XmlTransient
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blogCategoryId != null ? blogCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blogcategory)) {
            return false;
        }
        Blogcategory other = (Blogcategory) object;
        if ((this.blogCategoryId == null && other.blogCategoryId != null) || (this.blogCategoryId != null && !this.blogCategoryId.equals(other.blogCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Blogcategory[ blogCategoryId=" + blogCategoryId + " ]";
    }
    
}
