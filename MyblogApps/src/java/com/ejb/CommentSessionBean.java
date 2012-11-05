/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Comment;
import com.entities.Post;
import com.entities.PostCommentTransfer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Joseph
 */
@Stateless
public class CommentSessionBean implements CommentSessionBeanLocal {

    public static final String POSTID = "postId";
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Comment readComment(int commentId) {
        Comment comment = manager.find(Comment.class, commentId);
        return comment;
    }

    @Override
    public void createComment(Comment comment) {
        if (comment != null) {
            manager.persist(comment);
        }

    }

    @Override
    public void updateComment(Comment comment) {
        manager.merge(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment = manager.find(Comment.class, commentId);
        manager.remove(comment);
    }

    @Override
    public List<Comment> getComments() {
        return manager.createNamedQuery("Comment.findAll").getResultList();
    }
    
    @Override
    public List<Comment> getCommentForApproval(){
        
        Query query = manager.createQuery("SELECT c FROM Comment c WHERE c.approved = false ORDER BY c.publishedDate").setMaxResults(5);
        return query.getResultList();
    }

    @Override
    public List<PostCommentTransfer> findCommentsByPostId(Post id) {
      
        String queryString = "SELECT new com.entities.PostCommentTransfer(c,FUNC('MONTHNAME',c.publishedDate),FUNC('DAY',c.publishedDate),FUNC('YEAR',c.publishedDate)) FROM Comment c WHERE c.postId = :postId AND c.approved = true order by c.publishedDate desc ";
        Query query = manager.createQuery(queryString);
        query.setParameter("postId", id);

        return query.getResultList();
    }

    @Override
    public Long findCommentsCountPerPost(int id) {
        String queryString = "SELECT count(c) FROM Comment c WHERE c.postId = :postId order by c.publishedDate desc";
        Query query = manager.createQuery(queryString);
        query.setParameter("postId", id);

        return (Long) query.getSingleResult();
    }

    @Override
    public List<Comment> findByTitle(Object post_id, int... rowStartIdxAndCount) {
        return findByProperty(POSTID, post_id, rowStartIdxAndCount);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> findByProperty(String propertyName, final Object value,
            final int... rowStartIdxAndCount) {
        //EntityManagerHelper.log("finding Post instance with property: "
        //+ propertyName + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from Comment model where model."
                    + propertyName + "= :propertyValue order by model.publishedDate desc";
            Query query = manager.createQuery(queryString);
            query.setParameter("propertyValue", value);
            if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
                int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
                if (rowStartIdx > 0) {
                    query.setFirstResult(rowStartIdx);
                }

                if (rowStartIdxAndCount.length > 1) {
                    int rowCount = Math.max(0, rowStartIdxAndCount[1]);
                    if (rowCount > 0) {
                        query.setMaxResults(rowCount);
                    }
                }
            }
            return query.getResultList();
        } catch (RuntimeException re) {
            //EntityManagerHelper.log("find by property name failed",
            //Level.SEVERE, re);
            throw re;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
