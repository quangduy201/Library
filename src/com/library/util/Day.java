package com.library.util;

import java.util.Scanner;

public class Day {
    private int date, month, year;

    public Day() {
        this.date = 0;
        this.month = 0;
        this.year = 0;
    }

    public Day(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }

    public static int numOfDays(int month, int year) {
        return switch (month) {
            case 2 -> isLeapYear(year) ? 29 : 28;
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            default -> 0;
        };
    }

    public static boolean isValidDay(int date, int month, int year) {
        return  year > 1930 && year <= 2030 &&
                month > 0 && month <= 12 &&
                date > 0 && date <= numOfDays(month, year);
    }

    public static boolean isValidDay(Day day) {
        return  day.year > 1930 && day.year <= 2030 &&
                day.month > 0 && day.month <= 12 &&
                day.date > 0 && day.date <= numOfDays(day.month, day.year);
    }

    public static int calculateDays(Day day1, Day day2) {
        int days = 0;
        Day temp1, temp2;
        /*
         *      temp1 là ngày nhỏ hơn temp2 (ngày nhỏ hơn sẽ gán vào temp1)
         * nếu day1.year < day2.year thì gán day1 vào temp1 và ngược lại
         * nếu day1.year = day2.year thì xét tháng:
         * nếu day1.month < day2.month thì gán day1 vào temp1 và ngược lại
         * nếu day1.month = day2.month thì xét ngày:
         * nếu day1.date = day2.date thì gán day1 vào temp1 và ngược lại
         */
        if (day1.year < day2.year ||
            day1.year == day2.year && day1.month < day2.month ||
            day1.year == day2.year && day1.month == day2.month && day1.date < day2.date) {
            temp1 = new Day(day1.date, day1.month, day1.year);
            temp2 = new Day(day2.date, day2.month, day2.year);
        } else {
            temp1 = new Day(day2.date, day2.month, day2.year);
            temp2 = new Day(day1.date, day1.month, day1.year);
        }
        while (temp1.year <= temp2.year) {
            if (temp1.year == temp2.year && temp1.month == temp2.month) {
                days += temp2.date - temp1.date;
                break;
            } else {
                days += numOfDays(temp1.month, temp2.year) - temp1.date;
                temp1.month++;
                temp1.date = 0;
                if (temp1.month > 12) {
                    temp1.year++;
                    temp1.month = 1;
                }
            }
        }
        return days;
    }

    public static Day inputDay(String message) {
        Scanner sc = new Scanner(System.in);
        Day day = new Day();
        boolean hasError;
        do {
            hasError = false;
            System.out.print(message);
            String input = sc.nextLine();
            String[] splitInput = input.split("/");
            try {
                day.setDate(Integer.parseInt(splitInput[0]));
                day.setMonth(Integer.parseInt(splitInput[1]));
                day.setYear(Integer.parseInt(splitInput[2]));
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || !Day.isValidDay(day));
        return day;
    }

    public static Day parseDay(String str) throws Exception {
        String[] temp = str.split("/");
        int date, month, year;
        try {
            date = Integer.parseInt(temp[0]);
            month = Integer.parseInt(temp[1]);
            year = Integer.parseInt(temp[2]);
            if (!Day.isValidDay(date, month, year))
                throw new Exception();
        } catch (Exception e) {
            throw new Exception();
        }
        return new Day(date, month, year);
    }

    @Override
    public String toString() {
        // dd/mm/yyyy
        return  ((date < 10) ? "0" : "") + date + "/" +
                ((month < 10) ? "0" : "") + month + "/" +
                year;
    }
}
