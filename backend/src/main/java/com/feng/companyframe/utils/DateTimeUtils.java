package com.feng.companyframe.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {

    // 默认格式
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将 Date 转换为字符串，使用默认格式 yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    /**
     * 将 Date 转换为字符串，自定义格式
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        // 推荐使用 Java 8 的 LocalDateTime
        try {
            LocalDateTime localDateTime = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDateTime.format(formatter);
        } catch (Exception e) {
            // fallback：兼容老版 Java，避免异常
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }
    }

    // 可选：你也可以添加将字符串转回 Date 的方法
    // public static Date parse(String dateStr, String pattern) { ... }
}
