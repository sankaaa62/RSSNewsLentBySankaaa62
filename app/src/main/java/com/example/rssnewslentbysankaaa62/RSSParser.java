package com.example.rssnewslentbysankaaa62;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RSSParser {
    private RSSXMLTag currentTag;

    private InputStream ReadURL(String URLstr){

        InputStream inputStream = null;

        try {
            //Загрузка данных по URL
            URL url = new URL(URLstr);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int response = connection.getResponseCode();
            Log.d("debug", "The response is: " + response);
            inputStream = connection.getInputStream();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return inputStream;
    }

    private ArrayList<Post> ParseRSS(InputStream inputStream){

        //Распарсим поток данных в лист постов.
        ArrayList<Post> postList = new ArrayList<Post>();

        try {
            // parse xml after getting the data
            XmlPullParserFactory xmlParserFactory = XmlPullParserFactory
                    .newInstance();
            xmlParserFactory.setNamespaceAware(true);
            XmlPullParser xpp = xmlParserFactory.newPullParser();
            xpp.setInput(inputStream, null);

            int eventType = xpp.getEventType();
            Post pdData = null;
            //SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, DD MMM yyyy HH:mm:ss");

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("item")) {
                        pdData = new Post();
                        currentTag = RSSXMLTag.IGNORETAG;
                    } else if (xpp.getName().equals("title")) {
                        currentTag = RSSXMLTag.TITLE;
                    } else if (xpp.getName().equals("link")) {
                        currentTag = RSSXMLTag.LINK;
                    } else if (xpp.getName().equals("pubDate")) {
                        currentTag = RSSXMLTag.DATE;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("item")) {
                        // format the data here, otherwise format data in
                        // Adapter
                        //Date postDate = dateFormat.parse(pdData.postDate);
                        //pdData.postDate = dateFormat.format(postDate);

                        String oldTime = pdData.postDate;

                        // RSS time format
                        DateFormat originalFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
                        // SQLite compatible format
                        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = null;
                        try {
                            date = originalFormat.parse(oldTime);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String formattedDate = targetFormat.format(date);
                        pdData.postDate = formattedDate;

                        postList.add(pdData);
                    } else {
                        currentTag = RSSXMLTag.IGNORETAG;
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    String content = xpp.getText();
                    content = content.trim();
                    Log.d("debug", content);
                    if (pdData != null) {
                        switch (currentTag) {
                            case TITLE:
                                if (content.length() != 0) {
                                    if (pdData.postTitle != null) {
                                        pdData.postTitle = content;
                                    } else {
                                        pdData.postTitle = content;
                                    }
                                }
                                break;
                            case LINK:
                                if (content.length() != 0) {
                                    if (pdData.postURL != null) {
                                        pdData.postURL = content;
                                    } else {
                                        pdData.postURL = content;
                                    }
                                }
                                break;
                            case DATE:
                                if (content.length() != 0) {
                                    if (pdData.postDate != null) {
                                        pdData.postDate = content;
                                    } else {
                                        pdData.postDate = content;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }

                eventType = xpp.next();
            }
            Log.v("tst", String.valueOf((postList.size())));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
 //       } catch (ParseException e) {
 //           // TODO Auto-generated catch block
 //           e.printStackTrace();
        }

        return postList;
    }

    public ArrayList<Post> ReadRSS(String URLstr){

        return ParseRSS(ReadURL(URLstr));
    }
}
