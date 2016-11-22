package com.example.administrator.androidunittest.dagger2;

import android.content.SharedPreferences;

import com.example.administrator.androidunittest.BuildConfig;
import com.example.administrator.androidunittest.groupshare.JSpec;
import com.example.administrator.androidunittest.mockito.PasswordValidator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static  org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Administrator on 2016/11/22.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginPresenterTest {
    @Test
    public void testLogin_daggerVersion(){
        TestUtils.setupDagger();
        UserManager mockUserManager=mock(UserManager.class);
        Mockito.when(TestUtils.appModule.provideUserManager(any(SharedPreferences.class),any(UserApiService.class))).thenReturn(mockUserManager);

        LoginPresenter presenter=ComponentHolder.getAppComponent().loginPresenter();
        presenter.login("xiaochuang", "xiaochuang_is_handsome");
        verify(mockUserManager).performLogin("xiaochuang", "xiaochuang_is_handsome");
    }
    @Test
    @JSpec(desc = "should fail for mock is not used")
    public void testLogin(){
        UserManager mockUserManager=mock(UserManager.class);
        //因为这里我们不verify PasswordValidator，所以不需要mock这个。
        LoginPresenter presenter=new LoginPresenter(mockUserManager,new PasswordValidator());
        presenter.login("xiaochuang", "xiaochuang_is handsome");
        verify(mockUserManager).performLogin("xiaochuang", "xiaochuang_is handsome");

    }



}