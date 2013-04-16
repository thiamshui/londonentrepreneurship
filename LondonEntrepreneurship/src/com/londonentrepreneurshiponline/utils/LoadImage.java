package com.londonentrepreneurshiponline.utils;

import java.io.InputStream;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

public class LoadImage{

	public static Drawable LoadImageFromWebOperations(String url)
    {
          try{
        InputStream is = (InputStream) new URL(url).getContent();
        Drawable d = Drawable.createFromStream(is, "src name");
        return d;
      }catch (Exception e) {
        System.out.println("Exc="+e);
        return null;
      }
    }
	
    public static Drawable widenImage(Drawable drawable, Context context){
		Bitmap bMap = ((BitmapDrawable)drawable).getBitmap(); 
		Drawable d = new BitmapDrawable(context.getResources(),getResizedBitmap(bMap,220,500));
		return d;
	}
	
	public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) { 
		int width = bm.getWidth(); 
		int height = bm.getHeight(); 
		float scaleWidth = ((float) newWidth) / width; 
		float scaleHeight = ((float) newHeight) / height; 
		Matrix matrix = new Matrix(); 
		matrix.postScale(scaleWidth, scaleHeight); 
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false); 
		return resizedBitmap; 
		}
}



