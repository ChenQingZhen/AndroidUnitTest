package com.example.administrator.androidunittest.mockito;

import com.example.administrator.androidunittest.groupshare.JSpec;
import com.example.administrator.androidunittest.groupshare.NetworkCallback;
import com.example.administrator.androidunittest.what.UserManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Copyright ©  bookegou.com
 * Created by Administrator on 2016/11/21.
 */
public class LoginPresenterTest {


    @Before
    public void setup() {


    }

    @Test
    public void testLogin() throws Exception {
        LoginPresenter loginPresenter;
        UserManager userManager;
        loginPresenter = new LoginPresenter();
        userManager = Mockito.mock(UserManager.class);
        loginPresenter.setUserManager(userManager);
        loginPresenter.login("dainel_chen", "123456");
        Mockito.verify(userManager).performLogin(anyString(), anyString());
    }

    @Test
    @JSpec(desc = "should mock return given value")
    public void test() {
        PasswordValidator passwordValidator = Mockito.mock(PasswordValidator.class);
        Mockito.when(passwordValidator.verifyPassword("123456")).thenReturn(true);
        Assert.assertTrue(passwordValidator.verifyPassword("123456"));
        //无论输入参数是什么，返回值都是true
        Mockito.when(passwordValidator.verifyPassword(anyString())).thenReturn(true);
        Assert.assertTrue(passwordValidator.verifyPassword("321"));
    }
    @Test
    @JSpec(desc = "should mock perform certain action")
    public void testMockAction(){
        UserManager userManager=Mockito.mock(UserManager.class);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args= invocation.getArguments();
                NetworkCallback callback= (NetworkCallback) args[2];
                callback.onFailure(500, "Server error");
                return 500;
            }
        }).when(userManager).performLogin(anyString(),anyString(),any(NetworkCallback.class));
        userManager.performLogin("daniel","123",Mockito.mock(NetworkCallback.class));
    }
    @Test
    public void testSpy(){
//        PasswordValidator mockValidator=Mockito.mock(PasswordValidator.class);
//        Assert.assertTrue(mockValidator.verifyPassword("xiaochuang_is_handsome"));//返回false，因为verifyPassword不会被执行
        PasswordValidator spyValidator=Mockito.spy(PasswordValidator.class);
        Assert.assertTrue(spyValidator.verifyPassword("xiaochuang_is_handsome"));//返回true,verifyPassword会被执行
        //spy对象的方法也可以指定特定的行为
        Mockito.when(spyValidator.verifyPassword(anyString())).thenReturn(true);
        Mockito.verify(spyValidator).verifyPassword("123");
    }
    @Test
    public void testLoginCallbackVersion() throws Exception {

    }
}