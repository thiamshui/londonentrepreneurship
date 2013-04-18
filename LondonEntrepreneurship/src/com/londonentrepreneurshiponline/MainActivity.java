package com.londonentrepreneurshiponline;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.londonentrepreneurshiponline.models.Video;
import com.londonentrepreneurshiponline.utils.LoadImage;

public class MainActivity extends Activity implements View.OnClickListener, OnTabChangeListener, OnQueryTextListener{
    private ArrayList<Video> videos;
    private Drawable[] images = new Drawable[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Unable to use network thread with ui threat on honeycomb > 3.
		/*if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}*/
		setContentView(R.layout.activity_main);
		setTabLayout();
		
		SearchView searchBar = (SearchView) findViewById(R.id.searchView1);
		searchBar.setOnQueryTextListener(this);		
	}
	
	public void setViews(int id){
		new loadVideoTask().execute(id);
	}
	
	protected class loadVideoTask extends AsyncTask<Integer,Void,Void>
	{
		@Override
		protected Void doInBackground(Integer... params) {
			switch(params[0]){
			  case 1: videos = Video.getFeaturedVideos(); break;
			  case 2: videos = Video.getLatestVideos();break;
			  case 3: videos = Video.getFeaturedVideos();break;
			}
			
			for(int i=0;i<5;i++)
			{
				images[i] = LoadImage.LoadImageFromWebOperations(videos.get(i).getThumbnail());
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			int[] imageId = {R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5};
			int[] textId = {R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5};

			for(int i = 0; i <= imageId.length-1; i++){	
			   ImageView image = (ImageView) findViewById(imageId[i]);
			   image.setOnClickListener(MainActivity.this);
			   
	           image.setImageDrawable(images[i]);
	           TextView textview = (TextView) findViewById(textId[i]);        
	      	   textview.setText(videos.get(i).getTitle());	
			}  			

		
			
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
        specs.setContent(R.id.Featured);
        specs.setIndicator("Featured");
        tabs.setOnTabChangedListener(this);
        tabs.addTab(specs);
        
        specs = tabs.newTabSpec("tag2");
        specs.setIndicator("Latest");
        specs.setContent(R.id.Featured);
        tabs.setOnTabChangedListener(this);
        tabs.addTab(specs);
        
        specs = tabs.newTabSpec("tag3");
        specs.setContent(R.id.Featured);
        specs.setIndicator("Categores");
        tabs.setOnTabChangedListener(this);
        tabs.addTab(specs);
		
        tabs.setCurrentTab(0);
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

	@Override
    public void onTabChanged(String tabId) {
        Log.d("selectedtab", tabId);
        
        if(tabId.contentEquals("tag1")){
        	setViews(1);
        }else if(tabId.contentEquals("tag2")){
        	setViews(2);
        }else if(tabId.contentEquals("tag2")){
        	setViews(3);
        }     
                  
	}

         
	@Override
	public void onClick(View v) {
		int id = 0;
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.imageView1:
			id = videos.get(0).getId();;
			break;
		case R.id.imageView2:
			id = videos.get(1).getId();
			break;
		case R.id.imageView3:
			id = videos.get(2).getId();
			break;
		case R.id.imageView4:
			id = videos.get(3).getId();
			break;
		case R.id.imageView5:
			id = videos.get(4).getId();
			break;
		}
		Intent myIntent = new Intent(this, VideoActivity.class);
		myIntent.putExtra("videoId", id);
		startActivity(myIntent);

	}

	@Override
	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		Log.d("search",arg0);
		Intent myIntent = new Intent(this, SearchList.class);
		myIntent.putExtra("Query", arg0);
		startActivity(myIntent);
		return false;
	}


}