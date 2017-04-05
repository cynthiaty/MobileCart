package com.example.cynthiaty.mobilecart.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cynthiaty.mobilecart.R;
import com.example.cynthiaty.mobilecart.model.entity.Coupon;

import java.util.List;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：自定义适配器adapter：CouponAdapter
 */
public class CouponAdapter extends BaseAdapter {
    private Context mContext;
    private List<Coupon> mData;
    private LayoutInflater inflater;

    public CouponAdapter(Context context, List<Coupon> data) {
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
        TextView threshold = null;
        TextView minus = null;
        TextView date = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.coupon_item, null);
            threshold = (TextView) convertView.findViewById(R.id.threshold);
            minus = (TextView) convertView.findViewById(R.id.minus);
            date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(new ViewHolder(threshold, minus, date));
        }
        else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            threshold = viewHolder.threshold;
            minus = viewHolder.minus;
            date = viewHolder.date;
        }

        Coupon coupon = mData.get(position);
        threshold.setText("全场满" + coupon.getThreshold());
        minus.setText("减" + coupon.getMinus() + "优惠券");
        date.setText("有效期至：" + coupon.getDate());
        return convertView;
    }

    /**
     * ViewHolder:静态类提高ListView性能
     */
    private static final class ViewHolder {
        private TextView threshold;
        private TextView minus;
        private TextView date;

        public ViewHolder(TextView threshold, TextView minus, TextView date) {
            this.threshold = threshold;
            this.minus = minus;
            this.date = date;
        }
    }
}

