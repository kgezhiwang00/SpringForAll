package com.spring.push.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Desc:
 * @Author: sunjiang
 * @Date: Created in 11:11 2018/6/4
 * @copyright Navi WeCloud
 */
public class DateUtil {

    private static final String formatStr = "yyyy-MM-dd HH:mm:ss";
    private static final String formatStrByDay = "yyyy-MM-dd";
    private static final String getFormatStrByHours = "HH:00:00";
    private static final String getFormatStrByHHMMSS = "HH:mm:ss";

    /**
     *
     * @param date
     * @return 格式为：yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date){
        DateFormat format2 = new SimpleDateFormat(formatStr);
        String executeTime = format2.format(date);
        return executeTime;
    }

    public static String formatByDay(Date date){
        DateFormat format2 = new SimpleDateFormat(formatStrByDay);
        String executeTime = format2.format(date);
        return executeTime;
    }

    public static String getGetFormatStrByHours(Date date){

        DateFormat format2 = new SimpleDateFormat(getFormatStrByHours);
        String executeTime = format2.format(date);
        return executeTime;
    }

    public static String getGetFormatStrByHHMMSS(Date date){

        DateFormat format2 = new SimpleDateFormat(getFormatStrByHHMMSS);
        String executeTime = format2.format(date);
        return executeTime;
    }

//    public static void main(String[] args){
//
//        System.out.println(getGetFormatStrByHours(new Date()));
//
//    }

    public static String formatCurrent(){
        return format(new Date());
    }

    public static String formatCurrentByDay(){
        return formatByDay(new Date());
    }

    public static String formatCurrentByDay(Date date){
        return formatByDay(date);
    }

    public static Date parse(String sTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = format.parse(sTime);
        return date;
    }


    /**
     * 获得指定日期的前一天
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static Date getSpecifiedDayBefore(Date specifiedDay){
        Calendar c = Calendar.getInstance();

        c.setTime(specifiedDay);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE, day-1);

        return c.getTime();
        /*String dayBefore=new SimpleDateFormat(formatStr).format(c.getTime());
        return dayBefore;*/
    }
    /**
     * 获得指定日期的后一天
     * @param specifiedDay
     * @return
     */
    public static Date getSpecifiedDayAfter(Date specifiedDay){
        return getSpecifiedDayAfterN(specifiedDay, 1);
        /*if(specifiedDay == null){
            return null;
        }
        Calendar c = Calendar.getInstance();

        c.setTime(specifiedDay);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE, day+1);

        return c.getTime();*/
    }
    public static Date getSpecifiedDayAfterN(Date specifiedDay, int n){
        if(specifiedDay == null){
            return null;
        }
        Calendar c = Calendar.getInstance();

        c.setTime(specifiedDay);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + n);

        return c.getTime();
    }

    public static Date getSpecifiedDayBeforeN(Date specifiedDay, int n){
        if(specifiedDay == null){
            return null;
        }
        Calendar c = Calendar.getInstance();

        c.setTime(specifiedDay);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - n);

        return c.getTime();
    }

    /**
     * 获取指定时间下一个小时的时间
     * @param specifiedDay
     * @return
     */
    public static Date getSpecifiedDayNextHourTime(Date specifiedDay){
        if(specifiedDay == null){
            return null;
        }
        Calendar c = Calendar.getInstance();

        c.setTime(specifiedDay);
        int hour=c.get(Calendar.HOUR_OF_DAY);
        c.set(Calendar.HOUR_OF_DAY, hour+1);

        return c.getTime();
    }



    /**
     * 获取指定时间中精确到小时的时间：2015-02-15 18:34:23 =》2015-02-15 18:00:00
     * @param date
     * @return
     */
    public static Date getTimeAccurateToHour (Date date){
        if(date == null){
            return null;
        }
        //String time = DateUtil.format(new Date()).substring(0,13) + ":00:00";
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        //c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getTimeAccurateToDay (Date date){
        if(date == null){
            return null;
        }
        //String time = DateUtil.format(new Date()).substring(0,13) + ":00:00";
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        //c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getTimeOfDayLastMinute (Date date){
        if(date == null){
            return null;
        }
        //String time = DateUtil.format(new Date()).substring(0,13) + ":00:00";
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        //c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定时间中精确到小时的时间：2015-02-15 18:34:23 =》2015-02-15 18:00:00
     * @param date
     * @return
     */
    public static String getTimeStrAccurateToHour(Date date){
        if(date == null){
            return null;
        }

        return getTimeStrAccurateToHour(format(date));
    }

    public static String getTimeStrAccurateToHour(String date){
        if(date == null){
            return null;
        }
        String time = date.substring(0,13) + ":00:00";
        return time;
    }

    /**
     * 获取指定时间中精确到天的时间：2015-02-15 18:34:23 =》2015-02-15 00:00:00
     * @param date
     * @return
     */
    public static String getTimeStrAccurateToDay (Date date){
        if(date == null){
            return null;
        }
        return getTimeStrAccurateToDay(format(date));

        /*String time = format(date).substring(0,10) + " 00:00:00";
        return time;*/
    }

    public static String getTimeStrAccurateToDay (String date){
        if(date == null){
            return null;
        }
        String time = date.substring(0,10) + " 00:00:00";
        return time;
    }

    /**
     * 以小时为单位，获取时间段数
     * @param startTime
     * @param endTime
     */
    public static int calculateTimeSegmentsByHour(Date startTime, Date endTime){
        if(startTime == null || endTime == null){
            throw new IllegalArgumentException("参数不可为空");
        }
        long segments = (endTime.getTime() - startTime.getTime()) / 3600000 + 1;
        return (int)segments;
    }

    /**
     * 以天为单位，获取时间段数
     * @param startTime
     * @param endTime
     */
    public static int calculateTimeSegmentsByDay(Date startTime, Date endTime){
        if(startTime == null || endTime == null){
            throw new IllegalArgumentException("参数不可为空");
        }
        long segments = (endTime.getTime() - startTime.getTime()) / (3600000 * 24) + 1;
        return (int)segments;
    }
}
