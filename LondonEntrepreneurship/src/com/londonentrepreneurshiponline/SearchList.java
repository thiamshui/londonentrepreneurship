package com.londonentrepreneurshiponline;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
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
    private Drawable drawable;
	private ArrayList<Video> videos;
	ImageView rowImages;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){	
		super.onCreate(savedInstanceState);
		
		//Unable to use network thread with ui threat on honeycomb > 3.
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
	}	
		videos = Video.searchVideos("Richard");
	        
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
                
  		        
                rowImages = new ImageView(this);
			    rowImages.setId(i);
			    String uri = (videos.get(i).getThumbnail());
			    rowImages.setTag(uri);
			    drawable = LoadImage.LoadImageFromWebOperations(uri);
			    Log.d("BACKGROUND", "before");
			    //new DownloadImage().execute(uri);
			    rowImages.setImageDrawable(drawable);   
			    
			    rowImages.setLayoutParams(params);
			    row.addView(rowImages);
			    rowImages.setOnClickListener(this);
			    
			    TextView textview = new TextView(this);        
	      	    textview.setText(videos.get(i).getTitle());
	      	    textview.setLayoutParams(paramsText);
	      	    row.addView(textview);
	 		    	
	      	    ll.addView(row);
		}
	    this.setContentView(sv);			
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

	//thread method of getting image
	private void setImage(Drawable drawable)
	{
	    rowImages.setImageDrawable(drawable);
	}

	protected class DownloadImage extends AsyncTask<String, Integer, Drawable> {

	    @Override
	    protected Drawable doInBackground(String... arg0) {
	        // This is done in a background thread
	        return downloadImage(arg0[0]);
	    }


	    protected void onPostExecute(Drawable image)
	    {
	        setImage(image);
	    }

	    
	    private Drawable downloadImage(String _url)
	    {
	    	Log.d("BACKGROUND", "download");
	        URL url;        
	        BufferedOutputStream out;
	        InputStream in;
	        BufferedInputStream buf;
            Context context = getApplicationContext();
        
	        try {
	            url = new URL(_url);
	            in = url.openStream();

	            buf = new BufferedInputStream(in);
	            Log.d("BACKGROUND", "halfway");
	            Bitmap bMap = BitmapFactory.decodeStream(buf);
	            if (in != null) {
	                in.close();
	            }
	            if (buf != null) {
	                buf.close();
	            }
  
	            return new BitmapDrawable(context.getResources(), bMap);

	        } catch (Exception e) {
	            Log.e("Error reading file", e.toString());
	        }

	        return null;
	    }

	}


}	
	
	
