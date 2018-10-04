package com.example.sandy.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class OptionalComments extends AppCompatActivity {

    protected EditText input;
    protected String userInput;
    protected Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        input = (EditText) findViewById(R.id.commentField);
        submitButton = (Button) findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Emotion object = (Emotion) intent.getSerializableExtra("emotionObject");
                userInput = input.getText().toString();
                addComment(userInput,object);
                saveload.saveFile();
                finish();

            }
        });
    }

//    Finds the emotion object and sets the comment of that object.
    protected void addComment(String text,Emotion object){
        ListEmotions emotionlist = ListEmotions.getInstance();
        ArrayList<Emotion> list = emotionlist.getList();


//    Looks through all emotions in the list and compares the date. If the dates are equal, the user input is set as the comment for that object
        for (int x= 0; x<list.size();x++){
            Emotion item = list.get(x);
            if (item.getDate().equals(object.getDate())){
                item.setComment(userInput);
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
