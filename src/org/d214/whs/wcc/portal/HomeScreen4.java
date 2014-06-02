package org.d214.whs.wcc.portal;


import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.d214.whs.wcc.portal.HomeScreen2.GetJSon;

import android.content.Intent;
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

import com.example.whs.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class HomeScreen4 extends ListFragment{
	
	public String[] newsList = {
			"Wheeling High School Orchesis Annual Concert",
	        "Wheeling High School MAth Team Wins District Meet",
	        "Wheeling Dance Teacher To Recieve The 2014 Ruth Page Award",
	        "The SisterHood Of the Traveling Dress"
	};
	
	public static TopNews[] listNews;
	private ProgressBar load;
	private TextView reload;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_news,
				container, false);
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		load = (ProgressBar) getView().findViewById(R.id.progressBar1);
		reload = (TextView) getView().findViewById(R.id.reload3);
		final View online = (View) getView().findViewById(R.id.onlineBox2);
		/*listNews[0] = new TopNews("Monday, March, 3 2014","Text message stuff things test words");
		listNews[1] = new TopNews("Monday, March, 3 2014","Text message stuff things test words");
		listNews[2] = new TopNews("Monday, March, 3 2014","Text message stuff things test words");
		listNews[3] = new TopNews("Monday, March, 3 2014","Text message stuff things test words");
		 */
		//List<TopNews> temp = new ArrayList<TopNews>();
		
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
		
		//for (TopNews event: listNews){
			//temp.add(event);
		//}
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_list_item_1, newsList);
        
		//NewsListAdapter adapter = new NewsListAdapter(getListView().getContext(), temp);
		//setListAdapter(adapter);
				
	}
	
	public class GetJSon extends AsyncTask <String, Integer, Long>{

		//private UpcomingEvent[] listEvents;

		@Override
		protected Long doInBackground(String... params) {
			try{
				URL upcomingEvents = new URL("http://wcc-mobile-app.appspot.com/top_news");
				HttpURLConnection connection = (HttpURLConnection) upcomingEvents.openConnection();

				connection.connect();

				InputStream inputStream = connection.getInputStream();
				if (connection.getResponseCode() == 200 && inputStream != null) {
					String responseJson = IOUtils.toString(inputStream);

					Gson gson = new GsonBuilder().create();
					Type type = new TypeToken<TopNews[]>(){}.getType();

					listNews = gson.fromJson(responseJson, type);
				} else {
					listNews = new TopNews[4];
					listNews[0] = new TopNews("Wendesday, April 16, 2014", "Athletic Booster's Meeting");
					listNews[1] = new TopNews("Wendesday, April 16, 2014", "JAZZAPALOOZA");
					listNews[2] = new TopNews("Thursday, April 17, 2014", "Gold Seminar");
					listNews[3] = new TopNews("Friday, April 18, 2014", "No School!!!");
				}
			} catch(Exception e){
			}	

			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);
			
			//List<String> temp = new ArrayList<String>();
			
			List<TopNews> temp = new ArrayList<TopNews>();
			

			load.setVisibility(0X00000004);
			
			if(listNews == null || listNews.length == 0){
				reload.setVisibility(0);
				reload.setText("No news (click to reload)");
			}
			else{
				for (TopNews event: listNews){
					//temp.add(event.eventTitle);
					temp.add(event);
				}
				
				NewsListAdapter adapter = new NewsListAdapter(getListView().getContext(), temp);
				setListAdapter(adapter);
			}
		}
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		/*Intent intent = new Intent(getActivity().getApplicationContext(), Read.class);
		intent.putExtra("title", listNews[position].title);
		intent.putExtra("details", listNews[position].details);
		startActivity(intent);*/
		
		Intent intent = new Intent(getActivity().getApplicationContext(), News.class);
		//intent.putExtra("news", listNews);
		intent.putExtra("title", listNews[position].title);
		intent.putExtra("details", listNews[position].details);
		intent.putExtra("type", true);
		intent.putExtra("page", position);
		startActivity(intent);
		
	}
}
