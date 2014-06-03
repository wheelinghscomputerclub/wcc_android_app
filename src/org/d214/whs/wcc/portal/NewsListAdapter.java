package org.d214.whs.wcc.portal;

import java.util.List;

import org.d214.whs.wcc.portal.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsListAdapter  extends ArrayAdapter<TopNews>{
	
	protected Context mContext;
	protected List<TopNews> mEvents;

	public NewsListAdapter(Context context, List<TopNews> events){
		super(context, R.layout.news_list, events);
		mContext = context;
		mEvents = events;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		convertView = LayoutInflater.from(mContext).inflate(R.layout.news_list, null);
		holder = new ViewHolder();
		holder.titleLabel = (TextView) convertView.findViewById(R.id.newsTitle);
		holder.newsLabel = (TextView) convertView.findViewById(R.id.newsNews);

		TopNews news = mEvents.get(position);
		
		holder.newsLabel.setText(news.details);
		holder.titleLabel.setText(news.title);
		
		
		
		return convertView;
	}

	private static class ViewHolder{
		TextView titleLabel;
		TextView newsLabel;
	}
	
}
