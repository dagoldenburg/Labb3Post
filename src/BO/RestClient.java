package BO;

import UI.PostViewModel;
import UI.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;

public class RestClient {
    private static final String userUrl ="http://localhost:8080/resource/";
    private static final String postUrl ="http://localhost:8081/resource/";
    private  static Client client  = ClientBuilder.newClient();
    private static Gson gson =  new Gson();


    public static UserViewModel getUserById(long id){
        String value =client.target(userUrl+"user").path(String.valueOf(id)).request().accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        UserViewModel userViewModel =gson.fromJson(value, UserViewModel.class);
        return userViewModel;
    }

    public static LinkedList<PostViewModel> getPostsByOwnerId(long id){
        String value =client.target(postUrl+"postByOwner").path(String.valueOf(id)).request().accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        LinkedList<PostViewModel> posts= gson.fromJson(value, new TypeToken<LinkedList<PostViewModel>>(){}.getType());

        return posts;
    }

    public static LinkedList<UserViewModel> getFriendList(long listOwnerId){
        String value =client.target(userUrl+"friendList").path(String.valueOf(listOwnerId)).request().accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        LinkedList<UserViewModel> users= gson.fromJson(value, new TypeToken<LinkedList<UserViewModel>>(){}.getType());

        return users;

    }

}
