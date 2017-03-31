package com.myproject.bilibili.model.shopping.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myproject.bilibili.model.shopping.database.bean.GoodsBean;
import com.myproject.bilibili.model.shopping.database.db.GoodsDB;
import com.myproject.bilibili.model.shopping.database.table.GoodsTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2017/3/29 0:57.
 * 作用:XXXX
 */

public class GoodsDao {


    private GoodsDB dbHelper;

    public GoodsDao(Context mContext) {
        dbHelper = new GoodsDB(mContext);
    }

    // 获取商品
    public List<GoodsBean> getAllGoods(){

        List<GoodsBean> list = new ArrayList<>();

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String sql = "select * from " + GoodsTable.TABLE_NAME;

        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            GoodsBean goodsBean = new GoodsBean();

//            goodsBean.setId(cursor.getInt(cursor.getColumnIndex(GoodsTable.GOODS_ID)));

            goodsBean.setSkuid(cursor.getInt(cursor.getColumnIndex(GoodsTable.GOODS_SKUID)));
            goodsBean.setNumber(cursor.getInt(cursor.getColumnIndex(GoodsTable.GOODS_NUMBER)));
            goodsBean.setPrice(cursor.getInt(cursor.getColumnIndex(GoodsTable.GOODS_PRICE)));
            goodsBean.setImageUrl(cursor.getString(cursor.getColumnIndex(GoodsTable.GOODS_IMAGEURL)));
            goodsBean.setName(cursor.getString(cursor.getColumnIndex(GoodsTable.GOODS_NAME)));

            list.add(goodsBean);

        }

        cursor.close();
        return list;
    }


    public GoodsBean getGoodsBySkuID(int skuid){
        if (skuid == -1){
            return null;
        }

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String sql = "select * from " + GoodsTable.TABLE_NAME +
                " where " + GoodsTable.GOODS_SKUID + "=?";
        Cursor cursor = database.rawQuery(sql, new String[]{skuid+""});

        GoodsBean goodsBean = null;
        while (cursor.moveToNext()){
            goodsBean =  new GoodsBean();
//            goodsBean.setId(cursor.getInt(cursor.getColumnIndex(GoodsTable.GOODS_ID)));
//            goodsBean.setSkuid(cursor.getInt(cursor.getColumnIndex(GoodsTable.GOODS_SKUID)));
            goodsBean.setNumber(cursor.getInt(cursor.getColumnIndex(GoodsTable.GOODS_PRICE)));
            goodsBean.setPrice(cursor.getInt(cursor.getColumnIndex(GoodsTable.GOODS_PRICE)));

        }
        cursor.close();
        return goodsBean;
    }

    public void deleteGoods(GoodsBean goodsBean){
        if (goodsBean == null){
            return;
        }

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.delete(GoodsTable.TABLE_NAME , "skuid=?" , new String[]{goodsBean.getSkuid()+""});
    }

    public void AddGoods(GoodsBean goodsBean){
        if (goodsBean == null){
            return;
        }

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoodsTable.GOODS_SKUID , goodsBean.getSkuid());
        values.put(GoodsTable.GOODS_NUMBER , goodsBean.getNumber());
        values.put(GoodsTable.GOODS_PRICE , goodsBean.getPrice());
        values.put(GoodsTable.GOODS_IMAGEURL , goodsBean.getImageUrl());
        values.put(GoodsTable.GOODS_NAME , goodsBean.getName());
        database.insert(GoodsTable.TABLE_NAME , null , values);
    }

    public void updataGoods(GoodsBean goodsBean) {
        if (goodsBean == null) return;

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(GoodsTable.GOODS_NUMBER, goodsBean.getNumber());
        database.update(GoodsTable.TABLE_NAME,values,"skuid =?",new String[]{goodsBean.getSkuid()+""});
    }
}
