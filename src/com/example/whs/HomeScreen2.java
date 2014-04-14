package com.example.whs;



import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class HomeScreen2 extends ListFragment{
	
	private ProgressBar load;
	public static String seminar = "test";
	public static String date, message;
	private UpcomingEvent[] listEvents;
	private TextView dateLabel, messageLabel;
	public String[] listAnnouncements = {
			"NO SCHOOL!",
	        "2.18.14 Tuesday",
	        "2.19.14 Wednesday",
	        "2.20.14 Thursday",
	        "2.21.14 Friday"
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_events,
				container, false);
		return rootView;
	}


	@Override
	public void onResume() {
		super.onResume();
		
		load = (ProgressBar) getView().findViewById(R.id.progressBar1);
		dateLabel = (TextView) getView().findViewById(R.id.labelEventMessage);
		messageLabel = (TextView) getView().findViewById(R.id.labelEventMessage);
		GetJSon getIt = new GetJSon();
		getIt.execute("");
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_list_item_1, listAnnouncements);
        //setListAdapter(adapter);
				
	}
	
	public class GetJSon extends AsyncTask <String, Integer, Long>{

		//private UpcomingEvent[] listEvents;

		@Override
		protected Long doInBackground(String... params) {
			try{
				URL upcomingEvents = new URL("http://1.wccsandbox.appspot.com/upcoming_events");
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

			for (UpcomingEvent event: listEvents){
				if(event.eventDate.substring(0, 2).equalsIgnoreCase("th")){
					seminar = event.toString();
				}
				
			}
			date = listEvents[0].eventDate;
			message = listEvents[0].eventTitle;
			
			load.setVisibility(0X00000004);
			
			//EventsListAdapter adapter = new EventsListAdapter(getListView().getContext(), temp);
			//ArrayAdapter<EventsListAdapter> adapter = new ArrayAdapter<EventsListAdapter>(Events.this, android.R.layout.simple_list_item_1, temp.toArray(new EventsListAdapter[]{}));
			
			EventsListAdapter adapter = new EventsListAdapter(getListView().getContext(), temp);
			
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(Events.this, android.R.layout.simple_list_item_1, temp.);
			setListAdapter(adapter);
		}
		
	}
	
	public static String getSeminar(){
		return seminar;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		UpcomingEvent event = listEvents[position];
		//date = event.eventDate;
		//message = event.eventTitle;
		dateLabel.setText(event.eventDate);
		messageLabel.setText(event.eventTitle);
	

	}
	
}


