package com.londonentrepreneurshiponline;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.londonentrepreneurshiponline.annotate.AnnotateTextActivity;
import com.londonentrepreneurshiponline.annotate.annotateList;
import com.londonentrepreneurshiponline.models.Annotation;
import com.londonentrepreneurshiponline.models.Video;

public class VideoActivity extends FragmentActivity {

	private Video video;
	private VideoView vv;
	private int videoPos = 0;
    private VideoFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		
		if(savedInstanceState != null)
		{
			video = (Video) savedInstanceState.getSerializable("video");
		}
		
		setContentView(R.layout.activity_video);

//		int time = getIntent().getIntExtra("milliSeconds", -1);
//		video = (Video) getIntent().getSerializableExtra("videoFromAnnotation");
			
		video = (Video) getIntent().getSerializableExtra("video");
		
		
		TextView title = (TextView) findViewById(R.id.textView2);
		TextView desc = (TextView) findViewById(R.id.textView3);
		if(title != null)
		{
			title.setText(video.getTitle());
			desc.setText(video.getDesc());
		}
		
		FragmentManager fm = getSupportFragmentManager();
		fragment = (VideoFragment) fm.findFragmentById(R.id.fragment1);
		vv = fragment.vv;	
	}

	public void retrieveFromAnnotationList(){
		
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

    
	public void annotationList(View v){
	    Intent myIntent = new Intent(VideoActivity.this, annotateList.class);
		myIntent.putExtra("video", video);
		startActivity(myIntent);	
	}

	public void annotateImpt(View v) {
		if (((MainApplication) getApplication()).getLoggedOnUser() != -1)
		{
			double curPos = vv.getCurrentPosition(), duration = vv.getDuration();
			int segment = (int) (curPos / duration * 19);
			Log.d("test","CUR " + vv.getCurrentPosition() + " TOTAL " + vv.getDuration());
			new annotateImportanceTask().execute(segment,video.getId());
		}
		else
			startActivityForResult(new Intent(this, Login.class), 2);
	}
	
	public void launchHeatMap(View v)
	{
		Intent in = new Intent(this,Heatmap.class);
		in.putExtra("video", video);
		in.putExtra("videoDur", vv.getDuration());
		
		
		startActivity(in);
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
		if(requestCode == 2)
		{
			vv.start();
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

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("video", video);
		outState.putInt("videoPos", videoPos);
		fragment.setVideoFinished();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		fragment.reReadAnnotations();
	}
	
	protected class annotateImportanceTask extends AsyncTask<Integer,Void,Void>	{
		@Override
		protected Void doInBackground(Integer... params) {
			Video.saveImportance(params[0] + 1, params[1]);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			Toast.makeText(VideoActivity.this, "Annotation Saved.", 5000).show();
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		fragment.setVideoFinished();
	}
}
