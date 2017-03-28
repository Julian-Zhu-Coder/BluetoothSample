package com.xanthuim.bluetoothsample.bluetooth;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Xanthium on 2017/3/1.
 * <p>
 * 与蓝牙相关的回调
 */

public interface IBluetoothCallback
{
    /**
     * 扫描成功
     *
     * @param bluetoothDevice
     */
    void scanSuccess(BluetoothDevice bluetoothDevice, int rssi);

    /**
     * 连接状态改变
     *
     * @param bluetoothDevice
     */
    void connectionStateChange(BluetoothDevice bluetoothDevice, int newState);

    /**
     * 发现服务
     */
    void serviceDiscoveryed(BluetoothDevice bluetoothDevice, int status);

    /**
     * 监听蓝牙通信值
     *
     * @param value
     */
    void valueChanged(String value);
}
