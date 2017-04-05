package com.example.cynthiaty.mobilecart.model.entity;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：Discount实体类，封装了Discount这个数据的属性和方法
 */
public class Discount {
    private int _id;            //唯一编号
    private String type;        //折扣类别
    private float rate;         //折扣力度
    private String date;        //截止日期

    public Discount(String type, float rate, String date) {
        this.type = type;
        this.rate = rate;
        this.date = date;
    }

    public Discount(int _id, String type, float rate, String date) {
        this._id = _id;
        this.type = type;
        this.rate = rate;
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
