package com.bartosztanski.visitreservation.service;

import java.util.Calendar;
import java.util.Date;

public class DateService {
	
	Calendar cal = Calendar.getInstance();
	
	public Date getPrevWeekMonday(Date week) {
		
		cal.setTime(week);
		cal.add(Calendar.DATE, -7);
		cal.set(Calendar.DAY_OF_WEEK,2);
		Date prevWeek = cal.getTime();
		return prevWeek;
	}
	
	public Date getNextWeekMonday(Date week) {
		cal.setTime(week);
		cal.add(Calendar.DATE, 7);
		cal.set(Calendar.DAY_OF_WEEK,2);
		Date nextWeek = cal.getTime();
		return nextWeek;
	}
	
	public Date getCurrentWeekMonday(Date week) {
		cal.setTime(week);
		cal.set(Calendar.DAY_OF_WEEK,2);
		Date thisWeek = cal.getTime();
		return thisWeek;
	}
	

}
