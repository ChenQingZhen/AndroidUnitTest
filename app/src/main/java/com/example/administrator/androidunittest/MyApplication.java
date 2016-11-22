package com.example.administrator.androidunittest;

import android.app.Application;
import android.content.res.Configuration;

import com.example.administrator.androidunittest.dagger2.AppComponent;
import com.example.administrator.androidunittest.dagger2.AppModule;
import com.example.administrator.androidunittest.dagger2.ComponentHolder;
import com.example.administrator.androidunittest.dagger2.DaggerAppComponent;

/**
 * Created by Administrator on 2016/11/22.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent appComponent= DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        ComponentHolder.setAppComponent(appComponent);
    }
}
