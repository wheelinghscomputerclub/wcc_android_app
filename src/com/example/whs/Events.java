package com.example.whs;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Events extends ListActivity {
	
	/*public String[] listEvents = {
			"THURSDAY, FEBRUARY 20, 2014",
	        "TUESDAY, FEBRUARY 25, 2014",
	        "THURSDAY, FEBRUARY 27, 2014",
	        "THURSDAY, FEBRUARY 27, 2014",
	        "FEBRUARY 28, 2014",
	        "MARCH 01, 2014",
	        "MARCH 06, 2014",
	        "MARCH 06, 2014"
	};*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		try{
		URL upcomingEvents = new URL("http://1.wccsandbox.appspot.com/wcc_prototype");
		HttpURLConnection connection = (HttpURLConnection) upcomingEvents.openConnection();
		
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		
		byte[] response = new byte[inputStream.available()];
		
		inputStream.read(response);
		String responseJSon = new String(response);
			
		Gson gson = new GsonBuilder().create();
		//gson.fromJson(response, String[].class);
		Type type = new TypeToken<String[]>(){}.getType();
		
		String[] listEvents = gson.fromJson(responseJSon, type);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listEvents);
        setListAdapter(adapter);
		} catch(Exception e)
		{
			Toast toast = Toast.makeText(getApplicationContext(), "Can't find data", Toast.LENGTH_SHORT);
			toast.show();
			e.printStackTrace();
		}
		
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














