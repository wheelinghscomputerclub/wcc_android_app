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

public class WebSitesAdapter  extends ArrayAdapter<WebSite>{

	protected Context mContext;
	protected List<WebSite> mSites;
	
	public WebSitesAdapter(Context context, List<WebSite> events){
		super(context, R.layout.websites_list, events);
		mContext = context;
		mSites = events;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
			convertView = LayoutInflater.from(mContext).inflate(R.layout.websites_list, null);
			holder = new ViewHolder();
			holder.titleLabel = (TextView) convertView.findViewById(R.id.webSiteTitle);
			holder.siteImage = (ImageView) convertView.findViewById(R.id.webSiteimage);
			
			WebSite site = mSites.get(position);
		
			holder.titleLabel.setText(site.siteTitle);
			
			if (site.siteTitle.equalsIgnoreCase("homelogic"))
				holder.siteImage.setImageResource(R.drawable.homelogic);
			else if (site.siteTitle.equalsIgnoreCase("moodle"))
				holder.siteImage.setImageResource(R.drawable.moodle);
			else if (site.siteTitle.equalsIgnoreCase("calendar"))
				holder.siteImage.setImageResource(R.drawable.calendar_icon);
			else if (site.siteTitle.equalsIgnoreCase("family connection"))
				holder.siteImage.setImageResource(R.drawable.familyconnection);
			else if (site.siteTitle.equalsIgnoreCase("athletic page"))
				holder.siteImage.setImageResource(R.drawable.sports_icon);
		

		
		return convertView;
	}
	
	private static class ViewHolder{
		TextView titleLabel;
		ImageView siteImage;
	}
	
}









