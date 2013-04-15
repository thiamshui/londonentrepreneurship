package com.londonentrepreneurshiponline;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.londonentrepreneurshiponline.models.Video;

public class VideoActivity extends Activity implements SeekBar.OnSeekBarChangeListener,OnPreparedListener {

	private VideoView vv;
	private SeekBar seekbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		vv = (VideoView) findViewById(R.id.videoView1);
		MediaController mc = new MediaController(this);
		mc.setMediaPlayer(vv);
		vv.setOnPreparedListener(this);
		
		seekbar = (SeekBar) findViewById(R.id.seekBar1);
		seekbar.setOnSeekBarChangeListener(this);
		vv.setMediaController(mc);
		new loadVideoTask().execute(1);
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
