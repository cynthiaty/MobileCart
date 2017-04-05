package com.example.cynthiaty.mobilecart.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cynthiaty.mobilecart.model.entity.Discount;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：访问数据库
 */
public class DiscountService {
    private DBOpenHelper dbOpenHelper;

    public DiscountService(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    /**
     * 增加一条discount记录
     */
    public void insert(Discount discount) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into discount(type, rate, date) values(?, ?, ?)",
                new Object[]{discount.getType(), discount.getRate(), discount.getDate()});
        db.close();
    }

    /**
     * 删除一条discount记录
     */
    public void delete(int id) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from discount where _id=?", new Object[]{id});
        db.close();
    }

    /**
     * 修改一条discount记录
     */
    public void update(Discount discount) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("update discount set type=?, rate=?, date=? where _id=?",
                new Object[]{discount.getType(), discount.getRate(), discount.getDate(), discount.get_id()});
        db.close();
    }

    /**
     * 查找一条discount记录
     */
    public Discount find(int id) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from discount where _id=?",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {      //判断cursor中是否有数据
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            float rate = cursor.getFloat(cursor.getColumnIndex("rate"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            return new Discount(_id, type, rate, date);
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
    public List<Discount> getScrollData(int offset, int count) {
        List<Discount> discountList = new ArrayList<>();

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from discount limit ?,?",
                new String[]{String.valueOf(offset), String.valueOf(count)});
        while (cursor.moveToNext()){
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            float rate = cursor.getFloat(cursor.getColumnIndex("rate"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            discountList.add(new Discount(_id, type, rate, date));
        }
        cursor.close();
        db.close();
        return  discountList;
    }

    /**
     * 获取discount总记录数
     */
    public long getCount() {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from discount", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        cursor.close();
        db.close();
        return 0;
    }
}
