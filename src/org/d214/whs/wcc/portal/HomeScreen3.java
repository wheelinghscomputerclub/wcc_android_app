package org.d214.whs.wcc.portal;


import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
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

public class HomeScreen3 extends ListFragment{
	
	public String[] listAnnouncements = {
			"NO SCHOOL!",
	        "2.18.14 Tuesday",
	        "2.19.14 Wednesday",
	        "2.20.14 Thursday",
	        "2.21.14 Friday"
	};
	public static DailyAnnouncements[] listAnn;
	private ProgressBar load;
	private TextView reload;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_announcements,
				container, false);
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		load = (ProgressBar) getView().findViewById(R.id.progressBar1);
		reload = (TextView) getView().findViewById(R.id.reload2);
		final View online = (View) getView().findViewById(R.id.onlineBox);
		 /*listAnn[0] = new DailyAnnouncements("Monday, March, 3 2014","Text message stuff things test words");
		 listAnn[1] = new DailyAnnouncements("Monday, March, 3 2014","Text message stuff things test words");
		 listAnn[2] = new DailyAnnouncements("Monday, March, 3 2014","Text message stuff things test words");
		 listAnn[3] = new DailyAnnouncements("Monday, March, 3 2014","Text message stuff things test words");
		*/
		
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
				intent.setData(Uri.parse("http://whs.d214.org/student_resources/daily_announcements.aspx"));
				startActivity(intent);
			}
		});
		
	}
	
	
	
	public class GetJSon extends AsyncTask <String, Integer, Long>{

		//private UpcomingEvent[] listEvents;

		@Override
		protected Long doInBackground(String... params) {
			
			
			try{
				URL upcomingEvents = new URL("http://wcc-mobile-app.appspot.com/daily_announcements");
				HttpURLConnection connection = (HttpURLConnection) upcomingEvents.openConnection();

				connection.connect();

				InputStream inputStream = connection.getInputStream();
				if (connection.getResponseCode() == 200 && inputStream != null) {
					String responseJson = IOUtils.toString(inputStream);

					Gson gson = new GsonBuilder().create();
					Type type = new TypeToken<DailyAnnouncements[]>(){}.getType();

					listAnn = gson.fromJson(responseJson, type);
				} else {
					listAnn = new DailyAnnouncements[4];
					listAnn[0] = new DailyAnnouncements("Wendesday, April 16, 2014", "Athletic Booster's Meeting");
					listAnn[1] = new DailyAnnouncements("Wendesday, April 16, 2014", "JAZZAPALOOZA");
					listAnn[2] = new DailyAnnouncements("Thursday, April 17, 2014", "Gold Seminar");
					listAnn[3] = new DailyAnnouncements("Friday, April 18, 2014", "This is a test");
				}
			} catch(Exception e){
			}	

			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);
			
			//List<String> temp = new ArrayList<String>();
			
			List<DailyAnnouncements> temp = new ArrayList<DailyAnnouncements>();
			
			load.setVisibility(0X00000004);
			reload.setVisibility(0X00000004);
			
			if(listAnn == null || listAnn.length == 0){
				reload.setVisibility(0);
				reload.setText("No announcements (click to reload)");
			}else{
				for (DailyAnnouncements event: listAnn){
					//temp.add(event.eventTitle);
					temp.add(event);
				}
				
				AnnouncementsListAdapter adapter = new AnnouncementsListAdapter(getListView().getContext(), temp);
				setListAdapter(adapter);
			}
		}
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		/*Intent intent = new Intent(getActivity().getApplicationContext(), Read.class);
		intent.putExtra("title", listAnn[position].date);
		intent.putExtra("details", listAnn[position].text);
		intent.putExtra("page", position);
		startActivity(intent);*/
		
		Intent intent = new Intent(getActivity().getApplicationContext(), News.class);
		//intent.putExtra("news", listNews);
		intent.putExtra("title", listAnn[position].date);
		intent.putExtra("details", listAnn[position].text);
		intent.putExtra("type", false);
		intent.putExtra("page", position);
		startActivity(intent);
		
	}
}











