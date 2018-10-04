package com.example.sandy.feelsbook;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SaveLoad {

    protected static final String FILENAME = "file.sav";
    private Context mContext;
    protected ListEmotions emotionlist = ListEmotions.getInstance();
    protected ArrayList<Emotion> emolist = emotionlist.getList();
    protected ArrayList<Emotion> afterlist;

    public SaveLoad(Context context){
        this.mContext = context;
    }

    protected void saveFile(){
        try {
            FileOutputStream fos = mContext.openFileOutput(FILENAME,0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(emolist, writer);
            Log.d("---SANDy---","THE FILE HAS BEEN WRITTEN");
            writer.flush();
            fos.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void loadFile(){
        try {
            FileInputStream fis = mContext.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            Type emotionType = new TypeToken<ArrayList<Emotion>>(){}.getType();
            afterlist=gson.fromJson(reader,emotionType);
            emotionlist.setLoadList(afterlist);
            Log.d("---SANDy---","THE FILE HAS BEEN Loaded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
