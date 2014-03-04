package com.example.whs;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
		startActivity(intent);
		return true;
	}

}











