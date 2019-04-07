package com.distribution.wamoli.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CustomDateUtil {

	/**
	 * 
	 * 
	 * 获取日期年份
	 * 
	 * 
	 * @param date
	 *            日期
	 * 
	 * 
	 * @return
	 */

	public static int getYear(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 
	 * 功能描述：返回月
	 * 
	 * @param date
	 * 
	 * @return 返回月份
	 */

	public static int getMonth(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.MONTH) + 1;

	}

	public static int getDay(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 
	 * 
	 * 功能描述：返回小
	 * 
	 * @param date
	 * 
	 * 
	 * @return 返回小时
	 */

	public static int getHour(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.HOUR_OF_DAY);

	}

	/**
	 * 
	 * 
	 * 功能描述：返回分
	 * 
	 * @param date
	 * 
	 * @return 返回分钟
	 */

	public static int getMinute(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.MINUTE);

	}

	public static int getSecond(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.SECOND);

	}

	/**
	 * 功能描述：返回毫
	 * 
	 * 
	 * @return 返回毫
	 */

	public static long getMillis(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.getTimeInMillis();

	}

	public static String getCurrentYear() {
		SimpleDateFormat _SimpleYearAndMonthFormat = new SimpleDateFormat("yyyy");

		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleYearAndMonthFormat.format(curdate.getTime());
	}

	public static String getCurrentMonth() {
		SimpleDateFormat _SimpleYearAndMonthFormat = new SimpleDateFormat("MM");

		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleYearAndMonthFormat.format(curdate.getTime());
	}

	public static String getCurrentDay() {
		SimpleDateFormat _SimpleYearAndMonthFormat = new SimpleDateFormat("dd");

		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleYearAndMonthFormat.format(curdate.getTime());
	}

	public static String getCurrentHour() {
		SimpleDateFormat _SimpleYearAndMonthFormat = new SimpleDateFormat("HH24");

		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleYearAndMonthFormat.format(curdate.getTime());
	}

	public static String getCurrentDateStringToYYYYMM() {
		SimpleDateFormat _SimpleYearAndMonthFormat = new SimpleDateFormat("yyyy-MM");

		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleYearAndMonthFormat.format(curdate.getTime());
	}

	public static String getCurrentDateStringToYYYYMMNotLine() {
		SimpleDateFormat _SimpleYearAndMonthFormat = new SimpleDateFormat("yyyyMM");

		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleYearAndMonthFormat.format(curdate.getTime());
	}

	public static String getCurrentDate14String() {
		SimpleDateFormat _SimpleYearAndMonthFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleYearAndMonthFormat.format(curdate.getTime());
	}

	/** */
	/**
	 * ��ȡ��ǰ����yyyymm
	 * 
	 * @return String
	 */
	public static String getCurrentDateString() {
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleDateFormat.format(curdate.getTime());
	}

	/**
	 * ��ȡ��ǰ����yyyymmdd
	 * 
	 * @return String
	 */
	public static String getCurrentDateStringNotLine() {
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleDateFormat.format(curdate.getTime());
	}

	/**
	 * ��ʽ��ָ��������
	 * 
	 * @param date
	 * @return һ�������ַ���ʽΪyyyy-MM-dd
	 */
	public static String formatDate(java.util.Date date) {

		if (date == null)
			return "";
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return _SimpleDateFormat.format(date);
	}

	public static String formatDate(java.sql.Date date) {

		if (date == null)
			return "";
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return _SimpleDateFormat.format(new java.util.Date(date.getTime()));
	}

	/**
	 * ʹ��"yyyy-MM-dd HH:mm:ss"��hh��ʽ��һ������ʱ������
	 * 
	 * @param date
	 *            Ҫ��ʽ��������ʱ��
	 * @return
	 */
	public static String formatDateTime(java.util.Date date) {

		if (date == null)
			return "";
		SimpleDateFormat _SimpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return _SimpleDateTimeFormat.format(date);
	}

	/**
	 * ���һ����Ч������
	 * 
	 * @param date
	 *            �� ��ʽ����Ϊyyyy-MM-dd
	 * @return һ����Ч������
	 * @throws ParseException
	 */
	public static java.util.Date parseDate(String dateStr) {

		if (dateStr == null || "".equals(dateStr.trim()))
			return null;
		// �ϸ����ڼ��
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		_SimpleDateFormat.setLenient(false);
		Date result;
		try {
			result = _SimpleDateFormat.parse(dateStr.trim());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public static Date parseDateTime(String dateStr) {

		if (dateStr == null || "".equals(dateStr))
			return null;
		SimpleDateFormat _SimpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return _SimpleDateTimeFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/** */
	/**
	 * ��ȡ��ǰʱ��yyyymmddhhmmss
	 * 
	 * @return String
	 */
	public static String getCurrentTimeString() {
		SimpleDateFormat _SimpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleDateTimeFormat.format(curdate.getTime());
	}

	public static Date getCurrentDate() {
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return curdate.getTime();
	}

	// �õ��µĵ�һ��
	public static Date getStartOfMonth() {
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		Date date = curdate.getTime();
		int year, month, day;
		year = date.getYear();
		month = date.getMonth();
		day = 1;
		Date d = new Date(year, month, 1);
		return d;
	}

	// �õ��µĵ�һ��
	public static Date getStartOfMonth(Date date) {
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		int year, month, day;
		year = date.getYear();
		month = date.getMonth();
		day = 1;
		Date d = new Date(year, month, 1);
		return d;
	}

	// �õ��µ����һ��
	public static Date getEndOfMonth() {
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		Date date = curdate.getTime();
		int year, month, day;
		year = date.getYear();
		month = date.getMonth() + 1;
		day = 1;
		if (month > 11) {
			year++;
			month = 0;
		}
		Date d = new Date(year, month, 1);
		d.setDate(d.getDate() - 1);
		return d;
	}

	// �õ��µ����һ��
	public static Date getEndOfMonth(Date date) {
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		int year, month, day;
		year = date.getYear();
		month = date.getMonth() + 1;
		day = 1;
		if (month > 11) {
			year++;
			month = 0;
		}
		Date d = new Date(year, month, 1);
		d.setDate(d.getDate() - 1);
		return d;
	}

	// �õ� ������������
	public static Date addDays(Date date, int addDays) {
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		curdate.setTime(date);
		curdate.add(Calendar.DATE, addDays);

		return curdate.getTime();
	}

	// �õ� ������������
	public static String addDaysString(String date, int addDays) {

		return formatDate(addDays(parseDate(date), addDays));

	}

	// �õ� ������������
	public static Date addMonths(Date date, int addMonths) {
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		curdate.setTime(date);
		curdate.add(Calendar.MONTH, addMonths);

		return curdate.getTime();
	}

	// �õ� ������������
	public static String addMonthsString(String date, int addMonths) {

		return formatDate(addMonths(parseDate(date), addMonths));

	}

	// �õ� ������������
	public static Date addYears(Date date, int addYears) {
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		curdate.setTime(date);
		curdate.add(Calendar.YEAR, addYears);

		return curdate.getTime();
	}
	public static Date addHours(Date date, int addYears) {
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		curdate.setTime(date);
		curdate.add(Calendar.HOUR, addYears);

		return curdate.getTime();
	}
	// �õ� ������������
	public static String addYearsString(String date, int addYears) {

		return formatDate(addYears(parseDate(date), addYears));

	}

	// �õ��µĵ�һ�� �ַ�
	public static String getStartOfMonthString() {
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return _SimpleDateFormat.format(getStartOfMonth());

	}

	// �õ��µ����һ�� �ַ�
	public static String getEndOfMonthString() {
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return _SimpleDateFormat.format(getEndOfMonth());

	}

	// �õ��µĵ�һ�� �ַ�
	public static String getStartOfMonthString(String date) {
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return _SimpleDateFormat.format(getStartOfMonth(parseDate(date)));

	}

	// �õ��µ����һ�� �ַ�
	public static String getEndOfMonthString(String date) {
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return _SimpleDateFormat.format(getEndOfMonth(parseDate(date)));

	}

	// �õ���ĵ�һ��
	public static Date getStartOfYear() {
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		Date date = curdate.getTime();
		int year, month, day;
		year = date.getYear();
		month = 0;
		day = 1;
		Date d = new Date(year, month, 1);
		return d;
	}

	// �õ���ĵ�һ�� �ַ�
	public static String getStartOfYearString() {
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return _SimpleDateFormat.format(getStartOfYear());

	}

	// �õ�ȥ��Ľ������� �ַ�
	public static String getLastYearDateString() {
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		curdate.add(Calendar.YEAR, -1);
		return _SimpleDateFormat.format(curdate.getTime());
	}

	/**
	 * ����
	 * 
	 * �ַ�ת����java.sql.Date
	 */
	public static java.sql.Date parseSqlDate(String str) {
		java.util.Date date = parseDate(str);
		if (date == null)
			return null;

		java.sql.Date result = new java.sql.Date(date.getTime());
		return result;
	}

	/**
	 * ����
	 * 
	 * �ַ�ת����java.sql.Timestamp������ʱ����
	 */
	public static java.sql.Timestamp parseSqlDateTime(String str) {
		Date date = parseDateTime(str);
		if (date == null)
			return null;

		java.sql.Timestamp result = new java.sql.Timestamp(date.getTime());
		return result;
	}

	public static java.sql.Timestamp parseSqlDateTime(java.util.Date date) throws ParseException {
		if (date == null)
			return null;

		java.sql.Timestamp result = new java.sql.Timestamp(date.getTime());
		return result;
	}

	/**
	 * ���� 8λ�ַ�ת��������
	 * 
	 * @throws ParseException
	 */
	public static java.util.Date numberYMDToDate(String str) {

		if (str == null || "".equals(str) || "00000000".equals(str))
			return null;

		if (str.trim().length() != 8) {
			throw new RuntimeException("�ַ���8λ");
		}

		SimpleDateFormat _SimpleYMDDateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			return _SimpleYMDDateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static String dateToNumberYMD(java.util.Date date) {
		if (date == null)
			return "";
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return _SimpleDateFormat.format(date);

	}

	public static String sqlDateToNumberYMD(java.sql.Date sqldate) {
		if (sqldate == null)
			return "";
		java.util.Date date = new java.util.Date(sqldate.getTime());
		return dateToNumberYMD(date);
	}

	/**
	 * ���� 6λ�ַ�ת��������
	 * 
	 * @throws ParseException
	 */
	public static java.util.Date numberYMToDate(String str) {
		if (str.trim().length() != 6) {
			throw new RuntimeException("�ַ���6λ");
		}
		str += "01";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		try {
			return formatter.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static java.sql.Date numberYMDToSqlDate(String str) {
		Date date = numberYMDToDate(str);
		if (date == null)
			return null;

		java.sql.Date result = new java.sql.Date(date.getTime());
		return result;
	}

	/**
	 * ���� �ַ�ת��������
	 * 
	 * @throws ParseException
	 */
	public static java.util.Date numberToDate(String str) {
		Date date = null;
		if (str.trim().length() == 6) {
			date = numberYMToDate(str);
		} else if (str.trim().length() == 8) {
			date = numberYMDToDate(str);
		} else {
			throw new RuntimeException("�ַ�λ������");
		}

		return date;

	}

	/**
	 * ���� yyyy-MM-dd ת���� 8λ�ַ�yyyyMMdd
	 * 
	 * @throws ParseException
	 */
	public static String dateToNumberYMD(String str) {

		Date date = parseDate(str);
		if (date == null)
			return "";

		SimpleDateFormat _SimpleYMDDateFormat = new SimpleDateFormat("yyyyMMdd");
		String result = _SimpleYMDDateFormat.format(date);
		return result;

	}

	/**
	 * ���� 8λ�ַ�yyyyMMdd ת���� yyyy-MM-dd
	 * 
	 * @throws ParseException
	 */
	public static String numberYMDtoDateStr(String str) {
		String result = "";
		Date date = numberYMDToDate(str);
		if (date == null)
			return "";

		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		result = _SimpleDateFormat.format(date);
		return result;

	}

	// �������������������
	public static int getMonthsOldDateToNewDate(String sStartDate, String sEndDate) {
		int db = 0;
		// ���� = ���۾���ֹ�� �C ��ǰ�۾��꣩ * 12 �� ���۾���ֹ�� �C ��ǰ�۾��£�
		Date startDate = parseDate(sStartDate);
		Date endDate = parseDate(sEndDate);
		db = (endDate.getYear() - startDate.getYear()) * 12 + (endDate.getMonth() - startDate.getMonth()) + 1;
		return db;
	}

	// sqlʱ���--ת����----14λ����ʱ��

	public static String getCurrentDate17String() {
		SimpleDateFormat _SimpleYearAndMonthFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleYearAndMonthFormat.format(curdate.getTime());
	}

	public static String getCurrentDate8String() {
		SimpleDateFormat _SimpleYearAndMonthFormat = new SimpleDateFormat("yyyyMMdd");

		Calendar curdate = Calendar.getInstance();
		curdate = Calendar.getInstance(Locale.CHINESE);
		return _SimpleYearAndMonthFormat.format(curdate.getTime());
	}

	public static java.sql.Date getCurrentSqlDate() {
		return java.sql.Date.valueOf(getCurrentDateString());
	}

	public static String dateStart14(String date) {
		Date a = parseDate(date);
		return date.replace("-", "");
	}

	public static String dateEnd14(String date) {
		Date a = parseDate(date);
		return date.replace("-", "") + "235959";
	}

	public static String formatString10(String dateStr) {
		return formatDate(numberYMDToDate(dateStr));
	}

	/**
	 * 取得当月天数
	 * */
	public static int getCurrentMonthLastDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到指定月的天数
	 * */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
}
