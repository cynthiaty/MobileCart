package com.example.cynthiaty.mobilecart.model.entity;

/**
 * 作者：尚萍萍
 * 日期：2017-04-05
 * 描述：Goods实体类，封装了Goods这个数据的属性和方法
 */
public class Goods {
    private int _id;            //唯一编号
    private String type;        //商品类别
    private String name;        //商品名称
    private double price;       //商品单价

    public Goods(String type, String name, double price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public Goods(int _id, String type, String name, double price) {
        this._id = _id;
        this.type = type;
        this.name = name;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
