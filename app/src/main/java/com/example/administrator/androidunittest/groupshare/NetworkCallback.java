package com.example.administrator.androidunittest.groupshare;

/**
 * Created by xiaochuang on 3/2/16.
 */
public interface NetworkCallback  {
    void onSuccess(Object data);
    void onFailure(int code, String msg);
}
