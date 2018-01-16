package com.eebbk.reflectiondemo.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wj on 2018/1/16 0016.
 */

public class ReflectionUtil {

    public static void invokeWithCallback(String className, String methodName, String[] callback,Object object,
                                          Object[] preArgs, InvocationHandler handler){
        try {
            Class<?> clz = Class.forName(className);
            Class[] arrays = new Class[callback.length];
            for(int j=0;j<callback.length;j++){
                arrays[j] = Class.forName(callback[j]);
            }
            Object obj = Proxy.newProxyInstance(clz.getClassLoader(), arrays, handler);
            if(null==preArgs){
                Method m3 = clz.getDeclaredMethod(methodName, arrays);
                m3.invoke(object, obj);
            }else{
                Class[] addPre = new Class[preArgs.length+arrays.length];
                for(int i=0;i<preArgs.length+arrays.length;i++){
                    addPre[i] = i<preArgs.length?preArgs.getClass():arrays[i-preArgs.length];
                }
                Method m3 = clz.getDeclaredMethod(methodName, addPre);
                m3.invoke(object, preArgs, obj);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
