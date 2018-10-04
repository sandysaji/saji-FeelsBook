package com.example.sandy.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryEdit extends AppCompatActivity {

    EditText input;
    protected String userInput;
    Button submitButton;
    EditText datefield;

    Intent intent;
    Emotion emotion;

    protected String objectDate;
    protected String editDate;

    ArrayList<Emotion> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_edit);
        input = (EditText) findViewById(R.id.commentField);
        submitButton = (Button) findViewById(R.id.buttonSubmit);
        datefield= (EditText) findViewById(R.id.dateFieldEdit);
        intent = getIntent();
        emotion = (Emotion) intent.getSerializableExtra("emotionObject");

        objectDate = emotion.getDate();
        datefield.setText(objectDate);
        input.setText(emotion.getComment());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userInput = input.getText().toString();
                editDate = datefield.getText().toString();

                editcomment(userInput,objectDate,editDate);
                saveload.saveFile();
                finish();
            }
        });

    }

    //    The comment variable of the emotion class is being edited with the user input.
    protected void editcomment(String text,String objectDate,String editdate){
        ListEmotions emotionlist = ListEmotions.getInstance();
        list = emotionlist.getList();

//        Iterates through the array and looks for the chosen object's date. Once a match is found, the comment is set to the userinput
//                and the date is updated.
        for (int i=0; i<list.size();i++){
            Emotion object = list.get(i);
            if (object.getDate().equals(objectDate)){
                object.setComment(text);
                object.setDate(editdate);
                emotionlist.arraySort();
            }
        }
    }

    SaveLoad saveload = new SaveLoad(this);
    @Override
    protected void onStop(){
        super.onStop();
        saveload.saveFile();
    }
}
