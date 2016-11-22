package com.example.administrator.androidunittest.dagger2;
import org.robolectric.RuntimeEnvironment;

import static org.mockito.Mockito.spy;
/**
 * Created by Administrator on 2016/11/22.
 */
public class TestUtils {
    public static final AppModule appModule=spy(new AppModule(RuntimeEnvironment.application));

    public static void setupDagger(){
        AppComponent appComponent=DaggerAppComponent.builder().appModule(appModule).build();
        ComponentHolder.setAppComponent(appComponent);
    }
}
