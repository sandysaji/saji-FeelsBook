package com.example.sandy.feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryDelete extends AppCompatActivity {

    ListView historylist;
    ArrayList<Emotion> list;
    HistoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_delete);

        historylist = (ListView) findViewById(R.id.historyListview);

        final ListEmotions emotionlist = ListEmotions.getInstance();
        list = emotionlist.getList();

//        Creates the adapter and set it
        adapter = new HistoryListAdapter(HistoryDelete.this,R.layout.adapter_view,list);
        historylist.setAdapter(adapter);

        historylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Emotion emotion = (Emotion) (historylist.getItemAtPosition(position));
                deleteItem(emotion,list);

                adapter.notifyDataSetChanged();
                saveload.saveFile();


            }
        });
    }

//    deletes the item chosen by the user.
    protected void deleteItem(Emotion emotion,ArrayList<Emotion> list){
//        Iterates through the arraylist and tries to match the object date. Once a match is found, the item is removed.
        for (int i=0; i<list.size();i++){
            Emotion object = list.get(i);
            if (object.getDate().equals(emotion.getDate())){
                list.remove(i);
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
