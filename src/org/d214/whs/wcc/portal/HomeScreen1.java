package org.d214.whs.wcc.portal;


import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.whs.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class HomeScreen1 extends ListFragment{
	private ArrayList<WebSite> webSites = new ArrayList<WebSite>();
	public static String status = "";
	//private String[] webSites = new String[]{"HomePage", "HomeLogic", "Calendar", "Moodle"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.home_screen1,
				container, false);
		WebSite home = new WebSite("Homepage");
		WebSite grades = new WebSite("HomeLogic");
		WebSite calendar = new WebSite("Calendar");
		WebSite moodle = new WebSite("Moodle");
		WebSite family = new WebSite("Family Connection");
		WebSite sports = new WebSite("Athletic Page");
		
		webSites.add(home);
		webSites.add(grades);
		webSites.add(calendar);
		webSites.add(moodle);
		webSites.add(family);
		webSites.add(sports);
		
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//String sites = String.valueOf(R.array.website_choices_freshman);
		//{"HomePage", "HomeLogic", "Calendar", "Moodle"};
		
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_list_item_1, webSites);
		WebSitesAdapter adapter = new WebSitesAdapter(getListView().getContext(), webSites);
		setListAdapter(adapter);
		
		GetJSon getIt = new GetJSon();
		getIt.execute("");
	}
	
	public class GetJSon extends AsyncTask <String, Integer, Long>{

		//private UpcomingEvent[] listEvents;

		@Override
		protected Long doInBackground(String... params) {
			
			
			try{
				URL upcomingEvents = new URL("http://wcc-mobile-app.appspot.com/emergency");
				HttpURLConnection connection = (HttpURLConnection) upcomingEvents.openConnection();

				connection.connect();

				InputStream inputStream = connection.getInputStream();
				if (connection.getResponseCode() == 200 && inputStream != null) {
					String responseJson = IOUtils.toString(inputStream);

					Gson gson = new GsonBuilder().create();
					Type type = new TypeToken<String>(){}.getType();
					status = gson.fromJson(responseJson, type);
					
					//Toast toast = Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT);
					//toast.show();
					
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
			
			
			
		}
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		
		switch(position) {
		case 0:
			intent.setData(Uri.parse("http://whs.d214.org/"));
			break;
		case 1:
			intent.setData(Uri.parse("https://hl.d214.org/homelogic/"));
			break;
		case 2:
			intent.setData(Uri.parse("http://whs.d214.org/events/month.aspx"));
			break;
		case 3:
			intent.setData(Uri.parse("http://moodle2.d214.org/"));
			break;
		case 4:
			intent.setData(Uri.parse("http://whs.d214.org/student_resources/college_resources_temp.aspx"));
			break;
		case 5:
			intent.setData(Uri.parse("http://il.8to18.com/wheeling"));
			break;
		}
		
		//intent.setData(Uri.parse("http://whs.d214.org/"));
			
		startActivity(intent);
	

	}
}











