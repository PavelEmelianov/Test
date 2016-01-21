package com.emelianov.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateParser {

    private static final Logger logger = LoggerFactory
            .getLogger(DateParser.class);

    public static Date parseDate(String date) {
        Date parsedDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        formatter.setLenient(false);

        try {
            parsedDate = new Date(formatter.parse(date).getTime());
        } catch (ParseException e) {
            logger.error(
                    "Invalid birthday format, set birthday in \"year-month-day\" format, example: \"1990-01-01\" is valid",
                    e);
        }
        return parsedDate;
    }

}
