package com.example.muyeyong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class DetailActivity extends AppCompatActivity  {

    Button sharebtn;
    Button savebtn;

    ImageView imageView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        File sdcard = Environment.getExternalStorageDirectory();
        file = new File(sdcard, "colorimage9.png");

        imageView = findViewById(R.id.imageView9);

        ImageButton sharebtn = (ImageButton) findViewById(R.id.sharebtn);
        ImageButton savebtn = (ImageButton) findViewById(R.id.savebtn);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sharing_intent = new Intent(Intent.ACTION_SEND);
                Sharing_intent.setType("text/plain");

                String Test_Message = "공유할 Text";

                Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                startActivity(Sharing);
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
            }
        });
    }

    public void download(){
        file.
               String filename = "myfile";
                String string = "Hello world!";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(string.getBytes());
                    outputStream.close();

                    Toast.makeText(this, "this is internal storage save success.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();

                    Toast.makeText(this, "this is internal storage save fail.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }}






