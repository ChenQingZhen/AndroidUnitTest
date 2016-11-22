package com.example.administrator.androidunittest.dagger2;

import android.widget.EditText;

import com.example.administrator.androidunittest.BuildConfig;
import com.example.administrator.androidunittest.R;
import com.example.administrator.androidunittest.mockito.PasswordValidator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import  static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by Administrator on 2016/11/22.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 21)
public class LoginActivityTest {
    @Test
    public void testActivityStart() {
        //创建一个mockAppModule，这里不能spy(AppModule.class)，因为`AppModule`没有默认无参数的Constructor，
        // 也不能mock(AppModule.class),原因是dagger2的约束，Provider方法不能返回null，除非用@Nullable修饰
        AppModule mockAppModule=spy(new AppModule(RuntimeEnvironment.application));
        //创建一个mockLoginPresenter
        LoginPresenter mockLoginPresenter=mock(LoginPresenter.class);
        //当mockAppModule的provideLoginPresenter()方法被调用时，让它返回mockLoginPresenter
        Mockito.when(mockAppModule.provideLoginPresenter(any(UserManager.class),any(PasswordValidator.class))).thenReturn(mockLoginPresenter);
        //用mockAppModule来创建DaggerAppComponent
        AppComponent appComponent=DaggerAppComponent.builder().appModule(mockAppModule).build();
        //记得放到ComponentHolder里面，这样LoginActivity#onCreate()里面通过ComponentHolder.getAppComponent()获得的就是这里创建的appComponent
        ComponentHolder.setAppComponent(appComponent);
        //启动LoginActivity，onCreate方法会得到调用，里面的mLoginPresenter通过dagger2获得的，将是mockLoginPresenter
        LoginActivity loginActivity= Robolectric.setupActivity(LoginActivity.class);
        ((EditText)loginActivity.findViewById(R.id.username)).setText("xiaochuang");
        ((EditText)loginActivity.findViewById(R.id.password)).setText("xiaochuang is handsome");
        loginActivity.findViewById(R.id.login).performClick();
        verify(mockLoginPresenter).login("xiaochuang", "xiaochuang is handsome");

    }

    /**
     * 上一个方法的简化版本
     */
    @Test
    public void testActivityStartsSimple() {
        //创建一个mockAppModule，这里不能spy(AppModule.class)，因为`AppModule`没有默认无参数的Constructor，
        // 也不能mock(AppModule.class),原因是dagger2的约束，Provider方法不能返回null，除非用@Nullable修饰
        TestUtils.setupDagger();
        //创建一个mockLoginPresenter
        LoginPresenter mockLoginPresenter=mock(LoginPresenter.class);
        //当mockAppModule的provideLoginPresenter()方法被调用时，让它返回mockLoginPresenter
        Mockito.when(TestUtils.appModule.provideLoginPresenter(any(UserManager.class),any(PasswordValidator.class))).thenReturn(mockLoginPresenter);

        //启动LoginActivity，onCreate方法会得到调用，里面的mLoginPresenter通过dagger2获得的，将是mockLoginPresenter
        LoginActivity loginActivity= Robolectric.setupActivity(LoginActivity.class);
        ((EditText)loginActivity.findViewById(R.id.username)).setText("xiaochuang");
        ((EditText)loginActivity.findViewById(R.id.password)).setText("xiaochuang is handsome");
        loginActivity.findViewById(R.id.login).performClick();
        verify(mockLoginPresenter).login("xiaochuang", "xiaochuang is handsome");

    }

}