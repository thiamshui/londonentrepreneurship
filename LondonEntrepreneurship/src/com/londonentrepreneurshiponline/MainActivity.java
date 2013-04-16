package com.londonentrepreneurshiponline;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.VideoView;

import com.londonentrepreneurshiponline.models.Video;
import com.londonentrepreneurshiponline.utils.LoadImage;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Unable to use network thread with ui threat on honeycomb > 3.
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}		
		setContentView(R.layout.activity_main);
		setTabLayout();
		
		ArrayList<Video> videos = Video.getAllVideos();
		int[] imageId = {R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4};
		int[] textId = {R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4};
		ImageView[] images = new ImageView[imageId.length];
		
		for(int i = 0; i <= imageId.length-1; i++){	
		   images[i] = (ImageView) findViewById(imageId[i]);
		   Drawable drawable = LoadImage.LoadImageFromWebOperations(videos.get(i+1).getThumbnail());
           images[i].setImageDrawable(drawable);
           TextView textview = (TextView) findViewById(textId[i]);        
      	   textview.setText(videos.get(i+1).getTitle());	
		}  	
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.list_menu, menu);
		return true;
	}

	public void setTabLayout(){
		TabHost tabs  = (TabHost) findViewById(android.R.id.tabhost);
		tabs.setup();
		
		TabSpec specs = tabs.newTabSpec("tag1");
		specs.setContent(R.id.featured);
		specs.setIndicator("Featured");
		tabs.addTab(specs);
		
		specs = tabs.newTabSpec("tag2");
		specs.setContent(R.id.latest);
		specs.setIndicator("Latest");
		tabs.addTab(specs);
		
		specs = tabs.newTabSpec("tag3");
		specs.setContent(R.id.categories);
		specs.setIndicator("Categores");
		tabs.addTab(specs);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()){
		case R.id.about:
			Intent i = new Intent("android.intent.action.ABOUT");
			startActivity(i);
			break;
		case R.id.help:
			
			break;
		case R.id.preferences:
			
			break;		
		}
		return false;
		
	}

	
}
