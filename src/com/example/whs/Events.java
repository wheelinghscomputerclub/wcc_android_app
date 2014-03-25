package com.example.whs;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Events extends ListActivity {
	
	private ProgressBar load;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		load = (ProgressBar) findViewById(R.id.progressBar1);
		
		GetJSon getIt = new GetJSon();
		getIt.execute("");

		//Toast toast = Toast.makeText(getApplicationContext(), "Can't find data", Toast.LENGTH_SHORT);
		//toast.show();

	}
	//test
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		

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
		finish();
		return true;
	}


	public class GetJSon extends AsyncTask <String, Integer, Long>{

		private UpcomingEvent[] listEvents;

		@Override
		protected Long doInBackground(String... params) {
			try{
				URL upcomingEvents = new URL("http://1.wccsandbox.appspot.com/wcc_prototype");
				HttpURLConnection connection = (HttpURLConnection) upcomingEvents.openConnection();

				connection.connect();

				InputStream inputStream = connection.getInputStream();
				if (connection.getResponseCode() == 200 && inputStream != null) {
					String responseJson = IOUtils.toString(inputStream);

					Gson gson = new GsonBuilder().create();
					Type type = new TypeToken<UpcomingEvent[]>(){}.getType();

					listEvents = gson.fromJson(responseJson, type);
				} else {
					listEvents = new UpcomingEvent[]{};
				}
			} catch(Exception e){			
			}	

			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);
			
			//List<String> temp = new ArrayList<String>();
			
			List<UpcomingEvent> temp = new ArrayList<UpcomingEvent>();
			
			for (UpcomingEvent event: listEvents){
				//temp.add(event.eventTitle);
				temp.add(event);
			}
			load.setVisibility(0X00000004);
			
			//EventsListAdapter adapter = new EventsListAdapter(getListView().getContext(), temp);
			//ArrayAdapter<EventsListAdapter> adapter = new ArrayAdapter<EventsListAdapter>(Events.this, android.R.layout.simple_list_item_1, temp.toArray(new EventsListAdapter[]{}));
			
			EventsListAdapter adapter = new EventsListAdapter(getListView().getContext(), temp);
			
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(Events.this, android.R.layout.simple_list_item_1, temp.toArray(new String[]{}));
			setListAdapter(adapter);
		}

	}









}