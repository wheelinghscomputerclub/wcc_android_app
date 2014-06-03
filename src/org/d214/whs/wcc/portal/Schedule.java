package org.d214.whs.wcc.portal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d214.whs.wcc.portal.R;


public class Schedule extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ActionBar.OnNavigationListener {

	public static int page = 0;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public static boolean running = true;
    public static Handler handler;
    public static ArrayList[] schedules;
    public static int today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
        page = position;
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
            case 7:
                mTitle = getString(R.string.title_section7);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }
    
    @Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}
    
    @Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}
    
    @Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		getFragmentManager()
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
		return true;
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.schedule2, menu);
            //restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends ListFragment {
    	
    	private ArrayList<Period> regularPeriods = new ArrayList<Period>();
    	private ArrayList<Period> goldPeriods = new ArrayList<Period>();
    	private ArrayList<Period> bluePeriods = new ArrayList<Period>();
    	private ArrayList<Period> exam1Periods = new ArrayList<Period>();
    	private ArrayList<Period> exam2Periods = new ArrayList<Period>();
    	private ArrayList<Period> mayPeriods = new ArrayList<Period>();
    	private ArrayList<Period> mayGoldPeriods = new ArrayList<Period>();
    	private ArrayList<Period> regularBlocks = new ArrayList<Period>();
    	private ArrayList<Period> goldBlocks = new ArrayList<Period>();
    	private ArrayList<Period> blueBlocks = new ArrayList<Period>();
    	private ArrayList<Period> exam1Blocks = new ArrayList<Period>();
    	private ArrayList<Period> exam2Blocks = new ArrayList<Period>();
    	private ArrayList<Period> mayBlocks = new ArrayList<Period>();
    	private ArrayList<Period> mayGoldBlocks = new ArrayList<Period>();
    	public int page = 0;
    	public String startTime = "7:25", endTime = "2:50";
    	TextView blockLabel, timeLabel, dayLabel;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
            
            regularPeriods.add(new Period("Period 1", "7:25", "8:10"));
            regularPeriods.add(new Period("Period 2", "8:14", "8:59"));
            regularPeriods.add(new Period("Period 3", "9:04", "9:54"));
            regularPeriods.add(new Period("Period 4", "9:58", "10:43"));
            regularPeriods.add(new Period("Period 5", "10:48", "11:33"));
            regularPeriods.add(new Period("Period 6", "11:37", "12:22"));
            regularPeriods.add(new Period("Period 7", "12:27", "1:12"));
            regularPeriods.add(new Period("Period 8", "1:16", "2:01"));
            regularPeriods.add(new Period("Period 9", "2:05", "2:50"));
            
            regularBlocks.add(new Period("Block A", "8:25", "8:55"));
            regularBlocks.add(new Period("Block B", "9:04", "10:39"));
            regularBlocks.add(new Period("Block C1", "10:48", "12:18"));
            regularBlocks.add(new Period("Block C2", "11:42", "1:12"));
            regularBlocks.add(new Period("Block D", "1:20", "2:50"));

            goldPeriods.add(new Period("Period 1", "8:25", "9:04"));
            goldPeriods.add(new Period("Period 2", "9:08", "9:46"));
            goldPeriods.add(new Period("Period 3", "9:51", "10:34"));
            goldPeriods.add(new Period("Period 4", "10:38", "11:17"));
            goldPeriods.add(new Period("Period 5", "11:22", "12:00"));
            goldPeriods.add(new Period("Period 6", "12:04", "12:42"));
            goldPeriods.add(new Period("Period 7", "12:47", "1:25"));
            goldPeriods.add(new Period("Period 8", "1:29", "2:08"));
            goldPeriods.add(new Period("Period 9", "2:12", "2:50"));
            
            goldBlocks.add(new Period("Block A", "8:25", "9:42"));
            goldBlocks.add(new Period("Block B", "9:51", "11:13"));
            goldBlocks.add(new Period("Block C1", "11:22", "12:38"));
            goldBlocks.add(new Period("Block C2", "12:09", "1:25"));
            goldBlocks.add(new Period("Block D", "1:33", "2:50"));
    		
            bluePeriods.add(new Period("Period 1", "9:25", "9:57"));
            bluePeriods.add(new Period("Period 2", "10:01", "10:33"));
            bluePeriods.add(new Period("Period 5", "10:38", "11:09"));
            bluePeriods.add(new Period("Period 6", "11:13", "11:44"));
            bluePeriods.add(new Period("Period 7", "11:49", "12:20"));
            bluePeriods.add(new Period("Period 3", "12:25", "1:02"));
            bluePeriods.add(new Period("Period 4", "1:06", "1:38"));
            bluePeriods.add(new Period("Period 8", "1:42", "2:14"));
            bluePeriods.add(new Period("Period 9", "2:18", "2:50"));
            
            blueBlocks.add(new Period("Block A", "9:25", "10:29"));
            blueBlocks.add(new Period("Block C1", "10:38", "11:40"));
            blueBlocks.add(new Period("Block C2", "11:18", "12:20"));
            blueBlocks.add(new Period("Block B", "12:29", "1:38"));
            blueBlocks.add(new Period("Block D", "1:46", "2:50"));
    		
            exam1Periods.add(new Period("Period 1", "7:30", "8:20"));
            exam1Periods.add(new Period("Period 2", "8:25", "9:20"));
            exam1Periods.add(new Period("Period 5", "9:30", "10:20"));
            exam1Periods.add(new Period("Period 6", "10:25", "11:15"));
            exam1Periods.add(new Period("Period 7", "11:20", "12:10"));
    		
            exam2Periods.add(new Period("Period 3", "7:30", "8:20"));
            exam2Periods.add(new Period("Period 4", "8:25", "9:20"));
            exam2Periods.add(new Period("Period 8", "9:30", "10:20"));
            exam2Periods.add(new Period("Period 9", "10:25", "11:15"));
    		
            mayPeriods.add(new Period("Period 1", "7:25", "8:09"));
            mayPeriods.add(new Period("Period 2", "8:14", "8:57"));
            mayPeriods.add(new Period("Reading Time", "9:01", "9:16"));
            mayPeriods.add(new Period("Period 3", "9:16", "10:00"));
            mayPeriods.add(new Period("Period 4", "10:04", "10:48"));
            mayPeriods.add(new Period("Period 5", "10:53", "11:37"));
            mayPeriods.add(new Period("Period 6", "11:41", "12:25"));
            mayPeriods.add(new Period("Period 7", "12:30", "1:14"));
            mayPeriods.add(new Period("Period 8", "1:18", "2:02"));
            mayPeriods.add(new Period("Period 9", "2:06", "2:50"));
    		
            mayGoldPeriods.add(new Period("Period 1", "8:25", "9:03"));
            mayGoldPeriods.add(new Period("Period 2", "9:07", "9:44"));
            mayGoldPeriods.add(new Period("Reading Time", "9:48", "10:03"));
            mayGoldPeriods.add(new Period("Period 3", "10:03", "10:40"));
            mayGoldPeriods.add(new Period("Period 4", "10:45", "11:22"));
            mayGoldPeriods.add(new Period("Period 5", "11:27", "12:04"));
            mayGoldPeriods.add(new Period("Period 6", "12:08", "12:45"));
            mayGoldPeriods.add(new Period("Period 7", "12:50", "1:27"));
    		mayGoldPeriods.add(new Period("Period 8", "1:31", "2:09"));
    		mayGoldPeriods.add(new Period("Period 9", "2:13", "2:50"));
    		
    		 schedules = new ArrayList[]{regularPeriods, goldPeriods, bluePeriods, exam1Periods, 
             		exam2Periods, mayPeriods, mayGoldPeriods};
    		
            return rootView;
        }
        
        @Override
    	public void onResume() {
    		super.onResume();
    		 blockLabel = (TextView) getView().findViewById(R.id.textViewBlock);
    		 timeLabel = (TextView) getView().findViewById(R.id.textViewTime);
    		 dayLabel = (TextView) getView().findViewById(R.id.textViewSchedule);
    		 
    		switch(Schedule.page){
    		case 0:
    			//findPeriod(regularPeriods);
    			today = 0;
    			dayLabel.setText("Regular Schedule");
    			ScheduleListAdapter adapter1 = new ScheduleListAdapter(getListView().getContext(), regularPeriods);
        		setListAdapter(adapter1);
        		break;
    		case 1:
    			today = 1;
    			startTime = "8:25";
    			//findPeriod(goldPeriods);
    			dayLabel.setText("Gold Late Start");
    			ScheduleListAdapter adapter2 = new ScheduleListAdapter(getListView().getContext(), goldPeriods);
        		setListAdapter(adapter2);
        		break;
    		case 2:
    			today = 2;
    			startTime = "9:25";
    			//findPeriod(bluePeriods);
    			dayLabel.setText("Blue Late Start");
    			ScheduleListAdapter adapter3 = new ScheduleListAdapter(getListView().getContext(), bluePeriods);
        		setListAdapter(adapter3);
        		break;
    		case 3:
    			today = 3;
    			startTime = "7:30";
    			endTime = "12:10";
    			//findPeriod(exam1Periods);
    			dayLabel.setText("Final Exams Day 1");
    			ScheduleListAdapter adapter4 = new ScheduleListAdapter(getListView().getContext(), exam1Periods);
        		setListAdapter(adapter4);
        		break;
    		case 4:
    			today = 5;
    			startTime = "7:30";
    			endTime = "11:15";
    			//findPeriod(exam2Periods);
    			dayLabel.setText("Final Exams Day 2");
    			ScheduleListAdapter adapter5 = new ScheduleListAdapter(getListView().getContext(), exam2Periods);
        		setListAdapter(adapter5);
        		break;
    		case 5:
    			today = 5;
    			//findPeriod(mayPeriods);
    			dayLabel.setText("May Reading");
    			ScheduleListAdapter adapter6 = new ScheduleListAdapter(getListView().getContext(), mayPeriods);
        		setListAdapter(adapter6);
        		break;
    		case 6:
    			today = 6;
    			startTime = "8:25";
    			//findPeriod(mayGoldPeriods);
    			dayLabel.setText("May Late Start");
    			ScheduleListAdapter adapter7 = new ScheduleListAdapter(getListView().getContext(), mayGoldPeriods);
        		setListAdapter(adapter7);
        		break;
    		}
    		
    		findPeriod(schedules[today]);
    		handler = new Handler();
    		Runnable runnable = new Runnable(){
    			@Override
    			public void run() {
    				while (running){
    					try{
    						Thread.sleep(60000);
    					} catch(InterruptedException e){
    						e.printStackTrace();
    					}
    					handler.post(new Runnable() {
    						
    						@Override
    						public void run() {
    							findPeriod(schedules[today]);
    						}
    					});
    				}
    				
    			}
    		};
    		new Thread(runnable).start();
    		
    		//ScheduleListAdapter adapter = new ScheduleListAdapter(getListView().getContext(), periods);
    		//setListAdapter(adapter);
    		
        }
        
        public void findPeriod(ArrayList<Period> periods){
        	int h, m, t;
    		String s;
    		int school = 0;
    		
    		Date date = new Date();
    		Calendar cal = Calendar.getInstance();
    		//String time = dateFormat.format(date);
    		int time = 0;
    		boolean inClass = false;
    		int day = cal.get(Calendar.DAY_OF_WEEK);
    		if (day == Calendar.SATURDAY || day == Calendar.SUNDAY)
    			school = 1;
    		else if (date.getHours() > 12){
    			time = (date.getHours() * 60) + date.getMinutes();
    			m = Integer.parseInt(endTime.substring(endTime.length() - 2));
				h = Integer.parseInt(endTime.substring(0, endTime.length() - 3));
				t = (h * 60) + m;
    			if (time > t)
    				school = 2;
    			else
    				time = ((date.getHours() - 12) * 60) + date.getMinutes();
    		} else{
    			time = (date.getHours() * 60) + date.getMinutes();
    			m = Integer.parseInt(startTime.substring(startTime.length() - 2));
				h = Integer.parseInt(startTime.substring(0, startTime.length() - 3));
				t = (h * 60) + m;
    			if (time < t)
    				school = 3;
    		}
    		
    		if (school == 0)
    			for (Period p: periods){
    				s = p.start;
    				m = Integer.parseInt(s.substring(s.length() - 2));
    				h = Integer.parseInt(s.substring(0, s.length() - 3));
    				t = (h * 60) + m;
    				if (time >= t){
    					s = p.end;
    					m = Integer.parseInt(s.substring(s.length() - 2));
    					h = Integer.parseInt(s.substring(0, s.length() - 3));
    					t = (h * 60) + m;
    					if (time < t){
    						blockLabel.setText(p.period);
    						timeLabel.setText(p.start +  " - " + p.end);
    						inClass = true;
    						//school = 0;
    						break;
    					}
    				}
    			}
    		else switch(school){
    			case 1:
    				blockLabel.setText("Weekend");
    				timeLabel.setText("classes will resume next week");
    				inClass = true;
    				break;
    			case 2:
    				blockLabel.setText("After School");
    				timeLabel.setText("classes will resume tomorrow");
    				inClass = true;
    				break;
    			case 3:
    				blockLabel.setText("Before School");
    				timeLabel.setText("classes will begin at " + startTime);
    				inClass = true;
    				break;
    			}
    		
    		if (!inClass){
    			for (int i = 0; i < periods.size() - 1; i++){
    				Period p = periods.get(i);
    				Period pn = periods.get(i+1);
    				s = p.end;
    				m = Integer.parseInt(s.substring(s.length() - 2));
    				h = Integer.parseInt(s.substring(0, s.length() - 3));
    				t = (h * 60) + m;
    				if (time >= t){
    					s = pn.start;
    					m = Integer.parseInt(s.substring(s.length() - 2));
    					h = Integer.parseInt(s.substring(0, s.length() - 3));
    					t = (h * 60) + m;
    					if (time < t){
    						blockLabel.setText("Passing Period");
    						timeLabel.setText(p.end +  " - " + pn.start);
    						inClass = true;
    						//school = 0;
    						break;
    					}
    				}
    			}
    		}

        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
           // ((Schedule) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


}
