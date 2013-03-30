/**
 * 
 */
package com.londonentrepreneurshiponline.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import android.util.Log;
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
	private static ArrayList<Map<String,String>> videos;
	
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

	public String getVideobyID(int id, String key)
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
	
	public void getAllVideos()
	{
		String jsonString = "";
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();	
		
		Type vidCollection = new TypeToken<ArrayList<HashMap<String,String>>>(){}.getType();
	    
		try {
			jsonString = WSClient.readJsonFromUrl("http://comp1008.thiamshui.net/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		    
	    videos = gson.fromJson(jsonString, vidCollection);
	}
	
	}