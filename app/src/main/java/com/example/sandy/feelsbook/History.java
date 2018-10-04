package com.example.sandy.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class History extends AppCompatActivity {
    ListView historylist;
    Button delete;
    SaveLoad saveload = new SaveLoad(this);
    Button buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        historylist = (ListView) findViewById(R.id.historyListview);
        delete = (Button) findViewById(R.id.deleteHistoryButton);

//        Moves to the new activity called activity_history_delete.
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History.this,HistoryDelete.class);
                History.this.startActivity(intent);
            }
        });

        buttonHome= (Button) findViewById(R.id.homeButton);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();

        ListEmotions emotionlist = ListEmotions.getInstance();
        ArrayList<Emotion> list = emotionlist.getList();
        emotionlist.arraySort();

//        Creates an adapter and sets it.
        HistoryListAdapter adapter = new HistoryListAdapter(History.this,R.layout.adapter_view,list);
        historylist.setAdapter(adapter);

//        Upon clicking on a record, that object is passed through an intent to the HistoryEdit class.
        historylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Emotion object = (Emotion) (historylist.getItemAtPosition(position));

                Intent intent = new Intent(History.this,HistoryEdit.class);
                intent.putExtra("emotionObject",object);
                History.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        saveload.saveFile();
    }
}
