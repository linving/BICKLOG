package com.tanerdiler.util;

import java.io.Serializable;
import java.util.Calendar;

public class HumanFriendlyTime implements Serializable {
	private static final long serialVersionUID = 1307357166046011333L;

	public enum TimeType {
		NORMAL, SEC, MIN, HOUR, DAY, WEEK, MONTH 
	};
	
	private long amount;
	private TimeType type;
	private Calendar time;

	public HumanFriendlyTime(Calendar time) {
		this.time = time;
		this.type = TimeType.NORMAL;
	}

	public HumanFriendlyTime(long amount, TimeType type) {
		this.amount = amount;
		this.type = type;
	}
	
	public boolean isSingle(){
		if(amount > 1){
			return false;
		} 
		return true;
	}

	public Calendar getTime() {
		return time;
	}

	public long getAmount() {
		return amount;
	}

	public TimeType getType() {
		return type;
	}

	

}
