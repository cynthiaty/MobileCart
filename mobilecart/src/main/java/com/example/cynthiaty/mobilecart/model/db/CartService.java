package com.example.cynthiaty.mobilecart.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cynthiaty.mobilecart.model.entity.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：访问数据库
 */
public class CartService {
    private DBOpenHelper dbOpenHelper;

    public CartService(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    /**
     * 增加一条cart记录
     */
    public void insert(Cart cart) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into cart(type, name, price, num) values(?, ?, ?, ?)",
                new Object[]{cart.getType(), cart.getName(), cart.getPrice(), cart.getNum()});
        db.close();
    }

    /**
     * 删除一条cart记录
     */
    public void delete(int id) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from cart where _id=?", new Object[]{id});
        db.close();
    }

    /**
     * 修改一条cart记录
     */
    public void update(Cart cart) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("update cart set type=?, name=?, price=?, num=? where _id=?",
                new Object[]{cart.getType(), cart.getName(), cart.getPrice(), cart.getNum(), cart.get_id()});
        db.close();
    }

    /**
     * 查找一条cart记录
     */
    public Cart find(int id) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from cart where _id=?",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {      //判断cursor中是否有数据
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            int num = cursor.getInt(cursor.getColumnIndex("num"));
            return new Cart(_id, type, name, price, num);
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
    public List<Cart> getScrollData(int offset, int count) {
        List<Cart> cartList = new ArrayList<>();

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from cart limit ?,?",
                new String[]{String.valueOf(offset), String.valueOf(count)});
        while (cursor.moveToNext()){
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            int num = cursor.getInt(cursor.getColumnIndex("num"));
            cartList.add(new Cart(_id, type, name, price, num));
        }
        cursor.close();
        db.close();
        return cartList;
    }

    /**
     * 获取cart总记录数
     */
    public long getCount() {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from cart", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        cursor.close();
        db.close();
        return 0;
    }
}