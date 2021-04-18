package com.example.muyeyong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
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
                                Toast.makeText(getApplication(),"나의 프로필입니다",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.m3:
                                Toast.makeText(getApplication(),"색상 상세보기 입니다",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), DetailActivity.class));
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
    }}






