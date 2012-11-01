/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Joseph
 */
//@Entity
public class PostCommentTransfer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @OneToOne
    private Post post;
    private Number count;
    @OneToOne
    private Comment comment;
    
    private String month;
    private Number day;
    private Number year;
    
    private String dateFormat;
    
    public PostCommentTransfer(){
        
    }

    public PostCommentTransfer(Post post, Number count) {
        this.post = post;
        this.count = count;
    }

    public PostCommentTransfer(Post post, Number count, Comment comment){
        this.post = post;
        this.count = count;
        this.comment = comment;
    }

    public PostCommentTransfer(Comment comment, String month, Number day, Number year) {
        this.comment = comment;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public PostCommentTransfer(Comment comment, String dateFormat) {
        this.comment = comment;
        this.dateFormat = dateFormat;
    }

 
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }



    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Number getDay() {
        return day;
    }

    public void setDay(Number day) {
        this.day = day;
    }

    public Number getYear() {
        return year;
    }

    public void setYear(Number year) {
        this.year = year;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }


    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PostCommentTransfer other = (PostCommentTransfer) obj;
        if (this.post != other.post && (this.post == null || !this.post.equals(other.post))) {
            return false;
        }
        if (this.comment != other.comment && (this.comment == null || !this.comment.equals(other.comment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PostCommentTransfer{" + "id=" + id + ", post=" + post + ", count=" + count + ", comment=" + comment + ", month=" + month + ", day=" + day + ", year=" + year + '}';
    }


    
}
