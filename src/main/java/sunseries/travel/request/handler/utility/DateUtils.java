package sunseries.travel.request.handler.utility;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtils {

    public static String convertISODateToUTC(Date datetime) {
        DateTime dt = new DateTime(datetime, DateTimeZone.UTC);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String dateAsISO = fmt.print(dt);
        return dateAsISO;
    }

    public static String currentISODateWithUTC() {
        DateTime dt = new DateTime(DateTimeZone.UTC);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String dateAsISO = fmt.print(dt);
        return dateAsISO;
    }

    /*
        Using for testing house keeping
     */
    public static String hundredDaysAgoISODate() {
        DateTime dt = new DateTime(DateTimeZone.UTC);
        DateTime weekAgo = dt.minusDays(100);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String dateAsISO = fmt.print(weekAgo);
        return dateAsISO;

    }

    public static Date currentDateTimeWithUTC() {
        DateTime currentDateTimeWithUTC = new DateTime(DateTimeZone.UTC);
        return currentDateTimeWithUTC.toDate();
    }

}
