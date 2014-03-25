package com.example.whs;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class WHS extends Activity {

	
	protected DialogInterface.OnClickListener mDialogListener = new DialogInterface.OnClickListener() {
 
		@Override
		public void onClick(DialogInterface dialog, int which) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			switch(which) {
			case 0:
				intent.setData(Uri.parse("http://whs.d214.org/"));
				startActivity(intent);
				break;
			case 1:
				intent.setData(Uri.parse("http://whs.d214.org/events/month.aspx"));
				startActivity(intent);
				break;
			case 2:
				intent.setData(Uri.parse("https://hl.d214.org/homelogic/"));
				startActivity(intent);
				break;
			case 3:
				intent.setData(Uri.parse("http://moodle2.d214.org/"));
				startActivity(intent);
				break;
			}
		}
	};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whs);
		
		final Button bnEvents = (Button) findViewById(R.id.buttonEvents);
		final Button bnAnnouncements = (Button) findViewById(R.id.buttonAnnouncements);
		final Button bnNews = (Button) findViewById(R.id.buttonNews);
		final Button bnWebsite = (Button) findViewById(R.id.buttonWebsite);
		
		SharedPreferences settings = getSharedPreferences("WCCAppPrefs", 0);
		
		if (settings.getString("language", "english").equals("english"))
		{
			bnEvents.setText(R.string.bEvents);
			bnAnnouncements.setText(R.string.bAnnouncements);
			bnNews.setText(R.string.bTopNews);
			bnWebsite.setText(R.string.bWebsite);
		} else if(settings.getString("language", "english").equals("spanish"))
		{
			bnEvents.setText(R.string.bEvents_Spanish);
			bnAnnouncements.setText(R.string.bAnnouncements_Spanish);
			bnNews.setText(R.string.bTopNews_Spanish);
			bnWebsite.setText(R.string.bWebsite_Spanish);
		} else if (settings.getString("language", "english").equals("russian"))
		{
			bnEvents.setText(R.string.bEvents_Russian);
			bnAnnouncements.setText(R.string.bAnnouncements_Russian);
			bnNews.setText(R.string.bTopNews_Russian);
			bnWebsite.setText(R.string.bWebsite_Russian);
		}
		bnEvents.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WHS.this, Events.class);
				startActivity(intent);
				
			}
		});
		
		bnAnnouncements.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WHS.this, Announcements.class);
				startActivity(intent);
				
			}
		});

		bnNews.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(WHS.this, News.class);
		startActivity(intent);
		
			}
		});

		bnWebsite.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

					AlertDialog.Builder builder = new AlertDialog.Builder(WHS.this);
					builder.setItems(R.array.website_choices, mDialogListener);
					AlertDialog dialog = builder.create();
					dialog.show();
		
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.whs, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		final Button bnEvents = (Button) findViewById(R.id.buttonEvents);
		final Button bnAnnouncements = (Button) findViewById(R.id.buttonAnnouncements);
		final Button bnNews = (Button) findViewById(R.id.buttonNews);
		final Button bnWebsite = (Button) findViewById(R.id.buttonWebsite);
		
		SharedPreferences settings = getSharedPreferences("WCCAppPrefs", 0);
		SharedPreferences.Editor editor = settings.edit();

		if (item.getItemId() == R.id.action_English)
		{
			bnEvents.setText(R.string.bEvents);
			bnAnnouncements.setText(R.string.bAnnouncements);
			bnNews.setText(R.string.bTopNews);
			bnWebsite.setText(R.string.bWebsite);
			editor.putString("language", "english");
		}else if (item.getItemId() == R.id.action_Spanish)
		{
			bnEvents.setText(R.string.bEvents_Spanish);
			bnAnnouncements.setText(R.string.bAnnouncements_Spanish);
			bnNews.setText(R.string.bTopNews_Spanish);
			bnWebsite.setText(R.string.bWebsite_Spanish);
			editor.putString("language", "spanish");
		}else if (item.getItemId() == R.id.action_Russian)
		{
			bnEvents.setText(R.string.bEvents_Russian);
			bnAnnouncements.setText(R.string.bAnnouncements_Russian);
			bnNews.setText(R.string.bTopNews_Russian);
			bnWebsite.setText(R.string.bWebsite_Russian);
			editor.putString("language", "russian");
		}else
		{
			Intent intent = new Intent(WHS.this, ChangeSettings.class);
			startActivity(intent);
		}
		editor.commit();
		return true;    	
	}

	

}























