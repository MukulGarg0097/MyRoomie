package com.myRoomie.Utilities;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtil {

	public static final String TIMESTAMP_PATTERN = "dd-MM-yyyy HH:mm:ss";
	public static final String DB_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN = "dd-MM-yyyy";
	public static final String TIME_PATTERN_24_HOUR = "HH:mm:ss";
	public static final String DB_DATE_PATTERN = "yyyy-MM-dd";
	public static final String TIME_PATTERN = "HH:mm";
	public static final String TIME_PATTERN_12_HOUR = "dd-MM-yyyy  hh:mm aa";
	public static final String START_24_HOUR_TIME_PATTERN = "00:00:00";
	public static final String END_24_HOUR_TIME_PATTERN = "23:59:59";
	public static final String DATE_WITHOUT_SPACE_PATTERN = "yyyyMMdd";
	public static final String DATE_EKYC_PATTERN = "yyMMddHHmmss";

	public static String formateDate(Date date, String format) {
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
		}
		return null;
	}
	
	public static Date formateDateToDate(Date date, String format) {
		try {
			return new SimpleDateFormat(format).parse(formateDate(date , format));
		} catch (Exception e) {
		}
		return null;
	}

	public static Date parseDate(String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (Exception e) {
		}
		return null;
	}

	public static String parseAndFormatDate(String dateString, String parseFormat, String formatFormat) {
		Date date = parseDate(dateString, parseFormat);
		return formateDate(date, formatFormat);
	}

	public static String formatTime(LocalTime time, String format) {
		try {
			return time.format(DateTimeFormatter.ofPattern(format));
		} catch (Exception e) {
		}
		return null;
	}

	public static LocalTime parseTime(String time, String format) {
		try {
			return LocalTime.parse(time, DateTimeFormatter.ofPattern(format));
		} catch (Exception e) {
		}
		return null;
	}

	public static Date addToDate(Date date, int years, int months) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR, years);
			cal.add(Calendar.MONTH, months);
			return cal.getTime();
		} catch (Exception e) {
		}
		return null;
	}

	public static Date convertFromSqlToUtil(java.sql.Date date) {
		return new Date(date.getTime());
	}

	public static java.sql.Date convertFromUtilToSql(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Date getSqlDate() {
		java.sql.Date date = new java.sql.Date(new Date().getTime());
		return date;
	}

	public static List<String> getDatesBetween(Date from, Date till) {
		if (from == null || till == null) {
			return null;
		}
		List<String> dates = new ArrayList<>();
		Calendar start = Calendar.getInstance();
		start.setTime(from);
		Calendar end = Calendar.getInstance();
		end.setTime(till);
		end.add(Calendar.DATE, 1);
		for (start.getTime(); start.before(end); start.add(Calendar.DATE, 1)) {
			dates.add(formateDate(start.getTime(), DATE_PATTERN));
		}
		return dates;
	}

	public static String formatDateTimeZone(Date date, String format, String timeZone) {
		if (date == null || format == null || timeZone == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (timeZone == null || "".equalsIgnoreCase(timeZone.trim())) {
			timeZone = Calendar.getInstance().getTimeZone().getID();
		}
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdf.format(date);
	}

	public static Date getDateAndTime(String date, String time) {
		if (date == null || time == null) {
			return null;
		}
		return parseDate(date + " " + time, TIMESTAMP_PATTERN);
	}
}
