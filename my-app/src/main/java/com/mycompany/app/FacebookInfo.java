package com.mycompany.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.User;

@WebServlet("/FacebookInfo")
public class FacebookInfo extends HttpServlet{
	
	 /* Variables */
    private final String pageAccessToken = "EAACEdEose0cBAPZAJGQabh30sxqjZBgL2MyCugeG3y2xBH1svKahl5VXI0Kq40Pbb6ytZAJMZC5MFEZCkIf4Nzjt4jZClnWbZBWVlzTjZAJ5drcf9fiFZAChOEZCzdx1fCG6YhUbrRS2pQagTwvTbI61la9EBkZCF4ltZCZAK4bGYdtyMsgZDZD";
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
    
    public JsonObject getMyInfo(FacebookClient fbClient){
    	JsonObject info = fbClient.fetchObject("me", JsonObject.class, Parameter.with("fields",  "name,"
    			+ "									 email, location, relationship_status, religion, political, work, gender"));
    	return info;
    }

    public JsonObject getFriend(FacebookClient fbClient,String name){
    	Connection<User> myFriends = fbClient.fetchConnection("me/friends", User.class);

    	System.out.println("Count of my friends " + myFriends.getTotalCount()); 
    	
    	System.out.println(myFriends.toString());
    	
    	JsonObject userData = fbClient.fetchObject("me/friends",
                JsonObject.class, Parameter.with("fields", "name, first_name, hometown"));
    	
    	return userData;
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	resp.getWriter().println("Testing Succes!");
    	
    }
    
    public static void main(String[] args)
    {
    	FacebookInfo p = new FacebookInfo();
    	//p.makeTestPost();
		FacebookClient facebookClient= new DefaultFacebookClient(p.getAccessToken());           
        
//        JsonObject userData = facebookClient.fetchObject("me",
//                JsonObject.class, Parameter.with("fields", "name, first_name, hometown"));
// 
//        System.out.println("userData=" + userData);
// 
//        System.out.println("FirstName=" + userData.getString("first_name"));
//        System.out.println("Name= " + userData.getString("name"));
//        System.out.println("hometown= " + userData.getString("hometown"));
//        
//        JsonObject friends = p.getFriend(facebookClient,"");
		
		JsonObject info = p.getMyInfo(facebookClient);
		System.out.println(info.toString());
    }

}
