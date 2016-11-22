package com.example.administrator.androidunittest.dagger2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.administrator.androidunittest.mockito.PasswordValidator;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/11/22.
 */
@Module
public class AppModule {

    private final Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }
    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com")
                .build();
        return retrofit;
    }
    @Provides
    @Singleton
    public UserApiService provideUserApiService(Retrofit retrofit){
        return retrofit.create(UserApiService.class);
    }
    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    @Provides
    public UserManager provideUserManager(SharedPreferences preferences,UserApiService service){
        return new UserManager(preferences,service);

    }
    @Provides
    public PasswordValidator providePasswordValidator(){
        return new PasswordValidator();
    }
    @Provides
    public LoginPresenter provideLoginPresenter(UserManager userManager,PasswordValidator validator){
        return new LoginPresenter(userManager,validator);
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }
}
