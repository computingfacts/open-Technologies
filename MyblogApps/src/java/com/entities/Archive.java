/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *this is a transfer object class to enable entity retrieval during JPQL query
 * @author Joseph
 */
@Entity
public class Archive implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date theMonth;
    private Number count;
    private String monthName;
    private Number theYear;

    public Archive() {
    }

    public Archive(String mon, Number year, Number count) {
        this.monthName = mon;
        this.theYear = year;
        this.count = count;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }

    public Date getTheMonth() {
        return theMonth;
    }

    public void setTheMonth(Date theMonth) {
        this.theMonth = theMonth;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Number getTheYear() {
        return theYear;
    }

    public void setTheYear(Number theYear) {
        this.theYear = theYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Archive)) {
            return false;
        }
        Archive other = (Archive) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Archive[ id=" + id + " ]";
    }
}
