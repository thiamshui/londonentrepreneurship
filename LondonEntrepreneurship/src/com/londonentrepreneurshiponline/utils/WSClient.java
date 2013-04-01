package com.londonentrepreneurshiponline.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class WSClient {
		
	// Reference: http://www.vogella.com/articles/ApacheHttpClient/article.html
	
	public static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}

	public static String readJsonFromUrl(String url) throws IOException, JSONException{
		BufferedReader rd = null;
     	URL url1 = new URL(url);
		try {
		   rd = new BufferedReader(new InputStreamReader(url1.openStream()));
		   String jsonText = readAll(rd);
		   return jsonText;
        }finally {
		  rd.close();
        }
	}

}
