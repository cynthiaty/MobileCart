package com.example.cynthiaty.mobilecart.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.cynthiaty.mobilecart.R;
import com.example.cynthiaty.mobilecart.model.adapter.CommodityAdapter;
import com.example.cynthiaty.mobilecart.model.db.GoodsService;
import com.example.cynthiaty.mobilecart.model.adapter.ElectronAdapter;
import com.example.cynthiaty.mobilecart.model.adapter.FoodAdapter;
import com.example.cynthiaty.mobilecart.model.adapter.LiquorAdapter;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：页面-商品
 */
public class MallActivity extends AppCompatActivity {
    private TabHost tabHost;
    private ListView listElectron;
    private ListView listFood;
    private ListView listCommodity;
    private ListView listLiquor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mall);

        initTab();
        initData();
    }

    private void initTab() {
        //从布局中获取TabHost并建立
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("电子")
                .setContent(R.id.list_electron));

        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("食品")
                .setContent(R.id.list_food));

        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("日用品")
                .setContent(R.id.list_commodity));

        tabHost.addTab(tabHost.newTabSpec("tab4")
                .setIndicator("酒类")
                .setContent(R.id.list_liquor));

        tabHost.setCurrentTab(0);
    }

    private void initData() {
        listElectron = (ListView) findViewById(R.id.list_electron);
        listFood = (ListView) findViewById(R.id.list_food);
        listCommodity = (ListView) findViewById(R.id.list_commodity);
        listLiquor = (ListView) findViewById(R.id.list_liquor);

        GoodsService service = new GoodsService(this);
        listElectron.setAdapter(new ElectronAdapter(this, service.getScrollData(0, 5)));
        listFood.setAdapter(new FoodAdapter(this, service.getScrollData(5, 5)));
        listCommodity.setAdapter(new CommodityAdapter(this, service.getScrollData(10, 4)));
        listLiquor.setAdapter(new LiquorAdapter(this, service.getScrollData(14, 3)));
    }
}