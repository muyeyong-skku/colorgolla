package com.example.muyeyong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class DetailActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageButton sharebtn = (ImageButton) findViewById(R.id.sharebtn);
        ImageButton savebtn = (ImageButton) findViewById(R.id.savebtn);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sharing_intent = new Intent(Intent.ACTION_SEND);
                Sharing_intent.setType("text/plain");
                Sharing_intent.setType("image/*");

                Sharing_intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(String.valueOf(R.drawable.i11)));
                String Test_Message = "Color Golla에서 공유한 이미지입니다.";

                Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                startActivity(Sharing);
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //download();
            }
        });
    }

    public void onButton2Clicked(View v) {
        Intent myIntent;
        String url = "https://bit.ly/3vdYC0a";
        myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(myIntent);

    }
}





