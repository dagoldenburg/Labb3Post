package DB;


import BO.Post;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;

/**
 * Created by douglas on 11/4/17.
 */
public class PostDb {

    public boolean createPost(String Content, String title, Date date, long userId){
        Post post=null;
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            post =new Post();
            post.setContent(Content);
            post.setTitle(title);
            post.setPublishDate(date);
            post.setCreatorId(userId);
            entityManager.persist(post);
            entityManager.getTransaction().commit();
        }catch (PersistenceException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return  false;
        }finally {
            entityManager.close();
        }
        return true;
    }

    public List<Post> getPostsByOwnerId(long ownerid){
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Post> posts=null;
        try {
            posts = entityManager.createNamedQuery("Post.FindPostBycreatorId").setParameter("id",ownerid).getResultList();
        }catch (NoResultException e){
            System.out.println("No result");
        }finally {
            entityManager.close();
        }
        return posts;
    }
}
