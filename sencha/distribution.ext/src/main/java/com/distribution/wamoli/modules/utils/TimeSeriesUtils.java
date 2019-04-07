package com.distribution.wamoli.modules.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeSeriesUtils {
    public String YMD_DB_PATTERN = "yyyyMMdd";
    public String YMD_PAGE_PATTERN = "yyyy-MM-dd";

	public static final boolean CURVE_START_WITH_ZERO=true;
    /**24点列名。*/
    public static final String[] CURVE_24COLUMN=new String[24];
    public static final String[] X_CATEGORIES_24COLUMN=new String[24];
    /**48点列名。*/
    public static final String[] CURVE_48COLUMN=new String[48];
    public static final String[] X_CATEGORIES_48COLUMN=new String[48];
	/**96点列名。*/
	public static final String[] CURVE_96COLUMN=new String[96];
	public static final String[] X_CATEGORIES_96COLUMN=new String[96];
	/**288点列名。*/
	public static final String[] CURVE_288COLUMN=new String[288];
	public static final String[] X_CATEGORIES_288COLUMN=new String[288];
	static {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		SimpleDateFormat xdf = new SimpleDateFormat("HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 0, 1, 0, 0, 0);
		if (!CURVE_START_WITH_ZERO)
			calendar.add(Calendar.MINUTE, 5);
		int j=0,k=0,l=0;
		for (int i = 0; i < 288; i++) {
			String time = xdf.format(calendar.getTime());
			String vtime = sdf.format(calendar.getTime());
			if (!CURVE_START_WITH_ZERO && "00:00".equals(time)) {
				time = "24:00";
			}
			if (!CURVE_START_WITH_ZERO && "0000".equals(vtime)) {
				vtime = "2400";
			}
			if(i%3 == 0){
				X_CATEGORIES_96COLUMN[j] = time;
				CURVE_96COLUMN[j] = "val" + vtime;
				j++;
			}
			if(i%6 == 0){
				X_CATEGORIES_48COLUMN[k] = time;
				CURVE_48COLUMN[k] = "val" + vtime;
				k++;
			}if(i%12 == 0){
				X_CATEGORIES_24COLUMN[l] = time;
				CURVE_24COLUMN[l] = "val" + vtime;
				l++;
			}
			X_CATEGORIES_288COLUMN[i] = time;
			CURVE_288COLUMN[i] = "val" + vtime;
			calendar.add(Calendar.MINUTE, 5);
		}
	}
}
