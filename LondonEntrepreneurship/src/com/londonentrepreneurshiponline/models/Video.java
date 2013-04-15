/**
 * 
 */
package com.londonentrepreneurshiponline.models;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.londonentrepreneurshiponline.utils.WSClient;

/**
 * @author thiamshui
 * 
 */
public class Video {

	private int id;
	private String uri;
	private String title;
	private String desc;
	private int likes;
	private String thumbnail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

<<<<<<< HEAD
	public String getVideoValue(int id, String key)
	{
		getAllVideos();	
		String title = null;
		for (Map<String,String> m : videos)
	    {
			String getId = m.get("ID");
			if(getId.equals(String.valueOf(id)))
			{
	           title = m.get(key);
			   return title;
			}   
	    }
		return null;
	}
	
	/* deserialize video collection
=======
	/*
	 * deserialize video collection
>>>>>>> branch 'master' of https://github.com/thiamshui/londonentrepreneurship.git
	 * Reference: https://sites.google.com/site/gson/gson-user-guide#TOC-Collections-Examples
	 */
	public static ArrayList<Video> getAllVideos()
	{
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();

		Type vidCollection = new TypeToken<ArrayList<Video>>() {}.getType();
		ArrayList<Video> videos = null;

		
		String json = WSClient.httpGET("http://comp1008.thiamshui.net/video.php");
		videos = gson.fromJson(json, vidCollection);
		
		return videos;
	}

	public static Video getVideoById(int id) {
		String jsonString = "";
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();

		jsonString = WSClient.httpGET("http://comp1008.thiamshui.net/video.php?id="	+ id);
		
<<<<<<< HEAD
		try {
			jsonString = WSClient.readJsonFromUrl("http://comp1008.thiamshui.net/video.php?id=" + id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		    
	    return (Video) gson.fromJson(jsonString, Video.class);		
=======
		return (Video) gson.fromJson(jsonString, Video.class);
>>>>>>> branch 'master' of https://github.com/thiamshui/londonentrepreneurship.git
	}
}
