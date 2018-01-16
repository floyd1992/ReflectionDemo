package com.eebbk.reflectiondemo;

import android.os.Message;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wj on 2018/1/16 0016.
 */

public class ClickHandlerImpl implements InvocationHandler {

    private int message;

    public ClickHandlerImpl(int msg){
        message = msg;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d("invoke_click", "invoke method...1111  ---> "+message);
        Message msg = new Message();
        msg.what = 1;
        msg.arg1 = message;
        MainActivity.mMainHandler.sendMessage(msg);
        return null;
    }


}
