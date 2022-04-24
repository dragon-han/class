package com.alvin.aclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "mycourse.db";
    private static final String TB_COURSE_NAME = "courses";
    private static final String CREATE_TABLE_SQL = "create table "+TB_COURSE_NAME+
            "(_id integer primary key autoincrement,course_name text,teacher text,class_room text,day integer,class_start integer,class_end integer)";

    public MySQLiteOpenHelper (Context context){
        super(context,DB_NAME,null,1);
    }

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
