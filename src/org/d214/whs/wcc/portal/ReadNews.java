package org.d214.whs.wcc.portal;


import org.d214.whs.wcc.portal.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReadNews extends Fragment{
	
	public int page;
	public boolean type;
	
	public ReadNews(int page, boolean type){
		this.page = page;
		this.type = type;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_read,
				container, false);
		return rootView;
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		final TextView title = (TextView) getView().findViewById(R.id.titleLabel);
		final TextView message = (TextView) getView().findViewById(R.id.anouncementLabel);
		
		if (type){
			title.setText(News.news[page].title);
			message.setText(News.news[page].details);
		}else{
			title.setText(News.ann[page].date);
			message.setText(News.ann[page].text);
		}
		
	}
}
