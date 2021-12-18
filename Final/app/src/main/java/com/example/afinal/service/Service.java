package com.example.afinal.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.afinal.data.Record;
import com.example.afinal.db.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Service {

    private final String model="1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    private util dbUtil=null;

    public Service(Context context) {          //获取数据库连接
        this.dbUtil = new util(context,"record_db",null,1);
    }

    private String getDateString(boolean flag){      //获取日期
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date=new Date();
        if(flag)
            return sdf.format(date);
        else return sdf2.format(date);
    }

    private String getIdentity(){        //获取编码
        StringBuilder info=new StringBuilder();
        for(int i=0;i<10;i++)
            info.append(model.charAt((int)(Math.random()*100)%62));
        return info.append(getDateString(false)).toString();
    }


//    }

    public void insertRecord(Record record){     //增加
        ContentValues values=new ContentValues();
        values.put("rid",getIdentity());
        values.put("uid",record.getUid());
        values.put("catchCount",record.getCatchCount());
        values.put("wishTime",getDateString(true));
        SQLiteDatabase db=dbUtil.getReadableDatabase();
        db.insert("record",null,values);
        db.close();
    }

    public List<Record> showAllRecord(){    //获取祈愿
        String sql="select * from record order by wishTime desc";
        List<Record> list=new ArrayList<>();
        if(dbUtil!=null) {
            SQLiteDatabase db = dbUtil.getWritableDatabase();
            Cursor cursor=db.rawQuery(sql,null);
            while (cursor.moveToNext()){
                Record record=new Record();
                String id=cursor.getString(cursor.getColumnIndex("uid"));
                record.setUid(Integer.valueOf(id));
                record.setCatchCount(Double.valueOf(cursor.getString(cursor.getColumnIndex("catchCount"))));
                record.setWishTime(cursor.getString(cursor.getColumnIndex("wishTime")));


                list.add(record);
            }
            cursor.close();
            db.close();
        }
        return list;
    }



    public void clearWish() {  //清除祈愿
        SQLiteDatabase database = dbUtil.getWritableDatabase();
        database.execSQL("delete from " + "record");
    }


}
