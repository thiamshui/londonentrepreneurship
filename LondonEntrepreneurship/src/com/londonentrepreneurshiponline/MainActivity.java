package com.londonentrepreneurshiponline;

import java.io.IOException;

import org.json.JSONException;

import com.londonentrepreneurshiponline.models.Video;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.TabHost.TabSpec;

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
		
		Video videoObj = new Video();
		VideoView vid = (VideoView) findViewById(R.id.videoView1);
		MediaController mc = new MediaController(this);
		mc.setMediaPlayer(vid);
		vid.setVideoURI(Uri.parse(videoObj.getVideobyID(1, "uri")));
        vid.setMediaController(mc);
        vid.start();
        
        TextView textview = (TextView) findViewById(R.id.textView1);        
		textview.setText(videoObj.getVideobyID(2, "title"));		
		VideoView video1 = (VideoView) findViewById(R.id.videoView2);
		video1.setVideoURI(Uri.parse(videoObj.getVideobyID(2, "uri"))); 
		MediaController mc1 = new MediaController(this);
		mc1.setMediaPlayer(video1);
		video1.setMediaController(mc1);
        video1.start();
		
		TextView textview1 = (TextView) findViewById(R.id.textView2);        
		textview1.setText(videoObj.getVideobyID(3, "title"));		
  
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
