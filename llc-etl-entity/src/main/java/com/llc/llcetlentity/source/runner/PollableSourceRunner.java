package com.llc.llcetlentity.source.runner;

import com.llc.llcetlentity.lifecycle.LifecycleState;

/**
 * @author ：llc
 * @date ：Created in 2020/5/24 16:53
 * @description： {@link SourceRunner}的一种实现，用来驱动{@link com.llc.llcetlentity.source.Source}
 *
 *  内部维护一个线程，循环主动拉取source 的数据
 *
 * @modified By：
 * @version: $
 */
public class PollableSourceRunner extends SourceRunner{
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public LifecycleState getLifecycleState() {
        return null;
    }
}
