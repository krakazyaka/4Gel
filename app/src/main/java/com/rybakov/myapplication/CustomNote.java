package com.rybakov.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomNote extends ArrayAdapter<String> {
    Activity context;
    String[] notes;

    public CustomNote(Activity context, int resource, String[] notess) {
        super(context, R.layout.my_row, notess);

        this.context=context;
        notes=notess;

    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.my_row, null,true);
        TextView tvID = (TextView)v.findViewById(R.id.start_note);
        tvID.setText(notes[position]);


        return v;
    }
}
