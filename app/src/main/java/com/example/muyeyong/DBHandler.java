package com.example.muyeyong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.widget.Toast;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    Context context;
    private static String DATABASE_NAME = "TermProject.db";

    private static int DATABASE_VERSION = 1;
    private static String createTableQuery = "" +
            "CREATE TABLE colorInfo (" +
            "color_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "first TEXT NOT NULL," +
            "second TEXT NOT NULL," +
            "third TEXT NOT NULL," +
            "fourth TEXT NOT NULL," +
            "img INTEGER NOT NULL," +
            "tag TEXT NOT NULL" +
            ")";
    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(createTableQuery);
            Toast.makeText(context, "테이블이 성공적으로 데이터베이스에 저장", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int toggleLike(color color) {
        try
        {
            SQLiteDatabase objectSqLiteDatabase = this.getReadableDatabase();
            ArrayList<color> colorList = new ArrayList<>();

            Cursor objectCursor = objectSqLiteDatabase.rawQuery("select * from colorInfo where img=" + color.img, null);
            if (objectCursor.getCount() > 0) {
                return this.deleteColor(color);
            } else {
                return this.storeColor(color);
            }


        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    public int storeColor(color color) {

        try {
            SQLiteDatabase objectSqLiteDatabase = this.getWritableDatabase();

            ContentValues Values = new ContentValues();

            Values.put("first", color.first);
            Values.put("second", color.second);
            Values.put("third", color.third);
            Values.put("fourth", color.fourth);
            Values.put("img", color.img);
            Values.put("tag", color.tag);

            long checkIFQueryRuns = objectSqLiteDatabase.insert("colorInfo", null, Values);
            if (checkIFQueryRuns != -1) {
                Toast.makeText(context, "데이터가 추가됨", Toast.LENGTH_SHORT).show();
                objectSqLiteDatabase.close();

                return 1;
            } else {
                Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show();
                return -1;
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    public int deleteColor(color color) {

        try {
            SQLiteDatabase objectSqLiteDatabase = this.getWritableDatabase();

            String[] selectionArgs = { String.valueOf(color.img) };

            long checkIFQueryRuns = objectSqLiteDatabase.delete("colorInfo", "img LIKE ?", selectionArgs);
            if (checkIFQueryRuns > 0) {
                Toast.makeText(context, "좋아요가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                objectSqLiteDatabase.close();

                return 2;
            } else {
                Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show();
                return -1;
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    public ArrayList<color> getColors() {
        try
        {
            SQLiteDatabase objectSqLiteDatabase = this.getReadableDatabase();
            ArrayList<color> colorList = new ArrayList<>();

            Cursor objectCursor = objectSqLiteDatabase.rawQuery("select * from colorInfo", null);
            if (objectCursor.getCount() != 0) {
                while (objectCursor.moveToNext()) {
                    String first = objectCursor.getString(1);
                    String second = objectCursor.getString(2);
                    String third = objectCursor.getString(3);
                    String fourth = objectCursor.getString(4);
                    int img = objectCursor.getInt(5);
                    String tag = objectCursor.getString(6);

                    colorList.add(new color(first, second, third, fourth, img, tag));
                }

                return colorList;
            } else {
                Toast.makeText(context, "데이터베이스에 아무 데이터도 없습니다", Toast.LENGTH_SHORT).show();
                return null;
            }


        } catch (Exception e) {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            return null;
        }
    }


}
