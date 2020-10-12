package com.mohamadamin.persianmaterialdatetimepicker;

import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.Calendar;
import java.util.Locale;

public class XPersianCalendar extends PersianCalendar {


    public XPersianCalendar() {
        super();
    }

    public XPersianCalendar(Long date) {
        super();
        setTimeInMillis(date);
    }

    public XPersianCalendar(int year, int month, int day) {
        super();
        setPersianDate(year, month, day);
    }

    public XPersianCalendar(int day, int month, int year, int hour, int min) {
        super();
        setPersianDate(year, month, day);
        set(HOUR, hour);
        set(MINUTE, min);
        set(SECOND, 0);
        set(MILLISECOND, 0);
    }

    public static XPersianCalendar getCalendar(String persianStringDate, String timeString) {
        if (persianStringDate == null || persianStringDate.isEmpty()) {
            return null;
        }

        String[] splited = persianStringDate.split("/");
        String[] timeSplit = timeString.split(":");

        if (splited.length != 3) {
            return null;
        }

        XPersianCalendar persianCalendar = new XPersianCalendar();
        persianCalendar.setPersianDate(Integer.valueOf(splited[0]), Integer.valueOf(splited[1]), Integer.valueOf(splited[2]));
        persianCalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeSplit[0]));
        persianCalendar.set(Calendar.MINUTE, Integer.valueOf(timeSplit[1]));
        persianCalendar.set(Calendar.SECOND, 0);
        persianCalendar.set(Calendar.MILLISECOND, 0);

        return persianCalendar;
    }

    public static XPersianCalendar getCalendar(String persianStringDate) {
        return getCalendar(persianStringDate, "00:00");
    }

    private static String getHourTimeString(XPersianCalendar persianCalendar) {
        return String.format(Locale.US, "%02d:%02d", persianCalendar.get(Calendar.HOUR_OF_DAY), persianCalendar.get(Calendar.MINUTE));
    }

    private static String getPersianShortFormat(XPersianCalendar persianCalendar) {
        return String.format(Locale.US, "%04d/%02d/%02d", persianCalendar.getPersianYear(), persianCalendar.getPersianMonth(), persianCalendar.getPersianDay());
    }

    private static String getGregorianFullFormat(XPersianCalendar sappPersianCalendar) {
        String FORMAT = "%04d-%02d-%02d %02d:%02d:%02d";
        return String.format(Locale.US,
                FORMAT,
                sappPersianCalendar.get(Calendar.YEAR),
                sappPersianCalendar.get(Calendar.MONTH) + 1,
                sappPersianCalendar.get(Calendar.DAY_OF_MONTH),
                sappPersianCalendar.get(Calendar.HOUR_OF_DAY),
                sappPersianCalendar.get(Calendar.MINUTE),
                sappPersianCalendar.get(Calendar.SECOND)
        );
    }

    public String getHourTimeString() {
        return getHourTimeString(this);
    }

    public String getGregorianFullFormat() {
        return getGregorianFullFormat(this);
    }

    public String getPersianShortFormat() {
        return getPersianShortFormat(this);
    }

    public void setDate(PersianCalendar persianCalendar) {
        setTimeInMillis(persianCalendar.getTimeInMillis());
    }

    public void clearTime() {
        set(Calendar.HOUR_OF_DAY, 0);
        set(Calendar.MINUTE, 0);
        set(Calendar.SECOND, 0);
        set(Calendar.MILLISECOND, 0);
    }
}
