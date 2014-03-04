package com.example.whs;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Events extends ListActivity {
	
	public String[] listEvents = {
			"THURSDAY, FEBRUARY 20, 2014",
	        "TUESDAY, FEBRUARY 25, 2014",
	        "THURSDAY, FEBRUARY 27, 2014",
	        "THURSDAY, FEBRUARY 27, 2014",
	        "FEBRUARY 28, 2014",
	        "MARCH 01, 2014",
	        "MARCH 06, 2014",
	        "MARCH 06, 2014"
	        
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listEvents);
        setListAdapter(adapter);
	}
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    		
			Intent intent = new Intent(Events.this, Read.class);
			startActivity(intent);
    	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.events, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		Intent intent = new Intent(Events.this, WHS.class);
		startActivity(intent);
		return true;
	}
}














