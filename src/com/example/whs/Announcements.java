package com.example.whs;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Announcements extends ListActivity {
	
	public String[] listAnnouncements = {
			"NO SCHOOL!",
	        "2.18.14 Tuesday",
	        "2.19.14 Wednesday",
	        "2.20.14 Thursday",
	        "2.21.14 Friday"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_announcements);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listAnnouncements);
        setListAdapter(adapter);
	}
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    		
			Intent intent = new Intent(Announcements.this, Read.class);
			startActivity(intent);
    	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.announcements, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		Intent intent = new Intent(Announcements.this, WHS.class);
		startActivity(intent);
		return true;
	}

}








