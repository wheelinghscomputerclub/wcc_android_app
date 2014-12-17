package org.d214.whs.wcc.portal;

import java.util.ArrayList;
import java.util.List;

import com.example.whs.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class ChangeSettings extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_settings);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		List<Setting> temp = new ArrayList<Setting>();
		
		SharedPreferences settings = getSharedPreferences("WCCAppPrefs", 0);
		
		Setting afiliation = new Setting("Grade", settings.getString("grade", "Sophmore"), false, false);
		Setting saveData = new Setting("Save Data", "Saves loading time", true, settings.getBoolean("check", false));
		Setting dataSaveTime = new Setting("Save Time", settings.getString("time", "1 Week"), false, false);
		Setting language = new Setting("Set Language", settings.getString("language", "English"), false, false);
		
		temp.add(afiliation);
		temp.add(language);
		temp.add(saveData);
		temp.add(dataSaveTime);
		
		SettingsAdapter adapter = new SettingsAdapter(getListView().getContext(), temp);
		
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		SharedPreferences settings = getSharedPreferences("WCCAppPrefs", 0);
		SharedPreferences.Editor editor = settings.edit();
		
		String[] grades = new String[]{"Freshman", "Sophmore", "Junior", "Senior"};
		String[] saveTimes = new String[]{"1 Week", "2 Weeks", "3 Weeks"};
		String[] languages = new String[]{"English", "Spanish", "Russian"};
		
		final CheckBox check = (CheckBox) findViewById(R.id.checkBox1);
		final TextView settingLabel = (TextView) findViewById(R.id.textViewTitle);
		final TextView detailLabel = (TextView) findViewById(R.id.textViewDetails);
		
		switch(position){
		case 0:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Select a Grade");
			builder.setItems(grades, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					SharedPreferences settings = getSharedPreferences("WCCAppPrefs", 0);
					SharedPreferences.Editor editor = settings.edit();
					
					switch(which){
						case 0:
							editor.putString("grade", "Freshman");
							break;
						case 1:
							editor.putString("grade", "Sophmore");
							break;
						case 2:
							editor.putString("grade", "Junior");
							break;
						case 3:
							editor.putString("grade", "Senior");
							break;
					}
					editor.commit();
					
					List<Setting> temp = new ArrayList<Setting>();
					Setting afiliation = new Setting("Grade", settings.getString("grade", "Sophmore"), false, false);
					Setting saveData = new Setting("Save Data", "Saves loading time", true, settings.getBoolean("check", false));
					Setting dataSaveTime = new Setting("Save Time", settings.getString("time", "1 Week"), false, false);
					Setting language = new Setting("Set Language", settings.getString("language", "English"), false, false);
					
					temp.add(afiliation);
					temp.add(language);
					temp.add(saveData);
					temp.add(dataSaveTime);
					
					SettingsAdapter adapter = new SettingsAdapter(getListView().getContext(), temp);
					setListAdapter(adapter);
				}
			});
			builder.show();
			break;
		case 1:
			AlertDialog.Builder builderLan = new AlertDialog.Builder(this);
			builderLan.setTitle("Select a Language");
			builderLan.setItems(languages, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					SharedPreferences settings = getSharedPreferences("WCCAppPrefs", 0);
					SharedPreferences.Editor editor = settings.edit();
					
					switch(which){
						case 0:
							editor.putString("language", "English");
							break;
						case 1:
							editor.putString("language", "Spanish");
							break;
						case 2:
							editor.putString("language", "Russian");
							break;
					}
					editor.commit();
					
					List<Setting> temp = new ArrayList<Setting>();
					Setting afiliation = new Setting("Grade", settings.getString("grade", "Sophmore"), false, false);
					Setting saveData = new Setting("Save Data", "Saves loading time", true, settings.getBoolean("check", false));
					Setting dataSaveTime = new Setting("Save Time", settings.getString("time", "1 Week"), false, false);
					Setting language = new Setting("Set Language", settings.getString("language", "English"), false, false);
					
					temp.add(afiliation);
					temp.add(language);
					temp.add(saveData);
					temp.add(dataSaveTime);
					
					SettingsAdapter adapter = new SettingsAdapter(getListView().getContext(), temp);
					setListAdapter(adapter);
				}
			});
			builderLan.show();
			break;
		case 2:
			break;
		case 3:
			AlertDialog.Builder builderTime = new AlertDialog.Builder(this);
			builderTime.setTitle("Select how long the data will be strored");
			builderTime.setItems(saveTimes, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					SharedPreferences settings = getSharedPreferences("WCCAppPrefs", 0);
					SharedPreferences.Editor editor = settings.edit();
					
					switch(which){
						case 0:
							editor.putString("time", "1 Week");
							break;
						case 1:
							editor.putString("time", "2 Weeks");
							break;
						case 2:
							editor.putString("time", "3 Weeks");
							break;
					}
					editor.commit();
					
					List<Setting> temp = new ArrayList<Setting>();
					Setting afiliation = new Setting("Grade", settings.getString("grade", "Sophmore"), false, false);
					Setting saveData = new Setting("Save Data", "Saves loading time", true, settings.getBoolean("check", false));
					Setting dataSaveTime = new Setting("Save Time", settings.getString("time", "1 Week"), false, false);
					Setting language = new Setting("Set Language", settings.getString("language", "English"), false, false);
					
					temp.add(afiliation);
					temp.add(language);
					temp.add(saveData);
					temp.add(dataSaveTime);
					
					SettingsAdapter adapter = new SettingsAdapter(getListView().getContext(), temp);
					setListAdapter(adapter);
				}
			});
			builderTime.show();
			break;
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_settings, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		if (item.getItemId() == R.id.action_save){
			Intent intent = new Intent(ChangeSettings.this, WHS.class);
			startActivity(intent);
		}else{
			finish();
		}
		return true;
	}

}











