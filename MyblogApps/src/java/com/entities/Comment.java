/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joseph
 */
@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByCommentsId", query = "SELECT c FROM Comment c WHERE c.commentsId = :commentsId"),
    @NamedQuery(name = "Comment.findByWriterName", query = "SELECT c FROM Comment c WHERE c.writerName = :writerName"),
    @NamedQuery(name = "Comment.findByWriterEmail", query = "SELECT c FROM Comment c WHERE c.writerEmail = :writerEmail"),
    @NamedQuery(name = "Comment.findByUrl", query = "SELECT c FROM Comment c WHERE c.url = :url"),
    @NamedQuery(name = "Comment.findByComment", query = "SELECT c FROM Comment c WHERE c.comment = :comment"),
    @NamedQuery(name = "Comment.findByApproved", query = "SELECT c FROM Comment c WHERE c.approved = :approved"),
    @NamedQuery(name = "Comment.findByPublishedDate", query = "SELECT c FROM Comment c WHERE c.publishedDate = :publishedDate")})
public class Comment implements Serializable {
    @Column(name =     "publishedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishedDate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
   // @NotNull
    @Column(name = "commentsId")
    private Integer commentsId;
    @Size(max = 45)
    @Column(name = "writerName")
    private String writerName;
    @Size(max = 45)
    @Column(name = "writerEmail")
    private String writerEmail;
    @Size(max = 500)
    @Column(name = "url")
    private String url;
    @Size(max = 500)
    @Column(name = "comment")
    private String comment;
    @Column(name = "approved")
    private Boolean approved;
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    @ManyToOne(optional = false)
    private Post postId;

    public Comment() {
    }

    public Comment(Integer commentsId) {
        this.commentsId = commentsId;
    }

    public Integer getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(Integer commentsId) {
        this.commentsId = commentsId;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriterEmail() {
        return writerEmail;
    }

    public void setWriterEmail(String writerEmail) {
        this.writerEmail = writerEmail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
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
        hash += (commentsId != null ? commentsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentsId == null && other.commentsId != null) || (this.commentsId != null && !this.commentsId.equals(other.commentsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Comment[ commentsId=" + commentsId + " ]";
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
    
}
