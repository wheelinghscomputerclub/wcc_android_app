package com.example.whs;

public class DailyAnnouncements
{
    public String date, text;
    
    public DailyAnnouncements(String eventDate, String eventText)
    {
        if ((eventDate == null) || (eventText == null))
            throw new NullPointerException();
        date = eventDate;
        text = eventText;
    }
}
