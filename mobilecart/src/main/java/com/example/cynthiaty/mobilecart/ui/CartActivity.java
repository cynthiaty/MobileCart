package com.example.cynthiaty.mobilecart.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cynthiaty.mobilecart.R;
import com.example.cynthiaty.mobilecart.model.adapter.CartAdapter;
import com.example.cynthiaty.mobilecart.model.db.CartService;
import com.example.cynthiaty.mobilecart.model.db.CouponService;
import com.example.cynthiaty.mobilecart.model.db.DiscountService;
import com.example.cynthiaty.mobilecart.model.entity.Cart;
import com.example.cynthiaty.mobilecart.model.entity.Coupon;
import com.example.cynthiaty.mobilecart.model.entity.Discount;

import java.util.List;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：页面-购物车
 */
public class CartActivity extends AppCompatActivity {
    private ListView listCart;
    TextView sum;
    Button closeAccount;
    View lineCoupon;
    RelativeLayout cartCoupon;
    TextView couponDetail;

    private CartAdapter cartAdapter;
    private CartService cartService;
    private List<Cart> cartData;
    private CouponService couponService;
    private List<Coupon> couponData;
    private List<Discount> discountData;
    private DiscountService discountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        initData();
        handleAffair();
    }

    private void initData() {
        listCart = (ListView) findViewById(R.id.list_cart_goods);
        sum = (TextView) findViewById(R.id.sum);
        closeAccount = (Button) findViewById(R.id.close_account);
        lineCoupon = findViewById(R.id.line_coupon);
        cartCoupon = (RelativeLayout) findViewById(R.id.cart_coupon);
        couponDetail = (TextView) findViewById(R.id.coupon_detail);

        cartService = new CartService(this);
        cartData = cartService.getScrollData(0, (int) cartService.getCount());
        cartAdapter = new CartAdapter(this, cartData);
        listCart.setAdapter(cartAdapter);
        couponService = new CouponService(this);
        couponData = couponService.getScrollData(0, (int) couponService.getCount());
        discountService = new DiscountService(this);
        discountData = discountService.getScrollData(0, (int) discountService.getCount());

        closeAccount(false);
    }

    /**
     * 处理相关事务：加减商品数量、结算
     */
    private void handleAffair() {
        cartAdapter.setOnSubNum(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = v.getTag();
                if (tag != null && tag instanceof Integer) {
                    int position = (Integer) tag;
                    Cart posCart = cartData.get(position);
                    int num = posCart.getNum();
                    if (num > 0) {
                        num--;
                        if (num == 0) {
                            cartData.remove(posCart);
                            cartService.delete(posCart.get_id());
                        }
                        else {
                            posCart.setNum(num);
                            cartData.get(position).setNum(num);
                            cartService.update(posCart);
                        }
                        closeAccount(false);
                        cartAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        cartAdapter.setOnAddNum(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = v.getTag();
                if (tag != null && tag instanceof Integer) {
                    int position = (Integer) tag;
                    Cart posCart = cartData.get(position);
                    int num = posCart.getNum();
                    num++;
                    posCart.setNum(num);
                    cartData.get(position).setNum(num);
                    cartService.update(posCart);
                    closeAccount(false);
                    cartAdapter.notifyDataSetChanged();
                }
            }
        });

        closeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeAccount(true);
                Toast.makeText(CartActivity.this, "亲，购买成功，接着逛呢~", Toast.LENGTH_SHORT).show();
                for (Cart cart : cartData) {
                    cartService.delete(cart.get_id());
                }
                cartData.clear();
                sum.setText("合计：¥" + 0.0);
                cartAdapter.notifyDataSetChanged();

                lineCoupon.setVisibility(View.INVISIBLE);
                cartCoupon.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * 结算购物车--折扣
     */
    private void closeAccountDiscount() {
        double account = 0.0;
        boolean flag = false;
        for (Cart cart : cartData) {
            for (Discount discount : discountData) {
                if (discount.getType().equals(cart.getType())) {
                    account += cart.getPrice() * cart.getNum() * discount.getRate() / 10;
                    flag = true;
                    break;
                }
                else {
                    flag = false;
                }
            }

            if (!flag) {
                account += cart.getPrice() * cart.getNum();
            }
        }
    }

    /**
     * 结算购物车
     */
    private void closeAccount(boolean mFlag) {
        double account = 0.0;
        for (Cart cart : cartData) {
            account += cart.getPrice() * cart.getNum();
        }

        if (account == 0.0 || couponData.size() == 0) {
            lineCoupon.setVisibility(View.INVISIBLE);
            cartCoupon.setVisibility(View.INVISIBLE);
        }
        else {
            boolean flag = false;
            for (Coupon coupon : couponData) {
                if (coupon.getThreshold() <= account) {
                    flag = true;
                    lineCoupon.setVisibility(View.VISIBLE);
                    cartCoupon.setVisibility(View.VISIBLE);
                    couponDetail.setText("满" + coupon.getThreshold() + "减"  + coupon.getMinus());

                    if (mFlag) {
                        couponService.delete(coupon.get_id());
                        couponData.remove(coupon);
                    }
                    account -= coupon.getMinus();

                    break;
                }
            }
            if (!flag) {
                lineCoupon.setVisibility(View.INVISIBLE);
                cartCoupon.setVisibility(View.INVISIBLE);
            }
        }

        sum.setText("合计：¥" + account);
    }
}
