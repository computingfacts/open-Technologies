/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.ejb.BlogCategorySessionBeanLocal;
import com.ejb.CommentSessionBeanLocal;
import com.ejb.PostSessionBeanLocal;
import com.entities.Archive;
import com.entities.Blogcategory;
import com.entities.Comment;
import com.entities.Post;
import com.entities.PostCategory;
import com.entities.PostCommentTransfer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Joseph
 */
public class PostController {
    
    CommentSessionBeanLocal commentsSessionBean = null;
    BlogCategorySessionBeanLocal categorySessionBean = null;
    PostSessionBeanLocal postSessionBean = null;
    private Post post;
    private List<Object[]> postList;
    private Blogcategory categories;
    private List<Blogcategory> categoriesList;
    private List<Object[]> postByCategory;
    private List<PostCommentTransfer> singlePost;
    private List<Object[]> singlePostObject;
    private Comment comment;
    private List<PostCommentTransfer> commentPerPost;
    private List<Object[]> categoryListing;
    private String categoryObject = null;
    private PostCategory pc;
    private List<PostCategory> pcList;
    private String param;
    private List<Archive> postArchive;
    private List<Object[]> postPerMonth;
    private List<Post> recentPost;
    private List<Post> relatedPost;
    
    public PostController() {
        post = new Post();
        categoryListing = new ArrayList<Object[]>();
        singlePost = new ArrayList<PostCommentTransfer>();
        postArchive = new ArrayList<Archive>();
        postPerMonth = new ArrayList<Object[]>();
        recentPost = new ArrayList<Post>();
        relatedPost = new ArrayList<Post>();
        singlePostObject = new ArrayList<Object[]>();
        
        try {
            pc = new PostCategory();
            pcList = new ArrayList<PostCategory>();
            postList = new ArrayList<Object[]>();
            postByCategory = new ArrayList<Object[]>();
            postSessionBean = lookupPostSessionBeanLocal();
        } catch (Exception e) {
        }
        categories = new Blogcategory();
        try {
            categoriesList = new ArrayList<Blogcategory>();
            categorySessionBean = lookupCategorySessionBeanLocal();
        } catch (Exception e) {
        }
        
        
        try {
            comment = new Comment();
            commentPerPost = new ArrayList<PostCommentTransfer>();
            commentsSessionBean = lookupCommentsSessionBeanLocal();
            
        } catch (Exception e) {
        }
        
    }
    
    public Post getPost() {
        return post;
    }
    
    public void setPost(Post post) {
        this.post = post;
    }
    
    public Blogcategory getCategories() {
        return categories;
    }
    
    public void setCategories(Blogcategory categories) {
        this.categories = categories;
    }
    
    public void findPost() {
        post = postSessionBean.findById(post.getPostId());
    }
    
    public void createPost() {
        
        System.out.println("INSERTING.." + post);
        postSessionBean.save(post);
        post = new Post();
    }
    
    public void updatePost() {
        postSessionBean.update(post);
        post = new Post();
    }
    
    public void deletePost() {
        postSessionBean.delete(post.getPostId());
        post = new Post();
        
    }
    
    public PostCategory getPc() {
        return pc;
    }
    
    public void setPc(PostCategory pc) {
        this.pc = pc;
    }
    
    public String getParam() {
        return param;
    }
    
    public void setParam(String param) {
        this.param = param;
    }
    
    public List<Archive> getPostArchive() {
        postArchive = postSessionBean.findArchive();
        for (Archive a : postArchive) {
            // System.out.println("Archive "+ a.getMonthName() + ""+ a.getTheYear()+ " "+ a.getCount());
        }
        return postArchive;
    }
    
    public List<Object[]> getPostPerMonth() {
        postPerMonth = postSessionBean.findByMonth(param);
//
       for(Object[] p : postPerMonth){
            System.out.println("POST PER MONTH =  "+ p[0] + ","+ p[8] + ","+ p[9]);
        }
        return postPerMonth;
    }
    
    public List<Post> getRecentPost() {
        recentPost = postSessionBean.findRecentPost();
//        for(Post p : recentPost){
//            System.out.println("Recent : "+ p);
//        }
        return recentPost;
    }
    
    public List<Post> getRelatedPost() {
       // relatedPost = postSessionBean.findByCategory(param);
        return relatedPost;
    }
    
    public List<PostCategory> getPcList() {
        pcList = postSessionBean.findPostsInCategory();
        
        return pcList;
    }

    /**
     * 
     * @return all post
     */
    public List<Object[]> getPostList() {
        
       // postList = postSessionBean.findAllPost();

        // for(Post p : postList){
        // System.out.println("dateee"+ p);
        // }
        return postList;
    }
    
    public String getCategoryObject() {
        
        
        return categoryObject;
    }
    
    public List<Object[]> getCategoryListing() {
        Object obj = null;
        
        //categoryListing = postSessionBean.findAll();
        
        for (Object[] ob : categoryListing) {
            obj = ob[0];
            obj.toString();
            
            
        }
        
        System.out.println(" Object : " + categoryObject);

        // System.out.println("First Object : "+ obj);
        // categoryObject = obj.toString();

        // System.out.println("my object Object : "+ categoryObject); 
        return categoryListing;
    }

    /**
     * 
     * @return all categories
     */
    public List<Blogcategory> getCategoriesList() {
        // List<Object[]> obj2 = postSessionBean.getObject();
        // categoriesList = categorySessionBean.getCategories();
//        List<Object[]> pp = postSessionBean.findAll();
//        for (Object[] result : pp) {
//            //System.out.println("NEW PPS"+ result);
//
//            System.out.println("object 1: " + result[0] + ", object 2: " + result[1]);
//        }
        categoriesList = categorySessionBean.getCategories();
        // List<Object[]> obj = categorySessionBean.getObject();


//           for (Object[] result : obj) {
//            System.out.println("object 1: " + result[0] + ", object 2: " + result[1]);
//        }
//           
//                for (Object[] result2 : obj2) {
//            System.out.println("object post: " + result2[0] + ", object post: " + result2[1]);
//        }
//           
//        System.out.println("ALL CAT "+ obj);
        // System.out.println("ALL CAT POST "+ categoriesList);

        
        return categoriesList;
    }
    
    public List<Object[]> getPostByCategory() {
        // System.out.println("CATEGORIES PARAMS " + param);
        // System.out.println("CATEGORIES another "+ post.getCategory());
       // postByCategory = postSessionBean.findPerCategory(category);
        // Number n = postSessionBean.findByCategoryCount("");
        //  System.out.println("COUNTER "+ n);
        for (Object[] p : postByCategory) {
            System.out.println("CATEGORIES RESULT " + p[1]);
            System.out.println("CATEGORIES RESULT " + p[2]);
            System.out.println("CATEGORIES RESULT " + p[3]);
            System.out.println("CATEGORIES RESULT " + p[4]);
            System.out.println("CATEGORIES RESULT " + p[9]);
            System.out.println("CATEGORIES RESULT " + p[10]);
            
        }
        
        return postByCategory;
    }
    
    public List<PostCommentTransfer> getSinglePost() {
        System.out.println("SINGLE POST " + post.getTitle());
        // singlePost = postSessionBean.findSinglePost(post.getTitle());
        //singlePost = postSessionBean.findSinglePost(post.getTitle());
        System.out.println("RESULT " + singlePost);
//       List< Object[]> obj = postSessionBean.findSinglePostObject(post.getTitle());
//       for(Object[] ob : obj){
//           System.out.println("MY OBJECTS "+ ob[0] + ob[1]);
//       }
        for (PostCommentTransfer p : singlePost) {
            System.out.println("SINGLE POST RESULT  " + p.getCount());
            // System.out.println("SINGLE POST RESULT  " + p.getPost().getTitle() +" ,"+ p.getComment().getWriterName() + " "+ p.getCount()); 
        }
        //System.out.println("SINGLE POST RESULT  " + singlePost1);
        return singlePost;
    }

    /**to get a single post with number of comments
     * 
     * @return 
     */
    public List<Object[]> getSinglePostObject() {
        singlePostObject = postSessionBean.findSinglePostObject(post.getTitle());
        
        return singlePostObject;
    }
    
    /**
     * creating a comment
     */
    public void createComment() {
       
        Object[] postObject = null;
        // singlePost = postSessionBean.findByTitle(post.getTitle(), 0);
        singlePostObject = postSessionBean.findSinglePostObject(post.getTitle());
        for (Object[] obj : singlePostObject) {
            postObject = obj;
        }
       // System.out.println("THE POST TO COMMENT ON " + postObject[0].toString());
       // System.out.println("IS COMMENT ALLOWED " + postObject[9].toString());
        
        if (postObject[9].equals(true)) {
            Integer pid = Integer.parseInt(postObject[0].toString());
           // System.out.println("The COMMENT ITSELF " + comment.getWriterEmail());
            //comment.setPostId(pid);
           
            comment.setPublishedDate(new Date());
            
            commentsSessionBean.createComment(comment);
            comment = new Comment();
        } else {
            System.out.println("NO COMMENTS ALLOWED");
        }

    }
    
    /**comment per post
     * 
     * @return 
     */
    public List<PostCommentTransfer> getCommentPerPost() {
        // singlePost = postSessionBean.findByTitle(post.getTitle(), 0);
        singlePostObject = postSessionBean.findSinglePostObject(post.getTitle());
        for (Object[] postComment : singlePostObject) {
            Integer pid = Integer.parseInt(postComment[0].toString());

            commentPerPost = commentsSessionBean.findCommentsByPostId(post);
            for(PostCommentTransfer c : commentPerPost ){
                System.out.println("COMMENT PER POST " + c.getMonth() + c.getDateFormat());
            }

        }

        
        return commentPerPost;
    }
    
    public Comment getComment() {
        return comment;
    }
    
    public void setComment(Comment comment) {
        this.comment = comment;
    }
    
    private PostSessionBeanLocal lookupPostSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (PostSessionBeanLocal) c.lookup("java:global/MyblogApps/PostSessionBean!com.ejb.PostSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private BlogCategorySessionBeanLocal lookupCategorySessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (BlogCategorySessionBeanLocal) c.lookup("java:global/MyblogApps/CategorySessionBean!com.ejb.CategorySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private CommentSessionBeanLocal lookupCommentsSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CommentSessionBeanLocal) c.lookup("java:global/MyblogApps/CommentsSessionBean!com.ejb.CommentsSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
