package com.example.muyeyong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                            Toast.makeText(getApplication(),"교수님",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.m2:
                            Toast.makeText(getApplication(),"여긴 나중에",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.m3:
                            Toast.makeText(getApplication(),"구현하겠습니다",Toast.LENGTH_SHORT).show();
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






