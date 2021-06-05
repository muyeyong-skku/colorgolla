package com.example.muyeyong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {
    ArrayList<color> al = new ArrayList<color>();
    private static final int MY_PERMISSION_STORAGE = 1111;
    GridView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        al.add(new color("#222831","#393E46","#00ADB5","#EEEEEE",R.drawable.i1,"e"));
        al.add(new color("a","b","c","d",R.drawable.i2,"e"));
        al.add(new color("a","b","c","d",R.drawable.i3,"e"));
        al.add(new color("a","b","c","d",R.drawable.i4,"e"));
        al.add(new color("a","b","c","d",R.drawable.i5,"e"));
        al.add(new color("a","b","c","d",R.drawable.i6,"e"));
        al.add(new color("a","b","c","d",R.drawable.i7,"e"));
        al.add(new color("a","b","c","d",R.drawable.i8,"e"));
        al.add(new color("a","b","c","d",R.drawable.i9,"e"));
        al.add(new color("a","b","c","d",R.drawable.i10,"e"));
        al.add(new color("#F1F3DE","#EB8F8F","#EC0101","#CD0A0A",R.drawable.i11,"e"));
        al.add(new color("a","b","c","d",R.drawable.i12,"e"));
        al.add(new color("a","b","c","d",R.drawable.i13,"e"));
        al.add(new color("a","b","c","d",R.drawable.i14,"e"));
        al.add(new color("a","b","c","d",R.drawable.i15,"e"));
        al.add(new color("a","b","c","d",R.drawable.i16,"e"));
        al.add(new color("a","b","c","d",R.drawable.i17,"e"));
        al.add(new color("a","b","c","d",R.drawable.i18,"e"));
        al.add(new color("a","b","c","d",R.drawable.i19,"e"));
        al.add(new color("a","b","c","d",R.drawable.i20,"e"));
        MyAdapter adapter = new MyAdapter(
                getApplicationContext(), // 현재화면의 제어권자
                R.layout.row,  // 리스트뷰의 한행의 레이아웃
                al);         // 데이터

        lv = (GridView)findViewById(R.id.listView1);
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

        checkPermission();
    }
    public void funcMoveDetail(View v){
        Intent it = new Intent(MainActivity.this, DetailActivity.class);
        startActivity(it);
    }
    public void funcMoveUser(View v){
        Intent it = new Intent(MainActivity.this, UserActivity.class);
        startActivity(it);
    }
    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 다시 보지 않기 버튼을 만드려면 이 부분에 바로 요청을 하도록 하면 됨 (아래 else{..} 부분 제거)
            // ActivityCompat.requestPermissions((Activity)mContext, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_CAMERA);

            // 처음 호출시엔 if()안의 부분은 false로 리턴 됨 -> else{..}의 요청으로 넘어감
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(this)
                        .setTitle("알림")
                        .setMessage("저장소 권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                        .setNeutralButton("설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_STORAGE:
                for (int i = 0; i < grantResults.length; i++) {
                    // grantResults[] : 허용된 권한은 0, 거부한 권한은 -1
                    if (grantResults[i] < 0) {
                        Toast.makeText(MainActivity.this, "해당 권한을 활성화 하셔야 합니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                // 허용했다면 이 부분에서..

                break;
        }
    }


}
class MyAdapter extends BaseAdapter { // 리스트 뷰의 아답타
    Context context;
    int layout;
    ArrayList<color> al;
    LayoutInflater inf;
    public MyAdapter(Context context, int layout, ArrayList<color> al) {
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
        ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);


        color m = al.get(position);
        iv.setImageResource(m.img);


        return convertView;
    }
}









