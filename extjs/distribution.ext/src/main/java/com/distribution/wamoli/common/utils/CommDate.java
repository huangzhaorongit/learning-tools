package com.distribution.wamoli.common.utils;

import java.util.Calendar;
import java.util.Date;

public class CommDate {
	
	
	private int year;		//年份
	private int month;		//月份
	private int day;		//日期
	private int hour;		//小时
	private int minute;		//分
	private int second;		//秒
	private int quarter;	//季度
	private int week;		//星期几
	private int weekofyear;	//一年中的星期

	
	public CommDate() {
		this(Calendar.getInstance());
	}
	
	public CommDate(Calendar c) {
		this.year = c.get(Calendar.YEAR);
		this.month = c.get(Calendar.MONTH)+1;
		this.day = c.get(Calendar.DATE);
		this.hour = c.get(Calendar.HOUR_OF_DAY);
		this.minute = c.get(Calendar.MINUTE);
		this.second = c.get(Calendar.SECOND);
		this.quarter = (this.month-1) / 3 + 1;
		c.setFirstDayOfWeek(Calendar.MONDAY);
		//c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		this.week = c.get(Calendar.DAY_OF_WEEK)-1;
		this.weekofyear = c.get(Calendar.WEEK_OF_YEAR);
	}
	
	
	
	public static CommDate valueOf(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return valueOf(c);
	}
	
	public static CommDate valueOf(Calendar c) {
		return new CommDate(c);
	}
	
	
	
	
	

	public String getYear() {
		String sy = "";
		if(year < 10) {
			sy = "000"+year;
		}else if(year < 100) {
			sy = "00"+year;
		}else if(year < 1000) {
			sy = "0"+year;
		}else {
			sy = String.valueOf(year);
		}
		return sy;
	}
	

	public String getMonth() {
		return month<10 ? "0"+month : String.valueOf(month);
	}


	public String getDay() {
		return day<10 ? "0"+day : String.valueOf(day);
	}


	public String getHour() {
		return hour<10 ? "0"+hour : String.valueOf(hour);
	}


	public String getMinute() {
		return minute<10 ? "0"+minute : String.valueOf(minute);
	}


	public String getSecond() {
		return second<10 ? "0"+second : String.valueOf(second);
	}
	
	public int getQuarter() {
		return this.quarter;
	}
	
	
	public String toString() {
		return getFullDate();
	}
	
	
	public String getShortDate() {
		return getYear()+"-"+getMonth()+"-"+getDay();
	}
	
	public String getTime() {
		return getHour()+":"+getMinute()+":"+getSecond();
	}
	
	public String getFullDate() {
		return getShortDate()+" "+getTime();
	}

	public int getWeek() {
		return week;
	}

	public String getWeekofyear() {
		return weekofyear<10 ? "0"+weekofyear : String.valueOf(weekofyear);
	}
	
	
	
	
	
	
	
}
