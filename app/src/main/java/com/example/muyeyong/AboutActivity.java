package com.example.muyeyong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageButton btn = (ImageButton) findViewById(R.id.viewmore);
        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미

                getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.m1:
                                Toast.makeText(getApplication(),"홈 화면입니다",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                break;
                            case R.id.m2:
                                Toast.makeText(getApplication(),"회사 소개입니다",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                                break;
                            case R.id.m3:
                                Toast.makeText(getApplication(),"공식 SNS로 이동합니다",Toast.LENGTH_SHORT).show();
                                Intent myIntent;
                                String url = "https://www.instagram.com/colorgolla/";
                                myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(myIntent);
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });

                popup.show();//Popup Menu 보이기
            }
        });
    }

}