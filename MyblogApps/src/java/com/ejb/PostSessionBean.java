/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Archive;
import com.entities.Post;
import com.entities.PostCategory;
import com.entities.PostCommentTransfer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author Joseph
 */
/**
 *
 * A data access object (DAO) providing persistence and search support for Post
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * @author Joseph
 */
@Stateless
public class PostSessionBean implements PostSessionBeanLocal {

    // property constants
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String AUTHOR_NAME = "authorName";
    public static final String ALLOW_COMMENTS = "allowComments";
    public static final String BLOG_CATEGORY = "category";
    @PersistenceContext
    private EntityManager manager;

//	private EntityManager getEntityManager() {
//		return EntityManagerHelper.getEntityManager();
//	}
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
     * PostDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            Post entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    @Override
    public void save(Post entity) {
        //EntityManagerHelper.log("saving Post instance", Level.INFO, null);
        try {
            manager.persist(entity);

            //EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            //EntityManagerHelper.log("save failed", Level.SEVERE, re);
            re.printStackTrace();
            //throw re;
        }
    }

    /**
     * Delete a persistent Post entity. This operation must be performed within
     * the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * PostDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            Post entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    @Override
    public void delete(int entityId) {
        //EntityManagerHelper.log("deleting Post instance", Level.INFO, null);

        try {
            Post entity = manager.find(Post.class, entityId);

            manager.remove(entity);
            //EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            //EntityManagerHelper.log("delete failed", Level.SEVERE, re);
        }
    }

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
     * entity = PostDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            Post entity to update
     * @return Post the persisted Post entity instance, may not be the same
     * @throws RuntimeException
     *             if the operation fails
     */
    @Override
    public Post update(Post entity) {
        //EntityManagerHelper.log("updating Post instance", Level.INFO, null);
        try {
            Post result = manager.merge(entity);
            //EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            //EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public Post findById(Integer id) {
        //EntityManagerHelper.log("finding Post instance with id: " + id,
        //Level.INFO, null);
        try {
            Post instance = manager.find(Post.class, id);
            return instance;
        } catch (RuntimeException re) {
            //EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

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
     *            number of results to return.
     * @return List<Post> found by query
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Post> findByProperty(String propertyName, final Object value,
            final int... rowStartIdxAndCount) {
        //EntityManagerHelper.log("finding Post instance with property: "
        //+ propertyName + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from Post model where model."
                    + propertyName + "= :propertyValue order by model.publishedTime desc";
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

    @Override
    public List<Post> findByTitle(Object title, int... rowStartIdxAndCount) {
        return findByProperty(TITLE, title, rowStartIdxAndCount);
    }

    @Override
    public List<Post> findByContent(Object content, int... rowStartIdxAndCount) {
        return findByProperty(CONTENT, content, rowStartIdxAndCount);
    }

    @Override
    public List<Post> findByAuthorName(Object authorName,
            int... rowStartIdxAndCount) {
        return findByProperty(AUTHOR_NAME, authorName, rowStartIdxAndCount);
    }

    @Override
    public List<Post> findByAllowComments(Object allowComments,
            int... rowStartIdxAndCount) {
        return findByProperty(ALLOW_COMMENTS, allowComments,
                rowStartIdxAndCount);
    }

    @Override
    public List<Post> findRecentPost() {
        Query query = manager.createQuery("select p from Post p order by p.publishedDate desc");

        query.setMaxResults(3);
        return query.getResultList();
    }

    @Override
    public List<Object[]> findByMonth(String publishedDate) {

        String query = " SELECT p.*,att.authorName, ifnull(c.count,0) AS comment,DATE_FORMAT(p.`publishedDate`, '%W , %D %M %Y') FROM post AS p LEFT JOIN (SELECT * from author) AS att ON p.`authorId` = att.`authorId` LEFT JOIN (SELECT COUNT(comment.`commentsId`) AS count, comment.`postId` FROM comment WHERE comment.approved = true GROUP BY comment.`postId`) AS c ON p.`postId` = c.postId where  MONTHNAME(p.`publishedDate`) = '" + publishedDate + "' ORDER BY p.`publishedDate`DESC";
        return manager.createNativeQuery(query).getResultList();

    }

    @Override
    public List<Archive> findArchive() {
        //String query = "select new com.entities.Archive(FUNC('MONTHNAME',p.publishedDate),FUNC('YEAR',p.publishedDate),count(p)) from Post p GROUP by FUNC('MONTHNAME',p.publishedDate), FUNC('YEAR',p.publishedDate) ORDER BY FUNC('MONTHNAME',p.publishedDate) desc";
         String query = "select new com.entities.Archive(FUNC('MONTHNAME',p.publishedDate),FUNC('YEAR',p.publishedDate),count(p)) from Post p GROUP by FUNC('MONTHNAME',p.publishedDate), FUNC('YEAR',p.publishedDate) ORDER BY p.publishedDate desc";
        return manager.createQuery(query, Archive.class).getResultList();
    }

    @Override
    public List<Object[]> findPerCategory(String category) {

        String query = "SELECT p.*,att.authorName, ifnull(c.count,0) AS comment,cg.`blogCategory`,DATE_FORMAT(p.`publishedDate`, '%W , %D %M %Y') FROM post AS p left join blogcategory cg on p.`blogCategoryId` =cg.`blogCategoryId` LEFT JOIN (SELECT * from author) AS att ON p.`authorId` = att.`authorId` LEFT JOIN (SELECT COUNT(comment.`commentsId`) AS count, comment.`postId` FROM comment WHERE comment.approved = true GROUP BY comment.`postId`) AS c ON p.`postId` = c.postId where cg.`blogCategory` =  '" + category + "'  ORDER BY p.`publishedDate`DESC";
        return manager.createNativeQuery(query).getResultList();

    }

    @Override
    public List<PostCategory> findPostsInCategory() {
        String query = "select new com.entities.PostCategory(c.blogCategory,count(p)) from Post p LEFT JOIN P.blogCategoryId c GROUP BY c.blogCategory";

        return manager.createQuery(query, PostCategory.class).getResultList();
    }

    @Override
    public List<Object[]> findAllPost(int pageNumber, int resultsPerPage) {
        List<Object[]> posts = new  ArrayList<Object[]>();
        String query = "SELECT p.*,att.authorName, ifnull(c.count,0) AS comment,cg.blogCategory,DATE_FORMAT(p.`publishedDate`, '%W , %D %M %Y') FROM post AS p left join blogcategory cg on p.`blogCategoryId` =cg.`blogCategoryId` LEFT JOIN (SELECT * from author) AS att ON p.`authorId` = att.`authorId` LEFT JOIN (SELECT COUNT(comment.`commentsId`) AS count, comment.`postId` FROM comment WHERE comment.approved = true GROUP BY comment.`postId`) AS c ON p.`postId` = c.postId  ORDER BY p.`publishedDate`DESC";

        if(pageNumber <0 ){
            pageNumber = 0;
            
        }
        
        posts = manager.createNativeQuery(query).setFirstResult(pageNumber * resultsPerPage).setMaxResults(resultsPerPage).getResultList();
        if(posts.isEmpty()){
            pageNumber = 0;
            posts = manager.createNativeQuery(query).setFirstResult(pageNumber * resultsPerPage).setMaxResults(resultsPerPage).getResultList(); 
       }
        return posts;
    }

    @Override
    public List<Object[]> findSinglePostObject(String title) {
        List<Object[]> list = null;
        String query = "SELECT p.*,att.authorName, ifnull(c.count,0) AS comment,DATE_FORMAT(p.`publishedDate`, '%W , %D %M %Y') FROM post AS p LEFT JOIN (SELECT * from author) AS att ON p.`authorId` = att.`authorId` LEFT JOIN (SELECT COUNT(comment.`commentsId`) AS count, comment.`postId` FROM comment WHERE comment.approved = true GROUP BY comment.`postId`) AS c ON p.`postId` = c.postId where  p.title = '" + title + "' GROUP BY p.`postId`";
       
        list = manager.createNativeQuery(query).setMaxResults(1).getResultList();
        if(list.isEmpty()){
           // throw new NullPointerException("Wrong Value");
            Object [] obj = new Object[6];
            obj[0] = 0;
            obj[1] = "no data found";
            obj[2] = "NO POST FOUND";
            obj[3] = "no data found";
            obj[4] = "no data found";
            obj[5] = 0;
            list.add(obj);
           // return list;
            return new ArrayList<Object[]>();
        }
        return list;
    }

    @Override
    public List<Post> findRelatedPost(Integer categoryId, String target) {

       // String query = "SELECT p.*,att.authorName, ifnull(c.count,0) AS comment,cg.`blogCategory`,DATE_FORMAT(p.`publishedDate`, '%W , %D %M %Y') FROM post AS p left join blogCategory cg on p.`blogCategoryId` =cg.`blogCategoryId` LEFT JOIN (SELECT * from author) AS att ON p.`authorId` = att.`authorId` LEFT JOIN (SELECT COUNT(comment.`commentsId`) AS count, comment.`postId` FROM comment WHERE comment.approved = true GROUP BY comment.`postId`) AS c ON p.`postId` = c.postId where cg.`blogCategoryId` =  '" + categoryId + "'  ORDER BY p.`publishedDate`DESC";
         //String queryString = "select new com.entities.PostCategory(c.blogCategory,c.postList) from Post p LEFT JOIN P.blogCategoryId c WHERE c.blogCategoryId = :blogCategoryId GROUP BY c.blogCategoryId";
          String queryString = "select p from Post p JOIN P.blogCategoryId c WHERE c.blogCategoryId = :blogCategoryId";
         Query query = manager.createQuery(queryString);
         query.setParameter("blogCategoryId", categoryId);
       // List data = manager.createNativeQuery(query).getResultList();

        
        return query.getResultList();

    }

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
    @SuppressWarnings("unchecked")
    @Override
    public List<Post> findAll(final int... rowStartIdxAndCount) {
        //EntityManagerHelper.log("finding all Post instances", Level.INFO, null);
        try {
            final String queryString = "select model from Post model order by model.publishedDate desc";
            Query query = manager.createQuery(queryString);
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
            //EntityManagerHelper.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public List<Post> searchPost(String input_param) {

        // Task : search the customer and retrieve customers who name starts with Jo


        //obtain an instance of the class implementing the criteriaBuilder interface
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        //invoking the createQuery method in criteriabuilder implementation and passing a generic type
        // argument that would be returned upon execution
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);

        //root interface dictates what JPA entity we will be quering from (similar to FROM query in JPQL or SQL)
        Root<Post> root = criteriaQuery.from(Post.class);


        //getting the metamodel of the entity manager
        Metamodel metamodel = manager.getMetamodel();

        //the entity type enable us to browse the persistent attributes of the JPA entities at runtime
        EntityType<Post> customerEntityType = metamodel.entity(Post.class);

        // obtaining the entity involved and the related attributes
        SingularAttribute<Post, String> customerAttribute = customerEntityType.getDeclaredSingularAttribute("content", String.class);


        // passing the instance of the singular attribute to the path by invoking the get method of root instance
        Path<String> path = root.get(customerAttribute);

        // returns the implementation of the predicate inteface (equals(), greaterThan, lessThan, and(), or() etc can be used as well to create queries
        // Predicate predicate = criteriaBuilder.like(path, "Jo%");
        Predicate predicate = criteriaBuilder.like(path, "% " + input_param + " %");

        //passing the predicate as a parameter to the where() method in criteriaQuery
        criteriaQuery = criteriaQuery.where(predicate);

        //obtaining instance of type query by invoking the createQuery method of the entitymanager
        TypedQuery typedQuery = manager.createQuery(criteriaQuery);

        //returning the query result as a list
        return typedQuery.getResultList();
    }
}
