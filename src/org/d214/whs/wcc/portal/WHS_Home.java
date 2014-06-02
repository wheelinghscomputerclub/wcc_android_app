package org.d214.whs.wcc.portal;

import java.util.Locale;

import com.example.whs.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WHS_Home extends FragmentActivity{
	
	private ProgressBar load;
	public boolean run = true, loading = true;
	private UpcomingEvent[] listEvents;
	private boolean showInfo = true;
	private Handler handler;
	private View bar;
	private TextView label, datLabel, messageLabel, daysLeftLabel;
	private int monthDate;
	private ImageView showButton;
	private char color;
	private int trp;
	private HomeScreen2 getInfo;
	private int page = 0;
	public static int refreshDate;
	public static boolean connected;
	public static String savedEventsList = "[" +
			"Monday, April 16, 2014" + "\", \""+ "Test" + "\", \"" +
			"Tuesday, April 16, 2014" + "\", \""+ "Test" + "\", \""+
			"Wedensday, April 17, 2014" + "\", \""+ "Test" + "\", \""+
			"Thursday, April 18, 2014" + "\", \""+ "Test Test" + 
			"]";
	public static ConnectivityManager connMgr;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whs__home);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		 connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		 checkConnection();
		
		SharedPreferences settings = getSharedPreferences("WCCAppPrefs", 0);
		/*refreshDate = settings.getInt("refresh", 1);
		savedEventsList = settings.getString("events", "[" +
				"Monday, April 16, 2014" + "\", \""+ "Athletic Booster's Meeting" + "\", \"" +
				"Tuesday, April 16, 2014" + "\", \""+ "JAZZAPALOOZA" + "\", \""+
				"Wedensday, April 17, 2014" + "\", \""+ "Gold Seminar" + "\", \""+
				"Thursday, April 18, 2014" + "\", \""+ "No School!!!" + 
				"]");*/
		
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		mViewPager.setOffscreenPageLimit(5);
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				page = arg0;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				/*if (arg0 == 1 && page == 0){
					animateInfo2();
				}else if (arg0 == 1 && page == 1)
					animateInfo1();*/
				}
		});
	}
	
	public static void checkConnection(){
		

		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) 
			connected = true;
		 else 
			connected = false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.whs__home, menu);
		
			if (!connected){
			//Toast toast = Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT);
			//toast.show();
				
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Connection not found");
			builder.setMessage("News, events, announcements and other functions will not load.");
			
			builder.setPositiveButton("Continue", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});
		
			
			builder.setNegativeButton("Close", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			
			AlertDialog dialog = builder.create();
			dialog.show();
		}
			
		
		
		SharedPreferences settings = getSharedPreferences("WCCAppPrefs", 0);
		
		bar = (View) findViewById(R.id.infoBar1);
		label = (TextView) findViewById(R.id.infoText1);
		datLabel = (TextView) findViewById(R.id.labelEventDate);
		messageLabel = (TextView) findViewById(R.id.labelEventMessage);
		daysLeftLabel = (TextView) findViewById(R.id.labelDaysLeft);
		showButton = (ImageView) findViewById(R.id.imageInfo);
		
		//refreshDate = settings.getInt("refresh", 1);
		
		//savedEventsList = settings.getString("event", "[\"a\", \"b\", \"c\", \"d\", \"e\", \"f\", \"g\", \"h\"]");
		
		Typeface font1 = Typeface.createFromAsset(this.getAssets(), "Roboto-Regular.ttf"); 
		//Typeface font2 = Typeface.createFromAsset(this.getAssets(), "DroidSans-Bold.ttf");
		datLabel.setTypeface(font1);
		messageLabel.setTypeface(font1);
		daysLeftLabel.setTypeface(font1);
		
		getInfo = new HomeScreen2();
		
		/*handler = new Handler();
		Runnable runnable = new Runnable(){
			@Override
			public void run() {
				while (loading){
					try{
						Thread.sleep(90);
					} catch(InterruptedException e){
						e.printStackTrace();
					}
					handler.post(new Runnable() {
						
						@Override
						public void run() {
							if (!HomeScreen2.seminar.equalsIgnoreCase("test")){
								label.setText(HomeScreen2.seminar);
								animateInfo1();
								//animateInfo22();
								bar.setVisibility(0);
								label.setVisibility(0);
								//showButton.setVisibility(0x0000004);
								
								SharedPreferences settings = getSharedPreferences("WCCAppPrefs", 0);
								SharedPreferences.Editor editor = settings.edit();
								
								editor.putString("events", Arrays.toString(HomeScreen2.listEvents));
								editor.putInt("refresh", HomeScreen2.refresh);
								refreshDate = HomeScreen2.refresh;
								editor.putString("events", HomeScreen2.eventsListString);
								savedEventsList = HomeScreen2.eventsListString;
								//Toast toast = Toast.makeText(getApplicationContext(), "List updated: Will update next on the " + refreshDate, Toast.LENGTH_SHORT);
								//toast.show();
								editor.commit();
								
								loading = false;
							}
							
						}
					});
				}
			}
		};
		new Thread(runnable).start();*/
		
		/*showButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animateInfo1();
				animateInfo22();
			}
		});
		//hi
		bar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					//bar.setBackgroundColor(0x00FFFFFF);
					//label.setTextColor(0x00FFFFFF);
				//if (showInfo){
					
					showInfo = false;
				//} else{
					animateInfo2();
					animateInfo12();
					showInfo = true;
				
			}
		});*/
		
		return true;	
	
	}
	private void animateInfo1(){
		AlphaAnimation fadeInAnimation = new AlphaAnimation(0, (float) 0.75);
		fadeInAnimation.setDuration(900);
		fadeInAnimation.setFillAfter(true);
		
		label.setAnimation(fadeInAnimation);
		bar.setAnimation(fadeInAnimation);
	}
	
	private void animateInfo2(){
		AlphaAnimation fadeInAnimation = new AlphaAnimation((float) .75, 0);
		fadeInAnimation.setDuration(900);
		fadeInAnimation.setFillAfter(true);
		
		label.setAnimation(fadeInAnimation);
		bar.setAnimation(fadeInAnimation);
	}
	
	private void animateInfo12(){
		AlphaAnimation fadeInAnimation = new AlphaAnimation(0, (float) 0.85);
		fadeInAnimation.setDuration(900);
		fadeInAnimation.setFillAfter(true);
		
		showButton.setAnimation(fadeInAnimation);
	}
	
	private void animateInfo22(){
		AlphaAnimation fadeInAnimation = new AlphaAnimation((float) .85, 0);
		fadeInAnimation.setDuration(900);
		fadeInAnimation.setFillAfter(true);
		
		showButton.setAnimation(fadeInAnimation);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			
			switch(position){
			case 0:
				return new HomeScreen1();
			case 1:
				return new HomeScreen2();
			case 2:
				return new HomeScreen3();
			case 3:
				return new HomeScreen4();
			}
			return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_home).toUpperCase(l);
			case 1:
				return getString(R.string.title_events).toUpperCase(l);
			case 2:
				return getString(R.string.title_announcements).toUpperCase(l);
			case 3:
				return getString(R.string.title_news).toUpperCase(l);

			}
			return null;
		}
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		if (item.getItemId() == R.id.action_about){
			Intent intent1 = new Intent(WHS_Home.this, About.class);
			startActivity(intent1);
		}
		else{
			Intent intent2 = new Intent(WHS_Home.this, Schedule.class);
			startActivity(intent2);
		}
		
		
		
		return true;
	}
}













