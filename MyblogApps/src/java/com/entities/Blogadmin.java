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
@Table(name = "blogadmin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blogadmin.findAll", query = "SELECT b FROM Blogadmin b"),
    @NamedQuery(name = "Blogadmin.findByBlogAdminId", query = "SELECT b FROM Blogadmin b WHERE b.blogAdminId = :blogAdminId"),
    @NamedQuery(name = "Blogadmin.findByUsername", query = "SELECT b FROM Blogadmin b WHERE b.username = :username"),
    @NamedQuery(name = "Blogadmin.findByPassword", query = "SELECT b FROM Blogadmin b WHERE b.password = :password")})
public class Blogadmin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "blogAdminId")
    private Short blogAdminId;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 450)
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "blogAdminId")
    private List<Author> authorList;

    public Blogadmin() {
    }

    public Blogadmin(Short blogAdminId) {
        this.blogAdminId = blogAdminId;
    }

    public Short getBlogAdminId() {
        return blogAdminId;
    }

    public void setBlogAdminId(Short blogAdminId) {
        this.blogAdminId = blogAdminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blogAdminId != null ? blogAdminId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blogadmin)) {
            return false;
        }
        Blogadmin other = (Blogadmin) object;
        if ((this.blogAdminId == null && other.blogAdminId != null) || (this.blogAdminId != null && !this.blogAdminId.equals(other.blogAdminId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Blogadmin[ blogAdminId=" + blogAdminId + " ]";
    }
    
}
