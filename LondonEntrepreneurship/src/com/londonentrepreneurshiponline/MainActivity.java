package com.londonentrepreneurshiponline;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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
    boolean bool = false;
    int[] imageId = {R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5};
	int[] textId = {R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5};
	SearchView searchBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
     	
		setTabLayout();
		searchBar = (SearchView) findViewById(R.id.searchView1);
		searchBar.setIconified(false);
		searchBar.setOnQueryTextListener(this);	
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

	public void setViews(int id){
		new loadVideoTask().execute(id);
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
        
        tabs.setCurrentTab(1);
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
        if(bool != false && tabId.contentEquals("tag1")){
        	setViews(1);      	
        }else if(tabId.contentEquals("tag2")){
        	setViews(2);
        	bool = true;
        }else if(tabId.contentEquals("tag3")){
        	setViews(3);
        }             
	}
	
	@Override
	public void onClick(View v) {
		Video vid = null;
		// TODO Auto-generated method stub
		for(int i = 0; i<=imageId.length-1; i++){
			if(v.getId() == imageId[i] || v.getId() == textId[i]){	
				vid = videos.get(i);
			}	
		}			
		Intent myIntent = new Intent(this, VideoActivity.class);
		myIntent.putExtra("video",vid);
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
		searchBar.setQuery("", false);
		startActivity(myIntent);
		return false;
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
			Log.d("test","LENGTH:" + videos.size());
			for(int i = 0; i <= imageId.length-1; i++){	
			   ImageView image = (ImageView) findViewById(imageId[i]);
			   image.setOnClickListener(MainActivity.this);		   
	           image.setImageDrawable(images[i]);
	           TextView textview = (TextView) findViewById(textId[i]);        
	      	   textview.setText(videos.get(i).getTitle());	
	      	   textview.setOnClickListener(MainActivity.this);
			}  			
		}
	}
}
