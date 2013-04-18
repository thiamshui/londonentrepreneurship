package com.londonentrepreneurshiponline;

import java.util.HashMap;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.londonentrepreneurshiponline.models.Annotation;
import com.londonentrepreneurshiponline.models.Video;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class VideoFragment extends Fragment implements OnPreparedListener,OnCompletionListener {

	private VideoView vv;
	private HashMap<Integer,String> annotations;
	private TextView caption;
	private boolean videoFinished = false;
	private int prevCaptionTime = 0;

	public VideoFragment() {
		// Required empty public constructor
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_video, container, false);
		
		vv = (VideoView) view.findViewById(R.id.videoView1);
		caption = (TextView) view.findViewById(R.id.textView1);
		MediaController mc = new MediaController(view.getContext());
		mc.setMediaPlayer(vv);
		vv.setOnPreparedListener(this);
		vv.setOnCompletionListener(this);
		
		vv.setMediaController(mc);
		
//		return inflater.inflate(R.layout.fragment_video, container, false);
		Intent myIntent= getActivity().getIntent();
		int id = myIntent.getIntExtra("videoId", 1);
		Video vid = (Video) myIntent.getSerializableExtra("video");
		Log.d("test",vid.getDesc());
		new loadVideoTask().execute(vid.getId());
		
		return view;
	}
	
	protected class loadVideoTask extends AsyncTask<Integer,Void,Video>
	{
		@Override
		protected Video doInBackground(Integer... params) {
			Video vid = Video.getVideoById(params[0]);
			annotations = Annotation.getAnnotationsByVideo(params[0]);
			return vid;
		}
		
		@Override
		protected void onPostExecute(Video result) {
			// TODO Auto-generated method stub
			vv.setVideoURI(Uri.parse(result.getUri()));
			vv.start();
			
		}
	
	}
	
	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		vv.getCurrentPosition();
		Handler handler = new Handler();
		handler.post(updateAnnotations);
	}
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		videoFinished = true;
	}
	
	protected Runnable updateAnnotations = new Runnable() {
		public void run()
		{
			int currentPos = vv.getCurrentPosition() / 1000;
			String text = "";
			if((text = annotations.get(currentPos)) != null)
			{
				caption.setText(text);
				prevCaptionTime = currentPos;
			}
			
			if(currentPos < prevCaptionTime || currentPos - prevCaptionTime > 5)
				caption.setText("");
			
			if(!videoFinished)
			{
				new Handler().postDelayed(updateAnnotations, 1000);
			}
		}
	};
	
	public void captionTouch()
	{
		Log.d("test","TOUCH");
	}

}
