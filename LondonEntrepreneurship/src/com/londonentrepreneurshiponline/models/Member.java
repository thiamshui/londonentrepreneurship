package com.londonentrepreneurshiponline.models;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.londonentrepreneurshiponline.utils.WSClient;

public class Member {
	
	private int id;
	private String username;
	private String password;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static Member authenticate(String username, String password)
	{
		String jsonString = "";
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();

		jsonString = WSClient.httpGET("http://saturn.thiamshui.net/member.php?username=" + username + "&password=" + password);
		try
		{
			return (Member) gson.fromJson(jsonString, Member.class);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static String getUsernameById(int id)
	{
		String jsonString = "";
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();

		jsonString = WSClient.httpGET("http://saturn.thiamshui.net/member.php?id=" + id);
		try
		{
			Member m = (Member) gson.fromJson(jsonString, Member.class);
			return m.getUsername();
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
