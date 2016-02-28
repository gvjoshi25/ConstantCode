package assignment.trello.cause.code.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils
{
	private static final Logger	logger	= Logger.getLogger(DateUtils.class);
	private static final int	SECOND	= 1000;
	private static final int	MINUTE	= 60 * SECOND;
	private static final int	HOUR	= 60 * MINUTE;
	private static final int	DAY		= 24 * HOUR;

	private DateUtils()
	{

	}

	/**
	 * Convert string date into date object
	 * 
	 * @param stringDate
	 * @return Date
	 */
	public static Date getDate(String stringDate)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

		try
		{
			Date date = formatter.parse(stringDate);
			return date;
		}
		catch (Exception exception)
		{
			logger.error(exception.getMessage(), exception);
		}
		return null;
	}

	/**
	 * Format date difference in Days, Hours, Minutes, Seconds, Mili seconds
	 * 
	 * @param start date
	 * @param end date
	 * @return Difference between two dates.
	 */
	public static String getDifference(final Date date1, final Date date2)
	{
		long ms = date2.getTime() - date1.getTime();
		StringBuffer text = new StringBuffer("");
		if (ms > DAY)
		{
			text.append(ms / DAY).append(" days ");
			ms %= DAY;
		}
		if (ms > HOUR)
		{
			text.append(ms / HOUR).append(" hours ");
			ms %= HOUR;
		}
		if (ms > MINUTE)
		{
			text.append(ms / MINUTE).append(" minutes ");
			ms %= MINUTE;
		}
		if (ms > SECOND)
		{
			text.append(ms / SECOND).append(" seconds ");
			ms %= SECOND;
		}
		text.append(ms + " ms");
		return text.toString();
	}
}