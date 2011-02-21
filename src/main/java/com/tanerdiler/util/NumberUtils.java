package com.tanerdiler.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtils {
	private static NumberFormat simpleFormatter = new DecimalFormat("#,0");
	
	public static String formatNumber(Double number){
		if(number != null){
			return simpleFormatter.format(number);
		}
		return "#";
	}
}
