package com.example.cynthiaty.mobilecart.model.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：常用方法
 */
public class Batch {
    public static final String TAG = "MobileCart";

    /**
     * 获取系统当前日期
     */
    public static String GetSystemCalendar() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());

        return formatter.format(curDate);
    }
}
