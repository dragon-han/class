package com.alvin.aclass;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import com.alvin.aclass.pojo.Student;


public class MyStudentDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myHdu.db";
    private static final String TB_STUDENT_NAME = "tb_students";
    private static final String CREATE_TABLE_SQL = "create table " + TB_STUDENT_NAME +"(_id integer primary key autoincrement,usernumber text,password text,name text)";

    public MyStudentDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("hsl", "onCreate: SQL准备创建");
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
        Log.e("hsl", "onCreate: SQL创建");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertStudent(Student student) {
        Log.e("hsl", "insertStudent: 执行");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usernumber", student.getUsernumber());
        values.put("password", student.getPassword());
        values.put("name", student.getName());
        return db.insert(TB_STUDENT_NAME, null, values);

    }

    public Student selectByUserNumber(String usernumber) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TB_STUDENT_NAME, null, "usernumber = ?", new String[]{usernumber}, null, null, null);//null表示select *
        if (cursor != null) {
            while(cursor.moveToNext()) {
                @SuppressLint("Range") String userNumber = cursor.getString(cursor.getColumnIndex("usernumber"));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                Student student = new Student();
                student.setUsernumber(userNumber);
                student.setPassword(password);
                student.setName(name);
                return student;
            }
        }
        return null;
    }



}
