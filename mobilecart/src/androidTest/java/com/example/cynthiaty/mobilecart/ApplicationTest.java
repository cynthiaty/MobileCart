package com.example.cynthiaty.mobilecart;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

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
 * 描述：单元测试（需要在模拟器或真机环境下运行）
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testCreateDB() throws Exception {
        DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
        dbOpenHelper.getWritableDatabase();
    }

    public void testInsertGoods() throws Exception {
        GoodsService service = new GoodsService(getContext());
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
            service.insert(goodsList.get(i));
        }
    }

    public void testGetSystemCalendar() throws Exception {
        Log.i(Batch.TAG, Batch.GetSystemCalendar());
    }

    public void testInsertDiscount() throws Exception {
        DiscountService service = new DiscountService(getContext());
        List<Discount> discountList = new ArrayList<>();

        discountList.add(new Discount("电子类折扣", (float) 8.0, Batch.GetSystemCalendar()));
        discountList.add(new Discount("食品类折扣", (float) 9.0, Batch.GetSystemCalendar()));
        discountList.add(new Discount("日用品类折扣", (float) 9.0, Batch.GetSystemCalendar()));
        discountList.add(new Discount("酒类折扣", (float) 7.0, Batch.GetSystemCalendar()));

        for (int i = 0; i < discountList.size(); i++) {
            service.insert(discountList.get(i));
        }
    }

    public void testInsertCoupon() throws Exception {
        CouponService service = new CouponService(getContext());
        List<Coupon> couponList = new ArrayList<>();

        couponList.add(new Coupon(2000, 500, "2020-03-05"));
        couponList.add(new Coupon(1000, 200, "2017-03-05"));
        couponList.add(new Coupon(500, 199, "2020-03-05"));
        couponList.add(new Coupon(199, 99, "2017-03-05"));
        couponList.add(new Coupon(100, 5, "2020-03-05"));
        couponList.add(new Coupon(20, 1, "2017-03-05"));

        for (int i = 0; i < couponList.size(); i++) {
            service.insert(couponList.get(i));
        }
    }
}