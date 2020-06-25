package com.hjq.demo.controller;

import com.hjq.demo.common.MyController;
import com.hjq.demo.model.User;

public class LoginController implements MyController {

    public static final int LOGIN_SUCCEED = 0;
    public static final int LOGIN_ERROR_ACCOUNT_DONT_EXIST = 1;
    public static final int LOGIN_ERROR_WRONG_PASSWORD = 2;

    public static final int REGISTER_SUCCEED = 10;
    public static final int REGISTER_ERROR_ACCOUNT_ALREADY_EXISTS = 11;

    private User user;
    private boolean logged;

    @Override
    public void initModel(Object model) {
        User user = (User) model;
    }

    @Override
    public void saveModel(){
        User.updateUserInfo(user);
        System.out.println(user.toString() + "   SAVED");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public int loadUser(String account, String password){
        if(User.queryUserInfoByAccount(account) == null)
            return LOGIN_ERROR_ACCOUNT_DONT_EXIST;
        if(User.checkPassword(account, password) == false)
            return LOGIN_ERROR_WRONG_PASSWORD;

        logged = true;
        user = User.queryUserInfoByAccount(account);
        return LOGIN_SUCCEED;
    }

    public int createUser(String account, String usrname, String password, int usrtype, String usrStrID){
        if(User.queryUserInfoByAccount(account) != null)
            return REGISTER_ERROR_ACCOUNT_ALREADY_EXISTS;

        user = new User(usrname, account, password, usrtype, usrStrID);
        User.addUserInfo(user);
        return REGISTER_SUCCEED;
    }
}
