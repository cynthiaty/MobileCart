package com.example.cynthiaty.mobilecart.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：利用SQLiteOpenHelper类实现数据库的创建
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    public DBOpenHelper(Context context) {
        super(context, "mobile_cart.db", null, 17);    //数据库文件目录：运行机器的/data/data/package/databases/mobile_cart.db
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE goods(_id integer primary key, type varchar(20), name varchar(20), price decimal)");
        db.execSQL("CREATE TABLE discount(_id integer primary key, type varchar(20), rate decimal, date varchar(10))");
        db.execSQL("CREATE TABLE coupon(_id integer primary key, threshold integer, minus integer, date varchar(10))");
        db.execSQL("CREATE TABLE cart(_id integer primary key, type varchar(20), name varchar(20), price decimal, num integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists goods");
        db.execSQL("DROP TABLE if exists discount");
        db.execSQL("DROP TABLE if exists coupon");
        db.execSQL("DROP TABLE if exists cart");
        this.onCreate(db);
    }
}