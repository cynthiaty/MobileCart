package com.example.cynthiaty.mobilecart.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.cynthiaty.mobilecart.R;
import com.example.cynthiaty.mobilecart.model.adapter.CouponAdapter;
import com.example.cynthiaty.mobilecart.model.db.CouponService;
import com.example.cynthiaty.mobilecart.model.adapter.DiscountAdapter;
import com.example.cynthiaty.mobilecart.model.db.DiscountService;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：页面-我
 */
public class MeActivity extends AppCompatActivity {
    private TabHost tabHost;
    private ListView listDiscount;
    private ListView listCoupon;
    private Button addDiscount;
    private Button addCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me);

        initTab();
        initData();
        handleAffair();
    }

    private void initTab() {
        //从布局中获取TabHost并建立
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("优惠券")
                .setContent(R.id.tab1));

        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("折扣")
                .setContent(R.id.tab2));

        tabHost.setCurrentTab(0);
    }

    private void initData() {
        listDiscount = (ListView) findViewById(R.id.list_discount);
        listCoupon = (ListView) findViewById(R.id.list_coupon);
        addDiscount = (Button) findViewById(R.id.add_discount);
        addCoupon = (Button) findViewById(R.id.add_coupon);

        DiscountService discountService = new DiscountService(this);
        listDiscount.setAdapter(new DiscountAdapter(this,
                discountService.getScrollData(0, (int) discountService.getCount())));

        CouponService couponService = new CouponService(this);
        listCoupon.setAdapter(new CouponAdapter(this,
                couponService.getScrollData(0, (int) couponService.getCount())));
    }

    /**
     * 处理相关事务：添加优惠券和折扣
     */
    private void handleAffair() {
        addDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MeActivity.this, "亲，后台维护中，请谅解哦~", Toast.LENGTH_SHORT).show();
            }
        });

        addCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MeActivity.this, "亲，后台维护中，请谅解哦~", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
