package com.example.cynthiaty.mobilecart.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.cynthiaty.mobilecart.R;
import com.example.cynthiaty.mobilecart.model.common.Batch;
import com.example.cynthiaty.mobilecart.model.db.CouponService;
import com.example.cynthiaty.mobilecart.model.db.DBOpenHelper;
import com.example.cynthiaty.mobilecart.model.db.DiscountService;
import com.example.cynthiaty.mobilecart.model.db.GoodsService;
import com.example.cynthiaty.mobilecart.model.entity.Coupon;
import com.example.cynthiaty.mobilecart.model.entity.Discount;
import com.example.cynthiaty.mobilecart.model.entity.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：主界面
 */
public class MainActivity extends TabActivity {
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initDB();
        initTab();
    }

    private void initTab() {
        tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("MallActivity.class")
                .setIndicator("商城")
                .setContent(new Intent(this, MallActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));//点击该tab时重新加载页面;

        tabHost.addTab(tabHost.newTabSpec("MeActivity.class")
                .setIndicator("我")
                .setContent(new Intent(this, MeActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));//点击该tab时重新加载页面

        tabHost.addTab(tabHost.newTabSpec("CartActivity.class")
                .setIndicator("购物车")
                .setContent(new Intent(this, CartActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));//点击该tab时重新加载页面

        tabHost.setCurrentTab(0);
    }

    private void initDB() {
        boolean isExist = false;
        try {
            DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
            SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select count(*) from goods", null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    isExist = true;
                }
            }
            db.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (!isExist) {
            GoodsService goodsService = new GoodsService(this);
            List<Goods> goodsList = new ArrayList<>();

            goodsList.add(new Goods("电子", "iPad", 2399.00));
            goodsList.add(new Goods("电子", "iPhone", 5288.00));
            goodsList.add(new Goods("电子", "显示器", 899.00));
            goodsList.add(new Goods("电子", "笔记本电脑", 4599.00));
            goodsList.add(new Goods("电子", "键盘", 68.00));
            goodsList.add(new Goods("食品", "面包", 3.00));
            goodsList.add(new Goods("食品", "饼干", 5.00));
            goodsList.add(new Goods("食品", "蛋糕", 20.00));
            goodsList.add(new Goods("食品", "牛肉", 25.00));
            goodsList.add(new Goods("食品", "蔬菜", 3.00));
            goodsList.add(new Goods("日用品", "餐巾纸", 10.00));
            goodsList.add(new Goods("日用品", "收纳箱", 20.00));
            goodsList.add(new Goods("日用品", "咖啡杯", 5.00));
            goodsList.add(new Goods("日用品", "雨伞", 45.00));
            goodsList.add(new Goods("酒类", "啤酒", 2.00));
            goodsList.add(new Goods("酒类", "白酒", 150.00));
            goodsList.add(new Goods("酒类", "伏特加", 230.00));

            for (int i = 0; i < goodsList.size(); i++) {
                goodsService.insert(goodsList.get(i));
            }

            DiscountService discountService = new DiscountService(this);
            List<Discount> discountList = new ArrayList<>();

            discountList.add(new Discount("电子类折扣", (float) 8.0, Batch.GetSystemCalendar()));
            discountList.add(new Discount("食品类折扣", (float) 9.0, Batch.GetSystemCalendar()));
            discountList.add(new Discount("日用品类折扣", (float) 9.0, Batch.GetSystemCalendar()));
            discountList.add(new Discount("酒类折扣", (float) 7.0, Batch.GetSystemCalendar()));

            for (int i = 0; i < discountList.size(); i++) {
                discountService.insert(discountList.get(i));
            }

            CouponService couponService = new CouponService(this);
            List<Coupon> couponList = new ArrayList<>();

            couponList.add(new Coupon(2000, 500, "2020-03-05"));
            couponList.add(new Coupon(1000, 200, "2017-03-05"));
            couponList.add(new Coupon(500, 199, "2020-03-05"));
            couponList.add(new Coupon(199, 99, "2017-03-05"));
            couponList.add(new Coupon(100, 5, "2020-03-05"));
            couponList.add(new Coupon(20, 1, "2017-03-05"));

            for (int i = 0; i < couponList.size(); i++) {
                couponService.insert(couponList.get(i));
            }
        }
    }
}
