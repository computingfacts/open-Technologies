/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Archive;
import com.entities.Post;
import com.entities.PostCategory;
import com.entities.PostCommentTransfer;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joseph
 */
@Local
public interface PostSessionBeanLocal {
    
    /**
	 * Perform an initial save of a previously unsaved Post entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPostDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Post entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Post entity);

	/**
	 * Delete a persistent Post entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPostDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Post entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(int entityId);

	/**
	 * Persist a previously saved Post entity and return it or a copy of it to
	 * the sender. A copy of the Post entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPostDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Post entity to update
	 * @return Post the persisted Post entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Post update(Post entity);

	public Post findById(Integer id);

	/**
	 * Find all Post entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Post property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Post> found by query
	 */
	public List<Post> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Post> findByTitle(Object title, int... rowStartIdxAndCount);

	public List<Post> findByContent(Object content, int... rowStartIdxAndCount);

	public List<Post> findByAuthorName(Object authorName,
			int... rowStartIdxAndCount);

	public List<Post> findByAllowComments(Object allowComments,
			int... rowStartIdxAndCount);

	//public List<Post> findByCategory(Object category,
			//int... rowStartIdxAndCount);
        
        List<Object[]> findPerCategory(String category);
        List<Object[]> findAllPost(int pageNumber, int resultsPerPage);
        
        //List<PostCommentTransfer> findSinglePost(String category);
        
        List<PostCategory> findPostsInCategory();
        List<Archive> findArchive();
        List<Post> findRelatedPost(Integer categoryId, String target);
        
//        Number findByCategoryCount(String category);
//        List<Post> findRelatedPost(String category);
        // List<Post> findCategory();

	/**
	 * Find all Post entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Post> all Post entities
	 */
	 List<Post> findAll(int... rowStartIdxAndCount);
         
        // List<Object[]> findAll();
        
        List<Post> searchPost(String input_param);
        
       // List<Object[]> findByDate();
        List<Object[]> findByMonth(String publishedDate);
        List<Post> findRecentPost();
        List<Object[]> findSinglePostObject(String title);
        
        
    

    
}
