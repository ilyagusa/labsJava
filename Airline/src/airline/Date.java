/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline;

/**
 *
 * @author НИколай Соболев
 */
public class Date implements Comparable<Date> {

    private int hour;
    private int minute;
    private int year;
    private int month;
    private int day;

    public Date(int hour, int minute, int day, int month, int year) throws WrongNumException {
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.month = month;
        this.year = year;
        if (hour < 0 || hour > 24 || minute < 0 || minute > 60 || day < 0 || day > 31 || year < 0 || month < 0 || month > 12) {
            throw new WrongNumException();
        }
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public boolean equals(Object obj) {
        boolean a = true;
        boolean b = true;
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Date)) {
            return false;
        }

        Date date = (Date) obj;
        if (date == obj) {
            return a;
        } else {
            return b;
        }

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.hour;
        hash = 29 * hash + this.minute;
        hash = 29 * hash + this.year;
        hash = 29 * hash + this.month;
        hash = 29 * hash + this.day;
        return hash;
    }

    @Override
    public String toString() {
        return  "Время вылета:" + hour + ":" + minute + "\n" + "Дата вылета:" + day + "." + month + "." + year;
    }

    @Override
    public int compareTo(Date o) {
        int sum;
        sum = day + month + year + hour + minute - o.getDay() - o.month - o.getYear() - o.getHour() - o.getMinute();
        return sum;

    }

}
