/**
 * 
 */
package com.londonentrepreneurshiponline.models;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
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
	 * Reference: https://sites.google.com/site/gson/gson-user-guide#TOC-Collections-Examples
	 */
	public static ArrayList<Video> getFeaturedVideos()
	{
		return getVideosByUrl("http://comp1008.thiamshui.net/video.php?featured");
	}
	public static ArrayList<Video> getLatestVideos()
	{
		return getVideosByUrl("http://comp1008.thiamshui.net/video.php?latest");
	}
	
	public static ArrayList<Video> searchVideos(String searchStr)
	{
		return getVideosByUrl("http://comp1008.thiamshui.net/video.php?search=" + searchStr);
	}

	public static Video getVideoById(int id) {
		String jsonString = "";
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();

		jsonString = WSClient.httpGET("http://comp1008.thiamshui.net/video.php?id="	+ id);
		return (Video) gson.fromJson(jsonString, Video.class);
	}
	
	private static ArrayList<Video> getVideosByUrl(String url)
	{
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();

		Type vidCollection = new TypeToken<ArrayList<Video>>() {}.getType();
		ArrayList<Video> videos = null;

		String json = WSClient.httpGET(url);
		videos = gson.fromJson(json, vidCollection);
		
		return videos;
	}
	
<<<<<<< HEAD
	protected class asyncVideos extends AsyncTask<String,Void,ArrayList<Video>>{
		
		@Override
		protected ArrayList<Video> doInBackground(String... params) {
			GsonBuilder gsonb = new GsonBuilder();
			Gson gson = gsonb.create();

			Type vidCollection = new TypeToken<ArrayList<Video>>() {}.getType();
			ArrayList<Video> videos = null;

			String json = WSClient.httpGET(params[0]);
			videos = gson.fromJson(json, vidCollection);
			return videos;
		}
		
		@Override
		protected void onPostExecute(ArrayList<Video> result) {
			// TODO Auto-generated method stub
			
			
		}
	}
	
}
=======
}	

>>>>>>> branch 'master' of https://github.com/thiamshui/londonentrepreneurship.git
