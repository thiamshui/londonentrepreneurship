package com.londonentrepreneurshiponline;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
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
		specs = tabs.newTabSpec("tag1");
		specs.setContent(R.id.latest);
		specs.setIndicator("Latest");
		tabs.addTab(specs);
		specs = tabs.newTabSpec("tag1");
		specs.setContent(R.id.categories);
		specs.setIndicator("Categores");
		tabs.addTab(specs);
		
		//commit
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
