package com.londonentrepreneurshiponline;

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
import android.util.SparseArray;
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

	VideoView vv;
	private SparseArray<String> annotations;
	private TextView caption;
	private boolean videoFinished = false;
	private int prevCaptionTime = 0, lastVideoDuration = 0;
	private Video vid;

	public VideoFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		if(savedInstanceState != null)
			lastVideoDuration = savedInstanceState.getInt("videoPos");
			
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
		

		Intent myIntent= getActivity().getIntent();
		vid = (Video) myIntent.getSerializableExtra("video");
		new loadVideoTask().execute(vid.getId());
		
		vv.setVideoURI(Uri.parse(vid.getUri()));
		if(lastVideoDuration != 0)
			vv.seekTo(lastVideoDuration);
		vv.start();
		
		return view;
	}

	protected class loadVideoTask extends AsyncTask<Integer,Void,Void>	{
		@Override
		protected Void doInBackground(Integer... params) {
			annotations = Annotation.getAnnotationsByVideo(params[0]);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			Handler handler = new Handler();
			handler.postDelayed(updateAnnotations, 1000);

		}
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		caption.setVisibility(View.INVISIBLE);
		
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
				caption.setVisibility(View.VISIBLE);
				prevCaptionTime = currentPos;
			}
			
			if(currentPos < prevCaptionTime || currentPos - prevCaptionTime > 5)
			{
				caption.setText("");
				caption.setVisibility(View.INVISIBLE);
			}
			
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
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("videoPos",vv.getCurrentPosition());
	}
	
	public void reReadAnnotations()
	{
		new loadVideoTask().execute(vid.getId());
	}

}
