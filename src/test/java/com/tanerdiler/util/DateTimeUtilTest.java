package com.tanerdiler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import com.tanerdiler.util.HumanFriendlyTime.TimeType;

public class DateTimeUtilTest {

	public Calendar getPastTime(int amount, TimeType type){
		Calendar pastTime = Calendar.getInstance(); 
		int calendarField = 0;
		switch(type){
		case SEC : 
			calendarField = Calendar.SECOND; break;
		case MIN :
			calendarField = Calendar.MINUTE; break;
		case HOUR :
			calendarField = Calendar.HOUR; break;
		case DAY :
			calendarField = Calendar.DATE; break;
		case WEEK :
			calendarField = Calendar.WEEK_OF_MONTH; break;
		case MONTH :
			calendarField = Calendar.MONTH; break;
		}
		pastTime.add(calendarField, -1 * amount);
		return pastTime;
	}

	@Test
	public void testGetHumanFriendlyTime(){
		Calendar fiveSecBefore = getPastTime(5, TimeType.SEC);
		Calendar twoMinBefore = getPastTime(2, TimeType.MIN);
		Calendar fifteenHourBefore = getPastTime(15, TimeType.HOUR);
		Calendar oneDayBefore = getPastTime(1, TimeType.DAY);
		Calendar fourDayBefore = getPastTime(4, TimeType.DAY);
		Calendar twoWeekBefore = getPastTime(2, TimeType.WEEK);
		Calendar threeMonthBefore = getPastTime(3, TimeType.MONTH);
		
		HumanFriendlyTime fiveSecTime = DateTimeUtil.getHumanFriendlyTime(fiveSecBefore);
		assertTrue(fiveSecTime.getAmount() == 5);
		assertEquals(fiveSecTime.getType(), TimeType.SEC);
		
		HumanFriendlyTime twoMinTime = DateTimeUtil.getHumanFriendlyTime(twoMinBefore);
		assertTrue(twoMinTime.getAmount() == 2);
		assertEquals(twoMinTime.getType(), TimeType.MIN);
		
		HumanFriendlyTime fifteenHourTime = DateTimeUtil.getHumanFriendlyTime(fifteenHourBefore);
		assertTrue(fifteenHourTime.getAmount() == 15);
		assertEquals(fifteenHourTime.getType(), TimeType.HOUR);
		
		HumanFriendlyTime oneDayTime = DateTimeUtil.getHumanFriendlyTime(oneDayBefore);
		assertTrue(oneDayTime.getAmount() == 1);
		assertEquals(oneDayTime.getType(), TimeType.DAY);
		
		HumanFriendlyTime fourDayTime = DateTimeUtil.getHumanFriendlyTime(fourDayBefore);
		assertTrue(fourDayTime.getAmount() == 4);
		assertEquals(fourDayTime.getType(), TimeType.DAY);
		
		HumanFriendlyTime twoWeekTime = DateTimeUtil.getHumanFriendlyTime(twoWeekBefore);
		assertTrue(twoWeekTime.getAmount() == 2);
		assertEquals(twoWeekTime.getType(), TimeType.WEEK);
		
		HumanFriendlyTime threeMonthTime = DateTimeUtil.getHumanFriendlyTime(threeMonthBefore);
		assertTrue(threeMonthTime.getAmount() == 3);
		assertEquals(threeMonthTime.getType(), TimeType.MONTH);
		
	}
}
