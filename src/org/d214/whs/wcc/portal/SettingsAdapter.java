package org.d214.whs.wcc.portal;

import java.util.List;

import org.d214.whs.wcc.portal.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class SettingsAdapter extends ArrayAdapter<Setting>{
	
	protected Context mContext;
	protected List<Setting> mSettings;
	
	public SettingsAdapter(Context context, List<Setting> settings){
		super(context, R.layout.settings_list, settings);
		mContext = context;
		mSettings = settings;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		convertView = LayoutInflater.from(mContext).inflate(R.layout.settings_list, null);
		holder = new ViewHolder();
		holder.titleLabel = (TextView) convertView.findViewById(R.id.textViewTitle);
		holder.detailsLabel = (TextView) convertView.findViewById(R.id.textViewDetails);
		holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);
		
		Setting setting = mSettings.get(position);
		
		holder.titleLabel.setText(setting.settingTitle);
		holder.detailsLabel.setText(setting.settingDetails);
		
		if (setting.setttingCheckBox){
			holder.checkBox.setVisibility(0);
		} else{
			holder.checkBox.setVisibility(0X00000004);
		}
		
		if (setting.settingIsChecked){
			holder.checkBox.setChecked(true);
		}else{
			holder.checkBox.setChecked(false);
		}
		return convertView;
	}
	
	private static class ViewHolder{
		TextView titleLabel;
		TextView detailsLabel;
		CheckBox checkBox;
	}
}









