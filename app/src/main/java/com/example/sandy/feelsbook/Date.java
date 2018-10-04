package com.example.sandy.feelsbook;

import java.text.SimpleDateFormat;

public class  Date {
    protected String currentDate;

//Gets the current Date, sets in the required format and returns the date as a string
    public String getDate(){
        java.util.Date date = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = df.format(date);
        return currentDate;
    }
}
