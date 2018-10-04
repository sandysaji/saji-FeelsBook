package com.example.sandy.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class EmotionCount extends AppCompatActivity {

    Spinner menuSpinner;
    ListEmotions emotionlist;
    ArrayList<Emotion> list;
    EditText countField;
    SaveLoad saveload = new SaveLoad(this);
    Button buttonHome;

    protected int fearCount=0;
    protected int sadCount=0;
    protected int angryCount=0;
    protected int joyCount=0;
    protected int surpriseCount=0;
    protected int loveCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_count);

//        Creates the adapter for the spinner and sets the adapter
        menuSpinner = (Spinner) findViewById(R.id.countspinner);
        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(EmotionCount.this,android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.emotions_select));
        menuSpinner.setAdapter(menuAdapter);

//     Evokes the countEmotion function which counts the number of times each emotion is recorded
        countEmotion();

        countField = (EditText) findViewById(R.id.countNumber);
        menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

//    When an item from the spinner list is clicked, the count for that emotion is displayed by evoking displayCount method.
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = menuSpinner.getSelectedItem().toString();
                displayCount(selectedItemText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        buttonHome= (Button) findViewById(R.id.homeButton);
//        buttonHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EmotionCount.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        saveload.saveFile();
    }

//    The function iterates over the arraylist and counts the number of times each emotion was recorded.
    private void countEmotion(){
        emotionlist = ListEmotions.getInstance();
        list = emotionlist.getList();

// The for loop iterates over each emotion in the arraylist and compares the emotion name using a switch statement.
        for (int i=0; i<list.size();i++){
            Emotion object = list.get(i);
            switch (object.getName()){
                case "FEAR":
                    fearCount++;
                    break;
                case "ANGRY":
                    angryCount++;
                    break;
                case "SAD":
                    sadCount++;
                    break;
                case "JOY":
                    joyCount++;
                    break;
                case "SURPRISE":
                    surpriseCount++;
                    break;
                case "LOVE":
                    loveCount++;
                    break;
            }

        }
    }

//    The function displays the count of each emotion.
    private void displayCount(String text){

//        Using a switch, Compares the emotion chosen by the user from the list and displays it on the textfield
        switch (text){
            case "FEAR":
                countField.setText(String.valueOf(fearCount));
                break;
            case "ANGRY":
                countField.setText(String.valueOf(angryCount));
                break;
            case "SAD":
                countField.setText(String.valueOf(sadCount));
                break;
            case "JOY":
                countField.setText(String.valueOf(joyCount));
                break;
            case "SURPRISE":
                countField.setText(String.valueOf(surpriseCount));
                break;
            case "LOVE":
                countField.setText(String.valueOf(loveCount));
                break;
        }
    }

    public void onClick(View view) {
//        Intent intent = new Intent(EmotionCount.this, MainActivity.class);
//        startActivity(intent);
        finish();

    }
}
