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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "author")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
    @NamedQuery(name = "Author.findByAuthorId", query = "SELECT a FROM Author a WHERE a.authorId = :authorId"),
    @NamedQuery(name = "Author.findByAuthorName", query = "SELECT a FROM Author a WHERE a.authorName = :authorName"),
    @NamedQuery(name = "Author.findByAuthorPassword", query = "SELECT a FROM Author a WHERE a.authorPassword = :authorPassword"),
    @NamedQuery(name = "Author.findByAuthorEmail", query = "SELECT a FROM Author a WHERE a.authorEmail = :authorEmail"),
    @NamedQuery(name = "Author.findByAuthorWebsite", query = "SELECT a FROM Author a WHERE a.authorWebsite = :authorWebsite")})
public class Author implements Serializable {
    @Lob
    @Column(name = "authorImage")
    private byte[] authorImage;
    @JoinColumn(name = "blogAdminId", referencedColumnName = "blogAdminId")
    @ManyToOne
    private Blogadmin blogAdminId;
     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
   // @NotNull
    @Column(name = "authorId")
    private Integer authorId;
    @Size(max = 45)
    @Column(name = "authorName")
    private String authorName;
    @Size(max = 50)
    @Column(name = "authorPassword")
    private String authorPassword;
    @Size(max = 45)
    @Column(name = "authorEmail")
    private String authorEmail;
    @Size(max = 45)
    @Column(name = "authorWebsite")
    private String authorWebsite;
    @OneToMany(mappedBy = "authorId")
    private List<Post> postList;

    public Author() {
    }

    public Author(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorPassword() {
        return authorPassword;
    }

    public void setAuthorPassword(String authorPassword) {
        this.authorPassword = authorPassword;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorWebsite() {
        return authorWebsite;
    }

    public void setAuthorWebsite(String authorWebsite) {
        this.authorWebsite = authorWebsite;
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
        hash += (authorId != null ? authorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Author)) {
            return false;
        }
        Author other = (Author) object;
        if ((this.authorId == null && other.authorId != null) || (this.authorId != null && !this.authorId.equals(other.authorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Author[ authorId=" + authorId + " ]";
    }

    public Blogadmin getBlogAdminId() {
        return blogAdminId;
    }

    public void setBlogAdminId(Blogadmin blogAdminId) {
        this.blogAdminId = blogAdminId;
    }

    public byte[] getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(byte[] authorImage) {
        this.authorImage = authorImage;
    }
    
}
