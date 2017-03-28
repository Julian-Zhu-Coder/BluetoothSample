package com.xanthuim.bluetoothsample.adapter;

/**
 * Created by Xanthium on 2016/10/20.
 */

public interface IBaseHolder<D>
{
    /**
     * 加载Holder，子类应该去重写
     *
     * @param position 所在的位置
     */
    void bindHolder(int position);

    /**
     * 设置数据
     *
     * @param data
     */
    void setData(D data);

    /**
     * 获得数据
     *
     * @return
     */
    D getData();
}
