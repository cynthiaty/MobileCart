package com.example.cynthiaty.mobilecart.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cynthiaty.mobilecart.R;
import com.example.cynthiaty.mobilecart.model.entity.Discount;

import java.util.List;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：自定义适配器adapter：DiscountAdapter
 */
public class DiscountAdapter extends BaseAdapter {
    private Context mContext;
    private List<Discount> mData;
    private LayoutInflater inflater;

    public DiscountAdapter(Context context, List<Discount> data) {
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
        TextView type = null;
        TextView rate = null;
        TextView date = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.discount_item, null);
            type = (TextView) convertView.findViewById(R.id.type);
            rate = (TextView) convertView.findViewById(R.id.rate);
            date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(new ViewHolder(type, rate, date));
        }
        else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            type = viewHolder.type;
            rate = viewHolder.rate;
            date = viewHolder.date;
        }

        Discount discount = mData.get(position);
        type.setText(discount.getType());
        rate.setText(discount.getRate() + "折");
        date.setText("截止日期：" + discount.getDate());
        return convertView;
    }

    /**
     * ViewHolder:静态类提高ListView性能
     */
    private static final class ViewHolder {
        public TextView type;
        public TextView rate;
        public TextView date;

        public ViewHolder(TextView type, TextView rate, TextView date) {
            this.type = type;
            this.rate = rate;
            this.date = date;
        }
    }
}
