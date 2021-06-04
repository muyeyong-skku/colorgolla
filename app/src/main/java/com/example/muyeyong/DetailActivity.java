package com.example.muyeyong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

public class DetailActivity extends AppCompatActivity {
    private ImageView iv1;
    private Bitmap imageToStore;
    DatabaseHandler objectDatabaseHandler;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        try
        {
            //objectImageView=findViewById(R.id.imageView1);
            objectDatabaseHandler=new DatabaseHandler(this);
            //objectImageView.setImageBitmap(imageToStore);
            iv1.setImageBitmap(imageToStore);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        TextView tx1 = (TextView) findViewById(R.id.textView1);
        TextView tx2 = (TextView) findViewById(R.id.textView2);
        TextView tx3 = (TextView) findViewById(R.id.textView3);
        TextView tx4 = (TextView) findViewById(R.id.textView4);
        TextView tx5 = (TextView) findViewById(R.id.textView5);
        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        tx1.setText(intent.getStringExtra("first"));
        tx2.setText(intent.getStringExtra("second"));
        tx3.setText(intent.getStringExtra("third"));
        tx4.setText(intent.getStringExtra("fourth"));
        tx5.setText(intent.getStringExtra("tag"));
        iv1.setImageResource(intent.getIntExtra("img", 0));


        ImageButton sharebtn = (ImageButton) findViewById(R.id.sharebtn);
        ImageButton savebtn = (ImageButton) findViewById(R.id.savebtn);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sharing_intent = new Intent(Intent.ACTION_SEND);
                Sharing_intent.setType("text/plain");

                String Test_Message = "최예원님이 컬러골라에서 공유한 이미지입니다 \n \n https://url.kr/sofe4m";

                Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                startActivity(Sharing);
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent;
                String url = "https://blogattach.naver.com/0e9b12a1bbefea361afe9ca593720572d48f719f6e/20210525_19_blogfile/skkscan_1621924042723_h404tZ_png/i11.png";
                myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(myIntent);
            }
        });

//        try
//        {
//            objectImageView(findViewById(R.id.imageView1);
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
//        }
//    }
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
    public void storeImage (View view)
    {
        try
        {
            if(iv1.getDrawable()!=null&&imageToStore!=null)
            {
                objectDatabaseHandler.storeImage(new ModelClass(imageToStore));
            }
            else
            {
                Toast.makeText(this, "저장에 실패하였습니다", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "shit", Toast.LENGTH_SHORT).show();
        }
    }
}





