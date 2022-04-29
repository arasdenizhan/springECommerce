package com.example.yeczane.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private DateUtil(){
        throw new UnsupportedOperationException();
    }

    public static String convertDateToString(Date date){
        return SIMPLE_DATE_FORMAT.format(date);
    }
}
