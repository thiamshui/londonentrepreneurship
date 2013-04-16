package com.londonentrepreneurshiponline;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Heatmap extends Activity {
	
	private double[] secs = new double[20];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heatmap);
		
		// Getting reference to the button btn_chart
        Button btnChart = (Button) findViewById(R.id.btn_chart);
        
     // Defining click event listener for the button btn_chart
        OnClickListener clickListener = new OnClickListener() {
 
            @Override
            public void onClick(View v) {
                
                openChart();
            }
        };
 
       
        btnChart.setOnClickListener(clickListener);
	}

	private void openChart(){
		int[] x = { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19 };
		int[] clicks = new int [20];
		
		
		for(int a = 0; a<= secs.length; a++){
			double timeGap = 0;
			secs[a] = a*timeGap;
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
        		clicksRenderer.setColor(Color.CYAN);
        clicksRenderer.setFillPoints(true);
        clicksRenderer.setLineWidth(1);
        clicksRenderer.setDisplayChartValues(true);
        
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("IMPORTANCE CHART");
        multiRenderer.setChartTitleTextSize(30);
        multiRenderer.setXTitle("Number of seconds (s)");
        multiRenderer.setBarSpacing(0.05);
        multiRenderer.setAxisTitleTextSize(24);
        multiRenderer.setLabelsTextSize(15);
        multiRenderer.setYTitle("Importance (Clicks)");
        multiRenderer.setZoomButtonsVisible(false);
        for(int i=0; i< x.length;i++){
            multiRenderer.addXTextLabel(i, "" + secs[i]);
        }
        
        multiRenderer.addSeriesRenderer(clicksRenderer);
        
        Intent intent = ChartFactory.getBarChartIntent(getBaseContext(), dataset, multiRenderer, Type.DEFAULT);
        
        startActivity(intent);
        
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.heatmap, menu);
		return true;
	}

}
