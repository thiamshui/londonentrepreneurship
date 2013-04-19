package com.londonentrepreneurshiponline;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.londonentrepreneurshiponline.models.Video;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Heatmap extends Activity {
	
	private GraphicalView myChartView;
	private double[] secs = {0, 10, 20,30,40,50,60,70,80,
            90,100,110,120,130,140,150,160,170,
            180, 190};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heatmap);
		
		// Getting reference to the button btn_chart
		Video vid = (Video) getIntent().getSerializableExtra("video");
		int durationMs = getIntent().getIntExtra("videoDur", -1);
		openChart();
        
	}

	private void openChart(){
		
		Video videoObject = new Video();
		Video newVid = videoObject.getVideoById(11);
		
		
		// divide the video into 20 different parts, and creates heatmap
		// 20 different bars
		int[] x = { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19 };
		
		Video clicksObject = new Video();
		int[] clicks = clicksObject.getImportanceArray();
		
		
//		for(int a = 0; a<= secs.length; a++){
//			double timeGap = 0;
//			secs[a] = a*timeGap;
//		}
		
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
        //startActivity(intent);
        //myChartView.repaint();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.heatmap, menu);
		return true;
	}

}
