package com.example.whs;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class HomeScreen3 extends ListFragment{
	
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
		View rootView = inflater.inflate(R.layout.activity_announcements,
				container, false);
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_list_item_1, listAnnouncements);
        setListAdapter(adapter);
				
	}
}
