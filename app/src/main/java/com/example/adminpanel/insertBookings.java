package com.example.adminpanel;

public class insertBookings {
    String name;
    String num;
    String time;
    String date;

    public insertBookings(String name, String num, String time, String date) {
        this.name = name;
        this.num = num;
        this.time = time;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
