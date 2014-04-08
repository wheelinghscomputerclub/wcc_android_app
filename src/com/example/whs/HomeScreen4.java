package com.example.whs;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class HomeScreen4 extends ListFragment{
	
	public String[] listNews = {
			"Wheeling High School Orchesis Annual Concert",
	        "Wheeling High School MAth Team Wins District Meet",
	        "Wheeling Dance Teacher To Recieve The 2014 Ruth Page Award",
	        "The SisterHood Of the Traveling Dress"
	};
	
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
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_list_item_1, listNews);
        setListAdapter(adapter);
				
	}
}
