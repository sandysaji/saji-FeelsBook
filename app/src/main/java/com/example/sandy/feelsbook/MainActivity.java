package com.example.sandy.feelsbook;

//import android.content.Intent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    protected ListView list;
    protected String selectedItemText;


    protected TextView dateField;

    protected Button countButton;
    protected Button historyButton;

    protected ListEmotions emotionlist = ListEmotions.getInstance();
    protected ArrayList<Emotion> emolist = emotionlist.getList();
    protected ArrayList<Emotion> afterlist;
    SaveLoad saveload = new SaveLoad(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveload.loadFile();

    //Finds all the id of the widgets of the User interface

        list = (ListView) findViewById(R.id.listMenu);
        dateField = (TextView) findViewById(R.id.dateField);
        countButton = (Button) findViewById(R.id.countButton);
        historyButton = (Button) findViewById(R.id.historyButton);

    }


    @Override
    protected void onStart(){
        super.onStart();
        dateField.setText("");

        //        Creates an adapter for the List View and sets it

        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.emotions));
        list.setAdapter(menuAdapter);

//        Sets the on item click listener. Upon clicking an item(Emotion) of the list, the emotion is recorded with the date and
//                moves to a new activity where the user can enter an optional description.

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//            The date is being set using the getDate method defined in the MainActivity class.
                String currentDate = getDate();
                dateField.setText("TIME:  "+currentDate);
                selectedItemText = list.getItemAtPosition(position).toString();

//           The emotion chosen by the user is being added to the list
                Emotion emotion = recordEmotion(selectedItemText,currentDate);

//            Moves to the new activity where the user can enter optional comment

                Intent intent = new Intent(MainActivity.this,OptionalComments.class);
                intent.putExtra("emotionObject",emotion);
                MainActivity.this.startActivity(intent);

            }
        });

//        On Count button click, the Emotion count page is shown.

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent countIntent = new Intent(MainActivity.this,EmotionCount.class);
                MainActivity.this.startActivity(countIntent);
            }
        });

//        The history button click History page is shown.
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyIntent = new Intent(MainActivity.this,History.class);
                MainActivity.this.startActivity(historyIntent);
            }
        });
    }



    @Override
    protected void onStop(){
        super.onStop();
        saveload.saveFile();
    }


//    The Date class is being called where the current date is set and this method returns it as a string
    protected String getDate(){
        com.example.sandy.feelsbook.Date dte = new com.example.sandy.feelsbook.Date();
        String currentDate = dte.getDate();
        return currentDate;
    }

//    The emotion chosen by the user is recorded to the list.

    protected Emotion recordEmotion(String text,String date){
        Emotion emotion = new Emotion(text,date);
        ListEmotions emotionList = ListEmotions.getInstance();
        emotionList.addEmotion(emotion);
        saveload.saveFile();
        return emotion;
    }
}
