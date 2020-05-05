package com.llc.llcetlentity.util;

import cn.hutool.core.lang.Snowflake;

/**
 * @author ：llc
 * @date ：Created in 2020/5/5 17:32
 * @description：id生成器
 * @modified By：
 * @version: $
 */
public class IdUtil {
    private static Snowflake snowflake = cn.hutool.core.util.IdUtil.createSnowflake(1, 1);

    private IdUtil() {
    }

    public static long nextId() {
        return snowflake.nextId();
    }
}
