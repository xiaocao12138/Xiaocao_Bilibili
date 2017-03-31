package com.myproject.bilibili.model.shopping.database.bean;

/**
 * Created by chen on 2017/3/29 0:57.
 * 作用:XXXX
 */

public class GoodsBean {

    private int skuid;         //商品id
    private int number;     //数量
    private int price;   //商品位置
    private int id;      //价格
    private String name;
    private String imageUrl;

    public GoodsBean() {
    }

    public GoodsBean(int skuid, int number, int price, String name, String imageUrl) {
        this.skuid = skuid;
        this.number = number;
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public GoodsBean(String name, String imageUrl, int id, int price, int number, int skuid) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.id = id;
        this.price = price;
        this.number = number;
        this.skuid = skuid;
    }

    public GoodsBean(int number, int skuid, int price, int id, String name, String imageUrl, boolean isChecked) {
        this.number = number;
        this.skuid = skuid;
        this.price = price;
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.isChecked = isChecked;
    }

    /**
     * 是否选中
     */
    private boolean isChecked = true;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSkuid() {
        return skuid;
    }

    public void setSkuid(int skuid) {
        this.skuid = skuid;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", price=" + price +
                ", skuid=" + skuid +
                '}';
    }
}
