package com.example.whs;

import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.graphics.Typeface;
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
				if (arg0 == 1 && page == 0){
					animateInfo2();
				}else if (arg0 == 1 && page == 1)
					animateInfo1();
				}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.whs__home, menu);
		
		bar = (View) findViewById(R.id.infoBar1);
		label = (TextView) findViewById(R.id.infoText1);
		datLabel = (TextView) findViewById(R.id.labelEventDate);
		messageLabel = (TextView) findViewById(R.id.labelEventMessage);
		daysLeftLabel = (TextView) findViewById(R.id.labelDaysLeft);
		showButton = (ImageView) findViewById(R.id.imageInfo);
		
		Typeface font1 = Typeface.createFromAsset(this.getAssets(), "Roboto-Regular.ttf"); 
		//Typeface font2 = Typeface.createFromAsset(this.getAssets(), "DroidSans-Bold.ttf");
		datLabel.setTypeface(font1);
		messageLabel.setTypeface(font1);
		daysLeftLabel.setTypeface(font1);
		
		getInfo = new HomeScreen2();
		
		handler = new Handler();
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
								loading = false;
							}
						}
					});
				}
				/*while (true){
					try{
						Thread.sleep(100);
					} catch(InterruptedException e){
						e.printStackTrace();
					}
					handler.post(new Runnable() {
						
						@SuppressWarnings("deprecation")
						@Override
						public void run() {
							messageLabel.setText(HomeScreen2.message);
							datLabel.setText(HomeScreen2.date);
							Date clock = new Date();
							monthDate = Integer.parseInt(HomeScreen2.date.substring(HomeScreen2.date.length() - 8, HomeScreen2.date.length() - 6));
							if (clock.getDate() <= monthDate)
								daysLeftLabel.setText(String.valueOf(monthDate - clock.getDate()) + " days left");
							else
								daysLeftLabel.setText(String.valueOf(clock.getDate()- monthDate) + " days left");
						}
					});
				}*/
			}
		};
		new Thread(runnable).start();
		
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
		
		Intent intent = new Intent(WHS_Home.this, ChangeSettings.class);
		startActivity(intent);
		
		return true;
	}
}













