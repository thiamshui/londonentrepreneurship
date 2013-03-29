package com.londonentrepreneurshiponline;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		
		//commit
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.list_menu, menu);
		return true;
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
