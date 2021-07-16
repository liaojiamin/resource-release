package com.ljm.resource.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author liaojiamin
 * @Date:Created in 14:22 2021/6/18
 */
public class LocalDateDemo {
    /**
     * java时间类型，新旧api对比
     */
    public static void getCurrentDate() {
        LocalDate today = LocalDate.now();
        System.out.println("today's local date: " + today);
        Date date = new Date();
        System.out.println("today's date is: " + date);
    }

    /**
     * LocalDate 获取年月日信息,与之前Calendar对比
     */
    public static void getDetailDate() {
        LocalTime todayTime = LocalTime.now();
        int second = todayTime.getSecond();
        int minute = todayTime.getMinute();
        int hour = todayTime.getHour();
        int nano = todayTime.getNano();
        System.out.println("秒: " + second + "分: " + minute + "时: " + hour + "纳秒: " + nano);
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        int day_year = today.getDayOfYear();
        System.out.println(year + ": " + month + ": " + day + ": " + day_year);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.getHour());
    }

    /**
     * 处理特定日志 & 时间对比
     */
    public static void handleSpecilDate() {
        LocalDate localDate = LocalDate.of(2021, 11, 18);
        System.out.println(localDate);
        LocalDate date = LocalDate.of(2021, 6, 18);
        System.out.println(localDate.equals(date));
    }


    /**
     * 检查生日这种周期性时间
     */
    public static void cycleDate() {
        LocalDate localDate = LocalDate.now();
        LocalDate dateofBirth = LocalDate.of(2021, 6, 18);
        MonthDay birthday = MonthDay.of(dateofBirth.getMonth(), dateofBirth.getDayOfMonth());
        MonthDay nowDate = MonthDay.from(localDate);
        MonthDay monthParse = MonthDay.parse("2021-06-18", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(birthday.equals(nowDate));
        System.out.println(monthParse);
    }

    /**
     * yearMonth处理固定日期
     */
    public static void checkCardExpiry() {
        YearMonth currenctYearMonth = YearMonth.now();
        System.out.println(currenctYearMonth + ": " + currenctYearMonth.lengthOfMonth());
        YearMonth credtCard = YearMonth.of(2021, Month.FEBRUARY);
        System.out.println(credtCard + ": " + credtCard.lengthOfMonth());
        DayOfWeek dayOfWeek = DayOfWeek.of(3);
        System.out.println(dayOfWeek);
    }

    /**
     * 时间做加法
     * LocalTime支持：
     * NANOS
     * MICROS
     * MILLIS
     * SECONDS
     * MINUTES
     * HOURS
     * HALF_DAYS
     */
    public static void plusHours() {
        LocalTime localTime = LocalTime.now();
        localTime.plusHours(5);
        System.out.println(localTime);
        LocalTime nextWeek = localTime.plus(10, ChronoUnit.HOURS);
        System.out.println(nextWeek);
        System.out.println(localTime);
        LocalTime halfDay = localTime.plus(2, ChronoUnit.HALF_DAYS);
        System.out.println(halfDay);
    }

    /**
     * 日期做加法
     */
    public static void plusDate() {
        LocalDate localDate = LocalDate.now();
        LocalDate tomorrow = localDate.plus(1, ChronoUnit.DAYS);
        System.out.println(tomorrow);
        LocalDate nextYear = localDate.plus(1, ChronoUnit.YEARS);
        LocalDate beforeYear = localDate.minus(1, ChronoUnit.YEARS);
        System.out.println("nextYear: " + nextYear);
        System.out.println("beforeYear: " + beforeYear);
    }

    /**
     * 日期+ 时间，
     */
    public static void plusTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime afterHour = localDateTime.plus(1, ChronoUnit.HOURS).plus(1, ChronoUnit.MINUTES).plus(1, ChronoUnit.YEARS);
        System.out.println(afterHour);
        LocalDateTime nowDate = LocalDateTime.of(2021, 6, 19, 17, 23, 57);
        System.out.println(nowDate);

    }

    /**
     * 时间比较api
     */
    public static void checkTimes() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime afterDate = LocalDateTime.of(2021, 6, 19, 17, 23, 57);
        System.out.println(localDateTime.isBefore(afterDate));
        System.out.println(localDateTime.isAfter(afterDate));
    }

    /**
     * 时区处理
     */
    public static void zoneTime() {
        ZoneId america = ZoneId.of("America/Chicago");
        LocalDateTime dateAndTime = LocalDateTime.now();
        ZonedDateTime dateTimeInChicago = ZonedDateTime.of(dateAndTime, america);
        System.out.println(dateTimeInChicago);
        ZonedDateTime dateTimeInShenZhen = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println(dateTimeInShenZhen);
    }

    /**
     * 包括时差信息的日期与时间
     */
    public static void zoneOffSet() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.FEBRUARY, 14, 19, 24);
        ZoneOffset offset = ZoneOffset.of("+07:30");
        OffsetDateTime dateTime = OffsetDateTime.of(localDateTime, offset);
        System.out.println(dateTime);
    }

    /**
     * 检查闰年
     */
    public static void isLeapYear() {
        LocalDate today = LocalDate.now();
        System.out.println(today.getYear() + "年是闰年：" + today.isLeapYear());
    }

    /**
     * 计算日期之前年，月，天数
     */
    public static void calcDate() {
        LocalDate localDateTime = LocalDate.of(2021, 10, 28);
        LocalDate beforeDateTime = LocalDate.now().plus(3, ChronoUnit.DAYS);
        Period period = Period.between(localDateTime, beforeDateTime);
        System.out.println("year: " + period.getYears() + " month:" + period.getMonths() + " day:" + period.getDays());
    }


    /**
     * 格式化日期
     */
    public static void formatDate() {
        String dayAfterTomorrow = "2021-06-18 15:53:06";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formatDateTime = LocalDateTime.parse(dayAfterTomorrow, df);
        System.out.println("timeStr: " + dayAfterTomorrow + " dateTime:" + formatDateTime);

        String dayDate = "20210618";
        DateTimeFormatter dateDayFor = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(dayDate, dateDayFor);
        System.out.println("timeStr: " + dayDate + " dateTime:" + localDate);

        String dayTime = "160451";
        DateTimeFormatter dayTimeFor = DateTimeFormatter.ofPattern("HHmmss");
        LocalTime localTime = LocalTime.parse(dayTime, dayTimeFor);
        System.out.println("timeStr: " + dayTime + " dateTime:" + localTime);
    }


    public static void main(String[] args) {
        getCurrentDate();
        getDetailDate();
        handleSpecilDate();
        cycleDate();
        checkCardExpiry();
        plusHours();
        plusDate();
        plusTime();
        checkTimes();
        zoneTime();
        isLeapYear();
        calcDate();
        zoneOffSet();
        formatDate();
    }
}
