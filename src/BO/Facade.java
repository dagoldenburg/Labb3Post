package BO;

import DB.PostDb;
import UI.PostViewModel;
import UI.UserViewModel;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by douglas on 11/4/17.
 */
@Path("resource")
public class Facade {
    private static PostDb postDb = new PostDb();


    @POST
    @Path("createPost")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public static  String createPost(@FormParam("content")String Content ,
                                     @FormParam("title")String title,
                                     @FormParam("date")String date, @FormParam("creatorId")String creatorId){
        Gson gson =new Gson();
        return gson.toJson(postDb.createPost(Content,title,new Date(),Long.parseLong(creatorId)));
    }

    /**
     * this method return a list of all post by its creator's id
     * @param id creator id
     * @return list of post objects
     */
    @GET
    @Path("postByOwner/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static  String getPostsByOwnerId(@PathParam("id") String id){
        Gson gson = new Gson();

        LinkedList<PostViewModel> posts = new LinkedList<>();
        try {
            for (Post p : postDb.getPostsByOwnerId(Long.parseLong(id))) {
                posts.add(0,new PostViewModel(p.getId(),p.getTitle(), p.getContent(), p.getPublishDate(),
                        RestClient.getUserById(Long.parseLong(id))));
            }
        }catch(NullPointerException e){
            System.out.println("NULLPOINTER EXCEPTION");
        }
        return gson.toJson(posts);
    }

    /**
     * this method returns all posts from a persons friendslist
     * @param myId person one
     * @return list of post objects
     */
    @GET
    @Path("AllFriendsPosts/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String getAllFriendsPosts(@PathParam("id")String myId){
        Gson gson = new Gson();
        LinkedList<PostViewModel> posts = new LinkedList<>();
        for(UserViewModel u:RestClient.getFriendList(Long.parseLong(myId))){
            for(PostViewModel p:RestClient.getPostsByOwnerId(u.getId())){
                posts.add(p);
            }
        }
        posts.sort(PostViewModel::compareTo);
        return gson.toJson(posts);
    }


}
