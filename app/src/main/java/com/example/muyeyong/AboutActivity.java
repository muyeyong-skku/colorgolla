package com.example.muyeyong;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {
    private String DATABASE_NAME = "mydb.db";
    ArrayList<color> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        DBHandler dbhandler = new DBHandler(getApplicationContext());

        Log.i("test","test");

        al = dbhandler.getColors();

        Log.i("test1","test1");
    }
}