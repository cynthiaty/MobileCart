package com.example.cynthiaty.mobilecart.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cynthiaty.mobilecart.model.entity.Coupon;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：访问数据库
 */
public class CouponService {
    private DBOpenHelper dbOpenHelper;

    public CouponService(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    /**
     * 增加一条coupon记录
     */
    public void insert(Coupon coupon) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into coupon(threshold, minus, date) values(?, ?, ?)",
                new Object[]{coupon.getThreshold(), coupon.getMinus(), coupon.getDate()});
        db.close();
    }

    /**
     * 删除一条coupon记录
     */
    public void delete(int id) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from coupon where _id=?", new Object[]{id});
        db.close();
    }

    /**
     * 修改一条coupon记录
     */
    public void update(Coupon coupon) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("update coupon set threshold=?, minus=?, date=? where _id=?",
                new Object[]{coupon.getThreshold(), coupon.getMinus(), coupon.getDate(), coupon.get_id()});
        db.close();
    }

    /**
     * 查找一条coupon记录
     */
    public Coupon find(int id) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from coupon where _id=?",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {      //判断cursor中是否有数据
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            int threshold = cursor.getInt(cursor.getColumnIndex("threshold"));
            int minus = cursor.getInt(cursor.getColumnIndex("minus"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            return new Coupon(_id, threshold, minus, date);
        }
        cursor.close();
        db.close();
        return null;
    }

    /**
     * 分页显示
     * @param offset 起始位置/偏移量
     * @param count 每页显示数量
     * @return
     */
    public List<Coupon> getScrollData(int offset, int count) {
        List<Coupon> couponList = new ArrayList<>();

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from coupon limit ?,?",
                new String[]{String.valueOf(offset), String.valueOf(count)});
        while (cursor.moveToNext()){
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            int threshold = cursor.getInt(cursor.getColumnIndex("threshold"));
            int minus = cursor.getInt(cursor.getColumnIndex("minus"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            couponList.add(new Coupon(_id, threshold, minus, date));
        }
        cursor.close();
        db.close();
        return  couponList;
    }

    /**
     * 获取coupon总记录数
     */
    public long getCount() {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from coupon", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        cursor.close();
        db.close();
        return 0;
    }
}