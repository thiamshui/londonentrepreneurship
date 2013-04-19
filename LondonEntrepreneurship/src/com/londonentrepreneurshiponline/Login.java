package com.londonentrepreneurshiponline;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.londonentrepreneurshiponline.models.Member;

public class Login extends Activity {

	Button logon;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		logon = (Button) findViewById(R.id.signin);
	}
	
	public void signIn(View v)
	{
		EditText passText = (EditText) findViewById(R.id.tpassword);
		EditText usernameText = (EditText) findViewById(R.id.tusername);
		Button btn = (Button) findViewById(R.id.signin);
		btn.setText("Signing In...");
		btn.setEnabled(false);
		new authenticateTask().execute(usernameText.getText().toString(),md5(passText.getText().toString()));
				
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
	
	protected class authenticateTask extends AsyncTask<String,Void,Member>
	{
		@Override
		protected Member doInBackground(String... params) {
			Member m = Member.authenticate(params[0],params[1]);
			
			return m;
		}
		
		@Override
		protected void onPostExecute(Member result) {
			// TODO Auto-generated method stub
			if(result == null)
			{
				// Shake animation reference: http://stackoverflow.com/questions/10956454/android-r-anim-shake-not-found
				// http://www.edumobile.org/android/android-programming-tutorials/shake-animation-example/
				Button btn = (Button) findViewById(R.id.signin);
				btn.setText("SIGN IN");
				btn.setEnabled(true);
				
				Animation shake = AnimationUtils.loadAnimation(Login.this, R.anim.shake);
				findViewById(R.id.tpassword).startAnimation(shake);
				findViewById(R.id.tusername).startAnimation(shake);
				Toast toast = Toast.makeText(Login.this, "Invalid username/password.", 5000);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
			else
			{
				Toast.makeText(Login.this, "Sign-in Successful", 5000).show();
				
				((MainApplication) Login.this.getApplication()).setLoggedOnUser(result.getId());
				setResult(RESULT_OK,new Intent());
				finish();
			}

		}
	
	}

}
