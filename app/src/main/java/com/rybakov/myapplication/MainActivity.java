package com.rybakov.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rybakov.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    EditText et;
    MyDb mdb;
    ListView lv;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.textView);
        lv = (ListView)findViewById(R.id.listView);
        updateTable();

    }
    public  void saveNote(View v){
        System.out.println("hui");
        mdb=new MyDb(this);
        sqLiteDatabase=mdb.getWritableDatabase();
        sqLiteDatabase.execSQL("insert into notes values (?);", new String[]{et.getText().toString()});
        updateTable();
    }

    public void deleteNotes(View v){
        mdb=new MyDb(this);
        sqLiteDatabase=mdb.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from notes");
        updateTable();
    }

    public void updateTable(){
        System.out.println("hui");
        mdb=new MyDb(this);
        sqLiteDatabase=mdb.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from notes", null);

        String[] notes = new String[cursor.getCount()];
        int x =0;
        cursor.moveToFirst();

        while (cursor.isAfterLast()==false){
            notes[x] = cursor.getString(0);
            x++;
            cursor.moveToNext();
        }

        CustomNote cv = new CustomNote(this, R.layout.my_row,notes);
        lv.setAdapter(cv);
    }



}