package com.myproject.bilibili.model.shopping.database.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myproject.bilibili.model.shopping.database.table.GoodsTable;

/**
 * Created by chen on 2017/3/29 0:58.
 * 作用:XXXX
 */

public class GoodsDB extends SQLiteOpenHelper {

    public GoodsDB(Context context) {
        super(context, "goods_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库
        /**
         * "create table userinfo（id text primary key）"
         */
        db.execSQL(GoodsTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
