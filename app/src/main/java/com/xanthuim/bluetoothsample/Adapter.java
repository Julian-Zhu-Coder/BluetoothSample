package com.xanthuim.bluetoothsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.xanthuim.bluetoothsample.adapter.BaseAdapter;
import com.xanthuim.bluetoothsample.adapter.BaseHolder;


/**
 * Created by Xanthium on 2017/3/1.
 */

public class Adapter extends BaseAdapter<DeviceBean>
{

    @Override
    public View getLayoutView(ViewGroup viewGroup, int viewType)
    {
        return null;
    }

    @Override
    public int getLayoutId(int viewType)
    {
        return R.layout.item;
    }

    @Override
    public BaseHolder createViewHolder(View itemView, Context context, int viewType)
    {
        return new Holder(itemView, context, this);
    }

    public class Holder extends BaseHolder<DeviceBean>
    {
        private CheckedTextView checkedTextView;

        public Holder(View itemView, Context context, RecyclerView.Adapter adapter)
        {
            super(itemView, context, adapter);
            checkedTextView = (CheckedTextView) itemView.findViewById(R.id.checkedTextView);
        }

        @Override
        public void bindHolder(int position)
        {
            checkedTextView.setText(
                    data.getDevice().getAddress() + "\n" + data.getDevice().getName() + "\n");
            checkedTextView.setChecked(data.isChecked());
            //勾选了就禁用
            itemView.setClickable(!data.isChecked());
        }
    }
}
