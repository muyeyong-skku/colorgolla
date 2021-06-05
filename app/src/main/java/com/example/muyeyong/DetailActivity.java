package com.example.muyeyong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class DetailActivity extends AppCompatActivity {
//    private ImageView iv1;
//    private Bitmap imageToStore;
    DBHandler dbHandler;

    ImageView imageView;
    Button button;

    color clr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbHandler = new DBHandler(getApplicationContext());

        TextView tx1 = (TextView) findViewById(R.id.textView1);
        TextView tx2 = (TextView) findViewById(R.id.textView2);
        TextView tx3 = (TextView) findViewById(R.id.textView3);
        TextView tx4 = (TextView) findViewById(R.id.textView4);
        TextView tx5 = (TextView) findViewById(R.id.textView5);
        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);

        View cr1 = (View) findViewById(R.id.circle);
        View cr2 = (View) findViewById(R.id.circle2);
        View cr3 = (View) findViewById(R.id.circle3);
        View cr4 = (View) findViewById(R.id.circle4);


        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        clr = new color(
                intent.getStringExtra("first"),
                intent.getStringExtra("second"),
                intent.getStringExtra("third"),
                intent.getStringExtra("fourth"),
                intent.getIntExtra("img", 0),
                intent.getStringExtra("tag")
        );
        tx1.setText(intent.getStringExtra("first"));
        tx2.setText(intent.getStringExtra("second"));
        tx3.setText(intent.getStringExtra("third"));
        tx4.setText(intent.getStringExtra("fourth"));
        tx5.setText(intent.getStringExtra("tag"));
        iv1.setImageResource(intent.getIntExtra("img", 0));
        cr1.setBackgroundColor(Color.parseColor(intent.getStringExtra("first")));
        cr2.setBackgroundColor(Color.parseColor(intent.getStringExtra("second")));
        cr3.setBackgroundColor(Color.parseColor(intent.getStringExtra("third")));
        cr4.setBackgroundColor(Color.parseColor(intent.getStringExtra("fourth")));

        Bitmap bitmap = ((BitmapDrawable)iv1.getDrawable()).getBitmap();

        ImageButton likebtn = (ImageButton) findViewById(R.id.likebtn);
        ImageButton sharebtn = (ImageButton) findViewById(R.id.sharebtn);
        ImageButton savebtn = (ImageButton) findViewById(R.id.savebtn);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sharing_intent = new Intent(Intent.ACTION_SEND);
                Sharing_intent.setType("text/plain");

                String Test_Message = "최예원님이 컬러골라에서 공유한 이미지입니다 \n \n https://www.instagram.com/colorgolla/";

                Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                startActivity(Sharing);
            }
        });
        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.toggleLike(clr);
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "any_picture_name");
                values.put(MediaStore.Images.Media.BUCKET_ID, "test");
                values.put(MediaStore.Images.Media.DESCRIPTION, "test Image taken");
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
                Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                OutputStream outstream;
                try {
                    outstream = getContentResolver().openOutputStream(uri);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outstream);
                    outstream.close();
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public void onButton2Clicked(View v) {
        Intent myIntent;
        String url = "https://bit.ly/3vdYC0a";
        myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(myIntent);

    }

    public void funcMoveUser(View v){
        Intent it = new Intent(DetailActivity.this, UserActivity.class);
        startActivity(it);
    }


//    public void storeImage (View view)
//    {
//        try
//        {
//            if(iv1.getDrawable()!=null&&imageToStore!=null)
//            {
//                objectDatabaseHandler.storeImage(new ModelClass(imageToStore));
//            }
//            else
//            {
//                Toast.makeText(this, "저장에 실패하였습니다", Toast.LENGTH_SHORT).show();
//            }
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(this, "shit", Toast.LENGTH_SHORT).show();
//        }
//    }
}





