/**
 * 
 */
package com.londonentrepreneurshiponline.models;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.net.Uri;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.londonentrepreneurshiponline.utils.WSClient;

/**
 * @author thiamshui
 * 
 */
public class Video implements Serializable{
	
	private int id;
	private String uri;
	private String title;
	private String desc;
	private int likes;
	private int i01, i02, i03, i04, i05, i06, i07, i08, i09, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20;
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
	
	public int getI01() {
		return i01;
	}

	public void setI01(int i01) {
		this.i01 = i01;
	}

	public int getI02() {
		return i02;
	}

	public void setI02(int i02) {
		this.i02 = i02;
	}

	public int getI03() {
		return i03;
	}

	public void setI03(int i03) {
		this.i03 = i03;
	}

	public int getI04() {
		return i04;
	}

	public void setI04(int i04) {
		this.i04 = i04;
	}

	public int getI05() {
		return i05;
	}

	public void setI05(int i05) {
		this.i05 = i05;
	}

	public int getI06() {
		return i06;
	}

	public void setI06(int i06) {
		this.i06 = i06;
	}

	public int getI07() {
		return i07;
	}

	public void setI07(int i07) {
		this.i07 = i07;
	}

	public int getI08() {
		return i08;
	}

	public void setI08(int i08) {
		this.i08 = i08;
	}

	public int getI09() {
		return i09;
	}

	public void setI09(int i09) {
		this.i09 = i09;
	}

	public int getI10() {
		return i10;
	}

	public void setI10(int i10) {
		this.i10 = i10;
	}

	public int getI11() {
		return i11;
	}

	public void setI11(int i11) {
		this.i11 = i11;
	}

	public int getI12() {
		return i12;
	}

	public void setI12(int i12) {
		this.i12 = i12;
	}

	public int getI13() {
		return i13;
	}

	public void setI13(int i13) {
		this.i13 = i13;
	}

	public int getI14() {
		return i14;
	}

	public void setI14(int i14) {
		this.i14 = i14;
	}

	public int getI15() {
		return i15;
	}

	public void setI15(int i15) {
		this.i15 = i15;
	}

	public int getI16() {
		return i16;
	}

	public void setI16(int i16) {
		this.i16 = i16;
	}

	public int getI17() {
		return i17;
	}

	public void setI17(int i17) {
		this.i17 = i17;
	}

	public int getI18() {
		return i18;
	}

	public void setI18(int i18) {
		this.i18 = i18;
	}

	public int getI19() {
		return i19;
	}

	public void setI19(int i19) {
		this.i19 = i19;
	}

	public int getI20() {
		return i20;
	}

	public void setI20(int i20) {
		this.i20 = i20;
	}

	/* deserialize video collection
	 * Reference: https://sites.google.com/site/gson/gson-user-guide#TOC-Collections-Examples
	 */
	public static ArrayList<Video> getFeaturedVideos()
	{
		return getVideosByUrl("http://saturn.thiamshui.net/video.php?featured");
	}
	public static ArrayList<Video> getLatestVideos()
	{
		return getVideosByUrl("http://saturn.thiamshui.net/video.php?latest");
	}
	
	public static ArrayList<Video> searchVideos(String searchStr)
	{
		return getVideosByUrl("http://saturn.thiamshui.net/video.php?search=" + Uri.encode(searchStr));
	}

	public static Video getVideoById(int id) {
		String jsonString = "";
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();

		jsonString = WSClient.httpGET("http://saturn.thiamshui.net/video.php?id="	+ id);
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
	
	public int[] getImportanceArray()
	{
		return new int[] { i01, i02, i03, i04, i05, i06, i07, i08, i09, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20 };
	}
	

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
	
	public static void saveImportance(int segment, int videoId)
	{
		if(segment < 10)
			WSClient.httpGET("http://saturn.thiamshui.net/video.php?segment=0" + segment + "&videoId=" + videoId);
		else
			WSClient.httpGET("http://saturn.thiamshui.net/video.php?segment=" + segment + "&videoId=" + videoId);
	}
	
}