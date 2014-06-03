package org.d214.whs.wcc.portal;

import java.util.List;

import org.d214.whs.wcc.portal.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventsListAdapter extends ArrayAdapter<UpcomingEvent>{
	
	protected Context mContext;
	protected List<UpcomingEvent> mEvents;
	public String seminar = "";
	private String[] eventsList = new String[4];
	private int itemNum = 0;
	public int refresh;
	
	public EventsListAdapter(Context context, List<UpcomingEvent> events){
		super(context, R.layout.events_list, events);
		mContext = context;
		mEvents = events;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		convertView = LayoutInflater.from(mContext).inflate(R.layout.events_list, null);
		holder = new ViewHolder();
		
		holder.titleLabel = (TextView) convertView.findViewById(R.id.textViewTitle);
		holder.dateLabel = (TextView) convertView.findViewById(R.id.textViewDate);
		holder.calendarDate = (TextView) convertView.findViewById(R.id.textViewCalendarDate);
		holder.calendarImage = (ImageView) convertView.findViewById(R.id.imageViewCalendar);
		
		UpcomingEvent event = mEvents.get(position);
		
		//eventsList[position] = event.toString();
		
		if (event.eventTitle.length() >= 32){
			holder.titleLabel.setText(event.eventTitle.substring(0, 32) + "...");}
		else{
			holder.titleLabel.setText(event.eventTitle);}
		holder.dateLabel.setText(event.eventDate);
		holder.calendarDate.setText(event.eventDate.substring(event.eventDate.length() - 8, event.eventDate.length() - 6));
		//if (event.eventDate.substring(0, 2).equalsIgnoreCase("tu") || event.eventDate.substring(0, 2).equalsIgnoreCase("fr"))
			holder.calendarImage.setImageResource(R.drawable.calendar_blue);
		//else if(event.eventDate.substring(0, 2).equalsIgnoreCase("mo") || event.eventDate.substring(0, 2).equalsIgnoreCase("we"))
		//	holder.calendarImage.setImageResource(R.drawable.calendar_blue);
		//else
		//	holder.calendarImage.setImageResource(R.drawable.calendar_green);
		
		if (event.eventDate.substring(0, 2).equalsIgnoreCase("th")){
			seminar = event.toString();
		}
		//ksfdahcx
		return convertView;
	}


	private static class ViewHolder{
		TextView titleLabel;
		TextView dateLabel;
		TextView calendarDate;
		ImageView calendarImage;
	}
}









