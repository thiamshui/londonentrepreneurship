package com.londonentrepreneurshiponline;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import com.londonentrepreneurshiponline.annotate.AnnotateTextActivity;
import com.londonentrepreneurshiponline.models.Video;

public class VideoActivity extends FragmentActivity {

	private Video video;
	private VideoView vv;
	private int videoPos = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		setContentView(R.layout.activity_video);

		video = (Video) getIntent().getSerializableExtra("video");
		if(savedInstanceState != null)
		{
			video = (Video) savedInstanceState.getSerializable("video");
		}
		
		TextView title = (TextView) findViewById(R.id.textView2);
		TextView desc = (TextView) findViewById(R.id.textView3);
		if(title != null)
		{
			title.setText(video.getTitle());
			desc.setText(video.getDesc());
		}
		
		FragmentManager fm = getSupportFragmentManager();
		VideoFragment f = (VideoFragment) fm.findFragmentById(R.id.fragment1);
		vv = f.vv;
		
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		// setContentView(R.layout.activity_video);
	}

	public void annotateText(View v) {
		
		if (((MainApplication) getApplication()).getLoggedOnUser() != -1)
			startAnnotateText();
		else
		{
			videoPos = vv.getCurrentPosition();
			startActivityForResult(new Intent(this, Login.class), 1);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 1) // annotate Text
		{
			if (resultCode == RESULT_OK) {
				startAnnotateText();
			}
		}
	}
	
	private void startAnnotateText()
	{
		Intent intent = new Intent(this, AnnotateTextActivity.class);
		if(vv.getCurrentPosition() != 0)
			intent.putExtra("pos", vv.getCurrentPosition());
		else
			intent.putExtra("pos", videoPos);
		intent.putExtra("videoId", video.getId());
		startActivity(intent);
	}

	public void annotateImpt(View v) {
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("video", video);
		outState.putInt("videoPos", videoPos);
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		FragmentManager fm = getSupportFragmentManager();
		VideoFragment f = (VideoFragment) fm.findFragmentById(R.id.fragment1);
		f.reReadAnnotations();
	}
}
