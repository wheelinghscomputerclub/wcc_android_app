package org.d214.whs.wcc.portal;

import java.util.List;

import com.example.whs.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ScheduleListAdapter extends ArrayAdapter<Period>{
	
	protected Context mContext;
	protected List<Period> mSchedule;

	public ScheduleListAdapter(Context context, List<Period> schedule){
		super(context, R.layout.schedule_list, schedule);
		mContext = context;
		mSchedule = schedule;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		convertView = LayoutInflater.from(mContext).inflate(R.layout.schedule_list, null);
		holder = new ViewHolder();
		holder.periodLabel = (TextView) convertView.findViewById(R.id.textViewPeriod);
		holder.startLabel = (TextView) convertView.findViewById(R.id.textViewStart);
		holder.endLabel = (TextView) convertView.findViewById(R.id.textViewEnd);

		Period p = mSchedule.get(position);
		
		holder.periodLabel.setText(p.period);
		holder.startLabel.setText(p.start);
		holder.endLabel.setText(p.end);
		
		
		
		return convertView;
	}

	private static class ViewHolder{
		TextView periodLabel;
		TextView startLabel;
		TextView endLabel;
	}
	
}










