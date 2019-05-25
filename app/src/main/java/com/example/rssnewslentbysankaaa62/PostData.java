package com.example.rssnewslentbysankaaa62;

public class PostData {

    public PostData(String pLink, String pTitle, String pDate){
        postLink = pLink;
        postTitle = pTitle;
        postDate = pDate;
    }

    public PostData(){
        postLink = "";
        postTitle = "";
        postDate = "";
    }

    public String postLink;
    public String postTitle;
    public String postDate;
}
