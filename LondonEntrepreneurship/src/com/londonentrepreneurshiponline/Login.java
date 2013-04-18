package com.londonentrepreneurshiponline;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

public class Login extends Activity {
	
	Button logon;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		logon = (Button) findViewById (R.id.signin);
		
		
		String x = "nilesh";
		md5(x);
	}

			public static String md5(String s) {
			    try {
			        // Create MD5 Hash
			        MessageDigest digest = java.security.MessageDigest
			                .getInstance("MD5");
			        digest.update(s.getBytes(), 0, s.length());
			        
			        String y = new BigInteger(1, digest.digest()).toString(16);
			    } catch (NoSuchAlgorithmException e) {
			        e.printStackTrace();
			    }
			    return "";
			}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
