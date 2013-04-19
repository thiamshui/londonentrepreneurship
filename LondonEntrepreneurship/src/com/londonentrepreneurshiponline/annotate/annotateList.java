package com.londonentrepreneurshiponline.annotate;

import java.util.ArrayList;

import com.londonentrepreneurshiponline.R;
import com.londonentrepreneurshiponline.models.Annotation;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class annotateList extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		ArrayList<String>str = new ArrayList<String>();
		str.add("12:45");
		str.add("Richard");
		str.add("here i am");
		
		TextView video = (TextView) findViewById(R.id.textview0);
		
		GridView grid = (GridView) findViewById(R.id.gridView1);
		grid.setNumColumns(3);
						
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, str);

		grid.setAdapter(adapter);
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Toast.makeText(getApplicationContext(),
						((TextView) arg1).getText(), Toast.LENGTH_SHORT).show();
				
			}
		});
	
	}
}
