package com.library.util;


public class Day {
    private int date, month, year;

    public Day(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public Day() {
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

    public static boolean isLeapYear(int year){
        if ( year%400 == 0 || (year%100 != 0 && year%4 == 0)){
            return true;
        }else {
            return false;
        }
    }

    public static int numOfDays(int month, int year){
        int date=0;
        switch (month) {
            case 2:
                if  (isLeapYear(year)) {
                    date = 29;
                } else {
                    date = 28;
                }
                break;
            case 1, 3, 5, 7, 8, 10, 12:
                date=31;
                break;
            case 4, 6, 9, 11:
                date = 30;
                break;
            default:
                break;
        }
        return date;
    }

    public static boolean isValidDay(int date, int month, int year){
        if (date == numOfDays(month, year)){
            return true;
        }else {
            return false;
        }
    }

    public static int calculateDays(Day day1, Day day2){
        int days=0, date1, date2, month1, month2, year1, year2;
        /*
        year1 month1 date1 là ngày lớn hơn year2 month2 date2
        * nếu day1.year > day2.year thì day1 gán vào year1 month1 date1 và ngược lại
        * nếu day1.year = day2.year thì xét tháng:
        * nếu day1.month > day2.month thì day1 gán vào year1 month1 date1 và ngược lại
        * nếu day1.month = day2.month thì xét ngày:
        * nếu day1.date > day2.date thì day1 gán vào year1 month1 date1 và ngược lại
        */
        if (day1.year > day2.year){
            year1 = day1.year;
            year2 = day2.year;
            month1 = day1.month;
            month2 = day2.month;
            date1 = day1.date;
            date2 = day2.date;
        }else {
            if (day1.year < day2.year){
                year1 = day2.year;
                year2 = day1.year;
                month1 = day2.month;
                month2 = day1.month;
                date1 = day2.date;
                date2 = day1.date;
            }else {
                if (day1.month > day2.month){
                    year1 = day1.year;
                    year2 = day2.year;
                    month1 = day1.month;
                    month2 = day2.month;
                    date1 = day1.date;
                    date2 = day2.date;
                }else {
                    if (day1.month < day2.month){
                        year1 = day2.year;
                        year2 = day1.year;
                        month1 = day2.month;
                        month2 = day1.month;
                        date1 = day2.date;
                        date2 = day1.date;
                    }else {
                        if (day1.date > day2.date){
                            year1 = day1.year;
                            year2 = day2.year;
                            month1 = day1.month;
                            month2 = day2.month;
                            date1 = day1.date;
                            date2 = day2.date;
                        }else {
                            year1 = day2.year;
                            year2 = day1.year;
                            month1 = day2.month;
                            month2 = day1.month;
                            date1 = day2.date;
                            date2 = day1.date;
                        }
                    }
                }
            }
        }
        while (year1 >= year2){
            if (year1 == year2 && month1 == month2){
                days += date1-date2;
                break;
            }else {
                days += (numOfDays(month2,year2) - date2);
                month2++;
                date2 = 0;
                if (month2 > 12){
                    year2++;
                    month2 = 1;
                }
            }
        }
        return days;
    }
}
