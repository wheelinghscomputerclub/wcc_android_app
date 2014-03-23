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

import com.example.whs.Events.GetJSon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class News extends ListActivity {
	
	public String[] listNews = {
			"Wheeling High School Orchesis Annual Concert",
	        "Wheeling High School MAth Team Wins District Meet",
	        "Wheeling Dance Teacher To Recieve The 2014 Ruth Page Award",
	        "The SisterHood Of the Traveling Dress"
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//getActionBar().setListNavigationCallbacks(adapter, callback)
		//GetJSon getIt = new GetJSon();
		//getIt.execute("");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNews);
        setListAdapter(adapter);
	}
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    		
			Intent intent = new Intent(News.this, Read.class);
			startActivity(intent);
    	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		Intent intent = new Intent(News.this, WHS.class);
		finish();
		return true;
	}
	
	/*public class GetJSon extends AsyncTask <String, Integer, Long>{

		private TopNews[] listNews;

		@Override
		protected Long doInBackground(String... params) {
			try{
				URL topNews = new URL("http://1.wccsandbox.appspot.com/wcc_prototype");
				HttpURLConnection connection = (HttpURLConnection) topNews.openConnection();

				connection.connect();

				InputStream inputStream = connection.getInputStream();
				if (connection.getResponseCode() == 200 && inputStream != null) {
					String responseJson = IOUtils.toString(inputStream);

					Gson gson = new GsonBuilder().create();
					Type type = new TypeToken<TopNews[]>(){}.getType();

					listNews = gson.fromJson(responseJson, type);
				} else {
					listNews = new TopNews[]{};
				}
			} catch(Exception e){			
			}	

			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);
			List<String> temp = new ArrayList<String>();
			for (TopNews newsTitle: listNews){
				temp.add(newsTitle.title);
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(News.this, android.R.layout.simple_list_item_1, temp.toArray(new String[]{}));
			setListAdapter(adapter);
		}

	}*/

}











