package com.mycompany.app;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.User;

/**
 *
 * @author dsfounis
 */
public class PostOnFb {

    /* Variables */
    private final String pageAccessToken = "EAACEdEose0cBAL0UdiPfrvoGiF7Qe3aLZC0G2t9Mhop3TVrZBJkWB2znuxPjoZB5JjVld73mEMdo7RJeJ0xDvVey1zxALj3Gn2rJLi8iABBPZAl5RyrJEwZAVjFqKU0fIF8LpZBht2myv7M0IsggJxx66khsz7gZAn7vhiOmPyZAsQZDZD";
    private final String pageID = "10205237715641106";
    private FacebookClient fbClient;
    private User myuser = null;    //Store references to your user and page
    private Page mypage = null;    //for later use. In this answer's context, these
                                   //references are useless.
    private int counter = 0;

    public PostOnFb() {
        try {

            fbClient = new DefaultFacebookClient(pageAccessToken);
            myuser = fbClient.fetchObject("me", User.class);
            mypage = fbClient.fetchObject(pageID, Page.class);
            counter = 0;
        } catch (FacebookException ex) {     //So that you can see what went wrong
            ex.printStackTrace(System.err);  //in case you did anything incorrectly
        }
    }

    public void makeTestPost() {
        fbClient.publish(pageID + "/feed", FacebookType.class, Parameter.with("message", Integer.toString(counter) + ": Hello, facebook World!"));
        counter++;
    }
    
    public static void main(String[] args)
    {
    	PostOnFb p = new PostOnFb();
    	p.makeTestPost();
    }

}