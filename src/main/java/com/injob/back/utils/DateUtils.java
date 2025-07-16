package com.injob.back.utils;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@NoArgsConstructor
public class DateUtils {

    public static String convertDateTimeToStringDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(formatter);
    }

    public static LocalDateTime convertStringDateToDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the input date string to LocalDate
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        return localDate.atStartOfDay();
    }
}
