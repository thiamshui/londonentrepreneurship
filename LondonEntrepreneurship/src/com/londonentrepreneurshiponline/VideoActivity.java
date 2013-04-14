package com.londonentrepreneurshiponline;

import com.londonentrepreneurshiponline.models.Video;

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
		VideoView vv = (VideoView) findViewById(R.id.videoView1);
		MediaController mc = new MediaController(this);
		mc.setMediaPlayer(vv);
		
		Video vid = Video.getVideoById(1);
		
		vv.setVideoURI(Uri.parse(vid.getUri()));
		vv.setMediaController(mc);
		
		vv.start();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video, menu);
		return true;
	}

}
