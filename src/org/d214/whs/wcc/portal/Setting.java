package org.d214.whs.wcc.portal;

public class Setting
{
	public String settingTitle, settingDetails;
	public boolean setttingCheckBox, settingIsChecked;
    
    public Setting(String title, String detail, boolean check, boolean isChecked)
    {
        if ((title == null) || (detail == null))
            throw new NullPointerException();
        settingTitle = title;
        settingDetails = detail;
        setttingCheckBox = check;
        settingIsChecked = isChecked;
        
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return settingTitle + " " + settingDetails;
    }
}
