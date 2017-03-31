package com.myproject.bilibili.model.shopping.database.table;

/**
 * Created by chen on 2017/3/29 0:58.
 * 作用:XXXX
 */

public class GoodsTable {

    //表明
    public static final String TABLE_NAME = "goodsinfo";

    //id
//    public static final String GOODS_ID = "_id";

    //数量
    public static final String GOODS_NUMBER = "number";
    //位置
    public static final String GOODS_SKUID = "skuid";
    //价格
    public static final String GOODS_PRICE = "price";

    public static final String GOODS_IMAGEURL = "imageurl";

    public static final String GOODS_NAME = "name";


    public static String CREATE_TABLE = "create table "+ TABLE_NAME +"("

            + GOODS_SKUID + " INTEGER,"

            + GOODS_NUMBER + " INTEGER,"

            + GOODS_PRICE + " INTEGER,"

            + GOODS_IMAGEURL + " text,"

            + GOODS_NAME + " text);";

}
