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

        al.add(new color("#FF7B54","#FFB26B","#FFD56B","#939B62",R.drawable.i18,"#warm #hot #summer #africa #gradient \n #red #orange #yellow #green"));
        al.add(new color("#222831","#393E46","#00ADB5","#EEEEEE",R.drawable.i1,"#cold #dark #winter #night #gradient \n #black #gray #silver #blue"));
        al.add(new color("#C67ACE","#D8F8B7","#FF9A8C","#CE1F6A",R.drawable.i3,"#kitsch #bright #neon #teen #cute \n #pink #lime #orange #red"));
        al.add(new color("#F85F73","#FBE8D3","#928A97","#283C63",R.drawable.i20,"#classy #stylish #bright #dark #gradient \n #red #blue #pink #gray"));
        al.add(new color("#845460","#EAD3CB","#BDC7C9","#2B4F60",R.drawable.i6,"#soft #profound #wood #dark #gradient \n #brown #gray #turquoise"));
        al.add(new color("#F8EDE3","#BDD2B6","#A2B29F","#798777",R.drawable.i5,"#soft #weak #light #nature #gradient \n #green #beige #mugwort"));
        al.add(new color("#F1F3DE","#EB8F8F","#EC0101","#CD0A0A",R.drawable.i11,"#warn #hot #bright #gradient \n #red #pink #wine #strawberry"));
        al.add(new color("#35477D","#6C5B7B","#C06C84","#F67280",R.drawable.i13,"#stylish #cool #complementary #gradeint \n #blue #red #navy #pink"));
        al.add(new color("#F0E3CA","#FF8303","#A35709","#1B1A17",R.drawable.i9,"#old #traditional #gradient #india \n #beige #orange #borwn #black"));
        al.add(new color("#F4F9F9","#CCF2F4","#A4EBF3","#AAAAAA",R.drawable.i8,"#bright #light #heaven #gardient \n #white #skyblue #blue #gray"));
        al.add(new color("#FFC7C7","#FFE2E2","#F6F6F6","#8785A2",R.drawable.i12,"#soft #light #milk #gradient \n #pink #purple #navy #white"));
        al.add(new color("#21094E","#511281","#4CA1A3","#A5E1AD",R.drawable.i19,"#cold #cool #dark #night #twilight \n #blue #purple #green #mint"));
        al.add(new color("#206A5D","#81B214","#FFCC29","#F58634",R.drawable.i10,"#nature #healthy #bright #leaf #flower \n #green #orange #yellow #fruits"));
        al.add(new color("#433D3C","#944E6C","#E9C496","#83A95C",R.drawable.i14,"#classy #profound #dark #gorgeous \n #black #violet #beige #green"));
        al.add(new color("#413C69","#4A47A3","#709FB0","#A7C5EB",R.drawable.i16,"#cold #ocean #deep #gradient #dawn \n #navy #blue #darkblue #skyblue"));
        al.add(new color("#F3F2da","#4E8D7C","#045762","#EA97AD",R.drawable.i15,"#bight #stlyish #happy #pointcolor \n #green #pink #jade #lightgreen"));
        al.add(new color("#FFC996","#FF8474","#9F5F80","#583D72",R.drawable.i17,"#warm #pleasure #festival #gradjent \n #apricot #pink #violet #purple"));
        al.add(new color("#542E71","#FB3640","#FDCA40","#A799B7",R.drawable.i2,"#bright #excited #pointcolor \n #purple #red #yellow #violet"));
        al.add(new color("#865858","#8E7F7F","#BBBBBB","#E2D5D5",R.drawable.i7,"#wood #tranquil #lgiht #gradient \n #brown #gray #beige #browngray"));
        al.add(new color("#F0F5F9","#C9D6DF","#52616B","#1E2022",R.drawable.i4,"#cold #gradient #simple #modern \n #black #gary #white #gay-scale"));
        MyAdapter adapter = new MyAdapter(
                getApplicationContext(), // ??????????????? ????????????
                R.layout.row,  // ??????????????? ????????? ????????????
                al);         // ?????????

        lv = (GridView)findViewById(R.id.listView1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ???????????? ???????????? ????????????(????????? ?????????)
                // 1. ??????????????? ?????????
                // 2. AndroidManifest.xml ??? ????????? ????????????
                // 3. Intent ????????? ???????????? ?????????
                Intent intent = new Intent(
                        getApplicationContext(), // ??????????????? ????????????
                        DetailActivity.class); // ??????????????? ??????

                // intent ????????? ???????????? ????????? ?????????
                // ???????????? ????????? ????????? (Intent) ???????????? position ?????? ???????????? ???????????? ??????????????? ?????????
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
            // ?????? ?????? ?????? ????????? ???????????? ??? ????????? ?????? ????????? ????????? ?????? ??? (?????? else{..} ?????? ??????)
            // ActivityCompat.requestPermissions((Activity)mContext, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_CAMERA);

            // ?????? ???????????? if()?????? ????????? false??? ?????? ??? -> else{..}??? ???????????? ?????????
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(this)
                        .setTitle("??????")
                        .setMessage("????????? ????????? ?????????????????????. ????????? ???????????? ???????????? ?????? ????????? ?????? ??????????????? ?????????.")
                        .setNeutralButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("??????", new DialogInterface.OnClickListener() {
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
                    // grantResults[] : ????????? ????????? 0, ????????? ????????? -1
                    if (grantResults[i] < 0) {
                        Toast.makeText(MainActivity.this, "?????? ????????? ????????? ????????? ?????????.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                // ??????????????? ??? ????????????..

                break;
        }
    }


}
class MyAdapter extends BaseAdapter { // ????????? ?????? ?????????
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









