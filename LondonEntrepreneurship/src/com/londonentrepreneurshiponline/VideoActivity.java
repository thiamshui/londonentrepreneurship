package com.londonentrepreneurshiponline;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.londonentrepreneurshiponline.annotate.AnnotateTextActivity;
import com.londonentrepreneurshiponline.models.Video;

public class VideoActivity extends FragmentActivity {

	private boolean loggedIn = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		setContentView(R.layout.activity_video);

		Video vid = (Video) getIntent().getSerializableExtra("video");
		((TextView)findViewById(R.id.textView2)).setText(vid.getTitle());
		((TextView)findViewById(R.id.textView3)).setText(vid.getDesc());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		//setContentView(R.layout.activity_video);
	}

	public void annotateText(View v)
	{
		Intent intent = new Intent(this,AnnotateTextActivity.class);
		if(intent.getStringExtra("user") != null || loggedIn)
			startActivity(intent);
		else
			startActivityForResult(new Intent(this,Login.class),1);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == 1) // annotate Text
		{
			if(resultCode == RESULT_OK)
			{
				startActivity(new Intent(this,AnnotateTextActivity.class).putExtra("user", data.getStringExtra("user")));
				loggedIn = true;
			}
		}
	}
	
	public void annotateImpt(View v)
	{
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

}
