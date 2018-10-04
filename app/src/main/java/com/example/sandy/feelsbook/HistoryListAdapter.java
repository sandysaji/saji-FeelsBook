package com.example.sandy.feelsbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

//CUSTOM LIST ADAPTER

class HistoryListAdapter extends ArrayAdapter<Emotion> {
    private Context context;
    int res;
    public HistoryListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String date = getItem(position).getDate();
        String comment = getItem(position).getComment();

        Emotion emotion = new Emotion(name, date);
        emotion.setComment(comment);

        LayoutInflater inflater =LayoutInflater.from(context);
        convertView = inflater.inflate(res,parent,false);

        TextView emotionView = (TextView) convertView.findViewById(R.id.emotionField);
        TextView dateView = (TextView) convertView.findViewById(R.id.dateField);
        TextView descripView = (TextView) convertView.findViewById(R.id.descripField);

//        Sets the textfields using the variables.
        emotionView.setText(name);
        dateView.setText(date);
        descripView.setText(comment);

        return convertView;
    }
}
