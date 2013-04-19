package com.londonentrepreneurshiponline.annotate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.londonentrepreneurshiponline.MainApplication;
import com.londonentrepreneurshiponline.R;
import com.londonentrepreneurshiponline.models.Annotation;

public class AnnotateTextActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_annotate_text);
		
//		annotate = (EditText) findViewById(R.id.textAnnot);
//		annotate.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				annotate.setBackgroundColor(getResources().getColor(android.R.color.));
//			}
//		});
	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.annotate_text, menu);
		return true;
	}
	
	public void discard(View v)
	{
		finish();
	}
	
	public void save(View v)
	{
		EditText saveAnnot = (EditText) findViewById(R.id.textAnnot);
		String text = saveAnnot.getText().toString();
		int timeSecs = getIntent().getIntExtra("pos", -1) / 1000;
		int userID = ((MainApplication) getApplication()).getLoggedOnUser();
		int videoId = getIntent().getIntExtra("videoId",-1);
		new createAnnotationTask().execute(text, timeSecs, userID, videoId);
		
		
	}
	
	protected class createAnnotationTask extends AsyncTask<Object,Void,String>
	{
		@Override
		protected String doInBackground(Object... params) {
			
			return Annotation.createAnnotation((String) params[0], (Integer) params[1], (Integer) params[2], (Integer) params[3]);
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			Toast toast = Toast.makeText(AnnotateTextActivity.this, "Annotation Saved.", 5000);
			toast.show();
			finish();
			
			
		}  			
	}
	
	

}
