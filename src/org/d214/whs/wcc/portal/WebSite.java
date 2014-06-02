package org.d214.whs.wcc.portal;

public class WebSite {
	
	public String siteTitle;
    
    public WebSite(String title)
    {
        if ((title == null))
            throw new NullPointerException();
        siteTitle = title;
        
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return siteTitle;
    }
	
}
