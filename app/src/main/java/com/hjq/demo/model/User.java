package com.hjq.demo.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class User {

    /** 用户ID */
    private static int id = 0;
    /** 用户名 */
    private String usrname;
    /** 密码 */
    private String password;
    /** 省 */
    private String mProvince;
    /** 市 */
    private String mCity;
    /** 区 */
    private String mArea;

    public User(String usrname, String password){
        id++;
        this.usrname = usrname;
        this.password = password;
        this.mProvince = "广东省";
        this.mCity = "广州市";
        this.mArea = "番禺区";

        System.out.println("model User " + id + " created: " + usrname + " " + password);
    }

    public String toString(){
        String res = "name: " + usrname + " password: " + password + "\n";
        res += ("address: " + mProvince + mCity + mArea);
        return res;
    }

    public int getId(){
        return id;
    }

    public String getUsrname() {
        return usrname;
    }

    public String getmProvince() {
        return mProvince;
    }

    public String getmCity() {
        return mCity;
    }

    public String getmArea() {
        return mArea;
    }

    public String getPassword() {
        return password;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public void setmProvince(String mProvince) {
        this.mProvince = mProvince;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public void setmArea(String mArea) {
        this.mArea = mArea;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
