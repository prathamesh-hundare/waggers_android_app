package com.example.adminpanel;

public class bookings {
    String name;
    String time;
    String num;
    String date;

    public bookings(String name, String time, String num, String date) {
        this.name = name;
        this.time = time;
        this.num = num;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getNum() {
        return num;
    }

    public String getDate() {
        return date;
    }
}
