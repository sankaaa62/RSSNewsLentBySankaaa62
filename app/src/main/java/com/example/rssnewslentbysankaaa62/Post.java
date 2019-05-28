package com.example.rssnewslentbysankaaa62;

public class Post {

    public String postChanelURL;
    public String postURL;
    public String postTitle;
    public String postImageURL;
    public String postDate;
    public String postDescription;

    public Post(String pChanelURL, String pURL, String pTitle, String pImageURL, String pDate, String pDescription){
        postChanelURL = pChanelURL;
        postURL = pURL;
        postTitle = pTitle;
        postImageURL = pImageURL;
        postDate = pDate;
        postDescription = pDescription;
    }

    public Post(){
        postChanelURL = "none";
        postURL = "none";
        postTitle = "none";
        postImageURL = "none";
        postDate = "none";
        postDescription = "none";
    }
    public Post(String pLink, String pTitle, String pDate){
        postChanelURL = "none";
        postURL = pLink;
        postTitle = pTitle;
        postImageURL = "none";
        postDate = pDate;
        postDescription = "none";
    }
}
