package me.aq.plugin.ntirEco.utils;

import java.text.NumberFormat;
import java.text.ParseException;

public class TempBanUtils {

    public long getLong(String time) throws ParseException {

        if(time.contains("s")){
            long second = Long.parseLong(String.valueOf(NumberFormat.getInstance().parse(time).longValue()))*1000;
            return second;
        }

        if(time.contains("m")){
            long minute = Long.parseLong(String.valueOf(NumberFormat.getInstance().parse(time).longValue()))*60*1000;
            return minute;
        }

        if(time.contains("h")){
            long hour = Long.parseLong(String.valueOf(NumberFormat.getInstance().parse(time).longValue()))*60*60*1000;
            return hour;
        }

        if(time.contains("d")){
            long day = Long.parseLong(String.valueOf(NumberFormat.getInstance().parse(time).longValue()))*60*60*24*1000;
            return day;
        }

        if(time.contains("w")){
            long week = Long.parseLong(String.valueOf(NumberFormat.getInstance().parse(time).longValue()))*60*60*24*7*1000;
            return week;
        }

        if(time.contains("mon")){
            long month = Long.parseLong(String.valueOf(NumberFormat.getInstance().parse(time).longValue()))*60*60*24*7*4*1000;
            return month;
        }

        if(time.contains("y")){
            long year = Long.parseLong(String.valueOf(NumberFormat.getInstance().parse(time).longValue()))*60*60*24*7*4*12*1000;
            return year;
        }
        return 0;
    }

}
