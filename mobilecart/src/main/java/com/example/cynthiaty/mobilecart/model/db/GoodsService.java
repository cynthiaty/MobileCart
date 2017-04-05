package com.example.cynthiaty.mobilecart.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cynthiaty.mobilecart.model.entity.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：访问数据库
 */
public class GoodsService {
    private DBOpenHelper dbOpenHelper;

    public GoodsService(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    /**
     * 增加一条goods记录
     */
    public void insert(Goods goods) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into goods(type, name, price) values(?, ?, ?)",
                new Object[]{goods.getType(), goods.getName(), goods.getPrice()});
        db.close();
    }

    /**
     * 删除一条goods记录
     */
    public void delete(int id) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from goods where _id=?", new Object[]{id});
        db.close();
    }

    /**
     * 修改一条goods记录
     */
    public void update(Goods goods) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("update goods set type=?, name=?, price=? where _id=?",
                new Object[]{goods.getType(), goods.getName(), goods.getPrice(), goods.get_id()});
        db.close();
    }

    /**
     * 查找一条goods记录
     */
    public Goods find(int id) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from goods where _id=?",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {      //判断cursor中是否有数据
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            return new Goods(_id, type, name, price);
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
    public List<Goods> getScrollData(int offset, int count) {
        List<Goods> goodsList = new ArrayList<>();

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from goods limit ?,?",
                new String[]{String.valueOf(offset), String.valueOf(count)});
        while (cursor.moveToNext()){
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            goodsList.add(new Goods(_id, type, name, price));
        }
        cursor.close();
        db.close();
        return  goodsList;
    }

    /**
     * 获取goods总记录数
     */
    public long getCount() {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from goods", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        cursor.close();
        db.close();
        return 0;
    }
}