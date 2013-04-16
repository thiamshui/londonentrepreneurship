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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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

public class SearchList extends Activity {

	public SearchList(){
    		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState){	
		super.onCreate(savedInstanceState);
		
		//Unable to use network thread with ui threat on honeycomb > 3.
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}	
		ArrayList<Video> videos = Video.getAllVideos();
	
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.LEFT;
	    		
		ScrollView sv = new ScrollView(this);
		LinearLayout ll = new LinearLayout(this);	
		ll.setOrientation(LinearLayout.VERTICAL);
		sv.addView(ll);
		
		for(int i = 0; i <=3; i++){	
			   ImageView image = new ImageView(this);
			   Drawable drawable = LoadImage.LoadImageFromWebOperations(videos.get(i+1).getThumbnail());
			   image.setImageDrawable(drawable);   
		       image.setLayoutParams(params);
			   ll.addView(image);	
		}  	
			
		/*Drawable drawable = LoadImage.LoadImageFromWebOperations(videos.get(1).getThumbnail());
        image.setImageDrawable(drawable);   
        image.setLayoutParams(params);
		ll.addView(image);*/
		
		TextView textview = new TextView(this);
		textview.setText("Something here");	
	    ll.addView(textview);	
	    
	    this.setContentView(sv);			
	}
	
	
	
	
	
}