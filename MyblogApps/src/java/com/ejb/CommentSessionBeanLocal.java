/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Comment;
import com.entities.Post;
import com.entities.PostCommentTransfer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joseph
 */
@Local
public interface CommentSessionBeanLocal {

    Comment readComment(int commentsId);

    void createComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(int commentsId);

    List<Comment> getComments();
    List<Comment> getCommentForApproval();
    
    

    List<PostCommentTransfer> findCommentsByPostId(Post id);

    public List<Comment> findByProperty(String propertyName, Object value,
            int... rowStartIdxAndCount);

    public List<Comment> findByTitle(Object title, int... rowStartIdxAndCount);

    Long findCommentsCountPerPost(int id);
}
