/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joseph
 */
@Entity
@Table(name = "tags")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tags.findAll", query = "SELECT t FROM Tags t"),
    @NamedQuery(name = "Tags.findByTagId", query = "SELECT t FROM Tags t WHERE t.tagId = :tagId"),
    @NamedQuery(name = "Tags.findByTagLink", query = "SELECT t FROM Tags t WHERE t.tagLink = :tagLink"),
    @NamedQuery(name = "Tags.findByTagWeight", query = "SELECT t FROM Tags t WHERE t.tagWeight = :tagWeight"),
    @NamedQuery(name = "Tags.findByTagName", query = "SELECT t FROM Tags t WHERE t.tagName = :tagName")})
public class Tags implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tagWeight")
    private Double tagWeight;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
   // @NotNull
    @Column(name = "tagId")
    private Integer tagId;
    @Size(max = 100)
    @Column(name = "tagLink")
    private String tagLink;
    @Size(max = 100)
    @Column(name = "tagName")
    private String tagName;
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    @ManyToOne
    private Post postId;

    public Tags() {
    }

    public Tags(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagLink() {
        return tagLink;
    }

    public void setTagLink(String tagLink) {
        this.tagLink = tagLink;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagId != null ? tagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tags)) {
            return false;
        }
        Tags other = (Tags) object;
        if ((this.tagId == null && other.tagId != null) || (this.tagId != null && !this.tagId.equals(other.tagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Tags[ tagId=" + tagId + " ]";
    }

    public Double getTagWeight() {
        return tagWeight;
    }

    public void setTagWeight(Double tagWeight) {
        this.tagWeight = tagWeight;
    }
    
}
