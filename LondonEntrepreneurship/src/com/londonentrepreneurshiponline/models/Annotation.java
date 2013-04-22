package com.londonentrepreneurshiponline.models;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.londonentrepreneurshiponline.utils.WSClient;

public class Annotation {
	
	private int id;
	private String text;
	private int votes;
	private int timeSecs;
	private int userID;
	private int videoId;
	
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	
	public int getVideoId() {
		return videoId;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getTimeSecs() {
		return timeSecs;
	}
	public void setTimeSecs(int timeSecs) {
		this.timeSecs = timeSecs;
	}
	
	public static SparseArray<String> getAnnotationsByVideo(int id)
	{
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();

		Type vidCollection = new TypeToken<LinkedList<Annotation>>() {}.getType();
		LinkedList<Annotation> annotations = null;
		SparseArray<String> map = new SparseArray<String>();
		
		String json = WSClient.httpGET("http://saturn.thiamshui.net/annotation.php?id=" + id);
		annotations = gson.fromJson(json, vidCollection);
		
		while(!annotations.isEmpty())
		{
			Annotation annotation = annotations.pop();
			map.put(annotation.getTimeSecs(), annotation.getText());
		}
		
		return map;
	}
	
	public static ArrayList<Annotation> getAnnotationsListByVideo(int id)
	  {
	    GsonBuilder gsonb = new GsonBuilder();
	    Gson gson = gsonb.create();
	
	    Type vidCollection = new TypeToken<ArrayList<Annotation>>() {}.getType();
	    ArrayList<Annotation> annotations = null;
	    
	    String json = WSClient.httpGET("http://saturn.thiamshui.net/annotation.php?id=" + id);
	    annotations = gson.fromJson(json, vidCollection);
	    
	    return annotations;
	  }

	public static String createAnnotation(String text, int timeSecs, int userID, int videoId)
	{
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("text", text));
		params.add(new BasicNameValuePair("timeSecs", ""+timeSecs));
		params.add(new BasicNameValuePair("videoId",""+videoId));
		params.add(new BasicNameValuePair("userID",""+userID));
		return WSClient.httpPOST("http://venus.thiamshui.net/annotation.php", params);
	}
	

}
