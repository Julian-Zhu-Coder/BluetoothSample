package com.xanthuim.bluetoothsample;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.LogUtils;
import com.xanthuim.bluetoothsample.adapter.OnRecyclerViewItemClickListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
        implements OnRecyclerViewItemClickListener<DeviceBean>, IResultView<BluetoothDevice>
{
    private Adapter adapter;
    private RecyclerView rv;
    private AlertDialog alertDialog;

    private BluetoothService bluetoothUnity;
    private DeviceBean deviceBean;
    private Activity activity;

    public MainActivityFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        rv = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);
        alertDialog = new AlertDialog.Builder(getContext()).setMessage("连接中...")
                .setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        bluetoothUnity.disconnectDevice();
                    }
                }).create();
        alertDialog.setCanceledOnTouchOutside(false);

        adapter = new Adapter();
        adapter.setOnItemClickListener(this);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(adapter);
        return rv;
    }

    @Override
    public void onItemClick(View view, DeviceBean data)
    {
        this.deviceBean = data;
        alertDialog.show();
        bluetoothUnity.connectDevice(data.getDevice().getAddress());
    }

    @Override
    public void startView(String msg)
    {
        alertDialog.show();
    }

    @Override
    public void successView(final BluetoothDevice bean, final int rssi)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                adapter.addData(new DeviceBean(bean, rssi));
            }
        });
    }

    @Override
    public void disconnectedView(final String error)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                LogUtils.d(getClass().getSimpleName(),
                        error + "---" + Thread.currentThread().getName());
                alertDialog.setMessage(error);
                alertDialog.dismiss();
            }
        });
    }

    public void connectedView()
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (deviceBean != null)
                {
                    deviceBean.setChecked(true);
                    adapter.setData(deviceBean);
                }
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void completeView()
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                alertDialog.dismiss();
                if (deviceBean == null)
                {
                    return;
                }
                deviceBean.setChecked(true);
                adapter.setData(deviceBean);
            }
        });
    }

    /**
     * 当数据有改变的就会触发
     *
     * @param value
     */
    @Override
    public void valueView(final String value)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                activity.setTitle(value);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        init();
    }


    /**
     * 初始化
     */
    private void init()
    {
        this.bluetoothUnity = new BluetoothService(this, getContext());
    }

    /**
     * 开始扫描
     */
    public void scan()
    {
        adapter.getDatas().clear();
        adapter.notifyDataSetChanged();
        bluetoothUnity.startScan();
    }
}
