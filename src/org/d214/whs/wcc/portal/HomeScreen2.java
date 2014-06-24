package org.d214.whs.wcc.portal;



import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.io.IOUtils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.d214.whs.wcc.portal.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class HomeScreen2 extends ListFragment{
	
	private ProgressBar load;
	public static int refresh;
	public static String seminar = "test";
	public static String date, message;
	public static String eventsListString;
	public static UpcomingEvent[] listEvents;
	private TextView dateLabel, messageLabel, daysLeftLabel, reload;
	private View background, breaker, shadow;
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
		dateLabel = (TextView) getView().findViewById(R.id.labelEventDate);
		messageLabel = (TextView) getView().findViewById(R.id.labelEventMessage);
		daysLeftLabel = (TextView) getView().findViewById(R.id.labelDaysLeft);
		reload = (TextView) getView().findViewById(R.id.reload1);
		background = (View) getView().findViewById(R.id.eventView);
		breaker = (View) getView().findViewById(R.id.eventBreaker);
		shadow = (View) getView().findViewById(R.id.evetShadow2);
		final View online = (View) getView().findViewById(R.id.onlineBox);
		listEvents = new UpcomingEvent[4];
		
		int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		refresh = WHS_Home.refreshDate;
		int r = WHS_Home.refreshDate;
		
		if (WHS_Home.connected){
			GetJSon getIt = new GetJSon();
			getIt.execute("");
		} else{
			load.setVisibility(0X00000004);
			reload.setVisibility(0);
		}	
		
		reload.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				load.setVisibility(0);
				reload.setVisibility(0X00000004);
				WHS_Home.checkConnection();
				if (WHS_Home.connected){
					GetJSon getIt = new GetJSon();
					getIt.execute("");
				} else{
					load.setVisibility(0X00000004);
					reload.setVisibility(0);
				}	
			}
		});
		
		online.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://whs.d214.org/"));
				startActivity(intent);
			}
		});
				
	}

	public class GetJSon extends AsyncTask <String, Integer, Long>{

		//private UpcomingEvent[] listEvents;

		@Override
		protected Long doInBackground(String... params) {
			try{
				URL upcomingEvents = new URL("http://wcc-mobile-app.appspot.com/upcoming_events");
				HttpURLConnection connection = (HttpURLConnection) upcomingEvents.openConnection();

				connection.connect();

				InputStream inputStream = connection.getInputStream();
				if (connection.getResponseCode() == 200 && inputStream != null) {
					String responseJson = IOUtils.toString(inputStream);

					Gson gson = new GsonBuilder().create();
					Type type = new TypeToken<UpcomingEvent[]>(){}.getType();
					if(!responseJson.equals("\"\""))
					listEvents = gson.fromJson(responseJson, type);
				} else {
					listEvents = new UpcomingEvent[4];
					listEvents[0] = new UpcomingEvent("Wendesday, April 16, 2014", "Athletic Booster's Meeting");
					listEvents[1] = new UpcomingEvent("Wendesday, April 16, 2014", "JAZZAPALOOZA");
					listEvents[2] = new UpcomingEvent("Thursday, April 17, 2014", "Gold Seminar");
					listEvents[3] = new UpcomingEvent("Friday, April 18, 2014", "No School!!!");
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
			
			SharedPreferences settings = getActivity().getSharedPreferences("WCCAppPrefs", 0);
			SharedPreferences.Editor editor = settings.edit();
			
			load.setVisibility(0X00000004);
			if(listEvents.length == 0 || listEvents == null){
				reload.setVisibility(0);
				reload.setText("No upcomming events");
			}
			else{
				UpcomingEvent event = listEvents[0];
				if(event == null || event.eventDate == null || event.eventDate.equals("")){
				reload.setVisibility(0);
				reload.setText("No upcomming events (click to reload)");
				}else{
				dateLabel.setText(event.eventDate);
				messageLabel.setText(event.eventTitle);
				reload.setVisibility(0X00000004);
				
				for (UpcomingEvent e: listEvents){
					if(e.eventDate.substring(0, 2).equalsIgnoreCase("th")){
						if(e.eventTitle.contains("Gold")){
							editor.putInt("late", 1);
							break;
						}
						else if(e.eventTitle.contains("Blue")){
							editor.putInt("late", 2);
							break;
						}
						else if(e.eventTitle.contains("Exam")){
							editor.putInt("late", 3);
							break;
						}
					}
				}
				editor.commit();
			
			SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dddd, yyyy");
			Calendar cal = Calendar.getInstance();
			String date = sdf.format(cal.getTime());
			int difference = 4;
			if (event.eventDate.equalsIgnoreCase("ERRORERROR")){
				daysLeftLabel.setText("error");
			}else{				
				difference = getDateDif(date, event.eventDate);
				
				if (difference == 0)
					daysLeftLabel.setText("Today");
				else if (difference == 1)
					daysLeftLabel.setText("Tomorrow");
				else if (difference < 0)
					daysLeftLabel.setText("Complete");
				else
					daysLeftLabel.setText(String.valueOf(difference) + " days left");
			}
			background.setVisibility(0);
			dateLabel.setVisibility(0);
			messageLabel.setVisibility(0);
			daysLeftLabel.setVisibility(0);
			breaker.setVisibility(0);
			shadow.setVisibility(0);
			
			//EventsListAdapter adapter = new EventsListAdapter(getListView().getContext(), temp);
			//ArrayAdapter<EventsListAdapter> adapter = new ArrayAdapter<EventsListAdapter>(Events.this, android.R.layout.simple_list_item_1, temp.toArray(new EventsListAdapter[]{}));
			
			EventsListAdapter adapter = new EventsListAdapter(getListView().getContext(), temp);
			
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(Events.this, android.R.layout.simple_list_item_1, temp.);
			setListAdapter(adapter);
			}
		}
		
	}
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		UpcomingEvent event = listEvents[position];
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dddd, yyyy");
		Calendar cal = Calendar.getInstance();
		String date = sdf.format(cal.getTime());
		if (event.eventDate.equalsIgnoreCase("ERRORERROR")){
			daysLeftLabel.setText("error");
		}else{
			int difference = getDateDif(date, event.eventDate);
			
			if (difference == 0)
				daysLeftLabel.setText("Today");
			else if (difference == 1)
				daysLeftLabel.setText("Tomorrow");
			else if (difference < 0)
				daysLeftLabel.setText("Complete");
			else
				daysLeftLabel.setText(String.valueOf(difference) + " days left");
		}
		dateLabel.setText(event.eventDate);
		messageLabel.setText(event.eventTitle);
	}
	
	 private static int getDateDif(String date1, String date2)// throws Exception
	    {
	        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dddd, yyyy");
	        Date d1 = null;
	        Date d2 = null;
	        try
	        {
	            d1 = sdf.parse(date1);
	            d2 = sdf.parse(date2);
	        }
	        catch (Exception exc) {}
	        
	        GregorianCalendar gc1 = new GregorianCalendar();
	        gc1.setTime(d1);
	        
	        GregorianCalendar gc2 = new GregorianCalendar();
	        gc2.setTime(d2);
	        
	        int days1 = gc1.get(Calendar.DAY_OF_YEAR);
	        int days2 = gc2.get(Calendar.DAY_OF_YEAR);
	        int difference = days2 - days1;
	        return difference;
	    }
	
}








