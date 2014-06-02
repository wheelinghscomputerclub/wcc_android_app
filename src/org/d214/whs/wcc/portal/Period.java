package org.d214.whs.wcc.portal;

public class Period {
	
	public String period, start, end;
	
	public Period(String p, String s, String e){
		
		if ((p == null) || (s == null || e == null))
            throw new NullPointerException();
		period = p;
		start = s;
		end = e;
		
	} 
	
	@Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return start + ", " + end + " " + period;
    }

}
