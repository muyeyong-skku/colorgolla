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
    private static String DATABASE_NAME = "TP.db";

    private static int DATABASE_VERSION = 1;
    private static String createTableQuery = "" +
            "CREATE TABLE imageInfo (\n" +
            "\tcolor_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\tfirst TEXT NOT NULL,\n" +
            "\tsecond TEXT NOT NULL,\n" +
            "\tthird TEXT NOT NULL,\n" +
            "\tfourth TEXT NOT NULL,\n" +
            "\timg INTEGER NOT NULL,\n" +
            "\tfourth TEXT NOT NULL\n" +
            ");";
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

    public void storeImage(color color) {

        try {
            SQLiteDatabase objectSqLiteDatabase = this.getWritableDatabase();

            ContentValues Values = new ContentValues();

            Values.put("first", color.first);
            Values.put("second", color.second);
            Values.put("third", color.third);
            Values.put("fourth", color.fourth);
            Values.put("img", color.img);
            Values.put("tag", color.tag);

            long checkIFQueryRuns = objectSqLiteDatabase.insert("imageInfo", null, Values);
            if (checkIFQueryRuns != -1) {
                Toast.makeText(context, "데이터가 추가됨", Toast.LENGTH_SHORT).show();
                objectSqLiteDatabase.close();
            } else {
                Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<color> getColors() {
        try
        {
            SQLiteDatabase objectSqLiteDatabase = this.getReadableDatabase();
            ArrayList<color> colorList = new ArrayList<>();

            Cursor objectCursor = objectSqLiteDatabase.rawQuery("select * from imageInfo", null);
            if (objectCursor.getCount() != 0) {
                while (objectCursor.moveToNext()) {
                    byte[] imageBytes = objectCursor.getBlob(0);

                    Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    objectModelClassList.add(new ModelClass(objectBitmap));
                }

                return objectModelClassList;
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