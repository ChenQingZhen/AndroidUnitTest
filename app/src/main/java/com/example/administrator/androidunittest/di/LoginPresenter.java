package com.example.administrator.androidunittest.di;

import com.example.administrator.androidunittest.what.UserManager;

/**
 * Copyright ©  bookegou.com
 * Created by Administrator on 2016/11/22.
 */
public class LoginPresenter {
    private UserManager mUserManager;

    /**
     * 构造方法参数依赖注入
     * @param userManager
     */
    public LoginPresenter(UserManager userManager){
        mUserManager=userManager;
    }

    /**
     * 方法参数依赖注入
     * @param username
     * @param password
     */
    public void login(String username,String password){
        if (username == null || username.length() == 0) return;
        if (password == null || password.length() < 6) return;
        mUserManager.performLogin(username,password);
    }


    public void login(UserManager userManager,String username,String password){
        if (username == null || username.length() == 0) return;
        if (password == null || password.length() < 6) return;

        userManager.performLogin(username,password);
    }

}
