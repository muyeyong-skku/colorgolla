package com.example.muyeyong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ArrayList<color> al = new ArrayList<color>();

    GridView lv;

//    RVAdapter objectRvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        al.add(new color("#FF7B54","#FFB26B","#FFD56B","#939B62",R.drawable.i18,"#warm #hot #summer #africa #gradient \n #red #orange #yellow #green"));
        al.add(new color("#222831","#393E46","#00ADB5","#EEEEEE",R.drawable.i1,"#cold #dark #winter #night #gradient \n #black #gray #silver #blue"));
        al.add(new color("#C67ACE","#D8F8B7","#FF9A8C","#CE1F6A",R.drawable.i3,"#kitsch #bright #neon #teen #cute \n #pink #lime #orange #red"));
        al.add(new color("#F85F73","#FBE8D3","#928A97","#283C63",R.drawable.i20,"#classy #stylish #bright #dark #gradient \n #red #blue #pink #gray"));
        YourAdapter adapter = new YourAdapter(
                getApplicationContext(), // 현재화면의 제어권자
                R.layout.single_row,  // 리스트뷰의 한행의 레이아웃
                al);         // 데이터

        lv = (GridView)findViewById(R.id.listView2);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 상세정보 화면으로 이동하기(인텐트 날리기)
                // 1. 다음화면을 만든다
                // 2. AndroidManifest.xml 에 화면을 등록한다
                // 3. Intent 객체를 생성하여 날린다
                Intent intent = new Intent(
                        getApplicationContext(), // 현재화면의 제어권자
                        DetailActivity.class); // 다음넘어갈 화면

                // intent 객체에 데이터를 실어서 보내기
                // 리스트뷰 클릭시 인텐트 (Intent) 생성하고 position 값을 이용하여 인텐트로 넘길값들을 넘긴다
                intent.putExtra("first", al.get(position).first);
                intent.putExtra("second", al.get(position).second);
                intent.putExtra("tag", al.get(position).tag);
                intent.putExtra("third", al.get(position).third);
                intent.putExtra("img", al.get(position).img);
                intent.putExtra("fourth", al.get(position).fourth);




                startActivity(intent);
            }
        });
//
//        try
//        {
//                objectRecyclerView=findViewById(R.id.imageRV);
//                objectDatabaseHandler=new DatabaseHandler(this);
//        }
//        catch(Exception e)
//        {
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//        }


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


    class YourAdapter extends BaseAdapter { // 리스트 뷰의 아답타
        Context context;
        int layout;
        ArrayList<color> al;
        LayoutInflater inf;
public YourAdapter(Context context, int layout, ArrayList<color> al) {
        this.context = context;
        this.layout = layout;
        this.al = al;
        inf = (LayoutInflater)context.getSystemService
        (Context.LAYOUT_INFLATER_SERVICE);
        }
@Override
public int getCount() {
        return al.size();
        }
@Override
public Object getItem(int position) {
        return al.get(position);
        }
@Override
public long getItemId(int position) {
        return position;
        }
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
        convertView = inf.inflate(layout, null);
        }
        ImageView iv = (ImageView)convertView.findViewById(R.id.imageView2);


        color m = al.get(position);
        iv.setImageResource(m.img);


        return convertView;
        }
        }









