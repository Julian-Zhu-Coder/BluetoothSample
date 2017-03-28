package com.xanthuim.bluetoothsample.adapter;

import android.view.View;

/**
 * RecyclerView点击条目的接口监听
 */
public interface OnRecyclerViewItemClickListener<T>
{
    /**
     * 条目点击
     *
     * @param view     点击的View
     * @param data     点击的对象数据
     */
    void onItemClick(View view, T data);
}