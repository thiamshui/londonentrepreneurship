package com.londonentrepreneurshiponline.annotate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.londonentrepreneurshiponline.R;

public class AnnotateTextActivity extends Activity {
	
	Button saving, discarding;
	EditText saveAnnot, annotate;
	
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
		saving = (Button) findViewById(R.id.Bsave);
		saving.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				saveAnnot = (EditText) findViewById(R.id.textAnnot);
				String newAnnot = saveAnnot.getText().toString();
			}
		});
		
		discarding = (Button) findViewById(R.id.Bdiscard);
		discarding.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.annotate_text, menu);
		return true;
	}

}
