package com.hjq.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    //test
    private static Map<Integer, User> map = new HashMap<Integer, User>();

    static {
        map.put(1, new User("李艳会", "111", "111111", User.TEACHER, "11111111"));
        map.put(2, new User("聂卉", "222", "222222", User.TEACHER, "22222222"));
        map.put(3, new User("王乐球", "333", "333333", User.TEACHER, "33333333"));
    }

    public static final int TEACHER = 1;
    public static final int STUDENT = 0;

    /** 用户总数 */
    private static int sum = 3;

    /** 用户ID */
    private int uid;
    /** 用户真实姓名 */
    private String usrname;
    /** 用户登录名 */
    private String account;
    /** 密码 */
    private String password;
    /** 用户类型 */
    private int usrtype;
    /** 用户学号/教职工号 */
    private String strID;
    /** 年级（教师此字段无效） */
    private int grade;

    public User(String usrname, String account, String password, int usrtype, String strID){
        sum++;
        this.uid = sum;
        this.usrname = usrname;
        this.account = account;
        this.password = password;
        this.usrtype = usrtype;
        this.strID = strID;
        this.grade = 0;

        System.out.println("model User " + uid + " created: " + usrname + " " + password);
    }

    public String toString(){
        String res = "account: " + account + " password: " + password + "\n";
        return res;
    }

    public String getStrID() {
        return strID;
    }

    public int getUid() {
        return uid;
    }

    public String getUsrname() {
        return usrname;
    }

    public int getUsertype() {
        return usrtype;
    }

    public String getPassword() {
        return password;
    }

    public String getAccount() {
        return account;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setUsertype(int usrtype) {
        this.usrtype = usrtype;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public static boolean isExist(int usrtype, String strID){
        //test
        if(usrtype == STUDENT){
           if(strID.equals("12345678") || strID.equals("87654321"))
               return true;

        }
        else {
            return true;
        }

        return false;

        /*User user = queryUserInfoByStrID(usrtype, strID);
        if(user == null)
            return false;
        else
            return true;*/
    }

    public static int getUidByStrID(String strID){
        //test
        if(strID.equals("12345678"))
            return 1;
        if(strID.equals("87654321"))
            return 2;

        return 0;

        /*User user = queryUserInfoByStrID(strID);
        return user.getUid();*/
    }


    /*
     *  向学生和教师表发起查询
     *  输入：要查询的用户名   如果存在用户返回user，否则返回null
     */
    public static User queryUserInfoByAccount(String account){
        //test
        int i;
        for(i = 1 ; i <= sum; i++){
            User user = map.get(i);
            if(user.getAccount().equals(account))
                return user;
        }

        return null;
    }

    /*
     *  向学生和教师表发起查询
     *  输入：要查询的用户id   如果存在用户返回user，否则返回null
     */
    public static User queryUserInfoById(int uid){
        //test
        int i;
        for(i = 1 ; i <= sum; i++){
            User user = map.get(i);
            if(user.getUid() == uid)
                return user;
        }

        return null;
    }

    /*
     *  向学生和教师表发起查询
     *  输入：要查询的学号/教职工号   如果存在用户返回user，否则返回null
     */
    public static User queryUserInfoByStrID(String strID){
        //test
        int i;
        for(i = 1 ; i <= sum; i++){
            User user = map.get(i);
            if(user.getStrID().equals(strID))
                return user;
        }

        return null;
    }

    /*
     *  向学生和教师表发起查询，usrtpye指示是学生还是教师
     *  输入：要查询的学号/教职工号，用户类型   如果存在用户返回user，否则返回null
     */
    public static User queryUserInfoByStrID(int usrtype, String strID){
        //test
        return queryUserInfoByStrID(strID);
    }


    /*
     *  向学生/教师表中插入数据，user中的usrtpye指示是学生还是教师，教师用户的grade字段值无效
     *  输入：要添加的用户数据   无返回值
     */
    public static void addUserInfo(User user){
        //test
        //User res = new User(user.getUsrname(), user.getAccount(), user.getPassword(), user.getUsertype(), user.getStrID());
        map.put(user.getUid(), user);
    }

    /*
     *  向学生/教师表中更新数据，user中的usrtpye指示是学生还是教师，教师用户的grade字段值无效
     *  输入：要更新的用户数据   无返回值
     */
    public static void updateUserInfo(User user){
        //test
        map.remove(user.getUid());
        addUserInfo(user);
    }

    /*
     *  查询登录用户名和密码是否匹配，usrtpye指示是学生还是教师
     *  输入：用户名，密码   匹配返回true，否则返回false
     */
    public static boolean checkPassword(String account, String password){
        return true;
    }


}
