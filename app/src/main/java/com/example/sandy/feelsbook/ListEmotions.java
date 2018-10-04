package com.example.sandy.feelsbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//This is a singleton class.

public class ListEmotions {
    private static final ListEmotions instance = new ListEmotions();

    private ArrayList<Emotion> listEmotion;

    public static ListEmotions getInstance() {
        return instance;
    }

//    Creates an arraylist that holds Emotion objects
    private ListEmotions() {
         listEmotion = new ArrayList<Emotion>();
    }

//    Adds emotion to the list
    public void addEmotion (Emotion item){
        listEmotion.add(item);
    }
//    returns the list
    public ArrayList<Emotion> getList(){
        return listEmotion;
    }

    public void arraySort(){
        Collections.sort(listEmotion, new Comparator<Emotion>() {
            @Override
            public int compare(Emotion o1, Emotion o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
    }

    public void setLoadList(ArrayList<Emotion> loadedArray){
        listEmotion = loadedArray;
    }
}

