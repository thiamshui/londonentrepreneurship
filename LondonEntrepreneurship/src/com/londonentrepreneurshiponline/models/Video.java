/**
 * 
 */
package com.londonentrepreneurshiponline.models;

import java.lang.reflect.Type;
import java.util.Collection;

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
	
	/* deserialize video collection
	 * Reference:	https://sites.google.com/site/gson/gson-user-guide#TOC-Collections-Examples
	 */
	public static Collection<Video> getAllVideos()
	{
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		
		Type vidCollection = new TypeToken<Collection<Video>>(){}.getType();
		Collection<Video> videos = null;
		
		try
		{
			String json = WSClient.httpGET("http://comp1008.thiamshui.net/");
			videos = gson.fromJson(json, vidCollection);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return videos;
	}
}
