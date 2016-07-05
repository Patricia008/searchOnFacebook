package com.mycompany.app;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.User;

public class FacebookInfo {
	 /* Variables */
    private final String pageAccessToken = "EAACEdEose0cBAOVXirgYiHkz9Snf78CDf4e2W9DDrZA4iUqeNL5onV6csxRJcbcRV2kU99KMrXcrgVuGauoNZAKdCcFopUl1bWkdyFjBR92NmTP8wLtdvCF9VbutA6EB4xZA4Y1sNpzNy1RsHge66reV6dYBBsUDTZC7ZBUSEhQZDZD";
    private final String pageID = "10205237715641106";
    private FacebookClient fbClient;
    private User myuser = null;    //Store references to your user and page
    private Page mypage = null;    //for later use. In this answer's context, these
                                   //references are useless.
    private int counter = 0;

    public FacebookInfo() {
        try {

            fbClient = new DefaultFacebookClient(pageAccessToken);
            myuser = fbClient.fetchObject("me", User.class);
            mypage = fbClient.fetchObject(pageID, Page.class);
            counter = 0;
        } catch (FacebookException ex) {     //So that you can see what went wrong
            ex.printStackTrace(System.err);  //in case you did anything incorrectly
        }
    }
    
    public String getAccessToken(){
    	return pageAccessToken;
    }

    public void makeTestPost() {
        fbClient.publish(pageID + "/feed", FacebookType.class, Parameter.with("message", Integer.toString(counter) + ": Hello, facebook World!"));
        counter++;
    }
    
    public JsonObject getFriend(String name){
    	FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken);
    	Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);

    	System.out.println("Count of my friends- " + myFriends.getTotalCount()); 
    	
    	System.out.println(myFriends.toString());
    	
    	JsonObject userData = facebookClient.fetchObject("me",
                JsonObject.class, Parameter.with("fields", "name, first_name, hometown"));
    	
    	return userData;
    }
    
    public static void main(String[] args)
    {
    	FacebookInfo p = new FacebookInfo();
    	//p.makeTestPost();
		FacebookClient facebookClient= new DefaultFacebookClient(p.getAccessToken());
        
        User user = facebookClient.fetchObject("me", User.class);

        System.out.println("User="+ user);
        System.out.println("UserName= "+ user.getFirstName());
        System.out.println("Birthday= "+ user.getBirthday());
        System.out.println("Email= "+ user.getEmail());
        
        JsonObject userData = facebookClient.fetchObject("me",
                JsonObject.class, Parameter.with("fields", "name, first_name, hometown"));
 
        System.out.println("userData=" + userData);
 
        System.out.println("FirstName=" + userData.getString("first_name"));
        System.out.println("Name= " + userData.getString("name"));
        System.out.println("hometown= " + userData.getString("hometown"));
        
        JsonObject friends = p.getFriend("");
    }

}
