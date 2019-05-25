package com.example.rssnewslentbysankaaa62;

public class Post {

    public String postChanelURL;
    public String postURL;
    public String postTitle;
    public String postImageURL;
    public String postDate;

    public Post(String pChanelURL, String pURL, String pTitle, String pImageURL, String pDate){
        postChanelURL = pChanelURL;
        postURL = pURL;
        postTitle = pTitle;
        postImageURL = pImageURL;
        postDate = pDate;
    }

    public Post(){
        postChanelURL = "none";
        postURL = "none";
        postTitle = "none";
        postImageURL = "none";
        postDate = "none";
    }
    public Post(String pLink, String pTitle, String pDate){
        postChanelURL = "none";
        postURL = pLink;
        postTitle = pTitle;
        postImageURL = "none";
        postDate = pDate;
    }
}
