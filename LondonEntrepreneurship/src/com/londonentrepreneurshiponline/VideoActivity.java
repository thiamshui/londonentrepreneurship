package com.londonentrepreneurshiponline;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		VideoView vid = (VideoView) findViewById(R.id.videoView1);
		MediaController mc = new MediaController(this);
		mc.setMediaPlayer(vid);
		vid.setVideoURI(Uri.parse("http://www.londonentrepreneurshiponline.com/stream/yqqgv4v0snfohqsj.mp4"));
		vid.setMediaController(mc);
		
		vid.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video, menu);
		return true;
	}

}
