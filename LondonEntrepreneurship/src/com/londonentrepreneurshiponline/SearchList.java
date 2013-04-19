package com.londonentrepreneurshiponline;


import java.util.ArrayList;

import com.londonentrepreneurshiponline.models.Video;
import com.londonentrepreneurshiponline.utils.LoadImage;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View;

public class SearchList extends Activity implements View.OnClickListener {
	private ArrayList<Video> videos;
	ImageView rowImages;
	
    
	@Override
	protected void onCreate(Bundle savedInstanceState){	
		super.onCreate(savedInstanceState);
	
		Intent myIntent= getIntent();
		String query = myIntent.getStringExtra("Query");
		new loadImages().execute(query);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		for(int i = 0; i <= videos.size()-1; i++){
			if(v.getId() == i){
	     	  Intent myIntent = new Intent(this, VideoActivity.class);
		      myIntent.putExtra("video", videos.get(i));
		      startActivity(myIntent);
			}  
		}
	}
	
	protected class loadImages extends AsyncTask<String, Void, Drawable[] >{
		String query;
		protected Drawable[] doInBackground(String... params) {
			query = params[0];
			videos = Video.searchVideos(query);
				Drawable[] singleImage = new Drawable[videos.size()];
				for(int i = 0; i<= videos.size()-1; i++){			
					singleImage[i] = LoadImage.LoadImageFromWebOperations(videos.get(i).getThumbnail());
				}
				return singleImage;

		}
		
		protected void onPostExecute(Drawable[] singleImage){
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		    params.topMargin=30;
		    params.leftMargin = 20;

		    LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(
				    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
		    paramsText.topMargin=20;
		    paramsText.rightMargin = 20;
		    paramsText.leftMargin = 10;

			ScrollView sv = new ScrollView(SearchList.this);
			sv.setLayoutParams(params);
			LinearLayout ll = new LinearLayout(SearchList.this);	
			ll.setOrientation(LinearLayout.VERTICAL);
			

			if(videos.size() == 0){
				paramsText.gravity = 17;
				paramsText.topMargin =50;
			    TextView textview = new TextView(SearchList.this);
			    textview.setText("No video results found for '" + query + "'");
			    textview.setLayoutParams(paramsText);
			    ll.addView(textview);
			    SearchList.this.setContentView(ll);
			} 
			 else {
				 TextView displayQuery = new TextView(SearchList.this);
				 displayQuery.setText("Search Results for '" + query + "'");
				 displayQuery.setLayoutParams(paramsText);
				 paramsText.gravity =17;
				 ll.addView(displayQuery);
				 sv.addView(ll);
      			for (int i = 0; i <= videos.size() - 1; i++) {
      				Log.d("here", "now");
					LinearLayout row = new LinearLayout(SearchList.this);
					row.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));

					rowImages = new ImageView(SearchList.this);
					rowImages.setId(i);
					rowImages.setTag(videos.get(i).getThumbnail());
					rowImages.setImageDrawable(singleImage[i]);
					rowImages.setLayoutParams(params);
					rowImages.setOnClickListener(SearchList.this);
					row.addView(rowImages);
					
					TextView textview = new TextView(SearchList.this);
					textview.setText(videos.get(i).getTitle());
					textview.setId(i);
					textview.setLayoutParams(paramsText);
					textview.setOnClickListener(SearchList.this);
					row.addView(textview);
					ll.addView(row);
					
					SearchList.this.setContentView(sv);
				}
				
			}
		}
		
	}
}	