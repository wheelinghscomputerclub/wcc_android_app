package org.d214.whs.wcc.portal;

import org.d214.whs.wcc.portal.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Read extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read, menu);
		
		final TextView title = (TextView) findViewById(R.id.titleLabel);
		final TextView message = (TextView) findViewById(R.id.anouncementLabel);
		title.setText(getIntent().getExtras().getString("title"));
		message.setText(getIntent().getExtras().getString("details"));
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		finish();
		return true;
	}

}
