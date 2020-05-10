package com.llc.etl.admin.query;

import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.Page;
import lombok.Data;

/**
 * @author ：llc
 * @date ：Created in 2020/5/10 15:38
 * @description：node查询类
 * @modified By：
 * @version: $
 */
@Data
public class NodeQuery extends Page {
    private String name;
    private String ip;

    public static void main(String[] argus){
        String str = "aa";
        String aa = "aa";
        System.out.println(SecureUtil.md5(str));
        System.out.println(SecureUtil.md5(aa));
    }
}
