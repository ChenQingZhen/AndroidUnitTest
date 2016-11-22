package com.example.administrator.androidunittest.dagger2;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Copyright ©  bookegou.com
 * Created by Administrator on 2016/11/22.
 */
public class UserManager {
    private final SharedPreferences mPref;
    private final UserApiService mRestAdapter;

    @Inject
    public UserManager(SharedPreferences mPref, UserApiService mRestAdapter) {
        this.mPref = mPref;
        this.mRestAdapter = mRestAdapter;
    }
    public void performLogin(String username, String password) {
    }

    public void performRegister(String username, String password) {
    }
}
