package com.example.sandy.feelsbook;

import java.io.Serializable;

public class Emotion implements Serializable{
//    Creates the variables
    protected String name;
    protected String date;
    protected String comment;

//    The constructor method
    public Emotion(String emotionName, String emotionDate){
        this.name = emotionName;
        this.date = emotionDate;
    }

//    Returns the name
    public String getName(){
        return this.name;
    }

//    Returns the date
    public String getDate(){
        return this.date;
    }

//    Sets the commment
    public void setComment(String comment){
        this.comment = comment;
    }

//    Returns the comment
    public String getComment(){
        return this.comment;
    }

//     Sets the date
    public void setDate(String date) {
        this.date = date;
    }
}
