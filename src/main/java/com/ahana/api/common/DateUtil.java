package com.ahana.api.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


public class DateUtil {

	private static Logger logger = Logger.getLogger(DateUtil.class);

	public static final int MAX_DAY_IN_MONTH = 31;

	public static final int MAX_MONTH_IN_YEAR = 12;

	private static DateFormat DATETIME_FORMAT = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	private static DateFormat DATETIME_FORMAT_HYPEN = new SimpleDateFormat(
	"yyyy-MM-dd HH:mm:ss");

	public static final String MMDDYYYY = "MM/dd/yyyy";

	public static final String MMDDYY = "MM/dd/yy";

	public static final String MMDD = "MM/dd";

	public static final String YYYYMMDD_HHMMSS = "yyyy/MM/dd HH:mm:ss";

	public static final String YYYYMMDD_HHMMSS_HYPEN = "yyyy-MM-dd hh:mm:ss";
	
	public static final String YYYYMMDD_HHMMSS_HYPEN_24HR = "yyyy-MM-dd HH:mm:ss";
	
	public static final String YYYYMMDD_HYPEN = "yyyy-MM-dd";

	public static final String DDMMYYYYHHMMSS = "ddMMyyyyHHmmss";

	public static final String DDMMYYYYHHMMSS_SSS = "ddMMyyyyHHmmssSSS";

	public static final String[] dobDateFormats = { DateUtil.MMDDYY,
	DateUtil.MMDDYYYY };
	
	public static final String[] dateFormatArray = { "MMM dd, yyyy HH:mm:ss","MM/dd/yyyy HH:mm:ss","MM-dd-yyyy HH:mm:ss","yyyy-MM-dd HH:mm:ss","yyyy/MM/dd HH:mm:ss"};

	public static final String FORMAT_MMMddyyyyHHmmssz = "MMM dd, yyyy HH:mm:ss z";

	public static final String FORMAT_MMMddyyyyHHmma = "MMM dd, yyyy hh:mm a";
	
	public static final String FORMAT_MMMddyyyy = "MMM dd, yyyy";
	
	public static final String MMDDYYYY_HHMMSS = "MM/dd/yyyy HH:mm:ss";
	
	public static final String HHMMSS = "HH:mm:ss";
	
	public static final String TIME = "00:00:00";
	
	public static final String FORMAT_MM_DD_YYYY="MM-dd-yyyy HH:mm:ss";
	
	public static final String FORMAT_MAIL="yyyyMMdd'T'HHmmss";

	/**
	 * Returns a date string with month and day only for given timestamp date.
	 * Format MM/DD.
	 *
	 * @param date
	 *            date
	 * @return String
	 */
	public static String getDateAsStringMMDDOnly(final Timestamp date) {
		if (date == null) {
			return "xx/xx/xxxx";
		}
		int day = getDay(date);
		String dayS = "" + day;
		if (10 > day) {
			dayS = "0" + day;
		}
		int month = getMonth(date);
		String monthS = "yyy";
		if (month == 1) {
			monthS = "01";
		}
		if (month == 2) {
			monthS = "02";
		}
		if (month == 3) {
			monthS = "03";
		}
		if (month == 4) {
			monthS = "04";
		}
		if (month == 5) {
			monthS = "05";
		}
		if (month == 6) {
			monthS = "06";
		}
		if (month == 7) {
			monthS = "07";
		}
		if (month == 8) {
			monthS = "08";
		}
		if (month == 9) {
			monthS = "09";
		}
		if (month == 10) {
			monthS = "10";
		}
		if (month == 11) {
			monthS = "11";
		}
		if (month == 12) {
			monthS = "12";
		}

		return monthS + "/" + dayS;
	}

	/**
	 * Returns a date string with month, day and year for given timestamp date.
	 * Format MM/DD/YYYY.
	 *
	 * @param Timestamp
	 *            date
	 * @return String
	 */
	public static String getDateAsStringMMDDYYYY(Timestamp date) {
		if (date == null)
			return "xx/xx/xxxx";

		int day = getDay(date);
		String dayS = "" + day;
		if (10 > day) {
			dayS = "0" + day;
		}
		int month = getMonth(date);
		String monthS = "yyy";
		if (month == 1) {
			monthS = "01";
		}
		if (month == 2) {
			monthS = "02";
		}
		if (month == 3) {
			monthS = "03";
		}
		if (month == 4) {
			monthS = "04";
		}
		if (month == 5) {
			monthS = "05";
		}
		if (month == 6) {
			monthS = "06";
		}
		if (month == 7) {
			monthS = "07";
		}
		if (month == 8) {
			monthS = "08";
		}
		if (month == 9) {
			monthS = "09";
		}
		if (month == 10) {
			monthS = "10";
		}
		if (month == 11) {
			monthS = "11";
		}
		if (month == 12) {
			monthS = "12";
		}
		int year = getYear(date);

		return monthS + "/" + dayS + "/" + year;
	}
	/**
	 * Convert timestamp to String with given format.
	 *
	 * @param Timestamp
	 *            date
	 * @return String
	 * @throws Exception
	 */
	  public static String convertTimestampToString(Timestamp timestamp,String format)
			throws Exception {
		if (timestamp == null) {
			return null;
		} else {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				return sdf.format((java.util.Date) timestamp);
			} catch (Throwable e) {
				throw new Exception(Constants.INVALID_DATE);
			}
		}
	}


	/**
	 * Returns the day value from given Timestamp date.
	 *
	 * @param date
	 *            date
	 * @return int
	 */
	public static int getDay(final Timestamp date) {
		if (date == null) {
			return 00;
		}
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(date.getTime());
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (day < 1 || day > MAX_DAY_IN_MONTH) {
			return MAX_DAY_IN_MONTH;
		}
		return day;
	}

	/**
	 * Returns the month value from given Timestamp date.
	 *
	 * @param date
	 *            date
	 * @return int
	 */
	public static int getMonth(final Timestamp date) {
		if (date == null) {
			return 01;
		}
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(date.getTime());
		int month = c.get(Calendar.MONTH) + 1;
		if (month < 1 || month > MAX_MONTH_IN_YEAR) {
			return MAX_MONTH_IN_YEAR;
		}
		return month;
	}

	/**
	 * Returns the year value from given Timestamp date.
	 *
	 * @param date
	 *            date
	 * @return int
	 */
	public static int getYear(final Timestamp date) {
		GregorianCalendar c = new GregorianCalendar();
		int thisYear = c.get(Calendar.YEAR);
		if (date == null) {
			return thisYear;
		}
		c.setTimeInMillis(date.getTime());
		int year = c.get(Calendar.YEAR);
		/*
		 * if (year < (thisYear - 200) || year > (thisYear + 200)) { // At no
		 * time we want to allow for crap data return thisYear; }
		 */
		return year;
	}

	/**
	 * Returns Timestamp object for given year, month and day value.
	 *
	 * @param year
	 *            year
	 * @param month
	 *            month
	 * @param day
	 *            day
	 * @return Timestamp
	 */
	public static Timestamp getTimestamp(final int year, final int month,
			final int day) {
		GregorianCalendar c = new GregorianCalendar();
		c.set(year, month - 1, day);
		return new Timestamp(c.getTime().getTime());
	}

	/**
	 * Returns the hour(12 hour format) from given Timestamp date.
	 *
	 * @param Timestamp
	 *            date
	 * @return int
	 */
	public static int getHour12HrClock(Timestamp date) {
		GregorianCalendar c = new GregorianCalendar();
		if (date == null)
			return 00;
		c.setTimeInMillis(date.getTime());
		int hr = c.get(Calendar.HOUR);
		if (0 == hr) {
			hr = 12;
		}
		return hr;
	}
	/**
	 * Returns the hour from given Timestamp date.
	 *
	 * @param Timestamp
	 *            date
	 * @return int
	 */
    public static int getHour(Timestamp date) {
        GregorianCalendar c = new GregorianCalendar();
    	if(date == null) return 00;
        c.setTimeInMillis(date.getTime());
        return c.get(Calendar.HOUR_OF_DAY); 	
    }

	/**
	 * Returns minutes from given Timestamp date.
	 *
	 * @param Timestamp
	 *            date
	 * @return int
	 */
	public static int getMinute(Timestamp date) {
		GregorianCalendar c = new GregorianCalendar();
		if (date == null)
			return 00;
		c.setTimeInMillis(date.getTime());
		return c.get(Calendar.MINUTE);
	}

	/**
	 * Returns minutes from given Timestamp date.
	 *
	 * @param Timestamp
	 *            date
	 * @return int
	 */
	public static int getSecond(Timestamp date) {
		GregorianCalendar c = new GregorianCalendar();
		if (date == null)
			return 00;
		c.setTimeInMillis(date.getTime());
		return c.get(Calendar.SECOND);
	}

	/**
	 * Returns AM/PM from given Timestamp date.
	 *
	 * @param Timestamp
	 *            date
	 * @return int
	 */
	public static int getAmPm(Timestamp date) {
		GregorianCalendar c = new GregorianCalendar();
		if (date == null)
			return Calendar.AM;
		c.setTimeInMillis(date.getTime());
		return c.get(Calendar.AM_PM);
	}

	/**
	 * Returns Timestamp object for given year, month and day value.
	 *
	 * @param String
	 *            year
	 * @param String
	 *            month
	 * @param String
	 *            day
	 * @return Timestamp
	 */
	public static Timestamp getTimestamp(String year, String month, String day) {
		if (StringUtils.isEmpty(year) || StringUtils.isEmpty(month)
				|| StringUtils.isEmpty(day)) {
			return null;
		}
		if (StringUtils.isNumeric(year) && StringUtils.isNumeric(month)
				&& StringUtils.isNumeric(day)) {
			return getTimestamp(Integer.parseInt(year),
					Integer.parseInt(month), Integer.parseInt(day));
		}
		return null;
	}

	/**
	 * Return Timestamp value for given date as String.
	 *
	 * @param dateString
	 *            String
	 * @return Timestamp
	 */
	public static Timestamp getTimestamp(String dateString)
			throws Exception {
		Timestamp ts = null;
		try {
			ts = new Timestamp(DATETIME_FORMAT.parse(dateString).getTime());
		} catch (ParseException e) {
			throw new Exception(Constants.INVALID_DATE);
		}
		return ts;
	}

	/**
	 * Return Timestamp value for given date as String.
	 *
	 * @param dateString
	 *            String
	 * @return Timestamp
	 */
	public static Timestamp getTimestamp(String dateString,boolean lenient)
			throws Exception {
		Timestamp ts = null;
		try {
			if (dateString.trim().length() != YYYYMMDD_HHMMSS.length()){
				throw new Exception(Constants.INVALID_DATE);
			}
			DATETIME_FORMAT.setLenient(lenient);
			ts = new Timestamp(DATETIME_FORMAT.parse(dateString).getTime());
		} catch (ParseException e) {
			throw new Exception(Constants.INVALID_DATE);
		}
		return ts;
	}

	public static Timestamp getTimestampFromStringHypenFormat(String dateString)
	throws Exception {
	Timestamp ts = null;
	try {
		ts = new Timestamp(DATETIME_FORMAT_HYPEN.parse(dateString).getTime());
	} catch (ParseException e) {
		throw new Exception(Constants.INVALID_DATE);
	}
	return ts;
	}
	
	/**
	 * Return Timestamp value for given date as String.
	 *
	 * @param dateString
	 *            String
	 * @return Timestamp
	 */
	public static Timestamp getTimestampWithLenient(String dateString,boolean lenient)
			throws Exception {
		Timestamp ts = null;
		try {
			DATETIME_FORMAT.setLenient(lenient);
			ts = new Timestamp(DATETIME_FORMAT.parse(dateString).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception(Constants.INVALID_DATE);
		}
		return ts;
	}

	public static Timestamp convertStringToTimestamp(String dateTime,
			String format)  {
		Date dateObj;
		try {
			if (dateTime != null && dateTime.length() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				dateObj = sdf.parse(dateTime);
				return new Timestamp(dateObj.getTime());
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static Timestamp convertStringToTimestamp(String dateTime,
			String format,boolean lenient) throws Exception {
		Date dateObj;
		try {
			if (dateTime != null && dateTime.length() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				sdf.setLenient(lenient);
				dateObj = sdf.parse(dateTime);
				return new Timestamp(dateObj.getTime());
			}
		} catch (Exception e) {
			throw new Exception(Constants.INVALID_DATE);
		}
		return null;
	}

	public static Timestamp getToday() {
		return getOnlyDate(new Timestamp(System.currentTimeMillis()));
	}

	public static Timestamp getOnlyDate(Timestamp date) {
		if (date == null)
			return null;
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(date.getTime());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp convertStringToTimestamp(String dateTime,
			String formats[]) throws Exception {
		Date dateObj;
		try {
			if (dateTime != null && dateTime.length() > 0) {
				for (int i = 0; i < formats.length; i++) {
					SimpleDateFormat sdf = new SimpleDateFormat(formats[i]);
					try {
						dateObj = sdf.parse(dateTime);
					} catch (ParseException e) {
						continue;
					}
					return new Timestamp(dateObj.getTime());
				}

			}
		} catch (Throwable e) {
			throw new Exception(Constants.INVALID_DATE);
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static Timestamp getEODTime(String dateTime, String format)
			throws Exception {
		Timestamp timeObj = convertStringToTimestamp(dateTime, format);
		if (timeObj == null) {
			return null;
		}
		timeObj.setHours(23);
		timeObj.setMinutes(59);
		timeObj.setSeconds(59);
		return timeObj;
	}

	@SuppressWarnings("deprecation")
	public static boolean validateDate(String dateStr, String[] dateFormats,
			Map<Integer, Integer>[] defaultInstructions, boolean allowEmpty,
			int exactDateLength) throws IllegalArgumentException {
		if (allowEmpty
				&& !org.springframework.util.StringUtils.hasText(dateStr)) {
			return true;
		} else if (dateStr != null && exactDateLength >= 0
				&& dateStr.length() != exactDateLength) {
			return false;
		} else {
			long newValue = -1;
			for (int i = 0, n = dateFormats.length; i < n; i++) {
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormats[i]);
				sdf.setLenient(false);
				try {
					Date date = sdf.parse(dateStr);
					if (null != defaultInstructions
							&& defaultInstructions.length > i
							&& null != defaultInstructions[i]
							&& !defaultInstructions[i].isEmpty()) {
						Calendar cal = new GregorianCalendar();
						cal.setTime(date);
						Set<Integer> instructions = defaultInstructions[i]
								.keySet();
						for (Iterator<Integer> iterator = instructions.iterator(); iterator
								.hasNext();) {
							Integer instructionKey = iterator.next();
							Integer instructionVal = defaultInstructions[i]
									.get(instructionKey);

							if (instructionVal != null) {
								cal.set(instructionKey, instructionVal);
							}

						}

						date = cal.getTime();
					}
					newValue = date.getTime();
					if ((date.getYear() + 1900) > 9999
							|| (date.getYear() + 1900) < 1000) {
						return false;
					}
					break;
				} catch (ParseException ex) {
				}
			}

			if (-1 == newValue) {
				return false;
			}
		}
		return true;
	}

	public static String getDateAsMMDDYYYY(String dateStr) {
		dateStr = dateStr.replaceAll("[-\\s]+", "/");
		String[] dates = dateStr.split("/");
		if (dates.length == 2) {
			int year = getYear(getToday());
			dateStr += "/" + (year - 1);
		}
		return dateStr;
	}

	public static String getTodayDateAsString(String format)
			throws Exception {
		String dateStr = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			java.util.Date date = new java.util.Date();
			dateStr = dateFormat.format(date);
		} catch (Throwable e) {
			throw new Exception(Constants.INVALID_DATE);
		}
		return dateStr;
	}

	public static Timestamp getTimestampFromDate(Date date) {
		String dateString = DATETIME_FORMAT.format(date);
		Timestamp ts = null;
		try {
			ts = new Timestamp(DATETIME_FORMAT.parse(dateString).getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return ts;
	}

	public static Timestamp getTimestampFromDateHypenFormat(Date date) {
		String dateString = DATETIME_FORMAT_HYPEN.format(date);
		Timestamp ts = null;
		try {
			ts = new Timestamp(DATETIME_FORMAT_HYPEN.parse(dateString).getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return ts;
	}

	public static Timestamp moveDateToNoOfDays(int noOfDays) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, noOfDays);
		Timestamp movedTimeStamp = new Timestamp(cal.getTimeInMillis());
		return movedTimeStamp;
	}

	public static String getESTTime(Timestamp date) {
		int offset = -360;
		TimeZone tz = TimeZone.getTimeZone("US/Eastern");
		if (tz.inDaylightTime(new Date()))
		{
			offset -= 60;
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(toDate(date));
		cal.setTimeZone(tz);
		cal.add(Calendar.MINUTE, offset);
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_MMMddyyyyHHmmssz);
		formatter.setCalendar(cal);
		String timestamp = formatter.format(date.getTime());
		return timestamp;
	}

	public static Date toDate(Timestamp timestamp) {
	    long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
	    return new Date(milliseconds);
	}

	public static Timestamp getESTDate(Timestamp date) throws Exception {
		int offset = -360;
		TimeZone tz = TimeZone.getTimeZone("US/Eastern");
		if (tz.inDaylightTime(new Date()))
		{
			offset -= 60;
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(toDate(date));
		cal.setTimeZone(tz);
		cal.add(Calendar.MINUTE, offset);
		SimpleDateFormat formatter = new SimpleDateFormat(MMDDYYYY);
		formatter.setCalendar(cal);
		String dateStr = formatter.format(date.getTime());
		return convertStringToTimestamp(dateStr, MMDDYYYY);
	}

	public static String getDateAsHHMMSSTZ(Timestamp date) {
		int offset = -360;
		TimeZone tz = TimeZone.getTimeZone("US/Eastern");
		if (tz.inDaylightTime(new Date()))
		{
			offset -= 60;
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(toDate(date));
		cal.setTimeZone(tz);
		cal.add(Calendar.MINUTE, offset);
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a z");
		formatter.setCalendar(cal);
		String dateStr = formatter.format(date.getTime());
		return dateStr;
	}

	public static Timestamp moveDateToNoOfDays(Timestamp timeStamp , int noOfDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeStamp.getTime());
		cal.add(Calendar.DATE, noOfDays);
		Timestamp movedTimeStamp = new Timestamp(cal.getTimeInMillis());
		return movedTimeStamp;
	}

	public static void main(String s[]) throws Exception, ParseException {
	}
	
	public static boolean isEditable(String strStartTime, String strEndTime, String timeZone) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDD_HHMMSS_HYPEN_24HR);
		if(StringUtils.isBlank(timeZone)){
			timeZone = "US/Eastern";
		}
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		return isEditable(new Timestamp(df.parse(strStartTime).getTime()), new Timestamp(df.parse(strEndTime).getTime()), timeZone);
	}
	
	private static boolean isEditable(Timestamp startTime, Timestamp endTime, String timeZone){
		Calendar aptStartTimeCalendar = DateUtil.getTimeZoneCalendar(startTime, timeZone);
		Calendar aptEndTimeCalendar = DateUtil.getTimeZoneCalendar(endTime, timeZone);
		Calendar currentTimeZoneTime = DateUtil.getTimeZoneCalendar(new Timestamp(System.currentTimeMillis()), timeZone);
		aptStartTimeCalendar.add(Calendar.HOUR_OF_DAY, -2);
		return (aptEndTimeCalendar.after(currentTimeZoneTime) && aptStartTimeCalendar.after(currentTimeZoneTime));
	}
	
	public static boolean isDeletable(String strStartTime, String strEndTime, String timeZone) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDD_HHMMSS_HYPEN_24HR);
		if(StringUtils.isBlank(timeZone)){
			timeZone = "US/Eastern";
		}
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		return isDeletable(new Timestamp(df.parse(strStartTime).getTime()), new Timestamp(df.parse(strEndTime).getTime()), timeZone);
	}
	
	private static boolean isDeletable(Timestamp startTime, Timestamp endTime, String timeZone){
		Calendar aptStartTimeCalendar = DateUtil.getTimeZoneCalendar(startTime, timeZone);
		Calendar aptEndTimeCalendar = DateUtil.getTimeZoneCalendar(endTime, timeZone);
		Calendar currentTimeZoneTime = DateUtil.getTimeZoneCalendar(new Timestamp(System.currentTimeMillis()), timeZone);
		aptStartTimeCalendar.add(Calendar.HOUR_OF_DAY, -2);
		boolean isDeletable = false;
		 if(aptStartTimeCalendar.after(currentTimeZoneTime) && aptEndTimeCalendar.after(currentTimeZoneTime)){
			 isDeletable=true;
		 }else if((endTime.getTime() - startTime.getTime()) / (60 * 60 * 1000) > 24 && aptEndTimeCalendar.after(currentTimeZoneTime)){
			 isDeletable=true;
		 }
		return isDeletable;
	}

	@SuppressWarnings("unused")
	public static boolean checkValidDateWithTime(String dateString) {
		Timestamp dateAsTimeStamp;
		if (dateString.indexOf(":") == -1) {
			dateString = dateString.concat(" ").concat(
					TIME);
		}		
		try {
			dateAsTimeStamp = convertStringToTimestamp(dateString,
					DateUtil.MMDDYYYY_HHMMSS);
		} catch (Exception e) {
			return false;
		}
		int firstSlashIndex = dateString.indexOf("/");
		int lastSlashIndex = dateString.lastIndexOf("/");
		int firstColonIndex = dateString.indexOf(":");
		int lastColonIndex = dateString.lastIndexOf(":");
		int firstSpaceIndex = dateString.indexOf(" ");
		int lastSpaceIndex = dateString.lastIndexOf(" ");
		
		if(lastSpaceIndex > firstSpaceIndex){//space check  for single space between date and time value
			return false;
		}

		String monthVal = dateString.substring(0, firstSlashIndex);
		String dayVal = dateString.substring(firstSlashIndex + 1,
				lastSlashIndex);
		String yearVal = dateString.substring(lastSlashIndex + 1, firstSpaceIndex);

		String hourVal = dateString.substring(firstSpaceIndex + 1, firstColonIndex);
		String minVal = dateString.substring(firstColonIndex + 1,
				lastColonIndex);
		String secVal = dateString.substring(lastColonIndex + 1, dateString
				.length());
		
		if(yearVal.length() > 4){
			return false;
		}
		if(monthVal.length() > 2){
			return false;
		}
		if(dayVal.length() > 2){
			return false;
		}
		if(hourVal.length() > 2){
			return false;
		}
		if(minVal.length() > 2){
			return false;
		}
		if(secVal.length() > 2){//check for seconds with milli seconds/ more than 2 digits
			return false;
		}
		int day = Integer.parseInt(dayVal);
		int month = Integer.parseInt(monthVal);
		int year = Integer.parseInt(yearVal);

		int hour = Integer.parseInt(hourVal);
		int min = Integer.parseInt(minVal);
		int sec = Integer.parseInt(secVal);

		if (year < 1900) {
			return false;
		}

		if (month < 1 || month > 12) {
			return false;
		} else {
			int days = getActualMaximumDays(month, year);
			if (day < 1 || day > days) {
				return false;
			}
			if (hour > 23) {
				return false;
			} else {
				if (min > 59) {
					return false;
				}
				if (sec > 59) {
					return false;
				}
			}
		}

		return true;
	}
	
	@SuppressWarnings("unused")
	public static boolean checkValidDate(String dateString) {
		Timestamp dateAsTimeStamp;		
		try {
			dateAsTimeStamp = convertStringToTimestamp(dateString,
					DateUtil.MMDDYYYY);
		} catch (Exception e) {
			return false;
		}
		int firstSlashIndex = dateString.indexOf("/");
		int lastSlashIndex = dateString.lastIndexOf("/");		
		String monthVal = dateString.substring(0, firstSlashIndex);
		String dayVal = dateString.substring(firstSlashIndex + 1,
				lastSlashIndex);
		String yearVal = dateString.substring(lastSlashIndex + 1, dateString.length());
		if(yearVal.length() > 4){
			return false;
		}
		if(monthVal.length() > 2){
			return false;
		}
		if(dayVal.length() > 2){
			return false;
		}
		
		int day = Integer.parseInt(dayVal);
		int month = Integer.parseInt(monthVal);
		int year = Integer.parseInt(yearVal);		

		if (year < 1900) {
			return false;
		}

		if (month < 1 || month > 12) {
			return false;
		} else {
			int days = getActualMaximumDays(month, year);
			if (day < 1 || day > days) {
				return false;
			}			
		}
		return true;
	}
	
	public static int getActualMaximumDays(int month, int year) {
		int date = 1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}
	
	public static Timestamp addSeconds(Timestamp timestamp,int seconds) {
		if(timestamp == null) return null;
		GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(timestamp.getTime());
        c.add(Calendar.SECOND,seconds);
        return new Timestamp(c.getTimeInMillis());
	}
	
	public static Timestamp addYears(Timestamp timestamp,int year) {
		if(timestamp == null) return null;
		GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(timestamp.getTime());
        c.add(Calendar.YEAR,year);
        return new Timestamp(c.getTimeInMillis());
	}
	
    public static Timestamp getAdjustedHours(Timestamp original,int hoursOfDayAdjustment) {
    	if(original == null) return null;
        GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(original.getTime());
        c.add(Calendar.HOUR_OF_DAY, hoursOfDayAdjustment);
        return new Timestamp(c.getTimeInMillis());         	
    }    
    
    public static Timestamp getAdjustedMinutes(Timestamp original, int minutesAdjustment) {
    	if(original == null) return null;
        GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(original.getTime());
        c.add(Calendar.MINUTE, minutesAdjustment);
        return new Timestamp(c.getTimeInMillis());         	
    } 
    
    public static Timestamp getAdjustedDayes(Timestamp original,int daysAdjustment) {
    	if(original == null) return null;
        GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(original.getTime());
        c.add(Calendar.DAY_OF_YEAR, daysAdjustment);
        return new Timestamp(c.getTimeInMillis());
    }
    
	public static String getDayOfWeekAsString(Timestamp date){
		if(date == null) return "";
		GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(date.getTime());
        
        int dayOfWeek= c.get(Calendar.DAY_OF_WEEK);
        if(Calendar.SUNDAY == dayOfWeek){
        	return "SUNDAY";
        }
        if(Calendar.MONDAY == dayOfWeek){
        	return "MONDAY";
        }
        if(Calendar.TUESDAY == dayOfWeek){
        	return "TUESDAY";
        }
        if(Calendar.WEDNESDAY == dayOfWeek){
        	return "WEDNESDAY";
        }
        if(Calendar.THURSDAY == dayOfWeek){
        	return "THURSDAY";
        }
        if(Calendar.FRIDAY == dayOfWeek){
        	return "FRIDAY";
        }
        if(Calendar.SATURDAY == dayOfWeek){
        	return "SATURDAY";
        }
        return "";
	}
	
	@SuppressWarnings("deprecation")
	public static Timestamp convertStringToTimestamp(String dateString)
			throws Exception {

		long newValue = -1;
		for (int i = 0, n = dateFormatArray.length; i < n; i++) {
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormatArray[i]);
			formatter.setLenient(false);
			try {
				Date date = formatter.parse(dateString);
				newValue = date.getTime();
				if (date.getYear() > 9999 || date.getYear() < 0 )  {
					throw new Exception(Constants.INVALID_DATE);
				}
				break;
			} catch (ParseException ex) {
				
			}
		}

		if (-1 == newValue) {
			throw new Exception(Constants.INVALID_DATE);
		}
		return new Timestamp(newValue);

	}
	
	
	public static Timestamp convertFormattedStringToTimestamp(String dateString){

		long newValue = -1;
		for (int i = 0, n = dateFormatArray.length; i < n; i++) {
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormatArray[i]);
			formatter.setLenient(false);
			try {
				Date date = formatter.parse(dateString);
				newValue = date.getTime();
				break;
			} catch (ParseException ex) {
			}
		}
		return new Timestamp(newValue);
	}
	
	public static Map<String, String> getDateDifference(Timestamp fromTime,	Timestamp toTime) throws Exception {
		Map<String, String> dateTimeMap = new HashMap<String, String>();
		if (fromTime.before(toTime)) {
			double diff = toTime.getTime() - fromTime.getTime();
			double diffInHours = diff / (60 * 60 * 1000); // difference in hours																		
			double diffInSeconds = diff / 1000; // difference in seconds
			double diffInMinutes = diff / (60 * 1000); // difference in minutes
			double diffInDays = diff / (1000 * 60 * 60 * 24); // difference in days		
			dateTimeMap.put("diffInHours", String.valueOf(diffInHours));
			dateTimeMap.put("diffInMinutes", String.valueOf(diffInMinutes));
			dateTimeMap.put("diffInSeconds", String.valueOf(diffInSeconds));
			dateTimeMap.put("diffInDays", String.valueOf(diffInDays));
		}
		return dateTimeMap;
	}
	
	public static Timestamp getDateWithTime(Timestamp date,int hour,int min,int sec,int millisec) {
		if (date == null)
			return null;
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(date.getTime());
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, sec);
		c.set(Calendar.MILLISECOND, millisec);
		return new Timestamp(c.getTimeInMillis());
	}
	
	public static Timestamp getDateWithoutMilliSec(Timestamp date) {
		if (date == null)
			return null;
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(date.getTime());	
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}
	
	public static Timestamp getDateWithHourAndMin(Timestamp date) {
		if (date == null)
			return null;
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(date.getTime());	
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}
	
	public static Calendar getTimeZoneCalendar(Timestamp sourceTime, String sourceTimeZone) {
		if(StringUtils.isBlank(sourceTimeZone)){
			sourceTimeZone="US/Eastern";
		}
		Calendar timeZoneCalendar = new GregorianCalendar();
		timeZoneCalendar.setTimeZone(TimeZone.getTimeZone(sourceTimeZone));
		timeZoneCalendar.setTime(sourceTime);
		return timeZoneCalendar;
	}

	public static Calendar getTimeZoneCurrentTimeCalendar(String sourceTimeZone) {
		if(StringUtils.isBlank(sourceTimeZone)){
			sourceTimeZone="US/Eastern";
		}
		Calendar timeZoneCalendar = new GregorianCalendar();
		timeZoneCalendar.setTimeZone(TimeZone.getTimeZone(sourceTimeZone));
		timeZoneCalendar.setTime(new Timestamp(System.currentTimeMillis()));
		return timeZoneCalendar;
	}
	
	public static String convertTimeStringToUTC(String strSourceTime, String sourceTimeZone) throws ParseException {
		if(StringUtils.isBlank(sourceTimeZone)){
			sourceTimeZone="US/Eastern";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYYMMDD_HHMMSS_HYPEN_24HR);
		sdf.setTimeZone(TimeZone.getTimeZone(sourceTimeZone));
		Date date = sdf.parse(strSourceTime);
		
		//Convert to UTC
		Calendar calUTC = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		calUTC.setTime(date);
		
		StringBuffer tbuf = new StringBuffer();
		tbuf.append(calUTC.get(Calendar.YEAR));
		int imon = calUTC.get(Calendar.MONTH) + 1;
		if (imon < 1 || imon > DateUtil.MAX_MONTH_IN_YEAR) {
			imon=DateUtil.MAX_MONTH_IN_YEAR;
		}
		String mon = (imon<0||imon>9?"":"0")+imon;
		tbuf.append(mon);
		int iday = calUTC.get(Calendar.DAY_OF_MONTH);
		if (iday < 1 || iday > DateUtil.MAX_DAY_IN_MONTH) {
			iday=DateUtil.MAX_DAY_IN_MONTH;
		}
		String day = (iday<0||iday>9?"":"0")+iday;
		tbuf.append(day);
		tbuf.append("T");
		int ihr = calUTC.get(Calendar.HOUR_OF_DAY);
		String hr = (ihr<0||ihr>9?"":"0")+ihr;
		tbuf.append(hr);
		int imin = calUTC.get(Calendar.MINUTE);
		String min = (imin<0||imin>9?"":"0")+imin;
		tbuf.append(min);
		int isec = calUTC.get(Calendar.SECOND);
		String sec = (isec<0||isec>9?"":"0")+isec;
		tbuf.append(sec);
		tbuf.append("Z");
		return tbuf.toString();
	}
	
	 public static Timestamp getTimeZoneAdjustedHours(Timestamp original,String timeZone,int hoursOfDayAdjustment) {
		 	Calendar originalCalendar = getTimeZoneCalendar(original, timeZone);
		 	originalCalendar.add(Calendar.HOUR_OF_DAY, hoursOfDayAdjustment); 
		 	return new Timestamp(originalCalendar.getTimeInMillis());	            	
	 }    
	 
	 public static Timestamp getTimeZoneAdjustedMinutes(Timestamp original,String timeZone, int minutesAdjustment) {
	   	Calendar originalCalendar = getTimeZoneCalendar(original, timeZone);
	    originalCalendar.add(Calendar.MINUTE, minutesAdjustment);     
	    return new Timestamp(originalCalendar.getTimeInMillis());      	
	 }
	 
	 
	 public static boolean isEditable(String strStartTime, String timeZone,int hour) throws ParseException{
			SimpleDateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDD_HHMMSS_HYPEN_24HR);
			if(StringUtils.isBlank(timeZone)){
				timeZone = "US/Eastern";
			}
			df.setTimeZone(TimeZone.getTimeZone(timeZone));
			return isEditable(new Timestamp(df.parse(strStartTime).getTime()), timeZone,hour);
		}
		
		public static boolean isEditable(Timestamp startTime, String timeZone,int hour){
			Calendar startTimeCalendar = DateUtil.getTimeZoneCalendar(startTime, timeZone);		
			Calendar currentTimeZoneTime = DateUtil.getTimeZoneCalendar(new Timestamp(System.currentTimeMillis()), timeZone);
			startTimeCalendar.add(Calendar.HOUR_OF_DAY, hour);
			return (currentTimeZoneTime.after(startTimeCalendar));
		}
		
		
		public static boolean isEquals(String strStartTime, String timeZone,int hour) throws ParseException{
			SimpleDateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDD_HHMMSS_HYPEN_24HR);
			if(StringUtils.isBlank(timeZone)){
				timeZone = "US/Eastern";
			}
			df.setTimeZone(TimeZone.getTimeZone(timeZone));
			return isEquals(new Timestamp(df.parse(strStartTime).getTime()), timeZone,hour);
		}
		
		public static boolean isEquals(Timestamp startTime, String timeZone,int hour){
			Calendar startTimeCalendar = DateUtil.getTimeZoneCalendar(startTime, timeZone);		
			Calendar currentTimeZoneTime = DateUtil.getTimeZoneCalendar(new Timestamp(System.currentTimeMillis()), timeZone);
			startTimeCalendar.add(Calendar.HOUR_OF_DAY, hour);
			return (currentTimeZoneTime.equals(startTimeCalendar));
		}
		
		public static boolean isInBetween(String strStartTime, String strEndTime, String timeZone) throws ParseException{
			SimpleDateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDD_HHMMSS_HYPEN_24HR);
			if(StringUtils.isBlank(timeZone)){
				timeZone = "US/Eastern";
			}
			df.setTimeZone(TimeZone.getTimeZone(timeZone));
			return isInBetween(new Timestamp(df.parse(strStartTime).getTime()), new Timestamp(df.parse(strEndTime).getTime()), timeZone);
		}
		
		public static boolean isInBetween(Timestamp startTime, Timestamp endTime, String timeZone){
			Calendar startTimeCalendar = DateUtil.getTimeZoneCalendar(startTime, timeZone);
			Calendar endTimeCalendar = DateUtil.getTimeZoneCalendar(endTime, timeZone);
			Calendar currentTimeZoneTime = DateUtil.getTimeZoneCalendar(new Timestamp(System.currentTimeMillis()), timeZone);		
			return (startTimeCalendar.before(currentTimeZoneTime) && endTimeCalendar.after(currentTimeZoneTime));
		}
		
		public static boolean isAferCurrentTime(String strStartTime, String timeZone,int hour) throws ParseException{
			SimpleDateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDD_HHMMSS_HYPEN_24HR);
			if(StringUtils.isBlank(timeZone)){
				timeZone = "US/Eastern";
			}
			df.setTimeZone(TimeZone.getTimeZone(timeZone));
			return isAferCurrentTime(new Timestamp(df.parse(strStartTime).getTime()), timeZone,hour);
		}
		
		public static boolean isAferCurrentTime(Timestamp startTime, String timeZone,int hour){
			Calendar startTimeCalendar = DateUtil.getTimeZoneCalendar(startTime, timeZone);		
			Calendar currentTimeZoneTime = DateUtil.getTimeZoneCalendar(new Timestamp(System.currentTimeMillis()), timeZone);
			startTimeCalendar.add(Calendar.HOUR_OF_DAY, hour);
			return (startTimeCalendar.after(currentTimeZoneTime));
		}
		
		public static boolean isInBetweenForHour(String strStartTime, String strEndTime, String timeZone,int hour) throws ParseException{
			SimpleDateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDD_HHMMSS_HYPEN_24HR);
			if(StringUtils.isBlank(timeZone)){
				timeZone = "US/Eastern";
			}
			df.setTimeZone(TimeZone.getTimeZone(timeZone));
			return isInBetweenForHour(new Timestamp(df.parse(strStartTime).getTime()), new Timestamp(df.parse(strEndTime).getTime()), timeZone,hour);
		}
		
		/*
		 * This method converts based on JVM installed Time zone. Instead use isEditable(String, String,String)
		 * */
		private static boolean isInBetweenForHour(Timestamp startTime, Timestamp endTime, String timeZone,int hour){
			Calendar startTimeCalendar = DateUtil.getTimeZoneCalendar(startTime, timeZone);
			Calendar endTimeCalendar = DateUtil.getTimeZoneCalendar(endTime, timeZone);
			Calendar currentTimeZoneTime = DateUtil.getTimeZoneCalendar(new Timestamp(System.currentTimeMillis()), timeZone);
			startTimeCalendar.add(Calendar.HOUR_OF_DAY, hour);			
			return (startTimeCalendar.before(currentTimeZoneTime) && endTimeCalendar.after(currentTimeZoneTime));
		}
		
		public static Timestamp convertStringToTimestampTimeZone(String strStartTime,String timeZone) throws ParseException{
			SimpleDateFormat df = new SimpleDateFormat(DateUtil.YYYYMMDD_HHMMSS_HYPEN_24HR);
			if(StringUtils.isBlank(timeZone)){
				timeZone = "US/Eastern";
			}
			df.setTimeZone(TimeZone.getTimeZone(timeZone));
			return convertStringToTimestamp(new Timestamp(df.parse(strStartTime).getTime()), timeZone);
		}
		
		private static Timestamp convertStringToTimestamp(Timestamp startTime,String timeZone){
			Calendar aptTime = DateUtil.getTimeZoneCalendar(startTime, timeZone);
			return new Timestamp(aptTime.getTimeInMillis());
		}
		
		
		public static Timestamp getDateWithTimestamp(Timestamp sourceDate,Timestamp targetDate) {
	        Calendar sourceDateCalendar = new GregorianCalendar();
	        sourceDateCalendar.setTime(sourceDate);
			Calendar targetDateCalendar = new GregorianCalendar();
			targetDateCalendar.setTime(targetDate);
			targetDateCalendar.set(Calendar.HOUR_OF_DAY, sourceDateCalendar.get(Calendar.HOUR_OF_DAY));
			targetDateCalendar.set(Calendar.MINUTE, sourceDateCalendar.get(Calendar.MINUTE));
			targetDateCalendar.set(Calendar.SECOND, sourceDateCalendar.get(Calendar.SECOND));			
	        return new Timestamp(targetDateCalendar.getTimeInMillis());
		}
		
}
