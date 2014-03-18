package com.example.whs;

public class UpcomingEvent
{
	public String eventDate, eventTitle;
    
    public UpcomingEvent(String date, String title)
    {
        if ((date == null) || (title == null))
            throw new NullPointerException();
        eventDate = date;
        eventTitle = title;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return eventDate + " " + eventTitle;
    }
}
