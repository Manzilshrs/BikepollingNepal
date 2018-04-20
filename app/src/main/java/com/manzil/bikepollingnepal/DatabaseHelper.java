package com.manzil.bikepollingnepal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import static android.media.CamcorderProfile.get;

/**
 * Created by Manjil on 12/23/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name="Bikepollingnepaldb";
    static int version=1;


    String createTableUser="CREATE TABLE if not exists \"user\" (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`username`\tINTEGER,\n" +
            "\t`email`\tINTEGER,\n" +
            "\t`phone`\tINTEGER,\n" +
            "\t`address`\tINTEGER,\n" +
            "\t`password`\tINTEGER,\n" +
            "\t`gender`\tINTEGER,\n" +
            "\t`dob`\tINTEGER,\n" +
            "\t`image`\tBLOB\n" +
            ")";

    String createRiderinfo="CREATE TABLE  if not exists `Riderinfo` (\n" +
            "\t`Uid`\tTEXT,\n" +
            "\t`Rid`\tTEXT,\n" +
            "\t`BikeName`\tTEXT,\n" +
            "\t`BikeRegisterNo`\tTEXT,\n" +
            "\t`CurrentLocation`\tTEXT,\n" +
            "\t`Destination`\tTEXT,\n" +
            "\t`TimeNDate`\tTEXT,\n" +
            "\tPRIMARY KEY(UID)\n" +
            ")";
    String createCustomerinfo="CREATE TABLE if not exists`Customerinfo` (\n" +
            "\t`Uid`\tTEXT,\n" +
            "\t`Cid`\tTEXT,\n" +
            "\t`CurrentLocation`\tTEXT,\n" +
            "\t`Destination`\tTEXT,\n" +
            "\t`TimeNDate`\tTEXT,\n" +
            "\tPRIMARY KEY(Uid)\n" +
            ")";

    public DatabaseHelper(Context context ) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUser);
        getWritableDatabase().execSQL(createRiderinfo);
        getWritableDatabase().execSQL(createCustomerinfo);
        Log.e("DATABASE OPERATION","Table created...");
    }

    public void insertUser(ContentValues cv)
    {

        getWritableDatabase().insert("user","",cv);
        Log.e("DATABASE OPERATION","inserted");
    }
public void updateuser(String id,ContentValues cv){
    getWritableDatabase().update("user",cv,"id="+id,null);
//    getWritableDatabase().update("user",cv,"id=?",new String[]{id});
}

public void deleteUser(String id){

    getWritableDatabase().delete("user","id="+id,null);
}

public boolean isLoginValid(String phone,String password){

    String sql="Select count(*) from user where phone='"+phone+"' and password='"+password+"'";
    SQLiteStatement statement=getReadableDatabase().compileStatement(sql);
    long l=statement.simpleQueryForLong();
    if (l==1) {
        return true;
    }
    else
    {
        return false;
    }
}

    public ArrayList<UserInfo> getUserList(){

        String sql="select * from user";
        ArrayList<UserInfo> list=new ArrayList<UserInfo>();
        Cursor c =getReadableDatabase().rawQuery(sql,null);
        while (c.moveToNext()){
            UserInfo info=new UserInfo();
            info.id=c.getString(c.getColumnIndex("id"));
            info.username=c.getString(c.getColumnIndex("username"));
            info.password=c.getString(c.getColumnIndex("password"));
            info.email=c.getString(c.getColumnIndex("email"));
            info.address=c.getString(c.getColumnIndex("address"));
            info.gender=c.getString(c.getColumnIndex("gender"));
            info.dob=c.getString(c.getColumnIndex("dob"));
            info.phone=c.getString(c.getColumnIndex("phone"));
            info.image=c.getBlob(c.getColumnIndex("image"));
            list.add(info);

        }
        c.close();
        return list;
    }


    public UserInfo getUserInfo(String id){

        String sql="select * from user where id="+id;

        Cursor c =getReadableDatabase().rawQuery(sql,null);
        UserInfo info=new UserInfo();
        while (c.moveToNext()){

            info.id=c.getString(c.getColumnIndex("id"));
            info.username=c.getString(c.getColumnIndex("username"));
            info.password=c.getString(c.getColumnIndex("password"));
            info.email=c.getString(c.getColumnIndex("email"));
            info.address=c.getString(c.getColumnIndex("address"));
            info.gender=c.getString(c.getColumnIndex("gender"));
            info.dob=c.getString(c.getColumnIndex("dob"));
            info.phone=c.getString(c.getColumnIndex("phone"));
            info.image=c.getBlob(c.getColumnIndex("image"));


        }
        c.close();
        return info;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    public void concept(){
//        int[] intarray=new int[]{1,2,3,4,5,6,7,8}; /*fixed size is  limitation of array */
//        ArrayList<String>stringArrayList=new ArrayList<String>();
//        stringArrayList.add("Prem");
//        stringArrayList.add("Kuldeep");
//        stringArrayList.add("Bijay");
//        stringArrayList.add("Abishek");
//        stringArrayList.add("Dipesh");  /*solved limitation of array by arraylist*/
//
//     String value= stringArrayList.get(3);
//        ArrayList<UserInfo>userInfoArrayList= new ArrayList<UserInfo>();
//        UserInfo info=new UserInfo();
//        info.username="kuldeep";
//        info.address="banepa";
//        info.gender="male";
//        info.email="kuldeep@gmail.com";
//        info.password="pass";
//
//        userInfoArrayList.add(info);
//        userInfoArrayList.get(0);
//
//        HashMap<String, UserInfo>hashMap =  new HashMap<String,UserInfo>();/*Hashmap set the values in key and value her kuldeep is key */
//        hashMap.put("kuldeep",info);
//
//
//        UserInfo info1=hashMap.get("kuldeep");
//
//
//        ContentValues contentValues= new ContentValues(); /* it is like a hashmap ContentValues is inbuild class mainly used for database*/
//        contentValues.put("id","1");
//        contentValues.put("username","Dipesh");
//        contentValues.put("password","pass");
//        contentValues.put("address","jorpati");
//
//        contentValues.getAsString("username");



//    }
}
