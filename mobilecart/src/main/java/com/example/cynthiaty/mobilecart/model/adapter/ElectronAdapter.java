package com.example.cynthiaty.mobilecart.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cynthiaty.mobilecart.R;
import com.example.cynthiaty.mobilecart.model.db.CartService;
import com.example.cynthiaty.mobilecart.model.entity.Cart;
import com.example.cynthiaty.mobilecart.model.entity.Goods;

import java.util.List;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：自定义适配器adapter：ElectronAdapter
 */
public class ElectronAdapter extends BaseAdapter {
    private Context mContext;
    private List<Goods> mData;
    private LayoutInflater inflater;

    public ElectronAdapter(Context context, List<Goods> data) {
        mContext = context;
        mData = data;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    /**
     * convertView: 缓存第一屏数据（复用convertView提高ListView性能）
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        Goods goods = mData.get(position);

        TextView name = null;
        TextView price = null;
        Button add = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mall_item, null);
            name = (TextView) convertView.findViewById(R.id.name);
            price = (TextView) convertView.findViewById(R.id.price);
            add = (Button) convertView.findViewById(R.id.add);
            add.setOnClickListener(new HandleAdd(goods));
            add.setTag(position);
            convertView.setTag(new ViewHolder(name, price, add));
        }
        else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            name = viewHolder.name;
            price = viewHolder.price;
            add = viewHolder.add;
        }

        name.setText(goods.getName());
        price.setText("¥" + goods.getPrice());

        return convertView;
    }

    /**
     * 处理添加一条商品记录事件
     */
    private class HandleAdd implements View.OnClickListener {
        private Goods goods;

        public HandleAdd(Goods goods) {
            this.goods = goods;
        }

        @Override
        public void onClick(View v) {
            CartService service = new CartService(mContext);
            List<Cart> cartList = service.getScrollData(0, (int) service.getCount());
            boolean flag = false;
            for (Cart cart : cartList) {
                if (cart.getName().equals(goods.getName())) {
                    cart.setNum(cart.getNum() + 1);
                    service.update(cart);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Cart cart = new Cart(goods.getType(),goods.getName(), goods.getPrice(), 1);
                service.insert(cart);
            }
        }
    }

    /**
     * ViewHolder:静态类提高ListView性能
     */
    private static final class ViewHolder {
        private TextView name;
        private TextView price;
        private Button add;

        public ViewHolder(TextView name, TextView price, Button add) {
            this.name = name;
            this.price = price;
            this.add = add;
        }
    }
}
