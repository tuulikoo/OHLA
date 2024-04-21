package com.example;

public class TimeUtils {

    public static int timeToSec(String time) {
        String[] part = time.split(":");
        int hh = Integer.parseInt(part[0]);
        int mm = Integer.parseInt(part[1]);
        int ss = Integer.parseInt(part[2]);

        return 3600 * hh + 60 * mm + ss;
    }

    public static String secToTime(int a) {
        int hh, mm, ss;
    
        boolean isNegative = false;
        if (a < 0) {
            isNegative = true;
            a = -a; 
        }
    
        hh = a / 3600;
        a = a - (3600 * hh);
        mm = a / 60;
        ss = a - (60 * mm);
    
        StringBuilder res = new StringBuilder();
        if (isNegative) {
            res.append("-");
        }
        res.append(hh).append(":");
        if (mm < 10) {
            res.append("0");
        }
        res.append(mm).append(":");
        if (ss < 10) {
            res.append("0");
        }
        res.append(ss);
    
        return res.toString();
    }
}
