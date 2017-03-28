package com.xanthuim.bluetoothsample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Xanthium on 2016/10/20.
 * <p>
 * 说明：适配器相关的接口，与RecyclerView配套使用
 */
public interface IBaseAdapter<D, H>
{
    /**
     * 返回布局，无论是动态创建还是加载布局都可以
     *
     * @param viewGroup
     * @return
     */
    View getLayoutView(ViewGroup viewGroup, int viewType);

    /**
     * 返回布局id
     *
     * @return
     */
    int getLayoutId(int viewType);

    /**
     * 创建ViewHolder
     *
     * @param itemView
     * @return
     */
    H createViewHolder(View itemView, Context context, int viewType);

    /**
     * 获得数据集
     *
     * @return
     */
    List<D> getDatas();

    /**
     * 设置数据集
     *
     * @param dataes
     */
    void setDatas(List<D> dataes);

    /**
     * 添加数据集
     *
     * @param dataes
     */
    void addDatas(List<D> dataes);
}
