package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.ActionBar;

import com.codepath.apps.restclienttemplate.models.SampleModel;
import com.codepath.apps.restclienttemplate.models.SampleModelDao;
import com.codepath.oauth.OAuthLoginActionBarActivity;

public class LoginActivity extends OAuthLoginActionBarActivity<TwitterClient> {

	SampleModelDao sampleModelDao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		final SampleModel sampleModel = new SampleModel();
		sampleModel.setName("CodePath");

		sampleModelDao = ((TwitterApp) getApplicationContext()).getMyDatabase().sampleModelDao();

		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				sampleModelDao.insertModel(sampleModel);
			}
		});

		// Define ActionBar object
		ActionBar actionBar;
		actionBar = getSupportActionBar();

		// Define ColorDrawable object and parse color
		// using parseColor method
		// with color hash code as its parameter
		ColorDrawable colorDrawable
				= new ColorDrawable(Color.parseColor("#ff1da1f2"));

		// Set BackgroundDrawable
		actionBar.setBackgroundDrawable(colorDrawable);
	}



	// Inflate the menu; this adds items to the action bar if it is present.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"
	@Override
	public void onLoginSuccess() {
		Log.i("rkpkp", "login successful");
		Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);
	}

	// OAuth authentication flow failed, handle the error
	// i.e Display an error dialog or toast
	@Override
	public void onLoginFailure(Exception e) {
		e.printStackTrace();
	}

	// Click handler method for the button used to start OAuth flow
	// Uses the client to initiate OAuth authorization
	// This should be tied to a button used to login
	public void loginToRest(View view) {
		getClient().connect();
	}


	public void onLogoutSuccess() {
		Log.i("Logout", "Logout successful");
		Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);
	}

}



