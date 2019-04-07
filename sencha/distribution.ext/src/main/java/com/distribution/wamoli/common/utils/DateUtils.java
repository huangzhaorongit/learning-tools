/**
 * Copyright &copy; 2015-2020 <a href="http://www.yihere.org/">JeePlus</a> All rights reserved.
 */
package com.distribution.wamoli.common.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author yihere
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}
	
	
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static java.sql.Date getSqlCurrentDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	public static java.sql.Date getCurrentDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	public static Time getCurrentTime() {
		return new Time(System.currentTimeMillis());
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static void main(String[] args) {
		Date date = new Date();
		
		System.out.println(getSpecifiedDayBefore(date));
		System.out.println(getSpecifiedDayAfter(date));
		// System.out.println(formatDate(parseDate("2010/3/6")));
		// System.out.println(getDate("yyyy年MM月dd日 E"));
		// long time = new Date().getTime()-parseDate("2012-11-19").getTime();
		// System.out.println(time/(24*60*60*1000));
	}

	/**
	 * 获得指定日期的前一天
	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(Date date) {
		Calendar c = Calendar.getInstance();
     	c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);
		String dayAfter = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		return dayAfter;
	}




	/**
	 * 将指定字符串转换成日期
	 * 
	 * @param date
	 *            String 日期字符串
	 * @param datePattern
	 *            String 日期格式
	 * @return Date
	 */
	public static java.util.Date getFormatDate(String date, String datePattern) {
		SimpleDateFormat sd = new SimpleDateFormat(datePattern);
		return sd.parse(date, new java.text.ParsePosition(0));
	}
	

	/**
	 * 将指定日期对象转换成格式化字符串
	 * 
	 * @param date
	 *            Date XML日期对象
	 * @param datePattern
	 *            String 日期格式
	 * @return String
	 */
	public static String getFormattedString(Date date, String datePattern) {
		SimpleDateFormat sd = new SimpleDateFormat(datePattern);

		return sd.format(date);
	}

	/**
	 * 将指定XML日期对象转换成格式化字符串
	 * 
	 * @param xmlDate
	 *            Date XML日期对象
	 * @param datePattern
	 *            String 日期格式
	 * @return String
	 */
	public static String getFormattedString(XMLGregorianCalendar xmlDate,
			String datePattern) {
		SimpleDateFormat sd = new SimpleDateFormat(datePattern);

		Calendar calendar = xmlDate.toGregorianCalendar();

		return sd.format(calendar.getTime());
	}

	/**
	 * 将指定XML日期对象转换成日期对象
	 * 
	 * @param xmlDate
	 *            Date XML日期对象
	 * @param datePattern
	 *            String 日期格式
	 * @return Date
	 */
	public static Date xmlGregorianCalendar2Date(XMLGregorianCalendar xmlDate) {
		return xmlDate.toGregorianCalendar().getTime();
	}

	public static String getThisYear() {
		// 获得当前日期
		Calendar cldCurrent = Calendar.getInstance();
		// 获得年月日
		String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
		return strYear;
	}

	public static XMLGregorianCalendar convert2XMLCalendar(Calendar calendar) {
		try {
			DatatypeFactory dtf = DatatypeFactory.newInstance();			
			return dtf.newXMLGregorianCalendar(
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH)+1,
					calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.HOUR),
					calendar.get(Calendar.MINUTE),
					calendar.get(Calendar.SECOND),
					calendar.get(Calendar.MILLISECOND),
					calendar.get(Calendar.ZONE_OFFSET)/(1000*60));
				
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 获取当天时间
	public static java.sql.Timestamp getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String dateString = dateFormat.format(now);
		SimpleDateFormat sd = new SimpleDateFormat(dateformat);
		Date dateFormt = sd.parse(dateString, new java.text.ParsePosition(0));
		java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt
				.getTime());

		return dateTime;
		// return hehe;
	}

	// 获取指定时间
	public static java.sql.Timestamp getNowNewTime(String date,String dateformat){    
        //Date   now   =   new   Date();       
        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat(dateformat);//可以方便地修改日期格式   
        dateFormat.parse(date, new java.text.ParsePosition(0));
        
      //  String  dateString= dateFormat.format(date); 
       Date dateFormt= dateFormat.parse(date, new java.text.ParsePosition(0));
       java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt.getTime());
     
       
       return dateTime;
       // return hehe;    
    }
	/**
	 * @param 含有yyyy-MM-dd'T'hh:mm:ss.SSS格式的时间转换.
	 * @return
	 */
	public static String getTFormatString(String tdate) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String str ="";
		try {
			java.util.Date date = format1.parse(tdate);
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 str = format2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;
	}
	
//	public static void main(String[] args) {
//		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
//		String date = "20110202";
//		System.out.println(sd.parse(date, new java.text.ParsePosition(0)));
//		System.out.println(getBefore2HourDate());
//	}
//	//获取当前时间前2个小时的时间。
//	public static String getBefore2HourDate(){
//		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");  
//		 Calendar c = Calendar.getInstance();    
//		 c.add(Calendar.HOUR_OF_DAY, -2); // 目前時間加3小時    
//		return df.format(c.getTime());
//		
//	}
	
	/**
	 * 
	 * @param time1   当前时间  
	 * @param time2  比较时间 
	 * @return  如果time1比time2大gap分钟，则返回true;
	 */
	public static boolean compareDateTime(Date time1, Date time2, int gap) {
		return time1.getTime() - time2.getTime() > gap * 60 * 1000;
	}
}

