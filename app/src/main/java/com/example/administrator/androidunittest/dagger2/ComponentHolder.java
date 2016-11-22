package com.example.administrator.androidunittest.dagger2;

/**
 * Created by Administrator on 2016/11/22.
 */
public class ComponentHolder {
    private static AppComponent sAppComponent;
    public static void setAppComponent(AppComponent appComponent){
        sAppComponent=appComponent;
    }
    public static AppComponent getAppComponent(){
        return sAppComponent;
    }
}
