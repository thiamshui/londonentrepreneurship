package com.londonentrepreneurshiponline.annotate;

import com.londonentrepreneurshiponline.R;
import com.londonentrepreneurshiponline.R.layout;
import com.londonentrepreneurshiponline.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AnnotateTextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_annotate_text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.annotate_text, menu);
		return true;
	}

}
