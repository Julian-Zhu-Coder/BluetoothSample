package com.xanthuim.bluetoothsample.bluetooth.adapter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by 980086933@qq.com on 2016-12-12.
 * <p>
 * 支持API 18-20，从18开始Android支持低功耗BLE了，其实扫描的核心还是使用{@link BluetoothLeScanner}，
 * API 21以上请查看{@link ArBluetoothPeripheral}。
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class ArBluetoothLeGatt extends ArBluetooth
{
    private BluetoothAdapter.LeScanCallback leScanCallback;

    public ArBluetoothLeGatt(Context context, BluetoothAdapter.LeScanCallback leScanCallback)
    {
        super(context);
        this.leScanCallback = leScanCallback;
    }

    /**
     * 扫描设备
     */
    public void scanLeDevice()
    {
        if (isSupportBluetooth() && isEnabled())
        {
            setScanning(true);
            getBluetoothAdapter().startLeScan(leScanCallback);
            getHandler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    stopLeScan();
                }
            }, getScanPeriod());
        }
    }

    /**
     * 停止扫描
     */
    public void stopLeScan()
    {
        if (isSupportBluetooth() && isEnabled())
        {
            setScanning(false);
            getBluetoothAdapter().stopLeScan(leScanCallback);
        }
    }

}
