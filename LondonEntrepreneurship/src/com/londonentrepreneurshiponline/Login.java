package com.londonentrepreneurshiponline;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

	Button logon;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		logon = (Button) findViewById(R.id.signin);
		logon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText passText = (EditText) findViewById(R.id.tpassword);
				String getpass = passText.getText().toString();

				EditText usernameText = (EditText) findViewById(R.id.tusername);
				String getusername = usernameText.getText().toString();
				
				md5(getpass);
				
				
			}
		});
	}

	public String md5(String s) {
		
		String hash = "";
		
		try {
			MessageDigest encryptpass = java.security.MessageDigest
					.getInstance("MD5");
			encryptpass.update(s.getBytes(), 0, s.length());
		
			hash = new BigInteger(1, encryptpass.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
