package org.d214.whs.wcc.portal;

import java.util.List;

import com.example.whs.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnnouncementsListAdapter extends ArrayAdapter<DailyAnnouncements>{
	
	protected Context mContext;
	protected List<DailyAnnouncements> mEvents;
	
	public AnnouncementsListAdapter(Context context, List<DailyAnnouncements> events){
		super(context, R.layout.announcements_list, events);
		mContext = context;
		mEvents = events;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		convertView = LayoutInflater.from(mContext).inflate(R.layout.announcements_list, null);
		holder = new ViewHolder();
		holder.titleLabel = (TextView) convertView.findViewById(R.id.annTitle);
		holder.dateLabel = (TextView) convertView.findViewById(R.id.annDate);

		DailyAnnouncements ann = mEvents.get(position);
		
		holder.dateLabel.setText(ann.date);
		holder.titleLabel.setText(ann.text);
		
		
		
		return convertView;
	}

	private static class ViewHolder{
		TextView titleLabel;
		TextView dateLabel;
	}
}









