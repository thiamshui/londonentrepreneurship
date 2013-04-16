package com.londonentrepreneurshiponline;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.londonentrepreneurshiponline.models.Video;
import com.londonentrepreneurshiponline.utils.LoadImage;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View;

public class SearchList extends Activity implements View.OnClickListener {
    public Drawable drawable;
	public ArrayList<Video> videos = Video.getAllVideos();

	@Override
	protected void onCreate(Bundle savedInstanceState){	
		super.onCreate(savedInstanceState);
		
		//Unable to use network thread with ui threat on honeycomb > 3.
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
	}	
		
		
	
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.LEFT;
	    params.topMargin=30;
	    params.leftMargin = 20;
	    
	    LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(
			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		paramsText.gravity = Gravity.RIGHT;
	    paramsText.topMargin=20;
	    paramsText.rightMargin = 20;
	    paramsText.leftMargin = 10;
	    	
		ScrollView sv = new ScrollView(this);
		LinearLayout ll = new LinearLayout(this);	
		ll.setOrientation(LinearLayout.VERTICAL);
		sv.addView(ll);
		
		for(int i = 0; i<= videos.size()-1; i++){
		  LinearLayout row = new LinearLayout(this);
          row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			    ImageView image = new ImageView(this);
			    image.setId(i);
			    drawable = LoadImage.LoadImageFromWebOperations((videos.get(i).getThumbnail()));
			    image.setImageDrawable(drawable);   
		        image.setLayoutParams(params);
			    row.addView(image);
			    
			    TextView textview = new TextView(this);        
	      	    textview.setText(videos.get(i).getTitle());
	      	    textview.setLayoutParams(paramsText);
	      	    row.addView(textview);
	 		    	
	      	    ll.addView(row);
		}
	    this.setContentView(sv);			
	}
	
	protected class loadImageTask extends AsyncTask<String,Void,InputStream>
	{
		@Override
		protected InputStream doInBackground(String... params) {	
			 try{
			        InputStream is = (InputStream) new URL(params[0]).getContent();		        
			        return is;
			      }catch (Exception e) {
			        System.out.println("Exc="+e);
			        return null;
			      }
		}
		
		@Override
		protected void onPostExecute(InputStream result) {
			// TODO Auto-generated method stub
			drawable = Drawable.createFromStream(result, "src name");		
		}
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		for(int i = 0; i <= videos.size()-1; i++){
			if(v.getId() == i){
	     	  Intent myIntent = new Intent(this, VideoActivity.class);
		      myIntent.putExtra("videoId", videos.get(i).getId());
		      startActivity(myIntent);
			}  
		}
	}
	
	
	
}