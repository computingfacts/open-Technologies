/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.ejb.AuthorSessionBeanLocal;
import com.ejb.BlogCategorySessionBeanLocal;
import com.ejb.CommentSessionBeanLocal;
import com.ejb.MessageSessionBeanLocal;
import com.ejb.PostSessionBeanLocal;
import com.ejb.TagsSessionBeanLocal;
import com.entities.Archive;
import com.entities.Author;
import com.entities.Blogcategory;
import com.entities.Comment;
import com.entities.Message;
import com.entities.Post;
import com.entities.PostCategory;
import com.entities.PostCommentTransfer;
import com.entities.Tags;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Cloud.Case;
import org.mcavallo.opencloud.Tag;
import org.mcavallo.opencloud.filters.DictionaryFilter;
import org.mcavallo.opencloud.filters.Filter;

/**
 *
 * @author Joseph
 */
public class BlogController {

    MessageSessionBeanLocal messageSessionBean = null;
    private TagsSessionBeanLocal tagsSessionBean = null;
    private AuthorSessionBeanLocal authorSessionBean = null;
    private PostSessionBeanLocal postSessionBean = null;
    private CommentSessionBeanLocal commentSessionBean = null;
    private BlogCategorySessionBeanLocal blogCategorySessionBean = null;
    private Blogcategory category;
    private List<Blogcategory> categoryList;
    private Comment comment;
    private List<Comment> commentList;
    private Post post;
    private List<Post> postList;
    private List<Post> recentPostList;
    private List<Object[]> postsPerMonth;
    private List<Archive> postArchive;
    private List<Object[]> postByCategory;
    private List<PostCategory> postsInCategory;
    private List<Object[]> approvedPostList;
    private List<PostCommentTransfer> commentPerPost;
    private List<Object[]> singlePostObject;
    private List<Post> relatedPost;
    private Author author;
    private Set<Author> authorList;
    private Integer postCategoryId;
    private Integer postAuthorId;
    private String selected_month;
    private String selected_category;
    private String selected_title;
    private Tags tagCloud;
    private List<Tags> tagList;
    private final Double MAX_WEIGHT = 38.0;
    private final Integer MAX_DISPLAY = 8;
    private final Filter<Tag> filter = new DictionaryFilter(ResourceBundle.getBundle("com.controller.dictionary_blacklist"));
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random random = null;
    private String randomString;
    private String randomInput;
    private Message message;
    private List<Message> messageList;
    
   

    public BlogController() {
        random = new Random();
        category = new Blogcategory();
        categoryList = new ArrayList<Blogcategory>();
        try {
            blogCategorySessionBean = lookupBlogCategorySessionBeanLocal();
        } catch (Exception e) {
            //log
        }

        comment = new Comment();
        commentList = new ArrayList<Comment>();
        try {
            commentSessionBean = lookupCommentSessionBeanLocal();
        } catch (Exception e) {
            //log
        }

        post = new Post();
        postList = new ArrayList<Post>();
        recentPostList = new ArrayList<Post>();
        postsPerMonth = new ArrayList<Object[]>();
        postArchive = new ArrayList<Archive>();
        postByCategory = new ArrayList<Object[]>();
        postsInCategory = new ArrayList<PostCategory>();
        relatedPost = new ArrayList<Post>();
        commentPerPost = new ArrayList<PostCommentTransfer>();
        singlePostObject = new ArrayList<Object[]>();
        try {
            postSessionBean = lookupPostSessionBeanLocal();
        } catch (Exception e) {
        }

        author = new Author();
        authorList = new HashSet<Author>();
        try {
            authorSessionBean = lookupAuthorSessionBeanLocal();
        } catch (Exception e) {
        }

        try {
            tagCloud = new Tags();
            tagList = new ArrayList<Tags>();
            tagsSessionBean = lookupTagsSessionBeanLocal();
        } catch (Exception e) {
        }

        try {
            message = new Message();
            messageList = new ArrayList<Message>();
            messageSessionBean = lookupMessageSessionBeanLocal();
        } catch (Exception e) {
        }
    }

    //////////CATEGORY///////////////////////////////////////////////////////////////////////////////////
    public Blogcategory getCategory() {
        return category;
    }

    /**
     * to find a category
     */
    public void findBlogCategory() {
        category = blogCategorySessionBean.readCategory(category.getBlogCategoryId());
    }

    /**
     * create a blog category
     */
    public void createBlogCategory() throws Exception {
        try {
            blogCategorySessionBean.createCategory(category);
            category = new Blogcategory();
        } catch (Exception e) {
            //Todo
            // handle duplicate data exception
        }
    }

    /**
     * update the blog category
     */
    public void updateBlogCategory() {
        if (category != null) {
            blogCategorySessionBean.updateCategory(category);
            category = new Blogcategory();
        }
    }

    /**
     * delete a category
     */
    public void deleteBlogCategory() {
        try {
            blogCategorySessionBean.deleteCategory(category.getBlogCategoryId());
            category = new Blogcategory();
        } catch (Exception ex) {
            // Todo
        }
    }

    /**
     * 
     * @return list of categories
     */
    public List<Blogcategory> getCategoryList() {

        categoryList = blogCategorySessionBean.getCategories();

        return categoryList;
    }

    //////////////////////////////////////////////random generator/////////////////
    public String getRandomString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            buffer.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        randomString = buffer.toString();

        return randomString;// 7random string would be generated
    }

    public String getRandomInput() {
        return randomInput;
    }

    public void setRandomInput(String randomInput) {
        this.randomInput = randomInput;
    }

    ///////////////////////////////end of random//////////////////////////
    
    
    ////////////COMMENT////////////////////////////////////////////////////////////////////////////////////////////////
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    /**
     * to find a comment
     */
    public void findComment() {

        comment = commentSessionBean.readComment(comment.getCommentsId());
    }

    /**
     * creating a comment
     */
    public void createComment() {
        Object[] allowComment = null;
        singlePostObject = postSessionBean.findSinglePostObject(post.getTitle());
        for (Object[] thePost : singlePostObject) {
            allowComment = thePost;

        }
        //if the comment is allow, then create a comment else return error message
        if (allowComment[4].equals(true)) {

            comment.setPostId(post);
            comment.setPublishedDate(new Date());
            comment.setApproved(false);
            if (randomInput.equalsIgnoreCase(randomString)) {
                if (comment != null) {
                    // System.out.print("The Comment " + comment);
                    commentSessionBean.createComment(comment);
                    comment = new Comment();
                }
            } else {
                //TODO
                // RETURN ERROR
                System.out.println("PLEASE ENTER THE DISPLAYED LETTERS");
            }
        } else {
            System.out.println("NO COMMENT ALLOWED FOR THIS POST");
        }
    }

    /**
     * update the blog comment
     */
    public void updateComment() {

        try {
            if (comment != null) {
                commentSessionBean.updateComment(comment);
                comment = new Comment();
            }
        } catch (Exception e) {
            //log
            // e.printStackTrace();
        }

    }

    /**
     * delete a comment
     */
    public void deleteComment() {
        try {
            commentSessionBean.deleteComment(comment.getCommentsId());
            comment = new Comment();

        } catch (Exception ex) {
            // Todo
        }
    }

    public List<Comment> getCommentList() {
        commentList = commentSessionBean.getCommentForApproval();
        return commentList;
    }
    /////////////////////////////////////////AUTHOR/////////////////////////////////////////////////////////

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * to find an author
     */
    public void findAuthor() {


        author = authorSessionBean.readAuthor(author.getAuthorId());
    }

    /**
     * update an author's information
     */
    public void updateAuthor() {

        authorSessionBean.updateAuthor(author);
        author = new Author();

    }

    /**
     * delete an author
     */
    public void deleteAuthor() {
        try {
            authorSessionBean.deleteAuthor(author.getAuthorId());
            author = new Author();

        } catch (Exception ex) {
            // Todo
        }
    }

    public Set<Author> getAuthorList() {
        authorList = authorSessionBean.getAuthors();
        return authorList;
    }

    /////////////////////////////////POST////////////////////////////////////////
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * to find an a Post
     */
    public void findPost() {

        post = postSessionBean.findById(post.getPostId());
    }

    /**
     * update a Post
     */
    public void updatePost() {
        Date d = null;
        if (post != null) {
            d = post.getPublishedDate();//just experimenting..delete later
            post.setPublishedDate(d);

            postSessionBean.update(post);
            post = new Post();
        }

    }

    /**
     * delete a Post
     */
    public void deletePost() {
        try {

            postSessionBean.delete(post.getPostId());
            post = new Post();

        } catch (Exception ex) {
            // Todo
        }
    }

    /**
     * 
     * @return  all post
     */
    public List<Post> getPostList() {

        postList = postSessionBean.findAll(0);
        return postList;
    }

    /**
     * create a new post
     */
    public void createPost() {

        category.setBlogCategoryId(postCategoryId);
        author.setAuthorId(postAuthorId);

        post.setBlogCategoryId(category);
        post.setAuthorId(author);

        post.setPublishedDate(new Date());

        postSessionBean.save(post);
        this.createTagCloud(post);
        post = new Post();
        getTagList();

    }

    /**
     * 
     * @return most recent posts
     */
    public List<Post> getRecentPostList() {
        recentPostList = postSessionBean.findRecentPost();
        return recentPostList;
    }

    /**
     * 
     * @return all posts in the selected month
     */
    public List<Object[]> getPostsPerMonth() {
        postsPerMonth = postSessionBean.findByMonth(selected_month);


        return postsPerMonth;
    }

    /**
     * 
     * @return number of post per month
     */
    public List<Archive> getPostArchive() {
        postArchive = postSessionBean.findArchive();
        return postArchive;
    }

    /**
     * 
     * @return all post in this category
     */
    public List<Object[]> getPostByCategory() {
        postByCategory = postSessionBean.findPerCategory(selected_category);
       // for (Object[] obj : postByCategory) {
            // System.out.println("Returned : " + obj[1] + ""+ obj[2] + ""+ obj[3] + " "+ obj[4]);
            // System.out.println("Returned : " + obj[5] + ", "+ obj[6] + ", "+ obj[7] + ", "+ obj[8]);
            // System.out.println("Returned : " + obj[9] + ", " + obj[10] + ", " + obj[11]);
       // }

        return postByCategory;
    }

    /**
     * 
     * @return number of post in each category
     */
    public List<PostCategory> getPostsInCategory() {
        postsInCategory = postSessionBean.findPostsInCategory();


        return postsInCategory;
    }
///////////////////////////////////////////////pagination///////////////////
    private int pageNumber;
    private int resultsPerPage = 3;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
    ////////////////////////////////////////////////////

    /**
     * 
     * @return all post with number of approved comments
     */
    public List<Object[]> getApprovedPostList() {
        approvedPostList = postSessionBean.findAllPost(pageNumber, resultsPerPage);
        if (approvedPostList.isEmpty()) {
            // pageNumber = 0;
            approvedPostList = postSessionBean.findAllPost(pageNumber, resultsPerPage);
        }
        //for (Object[] obj : approvedPostList) {
            // System.out.println("Returned : " + obj[1] + ""+ obj[2] + ""+ obj[3] + " "+ obj[4]);
            // System.out.println("Returned : " + obj[5] + ", "+ obj[6] + ", "+ obj[7] + ", "+ obj[8]);
            //System.out.println("Returned : " + obj[9] + ", " + obj[10] + ", " + obj[11]);
        //}
        return approvedPostList;
    }

    public List<PostCommentTransfer> getCommentPerPost() {

        singlePostObject = postSessionBean.findSinglePostObject(post.getTitle());
        for (Object[] postComment : singlePostObject) {
            Integer pid = Integer.parseInt(postComment[0].toString());
            post.setPostId(pid);
            commentPerPost = commentSessionBean.findCommentsByPostId(post);


        }

        return commentPerPost;
    }

    public List<Object[]> getSinglePostObject() {
       // System.out.println("The title " + post.getTitle());
        String params = post.getTitle().replaceAll("%20", " ");
        try{
        
        singlePostObject = postSessionBean.findSinglePostObject(params);
        //System.out.println("Single " + params);
        }catch(Exception e){
            
        }
//        for (Object[] obj : singlePostObject) {
//            System.out.println("Single Object  " + obj[0] + "" + obj[1]);
//        }


        return singlePostObject;
    }

    public List<Post> getRelatedPost() {
        Integer categoryId = null;
        // System.out.println("selected one "+ selected_title);
        try {
            singlePostObject = postSessionBean.findSinglePostObject(selected_title);
        } catch (Exception e) {
        }
        for (Object[] obj : singlePostObject) {
            categoryId = Integer.parseInt(obj[5].toString());

        }
        // relatedPost = postSessionBean.findRelatedPost(categoryId,post.getTitle());
        relatedPost = postSessionBean.findRelatedPost(categoryId, selected_title);

        Post target = null;
        for (int x = 0; x < relatedPost.size(); x++) {
            // for(Post p : relatedPost){
            // System.out.println("This is it"+ p.getTitle() + " post"+ p.getBlogCategoryId() );
            target = relatedPost.get(x);
            //System.out.println("selected two "+ selected_title);
            //target.setTitle(selected_title);
            if (target.getTitle().equalsIgnoreCase(selected_title)) {
                // System.out.println("true" + relatedPost.contains(target));
                if (relatedPost.contains(target)) {
                    relatedPost.remove(target);
                } else {
                    return relatedPost;
                }
            }
        }
//        for(Post p2 : relatedPost){
//            System.out.println("after removal "+ p2 + " :"+ p2.getTitle());
//        }
//       


        return relatedPost;
    }

    /////////////////////////////Tag Cloud //////////////////////////////////////////
    public Tags getTagCloud() {
        return tagCloud;
    }

    public void setTagCloud(Tags tagCloud) {
        this.tagCloud = tagCloud;
    }

    public List<Tags> getTagList() {

        tagList = tagsSessionBean.getTags();

        return tagList;
    }

    private void createTagCloud(Post tagPost) {


        // String        link = "www.google.com/?title=%s";
        // String link = String.format("http://localhost:8080/MyblogApps/blog.jsp?title=%s", tagPost.getTitle());
        Cloud cloud = new Cloud(Case.PRESERVE_CASE, Locale.ENGLISH);
        cloud.setMaxWeight(MAX_WEIGHT);
        cloud.setTagCase(Case.PRESERVE_CASE);
        // cloud.setDefaultLink("http://www.flickr.com/photos/tags/%s/");
        cloud.setMaxTagsToDisplay(MAX_DISPLAY);


        cloud.addInputFilter(filter);
        // System.out.println("spliting : "+ tagPost.getContent().split(">"));

        cloud.addText(tagPost.getContent(), tagPost.getTitle());
        cloud.addInputFilter(filter);
       // cloud.setThreshold(0.5);
        cloud.tags(new Tag.NameComparatorAsc());


        cloud.addOutputFilter(filter);
        // cloud.setTagLifetime(100l);


        for (Tag tag : cloud.tags()) {

           // System.out.println(tag.getLink() + " : " + tag.getWeight() + ":  " + tag.getName());
            cloud.addOutputFilter(filter);
            tagCloud.setTagLink(tag.getLink());
            tagCloud.setTagWeight(tag.getWeight());
            tagCloud.setTagName(tag.getName());
            tagCloud.setPostId(tagPost);
            tagsSessionBean.createTags(tagCloud);
            tagCloud = new Tags();

        }


    }

    ////////////////////////messages/////////////////
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void createMessage() {
       // System.out.println("The message 1 " + message);
        if (message != null) {
            message.setMessageDate(new Date());
           // System.out.println("The message " + message);
            if (randomInput.equalsIgnoreCase(randomString)){
            messageSessionBean.createMessage(message);

            message = new Message();
            }else{
                //set error message
            }

        }
    }

    public List<Message> getMessageList() {
        messageList = messageSessionBean.getMessages();
        return messageList;
    }

    private BlogCategorySessionBeanLocal lookupBlogCategorySessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (BlogCategorySessionBeanLocal) c.lookup("java:global/MyblogApps/BlogCategorySessionBean!com.ejb.BlogCategorySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private CommentSessionBeanLocal lookupCommentSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CommentSessionBeanLocal) c.lookup("java:global/MyblogApps/CommentSessionBean!com.ejb.CommentSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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

    private AuthorSessionBeanLocal lookupAuthorSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (AuthorSessionBeanLocal) c.lookup("java:global/MyblogApps/AuthorSessionBean!com.ejb.AuthorSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private TagsSessionBeanLocal lookupTagsSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (TagsSessionBeanLocal) c.lookup("java:global/MyblogApps/TagsSessionBean!com.ejb.TagsSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public Integer getPostCategoryId() {
        return postCategoryId;
    }

    public void setPostCategoryId(Integer postCategoryId) {
        this.postCategoryId = postCategoryId;
    }

    public Integer getPostAuthorId() {
        return postAuthorId;
    }

    public void setPostAuthorId(Integer postAuthorId) {
        this.postAuthorId = postAuthorId;
    }

    public String getSelecte_month() {
        return selected_month;
    }

    public void setSelecte_month(String selecte_month) {
        this.selected_month = selecte_month;
    }

    public String getSelected_category() {
        return selected_category;
    }

    public void setSelected_category(String selected_category) {
        this.selected_category = selected_category;
    }

    public String getSelected_title() {
        return selected_title;
    }

    public void setSelected_title(String selected_title) {
        this.selected_title = selected_title;
    }

    private MessageSessionBeanLocal lookupMessageSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (MessageSessionBeanLocal) c.lookup("java:global/MyblogApps/MessageSessionBean!com.ejb.MessageSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
