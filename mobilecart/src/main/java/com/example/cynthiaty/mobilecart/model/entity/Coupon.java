package com.example.cynthiaty.mobilecart.model.entity;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：Coupon实体类，封装了Coupon这个数据的属性和方法
 */
public class Coupon {
    private int _id;            //唯一编号
    private int threshold;      //优惠条件
    private int minus;          //优惠额度
    private String date;        //有效期

    public Coupon(int threshold, int minus, String date) {
        this.threshold = threshold;
        this.minus = minus;
        this.date = date;
    }

    public Coupon(int _id, int threshold, int minus, String date) {
        this._id = _id;
        this.threshold = threshold;
        this.minus = minus;
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getMinus() {
        return minus;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
