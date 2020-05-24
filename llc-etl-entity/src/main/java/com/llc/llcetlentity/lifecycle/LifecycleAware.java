package com.llc.llcetlentity.lifecycle;

/**
 * @author liulichao
 *
 */
public interface LifecycleAware {
    /**
     * 启动一个组件
     */
    void start();

    /**
     * 停止一个组件
     */
    void stop();

    /**
     * 获取一个组件的生命周期
     *
     * @return 组件的生命周期
     */
    LifecycleState getLifecycleState();
}
