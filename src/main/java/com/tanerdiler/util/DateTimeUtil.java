package com.tanerdiler.util;

import java.util.Calendar;

import com.tanerdiler.util.HumanFriendlyTime.TimeType;

public class DateTimeUtil {
	private static long ONE_SEC = 1000;
	private static long ONE_MIN = 60 * 1000;
	private static long ONE_HOUR = 60 * ONE_MIN;
	private static long ONE_DAY = 24 * ONE_HOUR;
	private static long ONE_WEEK = 7 * ONE_DAY;
	private static long ONE_MONTH = 30 * ONE_DAY;
	private static long FIVE_MONTH = 5 * ONE_MONTH;
	
	public static HumanFriendlyTime getHumanFriendlyTime(Calendar time){
		Calendar now = Calendar.getInstance();
		long subTime = now.getTimeInMillis()-time.getTimeInMillis();
		long amount = 0;
		if(ONE_MIN > subTime){
			amount = subTime / ONE_SEC;
			return new HumanFriendlyTime(amount, TimeType.SEC);
		} else if(ONE_MIN <= subTime && subTime < ONE_HOUR){
			amount = subTime / ONE_MIN;
			return new HumanFriendlyTime(amount, TimeType.MIN);
		} else if(ONE_HOUR <= subTime && subTime < ONE_DAY){
			amount = subTime / ONE_HOUR;
			return new HumanFriendlyTime(amount, TimeType.HOUR);
		} else if(ONE_DAY <= subTime && subTime < ONE_WEEK){
			amount = subTime / ONE_DAY;
			return new HumanFriendlyTime(amount, TimeType.DAY);
		} else if(ONE_WEEK <= subTime && subTime < ONE_MONTH){
			amount = subTime / ONE_WEEK;
			return new HumanFriendlyTime(amount, TimeType.WEEK);
		} else if(ONE_MONTH <= subTime && subTime < FIVE_MONTH){
			amount = subTime / ONE_MONTH;
			return new HumanFriendlyTime(amount, TimeType.MONTH);
		} else {
			return new HumanFriendlyTime(time);
		}
	}
}
