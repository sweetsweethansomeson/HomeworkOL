package com.hjq.demo.controller;

import com.hjq.demo.common.MyController;
import com.hjq.demo.model.User;

public class LoginController implements MyController {

    private User user;
    private boolean logged;

    public LoginController(String usrname, String password){
        //此处假设已经过账号密码验证

        user = new User(usrname, password);
        initModel(user);

        logged = true;
    }

    @Override
    public void initModel(Object model) {
        User user = (User) model;

    }

    @Override
    public void saveModel(){
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
}
