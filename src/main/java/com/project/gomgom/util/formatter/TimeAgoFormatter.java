package com.project.gomgom.util.formatter;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeAgoFormatter {

    public static String format(LocalDateTime pastDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(pastDateTime, now);

        if (duration.toMinutes() < 1) {
            return "방금 전";
        } else if (duration.toHours() < 1) {
            long minutes = duration.toMinutes();
            return minutes + (minutes == 1 ? "분 전" : "분 전");
        } else if (duration.toDays() < 1) {
            long hours = duration.toHours();
            return hours + (hours == 1 ? "시간 전" : "시간 전");
        } else if (duration.toDays() < 30) {
            long days = duration.toDays();
            return days + (days == 1 ? "일 전" : "일 전");
        } else if (duration.toDays() < 365) {
            long months = duration.toDays() / 30;
            return months + (months == 1 ? "달 전" : "달 전");
        } else {
            long years = duration.toDays() / 365;
            return years + (years == 1 ? "년 전" : "년 전");
        }
    }

}
