package com.londonentrepreneurshiponline.models;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.londonentrepreneurshiponline.utils.WSClient;

public class Annotation {
	
	private int id;
	private String text;
	private int votes;
	private int timeSecs;
	
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
	
	public static LinkedList<Annotation> getAnnotationsByVideo(int id)
	{
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();

		Type vidCollection = new TypeToken<LinkedList<Annotation>>() {}.getType();
		LinkedList<Annotation> annotations = null;

		
		String json = WSClient.httpGET("http://comp1008.thiamshui.net/annotation.php?id=" + id);
		annotations = gson.fromJson(json, vidCollection);
		
		return annotations;
	}
	

}
