package org.d214.whs.wcc.portal;


import java.util.ArrayList;

import org.d214.whs.wcc.portal.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomeScreen1 extends ListFragment{
	private ArrayList<WebSite> webSites = new ArrayList<WebSite>();
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
				//sag
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











