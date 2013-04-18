package com.londonentrepreneurshiponline;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import com.londonentrepreneurshiponline.models.Video;

public class VideoActivity extends FragmentActivity {

	private VideoView vv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		setContentView(R.layout.activity_video);
		TextView title = (TextView) findViewById(R.id.textView2);
//		vv = (VideoView) findViewById(R.id.videoView1);
//		
//		
//		MediaController mc = new MediaController(this);
//		mc.setMediaPlayer(vv);
//		
//		
//		
//		seekbar = (SeekBar) findViewById(R.id.seekBar1);
//		if(seekbar != null)
//		{
//			seekbar.setOnSeekBarChangeListener(this);
//			vv.setOnPreparedListener(this);
//		}
//		vv.setMediaController(mc);
//
//		//new loadVideoTask().execute(1);

		Intent myIntent= getIntent();
		int id = myIntent.getIntExtra("videoId", 1);
		new loadVideoTask().execute(id);

	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		//setContentView(R.layout.activity_video);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video, menu);
		return true;
	}
	
	protected class loadVideoTask extends AsyncTask<Integer,Void,Video>
	{
		@Override
		protected Video doInBackground(Integer... params) {
			Video vid = Video.getVideoById(params[0]);
			return vid;
		}
		
		@Override
		protected void onPostExecute(Video result) {
			// TODO Auto-generated method stub
<<<<<<< HEAD
			vv.setVideoURI(Uri.parse(result.getUri()));		
			vv.start();
=======
			Log.d("test",result.getDesc());
			//vv.setVideoURI(Uri.parse(result.getUri()));
		
			//vv.start();
>>>>>>> branch 'master' of https://github.com/thiamshui/londonentrepreneurship.git
			
		}
	
	}

}
