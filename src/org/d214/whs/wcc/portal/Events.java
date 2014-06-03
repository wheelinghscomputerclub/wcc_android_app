package org.d214.whs.wcc.portal;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.d214.whs.wcc.portal.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Events extends ListActivity {
	
	private ProgressBar load;
	private UpcomingEvent[] listEvents;

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

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		UpcomingEvent event = listEvents[position];

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		//LayoutInflater inflater = this.getLayoutInflater();
		
		builder.setTitle(event.eventDate);
		builder.setMessage(event.eventTitle);
		
		//final TextView dialogTitle = (TextView) findViewById(R.id.dialogTitle);
		//final TextView dialogMessage = (TextView) findViewById(R.id.dialogMessage);
		
		//builder.setView(inflater.inflate(R.layout.events_dialog, null));
		//builder.setCustomTitle(inflater.inflate(R.layout.dialog_title, null));
		
		//dialogTitle.setText("test");
		//dialogMessage.setText("testing");
		
		builder.setPositiveButton("Save Event", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		
		builder.setNegativeButton("Close", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
	

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

		finish();
		return true;
	}


	public class GetJSon extends AsyncTask <String, Integer, Long>{

		//private UpcomingEvent[] listEvents;

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