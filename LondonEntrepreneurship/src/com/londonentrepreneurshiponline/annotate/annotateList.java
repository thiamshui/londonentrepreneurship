package com.londonentrepreneurshiponline.annotate;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.londonentrepreneurshiponline.R;
import com.londonentrepreneurshiponline.SearchList;
import com.londonentrepreneurshiponline.VideoFragment;
import com.londonentrepreneurshiponline.models.Annotation;
import com.londonentrepreneurshiponline.models.Member;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class annotateList extends Activity implements OnClickListener{
	private ArrayList<Annotation> annotationList;
	private String member[];
	private ArrayList<Annotation> orderedList;
    private int incomingId;

	@Override
	protected void onCreate(Bundle savedInstanceState){	
		super.onCreate(savedInstanceState);

		//incomingId = getIntent().getIntExtra("Id", 1);
		new loadAnnotationList().execute(11);

	}
	
	protected class loadAnnotationList extends AsyncTask<Integer,Void,Void>	{		
		@Override
		protected Void doInBackground(Integer... params) {
			annotationList = Annotation.getAnnotationsListByVideo(params[0]);
			member = new String[annotationList.size()];
			
			for(int i = 0; i<= annotationList.size()-1; i++){
				member[i] = Member.getUsernameById(annotationList.get(i).getUserID());
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			if(annotationList.size() != 0)
			 orderAnnotations();	
			else noAnnotationsMessage();
		}
	}
	
	public void orderAnnotations(){
		orderedList = new ArrayList<Annotation>();	
		
		while(annotationList.size() != 0){
			int min = annotationList.get(0).getTimeSecs();
			Annotation minAnnotation = annotationList.get(0);
			
			for(int i = 0; i<= annotationList.size()-1; i++){
		     	if(annotationList.get(i).getTimeSecs() < min){
			     	minAnnotation = annotationList.get(i);
			     	min = minAnnotation.getTimeSecs();
		         }
		     }
		    orderedList.add(minAnnotation);
		    annotationList.remove(minAnnotation);
		}
		
		setCommentsLayout();
	}
	
	public void setCommentsLayout(){
		setContentView(R.layout.gridview);
		LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(
			    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
	    paramsText.topMargin=20;
	    paramsText.rightMargin = 10;
	    paramsText.leftMargin = 40;
		
		
		ScrollView scrollview = (ScrollView) findViewById(R.id.scrollview1);
		LinearLayout ll = new LinearLayout(annotateList.this);
		ll.setOrientation(LinearLayout.VERTICAL);
		scrollview.addView(ll);
		
		for (int i = 0; i <= orderedList.size() - 1; i++) {
			LinearLayout row = new LinearLayout(annotateList.this);
			row.setLayoutParams(new LayoutParams(
			LayoutParams.WRAP_CONTENT,
			LayoutParams.WRAP_CONTENT));
		
			TextView timetv = new TextView(annotateList.this);
			int c = orderedList.get(i).getTimeSecs() % 60;
			int k = (orderedList.get(i).getTimeSecs())/60;
			if(c<10)
		    	timetv.setText(String.valueOf(k)+"."+String.valueOf(c)+"  ");
			else 
				timetv.setText(String.valueOf(k)+"."+String.valueOf(c));
			timetv.setId(i);
			timetv.setLayoutParams(paramsText);
			timetv.setOnClickListener(annotateList.this);
			row.addView(timetv);
			
			TextView texttv = new TextView(annotateList.this);
			texttv.setText(Html.fromHtml(orderedList.get(i).getText() + "<i>" 
			+ " by "+ member[i] + "</i>"));
			texttv.setId(i);
			texttv.setLayoutParams(paramsText);
			texttv.setOnClickListener(annotateList.this);
			row.addView(texttv);

			ll.addView(row);
		}						
		
	}
	
	public void noAnnotationsMessage(){
		    
		    TextView nothing = new TextView(this);
		    nothing.setText("This video has no annotations");
		    nothing.setGravity(17);
		    this.setContentView(nothing);
	}
	
	@Override
	public void onClick(View v) {
     int time = orderedList.get(v.getId()).getTimeSecs();
     int id = 11;
     Intent myIntent = new Intent(this, VideoFragment.class);
     myIntent.putExtra("milliSeconds", 600);
     myIntent.putExtra("id", id);
     startActivity(myIntent);
	}
}
