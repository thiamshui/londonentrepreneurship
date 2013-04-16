package com.londonentrepreneurshiponline;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.londonentrepreneurshiponline.models.Video;

public class VideoActivity extends FragmentActivity implements SeekBar.OnSeekBarChangeListener,OnPreparedListener {

	private VideoView vv;
	private SeekBar seekbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		setContentView(R.layout.activity_video);
		
		/*vv = (VideoView) findViewById(R.id.videoView1);
		
		
		MediaController mc = new MediaController(this);
		mc.setMediaPlayer(vv);
		
		
		
		seekbar = (SeekBar) findViewById(R.id.seekBar1);
		if(seekbar != null)
		{
			seekbar.setOnSeekBarChangeListener(this);
			vv.setOnPreparedListener(this);
		}
		vv.setMediaController(mc);

		new loadVideoTask().execute(1);*/

		Intent myIntent= getIntent();
		int id = myIntent.getIntExtra("videoId", 0);
		new loadVideoTask().execute(id);

	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		//setContentView(R.layout.activity_video);
	}
	
	
	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		seekbar.setMax(vv.getDuration());
		seekbar.postDelayed(updateSeekBar, 1000);
	}
	
	private Runnable updateSeekBar = new Runnable() {
		public void run()
		{
			if(seekbar != null)
			{
				seekbar.setProgress(vv.getCurrentPosition());
			}
			if(vv.isPlaying())
				seekbar.postDelayed(updateSeekBar, 1000);
		};
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video, menu);
		return true;
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		if(fromUser)
			vv.seekTo(progress);
	}
	
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
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
			vv.setVideoURI(Uri.parse(result.getUri()));
		
			vv.start();
			
		}
	
	}

}
