package com.example.cynthiaty.mobilecart.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cynthiaty.mobilecart.R;
import com.example.cynthiaty.mobilecart.model.entity.Cart;

import java.util.List;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：自定义适配器adapter：CartAdapter
 */
public class CartAdapter extends BaseAdapter {
    private Context mContext;
    private List<Cart> mData;
    private LayoutInflater inflater;
    private View.OnClickListener onAddNum;
    private View.OnClickListener onSubNum;

    public CartAdapter(Context context, List<Cart> data) {
        mContext = context;
        mData = data;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnAddNum(View.OnClickListener onAddNum){
        this.onAddNum = onAddNum;
    }

    public void setOnSubNum(View.OnClickListener onSubNum){
        this.onSubNum = onSubNum;
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
        TextView name = null;
        TextView price = null;
        Button decrease = null;
        EditText num = null;
        Button increase = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cart_item, null);
            name = (TextView) convertView.findViewById(R.id.name);
            price = (TextView) convertView.findViewById(R.id.price);
            decrease = (Button) convertView.findViewById(R.id.decrease);
            decrease.setOnClickListener(onSubNum);
            decrease.setTag(position);
            num = (EditText) convertView.findViewById(R.id.num);
            increase = (Button) convertView.findViewById(R.id.increase);
            increase.setOnClickListener(onAddNum);
            increase.setTag(position);
            convertView.setTag(new ViewHolder(name, price, decrease, num, increase));
        }
        else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            name = viewHolder.name;
            price = viewHolder.price;
            decrease = viewHolder.decrease;
            num = viewHolder.num;
            increase = viewHolder.increase;
        }

        Cart cart = mData.get(position);
        name.setText(cart.getName());
        price.setText("¥" + cart.getPrice());
        num.setText(String.valueOf(cart.getNum()));
        return convertView;
    }

    /**
     * ViewHolder:静态类提高ListView性能
     */
    private static final class ViewHolder {
        private TextView name;
        private TextView price;
        private Button decrease;
        private EditText num;
        private Button increase;

        public ViewHolder(TextView name, TextView price, Button decrease, EditText num, Button increase) {
            this.name = name;
            this.price = price;
            this.decrease = decrease;
            this.num = num;
            this.increase = increase;
        }
    }
}