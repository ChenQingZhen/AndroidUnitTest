package com.example.administrator.androidunittest.dagger2;

import com.example.administrator.androidunittest.mockito.PasswordValidator;

/**
 * Created by Administrator on 2016/11/22.
 */
public class LoginPresenter {
    UserManager mUserManager;
    PasswordValidator mPasswordValidator;

    public LoginPresenter(UserManager mUserManager, PasswordValidator mPasswordValidator) {
        this.mUserManager = mUserManager;
        this.mPasswordValidator = mPasswordValidator;
    }

    public void login(String username,String password){
        if(username==null||username.length()==0)return;
        if(!mPasswordValidator.verifyPassword(password))return;

        mUserManager.performLogin(username,password);
    }
    public boolean isLoggedIn(){
        return false;
    }
}
