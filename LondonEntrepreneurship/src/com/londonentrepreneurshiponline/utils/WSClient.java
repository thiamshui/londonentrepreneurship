package com.londonentrepreneurshiponline.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class WSClient {
		
	// Reference: http://www.vogella.com/articles/ApacheHttpClient/article.html
	public static String httpGET(String url)
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		String result = "";
		try
		{
			HttpResponse response = client.execute(request);

			String input = "";
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while((input = rd.readLine()) != null)
				result += input;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static String httpPOST(String url, ArrayList<NameValuePair> params)
	{
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		
		
		String result = "";
		try
		{
			request.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(request);

			String input = "";
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while((input = rd.readLine()) != null)
				result += input;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

}
