package com.example.administrator.androidunittest.dagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/11/22.
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    LoginPresenter loginPresenter();

    void inject(LoginActivity loginActivity);
}
