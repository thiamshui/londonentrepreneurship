package com.londonentrepreneurshiponline;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.londonentrepreneurshiponline.models.Video;

public class Heatmap extends Activity {
	
	private GraphicalView myChartView;
	private double[] secs = new double [20];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heatmap);
		
		openChart();
        
	}

	public void openChart(){
		Video vid = (Video) getIntent().getSerializableExtra("video");
		Log.d("test",vid.getDesc());
		int durationMs = getIntent().getIntExtra("videoDur", -1);
		
		// divide the video into 20 different parts, and creates heatmap
		// 20 different bars
		int[] x = { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19 };

		
		int[] clicks = vid.getImportanceArray();
		
		double durationPerSection = durationMs/20;
		for(int a = 1; a< secs.length; a++){
			secs[a] = a*durationPerSection/1000;
		}
		
		// Creating an  XYSeries for clicks
        XYSeries clicksSeries = new XYSeries("Clicks");
        
        for(int i=0;i<x.length;i++){
            clicksSeries.add(i,clicks[i]);
        }
        
     
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding clicks Series to the dataset
        dataset.addSeries(clicksSeries);
		
        
        
        XYSeriesRenderer clicksRenderer = new XYSeriesRenderer();
        clicksRenderer.setColor(Color.BLUE);
        clicksRenderer.setLineWidth(1);
        clicksRenderer.setFillPoints(true);
        clicksRenderer.setDisplayChartValues(true);
        
        XYMultipleSeriesRenderer allclicks = new XYMultipleSeriesRenderer();
        allclicks.setBarSpacing(0.05);
        allclicks.setChartTitleTextSize(30);
        allclicks.setChartTitle("IMPORTANCE CHART");
        
        allclicks.setXTitle("Number of seconds (s)");
        allclicks.setXLabels(0);
        allclicks.setAxisTitleTextSize(24);
        allclicks.setLabelsTextSize(10);
        allclicks.setYTitle("Importance (Clicks)");
        allclicks.setZoomButtonsVisible(false);
        for(int i=0; i< x.length;i++){
        	allclicks.addXTextLabel(i, "" + secs[i]);
        }
        
        allclicks.addSeriesRenderer(clicksRenderer);
        allclicks.setClickEnabled(true);
        allclicks.setSelectableBuffer(100);
        Intent intent = ChartFactory.getBarChartIntent(getBaseContext(), dataset, allclicks, Type.DEFAULT);
        startActivity(intent);
        
        // this part below was programmed to be able to click on the bars itself
        // and therefore this would take you straight to that part of the video.
        // this didn't work out. 
        
        myChartView = ChartFactory.getBarChartView(this, dataset, allclicks, Type.DEFAULT);
        myChartView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SeriesSelection seriesSelection = myChartView.getCurrentSeriesAndPoint();
				if (seriesSelection == null) {
		            Toast.makeText(Heatmap.this, "None of the bars were clicked", Toast.LENGTH_SHORT)
		                .show();
		          } else {
		            Toast.makeText(
		                Heatmap.this,
		                "Chart element in series index " + seriesSelection.getSeriesIndex()
		                    + " data point index " + seriesSelection.getPointIndex() + " was clicked", Toast.LENGTH_SHORT).show();
		            myChartView.repaint();
		          }
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.heatmap, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		finish();
	}

}
