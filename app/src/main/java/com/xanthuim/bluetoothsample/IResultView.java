package com.xanthuim.bluetoothsample;

/**
 * Created by Xanthium on 2017/2/20.
 * <p>
 * View层的回调接口
 */

public interface IResultView<T>
{
    void startView(String msg);

    void successView(T bean, int rssi);

    void disconnectedView(String error);

    void connectedView();

    void completeView();

    void valueView(String value);
}
